package myapp.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Group")
@Data
@NoArgsConstructor

@Table(name = "TGroup",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "id","name"
                })
        })

public class Group {

    private static final long serialVersionUID = 1L;

    @Version()
    private long version = 0;

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public Group(String name){
        this.name = name;
    }
}
