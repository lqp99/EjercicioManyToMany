package ejercicioGestionDeCandidaturas.modelPojo;

import jakarta.persistence.*;

import java.util.List;

@Entity  //define que es una entidad dentro de una database.
@Table(name = "skills")  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany /*(  //muchos a muchos.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree o actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })*/
    @JoinTable(  //es la tabla que se genera cuando hay una relacion de muchos a muchos (N:M).
            name = "users_skills",  //se pone el nombre de la tabla.
            joinColumns = @JoinColumn(name = "skill_id"),  //es la Foreign Key con el nombre de la clase donde se hace el @JoinTable.
            inverseJoinColumns = @JoinColumn(name = "user_id")  //la Foreign Key de la otra clase.
    )
    private List<User> users;

    @ManyToMany /*(  //muchos a muchos.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree o actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })*/
    @JoinTable(  //es la tabla que se genera cuando hay una relacion de muchos a muchos (N:M).
            name = "job_offers_skills",  //se pone el nombre de la tabla.
            joinColumns = @JoinColumn(name = "skill_id"),  //es la Foreign Key con el nombre de la clase donde se hace el @JoinTable.
            inverseJoinColumns = @JoinColumn(name = "job_offer_id")  //la Foreign Key de la otra clase.
    )
    private List<JobOffer> jobOffers;


    //constructor
    public Skill() {
    }


    //toString
    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                ", jobOffers=" + jobOffers +
                '}';
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }
}
