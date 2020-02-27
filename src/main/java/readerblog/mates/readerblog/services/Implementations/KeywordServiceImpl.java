package readerblog.mates.readerblog.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Keyword;
import readerblog.mates.readerblog.repositories.KeywordRepository;
import readerblog.mates.readerblog.services.KeywordService;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {
	private KeywordRepository keywordRepository;

	@Autowired
	public void setKeywordRepository(KeywordRepository keywordRepository) {
		this.keywordRepository = keywordRepository;
	}

	@Override
	public List<Keyword> findAll() {
		return keywordRepository.findAll();
	}
}
