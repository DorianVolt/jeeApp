package myapp.jpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Group")
@Data
@NoArgsConstructor

@Table(name = "TGroup")

public class Group {

    @OneToMany(
            cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },
            fetch = FetchType.LAZY, mappedBy = "schoolGroup")
    @ToString.Exclude
    private Set<Person> students;

    private static final long serialVersionUID = 1L;

    @Version()
    private long version = 0;

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic(optional = false)
    @Column(name = "group_name", length = 200, nullable = false)
    private String groupName;

    public Group(String groupName){
        this.groupName = groupName;
        this.students = new HashSet<>();
    }

    public void addStudent(Person person){
        if (students == null){
            students = new HashSet<>();
        }
        students.add(person);
    }


}
