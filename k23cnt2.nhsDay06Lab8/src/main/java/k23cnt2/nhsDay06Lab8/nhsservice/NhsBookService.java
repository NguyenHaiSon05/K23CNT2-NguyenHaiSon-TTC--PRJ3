package k23cnt2.nhsDay06Lab8.nhsservice;

import k23cnt2.nhsDay06Lab8.nhsentity.NhsBook;
import k23cnt2.nhsDay06Lab8.nhsrepository.NhsBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhsBookService {

    private final NhsBookRepository nhsBookRepository;

    public NhsBookService(NhsBookRepository nhsBookRepository) {
        this.nhsBookRepository = nhsBookRepository;
    }

    public List<NhsBook> getAllBooks() {
        return nhsBookRepository.findAll();
    }

    public NhsBook saveBook(NhsBook book) {
        return nhsBookRepository.save(book);
    }

    public NhsBook getBookById(Long id) {
        return nhsBookRepository.findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        nhsBookRepository.deleteById(id);
    }
}
