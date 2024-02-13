package br.com.mtbassi.opovo.api.modules.news.controllers;

import br.com.mtbassi.opovo.api.modules.news.dto.NewsRequest;
import br.com.mtbassi.opovo.api.modules.news.dto.NewsResponse;
import br.com.mtbassi.opovo.api.modules.news.services.NewsService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService service;

    @PostMapping("/create")
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid NewsRequest data, UriComponentsBuilder uriBuilder){
        var response = this.service.create(data);
        var uri = uriBuilder.path("/api/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<List<NewsResponse>> listNews(){
        return ResponseEntity.ok(this.service.listNews());
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<NewsResponse>> listNewsByType(@PathVariable UUID typeId){
        return ResponseEntity.ok(this.service.listNewsByType(typeId));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<NewsResponse> update(@RequestBody NewsRequest data, @PathVariable UUID id){
        return ResponseEntity.ok(this.service.update(data, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
