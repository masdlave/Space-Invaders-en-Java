package com.gamedev.dax06;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity{

    public Player(){
        // start position
        x = 280;
        y = 380;
        vx = 0;
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 40, 10);       // base
        g.fillRect(x+18, y-7, 4, 7);    // cañon
        //g.dispose();
    }

    @Override
    public void move() {
        x = x + vx;
        if(x <= 0) x = 0;
        if(x > 560) x = 560;
    }

    public void setVx(int vx){
        this.vx = vx;
    }

    /*
     * devuelve la coordenada x del jugador
     */
    public int getX(){
        return this.x;
    }

}
