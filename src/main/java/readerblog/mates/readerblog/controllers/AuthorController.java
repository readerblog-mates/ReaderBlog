package readerblog.mates.readerblog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.services.AuthorService;
import readerblog.mates.readerblog.services.CategoryService;
import readerblog.mates.readerblog.services.GenreService;
import readerblog.mates.readerblog.utils.AuthorFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mzheldin@yandex.ru
 */

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorFilter authorFilter;
    private final GenreService genreService;
    private final CategoryService categoryService;

    /**
     * Основная страница Авторов, результаты поиска по жанру/категории/ФИО/рейтингу направляют на нее же.
     * @param model
     * @param request
     * @return
     */
    @GetMapping()
    public String authors(Model model, HttpServletRequest request,
                          @RequestParam(name = "pageNumber", required = false) Integer pageNumber){
        if (pageNumber == null || pageNumber < 1)
            pageNumber = 1;
        authorFilter.takeRequest(request);
        Page<Author> page = authorService.findAllByPagingAndFiltering(authorFilter.getSpecification(), PageRequest.of(pageNumber - 1, 10, Sort.Direction.ASC, "id"));
        model.addAttribute("page", page);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("filters", authorFilter.getFiltersString());
        model.addAttribute("letters", authorService.getLetters());
        model.addAttribute("pageNumber", pageNumber);
        return "authors";
    }

    /**
     * Вариант с получением Author по id, возможно, стоит оформить в виде gson'а
     * @param id
     * @return
     */
//    @ResponseBody
//    @GetMapping("/{id}")
//    public Author author(@PathVariable(value = "id") Long id){
//        return authorService.findOne(id);
//    }

    /**
     * Вариант с получением страницы Автора по id
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String author(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("author", authorService.findOne(id));
        return "author";
    }

    /**
     * Редактирование Автора, направляет на форму редактирования автора, если для этого предполагается отдельная страница.
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit")
    public String showAuthorEditForm(Model model, @RequestParam(name = "id", required = false) Long id){
        Author author;
        if (id != null)
            author = authorService.findOne(id);
        else
            author = new Author();
        model.addAttribute("author", author);
        return "edit_author";
    }

    /**
     * Сохранение изменений в Авторе, направляет на страницу отредактированного Автора
     * @param author
     * @return
     */
    @PostMapping("/edit")
    public String saveAuthor(@ModelAttribute(name = "author")Author author){
        Author savedAuthor = authorService.save(author);
        return "redirect:/authors/" + savedAuthor.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(Model model, @PathVariable(name = "id") Long id){
        Author author = authorService.remove(id);
        System.out.println(author.getId());
        return "redirect:/authors/";
    }

    //Подобрать автора, сначала нужно придумать алгоритм
//    @GetMapping("/authors/choose")
//    public String choose(){
//        // выборка данных
//        return "authors";
//    }
}
