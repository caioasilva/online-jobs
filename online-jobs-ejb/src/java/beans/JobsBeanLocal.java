/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Local;
import model.JobDescription;

/**
 *
 * @author caio
 */
@Local
public interface JobsBeanLocal {
    List<JobDescription> getAllJobs();
    List<JobDescription> getJobsDescLimit(int start, int quant);
    JobDescription getJobById(int id);

    List<JobDescription> getJobsByKeywords(List<String> keywords);
}
