package readerblog.mates.readerblog.services;

import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Category;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public interface CategoryService {
    List<Category> findAll();
}
