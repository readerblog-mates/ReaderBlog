package readerblog.mates.ReaderBlog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.ReaderBlog.entities.Category;
import readerblog.mates.ReaderBlog.entities.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

    Genre findByName(String name);

    void removeById(Integer id);

    void removeByName(String name);

    @Query(value = "UPDATE genres set name = :newName where name = :oldName", nativeQuery = true)
    void update(String oldName, String newName);
}
