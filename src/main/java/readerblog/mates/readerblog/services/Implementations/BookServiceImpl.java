package readerblog.mates.readerblog.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
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
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private CategoryService categoryService;
    private GenreService genreService;
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findByGenre(Long genreId) {
        List<Book> books = new ArrayList<>();
        Genre genre = genreService.findOne(genreId);
        if (genre != null)
            return genre.getBooks();
        return books;
    }

    @Override
    public List<Book> findByCategory(Long categoryId) {
        List<Book> books = new ArrayList<>();
        Category category = categoryService.findOne(categoryId);
        if (category != null)
            return category.getBooks();
        return books;
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
        if (id != null && bookRepository.findById(id).isPresent())
            return bookRepository.removeById(id);
        return null;
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
            books.forEach(book -> book.setRating(Utilities.roundingRating(book.getRating())));
            return bookRepository.saveAll(books);
        }
        return result;
    }

    @Override
    @Transactional
    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();
        if (title != null)
            return bookRepository.findAllByTitle(title);
        return books;
    }

    @Override
    @Transactional
    public List<Book> findByPagesBetween(Integer firstPage, Integer lastPage) {
        List<Book> books = new ArrayList<>();
        if (firstPage != null && lastPage != null)
            return bookRepository.findAllByPagesBetween(firstPage, lastPage);
        return books;
    }

    @Override
    @Transactional
    public List<Book> findByFormat(List<String> formats) {
        List<Book> books = new ArrayList<>();
        if (formats != null && formats.size() > 0)
            return bookRepository.findAllByFormatIn(formats);
        return books;
    }

    @Override
    @Transactional
    public List<Book> findByYear(List<Integer> yearOfWritings) {
        List<Book> books = new ArrayList<>();
        if (yearOfWritings != null && yearOfWritings.size() > 0)
            return bookRepository.findAllByYearOfWritingIn(yearOfWritings);
        return books;
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
        List<Book> books = new ArrayList<>();
        if (originLanguages != null && originLanguages.size() > 0)
            return bookRepository.findAllByOriginLanguageIn(originLanguages);
        return books;
    }

    @Override
    @Transactional
    public List<Book> findByPublisher(List<String> publishers) {
        List<Book> books = new ArrayList<>();
        if (publishers != null && publishers.size() > 0)
            return bookRepository.findAllByPublisherIn(publishers);
        return books;
    }

    @Override
    @Transactional
    public List<Book> findByRating(Double ratingMin, Double ratingMax) {
        List<Book> books = new ArrayList<>();
        if (ratingMin != null && ratingMax != null)
            return bookRepository.findAllByRatingBetween(Utilities.roundingRating(ratingMin), Utilities.roundingRating(ratingMax));
        return books;
    }

    @Override
    @Transactional
    public Boolean updateRating(Long id, Double rating){
        if (id != null && rating != null){
            if (rating > 10.0 || rating < 0.0) throw new IllegalArgumentException();
            Book book = findOne(id);
            if (book != null){
                book.setRating(Utilities.roundingRating(rating));
                return save(book) != null;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public Book findOne(Long id) {
        if (id != null && bookRepository.findById(id).isPresent())
            return bookRepository.findById(id).get();
        return null;
    }
}
