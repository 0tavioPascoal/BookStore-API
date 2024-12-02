package com.Tavin.bookstore.controllers.user;

import com.Tavin.bookstore.infra.dtos.users.UserRequestDto;
import com.Tavin.bookstore.infra.dtos.users.UserResponseDto;
import com.Tavin.bookstore.infra.header.GeneratedHeader;
import com.Tavin.bookstore.infra.mappers.user.UserMapper;
import com.Tavin.bookstore.model.UserModel;
import com.Tavin.bookstore.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
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

    @DeleteMapping()
    public ResponseEntity<Object> deleteBook(
            @RequestParam String id) {

        return userService.findById(UUID.fromString(id))
                .map(UserModel -> {
                    userService.delete(UserModel);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping()
    public ResponseEntity<Object> updateBook(
            @RequestParam String id, @RequestBody @Valid UserRequestDto userRequestDto){
        return userService.findById(UUID.fromString(id))
                .map(UserModel -> {
                    UserModel userAux = userMapper.UserModelMapper(userRequestDto);
                    UserModel.setEmail(userAux.getEmail());
                    UserModel.setPassword(userAux.getPassword());
                    UserModel.setLogin(userAux.getLogin());
                    userService.updated(UserModel);
                    return ResponseEntity.noContent().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<Page<UserResponseDto>> findAll(
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "sizePage",defaultValue = "10",required = false)Integer sizePage) {

        Page<UserModel> result = userService.findAll(login, username, page, sizePage);

        Page<UserResponseDto> userResult = result.map(userMapper::UserResponseMapper);

        return ResponseEntity.ok(userResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@RequestParam String id) {
        return userService.findById(UUID.fromString(id))
                .map( user -> {
                    var dto = userMapper.UserResponseMapper(user);
                    return ResponseEntity.ok(dto);
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

}
