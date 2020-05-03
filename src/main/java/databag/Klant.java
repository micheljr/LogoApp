package databag;

import datatype.Terugbetaling;

import java.time.LocalDateTime;

/**
 * Databag-class Transportmiddel voor een klant
 * - private datamembers
 * - getters en setters
 * - defaultcontructor
 * - toString() (String-representatie van een Klant-object)
 */

public class Klant {

    private String naam, voornaam, email, straat, gemeente, mutualiteit, problematiek, school, telefoon;
    private int huisnummer, postcode, bus;
    private Terugbetaling terugbetaling;
    private LocalDateTime geboorteDatum;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getMutualiteit() {
        return mutualiteit;
    }

    public void setMutualiteit(String mutualiteit) {
        this.mutualiteit = mutualiteit;
    }

    public String getProblematiek() {
        return problematiek;
    }

    public void setProblematiek(String problematiek) {
        this.problematiek = problematiek;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public Terugbetaling getTerugbetaling() {
        return terugbetaling;
    }

    public void setTerugbetaling(Terugbetaling terugbetaling) {
        this.terugbetaling = terugbetaling;
    }

    public LocalDateTime getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(LocalDateTime geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    @Override
    public String toString() {
        return "Klant{" +
                "naam='" + naam + '\'' +
                ", voornaam='" + voornaam + '\'' +
                ", email='" + email + '\'' +
                ", straat='" + straat + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", bus='" + bus + '\'' +
                ", postcode=" + postcode +
                ", gemeente='" + gemeente + '\'' +
                ", mutualiteit='" + mutualiteit + '\'' +
                ", problematiek='" + problematiek + '\'' +
                ", school='" + school + '\'' +
                ", telefoon='" + telefoon + '\'' +
                ", terugbetaling='" + terugbetaling + '\'' +
                ", geboorteDatum=" + geboorteDatum +
                '}';
    }
}
