package myapp;

import myapp.jpa.dao.JpaDao;
import myapp.jpa.model.Group;
import myapp.jpa.model.Person;
import myapp.jpa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collection;


@Repository
@Transactional
public class DirectoryManager implements IDirectoryManager{

    @Autowired
    private ILogger logger;

    @Autowired
    JpaDao dao;

    @PostConstruct
    public void start() {
        if (logger == null) {
            throw new IllegalStateException("null logger");
        }
    }

    @Override
    public User newUser() {
        return new User();
    }

    @Override
    public Person findPerson(User user,long personId) {
        if (user.isLoggedIn){
           return dao.findPerson(personId);
        }
        else{
            System.out.println("Anonymous user");
            return null;
        }
    }

    @Override
    public Group findGroup(User user, long groupId) {
        if (user.isLoggedIn){
            return dao.findGroup(groupId);
        }
        else{
            System.out.println("Anonymous user");
            return null;
        }
    }

    @Override
    public boolean login(User user, long personId, String password) {
        var person = dao.findPerson(personId);
        if (person.getPassword().equals(password)){
            user.person = person;
            user.isLoggedIn = true;
            return true;
        }
        else {
            System.out.println("Wrong information");
            return false;
        }
    }

    @Override
    public void logout(User user) {
        user.isLoggedIn = false;
        user.person = null;
    }

    @Override
    public void savePerson(User user, Person p) {
        user.person = p;
        dao.addPerson(p);
    }

    @Override
    public void saveGroup(Group group){
        dao.saveGroup(group);
    }

    @Override
    public Collection<Group> findAllGroups() {
        return dao.findAllGroups();
    }
}
