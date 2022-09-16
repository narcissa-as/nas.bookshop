package nas.bookshop.service;

import nas.bookshop.domain.Author;

import java.util.Set;

public interface AuthorService extends CRUDService<Author,Long>{

    void deleteAuthorsOfABook(Long bookId,Set<Author> authorSet);
}
