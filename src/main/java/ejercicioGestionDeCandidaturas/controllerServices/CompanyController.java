package ejercicioGestionDeCandidaturas.controllerServices;

import ejercicioGestionDeCandidaturas.implementations.CompanyDAOimpl;
import ejercicioGestionDeCandidaturas.implementations.JobOfferDAOimpl;
import ejercicioGestionDeCandidaturas.modelPojo.Company;
import ejercicioGestionDeCandidaturas.modelPojo.JobOffer;

import java.util.ArrayList;

public class CompanyController {
    private static final CompanyDAOimpl companyDAOimpl = new CompanyDAOimpl();


    //metodos
    public static void createCompany(Company company) {
        companyDAOimpl.createCompany(company);
    }

    public Company createCompany(String name, String description) {
        Company company = new Company(name, description);

        companyDAOimpl.createCompany(company);  //añado la empresa a la database.

        return company;  //devolvemos la company.
    }

    public Company createCompany(String name) {
        Company company = new Company();  //creo la compañía.
        company.setName(name);  //setteo el nombre

        companyDAOimpl.createCompany(company);  //añado la empresa a la database.

        return company;  //devolvemos la company.
    }

    public void removeCompany(Company company) {
        companyDAOimpl.removeCompany(company);
    }

    public void printCompanyInfo(Company company) {
        System.out.println("Company info: ");
        System.out.println("\tID: " + company.getId());
        System.out.println("\tName: " + company.getName());
        System.out.println("\tDescription: " + company.getDescription());
        System.out.println("\tLaboral Experiences: " + company.getLaboralExperiences());
        System.out.println("\tJob Offers: " + company.getJobOffers());
    }

    public void addJobOffer(Company company, JobOffer jobOffer) {
        company.getJobOffers().add(jobOffer);  //en company, cogemos la lista de JobOffers y se añade el jobOffer que nos están pasando.
        jobOffer.setCompany(company);  //se settea la compañía.

        companyDAOimpl.updateCompany(company);  //actualizamos la compañía para que se guarden los cambios.
    }
}
