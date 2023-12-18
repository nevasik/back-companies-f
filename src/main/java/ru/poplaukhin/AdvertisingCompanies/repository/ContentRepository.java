package ru.poplaukhin.AdvertisingCompanies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poplaukhin.AdvertisingCompanies.entity.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
}
