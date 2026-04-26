package Models;
import jakarta.persistence.*;

@Entity
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String courseCode;
    private String courseCredit;
    private String grade;

    @ManyToOne
    @JoinColumn(name = "student_id",  nullable = false)
    private Student student;

    public Course() {}
    public Course(String courseName, String courseCode, String courseCredit, String grade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.grade = grade;
    }
}
