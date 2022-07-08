package nas.bookshop.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    @JoinColumn (name="publisher_id")
    private Publisher publisher;

    @ManyToMany
    @JoinTable (name="book_category",
    joinColumns=@JoinColumn(name="book_id"),inverseJoinColumns=@JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(name="book_formats",
    joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name="format_id"))
    private Set<Format> formats=new HashSet<>();

    private String name;
    private Long EANUPC;
    private String BISAC;

    private BigDecimal price;
    private Integer pages;
    private String language;
    private Boolean available;
    //@lobs
    private String description;
    private String review;

}
