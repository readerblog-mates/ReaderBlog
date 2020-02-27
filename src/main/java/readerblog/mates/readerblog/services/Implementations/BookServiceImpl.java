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
import readerblog.mates.readerblog.utils.Utilities;

import java.util.Collections;
import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private CategoryService categoryService;
//    private AuthorService authorService;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @Autowired
//    public void setAuthorService(AuthorService authorService) {
//        this.authorService = authorService;
//    }

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
        if (books != null && books.size() > 0){
            books.forEach(book -> book.setRating(Utilities.roundingRating(book.getRating())));
            return bookRepository.saveAll(books);
        }
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
    public List<Book> findByPagesBetween(Integer firstPage, Integer lastPage) {
        if (firstPage != null && lastPage != null)
            return bookRepository.findAllByPagesBetween(firstPage, lastPage);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByFormat(List<String> formats) {
        if (formats != null && formats.size() > 0)
            return bookRepository.findAllByFormatIn(formats);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByYear(List<Integer> yearOfWritings) {
        if (yearOfWritings != null && yearOfWritings.size() > 0)
            return bookRepository.findAllByYearOfWritingIn(yearOfWritings);
        return null;
    }

//    @Override
//    @Transactional
//    public List<Book> findByAuthors(List<Long> authorIds) {
//        if (authorIds != null && authorIds.size() > 0){
//            List<Author> authors = authorService.findAllById(authorIds);
//            if (authors != null && authors.size() > 0)
//                return bookRepository.findAllByAuthorsIn(authors);
//        }
//        return null;
//    }

    @Override
    @Transactional
    public List<Book> findByLanguage(List<String> originLanguages) {
        if (originLanguages != null && originLanguages.size() > 0)
            return bookRepository.findAllByOriginLanguageIn(originLanguages);
        return null;
    }

    @Override
    @Transactional
    public List<Book> findByPublisher(List<String> publishers) {
        if (publishers != null && publishers.size() > 0)
            return bookRepository.findAllByPublisherIn(publishers);
        return null;
    }

//    @Override
//    @Transactional
//    public List<Book> findByCategories(List<Long> categoryIds) {
//        if (categoryIds != null && categoryIds.size() > 0){
//            List<Category> categories = categoryService.findAllById(categoryIds);
//            if (categories != null && categories.size() > 0)
//                return bookRepository.findAllByCategoriesIn(categories);
//        }
//        return null;
//    }

    @Override
    @Transactional
    public List<Book> findByRating(Double ratingMin, Double ratingMax) {
        if (ratingMin != null && ratingMax != null)
            return bookRepository.findAllByRatingBetween(Utilities.roundingRating(ratingMin), Utilities.roundingRating(ratingMax));
        return null;
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

//    @Override
//    @Transactional
//    public List<Book> findAllById(List<Long> ids) {
//        if (ids != null && ids.size() > 0)
//            return bookRepository.findAllByIdIn(ids);
//        return null;
//    }
}
