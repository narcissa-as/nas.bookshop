package nas.bookshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "publicationOwners")
public class PublicationOwner extends Person {
    @Builder
    public PublicationOwner(String firstName, String lastName) {
        super(firstName, lastName);
    }
/*
    @OneToMany(mappedBy = "owner")
    private Set<Publisher> publishers=new HashSet<>();*/
}
