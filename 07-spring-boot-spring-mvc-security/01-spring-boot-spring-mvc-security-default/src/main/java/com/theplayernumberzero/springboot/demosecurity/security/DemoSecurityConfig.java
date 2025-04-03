package com.theplayernumberzero.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //add support for jdbc. No more hard coded user information
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        //how to find user
        userDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id = ?");
        //how to find role
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id = ?");

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        ).exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"))
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")  //customized login page
                                .loginProcessingUrl("/authenticateTheUser") //no need controller
                                .permitAll()    //everyone can see login page
                )
                //Add logout support
                .logout(logout -> logout.permitAll())
                ;
        return http.build();
    }
}

/*
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails bahadir = User.builder()
                .username("bahadir")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails umut = User.builder()
                .username("umut")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails kilic = User.builder()
                .username("kilic")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(bahadir, umut, kilic);
    }*/
