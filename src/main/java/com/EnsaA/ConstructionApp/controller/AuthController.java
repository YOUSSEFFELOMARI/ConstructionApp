//package com.EnsaA.ConstructionApp.controller;
//
//import com.EnsaA.ConstructionApp.model.Admin;
//import com.EnsaA.ConstructionApp.repository.AdminRepository;
//import com.EnsaA.ConstructionApp.service.AdminService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final AdminRepository adminRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AdminService adminService;
//
//    @PostMapping("/signing")
//    public ResponseEntity<String> authenticateUser(@RequestBody Admin admin){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                admin.getUserName(), admin.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody Admin admin){
//        adminService.create(admin);
//        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//    }
//
//}
