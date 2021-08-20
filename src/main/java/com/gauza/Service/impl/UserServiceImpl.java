package com.gauza.Service.impl;

import com.gauza.Dto.Common.ResDto;
import com.gauza.Dto.UserDto;
import com.gauza.Exception.ServiceException;
import com.gauza.Repository.UserRepository;
import com.gauza.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Override
    @Transactional
    public ResDto<UserDto.CResponse> createUser(UserDto.CRequest req) {
        ResDto<UserDto.CResponse> result = new ResDto<>();
        UserDto.CResponse data = new UserDto.CResponse();

        /* T_USER_INFO_MAST 데이터 생성 */
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("nickname", req.getNickname());
        int dbResult = 0;
        try {
            dbResult = userRepo.createUserInfoMast(reqMap); // 유저 생성
        } catch (Exception e) {
            throw new ServiceException(false, "unKnownError", "userRepo.createUser 수행 시 예외 발생", e);
        }
        if (dbResult == 1) {    // 유저 생성 성공 시
            data.setUserId((int)reqMap.get("userId"));
        } else {                // 실패 시
            throw new ServiceException(false, "unKnownError", "userRepo.createUser 수행 결과 미존재");
        }

        /* T_USER_LOGN_MAST 데이터 생성 */
        reqMap.clear(); // 맵 초기화
        reqMap.put("userId", data.getUserId());
        reqMap.put("loginId", req.getLoginId());
        reqMap.put("passwd", req.getPasswd());


        return null;
    }

    @Override
    @Transactional
    public ResDto<UserDto.CResponse> loginUser(UserDto.LoginRequest req) {
        return null;
    }
}
