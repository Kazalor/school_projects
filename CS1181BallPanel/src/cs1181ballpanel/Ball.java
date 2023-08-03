package cs1181ballpanel;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Lab 03
 * @author Trenton Brown
 * Lab Instructor: William Flaherty
 * Lecture Instructor: Dr. Doom
 * This class will be used to make the circles or "balls" for the animation
 */
public class Ball extends Circle {

    private int deltaX;
    private int deltaY;
    
    /**
     * This constructor is to help clean up the starting "CS1181BallPanel" file. 
     *  It takes the entered values in and then generates a ball to the specifications.
     * @param  x Location on the x axis
     * @param  y Location on the y axis
     * @param radius
     * @param dX deltaX
     * @param dY deltaY
     */
    public Ball(int x, int y, int radius, int dX, int dY) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(radius);
        deltaX = dX;
        deltaY = dY;
    }
    
    /**
     * This is a constructor that allows the creation of randomly sized and colored
     *  ball in a random location.
     * @param panelW panelWidth
     * @param panelH panelHeight
     */
    public Ball(int panelW, int panelH) {
        this.setRadius((int) (Math.random() * 10 + 1));
        this.setCenterX((int) (Math.random() * (panelW - this.getRadius())));
        this.setCenterY((int) (Math.random() * (panelH - this.getRadius())));
        deltaX = (int) (Math.random() * 10);
        deltaY = (int) (Math.random() * 10);

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        Color randomColor = Color.rgb(red, green, blue);
        this.setFill(randomColor);
    }

    /**
     * This method is running the bounds checks and updating the balls' locations.
     * @param pw panelWidth
     * @param ph panelHeight
     */
    public void updateLocale(int pw,int ph){
        // Comes from the original code in "CS1181BallPanel"
        // Check boundry contitions and update direction
        if (this.getCenterX() + this.getRadius() >= pw) {
            deltaX = -deltaX;
        } // end-if ball hits Right boundry
        if (this.getCenterY() + this.getRadius() >= ph) {
            deltaY = -deltaY;
        } // end-if ball hits Bottom boundry
        if (this.getCenterX() <= this.getRadius() ) {
            deltaX = -deltaX;
        } // end-if ball hits Left boundry
        if (this.getCenterY() <= this.getRadius() ) {
            deltaY = -deltaY;
        } // end-if ball hits Top boundry
        
        this.setCenterX(this.getCenterX() + deltaX);
        this.setCenterY(this.getCenterY() + deltaY);
    }
}
