package DAO.connect;

/**
 * Write a description of class Connectionmanager here.
 *
 * @author Michel Stroobants
 * @version 0.9
 */


import exception.DBException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    /**
     * Levert een connectie naar de be.vives.ti.Dao. Leest hiervoor uit het bestand
     * DB.properties
     *
     * @return connectie-object naar de be.ti.vives.DAO.
     * @throws DBException wanner de be.vives.ti.DAO niet toegankelijk is.
     */
    public static Connection getConnection() throws DBException {

        try {
            //driver laden
            Class.forName(DBProp.getDriver()).newInstance();
            return DriverManager.getConnection(DBProp.getDbUrl(), DBProp.getLogin(), DBProp.getPaswoord());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            throw new DBException("Connectie met de DAO mislukt: " + ex);
        }
    }
}

