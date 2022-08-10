package nas.bookshop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "book_formats",
            joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "format_id"))
    private Set<Format> formats = new HashSet<>();

    private String name;
    private Long EANUPC;
    //I delete this property because it's categories in my design.
    //private String BISAC;

    //private BigDecimal price;
    private String price;
    private Integer pages;
    private String language;
    private Boolean available;
    private LocalDate publishDate;

    @Lob
    private String description;
    @Lob
    private String review;

    //a helper method foe setting data for both sides(Author,Book) just for remembering the relation is many to many
    //and Owning side is not here and is Author, But I thought it won't matter to set the data here.
    public void addAuthor(Author author) {

        Set<Author> authors = new HashSet<>();
        authors.add(author);
        this.authors.add(author);
        //This is another way for not getting null error while using Builder
        if (author.getBooks() == null) {
            author.setBooks(new HashSet<>());
        }

        author.getBooks().add(this);
    }

}
