package ejercicioGestionDeCandidaturas.enumerations;

public enum Status {
    REJECTED(0), PENDING(1), ACCEPTED(2);

    private int code;


    //constructor
    private Status(int code) {
        this.code = code;
    }


    //metodo
    public Status valOf(String name) {
        for (Status type : values()){
            if (type.name().equals(name)){
                return type;
            }
        }
        return null;
    }
}
