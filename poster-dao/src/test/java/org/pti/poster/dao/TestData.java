package org.pti.poster.dao;

import com.google.common.collect.Lists;
import org.pti.poster.model.Post;

import java.util.Arrays;
import java.util.List;

public class TestData {

    public static List<Post> getPosts() {
        return Arrays.asList(
                new Post("first post", Lists.newArrayList(Tags.tagFourth, Tags.tagFirst)),
                new Post("second post", Lists.newArrayList(Tags.tagFourth)),
                new Post("third post", Lists.newArrayList(Tags.tagFifth, Tags.tagSecond, Tags.tagFirst)),
                new Post("fourth post", Lists.newArrayList(Tags.tagFourth, Tags.tagFirst)),
                new Post("fifth post", Lists.newArrayList(Tags.tagFourth, Tags.tagFirst)),
                new Post("sixth post", Lists.newArrayList(Tags.tagFifth, Tags.tagZero, Tags.tagThird, Tags.tagFirst)));
    }

    private static final class Tags {
        public static final Post.Tag tagZero = new Post.Tag("tag number 0");
        public static final Post.Tag tagFirst = new Post.Tag("tag number 1");
        public static final Post.Tag tagSecond = new Post.Tag("tag number 2");
        public static final Post.Tag tagThird = new Post.Tag("tag number 3");
        public static final Post.Tag tagFourth = new Post.Tag("tag number 4");
        public static final Post.Tag tagFifth = new Post.Tag("tag number 5");
    }
}
