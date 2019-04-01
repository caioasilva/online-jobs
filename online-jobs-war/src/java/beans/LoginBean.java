/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Freelancer;
import model.FreelancerSkill;
import model.Provider;
import model.User;
import sun.misc.IOUtils;

/**
 *
 * @author caio
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private FreelancersBeanLocal freelancersBean;

    @EJB
    private UsersBeanLocal usersBean;

    boolean loggedIn = false;

    private User user;
    private String username;
    private String password;
    private String error = "";
    private Part uploadedFile;

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getUser() {
        return user;
    }

    public String getError() {
        return error;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSkillsString() {
        List<FreelancerSkill> skills = freelancersBean.getFreelancerSkillsById(user.getFreelancer().getId());
        String res = skills.stream().map((f) -> f.getSkill()).collect(Collectors.joining(","));
        return res;
    }

    public void setSkillsString(String skills) {
        String[] sk = skills.split(",");
        List<FreelancerSkill> l_skills = new ArrayList();
        for (String s : sk) {
            FreelancerSkill f = new FreelancerSkill(user.getFreelancer().getId(), s);
            l_skills.add(f);
        }
        freelancersBean.updateFreelancerSkills(user.getFreelancer().getId(), l_skills);
    }

    public String login() throws IOException {
        user = usersBean.getUser(username, password);
        if (user != null) {
            loggedIn = true;
            error = "Logged";

            return "/pages/index.xhtml?faces-redirect=true";
        } else {
            error = "Invalid Username/Password combination";
            return "";
        }

    }

    public String logout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
//        error = "Logged Out Succesfully!";
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
        HttpSession session = (HttpSession) ectx.getSession(false);
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }

    public void updateUser() {
        if (uploadedFile != null) {
            saveFile();
        }
        user = usersBean.updateUser(user);
    }

    public void saveFile() {
        if (uploadedFile.getSize() > 0 && uploadedFile.getSize() <= 524288) {
            try (InputStream input = uploadedFile.getInputStream()) {
                boolean a = false;
                user.getFreelancer().setImage(IOUtils.readFully(input, (int) uploadedFile.getSize(), a));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
