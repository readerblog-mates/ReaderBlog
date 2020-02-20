package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
