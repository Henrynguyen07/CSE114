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
public class GrowingBouncyBox extends BouncyBox {
    public GrowingBouncyBox(double growingX, double growingY, double growingXVelocity, double growingYVelocity, double growingLength) {
        super(growingX, growingY, growingXVelocity, growingYVelocity, growingLength,false, Color.RED, 30);
    }
    
    @Override
    public void respondToCollision(BouncyBox otherBox) {
        super.setColor(Color.ORANGE);
        super.setLength(super.getLength() + 20);
        double randX = (double) ((Math.random() * super.getMaxVelocity()) + 1);
        double randY = (double) ((Math.random() * super.getMaxVelocity()) + 1);
        double randDirection;
        if (Math.random() >= .5) {
            randDirection = 1;
        } else {
            randDirection = -1;
        }
        randX *= randDirection;
        if (Math.random() >= .5) {
            randDirection = 1;
        } else {
            randDirection = -1;
        }
        randY *= randDirection;
        this.setXVelocity(randX);
        this.setYVelocity(randY);
        super.setColliding(true);

    }
 
    @Override
    public void respondToNoCollision() {
        super.setColor(Color.RED);
    }
}
