package readerblog.mates.readerblog.services;

import readerblog.mates.readerblog.entities.Category;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

public interface CategoryService {
    List<Category> findAll();
}
