package ejercicioGestionDeCandidaturas.gui;

import ejercicioGestionDeCandidaturas.controllerServices.CompanyController;
import ejercicioGestionDeCandidaturas.controllerServices.UserController;
import ejercicioGestionDeCandidaturas.modelPojo.Skill;
import ejercicioGestionDeCandidaturas.modelPojo.User;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        CompanyController companyController = new CompanyController();
        User user = new User("Perico", "pericoPec@example.com", "El de los palotes", 666777);
        User user2 = userController.createUser("John Doe", "jdoe@asdf.asdf", "asdf", 123);

        userController.createUser(user);
        userController.addSkill(user, "asdf");
        userController.addSkill(user, new Skill("skill2"));
        userController.addSkill(user2, "asdf");

    }
}
