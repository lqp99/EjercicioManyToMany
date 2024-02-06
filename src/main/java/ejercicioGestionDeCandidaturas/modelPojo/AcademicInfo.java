package ejercicioGestionDeCandidaturas.modelPojo;

import jakarta.persistence.*;

@Entity  //define que es una entidad dentro de una database.
@Table(name = "academic_info")  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
public class AcademicInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "init_date")
    private int initialDate;

    @Column(name = "end_date")
    private int endDate;

    @Column(name = "current")
    private int current;

    @Column(name = "mean_score")
    private float meanScore;

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
    private Institution institution;


    //constructor
    public AcademicInfo() {
    }


    //toString
    @Override
    public String toString() {
        return "AcademicInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", initialDate=" + initialDate +
                ", endDate=" + endDate +
                ", current=" + current +
                ", meanScore=" + meanScore +
                '}';
    }


    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(int initialDate) {
        this.initialDate = initialDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public float getMeanScore() {
        return meanScore;
    }

    public void setMeanScore(float meanScore) {
        this.meanScore = meanScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
