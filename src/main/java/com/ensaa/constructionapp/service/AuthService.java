package com.ensaa.constructionapp.service;
import com.ensaa.constructionapp.config.JwtService;
import com.ensaa.constructionapp.model.Admin.AppUser;
import com.ensaa.constructionapp.model.Admin.AuthenticationResponse;
import com.ensaa.constructionapp.model.Admin.LoginInfo;
import com.ensaa.constructionapp.model.Admin.RegisterUser;
import com.ensaa.constructionapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    //Login Service
        public ResponseEntity<AuthenticationResponse> login(LoginInfo loginInfo){
            String email=loginInfo.getEmail();
            String password=loginInfo.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );
           //authentication succee
        AppUser user=userRepository.findAppUserByEmail(email).orElseThrow();
        String Jwt=jwtService.buildToken(new HashMap<>(),user);
        AuthenticationResponse authenticationResponse=AuthenticationResponse.builder()
                .token(Jwt)
                .msg("user has access")
                .build();
        return new ResponseEntity<>(authenticationResponse,HttpStatus.OK);
        }

    //Registre Service
    public ResponseEntity<AuthenticationResponse> registre(RegisterUser registerUser){
            try {
             //check the entered values
            if(registerUser.getName()==null || registerUser.getEmail()==null || registerUser.getPassword()==null){
                AuthenticationResponse authenticationResponse=AuthenticationResponse.builder()
                        .msg("enter all the fileds")
                        .token("").build();
                return new ResponseEntity<>(authenticationResponse, HttpStatus.BAD_REQUEST);
            }

              // check if the user does already exist or not
                Optional<AppUser> ExistUser=userRepository.findAppUserByEmail(registerUser.getEmail());
                if(ExistUser.isPresent()){
                    AuthenticationResponse authenticationResponse=AuthenticationResponse.builder()
                            .msg("account already exist")
                            .token("")
                            .build();
                    return new ResponseEntity<>( authenticationResponse ,HttpStatus.BAD_REQUEST);
                }

              // add a new user
            AppUser user=  AppUser.builder()
                    .name(registerUser.getName())
                    .email(registerUser.getEmail())
                    .password(passwordEncoder.encode(registerUser.getPassword()))
                    .build();
                userRepository.save(user);

              //generate a tokn
            String token=jwtService.buildToken(new HashMap<>(),user);
              //build the response
            AuthenticationResponse authResponse=AuthenticationResponse.builder()
                                                                        .token(token)
                                                                        .build();
            return new ResponseEntity<>(authResponse,HttpStatus.OK);

            }catch (Exception e){
            e.getStackTrace();
            }
         //if somthing went wrong
        AuthenticationResponse authenticationResponse=AuthenticationResponse.builder()
                .msg("Something Went Wrong")
                .build();
        return new ResponseEntity<>(authenticationResponse,HttpStatus.BAD_REQUEST);
        }
}
