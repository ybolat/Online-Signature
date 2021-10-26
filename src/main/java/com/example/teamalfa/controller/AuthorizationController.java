package com.example.teamalfa.controller;

import com.example.teamalfa.dto.requestDto.UserDtoRequest;
import com.example.teamalfa.dto.responseDto.UserDtoResponse;
import com.example.teamalfa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthorizationController {

    private final UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authorization")
    public ResponseEntity<UserDtoResponse> authorization(@RequestBody UserDtoRequest userDtoRequest, HttpServletRequest request){
        return userService.authorization(userDtoRequest.getUsername().toLowerCase(), userDtoRequest.getPassword(), request);
    }
}
