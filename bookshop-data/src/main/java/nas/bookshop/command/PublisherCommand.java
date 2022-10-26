package nas.bookshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PublisherCommand extends BaseEntityCommand {
    @NotBlank
    @Size(min = 1)
    private String name;
    private String address;
    private Set<BookCommand> books = new HashSet<>();
}
