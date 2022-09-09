package nas.bookshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormatCommand extends BaseEntityCommand{
    private String name;
}
