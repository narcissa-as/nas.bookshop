package nas.bookshop.converter;

import nas.bookshop.command.CategoryCommand;
import nas.bookshop.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    static final Long CAT_ID = 1L;
    static final String CAT_NAME = "catName";

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void NullParameterTest() {
        assertNull(converter.convert(null));
    }

    @Test
    void EmptyObjectTest() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() throws Exception{
        //given
        Category category = new Category();
        category.setId(CAT_ID);
        category.setDescription(CAT_NAME);

        //when
        CategoryCommand command =converter.convert(category);

        //then
        assertEquals(command.getId(),CAT_ID);
        assertEquals(command.getDescription(),CAT_NAME);

    }
}