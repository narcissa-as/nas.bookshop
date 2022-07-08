package nas.bookshop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "publishers")
@Entity
public class Publisher extends BaseEntity{
    private Person owner;
    private LocalDate publishDate;
    private String address;
    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();
}
