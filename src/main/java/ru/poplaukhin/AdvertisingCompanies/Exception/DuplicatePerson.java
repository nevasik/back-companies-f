package ru.poplaukhin.AdvertisingCompanies.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class DuplicatePerson extends NoClassDefFoundError {

    public DuplicatePerson(String s) {
        super(s);
    }
}
