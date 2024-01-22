package com.ensaa.constructionapp.config;

import com.ensaa.constructionapp.model.Admin.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
@Slf4j
@Service
public class JwtService {
    private static final String SECRET_KEY="gh5i39q/EShZ0HgyrC7y7KGdWI7MfAKu/Q+RqsVcge5dOQ==";
    SecureRandom secureRandom = new SecureRandom();
    //to generate a token
    public String buildToken(
            Map<String, Object> extraClaims,
            AppUser userDetails
            //long expiration
    ) {
        LocalDateTime expirationTime = LocalDateTime.now().plusDays(30);
        Date expirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    //check is the token valid (check the user name and the expiration date)
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    //checkif the token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //extract the expiration date
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    //extract userName Claim
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //get a specific claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build() //build the parser jwParser
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {

        //to decode the secret_key from base64 to an array of bytes
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        // This line creates an HMAC-based signing key
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
