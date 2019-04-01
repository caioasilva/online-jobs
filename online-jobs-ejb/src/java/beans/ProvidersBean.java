/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Provider;

/**
 *
 * @author caio
 */
@Stateless
public class ProvidersBean implements ProvidersBeanLocal {

    @PersistenceContext(unitName = "online-jobs-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Provider getProviderById(int id) {
        Query q = em.createNamedQuery("Provider.findById");
        q.setParameter("id", id);
        try{
            return (Provider) q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
}
