package readerblog.mates.readerblog.services;

import readerblog.mates.readerblog.entities.Genre;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

public interface GenreService {
    List<Genre> findAll();
}
