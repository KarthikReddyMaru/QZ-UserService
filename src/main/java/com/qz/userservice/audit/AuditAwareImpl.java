package com.qz.userservice.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<String> {
    @Override

    public Optional<String> getCurrentAuditor() {
        return Optional.of("Ryomen");
    }
}
