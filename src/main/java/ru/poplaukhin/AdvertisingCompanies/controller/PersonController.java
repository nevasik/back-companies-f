package ru.poplaukhin.AdvertisingCompanies.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.poplaukhin.AdvertisingCompanies.dto.PersonDto;
import ru.poplaukhin.AdvertisingCompanies.entity.Person;
import ru.poplaukhin.AdvertisingCompanies.response.EntityResponse;
import ru.poplaukhin.AdvertisingCompanies.service.PersonService;
import ru.poplaukhin.AdvertisingCompanies.utils.MappingPersonUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/people")
@CrossOrigin
public class PersonController {
    private final PersonService service;
    private final MappingPersonUtils utils;

    @GetMapping("/all")
    @Operation(description = "Get all Persons")
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> list = new ArrayList<>(service.getAll().stream().map(utils::mapToDtoFromEntity).toList());
        list.sort(Comparator.comparing(PersonDto::getId));

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get by id Person")
    public ResponseEntity<EntityResponse<Person>> get(@PathVariable Integer id) {

        EntityResponse<Person> response = service.getPersonById(id);

        if (response.isEntityFound()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    @Operation(description = "Creation of a new Person")
    public ResponseEntity<?> post(@Valid @RequestBody PersonDto person) {
        try {
            Person savedPerson = service.save(utils.mapToEntityFromDto(person));
            return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{personId}")
    @Operation(description = "Update Person")
    public ResponseEntity<Person> updatePerson(@PathVariable("personId") Integer personId, @Valid @RequestBody PersonDto person) {
        Person updatePerson = service.update(personId, utils.mapToEntityFromDto(person));

        return ResponseEntity.ok(updatePerson);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete Person")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Integer id) {
        service.drop(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/registration")
    @Operation(description = "Регистрация пользователя")
    public ResponseEntity<?> registerPerson(@Valid @RequestBody PersonDto person) {
        service.register(person);

        return ResponseEntity.ok().build();
    }
}
