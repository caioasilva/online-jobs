/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.JobsBeanLocal;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import model.Job;

/**
 *
 * @author caio
 */
@Named(value = "searchBean")
@RequestScoped
public class SearchBean {

    @EJB
    private JobsBeanLocal jobsBean;

    private String query = "";

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private List<String> keywords;

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    private List<Job> results;

    public List<Job> getResults() {
        return results;
    }

    /**
     * Creates a new instance of JobBean
     */
    public SearchBean() {
    }

    public void init() throws IOException {
//        user = userService.find(id);
//        job = jobsBean.getJobById(id);
//        String aaa = "aaa aa, a";
        if (!query.isEmpty()) {
            this.keywords = Arrays.asList(this.query.split("[ ,]+"));
            boolean isId = false;
            if (keywords.size() == 1) {
                try {
                    int id = Integer.parseInt(keywords.get(0));
                    if (jobsBean.getJobById(id) != null) {
                        isId = true;
                    }
                } catch (NumberFormatException e) {
                    isId = false;
                }
            }
            if (isId) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("job.xhtml?id=".concat(keywords.get(0)));
            }

            results = jobsBean.getJobsByKeywords(keywords);

        } else {
            results = jobsBean.getAllOpenJobs();
        }
    }

}
