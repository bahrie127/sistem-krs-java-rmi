/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stta.bahrie.sisterserver.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bahrie
 */
public class DatabaseUtilities {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sister_krs", "root", "root");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}
