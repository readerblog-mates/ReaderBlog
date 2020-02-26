package readerblog.mates.readerblog.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Category;
import readerblog.mates.readerblog.repositories.BookRepository;
import readerblog.mates.readerblog.services.AuthorService;
import readerblog.mates.readerblog.services.BookService;
import readerblog.mates.readerblog.services.CategoryService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private CategoryService categoryService;
    private AuthorService authorService;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @Transactional
    public List<Book> findByGenre(Long genreId) {
        if (genreId != null)
            return bookRepository.findAllByGenreId(genreId);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByCategory(Long categoryId) {
        if (categoryId != null){
            Category category = categoryService.findOne(categoryId);
            if (category != null)
                return bookRepository.findAllByCategoriesIn(Collections.singletonList(category));
        }
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByCategoryAndGenre(Long categoryId, Long genreId) {
        if (categoryId != null && genreId != null){
            Category category = categoryService.findOne(categoryId);
            if (category != null)
                return bookRepository.findAllByGenreIdAndCategoriesIn(genreId, Collections.singletonList(category));
        }
        return null;
    }

    @Override
    @Transactional
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        if (id != null && bookRepository.findById(id).isPresent())
            bookRepository.removeById(id);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        if (book != null)
            return bookRepository.save(book);
        return null;
    }

    @Override
    @Transactional
    public List<Book> saveAll(List<Book> books) {
        if (books != null && books.size() > 0)
            return bookRepository.saveAll(books);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByTitle(String title) {
        if (title != null)
            return bookRepository.findAllByTitle(title);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByPages(Integer pages) {
        if (pages != null)
            return bookRepository.findAllByPages(pages);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByFormat(String format) {
        if (format != null)
            return bookRepository.findAllByFormat(format);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByYear(Integer yearOfWriting) {
        if (yearOfWriting != null)
            return bookRepository.findAllByYearOfWriting(yearOfWriting);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByAuthors(List<Long> authorIds) {
        if (authorIds != null && authorIds.size() > 0){
            List<Author> authors = authorService.findAllById(authorIds);
            if (authors != null && authors.size() > 0)
                return bookRepository.findAllByAuthorsIn(authors);
        }
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByLanguage(String originLanguage) {
        if (originLanguage != null)
            return bookRepository.findAllByOriginLanguage(originLanguage);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByPublisher(String publisher) {
        if (publisher != null)
            return bookRepository.findAllByPublisher(publisher);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByRating(Double rating) {
        if (rating != null)
            return bookRepository.findAllByRating(roundingRating(rating));
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByCategories(List<Long> categoryIds) {
        if (categoryIds != null && categoryIds.size() > 0){
            List<Category> categories = categoryService.findAllById(categoryIds);
            if (categories != null && categories.size() > 0)
                return bookRepository.findAllByCategoriesIn(categories);
        }
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByRating(Double ratingMin, Double ratingMax) {
        if (ratingMin != null && ratingMax != null)
            return bookRepository.findAllByRatingBetween(roundingRating(ratingMin), roundingRating(ratingMax));
        return null;
    }

    @Override
    @Transactional
    public List<Book> findAllById(List<Long> ids) {
        if (ids != null && ids.size() > 0)
            return bookRepository.findAllByIdIn(ids);
        return null;
    }

    private Double roundingRating(Double rating){
        if (rating != null)
            return BigDecimal.valueOf(rating).setScale(1, RoundingMode.HALF_UP).doubleValue();
        return null;
    }
}
