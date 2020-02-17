package readerblog.mates.readerblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.repositories.AuthorRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private BookService bookService;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public Author save(Author author) {
        if (author != null){
            author.setRating(roundingRating(author.getRating()));
            return authorRepository.save(author);
        }
        return null;
    }

    @Override
    @Transactional
    public Author findOneById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public List<Author> findByRating(Double rating) {
        return authorRepository.findByRating(roundingRating(rating));
    }

    /**
     * Общий метод для поиска Автора по имени, фамилии и/или отчеству. Возможно, не нужен при наличии спецификаций.
     * @param firstName
     * @param lastName
     * @param patronymicName
     * @return
     */
    @Override
    @Transactional
    public List<Author> findByName(String firstName, String lastName, String patronymicName) {
        if (firstName != null && lastName != null && patronymicName != null)
            return authorRepository.findByFirstNameAndLastNameAndPatronymicName(firstName, lastName, patronymicName);
        if (firstName != null && lastName != null)
            return authorRepository.findByFirstNameAndLastName(firstName, lastName);
        if (firstName != null && patronymicName != null)
            return authorRepository.findByFirstNameAndPatronymicName(firstName, patronymicName);
        if (lastName != null && patronymicName != null)
            return authorRepository.findByLastNameAndPatronymicName(lastName, patronymicName);
        if (firstName != null)
            return authorRepository.findByFirstName(firstName);
        if (lastName != null)
            return authorRepository.findByLastName(lastName);
        if (patronymicName != null)
            return authorRepository.findByPatronymicName(patronymicName);
        return null;
    }

    @Override
    @Transactional
    public List<Author> findByGenre(Long genreId) {
        return authorRepository.findAllByBooks(bookService.findByGenreId(genreId));
    }

    @Override
    @Transactional
    public List<Author> findByCategory(Long categoryId) {
        return authorRepository.findAllByBooks(bookService.findByCategoryId(categoryId));
    }

    @Override
    public List<Long> findIdByGenre(Long genreId) {
        List<Long> authorsId = new ArrayList<>();
        findByGenre(genreId).forEach(author -> authorsId.add(author.getId()));
        return authorsId;
    }

    @Override
    public List<Long> findIdByCategory(Long categoryId) {
        List<Long> authorsId = new ArrayList<>();
        findByCategory(categoryId).forEach(author -> authorsId.add(author.getId()));
        return authorsId;
    }

    @Override
    @Transactional
    public List<Author> findByCategoryAndGenre(Long categoryId, Long genreId) {
        return authorRepository.findAllByBooks(bookService.findAllByCategoryIdAndGenreId(categoryId, genreId));
    }

    @Override
    @Transactional
    public Page<Author> findAllByPagingAndFiltering(Specification<Author> specification, Pageable pageable) {
        return authorRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public void remove(Long authorId) {
        authorRepository.removeById(authorId);
    }

    @Override
    @Transactional
    public List<Long> findByBook(Book book) {
        List<Long> authorsId = new ArrayList<>();
        authorRepository.findAllByBooks(Collections.singletonList(book)).forEach(author -> authorsId.add(author.getId()));
        return authorsId;
    }

    /**
     * Округляет double до 1 знака после запятой.
     * @param rating рейтинг
     * @return округленный рейтинг
     */
    private Double roundingRating(Double rating){
        if (rating != null)
            return BigDecimal.valueOf(rating).setScale(1, RoundingMode.HALF_UP).doubleValue();
        return null;
    }
}
