package ru.jankbyte.trafficpolice.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Класс-утилита для создания страниц из запросов.
 */
public class PageableUtils {
    private PageableUtils() {
    }

    /**
     * Создать условие для страницы.
     */
    public static Pageable getPageable(int page) {
        return PageRequest.of(page - 1, 3);
    }
}
