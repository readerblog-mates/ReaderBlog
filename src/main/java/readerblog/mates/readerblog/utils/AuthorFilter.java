package readerblog.mates.readerblog.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.repositories.specifications.AuthorSpecifications;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mzheldin@yandex.ru
 */

@Component
@SessionScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorFilter {

    private Specification<Author> specification;
    private StringBuilder filtersString;
    private final AuthorSpecifications authorSpecifications;

    public Specification<Author> getSpecification() {
        return specification;
    }

    public StringBuilder getFiltersString() {
        return filtersString;
    }

    public void takeRequest(HttpServletRequest request){
        filtersString = new StringBuilder();
        specification = Specification.where(null);

        if (request.getParameter("genre_id") != null && !request.getParameter("genre_id").isEmpty()){
            specification = specification.and(authorSpecifications.genreEquals(Long.valueOf(request.getParameter("genre_id"))));
            filtersString.append("&genre_id=" + request.getParameter("genre_id"));
        }

        if (request.getParameter("cat_id") != null && !request.getParameter("cat_id").isEmpty()){
            specification = specification.and(authorSpecifications.categoryEquals(Long.valueOf(request.getParameter("cat_id"))));
            filtersString.append("&cat_id=" + request.getParameter("cat_id"));
        }

        if ((request.getParameter("minRating") != null && !request.getParameter("minRating").isEmpty()) ||
                (request.getParameter("maxRating") != null && !request.getParameter("maxRating").isEmpty())){
            specification = specification.and(authorSpecifications.ratingEquals(
                    (request.getParameter("minRating") != null && !request.getParameter("minRating").isEmpty()) ?
                            Double.valueOf(request.getParameter("minRating")) : null,
                    (request.getParameter("maxRating") != null && !request.getParameter("maxRating").isEmpty()) ?
                            Double.valueOf(request.getParameter("maxRating")) : null));
            filtersString.append("&minRating=").append(request.getParameter("minRating"))
                    .append("&maxRating=").append(request.getParameter("maxRating"));
        }



        if (request.getParameter("first_name") != null && !request.getParameter("first_name").isEmpty()){
            specification = specification.and(authorSpecifications.firstNameEquals(request.getParameter("first_name")));
            filtersString.append("&first_name=" + request.getParameter("first_name"));
        }

        if (request.getParameter("last_name") != null && !request.getParameter("last_name").isEmpty()){
            specification = specification.and(authorSpecifications.lastNameEquals(request.getParameter("last_name")));
            filtersString.append("&last_name=" + request.getParameter("last_name"));
        }

        if (request.getParameter("patronymic_name") != null && !request.getParameter("patronymic_name").isEmpty()){
            specification = specification.and(authorSpecifications.patronymicNameEquals(request.getParameter("patronymic_name")));
            filtersString.append("&patronymic_name=" + request.getParameter("patronymic_name"));
        }
    }
}
