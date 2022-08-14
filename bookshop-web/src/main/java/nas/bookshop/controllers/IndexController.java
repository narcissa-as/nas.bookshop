package nas.bookshop.controllers;

import nas.bookshop.repository.BookRepository;
import nas.bookshop.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private final BookService bookService;
    private final BookRepository bookRepository;

    public IndexController( BookService bookService,BookRepository bookRepository) {
       this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @RequestMapping({"/", "index"})
    public String getIndexPage(Model model) {

        model.addAttribute("books", bookRepository.findAll());
        return "index";

    }

}
