package ui.controller;

import databag.Account;
import exception.ApplicationException;
import exception.ApplicationExceptionType;
import exception.DBException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import security.PasswordEncryptor;
import service.AccountService;
import ui.LOGOapp;

import java.awt.*;
import java.net.URL;

public class LoginController {

    @FXML
    private Label laErrorMessage;
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField pwPaswoord;

    private LOGOapp logoApp;
    private AccountService accountService;

    public LoginController(AccountService accS) {
        this.accountService = accS;
    }

    public void setMainApp(LOGOapp LOGOapp) {
        this.logoApp = LOGOapp;
    }

    public void openWebpage(){
        resetErrorMessage();
        /*
        try {
            Desktop.getDesktop().browse(new URL("http://localhost:63342/Registreren/index.html").toURI());
        } catch (Exception e){
            laErrorMessage.setText(e.getMessage());
        }*/
        logoApp.laadRegistrerenScherm();
    }

    /**
     * Keuzescherm laden met de nodige checks:
     *  - login en pw worden gecheckt.
     *  anders applicationexception op laErrorMessage.
     */
    public void laadKeuzeScherm(){

        resetErrorMessage();

        try {
            checkAlleVeldenIngevuld();

            String login = tfLogin.getText();
            String paswoord = pwPaswoord.getText();

            Account account = accountService.zoekAccountOpLogin(login);
            if (account == null){
                throw new ApplicationException(ApplicationExceptionType.ACCOUNT_NULL.getMessage());
            }
            if (PasswordEncryptor.verifyPassword(paswoord, account.getPaswoord())){
                logoApp.laadMainScherm(account.getLogin());
            } else {
                throw new ApplicationException(ApplicationExceptionType.ACCOUNT_PASWOORD_FOUT.getMessage());
            }
        } catch (ApplicationException ae) {
            laErrorMessage.setText(ae.getMessage());
        } catch (DBException dbe){
            laErrorMessage.setText("Er is iets misgelopen, neem contact op met de programmeur");
            System.err.println(dbe.getMessage());
        }
    }

    /**
     * Checken of alle velden van de interface ingevuld zijn.
     * @throws ApplicationException exception die duidt op een probleem met een van de velden.
     */
    private void checkAlleVeldenIngevuld() throws ApplicationException {
        if (StringUtils.isBlank(tfLogin.getText())) {
            throw new ApplicationException(ApplicationExceptionType.LOGIN_LEEG.getMessage());
        }
        if (StringUtils.isBlank(pwPaswoord.getText())) {
            throw new ApplicationException(ApplicationExceptionType.PASWOORD_LEEG.getMessage());
        }
    }

    private void resetErrorMessage(){
        laErrorMessage.setText("");
    }
}
