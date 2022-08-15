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

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    BookService bookService;

    @InjectMocks
    IndexController indexController;

    MockMvc mockMvc;
    Set<Book> books;

    @BeforeEach
    void setUp() {
        books = new HashSet<>();
        books.add(Book.builder().name("testBook1").build());
        books.add(Book.builder().name("testBook2").build());
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }


    @Test
    void getIndexPage() throws Exception {


        when(bookService.findAll()).thenReturn(books);

        mockMvc.perform(get("/", "index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("books", hasSize(2)));

    }
}