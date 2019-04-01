/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Local;
import model.Job;

/**
 *
 * @author caio
 */
@Local
public interface JobsBeanLocal {
    List<Job> getAllJobs();
    List<Job> getJobsDescLimit(int start, int quant);
    Job getJobById(int id);

    List<Job> getJobsByKeywords(List<String> keywords);
}
