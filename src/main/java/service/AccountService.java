package service;

import DAO.AccountDAO;
import databag.Account;
import exception.ApplicationException;
import exception.ApplicationExceptionType;
import exception.DBException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService(AccountDAO accD){
        this.accountDAO = accD;
    }

    /**
     * Account zoeken met de meegegeven login.
     * @param login login dat we moeten zoeken in de database
     * @return Account account dat overeenkomt met de login of null.
     * @throws DBException exception die duidt op een probleem met de installatie
     *                     van de DAO of een fout in de query.
     * @throws ApplicationException exception die duidt op een probleem met de login.
     */
    public Account zoekAccountOpLogin(String login) throws ApplicationException, DBException {
        if (login.length() == 0){
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_NULL.getMessage());
        }

        return accountDAO.zoekAccountOpLogin(login);
    }

    /**
     * Account zoeken met een meegegeven emailadres.
     * @param email email dat we moeten zoeken in de database.
     * @return Account account dat overeenkomt met het emailadres of null.
     * @throws DBException exception die duidt op een probleem met de installatie
     *                     van de DAO of een fout in de query.
     * @throws ApplicationException exception die duidt op een probleem met het emailadres.
     */
    public Account zoekAccountOpEmail(String email) throws ApplicationException, DBException {
        if (email.length() == 0){
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_NULL.getMessage());
        }
        return accountDAO.zoekAccountOpEmail(email);
    }

    /**
     *  Voegt een account toe.
     * @param acc Account acc dat aan de database moet toegevoegd worden.
     * @return String login van het account dat net is opgeslagen.
     * @throws DBException exception die duidt op een probleem met de installatie
     *                     van de DAO of een fout in de query.
     * @throws ApplicationException exception die duidt op een probleem met het account,
     *                              of als de login of email al in gebruik is.
     */
    public String registrerenAccount(Account acc) throws DBException, ApplicationException {
        if (acc == null){
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_NULL.getMessage());
        }

        checkAlleVeldenIngevuld(acc);

        if(!(zoekAccountOpLogin(acc.getLogin()) == null)){
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_LOGIN_IN_GEBRUIK.getMessage());
        }

        if(!(zoekAccountOpEmail(acc.getEmail())== null)){
            throw new ApplicationException(ApplicationExceptionType.ACCOUNT_EMAIL_IN_GEBRUIK.getMessage());
        }

        return accountDAO.opslaanAccount(acc);
    }

    private void checkAlleVeldenIngevuld(Account acc){

    }


}
