package com.Tavin.bookstore.infra.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
            return http
                    .csrf(AbstractHttpConfigurer::disable)
                    .httpBasic(Customizer.withDefaults())
                    .authorizeHttpRequests( auth ->{
                    auth.requestMatchers(HttpMethod.POST,"/authors").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/authors").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST,"/users").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/users").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST,"/books").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/books").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                    })
                    .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER").build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("321"))
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }


}
