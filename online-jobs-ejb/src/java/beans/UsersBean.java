/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.FreelancerSkill;
import model.User;

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
        try{
            return (User) q.getSingleResult();
        }catch(Exception e){
            return null;
        }

    }
    
    

    @Override
    public User updateUser(User u) {
        User r = (User)em.merge(u);
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

}
