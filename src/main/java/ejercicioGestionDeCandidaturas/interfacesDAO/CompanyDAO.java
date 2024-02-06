package ejercicioGestionDeCandidaturas.interfacesDAO;

import ejercicioGestionDeCandidaturas.modelPojo.*;

import java.util.List;

public interface CompanyDAO {
    public void createCompany(Company company);
    public void updateCompany(Company company);
    public void removeCompany(Company company);
    public Company getCompany(Long idCompany);
    public Company getCompanyByName(String companyName);
    public List<JobOffer> getJobOffers(Company company);
    public List<Candidature> getCandidaturesByJobOffer(Company company, JobOffer jobOffer);
}
