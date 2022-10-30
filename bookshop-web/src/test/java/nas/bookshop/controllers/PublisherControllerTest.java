package nas.bookshop.controllers;

import nas.bookshop.domain.Publisher;
import nas.bookshop.exceptions.NotFoundException;
import nas.bookshop.service.PublisherService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {

    @Mock
    PublisherService publisherService;
    @InjectMocks
    PublisherController controller;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                //every Controller that uses ControllerAdvice, we should set ControllerAdvice for it
                .setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test
    void showPublishers() throws Exception{
        //given
        Set<Publisher>publishers=new HashSet<>();
        Publisher publisherTest1=new Publisher();
        publishers.add(publisherTest1);
        Publisher publisherTest2=new Publisher();
        publishers.add(publisherTest2);
        //when
        when(publisherService.findAll()).thenReturn(publishers);
        //then
        mockMvc.perform(get("/publishers/show"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("publishers"))
                .andExpect(view().name("publisher/publisherlist"));

    }

    @Test
    void handleNotFound() throws Exception {
        when(publisherService.findById(any())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/publisher/10/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    void handleBadRequest() throws Exception{
        mockMvc.perform(get("/publisher/asd/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}