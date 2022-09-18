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

        //Creating Book1

        //getting required Formats
        Optional<Format> hardCoverOptional = formatRepository.findByName("Hardcover");
        if (!hardCoverOptional.isPresent()) {
            throw new RuntimeException("Expected Hardcover Not Found");
        }
        Optional<Format> compactDiscOptional = formatRepository.findByName("Compact Disc");
        if (!compactDiscOptional.isPresent()) {
            throw new RuntimeException("Expected Compact Disc Not Found");
        }
        Format hardcover = hardCoverOptional.get();
        Format compactDisc = compactDiscOptional.get();

        //getting required Categories
        Optional<Category> fantasyHistoricalOptional = categoryRepository.findByDescription("Fantasy - Historical");
        if (!fantasyHistoricalOptional.isPresent()) {
            throw new RuntimeException("Expected Fantasy - Historical Not Found");
        }
        Optional<Category> romanceOptional = categoryRepository.findByDescription("Romance");
        if (!romanceOptional.isPresent()) {
            throw new RuntimeException("Expected Romance Disc Not Found");
        }
        Category fantasyHistorical = fantasyHistoricalOptional.get();
        Category romance = romanceOptional.get();

        Book foul = new Book();
        Author bAuthor = Author.builder().firstName("b").lastName("blast").description("des").build();
        Author foulAuthor = Author.builder().firstName("Chloe").lastName("Gong").description("Chloe Gong is the New York Times " +
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
        Author savedBAuthor = authorRepository.save(bAuthor);
        Author savedFoulAuthor = authorRepository.save(foulAuthor);
        foul.addAuthor(bAuthor);
        foul.addAuthor(foulAuthor);

        foul.getCategories().add(fantasyHistorical);
        foul.getCategories().add(romance);

        foul.getFormats().add(hardcover);
        foul.getFormats().add(compactDisc);
        ///set Book detail Properties

        foul.setName("Foul Lady Fortune");
        foul.setPrice("$20.45");
        foul.setPages(528);
        foul.setLanguage("English");
        foul.setAvailable(true);
        foul.setEANUPC(9781665905589L);
        String foulPublishDate = "2017-01-13";
        foul.setPublishDate(LocalDate.parse(foulPublishDate));

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


        Publisher foulPublisher = new Publisher();
        foulPublisher.setName("Margaret K. McElderry Books");
        foulPublisher.setAddress("address");

        Publisher savedPublisher = publisherRepository.save(foulPublisher);
        foul.setPublisher(savedPublisher);

        //foulPublisher.addBook(foul);
        //foul.setPublisher(savedPublisher);

        Book savedBook = bookRepository.save(foul);

        //Creating Book2

        //getting required Formats
        Optional<Format> smallSquareOptional = formatRepository.findByName("Small Square");
        if (!smallSquareOptional.isPresent()) {
            throw new RuntimeException("Expected Hardcover Not Found");
        }
        //Intellij suggestion ".isEmpty vs ! isPresent
        Optional<Format> standardPortraitOptional = formatRepository.findByName("Standard Portrait");
        if (standardPortraitOptional.isEmpty()) {
            throw new RuntimeException("Expected Compact Disc Not Found");
        }
        Format smallSquare = smallSquareOptional.get();
        Format standardPortrait = standardPortraitOptional.get();

        //getting required Categories
        Optional<Category> generalOptional = categoryRepository.findByDescription("General");
        if (generalOptional.isEmpty()) {
            throw new RuntimeException("Expected Fantasy - Historical Not Found");
        }
        Optional<Category> peoplePlacesOptional = categoryRepository.findByDescription("People & Places");
        if (!peoplePlacesOptional.isPresent()) {
            throw new RuntimeException("Expected Romance Disc Not Found");
        }
        Category general = generalOptional.get();
        Category peoplePlaces = peoplePlacesOptional.get();

        Book plant = new Book();
        Author plantAuthor = Author.builder().firstName("Sheil").lastName("Shukla").description("Dr. Sheil Shukla is an " +
                "internal medicine resident physician, artist, and cook who's passionate about plant-based nutrition and" +
                " its applications to clinical medicine. He loves exploring cultures through their food, and he's" +
                " especially interested in South Asian and East Asian cuisines, particularly the thali tradition of" +
                " Gujarat, India. He runs the website sheilshukla.com and the Instagram @plantbasedartist. He lives in " +
                "Chicago, IL.").books(new HashSet<>()).build();

        Author savedPlantAuthor = authorRepository.save(plantAuthor);
        plant.addAuthor(plantAuthor);
        //setting Categories and Formats
        plant.getCategories().add(peoplePlaces);
        plant.getCategories().add(general);

        plant.getFormats().add(smallSquare);
        plant.getFormats().add(standardPortrait);

        //setting Books detail prperties
        plant.setName("Plant-Based India: Nourishing Recipes Rooted in Tradition");
        plant.setPrice("$27.90");
        plant.setPages(256);
        plant.setLanguage("English");
        plant.setAvailable(true);
        plant.setEANUPC(9781615198535L);
        String plantPublishDate = "2021-01-13";
        plant.setPublishDate(LocalDate.parse(plantPublishDate));
        plant.setDescription("The first [cookbook] from which I want to cook absolutely everything.\"--Joanne Lee " +
                "Molinaro, creator of The Korean Vegan and New York Times-bestselling author of The Korean Vegan" +
                " Cookbook 100 stunningly photographed vegan recipes that celebrate fresh, healthful produce--and" +
                " capture the indelible flavors of India, India is home to a vibrant tapestry of culinary " +
                "traditions--and to more vegetarians than anywhere else in the world. It's also where" +
                " Dr. Sheil Shukla learned to love traditional Gujarāti fare, cooking alongside his adored ba" +
                " (grandmother) over summers in Mumbai. During his medical training, Dr. Shukla discovered the power of " +
                "plant-based nutrition to prevent and manage chronic illness--and so began his mission to reinvent the " +
                "classic vegetarian dishes of his heritage.");
        plant.setReview("Plant-Based India presents over 100 completely vegan recipes for shāk (spiced vegetable dishes)" +
                ", dāl (legume stews), rotli (flatbreads), bhāt (rice dishes), and more. From a comforting Pālak Tofu " +
                "that transcends dairy-based paneer, to vegan Nān, festive Navratan Rice, hearty Dāl Makhani, and " +
                "summery Chocolate Chāi Mousse with Berries, these are recipes from the heart--filled with nourishing " +
                "ingredients at their seasonal best.");


        Publisher plantPublisher = new Publisher();
        plantPublisher.setName("Experiment");
        plantPublisher.setAddress("address");

        Publisher savedPlantPublisher = publisherRepository.save(plantPublisher);
        plant.setPublisher(savedPlantPublisher);

        Book savedPlantBook = bookRepository.save(plant);

        //creating a Book that has a Author in Author table
        Book bookChloeAuthor = new Book();

        bookChloeAuthor.addAuthor(foulAuthor);

        bookChloeAuthor.setName("Everything Is Temporary: Illustrated Contemplations on How Death Shapes Our Lives");
        bookChloeAuthor.setPublisher(plantPublisher);
        bookChloeAuthor.setPrice("70");
        bookChloeAuthor.setPages(120);
        Book savedBookChloeAuthor = bookRepository.save(bookChloeAuthor);

    }
}
