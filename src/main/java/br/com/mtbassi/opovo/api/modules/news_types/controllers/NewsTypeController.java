package br.com.mtbassi.opovo.api.modules.news_types.controllers;

import br.com.mtbassi.opovo.api.infra.exception_handler.dto.RestErrorMessage;
import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistResponse;
import br.com.mtbassi.opovo.api.modules.news.dto.NewsResponse;
import br.com.mtbassi.opovo.api.modules.news_types.dto.NewsTypeRequest;
import br.com.mtbassi.opovo.api.modules.news_types.dto.NewsTypeResponse;
import br.com.mtbassi.opovo.api.modules.news_types.services.NewsTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
@Tag(name = "News type", description = "Contains operations for managing news types.")
public class NewsTypeController {

    private final NewsTypeService service;

    @Operation(summary = "Create news type.",
            description = "Resource creates new news type.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewsResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema())),
                    @ApiResponse(responseCode = "409", description = "Unique Constraint Violation.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @PostMapping("/create")
    public ResponseEntity<NewsTypeResponse> create(@RequestBody @Valid NewsTypeRequest data, UriComponentsBuilder uriBuilder) {
        var newsType = this.service.create(data);
        var uri = uriBuilder.path("/type/me").build().toUri();
        return ResponseEntity.created(uri).body(newsType);
    }

    @Operation(summary = "List news.",
            description = "Resource lists the journalist’s news type.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource consulted successfully.",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NewsTypeResponse.class)))),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema()))
            })
    @GetMapping("/me")
    public ResponseEntity<List<NewsTypeResponse>> me() {
        return ResponseEntity.ok(this.service.listNewsTypes());
    }

    @Operation(summary = "Update news type.",
            description = "Resource updates the news type by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource updated successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewsTypeResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema())),
                    @ApiResponse(responseCode = "404", description = "Resource not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @PutMapping("/update/{id}")
    public ResponseEntity<NewsTypeResponse> update(@RequestBody @Valid NewsTypeRequest data, @PathVariable UUID id){
        return ResponseEntity.ok(this.service.update(data, id));
    }

    @Operation(summary = "Delete news type.",
            description = "Resource deletes news type by id.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Resource deleted successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema())),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema())),
                    @ApiResponse(responseCode = "404", description = "Resource not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}