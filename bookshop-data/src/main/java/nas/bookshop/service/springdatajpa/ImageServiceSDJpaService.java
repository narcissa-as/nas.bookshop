package nas.bookshop.service.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import nas.bookshop.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ImageServiceSDJpaService implements ImageService {
    @Override
    public void SaveImageFile(Long id, MultipartFile file) {
        log.debug("recieved a file");
    }
}
