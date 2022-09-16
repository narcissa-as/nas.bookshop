package nas.bookshop.controllers;

import nas.bookshop.command.BookCommand;
import nas.bookshop.service.AuthorService;
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
    private final String BOOK_BOOKFORM_URL = "book/bookform";

    private final AuthorService authorService;
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
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
        return BOOK_BOOKFORM_URL;
    }

    @PostMapping("book")
    String saveOrUpdate(@ModelAttribute("book") BookCommand command) {
        BookCommand savedBookCommand = bookService.saveBookCommand(command);
        return "redirect:/book/" + savedBookCommand.getId() + "/show";
    }

    @GetMapping("book/{id}/update")
    String updateBook(@PathVariable String id, Model model) {

        model.addAttribute("book", bookService.findCommandById(Long.valueOf(id)));

        return BOOK_BOOKFORM_URL;
    }

    @GetMapping("book/{id}/delete")
    public String deleteBook(@PathVariable String id, Model model) throws Exception {
        bookService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
