package nas.bookshop.controllers;

import nas.bookshop.domain.Category;
import nas.bookshop.domain.Format;
import nas.bookshop.repository.CategoryRepository;
import nas.bookshop.repository.FormatRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private final CategoryRepository categoryRepository;
    private final FormatRepository formatRepository;

    public IndexController(CategoryRepository categoryRepository, FormatRepository formatRepository) {
        this.categoryRepository = categoryRepository;
        this.formatRepository = formatRepository;
    }

    @RequestMapping({"/", "index"})
    public String index() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("General");
        Optional<Format> formatOptional = formatRepository.findById(1l);
        System.out.println("Category is: " + categoryOptional.get().getId() + " and Format is: " + formatOptional.get().getName());
        return "index";

    }

}
