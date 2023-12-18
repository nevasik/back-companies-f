package ru.poplaukhin.AdvertisingCompanies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poplaukhin.AdvertisingCompanies.entity.Statistics;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {
}
