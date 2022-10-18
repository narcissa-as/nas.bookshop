package nas.bookshop.controllers;

import nas.bookshop.command.BookCommand;
import nas.bookshop.domain.Book;
import nas.bookshop.service.BookService;
import nas.bookshop.service.FormatService;
import nas.bookshop.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Mock
    BookService bookService;
    @Mock
    PublisherService publisherService;
    @Mock
    FormatService formatService;

    @InjectMocks
    BookController bookController;
    MockMvc mockMvc;

    Book testBook1 = new Book();
    Book testBook2 = new Book();

    //Values of @modelAttribute that should be populated
  /*  Set<Publisher>publishers;
    Set<Format>formats;
*/
    @BeforeEach
    void setUp() {
        testBook1.setName("newBook");
        testBook1.setName("newBook2");

        //populating drop-downs that are parameters in HTML files that should be defined
  /*      publishers=new HashSet<>();

        publishers.add(Publisher.builder().name("publisher1").build());
        publishers.add(Publisher.builder().name("publisher2").build());

        formats=new HashSet<>();
        formats.add(Format.builder().name("format1").build());
        formats.add(Format.builder().name("format2").build());*/

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void showById() throws Exception {
        //these 2 lines are for @ModelAttribute parameters definition that view HTML need them
      /*  when(publisherService.findAll()).thenReturn(publishers);
        when(formatService.findAll()).thenReturn(formats);*/

        when(bookService.findById(any())).thenReturn(testBook1);

        mockMvc.perform(get("/book/1/show")).
                andExpect(status().isOk()).andExpect(view().name("book/show"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void newBook() throws Exception {
        //these 2 lines are for @ModelAttribute parameters definition that view HTML need them
      /*  when(publisherService.findAll()).thenReturn(publishers);
        when(formatService.findAll()).thenReturn(formats);*/

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

    @Test
    void showFindBooks() throws Exception{
        mockMvc.perform(get("/book/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/findBooks"))
                .andExpect(model().attributeExists("book"));
            }

    @Test
    void findBooksReturnMany() throws Exception{
        //given
        List<Book> results=new ArrayList<>();
        results.add(testBook1);
        results.add(testBook2);
        //when
        when(bookService.findAllByNameLike(any())).thenReturn(results);
        //then
        mockMvc.perform(post("/book/find"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(view().name("book/showfindbooks"));

    }
    @Test
    void findBooksReturnNoResults() throws Exception {
        //when
        when(bookService.findAllByNameLike(any())).thenReturn(Arrays.asList());
        mockMvc.perform(post("/book/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/findBooks"));

    }

}