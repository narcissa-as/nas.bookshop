package nas.bookshop.controllers;

import nas.bookshop.command.BookCommand;
import nas.bookshop.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        //  Book newBook=bookService.findById(Long.valueOf(id));
        model.addAttribute("book", bookService.findById(Long.valueOf(id)));
        return "book/show";
    }

    @GetMapping("book/new")
    String newBook(Model model) {
        model.addAttribute("book", new BookCommand());
        return "book/bookform";
    }

    @PostMapping("book")
    String saveOrUpdate(@ModelAttribute("book") BookCommand command) {
        BookCommand savedBookCommand = bookService.saveBookCommand(command);

        return "redirect:/book/" + savedBookCommand.getId() + "/show";
    }
}
