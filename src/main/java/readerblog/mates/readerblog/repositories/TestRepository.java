package readerblog.mates.readerblog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import readerblog.mates.readerblog.entities.TestEntity;

@Repository
public interface TestRepository extends CrudRepository<TestEntity, Long> {
}
