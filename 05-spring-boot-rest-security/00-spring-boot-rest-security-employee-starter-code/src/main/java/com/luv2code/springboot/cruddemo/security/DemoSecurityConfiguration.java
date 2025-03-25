package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfiguration {
    //add support jdbc
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        //Default table kullandığında aşağıdaki kod yeter
        //return new JdbcUserDetailsManager(dataSource);

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //Authorization rules
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                );

        //use http basic authentication
        http.httpBasic(Customizer.withDefaults());

        //disable csrf
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}

/*
//In-memory usage
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails bahadir = User.builder()
                .username("bahadir")
                .password("{noop}bahadir123")
                .roles("EMPLOYEE")
                .build();

        UserDetails umut = User.builder()
                .username("umut")
                .password("{noop}umut123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails yigit = User.builder()
                .username("yigit")
                .password("{noop}yigit123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(bahadir, umut, yigit);
    } */