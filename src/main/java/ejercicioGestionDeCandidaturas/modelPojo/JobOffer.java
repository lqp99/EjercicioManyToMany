package ejercicioGestionDeCandidaturas.modelPojo;

import jakarta.persistence.*;

import java.util.List;

@Entity  //define que es una entidad dentro de una database.
@Table(name = "job_offers")  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "details")
    private String details;

    @Column(name = "location")
    private String location;

    @Column(name = "workday_type")
    private int workDayType;

    @Column(name = "open")
    private int open;

    @Column(name = "min_salary")
    private int minSalary;

    @Column(name = "max_salary")
    private int maxSalary;

    @Column(name = "required_candidates")
    private int requiredCandidates;

    @ManyToMany(  //muchos a muchos.
            mappedBy = "jobOffers"  //En el mappedBy ponemos el nombre de la tabla de esta clase.
            //fetch = FetchType.LAZY
            //El FetchType.EAGER: cuando se hace un select te trae ttodo lo que tiene que ver con esta clase. Te va a traer todas las asignaturas que tiene el profesor. Cuando te da un ERROR de que hay muchas llmadas se pone LAZY y se arregla.
            //El FetchType.LAZY: cuando se hace un select te trae ttodo lo que tiene que ver con esta clase menos las asignaturas y así controlas lo que te devuelve. Hay que crear un método para saber cuales asignatruas son las que están relacionadas con cada profesor.
    )
    private List<Skill> skills;

    @OneToMany(  //uno a muchos.
            mappedBy = "jobOffer",  //mapeamos el valor de la variable de la otra clase que hace la relación con esta clase.
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "author", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
    )
    private List<Candidature> candidatures;

    @ManyToOne(  //muchos a uno.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree o actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Company company;

    //constructor
    public JobOffer() {
    }


    //toString


    @Override
    public String toString() {
        return "JobOffer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", location='" + location + '\'' +
                ", workDayType=" + workDayType +
                ", open=" + open +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", requiredCandidates=" + requiredCandidates +
                ", skills=" + skills +
                ", candidatures=" + candidatures +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getWorkDayType() {
        return workDayType;
    }

    public void setWorkDayType(int workDayType) {
        this.workDayType = workDayType;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getRequiredCandidates() {
        return requiredCandidates;
    }

    public void setRequiredCandidates(int requiredCandidates) {
        this.requiredCandidates = requiredCandidates;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(List<Candidature> candidatures) {
        this.candidatures = candidatures;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}