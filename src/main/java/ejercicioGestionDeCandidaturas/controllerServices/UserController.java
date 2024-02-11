/*
KISS: Keep It Simple, Stupid.

userService
    addOrUpdate()
        DAOimpl
            crear
            actualizar
 */
package ejercicioGestionDeCandidaturas.controllerServices;

import ejercicioGestionDeCandidaturas.implementations.UserDAOimpl;
import ejercicioGestionDeCandidaturas.modelPojo.*;

import java.util.*;

public class UserController {
    private static final UserDAOimpl userDAOimpl= new UserDAOimpl();


    //metodos
    public static User createUser(String name, String mail, String description, int telephone){
        User user = new User(name, mail, description, telephone);
        return user;
    }

    public static void createUser(User user){
        userDAOimpl.createUser(user);
    }

    public static void removeUser(User user){
        userDAOimpl.removeUser(user);
    }

    public static void printUserInfo(User user) {
        System.out.println("User info: ");
        System.out.println("\tID: " + user.getId());
        System.out.println("\tName: " + user.getName());
        System.out.println("\tMail: " + user.getMail());
        System.out.println("\tDescription: " + user.getDescription());
        System.out.println("\tTelephone: " + user.getTelephone());
    }

    public static void addCandidature(User user, JobOffer jobOffer){

    }

    public static void addCandidature(User user, JobOffer jobOffer, String nose, String nose2) {

    }

    public static void addAcademicInfo(User user, AcademicInfo academicInfo, Institution institution) {

    }

    public static void addJobExperience(User user, LaboralExperience laboralExperience) {

    }

    public static void addSkill(User user, Skill skill) {

    }

    public static void addCandidature(User user, Candidature candidature, JobOffer jobOffer) {

    }

    public static void addCandidature(User user, Candidature candidature) {

    }

    public static volid addAcademicInfo(User user, AcademicInfo academicInfo) {

    }

    public static void addSkill(User user, String nose) {

    }

    public static void addJobExperience(User user, LaboralExperience laboralExperience, Company company) {

    }
}