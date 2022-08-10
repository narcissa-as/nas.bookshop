package nas.bookshop.bootstrap;

import nas.bookshop.domain.*;
import nas.bookshop.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class BookBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FormatRepository formatRepository;
    private final PublisherRepository publisherRepository;

    public BookBootstrap(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository,
                         FormatRepository formatRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.formatRepository = formatRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadData();
    }

    private void
    loadData() {
        List<Book> books = new ArrayList<>(2);

        //getting required Formats
        Optional<Format> formatOptional1 = formatRepository.findByName("Hardcover");
        if (!formatOptional1.isPresent()) {
            throw new RuntimeException("Expected Hardcover Not Found");
        }
        Optional<Format> formatOptional2 = formatRepository.findByName("Compact Disc");
        if (!formatOptional1.isPresent()) {
            throw new RuntimeException("Expected Compact Disc Not Found");
        }
        Format compactDisc = formatOptional1.get();
        Format hardcover = formatOptional2.get();

        //getting required Categories
        Optional<Category> categoryOptional1 = categoryRepository.findByDescription("Fantasy - Historical");
        if (!categoryOptional1.isPresent()) {
            throw new RuntimeException("Expected Fantasy - Historical Not Found");
        }
        Optional<Category> categoryOptional2 = categoryRepository.findByDescription("Romance");
        if (!categoryOptional2.isPresent()) {
            throw new RuntimeException("Expected Romance Disc Not Found");
        }
        Category fantasyHistorical = categoryOptional1.get();
        Category romance = categoryOptional2.get();

        Book foul = new Book();
        Author author = Author.builder().firstName("Chloe").lastName("Gong").description("Chloe Gong is the New York Times " +
                "bestselling author of These Violent Delights and its sequel, Our Violent Ends, as well as Foul Lady" +
                " Fortune. She is a recent graduate of the University of Pennsylvania, where she double majored in " +
                "English and international relations. Born in Shanghai and raised in Auckland, New Zealand, Chloe" +
                " is now located in New York pretending to be a real adult. You can find her on Twitter, Instagram," +
                " and TikTok under @TheChloeGong or check out her website at TheChloeGong.com.").books(new HashSet<>()).build();
//adding new Hashset<>() above is for defining an empty Hashset for avoid having a null Book list property and
// getting null error
/*
        Author author = new Author();
        author.setFirstName("Chloe");
        author.setLastName("Gong");
        author.setDescription("Chloe Gong is the New York Times " +
                "bestselling author of These Violent Delights and its sequel, Our Violent Ends, as well as Foul Lady" +
                " Fortune. She is a recent graduate of the University of Pennsylvania, where she double majored in " +
                "English and international relations. Born in Shanghai and raised in Auckland, New Zealand, Chloe" +
                " is now located in New York pretending to be a real adult. You can find her on Twitter, Instagram," +
                " and TikTok under @TheChloeGong or check out her website at TheChloeGong.com.");
*/
        Author savedAuthor = authorRepository.save(author);
        foul.addAuthor(author);

        foul.getCategories().add(fantasyHistorical);
        foul.getCategories().add(romance);

        foul.getFormats().add(hardcover);
        foul.getFormats().add(compactDisc);

        foul.setName("Foul Lady Fortune");
        foul.setPrice("$20.45");
        foul.setPages(528);
        foul.setLanguage("English");
        foul.setAvailable(true);
        foul.setEANUPC(9781665905589L);
        String publishDate = "2017-01-13";
        foul.setPublishDate(LocalDate.parse(publishDate));
        foul.setDescription("From the #1 New York Times bestselling author of These Violent Delights and Our Violent" +
                " Ends comes the first book in a captivating new duology following an ill-matched pair of spies posing" +
                " as a married couple to investigate a series of brutal murders in 1930s Shanghai.\n" +
                "It's 1931 in Shanghai, and the stage is set for a new decade of intrigue.\n" +
                "Four years ago, Rosalind Lang was brought back from the brink of death, but the strange experiment that" +
                " saved her also stopped her from sleeping and aging--and allows her to heal from any wound. In short, " +
                "Rosalind cannot die. Now, desperate for redemption for her traitorous past, she uses her abilities as" +
                " an assassin for her country.\n" +
                "Code name: Fortune.\n" +
                "But when the Japanese Imperial Army begins its invasion march, Rosalind's mission pivots.");
        foul.setReview("Amazing, show-stopping, spectacular. Chloe Gong does it again with her fantastical take on the " +
                "classic Chinese genre of Minguo-era spy thrillers, where no loyalties are certain and no one can be" +
                " trusted in the shadowy political battle between the Nationalists, communists, and imperialists." +
                " A enthralling mystery that is sure to stun as it unravels.\"--Xiran Jay Zhao, #1 New York Times " +
                "bestselling author of IRON WIDOW\n" +
                "\"Foul Lady Fortune had my heart pounding from the first chapter. With brilliant pacing and utterly" +
                " dynamic characters, Gong transports us to 1930s Shanghai--into a high stakes game of war, love, " +
                "and espionage--and conquers every page with cinematic precision.\"--Elizabeth Lim, New York Times " +
                "bestselling author of SIX CRIMSON CRANES\n" +
                "\"Chloe Gong's Foul Lady Fortune is an electric, action-packed jewel of a story, brimming with vivid " +
                "prose, exhilarating twists and turns, and incredible characters who will steal your heart. This book " +
                "dazzles.\"--Claire Legrand, New York Times bestselling author of Furyborn\n" +
                "\"Foul Lady Fortune is a thrilling historical fantasy packed with action, intrigue, and swoon-worthy " +
                "romance. ");


        Publisher publisher = new Publisher();
        publisher.setName("Margaret K. McElderry Books");
        publisher.setAddress("address");

        Publisher savedPublisher = publisherRepository.save(publisher);
        foul.setPublisher(savedPublisher);

        //publisher.addBook(foul);
        //foul.setPublisher(savedPublisher);

        Book savedBook = bookRepository.save(foul);


    }
}
