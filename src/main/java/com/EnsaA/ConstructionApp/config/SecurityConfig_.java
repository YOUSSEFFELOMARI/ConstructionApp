//package com.EnsaA.ConstructionApp.config;
//
////import com.EnsaA.ConstructionApp.Security.ConstuctionAppUsernamePwdAuthenticationProvider;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.filter.CommonsRequestLoggingFilter;
//
//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//public class SecurityConfig_ {
//
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(requests->
//                        requests.requestMatchers("/api/v1/**").permitAll()
//                                .requestMatchers("/auth/admins").permitAll())
//                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
//                .defaultSuccessUrl("/").failureUrl("/n").permitAll())
//                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/")
//                        .invalidateHttpSession(true).permitAll())
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public CommonsRequestLoggingFilter logFilter() {
//        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter() {
//            @Override
//            protected void beforeRequest(HttpServletRequest request, String message) {
//            }
//
//            @Override
//            protected void afterRequest(HttpServletRequest request, String message) {
//                message = message.substring(14);
//                super.afterRequest(request, message);
//            }
//        };
//        filter.setIncludeQueryString(true);
//        filter.setIncludePayload(true);
//        filter.setIncludeHeaders(false);
//        filter.setMaxPayloadLength(10000);
//        return filter;
//    }
//
//}
