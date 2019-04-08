/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.User;

/**
 *
 * @author caio
 */
@Named(value = "adminBean")
@RequestScoped
public class AdminBean {

    @EJB
    private UsersBeanLocal usersBean;

    String usertype;
    String name;
    String username;
    String password;
    String email;
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void createUser() {
        if (usersBean.createUser(usertype.charAt(0), username, password, name, email) != null) {
            msg = "Success!";
        } else {
            msg = "Error creating user";
        }
    }

        
    public List<User> getAllUsers(){
        return usersBean.getAllUsers();
    }
    
    public void removeUser(int id){
        usersBean.removeUser(id);
    }
    /**
     * Creates a new instance of AdminBean
     */
    public AdminBean() {
    }

}
