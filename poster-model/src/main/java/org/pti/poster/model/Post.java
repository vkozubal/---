package org.pti.poster.model;

import com.google.common.collect.ImmutableList;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Document(collection = "posts")
@TypeAlias("post")
@Getter
public final class Post extends AbstractDocument {

    private final Version version;

    private final Collection<Tag> postTags;
    
    private final String text;
    
    private final Date creationDate;

    public Post() {
        this(null, new ArrayList<Tag>());
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

    public Post(String text) {
        this(text, new ArrayList<Tag>());
    }

    public Collection<Tag> getPostTags() {
        return ImmutableList.copyOf(postTags);
    }

    @Override
    public String toString() {
        return "Post{" +
                "version=" + version +
                ", postTags=" + postTags +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Version {

        private Long previousVersionId;
        private Integer versionNumber;

        @Override
        public String toString() {
            return "Version{" +
                    "previousVersionId=" + previousVersionId +
                    ", versionNumber=" + versionNumber +
                    '}';
        }
    }

    @NoArgsConstructor
    @EqualsAndHashCode
    @Data
    public static class Tag {
        private String text;

        public Tag(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Tag{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }
    
    
}
