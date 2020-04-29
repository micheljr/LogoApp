package DAO;

import DAO.connect.ConnectionManager;
import databag.Account;
import exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    /**
     * Zoeken van een account op basis van de login.
     *
     * @param login login waarom gezocht moet worden.
     * @return Account account dat overeenkomt met de login of null.
     * @throws DBException exception die duidt op een probleem met de installatie
     *                     van de DAO of een fout in de query
     */
    public Account zoekAccountOpLogin(String login) throws DBException {
        if (login.length() > 0) {
            try (Connection conn = ConnectionManager.getConnection()) {
                try (PreparedStatement stmt = conn.prepareStatement(
                        "select naam, voornaam, emailadres, login, paswoord from account where login = ?"
                )) {
                    stmt.setString(1, login);
                    stmt.execute();

                    try (ResultSet r = stmt.getResultSet()) {
                        if (r.next()) {
                            return getAccountUitDatabase(r);
                        }
                    } catch (SQLException sqlEx) {
                        throw new DBException("SQL-Exception in zoekAccountOpLogin - resultset" + sqlEx);
                    }
                } catch (SQLException sqlEx) {
                    throw new DBException(("SQL-Exception in zoekAccountOpLogin - statement" + sqlEx));
                }
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-Exception in zoekAccountOpLogin - connection " + sqlEx);
            }
        }
        return null;
    }

    /**
     * Methode om een account uit de ResultSet te halen en te returnen.
     *
     * @param r ResultSet r resultaat van de query
     * @return Account account dat opgemaakt is uit de data uit de query.
     * @throws SQLException Exception die duidt op een probleem met het resultaat van de query.
     */
    private Account getAccountUitDatabase(ResultSet r) throws SQLException{
        Account account = new Account();
        account.setNaam(r.getString("naam"));
        account.setVoornaam(r.getString("voornaam"));
        account.setLogin(r.getString("login"));
        account.setPaswoord(r.getString("paswoord"));
        account.setEmail(r.getString("emailadres"));
        return account;
    }

    /**
     * Account toevoegen.
     *
     * @param account is de toe te voegen account.
     * @throws DBException exception die duit op een probleem met de installatie
     *                     van de DAO of een fout in de query
     */
    public String opslaanAccount(Account account) throws DBException {
        if (account != null) {
            // connectie tot stand brengen (en automatisch sluiten)
            try (Connection conn = ConnectionManager.getConnection()) {
                // preparedStatement opstellen (en automatisch sluiten)
                try (PreparedStatement stmt = conn.prepareStatement(
                        "insert into account(naam"
                                + " , voornaam"
                                + " , login"
                                + " , paswoord"
                                + " , emailadres"
                                + " ) values(?,?,?,?,?)")) {
                    stmt.setString(1, account.getNaam());
                    stmt.setString(2, account.getVoornaam());
                    stmt.setString(3, account.getLogin());
                    stmt.setString(4, account.getPaswoord());
                    stmt.setString(5, account.getEmail());
                    stmt.execute();


                } catch (SQLException sqlEx) {
                    throw new DBException("SQL-exception in toevoegenKlant "
                            + "- statement" + sqlEx);
                }
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in toevoegenKlant "
                        + "- connection" + sqlEx);
            }
            return account.getLogin();
        }
        return "";
    }

    /**
     *  Zoeken van een account op basis van het emailadres
     *
     * @param email emailadres van het te zoeken account.
     * @return Account account dat overeenkomt met het emailadres of null.
     * @throws DBException exception dat duidt op een probleem met de installatie
     *                     van de DAO of een fout in de query
     */
    public Account zoekAccountOpEmail(String email) throws DBException {
        if (email.length() > 0) {
            try (Connection conn = ConnectionManager.getConnection()) {
                try (PreparedStatement stmt = conn.prepareStatement(
                        "select naam,"
                                + "voornaam, login,"
                                + "paswoord,"
                                + "emailadres"
                                + " from account where emailadres = ? "
                )) {
                    stmt.setString(1, email);
                    stmt.execute();

                    try (ResultSet r = stmt.getResultSet()) {
                        if (r.next()) {
                            return getAccountUitDatabase(r);
                        }
                    } catch (SQLException sqlEx) {
                        throw new DBException("SQL-Exception in zoekAccountOpLogin - resultset" + sqlEx);
                    }
                } catch (SQLException sqlEx) {
                    throw new DBException(("SQL-Exception in zoekAccountOpLogin - statement" + sqlEx));
                }
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-Exception in zoekAccountOpLogin - connection " + sqlEx);
            }
        }
        return null;
    }
}
