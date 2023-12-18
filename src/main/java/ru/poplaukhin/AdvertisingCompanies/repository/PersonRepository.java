package ru.poplaukhin.AdvertisingCompanies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poplaukhin.AdvertisingCompanies.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByLogin(String login);
}
