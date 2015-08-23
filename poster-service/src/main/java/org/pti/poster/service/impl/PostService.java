package org.pti.poster.service.impl;


import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.pti.poster.dao.repository.PostRepository;
import org.pti.poster.model.Person;
import org.pti.poster.model.Post;
import org.pti.poster.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Slf4j
public class PostService implements IPostService {

    @Autowired private PostRepository postRepository;
    @Autowired private PersonService personService;

    @Override
    public void addPost(UserDetails person, Iterable<Post> posts) {
        for (Post post : posts) {
            addPost(person, post);
        }
    }

    @Override
    public BigInteger addPost(UserDetails details, Post post) {
        Person person = getPerson(details.getUsername());
        person.addPost(post); // add post to user
        Post saved = postRepository.save(post);
        personService.save(person); // and save
        log.info("Added post `{}` for user `{}` ", saved.getId(), details.getUsername());
        return saved.getId();
    }

    @Override
    public void remove(UserDetails details, final BigInteger id) {
        log.info("Removed post with id {}", id);
        Person person = getPerson(details.getUsername());
        person.getPosts().remove(postRepository.findOne(id));
        personService.save(person);
    }

    @Override
    public Post getById(UserDetails details, final BigInteger id) {
        Person person = getPerson(details.getUsername());

        return FluentIterable.from(person.getPosts())
                .firstMatch(element -> element.getId().equals(id))
                .orNull();
    }

    @Override
    public List<Post> getAllPostsForUser(UserDetails userDetails) {
        Person person = personService.loadUserByUsername(userDetails.getUsername());
        return Lists.newArrayList(person.getPosts());
    }


    private Person getPerson(String userName) {
        return personService.loadUserByUsername(userName);
    }
}
