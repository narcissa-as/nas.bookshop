package nas.bookshop.service;

import nas.bookshop.command.BookCommand;
import nas.bookshop.domain.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService extends CRUDService<Book,Long>{
    BookCommand saveBookCommand(BookCommand command);
    BookCommand findCommandById(Long along);
}
