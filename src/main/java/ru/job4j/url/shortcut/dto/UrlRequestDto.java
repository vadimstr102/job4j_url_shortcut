package ru.job4j.url.shortcut.dto;

import org.hibernate.validator.constraints.URL;

public class UrlRequestDto {
    @URL(message = "Url is not valid")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
