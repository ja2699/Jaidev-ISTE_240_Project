package Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import Models.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentDAO extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    List<Student> findByFirstName(String firstName);
    void deleteByEmail(String email);
    boolean existsByEmail(String email);



    //long deleteById(long id);

    long countByActive();


}
