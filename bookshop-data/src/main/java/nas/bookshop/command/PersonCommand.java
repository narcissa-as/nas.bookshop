package nas.bookshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PersonCommand extends BaseEntityCommand {
    private String firstName;
    private String lastName;
}
