package readerblog.mates.readerblog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import readerblog.mates.readerblog.entities.Quote;
import readerblog.mates.readerblog.enums.StatusOfQueryOrFeedback;
import readerblog.mates.readerblog.repositories.specifications.QuoteSpecifications;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haroldEkb@mail.ru
 */

@Component
@SessionScope
public class QuoteFilter {

    private Specification<Quote> specification;
    private StringBuilder filtersString;
    private QuoteSpecifications quoteSpecifications;

    @Autowired
    public void setQuoteSpecifications(QuoteSpecifications quoteSpecifications) {
        this.quoteSpecifications = quoteSpecifications;
    }

    public Specification<Quote> getSpecification() {
        return specification;
    }

    public StringBuilder getFiltersString() {
        return filtersString;
    }

    public void takeRequest(HttpServletRequest request){
        filtersString = new StringBuilder();
        specification = Specification.where(null);

        if (request.getParameter("userId") != null && !request.getParameter("userId").isEmpty()){
            specification = specification.and(quoteSpecifications
                    .userIdEquals(Long.parseLong(request.getParameter("first_name"))));
            filtersString.append("&userId=").append(request.getParameter("userId"));
        }

        if (request.getParameter("bookId") != null && !request.getParameter("bookId").isEmpty()){
            specification = specification.and(quoteSpecifications
                    .bookIdEquals(Long.parseLong(request.getParameter("bookId"))));
            filtersString.append("&bookId=").append(request.getParameter("bookId"));
        }

        if (request.getParameter("status") != null && !request.getParameter("status").isEmpty()){
            specification = specification.and(quoteSpecifications
                    .statusEquals(Enum.valueOf(StatusOfQueryOrFeedback.class, request.getParameter("status"))));
            filtersString.append("&status=").append(request.getParameter("status"));
        }
    }
}
