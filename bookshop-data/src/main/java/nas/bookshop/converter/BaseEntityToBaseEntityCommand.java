package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.BaseEntityCommand;
import nas.bookshop.domain.BaseEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BaseEntityToBaseEntityCommand implements Converter<BaseEntity, BaseEntityCommand> {

    @Synchronized
    @Nullable
    @Override
    public BaseEntityCommand convert(BaseEntity source) {
        if (source == null) {
            return null;
        }

        final BaseEntityCommand command = new BaseEntityCommand();
        command.setId(source.getId());

        return command;
    }
}
