package nas.bookshop.controllers;

import nas.bookshop.domain.Book;
import nas.bookshop.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Mock
    BookService bookService;
    @InjectMocks
    BookController bookController;
    MockMvc mockMvc;

    Book testBook = new Book();

    @BeforeEach
    void setUp() {
        testBook.setName("newBook");
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void showById() throws Exception {
        when(bookService.findById(any())).thenReturn(testBook);

        mockMvc.perform(get("/book/1/show")).
                andExpect(status().isOk()).andExpect(view().name("book/show"))
                .andExpect(model().attributeExists("book"));
    }
}