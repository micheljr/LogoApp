package ui.controller.KlantBeheer;

import javafx.fxml.FXML;
import ui.LOGOapp;

public class KlantToevoegenController {

    private String login;
    private LOGOapp logoApp;

    public void setData(String login) {
        this.login = login;
    }
    public void setMainApp(LOGOapp logoApp){
        this.logoApp = logoApp;
    }

    /*TODO
        volgende 3 functies  en initialize-functie afmaken.
     */

    @FXML
    public void initialize(){}
    
    public void laadKlantBeheer(){}

    public void laadLoginScherm(){}

    public void klantToevoegen(){}
}
