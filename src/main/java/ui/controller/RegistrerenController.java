package ui.controller;

import databag.Account;
import exception.ApplicationException;
import exception.ApplicationExceptionType;
import exception.DBException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import security.PasswordEncryptor;
import service.AccountService;
import ui.LOGOapp;

public class RegistrerenController {

    private LOGOapp logoApp;
    private AccountService accountService;

    @FXML
    private TextField tfNaam;
    @FXML
    private TextField tfVoornaam;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pwPaswoord;
    @FXML
    private Label laErrorMessage;

    public RegistrerenController(AccountService accS){
        this.accountService = accS;
    }


    public void setMainApp(LOGOapp logoApp) {
        this.logoApp = logoApp;
    }

    public void annuleren(){
        logoApp.laadLoginScherm();
    }

    public void registreren(){
        resetErrorMessage();
        try {
            checkAlleVeldenIngevuld();
            Account acc = new Account();
            acc.setNaam(tfNaam.getText());
            acc.setVoornaam(tfVoornaam.getText());
            acc.setEmail(tfEmail.getText());
            acc.setLogin(tfLogin.getText());

            String pw = pwPaswoord.getText();
            String hpw = PasswordEncryptor.getEncryptedPassword(pw);
            acc.setPaswoord(hpw);

            String login = accountService.registrerenAccount(acc);
            if (login.length()<1) throw new DBException();
            logoApp.laadMainScherm(login);

        } catch (ApplicationException ae) {
            laErrorMessage.setText(ae.getMessage());
        } catch (DBException dbe){
            laErrorMessage.setText(("Fout in het programma, neem contact op met programmeur!"));
            System.err.println("Fout in registreren!");
            System.err.println(dbe.getMessage());
        }
    }

    /**
     * methode om te checken of alle velden van het interface zijn ingevuld.
     * @throws ApplicationException exception die duidt op een probleem met een van de velden.
     */
    private void checkAlleVeldenIngevuld() throws ApplicationException{
        if (StringUtils.isBlank(tfNaam.getText())) {
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_NAAM_LEEG.getMessage());
        }
        if (StringUtils.isBlank(tfVoornaam.getText())) {
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_VOORNAAM_LEEG.getMessage());
        }
        if (StringUtils.isBlank(tfEmail.getText())) {
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_EMAIL_LEEG.getMessage());
        }
        if (StringUtils.isBlank(tfLogin.getText())) {
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_LOGIN_LEEG.getMessage());
        }
        if (StringUtils.isBlank(pwPaswoord.getText())) {
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_PASWOORD_LEEG.getMessage());
        }
    }

    private void resetErrorMessage(){
        laErrorMessage.setText("");
    }
}
