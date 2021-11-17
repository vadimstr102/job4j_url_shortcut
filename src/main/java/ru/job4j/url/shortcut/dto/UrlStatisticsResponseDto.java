package ru.job4j.url.shortcut.dto;

public class UrlStatisticsResponseDto {
    private String url;
    private int total;

    public UrlStatisticsResponseDto() {
    }

    public UrlStatisticsResponseDto(String url, int total) {
        this.url = url;
        this.total = total;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
