package k23cnt2.nhsDay06Lab8.nhsrepository;

import k23cnt2.nhsDay06Lab8.nhsentity.NhsAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhsAuthorRepository extends JpaRepository<NhsAuthor, Long> {

}
