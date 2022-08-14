package nas.bookshop.service.springdatajpa;

import nas.bookshop.domain.Book;
import nas.bookshop.repository.BookRepository;
import nas.bookshop.service.BookService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BookSDJpaService implements BookService {
    private final BookRepository bookRepository;

    public BookSDJpaService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Set<Book> findAll() {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book findById(Long aLong) {
        Optional<Book> optionalBook = bookRepository.findById(aLong);
        return optionalBook.orElse(null);
    }

    @Override
    public Book save(Book object) {
        return bookRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        bookRepository.deleteById(aLong);
    }

    @Override
    public void delete(Book object) {
        bookRepository.delete(object);
    }
}
