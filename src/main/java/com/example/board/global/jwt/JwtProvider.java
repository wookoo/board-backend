package com.example.board.global.jwt;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final long ACCESS_EXPIRATION_TIME;
    private final SecretKey ACCESS_SECRET_KEY;
    public JwtProvider(
            @Value("${jwt.access.exp-time}") long accessExpirationTime,
            @Value("${jwt.access.secret}") String accessSecret
    ) {
        this.ACCESS_EXPIRATION_TIME = accessExpirationTime;

        this.ACCESS_SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));


    }

    public String createAccessToken(long memberId){

        long now = new Date().getTime();
        return Jwts.builder()
                .subject(memberId+"")
                .signWith(this.ACCESS_SECRET_KEY)
                .issuedAt(new Date(now))
                .expiration(new Date(now+this.ACCESS_EXPIRATION_TIME))
                .compact();

    }






    private String createToken(long memberRecordId, long now,SecretKey secretKey,long expirationTime) {
        return Jwts.builder()
                .subject(memberRecordId+"")
                .issuedAt(new Date(now))
                .expiration(new Date(now + expirationTime))
                .signWith(secretKey)
                .compact();
    }
//secret 이랑, time 이 필요해
}
