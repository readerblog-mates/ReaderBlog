package readerblog.mates.readerblog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.services.BookService;
import readerblog.mates.readerblog.services.CategoryService;
import readerblog.mates.readerblog.services.GenreService;
import readerblog.mates.readerblog.utils.BookFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Sergey Petukhov
 */

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookFilter bookFilter;
    private final GenreService genreService;
    private final CategoryService categoryService;

    @GetMapping("/books")
    public String showBook(Model model, @RequestParam Long id ){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "books";
    }

    @GetMapping("/books/search") //поиск с параметрами запроса , либо в теле POST - запроса передаем данные
    public String search(
                         @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                         HttpServletRequest request,
                         Model model){
        // выборка данных
        if (pageNumber == null || pageNumber < 1)
            pageNumber = 1;
        bookFilter.takeRequest(request);

        List<Book> books = bookService.findAll();
        Page<Book> page = bookService.findAllByPagingAndFiltering(bookFilter.getSpecification(),
                                                                  PageRequest.of(0, 10, Sort.Direction.ASC, "id"));
        model.addAttribute("books", books);
        model.addAttribute("page", page);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("filters", bookFilter.getFiltersString());
        model.addAttribute("pageNumber", pageNumber);
        return "books";
    }
}
