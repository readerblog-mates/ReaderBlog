package readerblog.mates.readerblog.services;

import readerblog.mates.readerblog.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}
