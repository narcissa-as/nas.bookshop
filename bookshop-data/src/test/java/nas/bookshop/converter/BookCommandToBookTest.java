package nas.bookshop.converter;

import nas.bookshop.command.AuthorCommand;
import nas.bookshop.command.BookCommand;
import nas.bookshop.command.CategoryCommand;
import nas.bookshop.command.FormatCommand;
import nas.bookshop.domain.Book;
import nas.bookshop.domain.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookCommandToBookTest {

    static final Long ID_VALUE=1L;
    static final String BOOK_NAME = "book name";
    final static String DESCRIPTION = "description";

    final static Long AUTHOR_ID = 1L;
    final static String AUTHOR_DESCRIPTION = "author description";
    final static Long CAT_ID = 1L;
    final static Long FORMAT_ID = 1L;
    final static Long PUBLISHER_ID = 1L;


    @Mock
    AuthorCommandToAuthor authorConverter;
    @Mock
    FormatCommandToFormat formatConverter;
    @Mock
    CategoryCommandToCategory categoryConverter;

    @InjectMocks
    BookCommandToBook converter;

    @BeforeEach
    void setUp() {
        converter = new BookCommandToBook(authorConverter, categoryConverter, formatConverter);
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new BookCommand()));
    }

    @Test
    void convert() throws Exception {
        //given
        BookCommand command = new BookCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        AuthorCommand authorCommand=new AuthorCommand();
        authorCommand.setId(AUTHOR_ID);
        authorCommand.setDescription(AUTHOR_DESCRIPTION);

        CategoryCommand categoryCommand=new CategoryCommand();
        categoryCommand.setId(CAT_ID);

        FormatCommand formatCommand=new FormatCommand();
        formatCommand.setId(FORMAT_ID);

        Publisher publisher=new Publisher();
        publisher.setId(PUBLISHER_ID);

        command.getAuthors().add(authorCommand);
        command.getFormats().add(formatCommand);
        command.getCategories().add(categoryCommand);
        command.setPublisherId(PUBLISHER_ID);

        //when
        Book book=converter.convert(command);

        //then
        assertNotNull(book);
        assertEquals(book.getId(),ID_VALUE);
        assertEquals(book.getDescription(),DESCRIPTION);
        assertEquals(book.getPublisher().getId(),PUBLISHER_ID);

        assertEquals(book.getAuthors().size(),1);
        assertEquals(book.getFormats().size(),1);


    }
}