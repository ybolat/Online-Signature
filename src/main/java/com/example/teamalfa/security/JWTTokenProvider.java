package com.example.teamalfa.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import static com.example.teamalfa.constant.SecurityConstant.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JWTTokenProvider {

    public String generateToken(UserPrincipal userPrincipal, String ipFromClient) {
        return JWT.create().withIssuer(ISSUER).withAudience(AUDIENCE)
                .withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
                .withClaim(CLIENT_IP, ipFromClient)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET));
    }

    public boolean isTokenValid(String username, String token, HttpServletRequest request) {
        JWTVerifier jwtVerifier = getJWTVerifier();
        Date expiration = jwtVerifier.verify(token).getExpiresAt();
        String ipFromClient = getIpFromClient(request);
        if (username != null && !expiration.before(new Date()) && jwtVerifier.verify(token)
                .getClaim(CLIENT_IP).asString().equals(ipFromClient)) {
            return true;
        } else {
            throw new TokenExpiredException("Token is not valid");
        }
    }

    public String getIpFromClient(HttpServletRequest request) {
        for (String header : VALID_IP_HEADER_CANDIDATES) {
            String ipAddress = request.getHeader(header);
            if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
                return ipAddress;
            }
        }
        return request.getRemoteAddr();
    }


    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    public Authentication getAuthentication(String userId, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(userId, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier jwtVerifier;

        try {
            Algorithm algorithm = HMAC512(SECRET);
            jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        }catch (JWTVerificationException e){
            throw new JWTVerificationException("Token can not be verified");
        }
        return jwtVerifier;
    }
}
