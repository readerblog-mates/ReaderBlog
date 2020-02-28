package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import readerblog.mates.readerblog.entities.Quote;
import readerblog.mates.readerblog.enums.StatusOfQueryOrFeedback;

import java.util.List;

/**
 * @author haroldEkb@mail.ru
 */

public interface QuoteRepository extends JpaRepository<Quote, Long>, JpaSpecificationExecutor<Quote> {
    List<Quote> findAllByBookId(Long id);
    List<Quote> findAllByUserId(Long id);

    //На сайтах-конкурентах обычно цитаты сразу отсортированы по лайкам
    List<Quote> findAllByOrderByRatingDesc();
    List<Quote> findAllByUserIdOrderByRatingDesc(Long id);
    List<Quote> findAllByBookIdOrderByRatingDesc(Long id);

    List<Quote> findAllByStatus(StatusOfQueryOrFeedback status);
    List<Quote> findAllByBookIdAndStatus(Long id, StatusOfQueryOrFeedback status);
    List<Quote> findAllByUserIdAndStatus(Long id, StatusOfQueryOrFeedback status);

//    Цитат может быть много, поэтому добавил пагинацию и спецификацию.
//    Теперь все эти методы findAllBy... не нужны по сути, потому что уточнить запрос можно через через спецификацию,
//    отсортировать в переданном Pageable, да и с Page<Quote> работать лучше, чем с List<Quote> неизвестно какого размера.
//    Или все-таки их стоит оставить?
}
