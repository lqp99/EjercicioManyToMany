package ejercicioGestionDeCandidaturas.gui;

import ejercicioGestionDeCandidaturas.controllerServices.CompanyController;
import ejercicioGestionDeCandidaturas.controllerServices.UserController;
import ejercicioGestionDeCandidaturas.modelPojo.LaboralExperience;
import ejercicioGestionDeCandidaturas.modelPojo.Skill;
import ejercicioGestionDeCandidaturas.modelPojo.User;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        CompanyController companyController = new CompanyController();
        User user1 = new User("Perico", "pericoPec@example.com", "El de los palotes", 666777);
        User user2 = userController.createUser("John Doe", "jdoe@asdf.asdf", "asdf", 123456);
        //LaboralExperience laboralExperience1 = new LaboralExperience("Barrendero", );


        userController.createUser(user1);
        userController.addSkill(user1, "asdf");
        userController.addSkill(user1, new Skill("skill2"));
        userController.addSkill(user2, "asdf");

        //userController.addJobExperience(user1, laboralExperience1);

        userController.getUserSkills(user1);

        userController.getUserLaboralExperience(user1);
    }
}
