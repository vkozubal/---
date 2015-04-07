package org.pti.poster.boot.security;

import org.pti.poster.model.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles(Constants.SECURITY.USER_ROLE.role).and()
                .withUser("admin") // #2
                .password("password").roles(Constants.SECURITY.getAllRolesAsArray());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/swagger/**")
//                .antMatchers("/app/api/rest/user/authenticate")  // hack to  make something work. TODO
                .antMatchers("/api-docs/**"); // #3
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
                .antMatchers("/signup", "/about", "/rest/user/authenticate").permitAll() // #4
                .antMatchers("/admin/**").hasRole(Constants.SECURITY.ADMIN_ROLE.role) // #6
//                .antMatchers("/app/api/rest/user/").hasRole(Constants.SECURITY.USER_ROLE.role)
                .anyRequest().authenticated() // 7
                .and()
                .formLogin().loginPage("/login") // #9 todo
                .permitAll(); // #5
    }
}
