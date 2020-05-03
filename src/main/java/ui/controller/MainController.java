package ui.controller;

import databag.Account;
import exception.ApplicationException;
import exception.DBException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.AccountService;
import ui.LOGOapp;

public class MainController {

    private final AccountService accS;
    private LOGOapp logoApp;
    private Account account;

    public MainController (AccountService accountService){
        this.accS = accountService;
    }

    @FXML
    private Label laUser, laErrorMessage;

    @FXML
    public void initialize(){
        laUser.setText(account.getVoornaam().substring(0,1) + ". " + account.getNaam());
    }

    public void setMainApp(LOGOapp logoApp){
        this.logoApp = logoApp;
    }

    public void setData(String login){
        try {
            account = accS.zoekAccountOpLogin(login);
        } catch (ApplicationException ae){
            laErrorMessage.setText(ae.getMessage());
        } catch (DBException dbe){
            laErrorMessage.setText("Fout in het Programma, neem contact op met uw beheerder.");
        }
    }

    public void dossierBeheer(){
        logoApp.laadDossierBeheer(account.getLogin());
    }

    public void factuurBeheer(){
        logoApp.laadFactuurBeheer(account.getLogin());
    }

    public void klantBeheer(){
        logoApp.laadKlantBeheer(account.getLogin());
    }

    public void laadLoginScherm(){
        logoApp.laadLoginScherm();
    }
}
