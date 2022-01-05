import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* ------  Custom key generation:----------
  1. create implementation of org.hibernate.id.IdentifierGenerator (it is an interface);
  2. override the method: public Serializable generate(...) )

  ------- Auto generation: -------------
  use annotation: @GeneratedValue(strategy=GenerationType.IDENTITY)

  -------- Changing a starting point for an auto-increment:---------------
  in your DB:
  alter database_name.table table_name auto_increment = 1000;
  - will start id's from 1000.
*/

@Entity
@Table//(name="student")
public class Student {
    @Id
    @Column(name="email")   // annotation @Column(...) does not work if you do not have it in your database yet
    private String email;
    @Column(name="name")
    private String name;
    @Column(name = "password")
    private String password;

    public Student() {

    }

    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
