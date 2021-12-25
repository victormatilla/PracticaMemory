package com.gf.vista;

import com.gf.adaptador.Adapter;
import com.gf.controlador.Controlador2;
import com.gf.controlador.ControladorPlayers;
import com.gf.modelo.dao.ImagesDAO;
import com.gf.modelo.entidades.Images;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author victor
 */
public class Match extends javax.swing.JFrame {

    private JPanel rigthPanel;
    private JPanel leftPanel;
    private int countDown;
    private ArrayList<Images> images;
    private JTextArea chronoZone;
    private ArrayList<JButton> buttons;
    private JButton imageToFind;
    private ArrayList<Images> imagesToFind;
    private int lastPosition;
    private ControladorPlayers controlPlayers;
    private Controlador2 control2;

    public Match() {
        initComponents();
        this.rigthPanel = new JPanel();
        this.leftPanel = new JPanel();
        this.countDown = 5;
        setLayout(new BorderLayout(20, 10));
        this.images = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.imageToFind = new JButton(new ImageIcon(".\\src\\main\\java\\com\\gf\\z_imagenes_reverso\\reverso.jpg"));
        this.imagesToFind = new ArrayList<>();
        setFrame();              
        setGameMode();        
        chronoZone = new JTextArea();
        generateRigthPanel();         
        control2=new Controlador2();        
        control2.setWindows(this);        
        countdown();        
        setExtendedState(MAXIMIZED_BOTH);        
    }

    public ArrayList<Images> getImages() {
        return images;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }

    public JTextArea getChronoZone() {
        return chronoZone;
    }

    public void setChronoZone(JTextArea chronoZone) {
        this.chronoZone = chronoZone;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<JButton> buttons) {
        this.buttons = buttons;
    }

    public Match(ArrayList<Images> imagesToFind) {
        this.imagesToFind = imagesToFind;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    private void setFrame() {

        setSize(700, 500);
        setTitle("Match");
        this.setLocationRelativeTo(null);

    }

    private void setGameMode() {
        Object[] games = {"Select one option", "Numbers", "Vowels", "Shapes"};
        String opcion = null;

        do {
            opcion = (String) JOptionPane.showInputDialog(null, "Select game mode", "Select", JOptionPane.QUESTION_MESSAGE, null, games, games[0]);

            if (opcion != null) {
                if (opcion.equals(games[0])) {
                    JOptionPane.showMessageDialog(this, games[0] + " is not a valid option", opcion, JOptionPane.ERROR_MESSAGE);
                }
            }

        } while (games[0].equals(opcion));

        initLeftPanel(Adapter.adapter(opcion));
    }

    private void countdown() {

        Thread hilo = new Thread() {

            public void run() {
                while (countDown >= 0) {

                    try {
                        Thread.sleep(1000);
                        chronoZone.setText("Segundos restante para memorizar --> " +String.valueOf(countDown));
                        countDown--;

                    } catch (Exception e) {

                    }
                }
                                  
                cronometro();
                startGame();
                orderToFind();
                setImageToFind();

            }
        };

        hilo.start();

    }

    private void cronometro() {

        Thread hilo2 = new Thread() {
            int segundos = 0;

            public void run() {
                while (segundos >= 0) {

                    try {
                        Thread.sleep(1000);
                        chronoZone.setText(String.valueOf(segundos) + " segundos");
                        segundos++;

                    } catch (InterruptedException e) {
                    }
                }

            }
        };

        hilo2.start();
    }

    private void setImages(String gameMode) {

        ImagesDAO allIMages = new ImagesDAO();
        ArrayList<Images> copy = allIMages.getImages(gameMode);
        int length = copy.size();

        while (this.images.size() < length) {
            int i = (int) (Math.random() * copy.size());

            this.images.add(copy.get(i));
            copy.remove(i);
        }

    }

    private void initLeftPanel(String gameMode) {

        setImages(gameMode);
        int cols = this.images.size() / 2;
        int rows = 2;

        if (this.images.size() % 2 != 0) {
            cols++;
        }
        generatePanelLeft(images, rows, cols);
    }

    private void generatePanelLeft(ArrayList<Images> ima, int rows, int cols) {

        this.leftPanel.setLayout(new GridLayout(rows, cols));

        int pos = 0; // it serves to get the ima´s possittion

        for (int i = 0; i < ima.size(); i++) {

            JButton boton = new JButton();
            boton.setMaximumSize(new Dimension(300, 190));
            boton.setIcon(new ImageIcon(".\\src\\main\\java\\com\\gf\\z_imagenes_reverso\\reverso.jpg"));
            boton.setDisabledIcon(ima.get(pos).getImagen());
            this.leftPanel.add(boton);
            this.buttons.add(boton);            
            pos++;
        }

        add(this.leftPanel, BorderLayout.CENTER);
        add(this.rigthPanel, BorderLayout.EAST);
    }

    private void generateRigthPanel() {
        this.rigthPanel.setLayout(new BorderLayout());
        this.rigthPanel.add(this.chronoZone, BorderLayout.BEFORE_FIRST_LINE);
        this.rigthPanel.add(this.imageToFind, BorderLayout.SOUTH);

        add(this.rigthPanel, BorderLayout.EAST);
    }

    public void startGame() {

        this.lastPosition = 0;
        int pos = 0;
        
        control2=new Controlador2();
        control2.setWindows(this);
        for (JButton b : buttons) {
            b.setEnabled(true);
            MouseListener[] mouseListeners = b.getMouseListeners();
            for (int i = 0; i < mouseListeners.length; i++) {
                b.removeMouseListener(mouseListeners[i]);             
            }
                       
        }
        controlPlayers=new ControladorPlayers();
        controlPlayers.setWindows(this);
        setControlator(controlPlayers);
        
    }

    private void orderToFind() {

        ArrayList<Images> copy = (ArrayList<Images>) this.images.clone();
        int length = copy.size();
        
        while (this.imagesToFind.size() < length) {
            int i = (int) (Math.random() * copy.size());

            this.imagesToFind.add(copy.get(i));
            copy.remove(i);
        }
        
    }

    public void setImageToFind() {

        this.imageToFind.setIcon(this.imagesToFind.get(lastPosition).getImagen());
    }

    public boolean imagesEquals(ImageIcon image1) {
                
        return image1.equals(imageToFind.getIcon());
    }

    public void setControlator(MouseListener mouse) {

        ArrayList<JButton> c = this.buttons;

        for (JButton j : c) {
            j.addMouseListener(mouse);
        }
    }
    
    public void setEnabled(int i){
        
        buttons.get(i).setEnabled(!buttons.get(i).isEnabled());
            
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
