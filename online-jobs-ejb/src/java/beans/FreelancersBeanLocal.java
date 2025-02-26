/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import model.Freelancer;
import model.FreelancerSkill;

/**
 *
 * @author caio
 */
@Local
public interface FreelancersBeanLocal {
    List<Freelancer> getFreelancersLimit(int start, int quant);

    Freelancer getFreelancerById(int id);

    List<FreelancerSkill> getFreelancerSkillsById(int id);
    
    void updateFreelancerSkills(int id, List<FreelancerSkill> skills);
    
    BigDecimal getSumByFreelancerId(int FreelancerId);
   
}
