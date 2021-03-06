package myapp.jpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Person")
@Data
@NoArgsConstructor

@Table(name = "TPerson")



public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version()
    private long version = 0;

    @ManyToOne(optional = true)
    @JoinColumn(name = "TGroup")
    @ToString.Exclude
    private Group schoolGroup;

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic(optional = false)
    @Column(name = "first_name", length = 200, nullable = false, unique = false)
    private String firstName;

    @Basic(optional = false)
    @Column(name = "last_name", length = 200, nullable = false, unique = false)
    private String lastName;

    @Basic(optional = false)
    @Column(name = "email_address", length = 200, nullable = false, unique = true)
    private String emailAddress;

    @Basic()
    @Column(name = "web_site", length = 200, unique = true)
    private String webSite;

    @Basic()
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
    private Date birthDay;

    @Basic(optional = false)
    @Column(name = "password", length = 200, nullable = false, unique = false)
    private String password;


    public Person(String firstName, String lastName, String emailAddress, String webSite, Date birthDay, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.webSite = webSite;
        this.birthDay = birthDay;
        this.password = password;
    }
}
