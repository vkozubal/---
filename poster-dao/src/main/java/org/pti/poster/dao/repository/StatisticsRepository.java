package org.pti.poster.dao.repository;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import org.joda.time.DateTime;
import org.pti.poster.dao.Constants.DATABASE;
import org.pti.poster.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class StatisticsRepository implements IStatisticsRepository {

    @Autowired private PostRepository postRepository;
    
    public static final String MAP_FUNCTION = "" +
            "function () {" +
            "  for (var i = 0; i < this.roles.length; i++) {" +
            "    emit(this.roles[i], this)" +
            "  }" +
            "}";

    public static final String REDUCE_FUNCTION = "" +
            "function (key, values) {" +
            "  var reducedVal = {count: 0, active: 0};" +
            "  for (var i = 0; i < values.length; i++) {" +
            "    reducedVal.count++;" +
            "    if (values[i].active) {" +
            "      reducedVal.active++;" +
            "    }" +
            "  }" +
            "return reducedVal;" +
            "}";

    @Autowired MongoOperations ops;



    @Override
    public Iterable<Post> getAllPostsForSpecificDate(Date dateTime) {
        DateTime now = new DateTime(dateTime);
        return postRepository.inRange(now.withTimeAtStartOfDay().toDate(),
                now.plusDays(1).withTimeAtStartOfDay().toDate());
    }
    
    @Override
    public Iterable<DBObject> getStatByRoles() {
        DBCollection collection = ops.getCollection(DATABASE.USERS_COLLECTION_NAME);

        MapReduceCommand cmd = new MapReduceCommand(collection, MAP_FUNCTION, REDUCE_FUNCTION, null, MapReduceCommand.OutputType.INLINE, null);
        MapReduceOutput out = collection.mapReduce(cmd);
        return out.results();
    }
}