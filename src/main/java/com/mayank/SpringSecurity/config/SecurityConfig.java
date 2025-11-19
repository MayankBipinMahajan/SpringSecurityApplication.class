package com.mayank.SpringSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //Remove the section validation because we are not using statefull
        http.csrf(customizer -> customizer.disable());
        //authenticates for every request
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        //remove form login because we are creating stateless architecture
        //http.formLogin(Customizer.withDefaults());

        //Authentication Provider
        http.httpBasic(Customizer.withDefaults());

        //By using Stateless
        //DO NOT create an HttpSession.
        //DO NOT create a JSESSIONID cookie.
        //DO NOT look for a session cookie to authenticate the User.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

//Default users (Static Users ) in memory

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails User = User
//                .withDefaultPasswordEncoder()
//                .username("mayank")
//                .password("123")
//                .roles("USER")
//                .build();
//        UserDetails admin = User
//                .withDefaultPasswordEncoder()
//                .username("maya")
//                .password("1234")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(User,admin);
//    }

    // Dynamic Users coming from Database
    @Autowired
    private  UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return provider;
    }






}
