package org.pti.poster.integration.security.service;

import org.springframework.security.access.prepost.PreAuthorize;

public interface MethodSecurityService {

    @PreAuthorize("hasRole('ROLE_USER')")
    String requiresUserRole();
}