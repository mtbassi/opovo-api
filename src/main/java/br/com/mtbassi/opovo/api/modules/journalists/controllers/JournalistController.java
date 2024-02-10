package br.com.mtbassi.opovo.api.modules.journalists.controllers;

import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistRequest;
import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistResponse;
import br.com.mtbassi.opovo.api.modules.journalists.services.JournalistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JournalistController {

    private final JournalistService service;

    @PostMapping("/register")
    public ResponseEntity<JournalistResponse> register(@RequestBody JournalistRequest data, UriComponentsBuilder uriBuilder) {
        var response = service.register(data);
        var uri = uriBuilder.path("/api/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}