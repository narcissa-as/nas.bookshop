package nas.bookshop.service.springdatajpa;

import nas.bookshop.command.BookCommand;
import nas.bookshop.converter.BookCommandToBook;
import nas.bookshop.converter.BookToBookCommand;
import nas.bookshop.domain.Author;
import nas.bookshop.domain.Book;
import nas.bookshop.repository.AuthorRepository;
import nas.bookshop.repository.BookRepository;
import nas.bookshop.service.AuthorService;
import nas.bookshop.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookSDJpaService implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    //private final CategoryRepository categoryRepository;

    private final BookCommandToBook bookCommandToBook;
    private final BookToBookCommand bookToBookCommand;

    private final AuthorService authorService;

    public BookSDJpaService(BookRepository bookRepository, BookCommandToBook bookCommandToBook, BookToBookCommand bookCommand, AuthorRepository authorRepository, BookToBookCommand bookToBookCommand, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookCommandToBook = bookCommandToBook;
        this.authorRepository = authorRepository;
        this.bookToBookCommand = bookToBookCommand;
        this.authorService = authorService;
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

        Optional<Book> bookOptional = bookRepository.findById(aLong);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            //deleting this Books from all authors, it means look in Authors table and delete this book for all authors
            Set<Author> authorSet = new HashSet<>();
            book.getAuthors().iterator().forEachRemaining(authorSet::add);
            authorService.deleteAuthorsOfABook(aLong, authorSet);
            //deleting all authors of this book
            book.getAuthors().clear();
            //deleting Book
            bookRepository.delete(book);
        }
    }

    @Override
    public void delete(Book object) {
        bookRepository.delete(object);
    }

    @Transactional
    @Override
    public BookCommand saveBookCommand(BookCommand command) {
        Book detachedBook = bookCommandToBook.convert(command);
        Book savedBook = bookRepository.save(detachedBook);
        return bookToBookCommand.convert(savedBook);
    }

    @Transactional
    @Override
    public BookCommand findCommandById(Long along) {

        return bookToBookCommand.convert(findById(along));
    }

    @Override
    public List<Book> findAllByNameLike(String name) {

        return bookRepository.findAllByNameLike(name);

    }
}
