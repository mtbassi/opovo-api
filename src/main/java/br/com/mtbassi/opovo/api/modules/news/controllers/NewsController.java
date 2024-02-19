package br.com.mtbassi.opovo.api.modules.news.controllers;

import br.com.mtbassi.opovo.api.infra.exception_handler.dto.RestErrorMessage;
import br.com.mtbassi.opovo.api.modules.news.dto.NewsRequest;
import br.com.mtbassi.opovo.api.modules.news.dto.NewsResponse;
import br.com.mtbassi.opovo.api.modules.news.services.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Tag(name = "News", description = "Contains operations for managing news.")
public class NewsController {

    private final NewsService service;


    @Operation(summary = "Create news.",
            description = "Resource creates new news.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewsResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema())),
                    @ApiResponse(responseCode = "409", description = "Unique Constraint Violation.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @SecurityRequirement(name = "Bearer Authentication", scopes = {})
    @PostMapping("/create")
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid NewsRequest data, UriComponentsBuilder uriBuilder) {
        var response = this.service.create(data);
        var uri = uriBuilder.path("/api/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }


    @Operation(summary = "List news.",
            description = "Resource lists the journalist’s news.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource consulted successfully.",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NewsResponse.class)))),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema()))
            })
    @SecurityRequirement(name = "Bearer Authentication", scopes = {})
    @GetMapping("/me")
    public ResponseEntity<List<NewsResponse>> listNews() {
        return ResponseEntity.ok(this.service.listNews());
    }

    @Operation(summary = "List news by type of news.",
            description = "Resource lists the journalist’s news by type of news.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource consulted successfully.",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NewsResponse.class)))),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema()))
            })
    @SecurityRequirement(name = "Bearer Authentication", scopes = {})
    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<NewsResponse>> listNewsByType(@PathVariable UUID typeId) {
        return ResponseEntity.ok(this.service.listNewsByType(typeId));
    }

    @Operation(summary = "Update news.",
            description = "Resource updates the news by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource updated successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewsResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema())),
                    @ApiResponse(responseCode = "404", description = "Resource not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @SecurityRequirement(name = "Bearer Authentication", scopes = {})
    @PutMapping("/update/{id}")
    public ResponseEntity<NewsResponse> update(@RequestBody NewsRequest data, @PathVariable UUID id) {
        return ResponseEntity.ok(this.service.update(data, id));
    }

    @Operation(summary = "Delete news.",
            description = "Resource deletes news by id.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Resource deleted successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema())),
                    @ApiResponse(responseCode = "403", description = "Token validation failed. User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema())),
                    @ApiResponse(responseCode = "404", description = "Resource not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @SecurityRequirement(name = "Bearer Authentication", scopes = {})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
