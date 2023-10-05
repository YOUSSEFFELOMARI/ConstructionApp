package com.EnsaA.ConstructionApp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {
    private final String SECRET_KEY="gh5i39q/EShZ0HgyrC7y7KGdWI7MfAKu/Q+RqsVcge5dOQ==";
    SecureRandom secureRandom = new SecureRandom();
    //to generate a token
    public String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
            //long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()  + 30*24*60*60*1000 ))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    //check is the token valid (check the user name and the ixpiration date)
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
