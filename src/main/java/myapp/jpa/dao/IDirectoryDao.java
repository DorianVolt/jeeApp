package myapp.jpa.dao;

import myapp.jpa.model.Group;
import myapp.jpa.model.Person;

import java.util.Collection;


public interface IDirectoryDao {

    Collection<Group> findAllGroups();

    Person findPerson(long id);

    Person findPerson(String email);

    Group findGroup(long id);

    void savePerson(Person p);

    void saveGroup(Group g);

    Person addPerson(Person p);

    Group addGroup(Group g);

    void updatePerson(Person p);

    void updateGroup(Group g);

    void removePerson(long id);

    int countPerson();


}