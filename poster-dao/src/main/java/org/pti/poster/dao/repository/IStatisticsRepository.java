package org.pti.poster.dao.repository;

import com.mongodb.DBObject;

public interface IStatisticsRepository {
    
    Iterable<DBObject> getStatByRoles();
}
