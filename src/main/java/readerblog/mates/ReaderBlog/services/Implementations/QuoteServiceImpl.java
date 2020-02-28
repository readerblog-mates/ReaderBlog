package readerblog.mates.readerblog.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Quote;
import readerblog.mates.readerblog.repositories.QuoteRepository;
import readerblog.mates.readerblog.services.QuoteService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author haroldEkb@mail.ru
 */

public class QuoteServiceImpl implements QuoteService {

    private QuoteRepository quoteRepository;

    @Autowired
    public void setQuoteRepository(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    @Transactional
    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    @Transactional
    public Quote findOneById(Long id) {
        return quoteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        quoteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Quote> findAllByPagingAndFiltering(Specification<Quote> specification, Pageable pageable) {
        return quoteRepository.findAll(specification, pageable);
    }
}
