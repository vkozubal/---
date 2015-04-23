package org.pti.poster.rest;

import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.dto.post.UnregisteredPostDto;
import org.pti.poster.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RequestMapping("/api/post")
@RestController
public class PostController {

	@Autowired
	PostService postService;

	@RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
	public
	@ResponseBody
	GenericPostDto getPost(@PathVariable(value = "id") String id) {
		return postService.findPostById(id);
	}

	@RequestMapping(value = "/get/last/{last}", method = RequestMethod.GET)
	public
	@ResponseBody
	GenericPostCollectionDto getLastPosts(@PathVariable("last") int last) {
		return postService.getLastPosts(last);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public
	@ResponseBody
	GenericPostDto save(@RequestBody UnregisteredPostDto post) {
		return postService.savePost(post);
	}

}
