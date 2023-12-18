package ru.poplaukhin.AdvertisingCompanies.security;

import org.springframework.security.core.userdetails.User;
import ru.poplaukhin.AdvertisingCompanies.entity.Person;

import java.util.List;

public class AuthPerson extends User {

    public AuthPerson(Person person) {
        super(person.getLogin(), person.getPassword(), List.of(person.getRole()));
    }
}
