package myapp;

import myapp.jpa.model.Group;
import myapp.jpa.model.Person;
import myapp.jpa.model.User;

import java.util.Collection;
import java.util.List;

public interface IDirectoryManager {

    User newUser();

    Person findPerson(User user, long personId);

    Group findGroup(User user, long groupId);

    boolean login(User user, long personId, String password);

    void logout(User user);

    void savePerson(User user, Person p);

    void saveGroup(Group p);

    Collection<Group> findAllGroups();
}