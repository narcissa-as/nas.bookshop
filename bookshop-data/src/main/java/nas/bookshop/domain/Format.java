package nas.bookshop.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "formats")
public class Format extends BaseEntity {
    private String name;
    @ManyToMany(mappedBy = "formats")
    private Set<Book> books = new HashSet<>();
}
