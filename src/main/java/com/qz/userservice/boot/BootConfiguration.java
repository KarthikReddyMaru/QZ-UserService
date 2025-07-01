package com.qz.userservice.boot;

import com.qz.userservice.model.User;
import com.qz.userservice.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BootConfiguration {

    private final UserRepo userRepo;

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {
            User user = new User();
            user.setUsername("Ryomen");
            user.setPassword("jujutsu");
            userRepo.save(user);
        };
    }

}
