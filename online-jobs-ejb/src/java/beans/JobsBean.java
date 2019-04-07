/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Job;
import model.JobKeyword;
import model.Provider;

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

    @Override
    public int createJob(Job j) {
        int id = ((Integer) em.createNamedQuery("Job.getHighestID").getSingleResult()) + 1;
        System.out.println(id);
        j.setId(id);
        j.setStatus("open");
        j.setCreationDate(new Date());
        
        persist(j);
        return id;
    }
    
    
    @Override
    public List<JobKeyword> getKeywordsById(int id) {
        Query q = em.createNamedQuery("JobKeyword.findByJobId");
        q.setParameter("jobId", id);
        return q.getResultList();
    }
    
    @Override
    public void updateJobKeywords(int id, List<JobKeyword> keys) {
        Query q = em.createNamedQuery("JobKeyword.deleteByJobId");
        q.setParameter("jobId", id);
        q.executeUpdate();
        for (JobKeyword jk:keys){
            em.persist(jk);
        }
        
    }

    @Override
    public Job updateJob(Job job) {
        return em.merge(job);
    }
    
    

}
