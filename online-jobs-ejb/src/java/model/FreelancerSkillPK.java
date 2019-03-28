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
public class FreelancerSkillPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "FREELANCER_ID")
    private int freelancerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SKILL")
    private String skill;

    public FreelancerSkillPK() {
    }

    public FreelancerSkillPK(int freelancerId, String skill) {
        this.freelancerId = freelancerId;
        this.skill = skill;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) freelancerId;
        hash += (skill != null ? skill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FreelancerSkillPK)) {
            return false;
        }
        FreelancerSkillPK other = (FreelancerSkillPK) object;
        if (this.freelancerId != other.freelancerId) {
            return false;
        }
        if ((this.skill == null && other.skill != null) || (this.skill != null && !this.skill.equals(other.skill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FreelancerSkillPK[ freelancerId=" + freelancerId + ", skill=" + skill + " ]";
    }
    
}
