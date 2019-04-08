/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.FreelancerSkill;
import model.User;
import model.Freelancer;
import model.Provider;

/**
 *
 * @author caio
 */
@Stateless
public class UsersBean implements UsersBeanLocal {

    @PersistenceContext(unitName = "online-jobs-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public User getUser(String username, String password) {
        Query q = em.createNamedQuery("User.findByUsernamePassword");
        q.setParameter("username", username);
        q.setParameter("password", password);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        try {
            return (User) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public User updateUser(User u) {
        User r = (User) em.merge(u);
        em.flush();
        return r;
    }

    @Override
    public User getUser(int id) {
        Query q = em.createNamedQuery("User.findById");
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        q.setParameter("id", id);
        User r = (User) q.getSingleResult();
        em.refresh(r);
        return r;

    }

    @Override
    public User createUser(char type, String username, String password, String name, String email) {
        int id;
        try {
            id = ((Integer) em.createNamedQuery("User.getHighestID").getSingleResult()) + 1;
        } catch (Exception e) {
            id = 1;
        }
        User newUser = new User(id, username, password, type);
        if (type == 'f') {
            int idf;
            try {
                idf = ((Integer) em.createNamedQuery("Freelancer.getHighestID").getSingleResult()) + 1;
            } catch (Exception e) {
                idf = 1;
            }
            Freelancer newFreelancer = new Freelancer(idf, name, email);
            newFreelancer.setDate(new Date());
            newFreelancer.setUserId(newUser);
            newUser.setFreelancer(newFreelancer);
        } else if (type == 'p') {
            int idp;
            try {
                idp = ((Integer) em.createNamedQuery("Provider.getHighestID").getSingleResult()) + 1;
            } catch (Exception e) {
                idp = 1;
            }
            Provider newProvider = new Provider(idp);
            newProvider.setName(name);
            newProvider.setEmail(email);
            newProvider.setDate(new Date());
            newProvider.setUserId(newUser);
            newUser.setProvider(newProvider);
        }
        try {
            em.persist(newUser);
            em.flush();
            return newUser;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

    }

    @Override
    public List<User> getAllUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public void removeUser(int id) {
        User u = (User) em.createNamedQuery("User.findById").setParameter("id", id).getSingleResult();
        em.remove(u);
    }
    

}
