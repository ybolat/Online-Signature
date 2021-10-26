package com.example.teamalfa.mapper;

import com.example.teamalfa.dto.responseDto.UserDtoResponse;
import com.example.teamalfa.mapper.security.RoleMapper;
import com.example.teamalfa.model.User;

import java.util.stream.Collectors;


public class UserMapper {
    public UserDtoResponse userToDTO(User user) {
        UserDtoResponse userResponseDto = new UserDtoResponse();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setBaseRole(user.getBaseRole());
        userResponseDto.setRoleList(user.getRoles().stream().map(v -> new RoleMapper().roleToDto(v)).collect(Collectors.toList()));
        return userResponseDto;
    }


}
