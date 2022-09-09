package nas.bookshop.service.springdatajpa;

import nas.bookshop.command.BookCommand;
import nas.bookshop.converter.BookCommandToBook;
import nas.bookshop.converter.BookToBookCommand;
import nas.bookshop.domain.Book;
import nas.bookshop.repository.BookRepository;
import nas.bookshop.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BookSDJpaService implements BookService {

    private final BookRepository bookRepository;
    private final BookCommandToBook bookCommandToBook;
    private final BookToBookCommand bookToBookCommand;

    public BookSDJpaService(BookRepository bookRepository, BookCommandToBook bookCommandToBook, BookToBookCommand bookCommand, BookToBookCommand bookToBookCommand) {
        this.bookRepository = bookRepository;
        this.bookCommandToBook = bookCommandToBook;
        this.bookToBookCommand = bookToBookCommand;
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

    @Transactional
    @Override
    public BookCommand saveBookCommand(BookCommand command) {
        Book detachedBook=bookCommandToBook.convert(command);
        Book savedBook = bookRepository.save(detachedBook);
        return  bookToBookCommand.convert(savedBook);
    }
}
