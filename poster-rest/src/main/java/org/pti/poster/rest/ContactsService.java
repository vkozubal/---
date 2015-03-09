package org.pti.poster.rest;

import org.pti.poster.PersonService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/")
public class ContactsService {

    @Inject
    private PersonService personService;
    
    @GET
    @Path("hello")
    public  String test(){
        return personService.sayHello();
    }
}
