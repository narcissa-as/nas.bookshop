package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.FormatCommand;
import nas.bookshop.domain.Format;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FormatToFormatCommand implements Converter<Format, FormatCommand> {

    @Synchronized
    @Nullable
    @Override
    public FormatCommand convert(Format source) {
        if(source==null){
            return null;
        }
        final FormatCommand command=new FormatCommand();

        command.setId(source.getId());
        command.setName(source.getName());

        return command;
    }
}
