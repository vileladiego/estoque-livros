package com.editora.estoque_livros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    public UserDetailsService users() {
        //service de autenticação criado apenas para o teste. Não use em producão.
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("steppenwolf")
                .password("7#F@x9qZ!mL2$wR3")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
