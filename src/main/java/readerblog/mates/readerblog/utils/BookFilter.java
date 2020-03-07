package readerblog.mates.readerblog.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.repositories.specifications.BookSpecifications;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergey Petukhov
 */


@Component
@SessionScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookFilter {

    private Specification<Book> specification;
    private StringBuilder filtersString;
    private final BookSpecifications bookSpecifications;

    public Specification<Book> getSpecification() {
        return specification;
    }

    public StringBuilder getFiltersString() {
        return filtersString;
    }

    public void takeRequest(HttpServletRequest request){
        filtersString = new StringBuilder();
        specification = Specification.where(null);

        if (request.getParameter("genre_id") != null && !request.getParameter("genre_id").isEmpty()){
            specification = specification.and(bookSpecifications.genreEquals(Long.valueOf(request.getParameter("genre_id"))));
            filtersString.append("&genre_id=" + request.getParameter("genre_id"));
        }

        if (request.getParameter("cat_id") != null && !request.getParameter("cat_id").isEmpty()){
            specification = specification.and(bookSpecifications.categoryEquals(Long.valueOf(request.getParameter("cat_id"))));
            filtersString.append("&cat_id=" + request.getParameter("cat_id"));
        }

        if ((request.getParameter("minRating") != null && !request.getParameter("minRating").isEmpty()) ||
                (request.getParameter("maxRating") != null && !request.getParameter("maxRating").isEmpty())){
            specification = specification.and(bookSpecifications.ratingEquals(
                    (request.getParameter("minRating") != null && !request.getParameter("minRating").isEmpty()) ?
                            Double.valueOf(request.getParameter("minRating")) : null,
                    (request.getParameter("maxRating") != null && !request.getParameter("maxRating").isEmpty()) ?
                            Double.valueOf(request.getParameter("maxRating")) : null));
            filtersString.append("&minRating=").append(request.getParameter("minRating"))
                    .append("&maxRating=").append(request.getParameter("maxRating"));
        }



        if (request.getParameter("author") != null && !request.getParameter("author").isEmpty()){
            specification = specification.and(bookSpecifications.authorEquals(request.getParameter("author")));
            filtersString.append("&author=" + request.getParameter("author"));
        }

        if (request.getParameter("title") != null && !request.getParameter("title").isEmpty()){
            specification = specification.and(bookSpecifications.titleEquals(request.getParameter("title")));
            filtersString.append("&title=" + request.getParameter("title"));
        }

        if (request.getParameter("year_of_writing") != null && !request.getParameter("year_of_writing").isEmpty()){
            specification = specification.and(bookSpecifications.yearOfWritingEquals(request.getParameter("year_of_writing")));
            filtersString.append("&year_of_writing=" + request.getParameter("year_of_writing"));
        }
    }
}
