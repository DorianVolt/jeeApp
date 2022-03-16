package jpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Person")
@Data
@NoArgsConstructor


@Table(name = "TPerson",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "id","first_name","last_name","email_address","web_site","birth_day","password"
                })
        })

public class Person implements Serializable {

    @Version()
    private long version = 0;

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Basic(optional = false)
    @Column(name = "first_name", length = 200, nullable = false, unique = false)
    private String firstName;

    @Basic(optional = false)
    @Column(name = "last_name", length = 200, nullable = false, unique = true)
    private String lastName;

    @Basic(optional = false)
    @Column(name = "email_address", length = 200, nullable = false, unique = true)
    private String emailAddress;

    @Basic(optional = false)
    @Column(name = "web_site", length = 200, nullable = true, unique = true)
    private String webSite;

    @Basic()
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
    private Date birthDay;

    @Basic(optional = false)
    @Column(name = "password", length = 200, nullable = false, unique = false)
    private String password;
}
