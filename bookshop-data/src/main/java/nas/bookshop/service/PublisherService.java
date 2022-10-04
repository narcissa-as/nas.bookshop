package nas.bookshop.service;

import nas.bookshop.domain.Publisher;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PublisherService extends CRUDService<Publisher, Long> {

    Set<Publisher> findAll();

    Publisher findById(Long id);

    Publisher save(Publisher object);

    void deleteById(Long aLong);

    void delete(Publisher object);
}
