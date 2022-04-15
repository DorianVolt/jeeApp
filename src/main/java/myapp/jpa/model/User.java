package myapp.jpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@SessionScope
@Component
public class User{

    public boolean isLoggedIn;
    Person person;
    public String username = "Unknown";


    public void setUsername(String username){
        if (isLoggedIn){
            this.username = username;
        }else {
            this.username = "Unknown";
        }
    }

    public String getUsername() {
       return username;
    }

    public Person getPerson() {
        return person;
    }
}
