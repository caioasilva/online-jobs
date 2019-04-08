/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author caio
 */
@Entity
@Table(name = "JOBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
    , @NamedQuery(name = "Job.findById", query = "SELECT j FROM Job j WHERE j.id = :id")
    , @NamedQuery(name = "Job.findByTitle", query = "SELECT j FROM Job j WHERE j.title = :title")
    , @NamedQuery(name = "Job.findByStatus", query = "SELECT j FROM Job j WHERE j.status = :status")
    , @NamedQuery(name = "Job.findByPayment", query = "SELECT j FROM Job j WHERE j.payment = :payment")
    , @NamedQuery(name = "Job.findByCreationDate", query = "SELECT j FROM Job j WHERE j.creationDate = :creationDate")
    , @NamedQuery(name = "Job.findAllOrderByDate", query = "SELECT j FROM Job j WHERE j.status = 'open' ORDER BY j.creationDate DESC")
    , @NamedQuery(name = "Job.getHighestID", query = "SELECT MAX(j.id) from Job j")
    , @NamedQuery(name = "Job.deleteById", query = "DELETE FROM Job j WHERE j.id = :id")})
public class Job implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobId")
    private Collection<Payments> paymentsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TITLE")
    private String title;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "STATUS")
    private String status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAYMENT")
    private BigDecimal payment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
    private Collection<JobKeyword> jobKeywordCollection;
    @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Provider providerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
    private Collection<JobOffer> jobOfferCollection;
    @OneToMany(mappedBy = "jobId")
    private Collection<Log> logCollection;

    public Job() {
    }

    public Job(Integer id) {
        this.id = id;
    }

    public Job(Integer id, String title, String status, BigDecimal payment, Date creationDate) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.payment = payment;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @XmlTransient
    public Collection<JobKeyword> getJobKeywordCollection() {
        return jobKeywordCollection;
    }

    public void setJobKeywordCollection(Collection<JobKeyword> jobKeywordCollection) {
        this.jobKeywordCollection = jobKeywordCollection;
    }

    public Provider getProviderId() {
        return providerId;
    }

    public void setProviderId(Provider provider) {
        this.providerId = provider;
    }

    @XmlTransient
    public Collection<JobOffer> getJobOfferCollection() {
        return jobOfferCollection;
    }

    public void setJobOfferCollection(Collection<JobOffer> jobOfferCollection) {
        this.jobOfferCollection = jobOfferCollection;
    }

    @XmlTransient
    public Collection<Log> getLogCollection() {
        return logCollection;
    }

    public void setLogCollection(Collection<Log> logCollection) {
        this.logCollection = logCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Job[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Payments> getPaymentsCollection() {
        return paymentsCollection;
    }

    public void setPaymentsCollection(Collection<Payments> paymentsCollection) {
        this.paymentsCollection = paymentsCollection;
    }
    
}
