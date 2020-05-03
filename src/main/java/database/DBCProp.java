package database;

import java.util.Properties;

public class DBCProp {

    private static String dbUrl;
    private static String driver;
    private static String login;
    private static String paswoord;


    /**
     * Haalt de URL, driver paswoord en login uit het bestand DBINIT.properties en
     * vult deze in in de overeenkomstige velden.
     */
    private DBCProp() {
        Properties appProperties = new Properties();
        try {
            String propertiesFile = "/database/DBINIT.properties";
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
            DBCProp db = new DBCProp();
        }
        return dbUrl;
    }

    /**
     * @return the driver
     */
    public static String getDriver() {
        if (driver == null) {
            DBCProp db = new DBCProp();
        }
        return driver;
    }

    /**
     * @return the login
     */
    public static String getLogin() {
        if (login == null) {
            DBCProp db = new DBCProp();
        }
        return login;
    }

    /**
     * @return the paswoord
     */
    public static String getPaswoord() {
        if (paswoord == null) {
            DBCProp db = new DBCProp();
        }
        return paswoord;
    }
}

