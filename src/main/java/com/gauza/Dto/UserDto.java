package com.gauza.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CRequest {
        String nickname;
        String loginId;
        String passwd;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CResponse {
        int userId;
        String token;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest {
        String loginId;
        String passwd;
    }

}
