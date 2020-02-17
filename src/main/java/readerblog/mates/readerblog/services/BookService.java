package readerblog.mates.readerblog.services;

import readerblog.mates.readerblog.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> findByGenreId(Long genreId);
    List<Book> findByCategoryId(Long categoryId);
    List<Book> findAllByCategoryIdAndGenreId(Long categoryId, Long genreId);
}
