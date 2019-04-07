/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Freelancer;
import model.FreelancerSkill;

/**
 *
 * @author caio
 */
@Stateless
public class FreelancersBean implements FreelancersBeanLocal {

    @PersistenceContext(unitName = "online-jobs-ejbPU")
    private EntityManager em;

    @Override
    public List<Freelancer> getFreelancersLimit(int start, int quant) {
        Query q = em.createNamedQuery("Freelancer.findAll");
        return q.setMaxResults(quant).setFirstResult(start).getResultList();
    }

    /**
     * make the passed object persistent
     *
     * @param object object to be made persistent
     */
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Freelancer getFreelancerById(int id) {
        Query q = em.createNamedQuery("Freelancer.findById");
        q.setParameter("id", id);
        try {
            return (Freelancer) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<FreelancerSkill> getFreelancerSkillsById(int id) {
        Query q = em.createNamedQuery("FreelancerSkill.findByFreelancerId");
        q.setParameter("freelancerId", id);
        return q.getResultList();
    }
    
    @Override
    public void updateFreelancerSkills(int id, List<FreelancerSkill> skills) {
        Query q = em.createNamedQuery("FreelancerSkill.deleteByFreelancerId");
        q.setParameter("freelancerId", id);
        q.executeUpdate();
        for (FreelancerSkill skill:skills){
            em.persist(skill);
        }
        em.flush(); 
    }
    
    @Override
    public BigDecimal getSumByFreelancerId(int FreelancerId) {
        em.flush();
        Query q = em.createNamedQuery("Payments.getSumByFreelancerId");
        q.setParameter("freelancerId", FreelancerId);
        return (BigDecimal)q.getSingleResult();
    }

}
