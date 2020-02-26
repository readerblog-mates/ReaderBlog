package readerblog.mates.readerblog.services;

import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Genre;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public interface GenreService {
    List<Genre> findAll();
    void remove(Long id);
    void remove(String name);
    Genre findOne(String name);
    Genre findOne(Long id);
    Genre save(Genre genre);
    void changeName(String oldName, String newName);
    List<Genre> findByBooks(List<Long> bookIds);
}
