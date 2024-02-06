package ejercicioGestionDeCandidaturas.modelPojo;

import jakarta.persistence.*;

@Entity  //define que es una entidad dentro de una database.
@Table(name = "candidatures")  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(name = "cv_path")  //para que la columna de la tabla se llame como le digas.
    private String cv;

    @Column(name = "cover_letter_path")  //para que la columna de la tabla se llame como le digas.
    private String cover_letter;

    @Column(name = "status")  //para que la columna de la tabla se llame como le digas.
    private int status;

    @ManyToOne(  //muchos a uno.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree o actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private User user;

    @ManyToOne(  //muchos a uno.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree o actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private JobOffer jobOffer;


    //constructor
    public Candidature() {
    }


    //toString
    @Override
    public String toString() {
        return "Candidature{" +
                "id=" + id +
                ", cv='" + cv + '\'' +
                ", cover_letter='" + cover_letter + '\'' +
                ", status=" + status +
                ", user=" + user +
                ", jobOffers=" + jobOffer +
                '}';
    }


    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getCover_letter() {
        return cover_letter;
    }

    public void setCover_letter(String cover_letter) {
        this.cover_letter = cover_letter;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }
}
