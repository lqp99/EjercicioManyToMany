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
import ejercicioGestionDeCandidaturas.modelPojo.User;

import java.util.*;

public class UserController {
    private static final UserDAOimpl userDAOimpl= new UserDAOimpl();

    public static void createUser(User user){
        List<User> users = userDAOimpl.getAllUsers();

        for (User u : users){
            if (u.getId() != user.getId()) {
                userDAOimpl.createUser(user);
            }
        }
    }
}