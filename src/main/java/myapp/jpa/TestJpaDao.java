package myapp.jpa;

import myapp.jpa.dao.JpaDao;
import myapp.jpa.model.Group;
import myapp.jpa.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.RollbackException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestJpaDao {

    @Autowired
    JpaDao dao;

    @Test
    public void addPerson(){
        var person = new Person("Alice","Dupon","alice@mail.fr",null,null,"1234");
        person = dao.addPerson(person);
        assertTrue(person.getId() > 0);
    }


    @Test
    public void findPerson(){
        var person = new Person("Alice","Dupon","alice@mail.fr",null,null,"1234");
        dao.addPerson(person);

        assertEquals("Alice",dao.findPerson(person.getId()).getFirstName());
    }

    @Test
    public void addGroup(){
        var group = new Group("FSI");
        group = dao.addGroup(group);
        assertTrue(group.getId() > 0);
    }

    @Test
    public void findGroup(){
        var group = new Group("IDL");
        group = dao.addGroup(group);

        group = dao.findGroup(group.getId());
        assertEquals("IDL",group.getName());
    }

    @Test
    public void updatePerson(){
        var person = new Person("Alice","Dupon","alice@mail.fr",null,null,"1234");
        person = dao.addPerson(person);
        person.setFirstName("Julie");
        dao.updatePerson(person);

        person = dao.findPerson(person.getId());

        assertEquals("Julie",person.getFirstName());
    }

    @Test
    public void updateGroup(){
        var group = new Group("FSI");
        group = dao.addGroup(group);

        group.setName("GIG");
        dao.updateGroup(group);

        group = dao.findGroup(group.getId());
        assertEquals("GIG",group.getName());
    }

    @Test
    public void testSameLastName(){
        var joe = new Person("Joe","Cool","coolest@fire.com",null,null,"number1");
        var maya = new Person("Maya","Cool","iceIceBaby@hotmail.fr",null,null,":)4141");

        assertDoesNotThrow(()->{
            dao.addPerson(maya);
            dao.addPerson(joe);
        });
    }

    @Test
    public void testSameEmailAddress(){
        var joe = new Person("Joe","Cool","iceIceBaby@hotmail.fr",null,null,"number1");
        var maya = new Person("Maya","Cool","iceIceBaby@hotmail.fr",null,null,":)4141");


        assertThrows(RollbackException.class,()->{
            dao.addPerson(joe);
            dao.addPerson(maya);
        });
    }

    @Test
    public void testSameWebsite(){
        var joe = new Person("Joe","Cool","oolest@fire.com","CoolWebSite",null,"number1");
        var maya = new Person("Maya","Cool","iceIceBaby@hotmail.fr","CoolWebSite",null,":)4141");


        assertThrows(DataIntegrityViolationException.class,()->{
            dao.addPerson(joe);
            dao.addPerson(maya);
        });
    }

}