package ui.controller.KlantBeheer;

import databag.Account;
import databag.Klant;
import exception.ApplicationException;
import exception.DBException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import service.AccountService;
import ui.LOGOapp;

public class KlantBeheerController {
    private LOGOapp logoApp;
    private AccountService accS;
    private Account account;

    public KlantBeheerController(AccountService accountService){
        this.accS = accountService;
    }

    @FXML
    private Label laUser, laErrorMessage;
    @FXML
    private ListView<Klant> lvListview;
    @FXML
    private Button buToevoegen;
    @FXML
    private Button buWijzigen;
    @FXML
    private Button buVerwijderen;

    @FXML
    private void initialize(){
        buVerwijderen.disableProperty().bind(lvListview.getSelectionModel().selectedItemProperty().isNull());
        buWijzigen.disableProperty().bind(lvListview.getSelectionModel().selectedItemProperty().isNull());
        laUser.setText(account.getVoornaam().substring(0,1) + ". " + account.getNaam());
    }

    public void setMainApp(LOGOapp logoApp) {
        this.logoApp = logoApp;
    }

    public void setData(String login) {
        try {
            account = accS.zoekAccountOpLogin(login);
        } catch (ApplicationException ae){
            laErrorMessage.setText(ae.getMessage());
        } catch (DBException dbe){
            laErrorMessage.setText("Fout in het programma, contacteer uw beheerder.");
        }

    }

    public void uitloggen(){
        logoApp.laadLoginScherm();
    }

    public void verwijderItem(){}
    public void wijzigItem(){}
    public void voegItemToe(){
        logoApp.laadKlantToevoegen(account.getLogin());
    }

    public void laadMainScherm(){
        logoApp.laadMainScherm(account.getLogin());
    }


}
