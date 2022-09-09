package nas.bookshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorCommand extends PersonCommand {

    private String description;
    //Commented because of circle dependency
  // Set<BookCommand> books = new HashSet<>();
}
