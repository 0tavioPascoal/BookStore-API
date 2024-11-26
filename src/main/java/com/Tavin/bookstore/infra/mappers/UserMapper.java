package com.Tavin.bookstore.infra.mappers;

import com.Tavin.bookstore.infra.dtos.users.UserRequestDto;
import com.Tavin.bookstore.infra.dtos.users.UserResponseDto;
import com.Tavin.bookstore.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel UserModelMapper(UserRequestDto userRequestDto);

    UserResponseDto UserResponseMapper(UserModel userModel);
}
