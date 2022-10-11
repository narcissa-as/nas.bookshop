package nas.bookshop.controllers;

import nas.bookshop.service.BookService;
import nas.bookshop.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    private final BookService bookService;
    private final ImageService imageService;

    public ImageController(BookService bookService, ImageService imageService) {
        this.bookService = bookService;
        this.imageService = imageService;
    }

    @GetMapping("/book/{id}/image")
    String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.findById(Long.valueOf(id)));
        return "book/imageuploadform";
    }

    @PostMapping("/book/{id}/image")
    String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        imageService.SaveImageFile(Long.valueOf(id), file);
        return "redirect:/book/" + id + "/show";
    }
}
