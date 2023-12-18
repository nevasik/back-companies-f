package ru.poplaukhin.AdvertisingCompanies.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.poplaukhin.AdvertisingCompanies.dto.PersonDto;
import ru.poplaukhin.AdvertisingCompanies.entity.Person;

@Service
public class MappingPersonUtils {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // from Entity to DTO
    public PersonDto mapToDtoFromEntity(Person person) {
        PersonDto personDto = new PersonDto();

        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setSecondName(person.getSecondName());
        personDto.setLastName(person.getLastName());
        personDto.setEmail(person.getEmail());
        personDto.setRole(person.getRole());
        personDto.setLogin(person.getLogin());

        return personDto;
    }

    // from DTO to Entity
    public Person mapToEntityFromDto(PersonDto personDto) {
        Person person = new Person();

        person.setId(personDto.getId());
        person.setFirstName(personDto.getFirstName());
        person.setSecondName(personDto.getSecondName());
        person.setLastName(personDto.getLastName());
        person.setEmail(personDto.getEmail());
        person.setRole(personDto.getRole());
        person.setLogin(personDto.getLogin());
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
        person.setAvatar(personDto.getAvatar());

        return person;
    }
}
