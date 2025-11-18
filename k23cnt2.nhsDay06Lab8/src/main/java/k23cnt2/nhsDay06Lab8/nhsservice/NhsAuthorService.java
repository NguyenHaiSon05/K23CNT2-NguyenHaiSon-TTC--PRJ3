package k23cnt2.nhsDay06Lab8.nhsservice;

import k23cnt2.nhsDay06Lab8.nhsentity.NhsAuthor;
import k23cnt2.nhsDay06Lab8.nhsrepository.NhsAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhsAuthorService {

    @Autowired
    private NhsAuthorRepository nhsAuthorRepository;

    public List<NhsAuthor> getAllAuthors() {
        return nhsAuthorRepository.findAll();
    }

    public NhsAuthor saveAuthor(NhsAuthor author) {
        return nhsAuthorRepository.save(author);
    }

    public NhsAuthor getAuthorById(Long id) {
        return nhsAuthorRepository.findById(id).orElse(null);
    }

    public void deleteAuthor(Long id) {
        nhsAuthorRepository.deleteById(id);
    }

    public List<NhsAuthor> findAllById(List<Long> ids) {
        return nhsAuthorRepository.findAllById(ids);
    }
}
