package nas.bookshop.service.springdatajpa;

import nas.bookshop.domain.Author;
import nas.bookshop.repository.AuthorRepository;
import nas.bookshop.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class AuthorSDJpaService implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorSDJpaService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Set<Author> findAll() {
        Set<Author> authors = new HashSet<>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    @Override
    public Author findById(Long aLong) {
        Optional<Author> authorOptional = authorRepository.findById(aLong);
        return authorOptional.orElse(null);
    }

    @Override
    public Author save(Author object) {
        return authorRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        authorRepository.deleteById(aLong);
    }

    @Override
    public void delete(Author object) {
        authorRepository.delete(object);
    }
}
