package Models;
import jakarta.persistence.*;
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstname;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastname;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column
    boolean active = true;

    public Student(String firstname, String lastname, String email,  boolean active) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.active = active;
    }
    public Student() {}

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isActive() {
        return active;
    }
}


/* CREATE TABLE students(id INT PRIMARY KEY,
first_name varchar(50) NOT NULL,
last_name varchar(50) NOT NULL,
email varchar(100) UNIQUE)
 */
