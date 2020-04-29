package DAO.connect;

import java.util.Properties;

public class DBProp {

    private static String dbUrl;
    private static String driver;
    private static String login;
    private static String paswoord;
    private static String propertiesFile = "/database/DBINIT.properties";


    /**
     * Haalt de URL, driver paswoord en login uit het bestand DBINIT.properties en
     * vult deze in in de overeenkomstige velden.
     */
    private DBProp() {
        Properties appProperties = new Properties();
        try {
            appProperties.load(this.getClass().getResourceAsStream(propertiesFile));

            dbUrl = appProperties.getProperty("dbUrl");
            driver = appProperties.getProperty("driver");
            login = appProperties.getProperty("login");
            paswoord = appProperties.getProperty("paswoord");

        } catch (java.io.IOException ex) {
            System.out.println("Properties file niet gevonden");
        }

    }

    /**
     * @return the dbUrl
     */
    public static String getDbUrl() {
        if (dbUrl == null) {
            DBProp db = new DBProp();
        }
        return dbUrl;
    }

    /**
     * @return the driver
     */
    public static String getDriver() {
        if (driver == null) {
            DBProp db = new DBProp();
        }
        return driver;
    }

    /**
     * @return the login
     */
    public static String getLogin() {
        if (login == null) {
            DBProp db = new DBProp();
        }
        return login;
    }

    /**
     * @return the paswoord
     */
    public static String getPaswoord() {
        if (paswoord == null) {
            DBProp db = new DBProp();
        }
        return paswoord;
    }
}
