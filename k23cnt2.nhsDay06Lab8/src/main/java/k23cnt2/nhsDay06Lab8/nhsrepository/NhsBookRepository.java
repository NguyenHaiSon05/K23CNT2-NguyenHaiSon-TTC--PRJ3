package k23cnt2.nhsDay06Lab8.nhsrepository;

import k23cnt2.nhsDay06Lab8.nhsentity.NhsBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhsBookRepository extends JpaRepository<NhsBook, Long> {

}
