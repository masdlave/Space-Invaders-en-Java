package com.gamedev.dax05;

import java.awt.Graphics;

public abstract class Entity {

    protected int x;    // position x
    protected int y;    // position y
    protected int vx;   // horizontal velocity
    protected int vy;   // vertical velocity

    public abstract void draw(Graphics g);

    public abstract void move();

}
