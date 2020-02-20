package readerblog.mates.readerblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
