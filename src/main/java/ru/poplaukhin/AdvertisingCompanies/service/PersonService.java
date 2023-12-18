package ru.poplaukhin.AdvertisingCompanies.service;


import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.poplaukhin.AdvertisingCompanies.dto.PersonDto;
import ru.poplaukhin.AdvertisingCompanies.entity.Person;
import ru.poplaukhin.AdvertisingCompanies.repository.PersonRepository;
import ru.poplaukhin.AdvertisingCompanies.response.EntityResponse;
import ru.poplaukhin.AdvertisingCompanies.security.AuthPerson;
import ru.poplaukhin.AdvertisingCompanies.utils.MappingPersonUtils;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService implements UserDetailsService {
    @Autowired
    private PersonRepository repository;

    @Autowired
    private MappingPersonUtils utils;

    private final Logger log = LoggerFactory.getLogger(Person.class);

    @Transactional
    public List<Person> getAll() {
        log.info("Получение всех людей");

        return repository.findAll();
    }

    @Transactional
    public EntityResponse<Person> getPersonById(Integer personId) {
        log.info("Получение человека с {} id", personId);
        Person person = repository.findById(personId).orElseThrow(
                () -> new RuntimeException("Человека не найдено с таким id " + personId));

        return new EntityResponse<>(true, person);
    }

    @Transactional
    public Person save(Person person) {
        log.info("Сохранение человека {}", person.toString());

        return repository.save(person);
    }

    @Transactional
    public Person update(Integer personId, Person person) {
        Person oldPerson = repository.findById(personId).orElseThrow(
                () -> new RuntimeException("Человека не найдено с таким id " + personId));

        log.info("Обновление человека с {} id на такого человека {} ", personId, person);

        if (Objects.nonNull(oldPerson)) {
            oldPerson.setFirstName(person.getFirstName());
            oldPerson.setSecondName(person.getSecondName());
            oldPerson.setLastName(person.getLastName());
            oldPerson.setEmail(person.getEmail());
            oldPerson.setRole(person.getRole());

            repository.save(oldPerson);
        }

        return oldPerson;
    }

    @Transactional
    public void drop(Integer id) {
        Person person = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Человека не найдено с таким id " + id));

        log.info("Удаление человека с таким {} id", id);

        repository.delete(person);
    }

    public void register(PersonDto personDto) {
        Person person = repository.findByLogin(personDto.getLogin());

        if (Objects.nonNull(person)) {
            throw new NoClassDefFoundError("Человек с таким логиным уже существует");
        } else {
            repository.save(utils.mapToEntityFromDto(personDto));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = repository.findByLogin(username);

        if (Objects.isNull(person)) {
            throw new RuntimeException("Пользователь с таким логиным " + username + " не найден");
        }

        return new AuthPerson(person);
    }
 }
