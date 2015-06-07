package org.pti.poster.service.interfaces;

import com.mongodb.DBObject;
import org.pti.poster.model.Post;

import java.util.Date;

public interface IShellCommandsService {
    
    Iterable<Post> getPostsForASpecificDate(Date dateTime);

    Iterable<DBObject> getStatByRoles();
}
