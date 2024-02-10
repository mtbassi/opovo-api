package br.com.mtbassi.opovo.api.modules.auth.controllers;

import br.com.mtbassi.opovo.api.modules.journalists.dto.LoginDTO;
import br.com.mtbassi.opovo.api.modules.journalists.dto.LoginResponse;
import br.com.mtbassi.opovo.api.modules.auth.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginDTO data) {
        return ResponseEntity.ok(this.service.login(data));
    }
}