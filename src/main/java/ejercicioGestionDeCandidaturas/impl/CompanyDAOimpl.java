package ejercicioGestionDeCandidaturas.impl;

import ejercicioGestionDeCandidaturas.interfacesDAO.CompanyDAO;
import ejercicioGestionDeCandidaturas.modelPojo.*;
import ejercicioGestionDeCandidaturas.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompanyDAOimpl implements CompanyDAO {
    @Override
    public void createCompany(Company company) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.persist(company);  //esto es como hacer un insert para insertar el libro a la tabla.

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR al añadir la company \"" + company.getName() + "\".");
        }
    }

    @Override
    public void updateCompany(Company company) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.merge(company);  //esto es como hacer un update para actualizar el producto a la tabla.

            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR actualizando la company \"" + company.getName() + "\".");
        }
    }

    @Override
    public void removeCompany(Company company) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();  //inicializamos la transacción.

            session.remove(company);  //si es distinto de null (existe el estudiante) que elimine el estudiante.

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR eliminando la company \"" + company.getName() + "\".");
        }
    }

    @Override
    public Company getCompany(Long idCompany) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<Company> query = builder.createQuery(Company.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<Company> companiesTable = query.from(Company.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, name, description, laboralExperiences, jobOffers)
            from companies
            where id = "id_company";
             */
            query.select(companiesTable).where(builder.equal(companiesTable.get("id"), idCompany));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public Company getCompanyByName(String companyName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<Company> query = builder.createQuery(Company.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<Company> companiesTable = query.from(Company.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, name, description, laboralExperiences, jobOffers)
            from companies
            where companyName = "company_name";
             */
            query.select(companiesTable).where(builder.equal(companiesTable.get("name"), companyName));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<JobOffer> getJobOffers(Company company) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<JobOffer> query = builder.createQuery(JobOffer.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.
            
            Root<JobOffer> jobOffersTable = query.from(JobOffer.class);  //se utilza para ver de que clase sacamos la información.
            Join<JobOffer, Company> companiesTable = jobOffersTable.join("companies");  //esta tabla que devuelve es de la tabla que hacemos el join.

            /*
            select * (id, title, detail, location, workday_type, open, min_salary, max_salary, required_candidates, skills, candidatures, company)
            from job_offers
            inner join companies
            on id = company_id;
             */
            query.select(jobOffersTable).where(builder.equal(companiesTable, company));  //donde tengan el mismo id coge todas los "JobOffer" y las devuelve en una lista.

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<Candidature> getCandidaturesByJobOffer(JobOffer jobOffer, Company company) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<Candidature> query = builder.createQuery(Candidature.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.
            
            Root<Candidature> candidaturesTable = query.from(Candidature.class);  //se utilza para ver de que clase sacamos la información.
            Join<Candidature, JobOffer> jobOfferTables = candidaturesTable.join("job_offers");  //esta tabla que devuelve es de la tabla que hacemos el join.
            Join<JobOffer, Company> companyTable = candidaturesTable.join("companies");  //esta tabla que devuelve es de la tabla que hacemos el join.

            /*
            select * (id, cv_path, cover_letter_path, status, user, jobOffer)
            from candidature
            inner join job_offers
            on id = job_offer_id
            inner join companies
            on id = company_id;
             */
            query.select(candidaturesTable).where(builder.equal(jobOfferTables, jobOffer), builder.equal(companyTable, company));  //donde tengan el mismo id coge todas las "Skills" y las devuelve en una lista.

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }
}
