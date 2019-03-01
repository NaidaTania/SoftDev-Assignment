/*
 * Question 2
 * This code generates an interface that allows to calculate future value of investment
 * given the current investment amount, interest rate and specified number of years
 * */

//Import libraries
import javafx.application.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Q2 extends Application{
	
	public void start(Stage primaryStage) {
		//Set pane and padding/margin
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10,15,10,15));
		pane.setHgap(10);
		pane.setVgap(5);
		
		//Add 'investment amount' field
		pane.add(new Label("Investment Amount"), 0, 0);
		TextField inv = new TextField();
		inv.setAlignment(Pos.BOTTOM_RIGHT);
		pane.add(inv,1,0);
		
		//Add 'years' field
		pane.add(new Label("Years"), 0, 1);
		TextField yr = new TextField();
		yr.setAlignment(Pos.BOTTOM_RIGHT);
		pane.add(yr,1,1);
		
		//Add 'annual interest rate' field
		pane.add(new Label("Annual Interest Rate"), 0, 2);
		TextField ann = new TextField();
		ann.setAlignment(Pos.BOTTOM_RIGHT);
		pane.add(ann,1,2);
		
		//Add 'future value' field
		pane.add(new Label("Future Value"), 0, 3);
		TextField fu = new TextField();
		fu.setStyle("-fx-control-inner-background: LIGHTGREY"); //set background of textfield to grey
		fu.setAlignment(Pos.BOTTOM_RIGHT);
		pane.add(fu,1,3);
		
		//Add 'calculate' button
		Button btn = new Button("Calculate");
		pane.add(btn,1,4);
		GridPane.setHalignment(btn, HPos.RIGHT);
		
		//Set button to calculate future value on click
		btn.setOnAction(e->{
			try {
				//Get data from textfields
				double inv_d = Double.parseDouble(inv.getText());
				double yr_d = Double.parseDouble(yr.getText());
				double ann_d = Double.parseDouble(ann.getText());
				
				//Calculate future value and send value to textfield
				double fu_d = inv_d * (Math.pow(1+(ann_d/1200),yr_d*12.0));
				fu.setText(String.format ("%.2f", fu_d));
			}
			catch (Exception ex){
				//Print error where value inserted is not numerical
				System.out.println("Please only enter numerical values.");
			}
		});
		
		//Set pane on scene, place scene on stage and show stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Q2: Investment Value Calculator"); // Set the stage title
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
