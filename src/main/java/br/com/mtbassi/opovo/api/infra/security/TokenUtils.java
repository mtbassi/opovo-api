package br.com.mtbassi.opovo.api.infra.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class TokenUtils {

    public static Object getPrincipal() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}