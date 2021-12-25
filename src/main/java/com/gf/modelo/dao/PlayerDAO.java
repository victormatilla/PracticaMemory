/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PlayerDAO {
    
    public int maxID(){
        
        String sql ="SELECT max(id) \"max\" FROM ranking";
        
        int id=0;
        
        try(Connection con = Conexion.abrirConexion()){
            
            Statement ps=con.createStatement();
                                    
            ResultSet rs=ps.executeQuery(sql);
            rs.next();
            id=rs.getInt("max");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return id;
    }
}
