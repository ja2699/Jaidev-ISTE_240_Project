package Repositories;

import Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDAO extends JpaRepository<Course, Long> {
    //findby course id, cousr name, etc.
}
