package org.pti.poster.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthorizationController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "OK";
	}

}
