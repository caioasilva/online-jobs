/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Job;
import model.Log;
import model.User;

/**
 *
 * @author caio
 */
@JMSDestinationDefinition(name = "java:app/LogQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "LogQueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/LogQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class LogBean implements MessageListener {

    @PersistenceContext(unitName = "online-jobs-ejbPU")
    private EntityManager em;

    public LogBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            int userId = message.getIntProperty("userId");
            int jobId = message.getIntProperty("jobId");
            String description = message.getStringProperty("description");
            int id;
            try {
                id = ((Integer) em.createNamedQuery("Log.getHighestID").getSingleResult()) + 1;
            } catch (Exception e) {
                id = 1;
            }
            Log log = new Log(id);
            log.setDate(new Date());
            log.setDescription(description);
            log.setJobId((Job)em.createNamedQuery("Job.findById").setParameter("id", jobId).getSingleResult());
            log.setUserId((User)em.createNamedQuery("User.findById").setParameter("id", userId).getSingleResult());
            em.persist(log);
            em.flush();
            
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
