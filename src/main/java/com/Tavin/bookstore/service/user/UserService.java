package com.Tavin.bookstore.service.user;

import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.model.UserModel;
import com.Tavin.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void save(UserModel user){
        var senha = user.getPassword();
        user.setPassword(passwordEncoder.encode(senha));
        userRepository.save(user);
    }

    public void delete(UserModel user){
        userRepository.delete(user);
    }

    public void updated(UserModel user){
        if(user.getId() == null){
            throw new IllegalArgumentException("To update, the User must already be registered");
        }
        userRepository.save(user);
    }


    public Page<UserModel> findAll(String username, String login, Integer page, Integer sizePage){
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setLogin(login);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        Example<UserModel> example = Example.of(user, matcher);

        Pageable pageRequest = PageRequest.of(page, sizePage);
        return userRepository.findAll(example, pageRequest);
    }

    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    public UserModel findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
