package com.example.teamalfa.mapper;

import com.example.teamalfa.dto.responseDto.UserDtoResponse;
import com.example.teamalfa.model.User;


public class UserMapper {
    public UserDtoResponse userToDTO(User user) {
        UserDtoResponse userResponseDto = new UserDtoResponse();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        return userResponseDto;
    }


}
