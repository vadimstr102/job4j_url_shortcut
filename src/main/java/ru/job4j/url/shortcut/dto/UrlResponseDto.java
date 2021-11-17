package ru.job4j.url.shortcut.dto;

public class UrlResponseDto {
    private String code;

    public UrlResponseDto() {
    }

    public UrlResponseDto(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
