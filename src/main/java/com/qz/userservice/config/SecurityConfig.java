package com.qz.userservice.config;

import com.qz.userservice.exception.AuthenticationEntryPointExceptionHandler;
import com.qz.userservice.filter.CsrfTokenGeneratorFilter;
import com.qz.userservice.model.User;
import com.qz.userservice.repo.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import java.util.Optional;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(hrc -> {
                    hrc
                            .requestMatchers("/login","/", "/csrf-token").permitAll()
                            .anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> {
                    csrf
                            .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                })
                .sessionManagement(smc -> {
                    smc.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
                })
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(new AuthenticationEntryPointExceptionHandler());
                })
                .addFilterAfter(new CsrfTokenGeneratorFilter(), CsrfFilter.class)
                .build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepo userRepo) {
        return username -> {
            Optional<User> user = userRepo.findByUsername(username);
            return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
