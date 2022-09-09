package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.BaseEntityCommand;
import nas.bookshop.domain.BaseEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BaseEntityCommandToBaseEntity implements Converter<BaseEntityCommand, BaseEntity> {

    @Synchronized
    @Nullable
    @Override
    public BaseEntity convert(BaseEntityCommand source) {
        if (source == null) {
            return null;
        }

        final BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(source.getId());

        return baseEntity;
    }
}
