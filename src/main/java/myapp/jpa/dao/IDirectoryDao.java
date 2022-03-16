package myapp.jpa.dao;

import myapp.jpa.model.Group;
import myapp.jpa.model.Person;

import java.util.Collection;


public interface IDirectoryDao {

    // récupérer les groupes
    Collection<Group> findAllGroups();

    // lire une personne
    Person findPerson(long id);

    // lire un groupe et ses personnes
    Group findGroup(long id);

    // modification ou ajout d'une nouvelle personne
    void savePerson(Person p);

    // modification ou ajout d'une nouvelle personne
    void saveGroup(Group g);

    Person addPerson(Person p);

    Group addGroup(Group g);

    void updatePerson(Person p);

    void updateGroup(Group g);


}