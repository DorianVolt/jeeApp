package myapp.jpa.model;


import myapp.jpa.dao.JpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;


@Service
@Transactional
public class Generator  {



    public void generatePerson(int nbPeronnes,JpaDao dao) {
        for (int i = 0; i < nbPeronnes; i++) {

            Person person = new Person(
                    "firstName" + i,
                    "lastName" + i,
                    "email" + i + "@gmail.com",
                    null,
                    null,
                    "azerty" + i);

            dao.addPerson(person);
        }
    }
}
