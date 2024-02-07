package ejercicioGestionDeCandidaturas.implementations;

import ejercicioGestionDeCandidaturas.interfacesDAO.UserDAO;
import ejercicioGestionDeCandidaturas.modelPojo.AcademicInfo;
import ejercicioGestionDeCandidaturas.modelPojo.LaboralExperience;
import ejercicioGestionDeCandidaturas.modelPojo.Skill;
import ejercicioGestionDeCandidaturas.modelPojo.User;
import ejercicioGestionDeCandidaturas.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOimpl implements UserDAO {
    public void createUser(User user) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.persist(user);  //esto es como hacer un insert para insertar el libro a la tabla.
            System.out.println("User con nombre \"" + user.getName() + "\" insertado.");

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR al añadir el User \"" + user.getName() + "\".");
        }
    }

    @Override
    public void updateUser(User user) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.merge(user);  //esto es como hacer un update para actualizar el producto a la tabla.
            System.out.println("User con id \"" + user.getId() + "\" actualizado.");

            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR actualizando el user \"" + user.getName() + "\".");
        }
    }

    @Override
    public void removeUser(User user) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();  //inicializamos la transacción.

            session.remove(user);  //si es distinto de null (existe el estudiante) que elimine el estudiante.
            System.out.println("User con nombre \"" + user.getName() + "\" eliminado.");

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR eliminando el user \"" + user.getName() + "\".");
        }
    }

    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<User> query = builder.createQuery(User.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<User> usersTable = query.from(User.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, name, mail, description, telephone)
            from users;
             */
            query.select(usersTable);

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public User getUser(Long idUser) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<User> query = builder.createQuery(User.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<User> usersTable = query.from(User.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, name, mail, description, telephone)
            from users
            where id = "id_user";
             */
            query.select(usersTable).where(builder.equal(usersTable.get("id"), idUser));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public User getUserByName(String userName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<User> query = builder.createQuery(User.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<User> usersTable = query.from(User.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, name, mail, description, telephone)
            from users
            where name = "user_name";
             */
            query.select(usersTable).where(builder.equal(usersTable.get("name"), userName));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<User> getUsersByName(String userName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<User> query = builder.createQuery(User.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<User> usersTable = query.from(User.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, name, mail, description, telephone)
            from users
            where name like %user_name%;
             */
            query.select(usersTable).where(builder.like(usersTable.get("name"), "%" + userName + "%"));

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<Skill> getUserSkills(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<Skill> query = builder.createQuery(Skill.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<Skill> skillsTable = query.from(Skill.class);  //se utilza para ver de que clase sacamos la información.
            Join<Skill, User> usersTable = skillsTable.join("users");  //esta tabla que devuelve es de la tabla que hacemos el join.

            /*
            select * (id, name, users, jobOffers)
            from skills
            inner join user_skills
            on id = skill_id
            inner join users
            on id = user_id;
             */
            query.select(skillsTable).where(builder.equal(usersTable, user));  //donde tengan el mismo id coge todas las "Skills" y las devuelve en una lista.

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<LaboralExperience> getUserLaboralExperience(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<LaboralExperience> query = builder.createQuery(LaboralExperience.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<LaboralExperience> laboralExperiencesTable = query.from(LaboralExperience.class);  //se utilza para ver de que clase sacamos la información.
            Join<LaboralExperience, User> usersTable = laboralExperiencesTable.join("users");  //esta tabla que devuelve es de la tabla que hacemos el join.

            /*
            select * (id, job_title, init_date, end_date, current, descripcion, localtion, users, company)
            from laboral_experiences
            inner join users
            on id = user_id;
             */
            query.select(laboralExperiencesTable).where(builder.equal(usersTable, user));  //donde tengan el mismo id coge todas las "LaboralExperience" y las devuelve en una lista.

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public List<AcademicInfo> getUserAcademicInfo(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<AcademicInfo> query = builder.createQuery(AcademicInfo.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.
            
            Root<AcademicInfo> academicInfoTable = query.from(AcademicInfo.class);  //se utilza para ver de que clase sacamos la información.
            Join<AcademicInfo, User> usersTable = academicInfoTable.join("users");  //esta tabla que devuelve es de la tabla que hacemos el join.

            /*
            select * (id, title, init_date, end_date, current, mean_score, user, institution)
            from academic_info
            inner join users
            on id = user_id;
             */
            query.select(academicInfoTable).where(builder.equal(usersTable, user));  //donde tengan el mismo id coge todas los "AcademicInfo" y las devuelve en una lista.

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }
}
