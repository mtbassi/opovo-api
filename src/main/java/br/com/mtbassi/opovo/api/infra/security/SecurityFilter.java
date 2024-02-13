package br.com.mtbassi.opovo.api.infra.security;

import br.com.mtbassi.opovo.api.modules.commons.exceptions.ModelException;
import br.com.mtbassi.opovo.api.modules.journalists.services.JournalistService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    private static final String AUTH_TYPE = "Bearer";
    private final TokenService tokenService;
    private final JournalistService journalistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            var token = this.recoverToken(request);
            if (Objects.nonNull(token)) {
                var login = tokenService.validateToken(token);
                UserDetails user = journalistService.loadUserByUsername(login);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.warn("Token validation failed. User not found.");
            throw new ModelException(e.getMessage(), e.getCause());
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (Objects.isNull(authHeader) || AUTH_TYPE.equals(authHeader)) return null;
        return authHeader.replace(AUTH_TYPE + " ", "");
    }
}