

package com.gf.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author victor
 */
public class Conexion {
        
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/memory";
    
    public static Connection abrirConexion() throws SQLException{        
        return DriverManager.getConnection(url, user, password);
    }
}
