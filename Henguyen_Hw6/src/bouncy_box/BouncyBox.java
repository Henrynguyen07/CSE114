package bouncy_box;

import javafx.scene.paint.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Author Henry Nguyen
 * SBU ID: 111484010
 * Net ID: Henguyen
 * CSE 114
 */
public abstract class BouncyBox {
    protected double x;
    protected double y;
    protected double xVelocity;
    protected double yVelocity;
    protected double length = 0;
    protected boolean colliding;
    protected Paint color = Color.BLACK;
    protected double maxVelocity = 0;
    public BouncyBox(double x, double y, double xVelocity, double yVelocity, double length, boolean colliding, Paint color, double maxVelocity) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.length = length;
        this.colliding = colliding;
        this.color = color;
        this.maxVelocity = maxVelocity;
    }
    
    // Getter Methods
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getXVelocity() {
        return xVelocity;
    }
    public double getYVelocity() {
        return yVelocity;
    }
    public double getLength() {
        return length;
    }
    public boolean isColliding() {
        return colliding;
    }
    public Paint getColor() {
        return color;
    }
    public double getMaxVelocity() {
        return maxVelocity; 
    }
    
    // Setter Methods
    public void setX(double newX) {
        this.x = newX;
    }
    public void setY(double newY) {
        this.y = newY;
    }
    public void setXVelocity(double newXVelocity) {
        this.xVelocity = newXVelocity;
    }
    public void setYVelocity(double newYVelocity) {
        this.yVelocity = newYVelocity;
    }
    public void setLength(double newLength) {
        this.length = newLength;
    }
    public void setColliding(boolean newColliding) {
        this.colliding = newColliding;
    }
    public void setColor(Paint newColor) {
        this.color = newColor;
    }
    public void setMaxVelocity(double newMaxVelocity) {
        this.maxVelocity = newMaxVelocity;
    }
    public void update(int worldWidth, int worldHeight) {
        this.setX(this.getX() + this.getXVelocity());
        this.setY(this.getY() + this.getYVelocity());
        clampBounds(worldWidth, worldHeight);
        
    }
    public void clampBounds(int worldWidth, int worldHeight) {
        if (this.getX() < 0) {
            this.setX(0);
            this.setXVelocity(this.getXVelocity() * -1);
        } else if (this.getX() > (worldWidth - 74)) {
            this.setX(this.getX() - this.getXVelocity());
            this.setXVelocity(this.getXVelocity() * -1);
        }
        if (this.getY() < 0) {
            this.setY(0);
            this.setYVelocity(this.getYVelocity() * -1);
        } else if (this.getY() > (worldHeight - 58)) {
            this.setY(this.getY() - this.getYVelocity());
            this.setYVelocity(this.getYVelocity() * -1);
        }
    }
    public abstract void respondToCollision(BouncyBox otherBox);
    public abstract void respondToNoCollision();
    
}
