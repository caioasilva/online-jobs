/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caio
 */
@Entity
@Table(name = "FREELANCERS_SKILLS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FreelancerSkill.findAll", query = "SELECT f FROM FreelancerSkill f")
    , @NamedQuery(name = "FreelancerSkill.findByFreelancerId", query = "SELECT f FROM FreelancerSkill f WHERE f.freelancerSkillPK.freelancerId = :freelancerId")
    , @NamedQuery(name = "FreelancerSkill.findBySkill", query = "SELECT f FROM FreelancerSkill f WHERE f.freelancerSkillPK.skill = :skill")
    , @NamedQuery(name = "FreelancerSkill.deleteByFreelancerId", query = "DELETE FROM FreelancerSkill s WHERE s.freelancerSkillPK.freelancerId=:freelancerId")})
public class FreelancerSkill implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FreelancerSkillPK freelancerSkillPK;
    @JoinColumn(name = "FREELANCER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Freelancer freelancer;

    public FreelancerSkill() {
    }

    public FreelancerSkill(FreelancerSkillPK freelancerSkillPK) {
        this.freelancerSkillPK = freelancerSkillPK;
    }

    public FreelancerSkill(int freelancerId, String skill) {
        this.freelancerSkillPK = new FreelancerSkillPK(freelancerId, skill);
    }

    public FreelancerSkillPK getFreelancerSkillPK() {
        return freelancerSkillPK;
    }

    public void setFreelancerSkillPK(FreelancerSkillPK freelancerSkillPK) {
        this.freelancerSkillPK = freelancerSkillPK;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }
    
    public String getSkill(){
        return freelancerSkillPK.getSkill();
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (freelancerSkillPK != null ? freelancerSkillPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FreelancerSkill)) {
            return false;
        }
        FreelancerSkill other = (FreelancerSkill) object;
        if ((this.freelancerSkillPK == null && other.freelancerSkillPK != null) || (this.freelancerSkillPK != null && !this.freelancerSkillPK.equals(other.freelancerSkillPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FreelancerSkill[ freelancerSkillPK=" + freelancerSkillPK + " ]";
    }
    
}
