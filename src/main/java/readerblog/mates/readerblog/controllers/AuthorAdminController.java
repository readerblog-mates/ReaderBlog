package readerblog.mates.readerblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.services.implementations.AuthorAdminService;

@Controller
@RequestMapping("/authorsAdmin")
public class AuthorAdminController {
    private AuthorAdminService authorAdminService;

    @Autowired
    public void setAuthorAdminService(AuthorAdminService authorAdminService) {
        this.authorAdminService = authorAdminService;
    }

    @GetMapping("/")
    public String showAllProducts(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        model.addAttribute("authors", authorAdminService.getAllAuthors());
        return "authors";
    }

    @PostMapping("/add")
    public String addNewAuthor(@ModelAttribute(name = "addAuthor") Author addAuthor) {
        authorAdminService.save(addAuthor);
        return "redirect:/authorsAdmin/";
    }



    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable(name = "id") Long id) {
       authorAdminService.deleteById(id);
        return "redirect:/authorsAdmin/";
            }


    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        Author author = authorAdminService.getAuthorById(id);
        model.addAttribute("author", author);
        return "edit_author";
    }

    @PostMapping("/edit")
    public String saveModifiedProduct(@ModelAttribute(name = "author") Author author) {
        authorAdminService.save(author);
        return "redirect:/authorsAdmin/";
    }



}
