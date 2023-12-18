package ru.poplaukhin.AdvertisingCompanies.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.poplaukhin.AdvertisingCompanies.entity.Compaign;
import ru.poplaukhin.AdvertisingCompanies.entity.Person;
import ru.poplaukhin.AdvertisingCompanies.repository.CompaignRepository;
import ru.poplaukhin.AdvertisingCompanies.repository.PersonRepository;
import ru.poplaukhin.AdvertisingCompanies.response.EntityResponse;

import java.util.List;
import java.util.Objects;

@Service
public class CompaignService {
    private final CompaignRepository repository;
    private final PersonRepository personRepository;
    private final Logger log = LoggerFactory.getLogger(Compaign.class);

    @Autowired
    public CompaignService(CompaignRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

    @Transactional
    public List<Compaign> getAll() {
        log.info("Получение всех компаний");

        return repository.findAll();
    }

    @Transactional
    public EntityResponse<Compaign> getCompainById(Integer compainId) {
        log.info("Получение компании с {} id", compainId);

        Compaign compaign = repository.findById(compainId).orElseThrow(
                () -> new RuntimeException("Компании не найдено с таким id " + compainId));

        return new EntityResponse<>(true, compaign);
    }

    @Transactional
    public Compaign save(Compaign compaign, Integer personId) {
        Person person = personRepository.findById(personId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден с таким id " + personId));

        compaign.setPerson(person);

        log.info("Сохранение компании: {}", compaign);

        return repository.save(compaign);
    }

    @Transactional
    public Compaign update(Integer compainId, Compaign compaignUpdate) {
        log.info("Обновление компании c {} id на {}", compainId, compaignUpdate);

        Compaign oldCompany = repository.findById(compainId).orElseThrow(
                () -> new RuntimeException("Компании не найдено с таким id " + compainId));

        if (Objects.nonNull(oldCompany)) {
            oldCompany.setName(compaignUpdate.getName());
            oldCompany.setBudget(compaignUpdate.getBudget());
            oldCompany.setTargetAudience(compaignUpdate.getTargetAudience());
            oldCompany.setStartDate(compaignUpdate.getStartDate());
            oldCompany.setEndDate(compaignUpdate.getEndDate());

            if (Objects.nonNull(compaignUpdate.getPerson())) {
                oldCompany.setPerson(compaignUpdate.getPerson());
            }

            repository.save(oldCompany);
        }

        return oldCompany;
    }

    @Transactional
    public void drop(Integer compainId) {
        Compaign deleteCompaign = repository.findById(compainId).orElseThrow(
                () -> new RuntimeException("Компании не найдено с таким id " + compainId));

        log.info("Удаление компании с {} id", deleteCompaign);

        repository.delete(deleteCompaign);
    }
}
