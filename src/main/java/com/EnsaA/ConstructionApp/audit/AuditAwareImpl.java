package com.EnsaA.ConstructionApp.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            return "admin".describeConstable();
        }
        return Optional.ofNullable((SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}
