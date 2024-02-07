package ejercicioGestionDeCandidaturas.implementations;

import ejercicioGestionDeCandidaturas.enumerations.WorkDayType;
import ejercicioGestionDeCandidaturas.interfacesDAO.JobOfferDAO;
import ejercicioGestionDeCandidaturas.modelPojo.*;
import ejercicioGestionDeCandidaturas.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JobOfferDAOimpl implements JobOfferDAO {
    @Override
    public void createJobOffer(JobOffer jobOffer) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.persist(jobOffer);  //esto es como hacer un insert para insertar el libro a la tabla.
            System.out.println("JobOffer con título \"" + jobOffer.getTitle() + "\" insertado.");

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR al añadir el JobOffer \"" + jobOffer.getTitle() + "\".");
        }
    }

    @Override
    public void updateJobOffer(JobOffer jobOffer) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.merge(jobOffer);  //esto es como hacer un update para actualizar el producto a la tabla.
            System.out.println("JobOffer con id \"" + jobOffer.getId() + "\" actualizado.");

            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR actualizando el JobOffer \"" + jobOffer.getTitle() + "\".");
        }
    }

    @Override
    public void removeJobOffer(JobOffer jobOffer) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();  //inicializamos la transacción.

            session.remove(jobOffer);  //si es distinto de null (existe el estudiante) que elimine el estudiante.
            System.out.println("JobOffer con título \"" + jobOffer.getTitle() + "\" eliminado.");

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR eliminando el JobOffer \"" + jobOffer.getTitle() + "\".");
        }
    }

    @Override
    public JobOffer getJobOffer(Long idJobOffer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<JobOffer> query = builder.createQuery(JobOffer.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<JobOffer> jobOffersTable = query.from(JobOffer.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, name, mail, description, telephone)
            from job_offers
            where id = "id_JobOffer";
             */
            query.select(jobOffersTable).where(builder.equal(jobOffersTable.get("id"), idJobOffer));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<JobOffer> getJobOfferByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<JobOffer> query = builder.createQuery(JobOffer.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<JobOffer> jobOffersTable = query.from(JobOffer.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, title, detail, location, workday_type, open, min_salary, max_salary, required_candidates, skills, candidatures, company)
            from job_offers
            where title like %jobOffer_title%;
             */
            query.select(jobOffersTable).where(builder.like(jobOffersTable.get("title"), "%" + title + "%"));

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<JobOffer> getJobOfferBySalary(int salary) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<JobOffer> query = builder.createQuery(JobOffer.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<JobOffer> jobOffersTable = query.from(JobOffer.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, title, detail, location, workday_type, open, min_salary, max_salary, required_candidates, skills, candidatures, company)
            from job_offers
            where id = "JobOffer_id";
             */
            query.select(jobOffersTable).where(builder.equal(jobOffersTable.get("min_salary"), salary));

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<JobOffer> getJobOfferByLocation(String location) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<JobOffer> query = builder.createQuery(JobOffer.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.
            
            Root<JobOffer> jobOffersTable = query.from(JobOffer.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, title, detail, location, workday_type, open, min_salary, max_salary, required_candidates, skills, candidatures, company)
            from job_offers
            where location = "jobOffer_location";
             */
            query.select(jobOffersTable).where(builder.equal(jobOffersTable.get("location"), location));

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<JobOffer> getJobOfferBySkill(String nameSkill) {

    }

    @Override
    public List<JobOffer> getJobOfferBySkill(Skill skill) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<JobOffer> query = builder.createQuery(JobOffer.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<JobOffer> JobOffersTable = query.from(JobOffer.class);  //se utilza para ver de que clase sacamos la información.
            Join<JobOffer, Skill> skillsTable = JobOffersTable.join("skills");  //esta tabla que devuelve es de la tabla que hacemos el join.

            /*
            select * (id, title, detail, location, workday_type, open, min_salary, max_salary, required_candidates, skills, candidatures, company)
            from job_offers
            inner join job_offers_skills
            on id = skill_id
            inner join skills
            on id = job_offer_id;
             */
            query.select(JobOffersTable).where(builder.equal(skillsTable, skill));  //donde tengan el mismo id coge todas las "skill" y las devuelve en una lista.

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<JobOffer> getJobOfferByCandidature(String candidature) {

    }

    @Override
    public List<Candidature> getJobOfferCandidatures(JobOffer jobOffer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<Candidature> query = builder.createQuery(Candidature.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<Candidature> candidaturesTable = query.from(Candidature.class);  //se utilza para ver de que clase sacamos la información.
            Join<Candidature, JobOffer> jobOfferTable = candidaturesTable.join("job_offers");  //esta tabla que devuelve es de la tabla que hacemos el join.

            /*
            select * (id, cv_path, cover_letter_path, status, user, jobOffer)
            from candidature
            inner join job_offers
            on id = job_offer_id;
             */
            query.select(candidaturesTable).where(builder.equal(jobOfferTable, jobOffer));  //donde tengan el mismo id coge todas las "skill" y las devuelve en una lista.

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<JobOffer> getJobOfferByCompany(Company company) {
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
    public List<JobOffer> getJobOffersByWorkDayType(WorkDayType codigoType) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<JobOffer> query = builder.createQuery(JobOffer.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<JobOffer> jobOffersTable = query.from(JobOffer.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, title, detail, location, workday_type, open, min_salary, max_salary, required_candidates, skills, candidatures, company)
            from job_offers
            where workday_type = "codigoType";
             */
            query.select(jobOffersTable).where(builder.equal(jobOffersTable.get("workday_type"), codigoType));

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }
}
