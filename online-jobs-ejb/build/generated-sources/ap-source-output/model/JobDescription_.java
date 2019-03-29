package model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-28T23:56:15")
@StaticMetamodel(JobDescription.class)
public class JobDescription_ { 

    public static volatile SingularAttribute<JobDescription, Integer> providerId;
    public static volatile SingularAttribute<JobDescription, String> name;
    public static volatile SingularAttribute<JobDescription, String> description;
    public static volatile SingularAttribute<JobDescription, BigDecimal> payment;
    public static volatile SingularAttribute<JobDescription, Integer> id;
    public static volatile SingularAttribute<JobDescription, String> title;
    public static volatile SingularAttribute<JobDescription, Date> creationDate;
    public static volatile SingularAttribute<JobDescription, String> status;

}