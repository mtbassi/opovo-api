package br.com.mtbassi.opovo.api.modules.news_types.controllers;

import br.com.mtbassi.opovo.api.modules.news_types.dto.NewsTypeRequest;
import br.com.mtbassi.opovo.api.modules.news_types.dto.NewsTypeResponse;
import br.com.mtbassi.opovo.api.modules.news_types.services.NewsTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class NewsTypeController {

    private final NewsTypeService service;

    @PostMapping("/create")
    public ResponseEntity<NewsTypeResponse> create(@RequestBody @Valid NewsTypeRequest data, UriComponentsBuilder uriBuilder) {
        var newsType = this.service.create(data);
        var uri = uriBuilder.path("/type/me").build().toUri();
        return ResponseEntity.created(uri).body(newsType);
    }

    @GetMapping("/me")
    public ResponseEntity<List<NewsTypeResponse>> me() {
        return ResponseEntity.ok(this.service.listNewsTypes());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NewsTypeResponse> update(@RequestBody @Valid NewsTypeRequest data, @PathVariable UUID id){
        return ResponseEntity.ok(this.service.update(data, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}