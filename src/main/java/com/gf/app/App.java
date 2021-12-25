/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gf.app;

import com.gf.controlador.Controlador2;
import com.gf.vista.Match;

/**
 *
 * @author victor
 */
public class App {
    public static void main(String[] args) {
        
        Match m= new Match();
        Controlador2 controller=new Controlador2();
        
        m.setControlator(controller);
        controller.setWindows(m);
        
        m.setVisible(true);
        
    }
}
