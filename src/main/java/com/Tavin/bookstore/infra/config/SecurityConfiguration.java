package com.Tavin.bookstore.infra.config;


import com.Tavin.bookstore.service.user.UserService;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(HttpMethod.POST, "/users").hasAnyRole("ADMIN", "GERENTE");
                    authorize.requestMatchers(HttpMethod.DELETE, "/users").hasAnyRole("ADMIN", "GERENTE");
                    authorize.requestMatchers(HttpMethod.POST, "/books").hasAnyRole("ADMIN", "GERENTE");
                    authorize.requestMatchers(HttpMethod.DELETE, "/books").hasAnyRole("ADMIN", "GERENTE");
                    authorize.requestMatchers(HttpMethod.POST, "/authors").hasAnyRole("ADMIN", "GERENTE");
                    authorize.requestMatchers(HttpMethod.DELETE, "/authors").hasAnyRole("ADMIN", "GERENTE");
                    authorize.requestMatchers(HttpMethod.PUT, "/authors").hasAnyRole("ADMIN", "GERENTE");
                    authorize.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder, UserService service) {

        return new CustomUserDetailService(service);
    }


}
