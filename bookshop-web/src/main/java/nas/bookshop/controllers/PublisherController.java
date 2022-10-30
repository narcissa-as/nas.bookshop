package nas.bookshop.controllers;

import nas.bookshop.command.BookCommand;
import nas.bookshop.domain.Publisher;
import nas.bookshop.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publisher/{id}/show")
    public String showPublisher(@PathVariable String id, Model model) {
        Publisher publisher = publisherService.findById(Long.valueOf(id));
        model.addAttribute("publisher", publisherService.findById(Long.valueOf(id)));
        return "publisher/show";
    }

    @GetMapping("publisher/book/new")
    public String newBook(Model model) {
        model.addAttribute("book", new BookCommand());
        model.addAttribute("publishers", publisherService.findAll());
        return "book/bookform";
    }

    @GetMapping("publishers/show")
    String showPublishers(Model model) {
        Set<Publisher> publishers = publisherService.findAll();
        model.addAttribute("publishers", publishers);
        return "publisher/publisherlist";
    }

//should write later
    /*@GetMapping("publisher/{id}/book/new")
    public String newBookForPublisher(@PathVariable String id,Model model){

        Long publisherId=Long.valueOf(id);
        return "redirect:/publisher/" + publisherId +"/show";
    }*/

}
