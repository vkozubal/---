package org.pti.poster;


import org.springframework.stereotype.Repository;

@Repository
public class PersonRespository {

    public PersonRespository(){
    }
    
    public String sayHello(){
        return "hello";
    }
}
