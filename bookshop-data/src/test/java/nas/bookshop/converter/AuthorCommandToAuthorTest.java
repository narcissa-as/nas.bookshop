package nas.bookshop.converter;

import nas.bookshop.command.AuthorCommand;
import nas.bookshop.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorCommandToAuthorTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID_VALUE = 1L;

    AuthorCommandToAuthor converter;

    @BeforeEach
    void setUp() {
        converter = new AuthorCommandToAuthor();

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new AuthorCommand()));
    }

    @Test
    public void convert() throws Exception {

        //given
        final AuthorCommand command = new AuthorCommand();
        command.setId(ID_VALUE.longValue());
        command.setDescription(DESCRIPTION);

        //when
        Author author = converter.convert(command);

        //then
        assertNotNull(author);
        assertEquals(author.getId(), ID_VALUE);
        assertEquals(author.getDescription(), DESCRIPTION);

    }
}