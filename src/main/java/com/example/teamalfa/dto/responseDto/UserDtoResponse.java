package com.example.teamalfa.dto.responseDto;

import com.example.teamalfa.dto.requestDto.security.RoleDtoResponse;
import lombok.Data;

import java.util.List;

@Data
public class UserDtoResponse {
    private Long id;
    private String username;
    String baseRole;
    List<RoleDtoResponse> roleList;
}
