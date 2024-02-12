package ejercicioGestionDeCandidaturas.modelPojo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity  //define que es una entidad dentro de una database.
@Table(name = "institutions")  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany /*(cascade = {CascadeType.PERSIST, CascadeType.MERGE})*/  //muchos a muchos. Al ser "CascadeType.PERSIST / MERGE" cada vez que se cree o actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
    @JoinTable(  //es la tabla que se genera cuando hay una relacion de muchos a muchos (N:M).
            name = "academic_info",  //se pone el nombre de la tabla.
            joinColumns = @JoinColumn(name = "institution_id"),  //es la Foreign Key con el nombre de la clase donde se hace el @JoinTable.
            inverseJoinColumns = @JoinColumn(name = "user_id")  //la Foreign Key de la otra clase.
    )
    private List<User> users = new ArrayList<>();

    @OneToMany(  //uno a muchos.
            mappedBy = "institution",  //mapeamos el valor de la variable de la otra clase que hace la relación con esta clase.
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "author", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
    )
    private List<AcademicInfo> academicInfos = new ArrayList<>();


    //constructor
    public Institution() {
    }

    public Institution(String name) {
        this.name = name;
    }


    //toString
    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                ", academicInfos=" + academicInfos +
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

    public List<AcademicInfo> getAcademicInfos() {
        return academicInfos;
    }

    public void setAcademicInfos(List<AcademicInfo> academicInfos) {
        this.academicInfos = academicInfos;
    }
}
