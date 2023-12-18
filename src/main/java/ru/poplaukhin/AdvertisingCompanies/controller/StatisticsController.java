package ru.poplaukhin.AdvertisingCompanies.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.poplaukhin.AdvertisingCompanies.dto.StatisticsDto;
import ru.poplaukhin.AdvertisingCompanies.entity.Statistics;
import ru.poplaukhin.AdvertisingCompanies.response.EntityResponse;
import ru.poplaukhin.AdvertisingCompanies.service.StatisticsService;
import ru.poplaukhin.AdvertisingCompanies.utils.MappingStatisticsUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/statistics")
@CrossOrigin
public class StatisticsController {
    private final StatisticsService service;
    private final MappingStatisticsUtils utils;

    @GetMapping("/all")
    @Operation(description = "Get all Statistics")
    public ResponseEntity<List<StatisticsDto>> getAll() {
        List<StatisticsDto> all = service.getAll()
                .stream().
                map(utils::mapToDtoFromEntity)
                .toList();

        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get by id Statistics")
    public ResponseEntity<EntityResponse<Statistics>> get(@PathVariable Integer id) {
        EntityResponse<Statistics> response = service.getStatisticsById(id);

        if (response.isEntityFound()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    @Operation(description = "Creation of a new Statistics")
    public ResponseEntity<?> post(@Valid @RequestBody StatisticsDto statistics) {
        try {
            Statistics statisticsSave = service.save(utils.mapToEntityFromDto(statistics), statistics.getCompainId());

            return new ResponseEntity<>(statisticsSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Update Statistics")
    public ResponseEntity<Statistics> updateStatistics(@PathVariable("id") Integer staticId,
                                                       @Valid @RequestBody StatisticsDto statisticsDto) {
        Statistics updateStatistics = service.update(staticId, utils.mapToEntityFromDto(statisticsDto));

        return ResponseEntity.ok(updateStatistics);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete Statistics")
    public ResponseEntity<?> deleteStatistics(@PathVariable("id") Integer id) {
        service.drop(id);

        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/dimension/{statisticsId}")
//    @Operation(description = "Calculate company performance")
//    public ResponseEntity<?> dimension(@PathVariable("statisticsId") Integer statisticsId) {
//        service.isBigCompaign(statisticsId);
//
//        return ResponseEntity.noContent().build();
//    }

//    @PatchMapping("/performance/{statisticsId}")
//    @Operation(description = "Calculate company performance")
//    public ResponseEntity<BigDecimal> calcPerform(@PathVariable("statisticsId") Integer statisticsId) {
//        BigDecimal bigDecimal = service.countPerformance(statisticsId);
//
//        return ResponseEntity.ok(bigDecimal);
//    }
}
