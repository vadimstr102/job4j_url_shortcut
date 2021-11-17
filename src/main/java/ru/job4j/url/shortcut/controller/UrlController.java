package ru.job4j.url.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url.shortcut.dto.UrlRequestDto;
import ru.job4j.url.shortcut.dto.UrlResponseDto;
import ru.job4j.url.shortcut.dto.UrlStatisticsResponseDto;
import ru.job4j.url.shortcut.model.Url;
import ru.job4j.url.shortcut.service.UrlService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/convert")
    public ResponseEntity<UrlResponseDto> convert(@Valid @RequestBody UrlRequestDto urlRequestDto) {
        Url url = urlService.convert(urlRequestDto);
        return new ResponseEntity<>(new UrlResponseDto(url.getCode()), HttpStatus.OK);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        Url url = urlService.getUrl(code);
        if (url == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url is not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url.getUrl())).build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<UrlStatisticsResponseDto>> statistics() {
        return new ResponseEntity<>(urlService.getStatistics(), HttpStatus.OK);
    }
}
