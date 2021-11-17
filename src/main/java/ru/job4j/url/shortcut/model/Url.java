package ru.job4j.url.shortcut.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String code;

    @Column(name = "call_counter")
    private int callCounter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    public Url() {
    }

    public Url(String url, String code, Person person) {
        this.url = url;
        this.code = code;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCallCounter() {
        return callCounter;
    }

    public void setCallCounter(int callCounter) {
        this.callCounter = callCounter;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Url url = (Url) o;
        return id == url.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
