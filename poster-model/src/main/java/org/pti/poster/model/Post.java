package org.pti.poster.model;

import com.google.common.collect.ImmutableList;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Document(collection = "posts")
@TypeAlias("post")
@Getter
@ToString
// rest doesn't work with final classes todo
/*
{
        "timestamp": 1440356035995,
        "status": 500,
        "error": "Internal Server Error",
        "exception": "java.lang.IllegalArgumentException",
        "message": "Cannot subclass final class class org.pti.poster.model.Post",
        "path": "/rest/posts"
        }
*/
public /*final*/ class Post extends AbstractDocument {

    private final Version version;

    private final Collection<Tag> postTags;
    
    private final String text;
    
    private final Date creationDate;

    public Post() {
        this(null, new ArrayList<>());
    }
    public Post(String text) {
        this(text, new ArrayList<>());
    }

    public Post(String text, Collection<Tag> postTags) {
        this(text, postTags, 0, null);
    }

    public Post(String text, Collection<Tag> postTags, Integer version, Long prevPostId) {
        creationDate = new Date();
        if (postTags == null) {
            postTags = Collections.emptyList();
        }
        this.postTags = ImmutableList.copyOf(postTags);
        this.text = text;
        this.version = new Version(prevPostId, ++version);
    }

    public Collection<Tag> getPostTags() {
        return ImmutableList.copyOf(postTags);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Version implements Serializable{
        private Long previousVersionId;
        private Integer versionNumber;
    }
}
