package nas.service;

import nas.bookshop.domain.Book;
import nas.bookshop.repository.BookRepository;
import nas.bookshop.service.springdatajpa.BookSDJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookSDJpaService bookService;

    Book returnBook;
    @BeforeEach
    void setUp() {
        returnBook=Book.builder().name("book1").build();
    }

    Long aLong = 1L;

    @Test
    void findAll() {
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(Book.builder().name("book1").build());
        bookSet.add(Book.builder().name("book2").build());

        when(bookRepository.findAll()).thenReturn(bookSet);

        Set<Book> serviceSet = bookService.findAll();

        assertNotNull(serviceSet);
        assertEquals(2,serviceSet.size());

    }

    @Test
    void findById() {
   Book book1=  Book.builder().name("abook").build();
        Optional <Book>bookOptional=Optional.of(book1);

        when(bookRepository.findById(aLong)).thenReturn(bookOptional);
        Optional<Book> optionalBook=Optional.of(bookService.findById(aLong));

        assertNotNull(optionalBook);
        assertEquals(optionalBook.orElse(null).getName(),book1.getName());
    }
    @Test
    void save(Book object){

      //  when(bookRepository.save(any())).thenReturn(returnBook);
    }
}