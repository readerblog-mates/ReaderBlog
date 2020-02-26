package readerblog.mates.readerblog.services;

import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Category;
import readerblog.mates.readerblog.entities.Genre;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public interface CategoryService {
    List<Category> findAll();
    Category findOne(Long id);
    Category findOne(String name);
    void changeName(String oldName, String newName);
    void remove(Long id);
    void remove(String name);
    Category save(Category category);
    List<Category> findByBooks(List<Long> bookIds);
    List<Category> findAllById(List<Long> ids);
}
