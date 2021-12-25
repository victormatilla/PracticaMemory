
package com.gf.modelo.dao;

import com.gf.modelo.conexion.Conexion;
import com.gf.modelo.entidades.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor
 */
public class RankingDAO {
        
    public int insert(Player player){
        
        String sql="INSERT INTO ranking(tiempo) VALUES(?)";
        
        try(PreparedStatement pst=Conexion.abrirConexion().prepareStatement(sql)){
            
            pst.setInt(1, player.getTime());
            return pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public List<Player> classification(){
        
        String sql="SELECT * FROM ranking ORDER BY tiempo";
        
        List<Player> players=null;
        try(Statement st=Conexion.abrirConexion().createStatement()){
            
            players=new ArrayList<>();
            ResultSet rs=st.executeQuery(sql);                        
            
            int position=1;
            while(rs.next()){
                players.add(new Player(position,rs.getInt(1), rs.getInt(2)));
                position++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return players;
    }
    
    public Object[] columnsNames(){
        
        String sql="SELECT * FROM ranking";
        Object[] names=null;
        
        try(Statement st=Conexion.abrirConexion().createStatement()){
            
            ResultSet rs=st.executeQuery(sql);
            
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int columnsNumber=rsmd.getColumnCount();
            names=new Object[columnsNumber+1];
            
            names[0]="Posicion";
            for (int i = 1; i <= columnsNumber; i++) {
                names[i]=rsmd.getColumnName(i);
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(RankingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return names;
    }

}
