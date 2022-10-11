package nas.bookshop.controllers;

import nas.bookshop.domain.Book;
import nas.bookshop.service.BookService;
import nas.bookshop.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {
    @Mock
    BookService bookService;
    @Mock
    ImageService imageService;
    @InjectMocks
    ImageController imageController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    void showUploadForm() throws Exception {
        //given
        Book book = new Book();
        book.setId(1L);

        //when
        when(bookService.findById(anyLong())).thenReturn(book);

        //then
        mockMvc.perform(get("/book/1/image"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/imageuploadform"))
                .andExpect(model().attributeExists("book"));

    }

    @Test
    void handleImagePost() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "Online Bookshop".getBytes());

        mockMvc.perform(multipart("/book/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/book/1/show"));
        //then
        verify(imageService, times(1)).SaveImageFile(anyLong(), any());
    }
}