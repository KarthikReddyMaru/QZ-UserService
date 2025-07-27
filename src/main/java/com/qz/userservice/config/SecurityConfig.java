package com.qz.userservice.config;

import com.qz.userservice.exception.AccessDeniedExceptionHandler;
import com.qz.userservice.exception.AuthenticationEntryPointExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(hrc -> {
                    hrc
                            .requestMatchers("/").anonymous()
                            .requestMatchers("/user/register", "/csrf-token").permitAll()
                            .anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(smc -> {
                    smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(new AuthenticationEntryPointExceptionHandler())
                            .accessDeniedHandler(new AccessDeniedExceptionHandler());
                })
                .build();
    }

}
