package myapp;

import myapp.jpa.model.Group;
import myapp.jpa.model.Person;
import myapp.jpa.model.User;

public interface IDirectoryManager {

    // créer un utilisateur anonyme
    User newUser();

    // chercher une personne
    Person findPerson(User user, long personId);

    // chercher un groupe
    Group findGroup(User user, long groupId);

    // identifier un utilisateur
    boolean login(User user, long personId, String password);

    // oublier l'utilisateur
    void logout(User user);

    // enregistrer une personne
    void savePerson(User user, Person p);
}