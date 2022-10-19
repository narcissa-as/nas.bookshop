package nas.bookshop.service.springdatajpa;

import nas.bookshop.domain.Book;
import nas.bookshop.exceptions.NotFoundException;
import nas.bookshop.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
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
    //JUnit4 version code is like below but I commented it because we use Junit5
    //and should use like
    //  @Test(expected= NotFoundException.class)
    @Test
    void getFindByIdNotFound(){
        //given
        Optional<Book>bookOptional=Optional.empty();
        //when
        when(bookRepository.findById(anyLong())).thenReturn(bookOptional);
        //we move this part to Assertion line due to using Assertions
       // Book book=bookService.findById(1L);
       // assertThrows(PersistenceException.class, () -> invalidUserRepository.save(user));
        //Assertions.assertThrows(NotFoundException.class, () -> { throw new NotFoundException();});
        //In Jupiter expected is replaced because new way Improves the test's readability. we declare that what exactly
        // lead to Exception,for example in line below after -> we define that this part exactly lead to NotFoundException.
        Assertions.assertThrows(NotFoundException.class, () -> bookService.findById(1L));
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