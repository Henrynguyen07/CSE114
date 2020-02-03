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
public class ShrinkingBouncyBox extends BouncyBox{
    public ShrinkingBouncyBox (double ShrinkingX, double ShrinkingY, double ShrinkingXVelocity, double ShrinkingYVelocity, double ShrinkingLength) {
        super (ShrinkingX, ShrinkingY, ShrinkingXVelocity, ShrinkingYVelocity, ShrinkingLength, false, Color.BLUE, 0);
    }
    
    @Override
    public void respondToCollision(BouncyBox otherBox) {
        super.setColor(Color.CYAN);
        super.setLength(super.getLength() - 10);
        super.setXVelocity(super.getXVelocity() * -2);
        super.setYVelocity(super.getYVelocity() * -2); 
        super.setColliding(true);
    }
 
    @Override
    public void respondToNoCollision() {
        super.setColor(Color.BLUE);
    }
}
