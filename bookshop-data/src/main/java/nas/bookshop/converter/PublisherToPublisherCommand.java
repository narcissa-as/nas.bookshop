package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.PublisherCommand;
import nas.bookshop.domain.Publisher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PublisherToPublisherCommand implements Converter<Publisher,PublisherCommand> {
    private final BookToBookCommand bookConverter;

    public PublisherToPublisherCommand(BookToBookCommand bookConverter) {
        this.bookConverter = bookConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public PublisherCommand convert(Publisher source) {
        if(source==null) {
            return null;
        }
        final PublisherCommand command=new PublisherCommand();
        command.setId(source.getId());
        command.setName(source.getName());
        command.setAddress(source.getAddress());

        if (source.getBooks()!=null && source.getBooks().size()>0){
            source.getBooks().forEach(bookCommand -> command.getBooks().add(bookConverter.convert(bookCommand)));
        }
        return command;
    }
}
