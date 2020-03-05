package readerblog.mates.readerblog.services.Implementations;

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
import readerblog.mates.readerblog.repositories.AuthorRepository;
import readerblog.mates.readerblog.services.AuthorService;
import readerblog.mates.readerblog.services.CategoryService;
import readerblog.mates.readerblog.services.GenreService;
import readerblog.mates.readerblog.utils.Utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mzheldin@yandex.ru
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final GenreService genreService;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public Author save(Author author) {
        if (author != null ){
            if (author.getRating() != null)
                author.setRating(Utilities.roundingRating(author.getRating()));
            return authorRepository.save(author);
        }
        return null;
    }

    @Override
    @Transactional
    public Author findOne(Long id) {
        return (id != null && authorRepository.findById(id).isPresent()) ? authorRepository.findById(id).get() : null;
    }

    @Override
    @Transactional
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public List<Author> findByRating(Double ratingMin, Double ratingMax) {
        return authorRepository.findAllByRatingBetween(ratingMin == null ? 0.0 : Utilities.checkRatingLimits(ratingMin),
                ratingMax == null ? 10.0 : Utilities.checkRatingLimits(ratingMax));
    }

    /**
     * Общий метод для поиска Автора по имени, фамилии и/или отчеству. Возможно, не нужен при наличии спецификаций.
     * @param firstName имя Автора
     * @param lastName фамилия Автора
     * @param patronymicName отчество Автора
     * @return список Авторов
     */
    @Override
    @Transactional
    public List<Author> findByName(String firstName, String lastName, String patronymicName) {
        List<Author> authors = new ArrayList<>();
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
        return authors;
    }

    @Override
    public List<Author> findByGenre(Long genreId) {
        Set<Author> authorSet = new HashSet<>();
        Genre genre = genreService.findOne(genreId);
        if (genre != null){
            List<Book> books = genre.getBooks();
            for (Book book : books)
                authorSet.addAll(book.getAuthors());
        }
        return new ArrayList<>(authorSet);
    }

    @Override
    public List<Author> findByCategory(Long categoryId) {
        Set<Author> authorSet = new HashSet<>();
        Category category = categoryService.findOne(categoryId);
        if (category != null){
            List<Book> books = category.getBooks();
            for (Book book : books)
                authorSet.addAll(book.getAuthors());
        }
        return new ArrayList<>(authorSet);
    }

    @Override
    public List<Long> findIdByGenre(Long genreId) {
        List<Long> ids = new ArrayList<>();
        findByGenre(genreId).forEach(author -> ids.add(author.getId()));
        return ids;
    }

    @Override
    public List<Long> findIdByCategory(Long categoryId) {
        List<Long> ids = new ArrayList<>();
        findByCategory(categoryId).forEach(author -> ids.add(author.getId()));
        return ids;
    }

    @Override
    public List<Author> findByCategoryAndGenre(Long categoryId, Long genreId) {
        List<Author> authors = new ArrayList<>();
        List<Author> authorsByGenre = findByGenre(genreId);
        for (Author author : findByCategory(categoryId))
            if (authorsByGenre.contains(author))
                authors.add(author);
        return authors;
    }

    @Override
    @Transactional
    public Page<Author> findAllByPagingAndFiltering(Specification<Author> specification, Pageable pageable) {
        return authorRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public Author remove(Long authorId) {
        return (authorId != null && authorRepository.findById(authorId).isPresent()) ?
                authorRepository.removeById(authorId) : null;
    }

    @Override
    @Transactional
    public List<Author> findByLastNameFirstLetter(String firstLetters){
        return firstLetters != null ? authorRepository.findAllByLastNameStartingWith(firstLetters) : new ArrayList<>();
    }

    @Override
    @Transactional
    public List<Author> findByFirstNameFirstLetter(String firstLetters) {
        return firstLetters != null ? authorRepository.findAllByFirstNameStartingWith(firstLetters) : new ArrayList<>();
    }

    @Override
    @Transactional
    public List<Author> saveAll(List<Author> authors) {
        List<Author> result = new ArrayList<>();
        if (authors != null && authors.size() > 0){
            authors.forEach(author -> author.setRating(Utilities.roundingRating(author.getRating())));
            return authorRepository.saveAll(authors);
        }
        return result;
    }

    @Override
    @Transactional
    public List<Author> findAllByOrderByLastName(){
        return authorRepository.findAllByOrderByLastName();
    }

    @Override
    @Transactional
    public List<Author> findAllByOrderByFirstName() {
        return  authorRepository.findAllByOrderByFirstName();
    }

    @Override
    public Boolean updateRating(Long id, Double rating){
        if (id != null && rating != null){
            Author author = findOne(id);
            if (author != null){
                author.setRating(Utilities.checkRatingLimits(rating));
                return save(author) != null;
            }
        }
        return false;
    }
}
