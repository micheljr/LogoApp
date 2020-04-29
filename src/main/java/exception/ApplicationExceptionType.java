package exception;

public enum ApplicationExceptionType {

    LOGIN_LEEG("Het login veld is leeg."),
    PASWOORD_LEEG("Het paswoord veld is leeg"),


    ACCOUNT_NULL("Er is geen account gevonden met deze login"),
    ACCOUNT_PASWOORD_FOUT("De ingevoerde login en paswoord komen niet overeen."),
    ACCOUNT_NAAM_LEEG("Het naam veld is niet ingevuld."),
    ACCOUNT_VOORNAAM_LEEG("Het voornaam veld is niet ingevuld."),
    ACCOUNT_LOGIN_LEEG("Het login veld is niet ingevuld."),
    ACCOUNT_PASWOORD_LEEG("Het paswoord veld is niet ingevuld."),
    ACCOUNT_EMAIL_LEEG("Het email veld is niet ingevuld."),
    ACCOUNT_LOGIN_IN_GEBRUIK("Deze login is reeds in gebruik!"),
    ACCOUNT_EMAIL_IN_GEBRUIK("Dit emailadres is reeds in gebruik!");


    private final String message;

    ApplicationExceptionType(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
