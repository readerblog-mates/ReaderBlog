package readerblog.mates.readerblog.repositories.specifications;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.services.BookService;
import readerblog.mates.readerblog.utils.Utilities;

/**
 * @author Sergey Petukhov
 */

@Component
@SessionScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookSpecifications {
    private final BookService bookService;

    public Specification<Book> authorEquals(String author){
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("author"), author);
    }

    public Specification<Book> titleEquals(String title){
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("title"), title);
    }

    public Specification<Book> yearOfWritingEquals(String yearOfWriting){
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("yearOfWriting"), yearOfWriting);
    }

    public Specification<Book> ratingEquals(Double minRating, Double maxRating){
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("rating"),
                minRating == null ? 0.0 : Utilities.checkRatingLimits(minRating),
                maxRating == null ? 10.0 : Utilities.checkRatingLimits(maxRating));
    }

    public Specification<Book> genreEquals(Long genreId){
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("genre_id").in(bookService.findByGenre(genreId)));
    }

    public Specification<Book> categoryEquals(Long categoryId){
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("cat_id").in(bookService.findByCategory(categoryId)));
    }
}
