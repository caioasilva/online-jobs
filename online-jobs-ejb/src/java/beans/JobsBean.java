/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Job;

/**
 *
 * @author caio
 */
@Stateless
public class JobsBean implements JobsBeanLocal {
    @PersistenceContext(unitName = "online-jobs-ejbPU")
    private EntityManager em;
    
    @Override
    public List<Job> getAllJobs(){
        Query q = em.createNamedQuery("Job.findAll");
        return q.getResultList();
    }
    
    @Override
    public List<Job> getJobsDescLimit(int start, int quant){
        Query q = em.createNamedQuery("Job.findAllOrderByDate");
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
    public Job getJobById(int id) {
//        JobDescription ret;
        try{
            Query q = em.createNamedQuery("Job.findById");
            q.setParameter("id", id);
            List<Job> r = q.getResultList();
            if (r.size() == 0)
                return null;
            return r.get(0);
        }catch(Exception e){
            return null;
        }
        
    }

    @Override
    public List<Job> getJobsByKeywords(List<String> keywords) {
        String query = "SELECT j FROM Job j WHERE j.id IN (SELECT k.jobKeywordPK.jobId FROM JobKeyword k WHERE ";
        for (String keyword: keywords){
            query+=" lower(k.jobKeywordPK.keyword) LIKE lower('"+keyword+"') OR";
        }
        query = query.substring(0,query.length()-2);
        query+=" GROUP BY k.jobKeywordPK.jobId)";
        Query q = em.createQuery(query);
        return q.getResultList();
    }
    
    

}
