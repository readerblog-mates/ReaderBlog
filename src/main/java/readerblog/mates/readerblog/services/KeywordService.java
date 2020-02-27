package readerblog.mates.readerblog.services;

import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Keyword;

import java.util.List;

/**
 * @author viiri@mail.ru
 */

@Service
public interface KeywordService {
	List<Keyword> findAll();
}
