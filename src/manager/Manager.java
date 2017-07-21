package manager;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Surfen on 20.07.2017.
 */
public class Manager {
    protected static final String DBCONNECTION = "jdbc:" + "mysql" + "://" + "127.0.0.1" + ":" + "3306" + "/" + "parking_project";;
    protected static final String DBUSER = "root";
    protected static final String DBPASSWORD = "yourpassword";

    private static Properties connectionProps;
    protected Connection conn = null;

    /**
     * Schließt die Datenbankverbindung der Klasse.
     */
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Liefert eine Datenbankverbindung.
     *
     * @return conn
     * @throws SQLException
     */
    protected static Connection getConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");
        return DriverManager.getConnection(DBCONNECTION, connectionProps);
    }

    /**
     * Prüft, ob in der DB eine Tabelle mit dem entsprechend übergebenen
     * Tabellennamen existiert. Falls dies der Fall ist, wird die Tabelle gelöscht.
     *
     * @param tablename
     */
    void checkTableExistsAndDrop(String tablename) {
        PreparedStatement stm = null;
        if (tableExists(tablename)) {
            try {
                Connection conn = getConnection();
                stm = conn.prepareStatement("DROP table " + tablename);
                stm.executeUpdate();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                cleanUp(stm, null);
            }
        }
    }

    protected void checkTableExistsAndDelete(String tablename) {
        PreparedStatement stm = null;
        if (tableExists(tablename)) {
            try {
                Connection conn = getConnection();
                stm = conn.prepareStatement("DELETE from " + tablename);
                stm.executeUpdate();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                cleanUp(stm, null);
            }
        }
    }

    /**
     * Schließt das übergebene PreparedStatement und ResultSet
     *
     * @param stm
     * @param rss
     */
    void cleanUp(PreparedStatement stm, ResultSet rss) {
        try {
            if (stm != null && !stm.isClosed()) {
                stm.clearBatch();
                stm.close();
            }
            if (rss != null && !rss.isClosed()) {
                rss.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Überprüft ob eine Datenbanktabelle existiert.
     *
     * @param tablename
     * @return tableExists
     */
    private boolean tableExists(String tablename) {
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            DatabaseMetaData md = conn.getMetaData();
            rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                if (rs.getString(3).equals(tablename.toLowerCase())) {
                    return true;
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            cleanUp(null, rs);
        }
        return false;
    }
}
