package ru.job4j.url.shortcut.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.Person;
import ru.job4j.url.shortcut.dto.PersonRequestDto;
import ru.job4j.url.shortcut.dto.PersonResponseDto;
import ru.job4j.url.shortcut.repository.PersonRepository;

import java.time.LocalDateTime;

import static java.util.Collections.emptyList;

@Service
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonService(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personRepository.findByLogin(login);
        if (person == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(person.getLogin(), person.getPassword(), emptyList());
    }

    public PersonResponseDto savePerson(PersonRequestDto personRequestDto) {
        String login = StringEncoder.encode(personRequestDto.getSite());
        String password = StringEncoder.encode(personRequestDto.getSite() + LocalDateTime.now());
        Person person = personRepository.findByLogin(login);
        if (person != null) {
            return new PersonResponseDto(false, "", "");
        }
        person = new Person(login, bCryptPasswordEncoder.encode(password));
        personRepository.save(person);
        return new PersonResponseDto(true, login, password);
    }

    public Person getCurrentPerson() {
        String personLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return personRepository.findByLogin(personLogin);
    }
}
