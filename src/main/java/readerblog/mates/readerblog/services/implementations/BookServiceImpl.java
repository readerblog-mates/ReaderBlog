package readerblog.mates.readerblog.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Category;
import readerblog.mates.readerblog.entities.Genre;
import readerblog.mates.readerblog.repositories.BookRepository;
import readerblog.mates.readerblog.services.AuthorService;
import readerblog.mates.readerblog.services.BookService;
import readerblog.mates.readerblog.services.CategoryService;
import readerblog.mates.readerblog.services.GenreService;
import readerblog.mates.readerblog.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @Override
    public List<Book> findByGenre(Long genreId) {
        Genre genre = genreService.findOne(genreId);
        return genre != null ? genre.getBooks() : new ArrayList<>();
    }

    @Override
    public List<Book> findByCategory(Long categoryId) {
        Category category = categoryService.findOne(categoryId);
        return category != null ? category.getBooks() : new ArrayList<>();
    }

    @Override
    public List<Book> findByCategoryAndGenre(Long categoryId, Long genreId) {
        List<Book> books = new ArrayList<>();
        List<Book> booksByGenre = findByGenre(genreId);
        for (Book book : findByCategory(categoryId))
            if (booksByGenre.contains(book))
                books.add(book);
        return books;
    }

    @Override
    @Transactional
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book remove(Long id) {
        return (id != null && bookRepository.findById(id).isPresent()) ? bookRepository.removeById(id) : null;
    }

    @Override
    @Transactional
    public Book save(Book book) {
        if (book != null){
            if (book.getRating() != null)
                book.setRating(Utilities.roundingRating(book.getRating()));
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    @Transactional
    public List<Book> saveAll(List<Book> books) {
        List<Book> result = new ArrayList<>();
        if (books != null && books.size() > 0){
            books.forEach(book -> book.setRating(Utilities.checkRatingLimits(book.getRating())));
            return bookRepository.saveAll(books);
        }
        return result;
    }

    @Override
    @Transactional
    public List<Book> findByTitle(String title) {
        return title != null ? bookRepository.findAllByTitle(title) : new ArrayList<>();
    }

    @Override
    @Transactional
    public List<Book> findByPagesBetween(Integer firstPage, Integer lastPage) {
        return (firstPage != null && lastPage != null) ?
                bookRepository.findAllByPagesBetween(firstPage, lastPage) : new ArrayList<>();
    }

    @Override
    @Transactional
    public List<Book> findByFormat(List<String> formats) {
        return (formats != null && formats.size() > 0) ? bookRepository.findAllByFormatIn(formats) : new ArrayList<>();
    }

    @Override
    @Transactional
    public List<Book> findByYear(List<Integer> yearOfWritings) {
        return (yearOfWritings != null && yearOfWritings.size() > 0) ?
                bookRepository.findAllByYearOfWritingIn(yearOfWritings) : new ArrayList<>();
    }

    @Override
    public List<Book> findByAuthors(List<Long> authorIds) {
        List<Book> books = new ArrayList<>();
        if (authorIds != null && authorIds.size() > 0)
            for (Long id : authorIds){
                Author author = authorService.findOne(id);
                if (author != null)
                    books.addAll(author.getBooks());
            }
        return books;
    }

    @Override
    @Transactional
    public List<Book> findByLanguage(List<String> originLanguages) {
        return (originLanguages != null && originLanguages.size() > 0) ?
                bookRepository.findAllByOriginLanguageIn(originLanguages) : new ArrayList<>();
    }

    @Override
    @Transactional
    public List<Book> findByPublisher(List<String> publishers) {
        return (publishers != null && publishers.size() > 0) ?
                bookRepository.findAllByPublisherIn(publishers) : new ArrayList<>();
    }

    @Override
    @Transactional
    public List<Book> findByRating(Double ratingMin, Double ratingMax) {
        return bookRepository.findAllByRatingBetween(ratingMin == null ? 0.0 : Utilities.checkRatingLimits(ratingMin),
                ratingMax == null ? 10.0 : Utilities.checkRatingLimits(ratingMax));
    }

    @Override
    public Boolean updateRating(Long id, Double rating){
        if (id != null && rating != null){
            Book book = findOne(id);
            if (book != null){
                book.setRating(Utilities.checkRatingLimits(rating));
                return save(book) != null;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public Book findOne(Long id) {
        return (id != null && bookRepository.findById(id).isPresent()) ? bookRepository.findById(id).get() : null;
    }

    @Override
    @Transactional
    public Page<Book> findAllByPagingAndFiltering(Specification<Book> specification, Pageable pageable) {
        return bookRepository.findAll(specification, pageable);
    }
}
