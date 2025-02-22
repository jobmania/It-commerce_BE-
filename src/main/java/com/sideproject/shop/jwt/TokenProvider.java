package com.sideproject.shop.jwt;
//토큰 생성, 유효성 검증 로직수행

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@PropertySource(value = "classpath:application-secret.yml")
public class TokenProvider  {


    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private final long tokenValidityInMilliseconds;
    private  final Key key;

    @Autowired
    public TokenProvider(
            @Value("${jwt.secret}") String secret,  // prpoerties에서 들고옴.
             @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds
    ) {
        this.tokenValidityInMilliseconds = tokenValidityInSeconds;
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    public TokenDto createToken(Authentication authentication){ // 컬렉션을 순회하면서 값들을 출력~
        // 권한들 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = new Date().getTime();
        Date accessValidity = new Date(now + this.tokenValidityInMilliseconds);

        String accessJwtToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(accessValidity)
                .compact();

        // Refresh Token 생성
        String refreshJwtToken = Jwts.builder()
                .setExpiration(new Date(now + this.tokenValidityInMilliseconds * 10))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();



        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessJwtToken)
                .accessTokenExpiresIn(accessValidity.getTime())
                .refreshToken(refreshJwtToken)
                .build();
    }


    public Authentication getAuthentication(String token){

        //토큰 복호화~~
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();


        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        User principal = new User(claims.getSubject(),"", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);

    }


    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명");
        }catch (ExpiredJwtException e){
            logger.info("만료된 jwt 서명");
        }catch (UnsupportedJwtException e){
            logger.info("지원되지 않은 JWT 토큰");
        }catch (IllegalArgumentException e){
            logger.info("jwt 토큰이 잘못됨");
        }
        return false;
    }
}
