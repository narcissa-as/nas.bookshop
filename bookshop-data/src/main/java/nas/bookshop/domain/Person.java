package nas.bookshop.domain;

import lombok.*;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@MappedSuperclass
public class Person extends BaseEntity{
    private String firstName;
    private String lastName;
}
