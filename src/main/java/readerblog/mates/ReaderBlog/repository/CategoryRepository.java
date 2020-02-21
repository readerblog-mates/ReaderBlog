package readerblog.mates.ReaderBlog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import readerblog.mates.ReaderBlog.entities.Category;

@Repository
@EnableTransactionManagement
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category findByName(String name);

    void removeById(Integer id);

    void removeByName(String name);

    @Query(value = "UPDATE categories set name = :newName where name = :oldName", nativeQuery = true)
    void update(String oldName, String newName);
}
