package com.example.teamalfa.mapper.security;

import com.example.teamalfa.dto.requestDto.security.RoleDtoResponse;
import com.example.teamalfa.model.security.Role;

public class RoleMapper {

    public RoleDtoResponse roleToDto(Role role) {
        RoleDtoResponse roleResponseDto = new RoleDtoResponse();
        roleResponseDto.setRoleName(role.getRoleName());
        return roleResponseDto;
    }
}
