package br.com.mtbassi.opovo.api.modules.auth.services;

import br.com.mtbassi.opovo.api.infra.security.TokenService;
import br.com.mtbassi.opovo.api.modules.journalists.dto.LoginDTO;
import br.com.mtbassi.opovo.api.modules.journalists.dto.LoginResponse;
import br.com.mtbassi.opovo.api.modules.journalists.entities.JournalistEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final ModelMapper modelMapper;

    public LoginResponse login(LoginDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return LoginResponse.builder()
                .token(tokenService.generateToken(modelMapper.map(auth.getPrincipal(), JournalistEntity.class)))
                .build();
    }
}