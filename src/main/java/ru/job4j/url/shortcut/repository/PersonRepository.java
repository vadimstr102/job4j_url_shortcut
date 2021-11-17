package ru.job4j.url.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findByLogin(String login);
}
