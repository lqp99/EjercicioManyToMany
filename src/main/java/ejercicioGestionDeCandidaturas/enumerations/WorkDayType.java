package ejercicioGestionDeCandidaturas.enumerations;

public enum WorkDayType {
    INTERNSHIP(0), FULL_TIME(1), CONTRACT(2), PART_TIME(3), TEMPORARY(4);

    private int code;


    //constructor
    private WorkDayType(int code) {
        this.code = code;
    }


    //metodo
    public WorkDayType valOf(String name) {
        for (WorkDayType type : values()){
            if (type.name().equals(name)){
                return type;
            }
        }
        return null;
    }
}
