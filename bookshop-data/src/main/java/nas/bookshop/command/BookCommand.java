package nas.bookshop.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BookCommand extends BaseEntityCommand {

    private Set<AuthorCommand> authors = new HashSet<>();
    private Set<CategoryCommand> categories=new HashSet<>();
    private Set<FormatCommand> formats = new HashSet<>();

    //Many To One Relation, no need to create a new Obj, It's enough to just Set a PublisherId for Publisher
    private Long publisherId;
    private String name;
    private Long EANUPC;
    private String price;
    private Integer pages;
    private String language;
    private Boolean available;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
    private String description;
    private String review;

}
