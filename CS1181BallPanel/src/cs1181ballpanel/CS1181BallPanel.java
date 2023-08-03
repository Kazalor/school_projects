/**
 * 
 * Citations: Final lab code is based on sample code distributed as part of
 * the laboratory assignment/requirements.
 * 
 * Instructions were removed. The original instantiation of the balls were removed
 * and the values that were originally used to make them were passed into the ball
 * class constructor, same with the private ints that weren't needed or used with
 * the ball class. An ArrayList was made to put the balls into so that regardless
 * of the quantity they could be easily animated to move around the screen.
 * The bounds checks were put into a method within the ball class so that there 
 * didn't need to be an individual set of "if" statements for each ball. A scanner
 * was also made so that a user would be prompted to add any number of balls they
 * so choose to the pane.
 */

package cs1181ballpanel;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;


public class CS1181BallPanel extends Application implements EventHandler<ActionEvent> {

    private int INITIAL_WIDTH_IN_PIXELS = 800;
    private int INITAL_HEIGHT_IN_PIXELS = 600;

    ArrayList<Ball> ballList;
    
    private Scene scene;

    @Override
    public void start(Stage stage) {
        
        // Initialize the Stage
        stage.setTitle("CS1181 Ball Panel");
        Pane pane = new Pane();
        scene = new Scene(pane, INITIAL_WIDTH_IN_PIXELS, INITAL_HEIGHT_IN_PIXELS);
        stage.setScene(scene);
        stage.show();

        // Initialize the animation loop
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), this);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        // Initialize the ArrayList so all balls can be passed into handle
        ballList = new ArrayList<Ball>();
                
        // Initialize two Balls and add them to the list
        Ball one = new Ball(50, 200, 30, 2, -1);
        one.setFill(Color.RED);

        Ball two = new Ball(500, 400, 40, -3, -3);
        two.setFill(Color.BLUE);
        
        ballList.add(one);
        ballList.add(two);
        
        // The scanner is used to get the number of balls the user would like to add to the pane
        // and passes that input into the for loop to generate each of those balls.
        Scanner in = new Scanner(System.in);
        System.out.println("How many balls would you like to add to the pane? ");
        int numBs = in.nextInt();
        for(int i = 0; i < numBs; i++){
            Ball n = new Ball(INITIAL_WIDTH_IN_PIXELS, INITAL_HEIGHT_IN_PIXELS);
            ballList.add(n);
        }
        
        pane.getChildren().addAll(ballList);
        
    } // end method start

    public static void main(String[] args) {
        launch(args);
    } // end method main

    /**
     * This method updates the frame/scene. It is called automatically by the
     * animation timeline every animation cycle.
     *
     * @param event Contains the specific information for the timeline clock
     * event
     */
    @Override
    public void handle(ActionEvent event) {
        
        // For loop iterates through each ball in the list which is on the pane 
        // and then passes them into the "checkLocale" method in the Ball class
        // which then moves the balls around the pane.
        for(Ball i: ballList){
            i.updateLocale(INITIAL_WIDTH_IN_PIXELS, INITAL_HEIGHT_IN_PIXELS);
        }


    } // end method handle

} // end class
