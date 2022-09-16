package nas.bookshop.service.springdatajpa;

import nas.bookshop.domain.Author;
import nas.bookshop.domain.Book;
import nas.bookshop.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AuthorSDJpaServiceTest {
    @Mock
    AuthorRepository authorRepository;
    @InjectMocks
    AuthorSDJpaService authorService;

    Book testBook;
    Long bookId;

    @BeforeEach
    void setUp() {
        bookId = 1L;
        testBook = new Book();
        testBook.setId(bookId);
        testBook.setName("testBook");
    }

    @Test
    void deleteAuthorsOfABook() throws Exception {
        //in the main method we have Authors that have a list of Books, and we remove mentioned Book from all Authors
        //so here we should test removing the Book from list of Authors
        //given
        Set<Author> authorSet = new HashSet<>();

        Author author1 = new Author();
        author1.setId(1L);
        author1.setFirstName("firstName");
        author1.setLastName("lastName");
        //Author author2=new Author();

        author1.getBooks().add(testBook);
        // author2.getBooks().add(testBook);

        authorSet.add(author1);
        //  authorSet.add(author2);

        //when
        when(authorRepository.save(any())).thenReturn(author1);
        authorService.deleteAuthorsOfABook(1L, authorSet);

        //then
//        verify(authorRepository,times(1)).save(any());
        assertEquals(author1.getBooks().size(),0);
    }
}