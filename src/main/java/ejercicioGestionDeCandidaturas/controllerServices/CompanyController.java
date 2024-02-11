package ejercicioGestionDeCandidaturas.controllerServices;

import ejercicioGestionDeCandidaturas.implementations.CompanyDAOimpl;
import ejercicioGestionDeCandidaturas.modelPojo.Company;
import ejercicioGestionDeCandidaturas.modelPojo.JobOffer;

public class CompanyController {
    private static final CompanyDAOimpl companyDAOimpl = new CompanyDAOimpl();


    //metodos
    public static void createCompany(Company company) {
        companyDAOimpl.createCompany(company);
    }

    public static Company createCompany(String name, String description) {
        Company company = new Company(name, description);
        return company;
    }

    public static Company createCompany(String nose) {

    }

    public static void removeCompany(Company company) {
        companyDAOimpl.removeCompany(company);
    }

    public static void printCompanyInfo(Company company) {
        System.out.println("Company info: ");
        System.out.println("\tID: " + company.getId());
        System.out.println("\tName: " + company.getName());
        System.out.println("\tDescription: " + company.getDescription());
    }

    public static void addJobOffer(Company company, JobOffer jobOffer) {
        company.setJobOffers(jobOffer);
    }
}
