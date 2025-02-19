package com.organization.employeeManagement.setUp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-time}")
    long expiration;

    @Value("${security.jwt.secret-key}")
    String key;

    public String generateToken(String username) {
        return Jwts
                .builder()
                .claims()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .and()
                .signWith(getKey())
                .compact();
    }

    public SecretKey getKey() {
        byte[] sequence = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(sequence);
    }

    public boolean validate(String authHeader, String username, UserDetails userDetails) {
        // check the name
        if (!username.equals(userDetails.getUsername())) {
            return false;
        }
        // check expiration
        if (extractExpiration(authHeader).before(new Date())) {
            return false;
        }

//        validate the signature
//        even if username is manipulated there is no way we can find the correct owner of jwt
        String verify = Jwts.builder()
                .claims(extractALlClaims(authHeader))
                .signWith(getKey())
                .compact();
        String[] verifyJwt = verify.split("\\.");
        String[] authHeaderJwt = authHeader.split("\\.");
        if (!verifyJwt[2].equals(authHeaderJwt[2])) {
            return false;
        }

        userDetails.getAuthorities().stream().forEach(grantedAuthority -> System.out.println(grantedAuthority));
        return true;
    }

    public <T> T extractClaim(String authHeader, Function<Claims, T> claimsExtractionFunction) {
        return claimsExtractionFunction.apply(extractALlClaims(authHeader));
    }

    public String extractUsername(String authHeader) {
        return extractClaim(authHeader, claims -> claims.getSubject());
    }

    public Date extractExpiration(String authHeader) {
        return extractClaim(authHeader, claims -> claims.getExpiration());
    }

    public Claims extractALlClaims(String authHeader) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(authHeader)
                .getPayload();
    }
}



