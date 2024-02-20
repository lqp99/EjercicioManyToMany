package ejercicioGestionDeCandidaturas.modelPojo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Calendar;

@Entity  //define que es una entidad dentro de una database.
@Table(name = "laboral_experiences")  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
public class LaboralExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "init_date")
    private LocalDate initialDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "current")
    private boolean current;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "localtion")
    private String location;

    @ManyToOne(  //muchos a uno.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private User user;

    @ManyToOne(  //muchos a uno.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Company company;


    //constructor
    public LaboralExperience() {
    }

    public LaboralExperience(String jobTitle, LocalDate initialDate, LocalDate endDate, boolean current, String descripcion, String location) {
        this.jobTitle = jobTitle;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.current = current;
        this.descripcion = descripcion;
        this.location = location;
    }

    //toString
    @Override
    public String toString() {
        return "LaboralExperience{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", initialDate=" + initialDate +
                ", endDate=" + endDate +
                ", current=" + current +
                ", descripcion='" + descripcion + '\'' +
                ", location='" + location + '\'' +
                ", user=" + user +
                ", company=" + company +
                '}';
    }


    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
