package org.pti.poster.rest.resources;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.pti.poster.model.Person;
import org.pti.poster.rest.TokenUtils;
import org.pti.poster.rest.view.TokenTransfer;
import org.pti.poster.rest.view.UserTransfer;
import org.pti.poster.service.impl.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Providers user management of application", position = 3)
@RequestMapping("rest/user")
@RestController
@Produces(MediaType.APPLICATION_JSON)
public class UserManagementResource {

    @Autowired private PersonService personService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    @ApiOperation(value = "Returns a user by id.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public UserTransfer getUser() {
        UserDetails userDetails = Utils.getCurrentUserDetails();
        return new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails));
    }

    private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
        Map<String, Boolean> roles = new HashMap<>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }
        return roles;
    }

    @ApiOperation(value = "Creates a new user.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BigInteger addUser(@RequestBody Person person) {
        return personService.addUser(person);
    }


    @ApiOperation(value = "Deletes existing user.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable BigInteger id) {
        personService.remove(id);
    }

    /**
     * @param username The name of the user.
     * @param password The password of the user.
     * @return A transfer containing the authentication token.
     */
    @ApiOperation(value = "Authenticates a user and creates an authentication token.")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public TokenTransfer authenticate(@FormParam("username") String username, @FormParam("password") String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
         * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
        UserDetails userDetails = this.personService.loadUserByUsername(username);
        return new TokenTransfer(TokenUtils.createToken(userDetails));
    }
}
