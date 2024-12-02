package com.Tavin.bookstore.infra.config;

import com.Tavin.bookstore.model.UserModel;
import com.Tavin.bookstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SecurityService {

    private final UserService userService;

    public UserModel loggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String user = userDetails.getUsername();
        return userService.findByLogin(user);
    }


}
