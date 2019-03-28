/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caio
 */
@Entity
@Table(name = "JOBS_DESCRIPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobDescription.findAll", query = "SELECT j FROM JobDescription j")
    , @NamedQuery(name = "JobDescription.findAllOrderByDate", query = "SELECT j FROM JobDescription j ORDER BY j.creationDate DESC")
    , @NamedQuery(name = "JobDescription.findById", query = "SELECT j FROM JobDescription j WHERE j.id = :id")
    , @NamedQuery(name = "JobDescription.findByProviderId", query = "SELECT j FROM JobDescription j WHERE j.providerId = :providerId")
    , @NamedQuery(name = "JobDescription.findByName", query = "SELECT j FROM JobDescription j WHERE j.name = :name")
    , @NamedQuery(name = "JobDescription.findByTitle", query = "SELECT j FROM JobDescription j WHERE j.title = :title")
    , @NamedQuery(name = "JobDescription.findByStatus", query = "SELECT j FROM JobDescription j WHERE j.status = :status")
    , @NamedQuery(name = "JobDescription.findByPayment", query = "SELECT j FROM JobDescription j WHERE j.payment = :payment")
    , @NamedQuery(name = "JobDescription.findByCreationDate", query = "SELECT j FROM JobDescription j WHERE j.creationDate = :creationDate")})
public class JobDescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @NotNull
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROVIDER_ID")
    private int providerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NAME")
    private String name;
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

    public JobDescription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
}
