package com.gamedev.dax07;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Bullet class
 * @author dax
 */
public class Bullet extends Entity{

    // flag para establecer si un disparo està en juego o no
    private boolean inGame;
    int vy = -5;    // el disparo se mueve desde abajo hacia arriba

    public Bullet(){
        this.x = this.y = -10;    // fake coordinates
        this.inGame = false;
    }

    /*
     * Constructor
     * x = coordenada x del Player
     */
    public Bullet(int x){
        // el disparo està en juego
        this.inGame = true;
        // coordenadas x - y
        this.x = x;
        this.y = 356;
    }

    /*
    public int setX(int x){
        return this.x = x;
    }
    */

    public boolean isInGame(){
        return inGame;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 4, 6);
    }

    @Override
    public void move() {
        y = y + vy;
        if(y <= -6){    // cuando sale del JFrame...
            // bullet.destroy();
            // tolgo dal gioco
            this.inGame = false;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 4, 6);
    }

}
