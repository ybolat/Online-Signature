package com.example.teamalfa.dto.requestDto;

import com.example.teamalfa.dto.requestDto.security.RoleDtoResponse;
import lombok.Data;

import java.util.List;

@Data
public class UserDtoRequest {
    private String username;
    private String password;
}
