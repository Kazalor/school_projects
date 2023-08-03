package cs1181.lab04.brown;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Lab 03
 * This class will be used to make the circles or "balls" for the animation
 */
/**
 * Lab 04
 * @author Trenton Brown
 * Lab Instructor: Dr. Doom
 * Lecture Instructor: Dr. Doom
 * The class is the same as Lab03 but with the directed changes so that a ball can
 * be added with a button, bounce off of another ball, and be removed if clicked on by the mouse
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
        this.setCenterX((int) (Math.random() * (panelW - this.getRadius()) + 1));
        this.setCenterY((int) (Math.random() * (panelH - this.getRadius()) + 1));
        deltaX = (int) (Math.random() * 10 + 1);
        deltaY = (int) (Math.random() * 10 + 1);

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
    
    /**
     * This method checks whether or not the ball and the ball passes in have collided
     * and if so it then calls bounce() on the first ball
     * @param ball The ball that was hit by this
     */
    public void detectCollision(Ball ball){
       // This variable holds the distance between the the ball and the ball passed
       // to the parameter
       double distance = Math.sqrt(Math.pow(this.getCenterX() - ball.getCenterX(), 2)
               + Math.pow(this.getCenterY() - ball.getCenterY(), 2));
       double radiusTotal = this.getRadius() + ball.getRadius();
       
       if(distance <= radiusTotal){
           this.bounce();
       }
       
    }
    
    /**
     * This method is used by the detectCollision method to move the ball in another
     * direction giving the effect that the ball bounced off another
     */
    public void bounce(){
        deltaX = -deltaX;
        deltaY = -deltaY;
        this.setCenterX(this.getCenterX() + deltaX);
        this.setCenterY(this.getCenterY() + deltaY);
    }
    
}
