/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author caio
 */
@Embeddable
public class JobOfferPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "JOB_ID")
    private int jobId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FREELANCER_ID")
    private int freelancerId;

    public JobOfferPK() {
    }

    public JobOfferPK(int jobId, int freelancerId) {
        this.jobId = jobId;
        this.freelancerId = freelancerId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) jobId;
        hash += (int) freelancerId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobOfferPK)) {
            return false;
        }
        JobOfferPK other = (JobOfferPK) object;
        if (this.jobId != other.jobId) {
            return false;
        }
        if (this.freelancerId != other.freelancerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.JobOfferPK[ jobId=" + jobId + ", freelancerId=" + freelancerId + " ]";
    }
    
}
