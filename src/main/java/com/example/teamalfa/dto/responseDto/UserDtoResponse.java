package com.example.teamalfa.dto.responseDto;

import com.example.teamalfa.dto.requestDto.security.RoleDtoResponse;
import lombok.Data;

@Data
public class UserDtoResponse {
    private Long id;
    private String username;
}
