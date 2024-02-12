package br.com.mtbassi.opovo.api.modules.journalists.controllers;

import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistRequest;
import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistResponse;
import br.com.mtbassi.opovo.api.modules.journalists.services.JournalistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class JournalistController {

    private final JournalistService service;

    @PostMapping("/register")
    public ResponseEntity<JournalistResponse> register(@RequestBody @Valid JournalistRequest data, UriComponentsBuilder uriBuilder) {
        var response = this.service.register(data);
        var uri = uriBuilder.path("/api/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<JournalistResponse> me(){
        return ResponseEntity.ok(service.me());
    }
}