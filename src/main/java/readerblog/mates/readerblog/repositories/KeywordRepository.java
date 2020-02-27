package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Keyword;

/**
 * @author viiri@mail.ru
 */

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
	Keyword findByKeyword(String keyword);

	void removeById(Integer id);

	void removeByKeyword(String keyword);
}
