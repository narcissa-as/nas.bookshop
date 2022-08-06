package nas.bookshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "authors")
public class Author extends Person {
    @Builder
    public Author(Long id, String firstName, String lastName, Set<Book> books, String description) {
        super(id, firstName, lastName);
        this.books = books;
        this.description = description;
    }

    @ManyToMany
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books = new HashSet<>();

    private String description;
}
