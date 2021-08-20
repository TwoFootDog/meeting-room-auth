package com.gauza.Repository;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserRepository {
    public int createUserInfoMast(Map<String, Object> req);
    public int createUserLoginMast(Map<String, Object> req);
    public int loginUser(Map<String, Object> req);
}
