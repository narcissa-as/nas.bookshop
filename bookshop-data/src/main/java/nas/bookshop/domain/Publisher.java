package nas.bookshop.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "publishers")
@Entity
public class Publisher extends BaseEntity{
    private Person owner;
    private LocalDate publishDate;
    private String address;
    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();
}
