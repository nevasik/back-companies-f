package ru.poplaukhin.AdvertisingCompanies.utils;

import org.springframework.stereotype.Service;
import ru.poplaukhin.AdvertisingCompanies.dto.StatisticsDto;
import ru.poplaukhin.AdvertisingCompanies.entity.Statistics;

@Service
public class MappingStatisticsUtils {

    // from Entity to DTO
    public StatisticsDto mapToDtoFromEntity(Statistics statistics) {
        StatisticsDto statisticsDto = new StatisticsDto();

        statisticsDto.setId(statistics.getId());
        statisticsDto.setViews(statistics.getViews());
        statisticsDto.setClicks(statistics.getClicks());
        statisticsDto.setConversions(statistics.getConversions());
        statisticsDto.setDate(statistics.getDate());
//        if (statistics.getCompaign() != null) {
//            statisticsDto.setCompainId(statistics.getCompaign().getId());
//        }
        statisticsDto.setPerformance(statistics.getPerformance());
        statisticsDto.setIsBig(statistics.getIsBig());

        return statisticsDto;
    }

    // from DTO to Entity
    public Statistics mapToEntityFromDto(StatisticsDto statisticsDto) {
        Statistics statistics = new Statistics();

        statistics.setId(statisticsDto.getId());
        statistics.setViews(statisticsDto.getViews());
        statistics.setClicks(statisticsDto.getClicks());
        statistics.setConversions(statisticsDto.getConversions());
        statistics.setDate(statisticsDto.getDate());
        statistics.setPerformance(statistics.getPerformance());
        statistics.setIsBig(statistics.getIsBig());

        return statistics;
    }
}
