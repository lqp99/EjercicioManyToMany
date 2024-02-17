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
    public User createUser(String name, String mail, String description, int telephone){
        User user = new User(name, mail, description, telephone);  //creamos el user pasandole todos los datos por el constructor.

        userDAOimpl.createUser(user);  //creamos el usuario.

        return user;  //devolvemos el user.
    }

    public void createUser(User user){
        userDAOimpl.createUser(user);
    }

    public void removeUser(User user){
        userDAOimpl.removeUser(user);
    }

    public void printUserInfo(User user) {
        System.out.println("User info: ");
        System.out.println("\tID: " + user.getId());
        System.out.println("\tName: " + user.getName());
        System.out.println("\tMail: " + user.getMail());
        System.out.println("\tDescription: " + user.getDescription());
        System.out.println("\tTelephone: " + user.getTelephone());
        System.out.println("\tSkills: "+ user.getSkills());
        System.out.println("\tCandidatures: "+ user.getCandidatures());
        System.out.println("\tLaboral Experiences: "+ user.getLaboralExperiences());
        System.out.println("\tAcademic Info: "+ user.getAcademicInfos());
    }

    public void addCandidature(User user, JobOffer jobOffer){
        Candidature candidature = new Candidature();  //creamos una nueva candidature.

        candidature.setUser(user);  //setteamos el user.
        candidature.getJobOffers().add(jobOffer);  //en candidature, cogemos la lista que tiene de JobOffers y añadimos la que nos pasan.

        user.getCandidatures().add(candidature);  //en user, cogemos la lista que tiene de candidatures y añadimos la que hemos creado.

        jobOffer.setCandidature(candidature);  //setteamos la candidature.

        userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
    }

    public void addCandidature(User user, JobOffer jobOffer, String cv, String cover_letter) {
        Candidature candidature = new Candidature(cv, cover_letter);  //creo una nueva candidature con datos.

        candidature.setUser(user);  //setteamos el user.
        candidature.getJobOffers().add(jobOffer);  //en candidature, cogemos la lista que tiene de jobOffers y añadimos el que nos pasan.

        user.getCandidatures().add(candidature);  //en user, cogemos la lista que tiene de candidatures y añadimos la nueva candidature que hemos creado.

        jobOffer.setCandidature(candidature);  //setteamos la candidature.

        userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
    }


    public void addAcademicInfo(User user, AcademicInfo academicInfo, Institution institution) {
        user.getAcademicInfos().add(academicInfo);  //en user, cogemos la lista que tiene de academicInfos y añadimos la academicInfo que nos pasan.

        academicInfo.setUser(user);  //setteamos el user.
        academicInfo.setInstitution(institution);  //setteamos la institution.

        institution.getUsers().add(user);  //en institution, cogemos la lista que tiene de users y añadimos el user que nos pasan.
        institution.getAcademicInfos().add(academicInfo);  //en institution, cogemos la lista que tiene de academicInfos y añadimos la academicInfo que nos pasan.

        userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
    }

    public void addSkill(User user, String skillName) {
        Skill skill = new Skill(skillName);  //creo una skill.
        List<Skill> skills = userDAOimpl.getUserSkills(user);  //creo una List con todas las skills que tiene el usuario.

        if (!skills.contains(skill)) {  //si la lista de skills no contiene la Skill que le pasamos....
            user.getSkills().add(skill);  //en user, cogemos la lista que tiene de skills y añadimos la skill que hemos creado.
            skill.getUsers().add(user);  //en skill, cogemos la lista que tiene de users y añadimos el user que nos pasan.
            userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
        }

//        for (Skill s : skills) {  //nos recorremos esa List.
//            if (s.getId() != skill.getId()) {  //si el id de la skill que hemos creado es distinta a alguna de las que hay en la lista
//                user.getSkills().add(skill);  //en user, cogemos la lista que tiene de skills y añadimos la skill que hemos creado.
//                skill.getUsers().add(user);  //en skill, cogemos la lista que tiene de users y añadimos el user que nos pasan.
//            }
//        }

    }

    public void addSkill(User user, Skill skill) {
        user.getSkills().add(skill);  //en user, cogemos la lista que tiene de skills y añadimos la skill que nos pasan.

        skill.getUsers().add(user);  //en skill, cogemos la lista que tiene de users y añadimos el user que nos pasan.

        userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
    }

    public void addCandidature(User user, Candidature candidature, JobOffer jobOffer) {
        user.getCandidatures().add(candidature);  //en user, cogemos la lista que tiene de candidatures y añadimos la candidature que nos pasan.

        candidature.setUser(user);  //setteamos el user.
        candidature.getJobOffers().add(jobOffer);  //en candidature, cogemos la lista que tiene de jobOffers y añadimos el jobOffer que nos pasan.

        jobOffer.setCandidature(candidature);  //setteamos la candidature.

        userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
    }

    public void addCandidature(User user, Candidature candidature) {
        user.getCandidatures().add(candidature);  //en user, cogemos la lista que tiene de candidatures y añadimos la candidature que nos pasan.

        candidature.setUser(user);  //setteamos el user.

        userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
    }

    public void addAcademicInfo(User user, AcademicInfo academicInfo) {
        user.getAcademicInfos().add(academicInfo);  //en user, cogemos la lista que tiene de academicInfos y añadimos la academicInfo que nos pasan.

        academicInfo.setUser(user);  //setteamos el user.

        userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
    }

    public void addJobExperience(User user, LaboralExperience laboralExperience) {
        user.getLaboralExperiences().add(laboralExperience);  //en user, cogemos la lista que tiene de laboralExperiences y añadimos la laboralExperience que nos pasan.

        laboralExperience.setUser(user);  //setteamos el user.

        userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
    }

    public void addJobExperience(User user, LaboralExperience laboralExperience, Company company) {
        user.getLaboralExperiences().add(laboralExperience);  //en user, cogemos la lista que tiene de laboralExperiences y añadimos la laboralExperience que nos pasan.

        laboralExperience.setUser(user);  //setteamos el user.
        laboralExperience.setCompany(company);  //setteamos la company.

        company.getLaboralExperiences().add(laboralExperience);  //en company, cogemos la lista que tiene de laboralExperiences y añadimos la laboralExperience que nos pasan.

        userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.
    }

    public User getUserByName(String userName) {
        User user = userDAOimpl.getUserByName(userName);
        return user;
    }

    public void getUserSkills(User user) {
        userDAOimpl.getUserSkills(user);
    }

    public void getUserLaboralExperience(User user) {
        userDAOimpl.getUserLaboralExperience(user);
    }
}