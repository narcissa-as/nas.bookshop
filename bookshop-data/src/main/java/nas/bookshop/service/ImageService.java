package nas.bookshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService{
    void SaveImageFile (Long id, MultipartFile file);
}