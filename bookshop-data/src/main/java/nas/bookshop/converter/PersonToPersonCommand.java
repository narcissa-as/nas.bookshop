package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.PersonCommand;
import nas.bookshop.domain.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonCommand implements Converter<Person, PersonCommand> {

    @Synchronized
    @Nullable
    @Override
    public PersonCommand convert(Person source) {
        if (source == null) {
            return null;
        }
        final PersonCommand command = new PersonCommand();

        command.setId(source.getId());
        command.setFirstName(source.getFirstName());
        command.setLastName(source.getLastName());

        return command;
    }
}
