package ejercicioGestionDeCandidaturas.interfacesDAO;

import ejercicioGestionDeCandidaturas.enumerations.WorkDayType;
import ejercicioGestionDeCandidaturas.modelPojo.Candidature;
import ejercicioGestionDeCandidaturas.modelPojo.Company;
import ejercicioGestionDeCandidaturas.modelPojo.JobOffer;
import ejercicioGestionDeCandidaturas.modelPojo.Skill;

import java.util.*;

public interface JobOfferDAO {
    public void createJobOffer(JobOffer jobOffer);
    public void updateJobOffer(JobOffer jobOffer);
    public void removeJobOffer(JobOffer jobOffer);
    public JobOffer getJobOffer(Long idJobOffer);
    public List<JobOffer> getJobOfferByTitle(String title);
    public List<JobOffer> getJobOfferBySalary(int salary);
    public List<JobOffer> getJobOfferByLocation(String location);
    public List<JobOffer> getJobOfferBySkill(String nameSkill);
    public List<JobOffer> getJobOfferBySkill(Skill skill);
    public List<Candidature> getJobOfferCandidatures(JobOffer jobOffer);
    public List<JobOffer> getJobOfferByCompany(Company company);
    public List<JobOffer> getJobOffersByWorkDayType(WorkDayType codigoType);
}
