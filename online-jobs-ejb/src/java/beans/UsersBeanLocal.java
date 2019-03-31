/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Local;
import model.FreelancerSkill;
import model.User;

/**
 *
 * @author caio
 */
@Local
public interface UsersBeanLocal {

    User getUser(String username, String password);

    User updateUser(User u);

    void updateFreelancerSkills(List<FreelancerSkill> skills);
    
}
