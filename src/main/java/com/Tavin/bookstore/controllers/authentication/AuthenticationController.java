package com.Tavin.bookstore.controllers.authentication;

import com.Tavin.bookstore.infra.dtos.users.UserLoginDto;
import com.Tavin.bookstore.infra.dtos.users.UserLoginResponseDto;
import com.Tavin.bookstore.infra.dtos.users.UserRequestDto;
import com.Tavin.bookstore.infra.header.GeneratedHeader;
import com.Tavin.bookstore.infra.mappers.user.UserMapper;
import com.Tavin.bookstore.infra.security.token.TokenService;
import com.Tavin.bookstore.model.UserModel;
import com.Tavin.bookstore.repository.UserRepository;
import com.Tavin.bookstore.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController implements GeneratedHeader {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> register(@RequestBody @Valid UserLoginDto data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generatedToken((UserModel) auth.getPrincipal());
        return ResponseEntity.ok(new UserLoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRequestDto userRequestDto) {
        if(this.userRepository.findByLogin(userRequestDto.login()) != null) return ResponseEntity.badRequest().build();

        UserModel user = userMapper.UserModelMapper(userRequestDto);
        userService.save(user);

        URI location = generateURI(user.getId());
        return ResponseEntity.created(location).build();
    }
}
