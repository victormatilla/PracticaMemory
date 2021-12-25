
package com.gf.modelo.dao;

import com.gf.modelo.conexion.Conexion;
import com.gf.modelo.entidades.Images;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class ImagesDAO {
    
    public ArrayList<Images> getImages(String image){
        
        String sql ="SELECT imagen FROM ";
        ArrayList<Images> images=new ArrayList<>();
        
        try(Connection con = Conexion.abrirConexion()){
            
            Statement ps=con.createStatement();
                                    
            ResultSet rs=ps.executeQuery(sql+image);
                        
            while (rs.next()) {
                                
                images.add(new Images(rs.getBlob("imagen")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return images;
    }
    
}
