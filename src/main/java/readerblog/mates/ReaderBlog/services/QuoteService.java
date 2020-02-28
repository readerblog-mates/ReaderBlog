package readerblog.mates.readerblog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import readerblog.mates.readerblog.entities.Quote;

import java.util.List;

/**
 * @author haroldEkb@mail.ru
 */

public interface QuoteService {
    Quote save(Quote quote);
    Quote findOneById(Long id);
    void remove(Long id);
    List<Quote> findAll();
    Page<Quote> findAllByPagingAndFiltering(Specification<Quote> specification, Pageable pageable);
}
