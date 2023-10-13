package com.EnsaA.ConstructionApp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    JwtService jwtService;
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        System.out.println("tokeeeen is :"+authHeader);
        String userEmail;
        String jwt;
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
            }
        jwt=authHeader.substring(7);
        userEmail=jwtService.extractUsername(jwt);
        System.out.println("useremail is "+userEmail);
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            System.out.println("hello in securitty context");
            UserDetails userDetails=userDetailsService.loadUserByUsername(userEmail);
            System.out.println("user by user email is"+userDetails.getUsername());
            if(jwtService.isTokenValid(jwt,userDetails)){
                System.out.println("token is valid or not ");
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        //This creates a new instance of WebAuthenticationDetails which holds information about the web request, such as the IP address, session ID
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                System.out.println("updating context");
                SecurityContextHolder.getContext().setAuthentication( authToken );
                System.out.println("let go to the next filer");
                filterChain.doFilter(request,response);
            }

        }


    }
}
