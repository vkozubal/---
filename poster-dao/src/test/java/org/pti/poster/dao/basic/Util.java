package org.pti.poster.dao.basic;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.MongoOperations;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Util {
    
    public static Date getDateFromString(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(org.pti.poster.model.Constants.DATE_PATTERN, Locale.ENGLISH);
        return format.parse(date);
    }
    
    /* inserts data into the database from json file */
    public static void insertDateToDbFromJsonFile(MongoOperations mongoOps, InputStream asStream, String collectionName) throws IOException {
        DBCollection usersCollection = mongoOps.getCollection(collectionName);
        String jsonString = IOUtils.toString(asStream);

        // convert JSON to DBObject directly
        BasicDBList dbEntities = (BasicDBList) JSON.parse(jsonString);
        for(BasicDBObject i : getAsList(dbEntities)){
            usersCollection.insert(i);
        }
    }
    
    @SuppressWarnings("unchecked")
    private static List<BasicDBObject> getAsList(BasicDBList dbUsers) {
        return (List<BasicDBObject>)(List<?>)  Arrays.asList(dbUsers.toArray());
    }
}
