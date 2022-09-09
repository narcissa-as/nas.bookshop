package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.PublisherCommand;
import nas.bookshop.domain.Publisher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PublisherCommandToPublisher implements Converter<PublisherCommand, Publisher> {
    private final BookCommandToBook bookConverter;

    public PublisherCommandToPublisher(BookCommandToBook bookConverter) {
        this.bookConverter = bookConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Publisher convert(PublisherCommand source) {

        if(source==null) {
            return null;
        }
        final Publisher publisher=new Publisher();
        publisher.setId(source.getId());
        publisher.setName(source.getName());
        publisher.setAddress(source.getAddress());

        if (source.getBooks()!=null && source.getBooks().size()>0){
            source.getBooks().forEach(bookCommand -> publisher.getBooks().add(bookConverter.convert(bookCommand)));
        }
        return publisher;
    }
}
