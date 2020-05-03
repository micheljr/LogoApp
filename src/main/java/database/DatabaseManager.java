package database;

import DAO.connect.DBProp;
import exception.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseManager {

    /**
     * Kijkt of er een connectie met de database gelegd kan worden. 
     * Als dit niet zo is word geprobeerd om een nieuwe database te maken. 
     * Leest hiervoor uit het bestand DBINIT.properties
     *
     * @return connectie-object naar de h2db.
     * @throws DBException wanner de database niet toegankelijk is.
     */
    public static Connection getConnection() throws DBException {

        try {
            //driver laden
            Class.forName(DBProp.getDriver()).newInstance();
            return DriverManager.getConnection(DBProp.getDbUrl(), DBProp.getLogin(), DBProp.getPaswoord());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {

            throw new DBException("Connectie met de database mislukt: " + ex);
                
        } catch (SQLException sqlEx){
            
            return getDatabase();
        }
    }

    public static Connection getDatabase() throws DBException{
        try {
            Class.forName(DBCProp.getDriver()).newInstance();
            return DriverManager.getConnection(DBCProp.getDbUrl(), DBCProp.getLogin(), DBCProp.getPaswoord());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
            System.out.println("getConnection dbm \n" + ex);
            throw new DBException("Connectie met de database mislukt: " + ex);
        }
    }
}
