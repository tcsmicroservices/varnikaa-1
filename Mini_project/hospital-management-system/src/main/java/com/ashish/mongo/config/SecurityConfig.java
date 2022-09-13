package com.ashish.mongo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.httpBasic().and().authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/doctor/doctorappointment").hasAnyRole("DOCTOR")
                .antMatchers(HttpMethod.GET,"/patient/myappointment").hasAnyRole("PATIENT")
                .antMatchers(HttpMethod.GET,"/viewprescrption").hasAnyRole("DOCTOR","PATIENT")
                .antMatchers(HttpMethod.POST,"/saveprescrption").hasAnyRole("DOCTOR","PATIENT")
                .and().csrf().disable().headers().frameOptions().disable();

    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.inMemoryAuthentication()
                .withUser("p1").password("{noop}password").roles("PATIENT").and()
                .withUser("doc1").password("{noop}password").roles("DOCTOR").and()
                .withUser("user").password("{noop}password").roles("DOCTOR","PATIENT");
    }
}