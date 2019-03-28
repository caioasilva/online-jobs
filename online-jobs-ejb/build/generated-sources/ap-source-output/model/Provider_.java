package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Job;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-28T18:45:30")
@StaticMetamodel(Provider.class)
public class Provider_ { 

    public static volatile CollectionAttribute<Provider, Job> jobCollection;
    public static volatile SingularAttribute<Provider, String> name;
    public static volatile SingularAttribute<Provider, Integer> id;
    public static volatile SingularAttribute<Provider, User> userId;

}