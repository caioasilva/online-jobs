package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-28T18:45:30")
@StaticMetamodel(Freelancer.class)
public class Freelancer_ { 

    public static volatile SingularAttribute<Freelancer, String> role;
    public static volatile SingularAttribute<Freelancer, String> name;
    public static volatile SingularAttribute<Freelancer, Integer> id;
    public static volatile SingularAttribute<Freelancer, String> message;
    public static volatile SingularAttribute<Freelancer, User> userId;
    public static volatile SingularAttribute<Freelancer, String> email;

}