package ejercicioGestionDeCandidaturas.interfacesDAO;

import ejercicioGestionDeCandidaturas.modelPojo.AcademicInfo;
import ejercicioGestionDeCandidaturas.modelPojo.LaboralExperience;
import ejercicioGestionDeCandidaturas.modelPojo.Skill;
import ejercicioGestionDeCandidaturas.modelPojo.User;

import java.util.List;

public interface UserDAO {
    public void createUser(User user);
    public void updateUser(User user);
    public void removeUser(User user);
    public User getUser(Long idUser);
    public User getUserByName(String userName);
    public List<User> getUsersByName(String userName);
    public List<Skill> getUserSkills(User user);
    public List<LaboralExperience> getUserLaboralExperience(User user);
    public List<AcademicInfo> getUserAcademicInfo(User user);
}
