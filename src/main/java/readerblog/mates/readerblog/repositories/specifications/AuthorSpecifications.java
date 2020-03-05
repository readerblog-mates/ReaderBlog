package readerblog.mates.readerblog.repositories.specifications;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.services.AuthorService;
import readerblog.mates.readerblog.utils.Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author mzheldin@yandex.ru
 */

@Component
@SessionScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorSpecifications {

    private final AuthorService authorService;

    public Specification<Author> firstNameEquals(String firstName){
        return (Specification<Author>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("firstName"), firstName);
    }

    public Specification<Author> lastNameEquals(String lastName){
        return (Specification<Author>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("lastName"), lastName);
    }

    public Specification<Author> patronymicNameEquals(String patronymicName){
        return (Specification<Author>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("patronymicName"), patronymicName);
    }

    public Specification<Author> ratingEquals(Double minRating, Double maxRating){
        return (Specification<Author>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("rating"),
                minRating == null ? 0.0 : Utilities.checkRatingLimits(minRating),
                maxRating == null ? 10.0 : Utilities.checkRatingLimits(maxRating));
    }

    public Specification<Author> genreEquals(Long genreId){
        return (Specification<Author>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("id").in(authorService.findIdByGenre(genreId)));
    }

    public Specification<Author> categoryEquals(Long categoryId){
        return (Specification<Author>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("id").in(authorService.findIdByCategory(categoryId)));
    }
}
