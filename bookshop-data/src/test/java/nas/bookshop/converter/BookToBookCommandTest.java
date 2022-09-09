package nas.bookshop.converter;

import nas.bookshop.command.BookCommand;
import nas.bookshop.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookToBookCommandTest {

    final static Long ID_VALUE = 1L;
    final static String DESCRIPTION = "description";

    final static Long AUTHOR_ID = 1L;
    final static String AUTHOR_DESCRIPTION="author description";
    final static Long CAT_ID = 1L;
    final static Long FORMAT_ID = 1L;
    final static Long PUBLISHER_ID = 1L;

    @Mock
    AuthorToAuthorCommand authorConverter;
    @Mock
    CategoryToCategoryCommand categoryConverter;
    @Mock
    FormatToFormatCommand formatConverter;

    @InjectMocks
    BookToBookCommand bookConverter;

    @BeforeEach
    void setUp() {
       // MockitoAnnotations.initMocks(this);
        bookConverter = new BookToBookCommand(authorConverter, categoryConverter, formatConverter);
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(bookConverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(bookConverter.convert(new Book()));
    }

    @Test
    void convert() throws Exception{

        //given
        Book book = new Book();
        book.setId(ID_VALUE);
        book.setDescription(DESCRIPTION);

        Author author = new Author();
        author.setId(ID_VALUE);
        author.setDescription(AUTHOR_DESCRIPTION);

        Publisher publisher = new Publisher();
        publisher.setId(ID_VALUE);

        Format format=new Format();
        format.setId(FORMAT_ID);

        Category category=new Category();
        category.setId(CAT_ID);

        book.setPublisher(publisher);
        book.getAuthors().add(author);
        book.getFormats().add(format);
        book.getCategories().add(category);

        //when
        BookCommand command = bookConverter.convert(book);

        //then
        assertNotNull(command);
        assertEquals(command.getId(), ID_VALUE);
        assertEquals(command.getDescription(), DESCRIPTION);
        assertEquals(command.getAuthors().size(), 1);
        assertEquals(command.getFormats().size(), 1);
        assertEquals(command.getCategories().size(), 1);
        assertEquals(command.getPublisherId(), PUBLISHER_ID);

    }
}
