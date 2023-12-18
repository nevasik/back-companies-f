package ru.poplaukhin.AdvertisingCompanies.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.poplaukhin.AdvertisingCompanies.dto.CompaignDto;
import ru.poplaukhin.AdvertisingCompanies.entity.Compaign;
import ru.poplaukhin.AdvertisingCompanies.response.EntityResponse;
import ru.poplaukhin.AdvertisingCompanies.service.CompaignService;
import ru.poplaukhin.AdvertisingCompanies.utils.MappingCompaignUtils;

import java.util.List;

@RestController
@RequestMapping("/compain")
@AllArgsConstructor
@CrossOrigin
public class CompaignController {
    private final CompaignService service;
    private final MappingCompaignUtils utils;

    @GetMapping("/all")
    @Operation(description = "Get all Compain")
    public ResponseEntity<List<CompaignDto>> getAll() {
        List<CompaignDto> all = service.getAll().stream().map(utils::mapToDtoFromEntity).toList();

        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get by id Compain")
    public ResponseEntity<EntityResponse<Compaign>> get(@PathVariable Integer id) {
        EntityResponse<Compaign> response = service.getCompainById(id);

        if (response.isEntityFound()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    @Operation(description = "Creation of a new Compain")
    public ResponseEntity<?> post(@Valid @RequestBody CompaignDto compain) {
        try {
            Compaign savedCompaign = service.save(utils.mapToEntityFromDto(compain), compain.getPerson());
            return new ResponseEntity<>(savedCompaign, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{compainId}")
    @Operation(description = "Update Compain by id")
    public ResponseEntity<Compaign> updateCompain(@PathVariable("compainId") Integer compainId , @Valid @RequestBody CompaignDto compain) {
        Compaign updateCompaign = service.update(compainId, utils.mapToEntityFromDto(compain));

        return ResponseEntity.ok(updateCompaign);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete Compain")
    public ResponseEntity<?> deleteCompain(@PathVariable("id") Integer id) {
        service.drop(id);

        return ResponseEntity.noContent().build();
    }
}
