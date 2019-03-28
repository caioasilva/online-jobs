package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Job;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-28T18:45:30")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, Date> date;
    public static volatile SingularAttribute<Log, Job> jobId;
    public static volatile SingularAttribute<Log, String> description;
    public static volatile SingularAttribute<Log, Integer> id;
    public static volatile SingularAttribute<Log, User> userId;

}