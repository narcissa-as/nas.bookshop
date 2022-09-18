package nas.bookshop.controllers;

import nas.bookshop.domain.Author;
import nas.bookshop.service.AuthorService;
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
class AuthorControllerTest {

    @Mock
    AuthorService authorService;
@InjectMocks
    AuthorController controller;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void showAuthor() throws Exception {
        //given
        Author author = new Author();
        author.setId(1L);
        //when
        when(authorService.findById(any())).thenReturn(author);

        mockMvc.perform(get("/author/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("" +
                        "author/show"))
                .andExpect(model().attributeExists("author"));

    }
}