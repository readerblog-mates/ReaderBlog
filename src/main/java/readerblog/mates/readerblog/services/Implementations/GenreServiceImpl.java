package readerblog.mates.readerblog.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Genre;
import readerblog.mates.readerblog.repositories.GenreRepository;
import readerblog.mates.readerblog.services.BookService;
import readerblog.mates.readerblog.services.GenreService;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;
    private BookService bookService;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        if (id != null && genreRepository.findById(id).isPresent())
            genreRepository.removeById(id);
    }

    @Override
    @Transactional
    public void remove(String name) {
        if (name != null && genreRepository.findByName(name) != null)
            genreRepository.removeByName(name);
    }

    @Override
    @Transactional
    public Genre findOne(String name) {
        if (name != null)
            return genreRepository.findByName(name);
        return null;
    }

    @Override
    @Transactional
    public Genre findOne(Long id) {
        if (id != null)
            return genreRepository.findById(id).get();
        return null;
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        if (genre != null)
            return genreRepository.save(genre);
        return null;
    }

    @Override
    @Transactional
    public void changeName(String oldName, String newName) {
        if (oldName != null && newName != null)
            genreRepository.update(oldName, newName);
    }

    @Override
    @Transactional
    public List<Genre> findByBooks(List<Long> bookIds) {
        if (bookIds != null && bookIds.size() > 0){
            List<Book> books = bookService.findAllById(bookIds);
            if (books != null && books.size() > 0)
                genreRepository.findAllByBooksIn(books);
        }
        return null;
    }
}
