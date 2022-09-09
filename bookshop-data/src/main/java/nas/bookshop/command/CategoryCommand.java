package nas.bookshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand extends BaseEntityCommand {
    private String description;
}
