package ru.amm.fileexplorer.server.service.api;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Service
public class JwtTokenProvider {
    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private JdbcUserDetailsManager manager;

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.ttl}")
    private int jwtTTL;


    public String generateToken(Authentication authentication)  {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+jwtTTL))
                .signWith(SignatureAlgorithm.HS256,jwtSecret)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean isTokenValid(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (UnsupportedJwtException e) {
            LOG.error("Unsupported JWT token");
        } catch (MalformedJwtException e) {
            LOG.error("Invalid JWT token");
        } catch (SignatureException e) {
            LOG.error("Invalid JWT signature");
        } catch (IllegalArgumentException e) {
            LOG.error("JWT claims string is empty.");
        }

        return false;
    }
}
