package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.BookCommand;
import nas.bookshop.domain.Book;
import nas.bookshop.domain.Publisher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {

    private AuthorCommandToAuthor authorConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final FormatCommandToFormat formatConverter;

    public BookCommandToBook(AuthorCommandToAuthor authorConverter, CategoryCommandToCategory categoryConverter, FormatCommandToFormat formatConverter) {
        this.authorConverter = authorConverter;
        this.categoryConverter = categoryConverter;
        this.formatConverter = formatConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Book convert(BookCommand source) {
        if (source == null) {
            return null;
        }
        final Book book = new Book();
        book.setId(source.getId());
        book.setName(source.getName());
        book.setPublishDate(source.getPublishDate());
        book.setLanguage(source.getLanguage());
        book.setReview(source.getReview());
        book.setPages(source.getPages());
        book.setEANUPC(source.getEANUPC());
        book.setAvailable(source.getAvailable());
        book.setDescription(source.getDescription());
        book.setPrice(source.getPrice());


            if (source.getAuthors() != null && source.getAuthors().size() > 0) {
                source.getAuthors().forEach(authorCommand -> book.getAuthors().add(authorConverter.convert(authorCommand)));
            }


        if(source.getCategories()!=null&&source.getCategories().size()>0){
            source.getCategories().forEach(categoryCommand -> book.getCategories().add(categoryConverter.convert(categoryCommand)));
        }

        if(source.getFormats()!=null&& source.getFormats().size()>0){
            source.getFormats().forEach(formatCommand -> book.getFormats().add(formatConverter.convert(formatCommand)));
        }
        if (source.getPublisherId()!=null){
            Publisher publisher=new Publisher();
            publisher.setId(source.getPublisherId());
            book.setPublisher(publisher);
            publisher.getBooks().add(book);
        }
        //first I set publisher like follow
        /*
        Publisher publisher = new Publisher();
        publisher.setId(source.getPublisherId());
        book.setPublisher(publisher);
        publisher.getBooks().add(book);*/
        return book;
    }
}
