package ru.job4j.url.shortcut.dto;

import javax.validation.constraints.NotBlank;

public class PersonRequestDto {
    @NotBlank(message = "Site must be not empty")
    private String site;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
