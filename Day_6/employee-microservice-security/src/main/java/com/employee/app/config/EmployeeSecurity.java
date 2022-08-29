package com.employee.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class EmployeeSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/get/employee").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/save/employee").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/update/employee").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/delete/employee").hasAnyRole("ADMIN")
                .and().csrf().disable().headers().frameOptions().disable();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user123").password("{noop}password").roles("USER").and()
                .withUser("admin123").password("{noop}password").roles("ADMIN").and()
                .withUser("test123").password("{noop}password").roles("USER").and()
                .withUser("ashish").password("{noop}password").roles("ADMIN");

    }
}