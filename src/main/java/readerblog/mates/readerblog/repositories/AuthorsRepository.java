/*
  @author haroldEkb@mail.ru
 */

package readerblog.mates.readerblog.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Author;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class AuthorsRepository {

    private IAuthorsRepository iAuthorsRepository;

    @Autowired
    public void setIAuthorsRepository(IAuthorsRepository iAuthorsRepository) {
        this.iAuthorsRepository = iAuthorsRepository;
    }

    public Author getAuthor(Long id){
        return iAuthorsRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void saveAuthor(Author author){
        iAuthorsRepository.save(author);
    }

    public void deleteAuthor(Author author){
        iAuthorsRepository.delete(author);
    }

    public void deleteAuthorById(Long id){
        iAuthorsRepository.deleteById(id);
    }

    public List<Author> findByLastNameFirstLetter(Character firstLetter){
        return iAuthorsRepository.findByLastNameStartingWith(firstLetter);
    }

    public List<Author> findAllOrderByLastName(){
        return iAuthorsRepository.findAllByOrderByLastName();
    }

    public Author getAuthorById(Long id){
        return getAuthor(id);
    }

    public void updateRating(Long id, Double rating){
        //Проверка значения не помешает, хотя может именно здесь это будет излишне?
        if (rating > 5.0 || rating < 0.0) throw new IllegalArgumentException();
        Author author = getAuthor(id);
        author.setRating(rating);
        saveAuthor(author);
    }
}
