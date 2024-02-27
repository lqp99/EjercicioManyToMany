package ejercicioGestionDeCandidaturas.controllerServices;

import ejercicioGestionDeCandidaturas.impl.CompanyDAOimpl;
import ejercicioGestionDeCandidaturas.modelPojo.Company;
import ejercicioGestionDeCandidaturas.modelPojo.JobOffer;

import java.time.LocalDate;

public class CompanyController {
    private final CompanyDAOimpl companyDAOimpl = new CompanyDAOimpl();


    //metodos
    public void createCompany(Company company) {
        this.companyDAOimpl.createCompany(company);
        System.out.println("Company con nombre \"" + company.getName() + "\" insertada.");
    }

    public Company createCompany(String name, String description) {
        Company company = new Company(name, description);

        this.companyDAOimpl.createCompany(company);  //añado la empresa a la database.
        System.out.println("Company con nombre \"" + company.getName() + "\" insertada.");

        return company;  //devolvemos la company.
    }

    public Company createCompany(String name) {
        Company company = new Company();  //creo la compañía.
        company.setName(name);  //setteo el nombre

        this.companyDAOimpl.createCompany(company);  //añado la empresa a la database.
        System.out.println("Company con nombre \"" + company.getName() + "\" insertada.");

        return company;  //devolvemos la company.
    }

    public Company createCompany(String name, String description, String password) {
        Company company = new Company(name, description, password);

        this.companyDAOimpl.createCompany(company);  //añado la empresa a la database.
        System.out.println("Company con nombre \"" + company.getName() + "\" insertada.");

        return company;  //devolvemos la company.
    }

    public void updateCompany(Company company) {
        this.companyDAOimpl.updateCompany(company);
        System.out.println("Company con nombre \"" + company.getName() + "\" actualizada.");
    }

    public void removeCompany(Company company) {
        this.companyDAOimpl.removeCompany(company);
        System.out.println("Company con nombre \"" + company.getName() + "\" eliminada.");
    }

    public void printCompanyInfo(Company company) {
        System.out.println("Company info: ");
        System.out.println("\tID: " + company.getId());
        System.out.println("\tName: " + company.getName());
        System.out.println("\tDescription: " + company.getDescription());
        System.out.println("\tLaboral Experiences: " + company.getLaboralExperiences());
        System.out.println("\tJob Offers: " + company.getJobOffers());
    }

    public Company login(String companyName, String password) {
        Company company = this.companyDAOimpl.getCompanyByName(companyName);

        if (company != null && company.getPassword().equals(password)){
            company.setLastTimeLogin(LocalDate.now());  //para actualizar la ultima vez que se hizo login a la hora actual.
            return company;
            //return true;
        } else {
            return null;
            //return false;
        }
    }

    public void addJobOffer(Company company, JobOffer jobOffer) {
        company.getJobOffers().add(jobOffer);  //en company, cogemos la lista de JobOffers y se añade el jobOffer que nos están pasando.
        jobOffer.setCompany(company);  //se settea la compañía.

        this.companyDAOimpl.updateCompany(company);  //actualizamos la compañía para que se guarden los cambios.
    }

    public Company getCompanyByName(String companyName) {
        Company company = this.companyDAOimpl.getCompanyByName(companyName);
        return company;
    }
}
