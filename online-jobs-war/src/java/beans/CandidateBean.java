/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.FreelancersBeanLocal;
import beans.JobsBeanLocal;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import model.Freelancer;
import model.FreelancerSkill;
import model.FreelancerSkillPK;

/**
 *
 * @author caio
 */
@Named(value = "candidateBean")
@RequestScoped
public class CandidateBean {

    @EJB
    private FreelancersBeanLocal freelancersBean;



    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    private Freelancer candidate;

    public Freelancer getCandidate() {
        return candidate;
    }

    public void setCandidate(Freelancer candidate) {
        this.candidate = candidate;
    }
    
    private List<FreelancerSkill> skills;

    public List<FreelancerSkill> getSkills() {
        return skills;
    }
    
        public void setSkills(List<FreelancerSkill> skills) {
        this.skills = skills;
    }
    
    public List<Freelancer> getSomeCandidates(){
        return freelancersBean.getFreelancersLimit(0, 4);
    }
    
    /**
     * Creates a new instance of JobBean
     */
    public CandidateBean() {
    }
    
    public void init() {
//        user = userService.find(id);

        candidate = freelancersBean.getFreelancerById(id);
        skills = freelancersBean.getFreelancerSkillsById(id);
//        System.out.println(skills);
          
    }
    
}
