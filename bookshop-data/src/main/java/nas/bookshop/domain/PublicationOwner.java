package nas.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "publicationOwners")
public class PublicationOwner extends Person {
    @OneToMany(mappedBy = "owner")
    private Set<Publisher> publishers=new HashSet<>();
}
