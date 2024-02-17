package ejercicioGestionDeCandidaturas.modelPojo;

import ejercicioGestionDeCandidaturas.enumerations.Status;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity  //define que es una entidad dentro de una database.
@Table(name = "candidatures")  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(name = "cv_path")  //para que la columna de la tabla se llame como le digas.
    private String cvPath;

    @Column(name = "cover_letter_path")  //para que la columna de la tabla se llame como le digas.
    private String cover_letter;

    @Column(name = "status")  //para que la columna de la tabla se llame como le digas.
    private Status status;

    @ManyToOne(  //muchos a uno.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private User user;

    @OneToMany(  //uno a muchos.
            mappedBy = "candidature",  //mapeamos el valor de la variable de la otra clase que hace la relación con esta clase.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    private List<JobOffer> jobOffers = new ArrayList<>();


    //constructor
    public Candidature() {
    }

    public Candidature(String cvPath, String cover_letter) {
        this.cvPath = cvPath;
        this.cover_letter = cover_letter;
    }


    //toString
    @Override
    public String toString() {
        return "Candidature{" +
                "id=" + id +
                ", cvPath='" + cvPath + '\'' +
                ", cover_letter='" + cover_letter + '\'' +
                ", status=" + status +
                ", user=" + user +
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

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public String getCover_letter() {
        return cover_letter;
    }

    public void setCover_letter(String cover_letter) {
        this.cover_letter = cover_letter;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }
}
