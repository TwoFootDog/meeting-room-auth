package com.gauza.Controller;

import com.gauza.Dto.Common.ResDto;
import com.gauza.Dto.UserDto;
import com.gauza.Service.UserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<ResDto<UserDto.CResponse>> createUser(@RequestBody UserDto.CRequest req) {

    }

    @PostMapping
    public ResponseEntity<ResDto<UserDto.CResponse>> loginUser(@RequestBody UserDto.LoginRequest req) {

    }
}
