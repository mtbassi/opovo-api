package br.com.mtbassi.opovo.api.modules.news.controllers;

import br.com.mtbassi.opovo.api.modules.news.dto.NewsRequest;
import br.com.mtbassi.opovo.api.modules.news.dto.NewsResponse;
import br.com.mtbassi.opovo.api.modules.news.services.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
}
