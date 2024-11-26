package com.Tavin.bookstore.controllers.user;

import com.Tavin.bookstore.infra.dtos.users.UserRequestDto;
import com.Tavin.bookstore.infra.header.GeneratedHeader;
import com.Tavin.bookstore.infra.mappers.user.UserMapper;
import com.Tavin.bookstore.model.UserModel;
import com.Tavin.bookstore.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController implements GeneratedHeader {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserModel user = userMapper.UserModelMapper(userRequestDto);
        userService.save(user);

        URI location = generateURI(user.getId());
        return ResponseEntity.created(location).build();
    }

}
