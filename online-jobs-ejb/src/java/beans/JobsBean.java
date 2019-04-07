/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Job;
import model.JobKeyword;
import model.JobOffer;
import model.Payments;
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
    public List<Job> getAllOpenJobs(){
        Query q = em.createNamedQuery("Job.findAllOrderByDate");
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
        String query = "SELECT j FROM Job j WHERE j.status = 'open' AND j.id IN (SELECT k.jobKeywordPK.jobId FROM JobKeyword k WHERE ";
        for (String keyword: keywords){
            query+=" lower(k.jobKeywordPK.keyword) LIKE lower('"+keyword+"') OR";
        }
        query = query.substring(0,query.length()-2);
        query+=" GROUP BY k.jobKeywordPK.jobId)";
        Query q = em.createQuery(query);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
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
        em.flush();
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
        em.flush();
        
    }

    @Override
    public Job updateJob(Job job) {
        return em.merge(job);
        
    }

    @Override
    public void deleteJob(int id) {
        Query q1 = em.createNamedQuery("Job.findById");
        q1.setParameter("id", id);
        Job j = (Job)q1.getSingleResult();
        if (j.getStatus().compareTo("open")==0){
            Query q = em.createNamedQuery("Job.deleteById");
            q.setParameter("id", id);
            q.executeUpdate();
            em.flush();
        }
    }

    @Override
    public void acceptFreelancer(int jobId, int freelancerId) {
        Query q = em.createNamedQuery("JobOffer.findByJobId&FreelancerId");
        q.setParameter("jobId", jobId);
        q.setParameter("freelancerId", freelancerId);

            JobOffer offer = (JobOffer) q.getSingleResult();
            offer.setStatus("accepted");
            offer.getJob().setStatus("closed");
            int id = ((Integer) em.createNamedQuery("Payments.getHighestID").getSingleResult()) + 1;
            Payments pay = new Payments(id);
            pay.setFreelancerId(offer.getFreelancer());
            pay.setJobId(offer.getJob());
            pay.setProviderId(offer.getJob().getProviderId());
            pay.setAmount(offer.getJob().getPayment());
            pay.setDate(new Date());
            em.merge(offer);
            em.persist(pay);
            em.flush();
//        }catch(Exception e){
//            System.err.println(e.toString());
//        }
    }

    @Override
    public Collection<Job> getJobsByProviderId(int providerId) {
        em.flush();
        Query q = em.createNamedQuery("Provider.findById");
        q.setParameter("id", providerId);
        return ((Provider)q.getSingleResult()).getJobCollection();
    }
    
    
    

}
