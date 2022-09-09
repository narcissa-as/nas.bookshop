package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.CategoryCommand;
import nas.bookshop.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }
        final CategoryCommand command = new CategoryCommand();

        command.setId(source.getId());
        command.setDescription(source.getDescription());

        return command;
    }
}
