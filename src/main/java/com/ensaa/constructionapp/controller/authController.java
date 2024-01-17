package com.ensaa.constructionapp.controller;


import com.ensaa.constructionapp.model.Admin.AuthenticationResponse;
import com.ensaa.constructionapp.model.Admin.LoginInfo;
import com.ensaa.constructionapp.model.Admin.RegisterUser;
import com.ensaa.constructionapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class authController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody LoginInfo loginInfo){
            return  authService.login(loginInfo);
    }

    @PostMapping("/registre")
    ResponseEntity<AuthenticationResponse> registre(@RequestBody RegisterUser registerUser){
        return authService.registre(registerUser);
    }
}
