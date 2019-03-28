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
import javax.validation.constraints.Size;

/**
 *
 * @author caio
 */
@Embeddable
public class JobKeywordPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "JOB_ID")
    private int jobId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "KEYWORD")
    private String keyword;

    public JobKeywordPK() {
    }

    public JobKeywordPK(int jobId, String keyword) {
        this.jobId = jobId;
        this.keyword = keyword;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) jobId;
        hash += (keyword != null ? keyword.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobKeywordPK)) {
            return false;
        }
        JobKeywordPK other = (JobKeywordPK) object;
        if (this.jobId != other.jobId) {
            return false;
        }
        if ((this.keyword == null && other.keyword != null) || (this.keyword != null && !this.keyword.equals(other.keyword))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.JobKeywordPK[ jobId=" + jobId + ", keyword=" + keyword + " ]";
    }
    
}
