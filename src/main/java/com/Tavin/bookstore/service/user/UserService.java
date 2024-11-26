package com.Tavin.bookstore.service.user;

import com.Tavin.bookstore.model.UserModel;
import com.Tavin.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public Optional<UserModel> findById(UUID id){
        return userRepository.findById(id);
    }
}
