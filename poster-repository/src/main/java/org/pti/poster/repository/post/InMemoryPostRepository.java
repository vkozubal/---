package org.pti.poster.repository.post;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.pti.poster.model.AbstractPost;
import org.pti.poster.model.post.GenericPost;
import org.pti.poster.model.post.GenericPostType;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository("inMemoryPostRepository")
public class InMemoryPostRepository implements PostRepository {

	public final static PostRepositoryType TYPE = PostRepositoryType.INMEMORY;
	Map<String, GenericPost> allPosts;

	@PostConstruct
	public void init() {
		allPosts = new LinkedHashMap<>();
	}

	@Override
	public GenericPost getPostById(String id) {
		return (GenericPost) allPosts.get(id);
	}

	@Override
	public List<GenericPost> getLastPosts(int number) {
		List<GenericPost> result = new ArrayList<>();
		List<Map.Entry<String, GenericPost>> entryList = new ArrayList<>(allPosts.entrySet());

		int endIndex = entryList.size();
		int startIndex = endIndex - number;

		List<Map.Entry<String, GenericPost>> lastEntries = entryList.subList(startIndex, endIndex);
		for (Map.Entry<String, GenericPost> entry : lastEntries) {
			result.add(entry.getValue());
		}

		return result;
	}

	@Override
	public GenericPost savePost(GenericPost post) {
		String id = UUID.randomUUID().toString();
		String date = getCurrentDateAsString();

		GenericPost savedPost = new GenericPost(GenericPostType.REGISTERED_POST, id, date, post.getUserId(), post.getText());
		allPosts.put(id, savedPost);

		return savedPost;
	}

	private String getCurrentDateAsString() {
		DateTime date = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(AbstractPost.DATE_FORMAT);
		return date.toString(fmt);
	}
}