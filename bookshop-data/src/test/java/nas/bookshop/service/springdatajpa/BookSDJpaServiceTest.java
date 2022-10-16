package nas.bookshop.service.springdatajpa;

import nas.bookshop.domain.Book;
import nas.bookshop.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookSDJpaServiceTest {
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookSDJpaService bookService;

    Book returnBook;

    Long aLong = 1L;

    @BeforeEach
    void setUp() {
        returnBook = Book.builder().name("book1").build();
    }



    @Test
    void findAll() {
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(Book.builder().name("book1").build());
        bookSet.add(Book.builder().name("book2").build());

        when(bookRepository.findAll()).thenReturn(bookSet);

        Set<Book> serviceSet = bookService.findAll();

        assertNotNull(serviceSet);
        assertEquals(2, serviceSet.size());

    }

    @Test
    void findById() {
        Book book1 = Book.builder().name("abook").build();
        Optional<Book> bookOptional = Optional.of(book1);

        when(bookRepository.findById(aLong)).thenReturn(bookOptional);
        Optional<Book> optionalBook = Optional.of(bookService.findById(aLong));

        assertNotNull(optionalBook);
        assertEquals(optionalBook.orElse(null).getName(), book1.getName());
    }

    @Test
    void save(Book object) {

        //  when(bookRepository.save(any())).thenReturn(returnBook);
    }

    @Test
    void findAllByNameLike() {

        List<Book>results=new ArrayList<>();
        results.add(Book.builder().name("book").build());
        results.add(Book.builder().name("book").build());

        //when
        when(bookRepository.findAllByNameLike(any())).thenReturn(results);

       List<Book>booksResult= bookService.findAllByNameLike("name");
        verify(bookRepository,times(1)).findAllByNameLike(any());
        assertEquals(booksResult.size(),2);

    }
}