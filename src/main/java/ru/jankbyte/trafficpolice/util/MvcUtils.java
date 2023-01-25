package ru.jankbyte.trafficpolice.util;

import ru.jankbyte.trafficpolice.model.ResultPage;
import org.springframework.ui.Model;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.List;

/**
 * Класс, упрощающий работу с MVC по нужным
 * спецификациям.
 */
public final class MvcUtils {
    private MvcUtils() {}

    /**
     * Устанавливает заголовок страницы.
     * @param model модель, куда установится пар-тр <code>title</code>
     * @param title текст заголовка
     */
    public static void setPageTitle(final Model model,
            final String title) {
        model.addAttribute("title", title);
    }

    /**
     * Устанавливает пар-тр для отображения страниц.
     * <p>В модель добавится два аттрибута:</p>
     * <ul>
     *   <li>Аттрибут "pages": если имеется более одной страницы</li>
     *   <li>Аттрибут "currentPage": текущая страница</li>
     * </ul>
     * @param model модель, куда установится пар-тры
     * @param pages Представление выгруженной страницы
     * @param path Имя пути, для переключения страниц
     */
    public static void setPageable(
            Model model, ResultPage pages, String path) {
        var count = pages.getPages();
        if (count > 1) {
            var numbers = IntStream.rangeClosed(1, count)
                .boxed().collect(Collectors.toList());
            model.addAttribute("pages", numbers);
        }
        model.addAttribute("path", path);
        model.addAttribute("currentPage", pages.getPage());
    }
}
