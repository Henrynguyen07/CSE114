/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bouncy_box;
import javafx.scene.paint.*;
/**
 *
 * Author Henry Nguyen
 * SBU ID: 111484010
 * Net ID: Henguyen
 * CSE 114
 */
public class PlayerBouncyBox extends BouncyBox {
    private double opacity;
    public PlayerBouncyBox (double playerX, double playerY, double playerXVelocity, double playerYVelocity, double playerLength) {
        super(playerX, playerY, playerXVelocity, playerYVelocity, playerLength, false, Color.GREEN, 20);
        this.opacity = 200;
    }
    public double getOpacity() {
        return opacity;
    }
    public void setOpacity(double newOpacity) {
        this.opacity = newOpacity;
    }
    
    @Override
    public void respondToCollision(BouncyBox otherBox) {
        super.setColor(Color.MAGENTA);
        super.setColliding(true);
        if(otherBox.getColor() == Color.RED) {
            super.setMaxVelocity(super.getMaxVelocity() - 1);
            this.setOpacity(this.getOpacity() - 10);
            this.setColor(this.color = new Color(0, 1, 0, opacity/200));
        }
//        if(otherBox instanceof PlayerBouncyBox) {
//            PlayerBouncyBox box = (PlayerBouncyBox)otherBox;
//        }
//        else if (otherBox instanceof GrowingBouncyBox) {
//            GrowingBouncyBox box = (GrowingBouncyBox) otherBox;
//            super.setMaxVelocity(super.getMaxVelocity() - 1);
//            this.setOpacity(this.getOpacity() - 10);
//        }
//        else {
//            ShrinkingBouncyBox box = (ShrinkingBouncyBox)otherBox;
//        }
    }
 
    @Override
    public void respondToNoCollision() {
        super.setColor(this.color = new Color(0, 1, 0, opacity/200));
    }
    
    public void moveLeft() {
        super.setXVelocity(this.getXVelocity() - this.getMaxVelocity());
    }
    public void moveRight() {
        super.setXVelocity(this.getXVelocity() + this.getMaxVelocity());
    }
    public void moveUp() {
        super.setYVelocity(this.getYVelocity() - this.getMaxVelocity());
    }
    public void moveDown() {
        super.setYVelocity(this.getYVelocity() + this.getMaxVelocity());
    }
    public void stopInX() {
        super.setXVelocity(0);
    }
    public void stopInY() {
        super.setYVelocity(0);
    }
}
