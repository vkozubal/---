package org.pti.poster.service.impl;

import com.mongodb.DBObject;
import org.pti.poster.dao.repository.StatisticsRepository;
import org.pti.poster.model.Post;
import org.pti.poster.service.interfaces.IShellCommandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
/**
 * TODO check here if user has permissions for doing this *
 * require ADMIN_ROLE permissions *
 */
public class ShellCommandsService implements IShellCommandsService {

    @Autowired private StatisticsRepository statisticsRepository;
    
   
    @Override
    public Iterable<Post> getPostsForASpecificDate(Date dateTime){
        return statisticsRepository.getAllPostsForSpecificDate(dateTime);
    }
    
    @Override
    public Iterable<DBObject> getStatByRoles(){
        return statisticsRepository.getStatByRoles();
    }
}
