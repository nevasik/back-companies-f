package ru.poplaukhin.AdvertisingCompanies.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.poplaukhin.AdvertisingCompanies.entity.Compaign;
import ru.poplaukhin.AdvertisingCompanies.entity.Statistics;
import ru.poplaukhin.AdvertisingCompanies.repository.CompaignRepository;
import ru.poplaukhin.AdvertisingCompanies.repository.StatisticsRepository;
import ru.poplaukhin.AdvertisingCompanies.response.EntityResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final CompaignRepository compaignRepository;

    private final Logger log = LoggerFactory.getLogger(Statistics.class);

    public StatisticsService(StatisticsRepository statisticsRepository, CompaignRepository compaignRepository) {
        this.statisticsRepository = statisticsRepository;
        this.compaignRepository = compaignRepository;
    }

    @Transactional
    public List<Statistics> getAll() {
        log.info("Получение всех статистик");

        return statisticsRepository.findAll();
    }

    @Transactional
    public EntityResponse<Statistics> getStatisticsById(Integer statisticId) {
        log.info("Получение статистики с {} id", statisticId);

        Statistics statistics = statisticsRepository.findById(statisticId).orElseThrow(
                () -> new RuntimeException("Статистики не найдено с таким id " + statisticId));

        return new EntityResponse<>(true, statistics);
    }

    @Transactional
    public Statistics save(Statistics statistics, Integer compainId) {
        Compaign compaign = compaignRepository.findById(compainId).orElseThrow(
                () -> new RuntimeException("Компании не найдено с таким id " + compainId));

//        statistics.setCompaign(compaign);

        log.info("Сохранение статистики с таким {} id", statistics);

        return statisticsRepository.save(statistics);
    }

    @Transactional
    public Statistics update(Integer statisticsId, Statistics statistics) {
        Statistics oldStat = statisticsRepository.findById(statisticsId).orElseThrow(
                () -> new RuntimeException("Статистики не найдено с таким id " + statisticsId));

        if (Objects.nonNull(oldStat)) {
            oldStat.setClicks(statistics.getClicks());
            oldStat.setViews(statistics.getViews());
            oldStat.setConversions(statistics.getConversions());
            oldStat.setDate(statistics.getDate());

//            if (Objects.nonNull(statistics.getCompaign())) {
//                oldStat.setCompaign(statistics.getCompaign());
//            }

            statisticsRepository.save(oldStat);
        }

        log.info("Обновление статистики c {} id на {} ", statisticsId, statistics);

        return oldStat;
    }

    @Transactional
    public void drop(Integer statisticsId) {
        Statistics statistics = statisticsRepository.findById(statisticsId).orElseThrow(
                () -> new RuntimeException("Статистики не найдено с таким id " + statisticsId));

        log.info("Удаление статистики с {} d ", statisticsId);

        statisticsRepository.delete(statistics);
    }

//    @Transactional
//    public void isBigCompaign(Integer statisticsId) {
//        Statistics statistics = statisticsRepository.findById(statisticsId).orElseThrow(
//                () -> new RuntimeException("Статистики не найдено с таким id " + statisticsId));
//
//        // check is big/not big
//        if (statistics.getViews() > 1000) {
//            statistics.setIsBig(true);
//        } else {
//            statistics.setIsBig(false);
//        }
//
//        statisticsRepository.save(statistics);
//    }

    @Transactional
    // conversion compaign
    public BigDecimal countPerformance(Integer statisticsId) {
        log.info("id статистики {}", statisticsId);

        Statistics statistic = statisticsRepository.findById(statisticsId).orElseThrow(
                () -> new RuntimeException("Статистики не найдено с таким id " + statisticsId));

        double performance = 0;
//        if (Objects.nonNull(statistic.getCompaign())) {
//            performance = ((statistic.getViews() / statistic.getClicks()) * 100);
//            statistic.setPerformance(BigDecimal.valueOf(performance));
//        } else {
//            statistic.setPerformance(BigDecimal.valueOf(0));
//            log.info("Статистику невозможно посчитать, потому что у нее нет привязки к компании {}", statistic);
//        }

        statisticsRepository.save(statistic);

        return BigDecimal.valueOf(performance);
    }
}
