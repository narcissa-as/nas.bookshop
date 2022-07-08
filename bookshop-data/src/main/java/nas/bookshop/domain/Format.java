package nas.bookshop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "formats")
public class Format extends BaseEntity {
    private String name;
    @ManyToMany(mappedBy = "formats")
    private Set<Book> books = new HashSet<>();
}
