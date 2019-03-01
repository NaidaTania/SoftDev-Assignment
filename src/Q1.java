/*
 * Question 1
 * This code generates random number between 1-54 
 * Black suit cards will have blue backside and red suit cards will have red backside
 * Upon click, the face of the randomly generated card will be revealed
 * */

//Import libraries
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class Q1 extends Application{

	public void start(Stage primaryStage) {
		
		//Create random instance
		Random rand = new Random();
		
		//Set up pane
		GridPane root = new GridPane(); 
	    root.setPadding(new Insets(5, 10, 5, 10));
	    root.setVgap(5.5);
	    root.setHgap(5.5);
	    
	    //Add instruction
	    root.add(new Label("Click to reveal the cards!"), 0,0,3,1); //Set label to span 3 columns and 1 row
	    
	    //Set array of imageview for back of the cards
	    ImageView[] images = new ImageView[3];
	    ImageView back;
	    
	    //To check for duplicates
	    Integer numArr[] = new Integer[3];
	    
	    //Choose 3 random cards
	    for(int i=0;i<3;i++) {
	    	//generates random number from 1 to 54
			int n = rand.nextInt(54) + 1;
			
			//Check for duplicate number
			if(i == 1) {
				while(n == numArr[0]) {
					n = rand.nextInt(54) + 1;
				}
			}
			else if(i == 2) {
				while(n == numArr[0] || n == numArr[1]) {
					n = rand.nextInt(54) + 1;
				}
			}
			numArr[i] = n;
			
			//Prepare and call image associated with the random number
			String cardnum = Integer.toString(n);
			String filename = "Cards/" + cardnum + ".png";
			ImageView front = new ImageView(new Image(filename));
			//System.out.println(filename);
			
			//Add card to pane
		    root.add(front,i,1);
		    
		    //Decide if the back of the card is blue or red based on suit
			if(n < 14 || n > 39) {
				back = new ImageView(new Image("Cards/backCard.png"));
			}
			else {
				back = new ImageView(new Image("Cards/b2fv.png"));
			}
			//Add back of the card to array and pane
			images[i] = back;
			root.add(back,i,1);
		}
		
	    //Set back of card to reveal front of card on click
		images[0].setOnMousePressed(e -> {
				images[0].setImage(null);
	        });
		
		images[1].setOnMousePressed(e -> {
			images[1].setImage(null);
        });
		
		images[2].setOnMousePressed(e -> {
			images[2].setImage(null);
        });
		
	    //Set pane on scene, place scene on stage and show stage
	    Scene scene = new Scene(root);
	    primaryStage.setTitle("Question 1: Cards"); // Set the stage title
	    primaryStage.setScene(scene); 
	    primaryStage.show(); 	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
