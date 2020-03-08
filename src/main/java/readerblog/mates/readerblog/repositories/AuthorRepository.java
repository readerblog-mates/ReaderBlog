package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Author;

import java.util.List;

/**
 * @author mzheldin@yandex.ru
 */

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    List<Author> findAllByRatingBetween(Double ratingMin, Double ratingMax);
    List<Author> findByFirstNameAndLastNameAndPatronymicName(String firstName, String lastName, String patronymicName);
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
    List<Author> findByFirstNameAndPatronymicName(String firstName, String patronymicName);
    List<Author> findByLastNameAndPatronymicName(String lastName, String patronymicName);
    List<Author> findByFirstName(String firstName);
    List<Author> findByLastName(String lastName);
    List<Author> findByPatronymicName(String patronymicName);

    // Изменил возвращаемый тип с Author на List<Author>, т.к. иначе вылезала ошибка
    // java.lang.Integer cannot be cast to readerblog.mates.readerblog.entities.Author
    // Видимо метод возвращает либо число(кол-во удаленных объектов), либо сами объекты в виде списка,
    // даже если удален лишь один объект
    List<Author> removeById(Long id);

    List<Author> findAllByLastNameStartingWith(String firstLetter);
    List<Author> findAllByFirstNameStartingWith(String firstLetter);
    List<Author> findAllByOrderByLastName();
    List<Author> findAllByOrderByFirstName();
}
