package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Freelancer;
import model.Log;
import model.Provider;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-28T23:56:15")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile CollectionAttribute<User, Freelancer> freelancerCollection;
    public static volatile CollectionAttribute<User, Provider> providerCollection;
    public static volatile CollectionAttribute<User, Log> logCollection;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, Character> type;
    public static volatile SingularAttribute<User, String> username;

}