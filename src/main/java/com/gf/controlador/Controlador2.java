/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gf.controlador;

import com.gf.modelo.dao.RankingDAO;
import com.gf.vista.Match;
import com.gf.vista.ViewRanking;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author matjorvi
 */
public class Controlador2 implements MouseListener{
    
    private ViewRanking viewRanking;
    private Match match;
    private RankingDAO rankingDAO;

    public Controlador2() {
        rankingDAO = new RankingDAO();
    }

    public void setWindows(Match m) {
        match = m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
        ArrayList<JButton> i = match.getButtons();
        
        int con=0;
        while(i.get(con) != e.getSource()){            
            con++;
        }
        
        match.setEnabled(con);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ArrayList<JButton> i = match.getButtons();
        
        int con=0;
        while(i.get(con) != e.getSource()){
            con++;
        }
        
        match.setEnabled(con);
    }
    
}
