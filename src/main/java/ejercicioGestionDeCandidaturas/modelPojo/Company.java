package ejercicioGestionDeCandidaturas.modelPojo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity  //define que es una entidad dentro de una database.
@Table(name = "companies")  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "password")
    private String password;

    @OneToMany(  //uno a muchos.
            mappedBy = "company",  //mapeamos el valor de la variable de la otra clase que hace la relación con esta clase.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    private List<LaboralExperience> laboralExperiences;

    @OneToMany(  //uno a muchos.
            mappedBy = "company",  //mapeamos el valor de la variable de la otra clase que hace la relación con esta clase.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    private List<JobOffer> jobOffers = new ArrayList<>();


    //constructor
    public Company() {
    }

    public Company(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Company(String name, String description, String password) {
        this.name = name;
        this.description = description;
        this.password = password;
    }


    //toString
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", laboralExperiences=" + laboralExperiences +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<LaboralExperience> getLaboralExperiences() {
        return laboralExperiences;
    }

    public void setLaboralExperiences(List<LaboralExperience> laboralExperiences) {
        this.laboralExperiences = laboralExperiences;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }
}
