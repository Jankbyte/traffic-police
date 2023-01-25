package ru.jankbyte.trafficpolice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

import org.springframework.data.domain.Page;
import lombok.*;

/**
 * Представление модели-страницы.
 * <p>Создаётся из объекта {@link org.springframework.data.domain.Page}.
 * Далее, данный объект можно отдавать клиенту: в качестве JSON-ответа или
 * MVC-объекта для Thymeleaf. Нужно учитывать - что первая страница будет начинаться
 * с единицы, а не с нуля (соотв. - последняя страницы будет равна переменной <code>pages</code>,
 * т.е. общему кол-ву страниц).</p>
 */
@Setter
@Getter
@JsonPropertyOrder({"page", "pages",
    "items", "elements"})
public class ResultPage<T> {
    @JsonProperty("total_items")
    private Long items;

    @JsonProperty("total_pages")
    private Integer pages;

    @JsonProperty("current_page")
    private Integer page;

    private List<T> elements;

    public ResultPage(Page<T> page) {
        this.elements = page.getContent();
        this.items = page.getTotalElements();
        this.pages = page.getTotalPages();
        this.page = page.getNumber() + 1;
    }
}
