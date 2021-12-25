/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gf.adaptador;

/**
 *
 * @author victor
 */
public class Adapter {
    
    public static String adapter(String gameMode){
        
        switch(gameMode.toLowerCase()){
            
            case "numbers": 
                return "numeros";
            
            case "shapes":
                return "formas";
        }
        
        return "vocales";
    }
}
