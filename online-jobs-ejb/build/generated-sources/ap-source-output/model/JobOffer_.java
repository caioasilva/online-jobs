package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Freelancer;
import model.Job;
import model.JobOfferPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-28T18:45:30")
@StaticMetamodel(JobOffer.class)
public class JobOffer_ { 

    public static volatile SingularAttribute<JobOffer, JobOfferPK> jobOfferPK;
    public static volatile SingularAttribute<JobOffer, Job> job;
    public static volatile SingularAttribute<JobOffer, Freelancer> freelancer;
    public static volatile SingularAttribute<JobOffer, String> status;

}