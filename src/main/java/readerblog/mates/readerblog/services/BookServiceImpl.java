package readerblog.mates.readerblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.repositories.BookRepository;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public List<Book> findByGenreId(Long genreId) {
        return bookRepository.findAllByCategoryId(genreId);
    }

    @Override
    @Transactional
    public List<Book> findByCategoryId(Long categoryId) {
        return bookRepository.findAllByCategoryId(categoryId);
    }

    @Override
    @Transactional
    public List<Book> findAllByCategoryIdAndGenreId(Long categoryId, Long genreId) {
        return bookRepository.findAllByCategoryIdAndGenreId(categoryId, genreId);
    }
}
