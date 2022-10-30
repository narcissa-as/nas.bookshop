package nas.bookshop.controllers;

import lombok.extern.slf4j.Slf4j;
import nas.bookshop.command.BookCommand;
import nas.bookshop.domain.Book;
import nas.bookshop.domain.Format;
import nas.bookshop.domain.Publisher;
import nas.bookshop.service.AuthorService;
import nas.bookshop.service.BookService;
import nas.bookshop.service.FormatService;
import nas.bookshop.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class BookController {
    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final FormatService formatService;

    private final String BOOK_BOOKFORM_URL = "book/bookform";

    public BookController(BookService bookService, PublisherService publisherService, AuthorService authorService, FormatService formatService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.formatService = formatService;
    }

    //populating drop-down list of Publishers in all view methods,before
    // I wrote it before for every method that its view need this values for its drop-down lists.
    //but now I write it one time and can use it in every method of this Controller
    @ModelAttribute("publishers")
    public Collection<Publisher> populatePublishersList() {
        return publisherService.findAll();
    }

    @ModelAttribute("formats")
    public Collection<Format> populateCategoryList() {
        return formatService.findAll();
    }

    //replace and deleting all "Book book = bookService.findById(Long.valueOf(id))"
    //from all Controller methods with this method
/*    @ModelAttribute("book")
    public Book findBookById(@PathVariable String id) {
        return (bookService.findById(Long.valueOf(id)));
    }*/

    @GetMapping("/book/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        //    Book newBook = bookService.findById(Long.valueOf(id));
        model.addAttribute("book", bookService.findById(Long.valueOf(id)));
        return "book/show";
    }

    @GetMapping("book/new")
    String newBook(Model model) {
        model.addAttribute("book", new BookCommand());
        return BOOK_BOOKFORM_URL;
    }
    //2 methods below are the same with different URLs,and no need to be redundant, so I add these URLs in saveOrUpdate-
    //method
  /*  @PostMapping("book/new")
   public String saveOrUpdate(@ModelAttribute("book") BookCommand command) {

        BookCommand savedBookCommand = bookService.saveBookCommand(command);
        return "redirect:/book/" + savedBookCommand.getId() + "/show";
    }

    @PostMapping("publisher/{id}/book/new")
    public String saveBookFromPublisherShowPage(@ModelAttribute() BookCommand command, @PathVariable String id) {
        bookService.saveBookCommand(command);
        Long publisherId = Long.valueOf(id);
        return "redirect:/publisher/" + publisherId + "/show";

    }*/

    @RequestMapping(value = {"book/{id}/update", "book/new", "publisher/{id}/book/new"}, method = RequestMethod.POST)
    public String saveOrUpdate(@Valid @ModelAttribute("book") BookCommand command, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return BOOK_BOOKFORM_URL;
        }

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

    // opening New Book page from a button on publisher/show.Html path
    @GetMapping("publisher/{id}/book/new")
    public String newBookForPublisher(@PathVariable String id, Model model) {

        model.addAttribute("publisher", Long.valueOf(id));
        model.addAttribute("book", new BookCommand());

        return BOOK_BOOKFORM_URL;

    }

    @GetMapping("book/find")
    public String showfindBooks(Model model) {
        BookCommand bookCommand = new BookCommand();
        model.addAttribute("book", bookCommand);
        return "book/findBooks";
    }

    @PostMapping("book/find")
    // @GetMapping()
    public String findBooks(@Valid Book book, BindingResult result, Model model) {
        // empty string signifies broadest possible search
        if (book.getName() == null) {
            book.setName("");
        }

        List<Book> results = bookService.findAllByNameLike("%" + book.getName() + "%");

        if (results.isEmpty()) {
            result.rejectValue("name", "notFound", "not found");
            return "book/findBooks";
        } else {
            model.addAttribute("books", results);
        }
        return "book/showfindbooks";
    }

    //gives us a javascript/xml output of our objects
    @GetMapping("api/books")
    @ResponseBody
    Set<Book> any() {
        return bookService.findAll();
    }

}
