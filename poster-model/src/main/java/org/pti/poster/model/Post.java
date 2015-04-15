package org.pti.poster.model;

import com.google.common.collect.ImmutableList;
import lombok.*;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

@Getter
public final class Post {

    private final Version version;
    private final Collection<Tag> postTags;
    private final String text;
    private final Calendar creationDate;
    private Long id = Long.valueOf(RandomStringUtils.randomNumeric(7));
    
    public Post(){
        this(null, new ArrayList<Tag>());
    }
    
    public Post(String text, Collection<Tag> postTags) {
        this(text, postTags, 0, null);
    }

    public Post(String text, Collection<Tag> postTags, Integer version, Long prevPostId) {
        creationDate = Calendar.getInstance();
        if (postTags == null) {
            postTags = Collections.emptyList();
        }
        this.postTags = ImmutableList.copyOf(postTags);
        this.text = text;
        this.version = new Version( prevPostId, ++version);
    }

    public Collection<Tag> getPostTags() {
        return ImmutableList.copyOf(postTags);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Version {
        private Long previousVersionId;
        private Integer versionNumber;
    }

    @NoArgsConstructor
    @EqualsAndHashCode
    @Data
    public static class Tag {
        private String text;

        public Tag(String text) {
            this.text = text;
        }
    }
}
