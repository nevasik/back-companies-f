package ru.poplaukhin.AdvertisingCompanies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poplaukhin.AdvertisingCompanies.entity.Compaign;

@Repository
public interface CompaignRepository extends JpaRepository<Compaign, Integer> {
}
