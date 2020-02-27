package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByGenreId(Long genreId);
    //List<Book> findAllByCategoryId(Long categoryId);
    //List<Book> findAllByCategoryIdAndGenreId(Long categoryId, Long genreId);
}
