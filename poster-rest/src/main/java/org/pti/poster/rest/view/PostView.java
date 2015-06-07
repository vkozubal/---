package org.pti.poster.rest.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pti.poster.model.Post;

import java.math.BigInteger;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostView {

    private String text;
    private Collection<Post.Tag> tags;
    private BigInteger id;

    public static PostView fromPost(Post post) {
        PostView postView = new PostView();
        postView.setText(post.getText());
        postView.setId(post.getId());
        postView.setTags(post.getPostTags());
        return postView;
    }

    public static Post transformToPost(PostView view) {
        return new Post(view.getText(), view.getTags());
    }
}
