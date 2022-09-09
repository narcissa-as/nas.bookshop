package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.AuthorCommand;
import nas.bookshop.domain.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AuthorCommandToAuthor implements Converter<AuthorCommand, Author> {

    // private final BookCommandToBook bookConverter;

  /*  public AuthorCommandToAuthor(BookCommandToBook bookConverter) {
        this.bookConverter = bookConverter;
    }*/

    @Synchronized
    @Nullable
    @Override
    public Author convert(AuthorCommand source) {
        if (source == null) {
            return null;
        }

        final Author author = new Author();
        author.setId(source.getId());

        author.setFirstName(source.getFirstName());
        author.setLastName(source.getLastName());
        author.setDescription(source.getDescription());
        /* Commented because of circle dependency
        if (source.getBooks() != null && source.getBooks().size() > 0) {

            source.getBooks().forEach(bookCommand -> author.getBooks().add(bookConverter.convert(bookCommand)));

        }*/
        return author;
    }
}
