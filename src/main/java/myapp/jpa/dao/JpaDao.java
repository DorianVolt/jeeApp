package myapp.jpa.dao;

import myapp.jpa.model.Group;
import myapp.jpa.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class JpaDao implements IDirectoryDao{

    @PersistenceContext
    EntityManager em;

    @Override
    public Collection<Group> findAllGroups() {
        /*String query = "SELECT g FROM Group g";
        TypedQuery<Group> q = em.createQuery(query, Group.class);
        return q.getResultList();*/
        return null;
    }

    @Override
    public Person findPerson(long id) {
        return em.find(Person.class,id);
    }

    @Override
    public Group findGroup(long id) {
        return em.find(Group.class,id);
    }

    @Override
    public Person addPerson(Person p) {
        em.persist(p);
        return p;
    }

    @Override
    public Group addGroup(Group g) {
        em.persist(g);
        return g;
    }


    @Override
    public void updatePerson(Person p){
        em.merge(p);
    }

    @Override
    public void updateGroup(Group g) {
        em.merge(g);
    }

    @Override
    public void savePerson(Person p) {
        if (findPerson(p.getId()) != null){
            addPerson(p);
        }else {
            updatePerson(p);
        }
    }


    @Override
    public void saveGroup(Group g) {
        if (findGroup(g.getId()) != null){
            addGroup(g);
        }else {
            updateGroup(g);
        }
    }


}
