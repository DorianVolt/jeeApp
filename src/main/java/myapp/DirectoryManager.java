package myapp;

import myapp.jpa.model.Group;
import myapp.jpa.model.Person;
import myapp.jpa.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class DirectoryManager implements IDirectoryManager{

    @Autowired
    private ILogger logger;

    @PostConstruct
    public void start() {
        if (logger == null) {
            throw new IllegalStateException("null logger");
        }
    }

    @Override
    public User newUser() {
        return null;
    }

    @Override
    public Person findPerson(User user, long personId) {
        return null;
    }

    @Override
    public Group findGroup(User user, long groupId) {
        return null;
    }

    @Override
    public boolean login(User user, long personId, String password) {
        return false;
    }

    @Override
    public void logout(User user) {

    }

    @Override
    public void savePerson(User user, Person p) {

    }
}
