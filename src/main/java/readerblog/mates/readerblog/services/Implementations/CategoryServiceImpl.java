package readerblog.mates.readerblog.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Category;
import readerblog.mates.readerblog.repositories.CategoryRepository;
import readerblog.mates.readerblog.services.CategoryService;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category findOne(Long id) {
        return (id != null && categoryRepository.findById(id).isPresent()) ?
                categoryRepository.findById(id).get() : null;
    }

    @Override
    @Transactional
    public Category findOne(String name) {
        return name != null ? categoryRepository.findByName(name) : null;
    }

    @Override
    @Transactional
    public Boolean changeName(String oldName, String newName) {
        return (oldName != null && newName != null) && categoryRepository.update(oldName, newName) > 0;
    }

    @Override
    @Transactional
    public Category remove(Long id) {
        return (id != null && categoryRepository.findById(id).isPresent()) ? categoryRepository.removeById(id) :null;
    }

    @Override
    @Transactional
    public Category remove(String name) {
        return (name != null && categoryRepository.findByName(name) != null) ?
                categoryRepository.removeByName(name) : null;
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return category != null ? categoryRepository.save(category) : null;
    }
}
