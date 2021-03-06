package myapp.jpa;

import myapp.jpa.dao.JpaDao;
import myapp.jpa.model.Generator;
import myapp.jpa.model.Group;
import myapp.jpa.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;


import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestJpaDao {

    @Autowired
    JpaDao dao;

    @Test
    public void addPerson() {
        var person = new Person("Dorian","Dupon","dorian@mail.fr",null,null,"1234");
        person = dao.addPerson(person);
        assertTrue(person.getId() > 1);
    }

    @Test
    public void addPersonToGroup(){
        var person = new Person("Charlene","Dupon","charlene@mail.fr",null,null,"1234");
        var group = new Group("FSI");
        group.addStudent(person);
        dao.addGroup(group);

        assertEquals(1, group.getStudents().size());
    }


    @Test
    public void findPerson(){
        var person = new Person("Eric","Dupon","eric@mail.fr",null,null,"1234");
        dao.addPerson(person);

        assertEquals("Eric",dao.findPerson(person.getId()).getFirstName());
    }

    @Test
    public void findPersonWithPassword(){
        var person = new Person("Lucas","SansTabouret","lucas@mail.fr",null,null,"1235");
        dao.addPerson(person);
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
        assertEquals("IDL",group.getGroupName());
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

        group.setGroupName("GIG");
        dao.updateGroup(group);

        group = dao.findGroup(group.getId());
        assertEquals("GIG",group.getGroupName());
    }

    @Test
    public void testSameLastName(){
        var eclair = new Person("Eclair","TarteAuCitron","hotdog@live.com",null,null,"number1");
        var sable = new Person("Sable","TarteAuCitron","spaghetti@live.fr",null,null,":)4141");

        assertDoesNotThrow(()->{
            dao.addPerson(sable);
            dao.addPerson(eclair);
        });
    }

    @Test
    public void testSameEmailAddress(){
        var joe = new Person("Joe","Warm","iceIceBaby@hotmail.fr",null,null,"number1");
        var maya = new Person("Maya","Cool","iceIceBaby@hotmail.fr",null,null,":)4141");


        assertThrows(DataIntegrityViolationException.class,()->{
            dao.addPerson(joe);
            dao.addPerson(maya);
        });
    }

    @Test
    public void testSameWebsite(){
        var joe = new Person("Joe","Cool","coolest@fire.com","CoolWebSite",null,"number1");
        var maya = new Person("Maya","Cool","iceIceBaby@hotmail.fr","CoolWebSite",null,":)4141");


        assertThrows(DataIntegrityViolationException.class,()->{
            dao.addPerson(joe);
            dao.addPerson(maya);
        });
    }

    @Test
    public void testFindAllGroup() {
        dao.addGroup(new Group("ISD"));
        Collection<Group> groups = dao.findAllGroups();
        assertEquals(1,groups.size());
    }

   @Test
    public void testGenerate() {
        Generator generator = new Generator();
        int nbPersonnes = 100;
        generator.generatePerson(nbPersonnes,dao);
        assertEquals(nbPersonnes,dao.countPerson());
    }
}