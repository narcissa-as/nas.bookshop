package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.BookCommand;
import nas.bookshop.domain.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookToBookCommand implements Converter<Book, BookCommand> {

    private final AuthorToAuthorCommand authorConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final FormatToFormatCommand formatConverter;

    public BookToBookCommand(AuthorToAuthorCommand authorConverter, CategoryToCategoryCommand categoryConverter, FormatToFormatCommand formatConverter) {
        this.authorConverter = authorConverter;
        this.categoryConverter = categoryConverter;
        this.formatConverter = formatConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public BookCommand convert(Book source) {
        if (source == null) {
            return null;
        }
        final BookCommand command = new BookCommand();
        command.setId(source.getId());
        command.setName(source.getName());
        command.setPublishDate(source.getPublishDate());
        command.setLanguage(source.getLanguage());
        command.setReview(source.getReview());
        command.setPages(source.getPages());
        command.setEANUPC(source.getEANUPC());
        command.setAvailable(source.getAvailable());
        command.setDescription(source.getDescription());
        command.setPrice(source.getPrice());


        if (source.getAuthors() != null && source.getAuthors().size() > 0) {
                source.getAuthors().forEach(author -> command.getAuthors().add(authorConverter.convert(author)));
            }


        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(category -> command.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getFormats() != null && source.getFormats().size() > 0) {
            source.getFormats().forEach(format -> command.getFormats().add(formatConverter.convert(format)));
        }

        if (source.getPublisher() != null) {
            command.setPublisherId(source.getPublisher().getId());
        }
        //first I set publisher like follow
        /*PublisherCommand publisherCommand = new PublisherCommand();
        publisherCommand.setId(source.getPublisher().getId());
        //I Think it's redundant and ome of follow 2 lines are enough
        command.setPublisherId(publisherCommand.getId());
        publisher.getBooks().add(command);*/
        return command;
    }
}
