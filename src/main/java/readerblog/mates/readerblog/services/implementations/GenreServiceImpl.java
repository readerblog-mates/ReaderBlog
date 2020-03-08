package readerblog.mates.readerblog.services.implementations;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public Genre remove(Long id) {
        return (id != null && genreRepository.findById(id).isPresent()) ? genreRepository.removeById(id) : null;
    }

    @Override
    @Transactional
    public Genre remove(String name) {
        return (name != null && genreRepository.findByName(name) != null) ? genreRepository.removeByName(name) : null;
    }

    @Override
    @Transactional
    public Genre findOne(String name) {
        return name != null ? genreRepository.findByName(name) : null;
    }

    @Override
    @Transactional
    public Genre findOne(Long id) {
        return (id != null && genreRepository.findById(id).isPresent()) ? genreRepository.findById(id).get() : null;
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return genre != null ? genreRepository.save(genre) : null;
    }

    @Override
    @Transactional
    public Boolean changeName(String oldName, String newName) {
        return (oldName != null && newName != null) && genreRepository.update(oldName, newName) > 0;
    }
}
