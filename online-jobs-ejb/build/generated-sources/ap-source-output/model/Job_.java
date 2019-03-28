package model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.JobKeyword;
import model.JobOffer;
import model.Log;
import model.Provider;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-28T18:45:30")
@StaticMetamodel(Job.class)
public class Job_ { 

    public static volatile SingularAttribute<Job, Provider> providerId;
    public static volatile SingularAttribute<Job, String> description;
    public static volatile CollectionAttribute<Job, JobKeyword> jobKeywordCollection;
    public static volatile SingularAttribute<Job, BigDecimal> payment;
    public static volatile CollectionAttribute<Job, Log> logCollection;
    public static volatile SingularAttribute<Job, Integer> id;
    public static volatile SingularAttribute<Job, String> title;
    public static volatile SingularAttribute<Job, Date> creationDate;
    public static volatile CollectionAttribute<Job, JobOffer> jobOfferCollection;
    public static volatile SingularAttribute<Job, String> status;

}