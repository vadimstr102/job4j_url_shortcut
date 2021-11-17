package ru.job4j.url.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.model.Person;
import ru.job4j.url.shortcut.model.Url;

import java.util.List;

@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {
    Url findByCode(String shortLink);

    Url findByUrl(String url);

    List<Url> findAllByPerson(Person person);

    @Modifying
    @Query("update Url u set u.callCounter = u.callCounter + 1 where u.id = :id")
    void incrementCallCount(@Param("id") int id);
}
