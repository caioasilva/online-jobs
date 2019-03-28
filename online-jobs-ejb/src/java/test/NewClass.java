/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import beans.JobsBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author caio
 */
public class NewClass {

    JobsBeanLocal jobsBean = lookupJobsBeanLocal();
    
    public static void main(String[] args) {
        
    }

    private JobsBeanLocal lookupJobsBeanLocal() {
        try {
            Context c = new InitialContext();
            return (JobsBeanLocal) c.lookup("java:global/online-jobs/online-jobs-ejb/JobsBean!beans.JobsBeanLocal");

        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
