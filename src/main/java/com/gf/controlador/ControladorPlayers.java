/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gf.controlador;

import com.gf.modelo.dao.RankingDAO;
import com.gf.modelo.entidades.Images;
import com.gf.modelo.entidades.Player;
import com.gf.vista.Match;
import com.gf.vista.ViewRanking;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author matjorvi
 */
public class ControladorPlayers implements MouseListener {

    private ViewRanking viewRanking;
    private Match match;
    private RankingDAO rankingDAO;

    public ControladorPlayers() {
        rankingDAO = new RankingDAO();
    }

    public void setWindows(Match m) {
        match = m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int pos = 0;
        ArrayList<JButton> i = match.getButtons();
        for (JButton b : i) {

            if (e.getSource() == b) {
                if (match.imagesEquals((ImageIcon) b.getDisabledIcon())) {

                    match.setEnabled(pos);
                    match.setLastPosition(match.getLastPosition() + 1);

                } else {
                    match.startGame();
                }
            }
            if (match.getButtons().size() - match.getLastPosition() == 0) {                
                String cadena = match.getChronoZone().getText();
                rankingDAO.insert(new Player(Integer.valueOf(cadena.substring(0, cadena.indexOf(" ")))));
                viewRanking = new ViewRanking();
                viewRanking.setVisible(true);
                this.match.dispose();
            } else {
                match.setImageToFind();
                pos++;
            }

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
