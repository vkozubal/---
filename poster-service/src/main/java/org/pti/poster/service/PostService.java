package org.pti.poster.service;


import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.pti.poster.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

@Service
public class PostService {
    private Collection<Post> inMemoryStore = new ArrayList<>();

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
     * @return the id of the
     */
    public long addPost(Post post) {
        this.inMemoryStore.add(post);
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
}
