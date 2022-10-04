package nas.bookshop.service.springdatajpa;

import nas.bookshop.domain.Format;
import nas.bookshop.repository.FormatRepository;
import nas.bookshop.service.FormatService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class FormatSDJPAService implements FormatService {
    private final FormatRepository formatRepository;

    public FormatSDJPAService(FormatRepository formatRepository) {
        this.formatRepository = formatRepository;
    }

    @Override
    public Set<Format> findAll() {
        Set<Format>formats=new HashSet<>();
        formatRepository.findAll().forEach(formats::add);
        return formats;
    }

    @Override
    public Format findById(Long aLong) {
        return null;
    }

    @Override
    public Format save(Format object) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Format object) {

    }
}
