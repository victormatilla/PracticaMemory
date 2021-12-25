
package com.gf.vista;

import com.gf.modelo.dao.PlayerDAO;
import com.gf.modelo.dao.RankingDAO;
import com.gf.modelo.entidades.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author matjorvi
 */
public class ViewRanking extends javax.swing.JFrame {


    private JLabel title;
    private JLabel possittion;
    private RankingDAO rd;
    private DefaultTableModel model;
    private JTable jTableRanking;
    private PlayerDAO playerDAO;
    
    public ViewRanking() {
        initComponents();
        this.rd=new RankingDAO();
        jTableRanking=new JTable();
        playerDAO=new PlayerDAO();
        
        setLayout(new BorderLayout(2,2));
        
        init();
        loadDatabe();

    }
    
    private void init(){
        
        initTitle();
        initRankingTable();
        initPossittion();
        setLocationRelativeTo(null);
    }
    
    private void initTitle(){
        title = new javax.swing.JLabel();
        title.setFont(new Font("Engravers MT", 1, 24));
        title.setText("RANKING");
        title.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel panel=new JPanel();
        panel.add(title);
        add(panel,BorderLayout.NORTH);
                
    }
    
    private void initRankingTable(){
        RankingDAO r=new RankingDAO();        
        model=new DefaultTableModel(null,getNamesColums());        
        this.jTableRanking.setModel(model);   
        
        add(new JScrollPane(jTableRanking),BorderLayout.CENTER);
    }
    
    private void initPossittion(){
        possittion=new JLabel();
        possittion.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel panel=new JPanel();
        panel.add(possittion);
        add(panel,BorderLayout.SOUTH);
    }

    public JTable getjTableRanking() {
        return jTableRanking;
    }

    
    private List<Player>getData(){
        return rd.classification();
    }
    
    private void loadDatabe(){        
        List<Player> ranking=rd.classification();
        int maxID=playerDAO.maxID();
        
        int i=0;
        for (Player player : ranking) {

            Object[] o={player.getPossition(),player.getId(),player.getTime()};
            model.addRow(o);
            
            i++;
            if(player.getId()==maxID){
                this.possittion.setText("Your possition--> "+String.valueOf(i));
            }
        }
                        
    }
        
    private Object[] getNamesColums(){
                
        return rd.columnsNames();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
