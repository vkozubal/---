package org.pti.poster.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

@Getter
public final class Post {

    public Post(String text, Collection<Tag> postTags) {
        creationDate = Calendar.getInstance();
        if (postTags == null) {
            postTags = Collections.emptyList();
        }
        this.postTags = ImmutableList.copyOf(postTags);
        this.text = text;
    }

    private final Collection<Tag> postTags;

    private Long id = Long.valueOf(RandomStringUtils.randomNumeric(7));
    private final String text;

    private final Calendar creationDate;

    public Collection<Tag> getPostTags() {
        return ImmutableList.copyOf(postTags);
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
