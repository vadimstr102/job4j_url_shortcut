package ru.job4j.url.shortcut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url.shortcut.dto.UrlRequestDto;
import ru.job4j.url.shortcut.dto.UrlStatisticsResponseDto;
import ru.job4j.url.shortcut.model.Url;
import ru.job4j.url.shortcut.repository.UrlRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final PersonService personService;

    public UrlService(UrlRepository urlRepository, PersonService personService) {
        this.urlRepository = urlRepository;
        this.personService = personService;
    }

    public Url convert(UrlRequestDto urlRequestDto) {
        Url url = urlRepository.findByUrl(urlRequestDto.getUrl());
        if (url != null) {
            return url;
        }
        String code = StringEncoder.encode(urlRequestDto.getUrl());
        url = new Url(urlRequestDto.getUrl(), code, personService.getCurrentPerson());
        return urlRepository.save(url);
    }

    @Transactional
    public Url getUrl(String code) {
        Url url = urlRepository.findByCode(code);
        if (url != null) {
            urlRepository.incrementCallCount(url.getId());
        }
        return url;
    }

    public List<UrlStatisticsResponseDto> getStatistics() {
        List<UrlStatisticsResponseDto> list = new ArrayList<>();
        urlRepository.findAllByPerson(personService.getCurrentPerson()).forEach(
                url -> list.add(new UrlStatisticsResponseDto(url.getUrl(), url.getCallCounter()))
        );
        return list;
    }
}
