
package com.gf.modelo.entidades;

/**
 *
 * @author victor
 */
public class Player {
    
    private int possition;
    private int id;
    private int time;

    public Player() {
    }

    public Player(int time) {
        this.time = time;
    }

    public Player(int possition, int id, int time) {
        this.possition = possition;
        this.id = id;
        this.time = time;
    }    

    public int getPossition() {
        return possition;
    }

    public void setPossition(int possition) {
        this.possition = possition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }      

    @Override
    public String toString() {
        return "Posicion " +possition + " Id: " + id + "--" + time;
    }
        
}
