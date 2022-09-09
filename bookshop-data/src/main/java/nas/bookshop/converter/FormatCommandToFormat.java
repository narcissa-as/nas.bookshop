package nas.bookshop.converter;

import lombok.Synchronized;
import nas.bookshop.command.FormatCommand;
import nas.bookshop.domain.Format;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FormatCommandToFormat implements Converter<FormatCommand, Format> {

    @Synchronized
    @Nullable
    @Override
    public Format convert(FormatCommand source) {
        if(source==null){
            return null;
        }
        final Format format=new Format();

        format.setId(source.getId());
        format.setName(source.getName());

        return format;
    }
}