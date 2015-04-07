package org.pti.poster;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pti.poster.boot.Application;
import org.pti.poster.boot.security.SpringWebMvcInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringWebMvcInitializer.class,
        SpringApplicationConfiguration.class, FirstTest.class, Application.class})
@WebAppConfiguration
public class FirstTest {

    @Autowired
    private WebApplicationContext wac;
//    @InjectMocks
//    PostResource postResource;


    private MockMvc mockMvc;

    @Before
    public void setup() {

        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.
        mockMvc = MockMvcBuilders.standaloneSetup(wac).build();
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(
                get("/rest/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Bean
    @Qualifier("authenticationManager")
    public org.springframework.security.authentication.AuthenticationManager getAuthManager() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
                Object principal = new User("user", "password", Arrays.asList(simpleGrantedAuthority));
                return new TestingAuthenticationToken(principal, null);
            }
        };
    }
}
