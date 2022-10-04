package nas.bookshop.service.springdatajpa;

import nas.bookshop.domain.Publisher;
import nas.bookshop.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherSDJpaServiceTest {


    @Mock
    PublisherRepository publisherRepository;
    @InjectMocks
    PublisherSDJpaService publisherSDJpaService;

    Publisher publisher;
    Long id = 1L;


    @BeforeEach
    void setUp() {
        publisher = new Publisher();
        publisher.setId(id);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() throws Exception {
        //given
        //when
        when(publisherRepository.findById(anyLong())).thenReturn(Optional.of(publisher));
        //then
        Publisher publisherReturned = publisherSDJpaService.findById(id);

        verify(publisherRepository, times(1)).findById(anyLong());

        assertNotNull(publisherReturned);
        assertEquals(publisherReturned.getId(), 1L);
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }
}