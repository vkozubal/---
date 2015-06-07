package org.pti.poster.integration.security;

import org.pti.poster.model.Constants;
import org.pti.poster.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IPersonService personService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = new ShaPasswordEncoder(); // use this if you want too store encrypted passwords in the db.
        PlaintextPasswordEncoder encoder = new PlaintextPasswordEncoder();
        auth.userDetailsService(personService).passwordEncoder(encoder);

//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles(Constants.SECURITY.USER_ROLE.role).and()
//                .withUser("admin").password("password").roles(Constants.SECURITY.getAllRolesAsArray());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/webjars/swagger-ui/**")
                .antMatchers("/api-docs/**");
//                .antMatchers("/rest/user/authenticate");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup", "/about", "/rest/user/authenticate").permitAll() // #4
                .antMatchers("/admin/**").hasRole(Constants.SECURITY.ADMIN_ROLE.role) // #6
//                .antMatchers("/rest/user/**").hasRole(Constants.SECURITY.ADMIN_ROLE.role)
//                .antMatchers("/rest/**").hasRole(Constants.SECURITY.USER_ROLE.role)
                .anyRequest().authenticated()
                .and().formLogin().permitAll();
    }
}