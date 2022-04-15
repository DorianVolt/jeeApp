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
    public Person person;


}
