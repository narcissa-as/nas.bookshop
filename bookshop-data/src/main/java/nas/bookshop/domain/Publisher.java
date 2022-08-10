package nas.bookshop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "publishers")
@Entity
public class Publisher extends BaseEntity {
 //   @ManyToOne
//    @JoinColumn(name = "publicationowner_id")
//    private PublicationOwner owner;
    private String name;
    private String address;
    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();
/*
    public void setPublisher(String name,String firstName, String lastName, String address) {
        this.setName(name);
        this.owner.setFirstName(firstName);
        this.owner.setLastName(lastName);
        this.setAddress(address);
    }*/

}
