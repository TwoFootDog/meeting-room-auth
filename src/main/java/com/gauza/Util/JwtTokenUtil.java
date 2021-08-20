package com.gauza.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil { // Jwt 토큰 생성 및 검증 모듈

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expireSecond}")
    private int expireSecond;
    @Value("${jwt.tokenName}")
    private String tokenName;

    private static final Logger logger = LogManager.getLogger(JwtTokenUtil.class);


    @PostConstruct
    protected void init() { // was 기동 시 호출
        logger.info("secretKey >>>>>: " + secretKey);
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Jwt 토큰 생성(access token or refresh token)
//    public String generateToken(String userPk, String tokenFlag, long expireSecond) {
    public String generateToken(String userPk, String tokenFlag) {
        String token = null;
        Claims claims = Jwts.claims().setSubject(userPk);
        Date nowDate = new Date();
        logger.info("generateToken >>>>>>>>>>>>>>>>>>>>>>");


        token = Jwts.builder()    // access 토큰 생성
                .setClaims(claims)  // 데이터(email + role)
                .setIssuedAt(nowDate)
                .setExpiration(new Date(nowDate.getTime() + expireSecond * 1000))  // Expire date 셋팅
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 암호화 알고리즘, secret 값 셋팅
                .compact();

        return token;
    }


    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        logger.info("getUserPk >>>>>>>>>>>>>>>>>>>>>>");
        if (token != null ){
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } else {
            return null;
        }
    }

    // 쿠키에서 token 파싱 : "X-AUTH-TOKEN : Jwt 토큰"
    public String getToken(HttpServletRequest request) {
        logger.info("resolveToken >>>>>>>>>>>>>>>>>>>>>>");
        return CookieUtil.getValue(request, tokenName);
    }


    // Jwt access token의 유효성 + 만료일자 확인
    public boolean validateToken(String Token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(Token);
            long nowTime = new Date().getTime();
            long expireTime = claims.getBody().getExpiration().getTime();
            if (expireTime > nowTime) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e) {
            return false;
        }
    }
}
