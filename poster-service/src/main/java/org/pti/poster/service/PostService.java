package org.pti.poster.service;


import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.pti.poster.model.Post;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    private static Collection<Post> inMemoryStore ;
    {
        // mocked initializtion
        inMemoryStore= new ArrayList<Post>() {{
            add(new Post("new Text1", Collections.<Post.Tag>emptyList()));
            add(new Post("new Text2", Collections.<Post.Tag>emptyList()));
            add(new Post("new Text3", Collections.<Post.Tag>emptyList()));
            add(new Post("new Text4", Collections.<Post.Tag>emptyList()));
        }};
    } 

    public Collection<Post> filterPostByDate(Calendar calendar) {
        Collection<Post> result = new ArrayList<>();
        for (Post post : inMemoryStore) {
            if (theSameDay(post.getCreationDate(), calendar)) {
                result.add(post);
            }
        }
        return result;
    }

    /**
     * adds post to persistence
     *
     * @param post the object to be added
     * @return the id of the created post
     */
    public long addPost(Post post) {
        inMemoryStore.add(post);
        return post.getId();
    }

    public long update(Post post) {
        return post.getId();
    }

    public Collection<Post> getAllPosts() {
        return inMemoryStore;
    }

    public void remove(final Long id) {
        inMemoryStore = Collections2.filter(inMemoryStore, new Predicate<Post>() {
            @Override
            public boolean apply(Post post) {
                return post.getId().equals(id);
            }
        });
    }

    public Post getById(final Long id) {
        for (Post u : inMemoryStore) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    private boolean theSameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * todo review this method
     * return  adds a tag to post with id {@param postId}  
     * @param postId id of {@link org.pti.poster.model.Post} to be updated
     * @param tag {@link org.pti.poster.model.Post.Tag}
     * @return id of updated created post
     */
    public Long addTag(Long postId, Post.Tag tag){
        getById(postId).getPostTags().add(tag);
        return postId;
    }
}
