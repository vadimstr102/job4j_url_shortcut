package ru.job4j.url.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.shortcut.dto.PersonRequestDto;
import ru.job4j.url.shortcut.dto.PersonResponseDto;
import ru.job4j.url.shortcut.service.PersonService;

import javax.validation.Valid;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/registration")
    public ResponseEntity<PersonResponseDto> registration(@Valid @RequestBody PersonRequestDto personRequestDto) {
        PersonResponseDto personResponseDto = personService.savePerson(personRequestDto);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }
}
