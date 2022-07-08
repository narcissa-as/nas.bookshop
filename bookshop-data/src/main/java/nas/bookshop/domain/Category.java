package nas.bookshop.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Book> books = new HashSet<>();
}
