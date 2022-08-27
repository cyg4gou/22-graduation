package cn.root.util;

import cn.root.common.Constant;
import cn.root.common.error.jwt.JwtExpiredException;
import cn.root.common.error.jwt.JwtNotCorrectException;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    public static String createToken(String userId, String salt) {
        Map<String, Object> claim = new HashMap<>();
        claim.put("userId", userId);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, salt)
                .setClaims(claim)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Constant.expireTime * 1000))
                .compact();
    }

    public static Map<String, Object> checkToken(String token, String salt) {
        try {
            Jwt parse = Jwts.parser()
                    .setSigningKey(salt)
                    .parse(token);
            return (Map<String, Object>) parse.getBody();
        } catch (ExpiredJwtException ex) {
            throw new JwtExpiredException();
        } catch (MalformedJwtException ex){
            throw new JwtNotCorrectException();
        }
    }

}
