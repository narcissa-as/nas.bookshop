package nas.bookshop.repository;

import nas.bookshop.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    @Override
    Optional<Category> findById(Long aLong);
    Optional<Category> findByDescription(String aString);
}
