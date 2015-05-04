package org.pti.poster.service;

import org.pti.poster.model.Post;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestData {
    
    public static List<Post> getPosts() {
        return Arrays.asList(
                new Post("first post", Collections.<Post.Tag>emptyList()),
                new Post("second post", Collections.<Post.Tag>emptyList()),
                new Post("third post", Collections.<Post.Tag>emptyList()),
                new Post("fourth post", Collections.<Post.Tag>emptyList()));
    }
}