package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // add our user for in memory authentication

        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("abc").password("abc").roles("EMPLOYEE"))
                .withUser(userBuilder.username("bcd").password("test1").roles("MANAGER", "EMPLOYEE"))
                .withUser(userBuilder.username("efg").password("test1").roles("ADMIN", "EMPLOYEE"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/leaders/**").hasRole("MANAGER")
                .antMatchers("/systems/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")  // login 시도하는 url
                .loginProcessingUrl("/authenticateTheUser") // login processing 하는 page
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
