package readerblog.mates.readerblog.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Keyword;
import readerblog.mates.readerblog.repositories.KeywordRepository;
import readerblog.mates.readerblog.services.KeywordService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KeywordServiceImpl implements KeywordService {
	private final KeywordRepository keywordRepository;

	@Override
	@Transactional
	public List<Keyword> findAll() {
		return keywordRepository.findAll();
	}
}
