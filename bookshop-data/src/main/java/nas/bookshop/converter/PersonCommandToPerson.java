package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.PersonCommand;
import nas.bookshop.domain.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PersonCommandToPerson implements Converter<PersonCommand, Person> {

    @Synchronized
    @Nullable
    @Override
    public Person convert(PersonCommand source) {
        if (source == null) {
            return null;
        }
        final Person person = new Person();

        person.setId(source.getId());
        person.setFirstName(source.getFirstName());
        person.setLastName(source.getLastName());

        return person;
    }
}
