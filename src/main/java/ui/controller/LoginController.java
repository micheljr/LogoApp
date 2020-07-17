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

public class LoginController {

    @FXML
    private Label laErrorMessage;
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pwPaswoord;
    @FXML
    private TextField tfPassword;

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
     * Als op button "Show" word geklikt, eventjes wachtwoord
     * in letters laten zien en dan na 1s terug maskeren.
     */
    public void showPassword(){
        tfPassword.setText(pwPaswoord.getText());
        pwPaswoord.setVisible(false);
        tfPassword.setVisible(true);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        tfPassword.setText("");
                        tfPassword.setVisible(false);
                        pwPaswoord.setVisible(true);
                    }
                },
                1000
        );

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
