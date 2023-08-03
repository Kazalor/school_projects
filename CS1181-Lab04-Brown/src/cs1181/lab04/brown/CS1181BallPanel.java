/**
 * CS1181 Lab Starter Code
 * Trenton Brown
 * Lab Section: XX
 * Lab Instructor: Lorem Ipsum
 * Lecture Instructor: Dr. Doom
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
 * 
 * Lab 04 changes
 * A loop was added to the handle method to make the balls bounce off of each other.
 * A button was also added so that if the user wanted more balls on the screen 
 * after the prompting for a quantity, they may do so. A MouseEvent was also added
 * so that when the user clicks on a ball with the mouse the ball is removed.
 */

package cs1181.lab04.brown;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class CS1181BallPanel extends Application implements EventHandler<ActionEvent> {

    private int INITIAL_WIDTH_IN_PIXELS = 800;
    private int INITAL_HEIGHT_IN_PIXELS = 600;

    ArrayList<Ball> ballList = new ArrayList<Ball>();
    
    // This variable will hold the ball that was pressed and set it to be
    // removed at the end after the end of all "for" loops
    private Ball ballToRemove;
    
    // Pane instantiation was moved so that it could be accessed by the handle
    // method so the mouse event can remove a ball while avoiding an exception
    private Pane pane = new Pane();
    private Scene scene;

    @Override
    public void start(Stage stage) {
        
        // Initialite the Stage
        stage.setTitle("CS1181 Ball Panel");
        scene = new Scene(pane, INITIAL_WIDTH_IN_PIXELS, INITAL_HEIGHT_IN_PIXELS);
        stage.setScene(scene);
        stage.show();

        // Initialize the animation loop
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), this);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
                
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
        System.out.print("How many balls would you like to add to the pane? ");
        int numberOfBalls = in.nextInt();
        for(int i = 0; i < numberOfBalls; i++){
            Ball n = new Ball(INITIAL_WIDTH_IN_PIXELS, INITAL_HEIGHT_IN_PIXELS);
            ballList.add(n);
        }
        
        pane.getChildren().addAll(ballList);
        
        // The button is created and named "Add ball" after its intended function
        Button btn = new Button();
        btn.setText("Add ball");
        btn.setLayoutX(350);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            // The button will use this handle method to create and then add balls
            // to the pane and the list so that the animation will be applied to the new balls
            @Override
            public void handle(ActionEvent event) {
                Ball m = new Ball(INITIAL_WIDTH_IN_PIXELS, INITAL_HEIGHT_IN_PIXELS);
                pane.getChildren().add(m);
                ballList.add(m);
            }
        });
        
        // Event was set so that the balls, if any, and if clicked on, the ball
        // is removed from the pane and from the list
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // This will search the list to see which ball qualifies as being
                // pressed and then assign the ball to the removal variable so
                // exceptions may be avoided during the removal process
                for (Ball i: ballList){
                    if(i.isPressed()){
                        ballToRemove = i;
                    }
                }
            }
        });
        
        pane.getChildren().add(btn);
        
    } // end method start

    public static void main(String[] args) {
        launch(args);
    } // end method main

    /**
     * This method updates the frame/scene. It is called automatically by the
     * animation time line every animation cycle.
     *
     * @param event Contains the specific information for the time line clock
     * event
     */
    @Override
    public void handle(ActionEvent event) {
        
        // For loop iterates through each ball in the list which is on the pane 
        // and then passes them into the "checkLocale" method in the Ball class
        // which then moves the balls around the pane.
        for(Ball i: ballList){
            i.updateLocale(INITIAL_WIDTH_IN_PIXELS, INITAL_HEIGHT_IN_PIXELS);
            // This change is fro Lab04, it will check for each ball if it's collided
            // with another ball and then behave accordingly
            for(Ball j: ballList){
                // If statement is needed to make sure the ball doesn't get checked
                // against itself causing it to appear as though it's not moving
                // or moving in a constricted space and direction
                if(i != j){
                  i.detectCollision(j);  
                }
            }
        }
        
        // At the end of the for loop cycle the ball, if any, that is assigned
        // to the ballToRemove variable will be removed from the list and the pane.
        ballList.remove(ballToRemove);
        pane.getChildren().remove(ballToRemove);

    } // end method handle

} // end class
