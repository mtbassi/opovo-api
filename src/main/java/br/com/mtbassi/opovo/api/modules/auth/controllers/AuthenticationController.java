package br.com.mtbassi.opovo.api.modules.auth.controllers;

import br.com.mtbassi.opovo.api.infra.exception_handler.dto.RestErrorMessage;
import br.com.mtbassi.opovo.api.modules.auth.dto.LoginRequest;
import br.com.mtbassi.opovo.api.modules.auth.dto.LoginResponse;
import br.com.mtbassi.opovo.api.modules.auth.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Login", description = "Contains the operation for a user login.")
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation(summary = "Authenticates the user.",
            description = "Resource returns a jwt token when logging in with email and password.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Token generated successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Login failed.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest data) {
        return ResponseEntity.ok(this.service.login(data));
    }
}