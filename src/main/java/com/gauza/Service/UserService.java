package com.gauza.Service;

import com.gauza.Dto.Common.ResDto;
import com.gauza.Dto.UserDto;

public interface UserService {
    public ResDto<UserDto.CResponse> createUser(UserDto.CRequest req);
    public ResDto<UserDto.CResponse> loginUser(UserDto.LoginRequest req);
}
