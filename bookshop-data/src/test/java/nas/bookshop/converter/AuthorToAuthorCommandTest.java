package nas.bookshop.converter;

import nas.bookshop.command.AuthorCommand;
import nas.bookshop.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorToAuthorCommandTest {
    static final Long ID_VALUE=1L;
    static final String DESCRIPTION="description";

    AuthorToAuthorCommand converter;

    @BeforeEach
    void setUp() {
        converter = new AuthorToAuthorCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Author()));
    }

    @Test
    void convert() throws Exception{
        //given
        final Author author=new Author();
        author.setId(ID_VALUE);
        author.setDescription(DESCRIPTION);

        //when
        AuthorCommand command =converter.convert(author);
        //then
        assertNotNull(command);
        assertEquals(command.getId(),ID_VALUE);
        assertEquals(command.getDescription(),DESCRIPTION);


    }
}