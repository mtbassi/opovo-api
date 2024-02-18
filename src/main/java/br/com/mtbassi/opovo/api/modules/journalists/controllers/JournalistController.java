package br.com.mtbassi.opovo.api.modules.journalists.controllers;

import br.com.mtbassi.opovo.api.infra.exception_handler.dto.RestErrorMessage;
import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistRequest;
import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistResponse;
import br.com.mtbassi.opovo.api.modules.journalists.services.JournalistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Journalist")
public class JournalistController {

    private final JournalistService service;


    @Operation(summary = "Register a journalist.",
            description = "Resource to create a new journalist.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = JournalistResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Unique Constraint Violation.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @PostMapping("/register")
    public ResponseEntity<JournalistResponse> register(@RequestBody @Valid JournalistRequest data, UriComponentsBuilder uriBuilder) {
        var response = this.service.register(data);
        var uri = uriBuilder.path("/api/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }


    @Operation(summary = "Retrieves information from the journalist.",
            description = "Retrieves information from the journalist.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource retrieved successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = JournalistResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content)
            })
    @GetMapping("/me")
    public ResponseEntity<JournalistResponse> me(){
        return ResponseEntity.ok(service.me());
    }
}