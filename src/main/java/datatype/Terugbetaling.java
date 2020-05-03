package datatype;

public enum Terugbetaling {

    RIZIV1("Riziv 1"), RIZIV2("Riziv 2"), AANVULLENDE("Aanvullende"), GEEN("Geen terugbetaling");

    private final String message;

    Terugbetaling(String message){ this.message = message;}

    public String getMessage(){
        return message;
    }
}
