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

    @Override
    @Transactional
    public Genre remove(Long id) {
        if (id != null && genreRepository.findById(id).isPresent())
            return genreRepository.removeById(id);
        return null;
    }

    @Override
    @Transactional
    public Genre remove(String name) {
        if (name != null && genreRepository.findByName(name) != null)
            return genreRepository.removeByName(name);
        return null;
    }

    @Override
    @Transactional
    public Genre findOne(String name) {
        if (name != null)
            return genreRepository.findByName(name);
        return null;
    }

    @Override
    @Transactional
    public Genre findOne(Long id) {
        if (id != null)
            return genreRepository.findById(id).get();
        return null;
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        if (genre != null)
            return genreRepository.save(genre);
        return null;
    }

    @Override
    @Transactional
    public Boolean changeName(String oldName, String newName) {
        if (oldName != null && newName != null)
            return genreRepository.update(oldName, newName) > 0;
        return false;
    }
}
