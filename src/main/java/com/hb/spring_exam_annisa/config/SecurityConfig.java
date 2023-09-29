package com.hb.spring_exam_annisa.config;

import com.hb.spring_exam_annisa.handlers.CustomAccessDeniedHandler;
import com.hb.spring_exam_annisa.services.SpringAuthService;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    SpringAuthService springAuthService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                /*.csrf().disable()*/
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/back-office", "/back-office/*").hasAuthority("Journalist")
                    .requestMatchers("/js/**", "css/**", "error/**").permitAll()
                    .anyRequest().permitAll())
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.accessDeniedPage("/errors/access-denied"))
                .formLogin((form) -> form.loginPage("/login").permitAll())
                .logout((logout) -> logout.logoutUrl("/logout"))
        ;
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(springAuthService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

//    @Bean
//    AccessDeniedHandler accessDeniedHandler() {
//        return new CustomAccessDeniedHandler();
//    }
}
