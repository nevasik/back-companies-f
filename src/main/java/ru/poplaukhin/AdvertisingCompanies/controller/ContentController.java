package ru.poplaukhin.AdvertisingCompanies.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.poplaukhin.AdvertisingCompanies.entity.Content;
import ru.poplaukhin.AdvertisingCompanies.response.EntityResponse;
import ru.poplaukhin.AdvertisingCompanies.service.ContentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/content")
public class ContentController {
    private final ContentService service;

    @GetMapping("/all")
    @Operation(description = "Get all Content")
    public ResponseEntity<List<Content>> getAll() {
        List<Content> all = service.getAll();

        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get by id Content")
    public ResponseEntity<EntityResponse<Content>> get(@PathVariable Integer id) {
        EntityResponse<Content> response = service.getContentById(id);

        if (response.isEntityFound()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    @Operation(description = "Creation of a new Content")
    public ResponseEntity<HttpStatus> post(@RequestBody Content content) {
        try {
            service.save(content);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Update Content")
    public ResponseEntity<Content> updateContent(@PathVariable("id") Integer id,
                                                 @RequestBody Content content) {
        Content updateContent = service.update(id, content);

        return ResponseEntity.ok(updateContent);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete Content")
    public ResponseEntity<?> deleteContent(@PathVariable("id") Integer id) {
        service.drop(id);

        return ResponseEntity.noContent().build();
    }
}
