package readerblog.mates.readerblog.repositories.specifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import readerblog.mates.readerblog.entities.Quote;
import readerblog.mates.readerblog.enums.StatusOfQueryOrFeedback;
import readerblog.mates.readerblog.services.QuoteService;

/**
 * @author haroldEkb@mail.ru
 */

@Component
@SessionScope
public class QuoteSpecifications {

    private QuoteService quoteService;

    @Autowired
    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public Specification<Quote> userIdEquals(Long userId){
        return (Specification<Quote>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userId"), userId);
    }

    public Specification<Quote> bookIdEquals(Long bookId){
        return (Specification<Quote>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("bookId"), bookId);
    }

    public Specification<Quote> statusEquals(StatusOfQueryOrFeedback status){
        return (Specification<Quote>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }
}
