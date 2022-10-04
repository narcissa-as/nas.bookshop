package nas.bookshop.service.springdatajpa;

import nas.bookshop.domain.Publisher;
import nas.bookshop.repository.PublisherRepository;
import nas.bookshop.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class PublisherSDJpaService implements PublisherService {
    private final PublisherRepository publisherRepository;

    PublisherSDJpaService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Set<Publisher> findAll() {
        Set<Publisher> publishers = new HashSet<>();
        publisherRepository.findAll().forEach(publishers::add);
        return publishers;
    }

    public Publisher findById(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (!publisherOptional.isPresent()) {
            return null;
        }
        return publisherOptional.orElse(null);
    }

    @Override
    public Publisher save(Publisher object) {
        return publisherRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        publisherRepository.deleteById(aLong);
    }

    @Override
    public void delete(Publisher object) {
        publisherRepository.delete(object);
    }
}
