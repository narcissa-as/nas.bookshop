package nas.bookshop.repository;

import nas.bookshop.domain.Format;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FormatRepository extends CrudRepository<Format,Long> {

    Optional<Format> findByName(String description);

}
