package org.pti.poster.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pti.poster.model.Post;

import java.util.Collection;

@NoArgsConstructor
@Data
public class PostView {

    private String text;
    @JsonIgnore
    private Long id;
    private Collection<Post.Tag> tags;

    public static PostView fromPost(Post post) {
        PostView postView = new PostView();
        postView.setText(post.getText());
        postView.setId(post.getId());
        postView.setTags(post.getPostTags());
        return postView;
    }

    public static Post fromView(PostView view) {
        return new Post(view.getText(), view.getTags());
    }
}
