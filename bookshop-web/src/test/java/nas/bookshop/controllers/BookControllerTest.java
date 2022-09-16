package nas.bookshop.controllers;

import nas.bookshop.command.BookCommand;
import nas.bookshop.domain.Book;
import nas.bookshop.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    void newBook() throws Exception {
        mockMvc.perform(get("/book/new"))
                .andExpect(status().isOk()).andExpect(model().attributeExists("book")).
                andExpect(view().name("book/bookform"));

    }

    @Test
    void saveOrUpdate() throws Exception {
        //given
        BookCommand command = new BookCommand();
        command.setId(1L);

        //when
        when(bookService.saveBookCommand(any())).thenReturn(command);

        mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("name", "bookName")
                        .param("page", "100")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/1/show"));
    }

    @Test
    void update() throws Exception {

        //given
        BookCommand command = new BookCommand();
        command.setId(1L);

        //when
        when(bookService.findCommandById(any())).thenReturn(command);
        //then
        mockMvc.perform(get("/book/1/update")).andExpect(status().isOk())
                .andExpect(view().name("book/bookform"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void deleteBook() throws Exception {

        mockMvc.perform(get("/book/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(bookService, times(1)).deleteById(1L);
    }
}