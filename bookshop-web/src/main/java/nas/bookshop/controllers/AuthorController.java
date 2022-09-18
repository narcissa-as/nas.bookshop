package nas.bookshop.controllers;

import nas.bookshop.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class AuthorController {
     private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/{id}/show")
    String showAuthor(@PathVariable String id, Model model){
        model.addAttribute("author",authorService.findById(Long.valueOf(id)));
        return "author/show";
    }


}
