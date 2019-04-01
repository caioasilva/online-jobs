/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.Provider;

/**
 *
 * @author caio
 */
@Named(value = "providerBean")
@RequestScoped
public class ProviderBean {

    @EJB
    private ProvidersBeanLocal providersBean;


    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    private Provider provider;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    
    /**
     * Creates a new instance of ProviderBean
     */
    public ProviderBean() {
    }
    
    public void init() {
        provider = providersBean.getProviderById(id);
          
    }
    
}
