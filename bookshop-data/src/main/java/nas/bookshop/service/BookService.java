package nas.bookshop.service;

import nas.bookshop.command.BookCommand;
import nas.bookshop.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService extends CRUDService<Book,Long>{
    BookCommand saveBookCommand(BookCommand command);
    BookCommand findCommandById(Long along);
    void deleteById(Long idToDelete);
    List<Book> findAllByNameLike(String name);
}
