package readerblog.mates.readerblog.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Genre;
import readerblog.mates.readerblog.repositories.GenreRepository;
import readerblog.mates.readerblog.services.GenreService;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
