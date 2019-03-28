/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caio
 */
@Entity
@Table(name = "JOBS_OFFERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobOffer.findAll", query = "SELECT j FROM JobOffer j")
    , @NamedQuery(name = "JobOffer.findByJobId", query = "SELECT j FROM JobOffer j WHERE j.jobOfferPK.jobId = :jobId")
    , @NamedQuery(name = "JobOffer.findByFreelancerId", query = "SELECT j FROM JobOffer j WHERE j.jobOfferPK.freelancerId = :freelancerId")
    , @NamedQuery(name = "JobOffer.findByStatus", query = "SELECT j FROM JobOffer j WHERE j.status = :status")})
public class JobOffer implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JobOfferPK jobOfferPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "STATUS")
    private String status;
    @JoinColumn(name = "FREELANCER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Freelancer freelancer;
    @JoinColumn(name = "JOB_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Job job;

    public JobOffer() {
    }

    public JobOffer(JobOfferPK jobOfferPK) {
        this.jobOfferPK = jobOfferPK;
    }

    public JobOffer(JobOfferPK jobOfferPK, String status) {
        this.jobOfferPK = jobOfferPK;
        this.status = status;
    }

    public JobOffer(int jobId, int freelancerId) {
        this.jobOfferPK = new JobOfferPK(jobId, freelancerId);
    }

    public JobOfferPK getJobOfferPK() {
        return jobOfferPK;
    }

    public void setJobOfferPK(JobOfferPK jobOfferPK) {
        this.jobOfferPK = jobOfferPK;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobOfferPK != null ? jobOfferPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobOffer)) {
            return false;
        }
        JobOffer other = (JobOffer) object;
        if ((this.jobOfferPK == null && other.jobOfferPK != null) || (this.jobOfferPK != null && !this.jobOfferPK.equals(other.jobOfferPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.JobOffer[ jobOfferPK=" + jobOfferPK + " ]";
    }
    
}
