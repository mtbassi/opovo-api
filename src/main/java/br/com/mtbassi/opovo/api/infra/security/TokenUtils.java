package br.com.mtbassi.opovo.api.infra.security;

import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtils {

    private static ModelMapper modelMapper;

    @Autowired
    TokenUtils(ModelMapper modelMapper) {
        TokenUtils.modelMapper = modelMapper;
    }

    public static Object getPrincipal() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static UUID getIdToken() {
        var principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return modelMapper.map(principal, JournalistResponse.class).getId();
    }
}