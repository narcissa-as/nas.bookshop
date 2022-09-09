package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.AuthorCommand;
import nas.bookshop.domain.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorCommand implements Converter<Author, AuthorCommand> {
    //Commented because of circle dependency
    //private final BookToBookCommand bookConverter;

   // public AuthorToAuthorCommand(BookToBookCommand bookConverter) {this.bookConverter = bookConverter;}

    @Synchronized
    @Nullable
    @Override
    public AuthorCommand convert(Author source) {
        if (source == null) {
            return null;
        }

        final nas.bookshop.command.AuthorCommand command = new nas.bookshop.command.AuthorCommand();
        command.setId(source.getId());

        command.setFirstName(source.getFirstName());
        command.setLastName(source.getLastName());
        command.setDescription(source.getDescription());
    /* Commented because of circle dependency
            if (source.getBooks() != null && source.getBooks().size() > 0) {
                //corrected mistake in defining in foreach line
               // source.getCategories()
                 //       .forEach(category -> command.getCategories().add(categoryConverter.convert(category)));
            source.getBooks().
                    forEach(book -> command.getBooks().add(bookConverter.convert(book)));
        }*/
            return command;
}
}

