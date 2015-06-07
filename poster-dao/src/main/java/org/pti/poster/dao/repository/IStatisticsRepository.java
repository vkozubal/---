package org.pti.poster.dao.repository;

import com.mongodb.DBObject;
import org.pti.poster.model.Post;

import java.util.Date;

public interface IStatisticsRepository {

    Iterable<Post> getAllPostsForSpecificDate(Date dateTime);

    Iterable<DBObject> getStatByRoles();
}
