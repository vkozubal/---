package org.pti.poster.rest;

import org.pti.poster.PersonRespository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/")
public class ContactsService {

    @Inject
    private PersonRespository personRespository;
    
    @GET
    @Path("hello")
    public  String test(){
        return personRespository.sayHello();
    }
}
