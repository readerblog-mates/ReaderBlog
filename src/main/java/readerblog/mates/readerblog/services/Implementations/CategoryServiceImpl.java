package readerblog.mates.readerblog.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Book;
import readerblog.mates.readerblog.entities.Category;
import readerblog.mates.readerblog.repositories.CategoryRepository;
import readerblog.mates.readerblog.services.BookService;
import readerblog.mates.readerblog.services.CategoryService;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private BookService bookService;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category findOne(Long id) {
        if (id != null)
            return categoryRepository.findById(id).get();
        return null;
    }

    @Override
    @Transactional
    public Category findOne(String name) {
        if (name != null)
            return categoryRepository.findByName(name);
        return null;
    }

    @Override
    @Transactional
    public void changeName(String oldName, String newName) {
        if (oldName != null && newName != null)
            categoryRepository.update(oldName, newName);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        if (id != null && categoryRepository.findById(id).isPresent())
            categoryRepository.removeById(id);
    }

    @Override
    @Transactional
    public void remove(String name) {
        if (name != null && categoryRepository.findByName(name) != null)
            categoryRepository.removeByName(name);
    }

    @Override
    @Transactional
    public Category save(Category category) {
        if (category != null)
            categoryRepository.save(category);
        return null;
    }

    @Override
    @Transactional
    public List<Category> findByBooks(List<Long> bookIds) {
        if (bookIds != null && bookIds.size() > 0){
            List<Book> books = bookService.findAllById(bookIds);
            if (books != null && books.size() > 0)
                categoryRepository.findAllByBooksIn(books);
        }
        return null;
    }

    @Override
    @Transactional
    public List<Category> findAllById(List<Long> ids) {
        if (ids != null && ids.size() > 0)
            return categoryRepository.findAllByIdIn(ids);
        return null;
    }
}
