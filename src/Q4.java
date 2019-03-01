import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.File;
import java.util.*;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Q4 extends Application{

	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		
		//Create hbox to hold the textfield and button
		HBox hBox = new HBox();
		Label id = new Label("Filename");
		TextField enterFile = new TextField();
		Button view = new Button("View");
		hBox.getChildren().addAll(id,enterFile,view);
		HBox.setHgrow(enterFile, Priority.ALWAYS);
		
		//Set hbox at the bottom of Borderpane
		pane.setBottom(hBox);
		
		//Create Pane to hold the histogram
		Pane grid = new Pane();
		pane.setCenter(grid);
		
		//Set the axis line
		Line l = new Line(30,450,510,450);
		l.setStrokeWidth(2);;
		grid.getChildren().add(l);
		
		//Set the axis
		String axis = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//Set hashtable to count number of alphabet occurences
		Hashtable<Character, Integer> count = new Hashtable<Character, Integer>();
		
		int pstn1 = 40;
		for (int i = 0; i < axis.length(); i++) {
			//Add alphabets one by one to form the horizontal axis
			Label n = new Label(String.valueOf(axis.charAt(i)));
			grid.getChildren().add(n);
			n.relocate(pstn1, 450);
			//Set gap
			pstn1 += 18;
			//Set alphabets as keys
			count.put(axis.charAt(i),0);
		}

		//On view button click
		view.setOnAction(e->{
			try {
				//read file content
				File file = new File(enterFile.getText());
				readFile(file,count);
				
				int pstn = 40;
				for (int i = 0; i < axis.length(); i++) {
					//Add rectangle for each alphabet
					Rectangle r = new Rectangle(10,count.get(axis.charAt(i)));
					r.setFill(null);
					r.setStroke(Color.BLACK);
					r.relocate(pstn, 450-count.get(axis.charAt(i)));
					pstn += 18;
					grid.getChildren().add(r);
				}
			}
			catch(Exception ex){
				System.out.println("Please enter a valid filename!");
			}
		});
		
		//On hitting Enter Key, same as View button
		enterFile.setOnAction(e -> {
			try {
				//read file content
				File file = new File(enterFile.getText());
				readFile(file,count);
				
				int pstn = 40;
				for (int i = 0; i < axis.length(); i++) {
					//Add rectangle for each alphabet
					Rectangle r = new Rectangle(10,count.get(axis.charAt(i)));
					r.setFill(null);
					r.setStroke(Color.BLACK);
					r.relocate(pstn, 450-count.get(axis.charAt(i)));
					pstn += 18;
					grid.getChildren().add(r);
				}
			}
			catch(Exception ex){
				System.out.println("Please enter a valid filename!");
			}
		});
		
		
		
		// Create a scene and place it in the stage
	    Scene scene = new Scene(pane,520,500);
	    primaryStage.setTitle("Question 4: Histogram"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	//Read file and count number of alphabet occurrences
	Hashtable readFile(File f, Hashtable<Character, Integer> count) throws Exception {
		Scanner input = new Scanner(f);
		String s = new String();
		while (input.hasNext()) {
			//read entire file into a string
			s += input.nextLine();
		}
		
		for (int i = 0; i < s.length(); i++) {
			//loop through string to count number of alphabet occurrences
			char c = Character.toUpperCase(s.charAt(i));
			if(c >= 'A' && c <= 'Z') {
				if(count.containsKey(c)) {
					count.put(c,count.get(c) + 1);
				}
			}
		}
		input.close();
		return count;
	}

}
