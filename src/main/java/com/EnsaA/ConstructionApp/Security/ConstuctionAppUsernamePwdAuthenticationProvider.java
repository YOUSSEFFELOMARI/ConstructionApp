//package com.EnsaA.ConstructionApp.Security;
//
//import com.EnsaA.ConstructionApp.annotation.PasswordValidator;
//import com.EnsaA.ConstructionApp.model.Admin;
//import com.EnsaA.ConstructionApp.model.User;
//import com.EnsaA.ConstructionApp.repository.AdminRepository;
//import com.EnsaA.ConstructionApp.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@AllArgsConstructor
//public class ConstuctionAppUsernamePwdAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String userName = authentication.getName();
//        String pwd = authentication.getCredentials().toString();
//        User user = userRepository.getAdminByUserName(userName);
//        if(null != user && user.getAdminId()>0 &&
//                passwordEncoder.matches(pwd,user.getPassword())){
//            return new UsernamePasswordAuthenticationToken(
//                    userName, null, new ArrayList<GrantedAuthority>(
//                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))));
//        }else{
//            throw new BadCredentialsException("Invalid credentials!");
//        }
//    }
//
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
