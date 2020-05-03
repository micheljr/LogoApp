package databag;

/**
 * Databag-class Transportmiddel voor een account
 * - private datamembers
 * - getters en setters
 * - defaultcontructor
 * - toString() (String-representatie van een Account-object)
 */

public class Account {

    private String voornaam;
    private String naam;
    private String email;
    private String login;
    private String paswoord;

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    @Override
    public String toString(){
    return "Account{"
            + "voornaam=" + voornaam
            + ", naam=" + naam
            + ", emailadres=" + email
            + ", login=" + login
            + ", paswoord=" + paswoord;
    }
}
