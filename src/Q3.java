import javafx.application.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;

/*
 * Click on red point to drag. 
 * Only drag around the perimeter (it might be a bit difficult to move the pts around :( )
 * */


public class Q3 extends Application{
	
	public void start(Stage primaryStage) {
		
		Pane root = new Pane();
		
		//Set circle
		int radius = 100;
		Circle circ = new Circle(radius+20,radius+10,radius);
		root.getChildren().add(circ);
		circ.setFill(null);
		circ.setStroke(Color.BLACK);
		
		//Set points
		Circle pt1 = new Circle(20,radius,5);
		pt1.setFill(Color.RED);
		pt1.setStroke(Color.BLACK);
		Circle pt2 = new Circle(20+(radius*2),radius,5);
		pt2.setFill(Color.RED);
		pt2.setStroke(Color.BLACK);
		Circle pt3 = new Circle(radius+20,10,5);
		pt3.setFill(Color.RED);
		pt3.setStroke(Color.BLACK);
		
		//Set lines		
		Line l12 = new Line(20,radius,20+(radius*2),radius); 
		Line l23 = new Line(20+(radius*2),radius,radius+20,10);
		Line l13 = new Line(20,radius,radius+20,10);
		
		
		//Set dynamically changing angle of 1st point
		Label angle1 = new Label();
		angleStr angle1String = new angleStr();
		angle1.textProperty().bind(angle1String.str);
		root.getChildren().add(angle1);
		pt1.setOnMouseDragged(e->{
			//If point is dragged within circle
			if(circ.contains(e.getX(), e.getY())){
				//Reajust point to follow mouse pointer
                pt1.setCenterX(e.getSceneX());
                pt1.setCenterY(e.getSceneY());
                //Readjust the lines
                l13.setStartX(pt1.getCenterX());
                l13.setStartY(pt1.getCenterY());
                l12.setStartX(pt1.getCenterX());
                l12.setStartY(pt1.getCenterY());
                //Calculate the length of the sides
                //a = the side opposite to the angle we are looking for
                double a = getLength(l23.getStartX(),l23.getEndX(),l23.getStartY(),l23.getEndY());
                double b = getLength(l13.getStartX(),l13.getEndX(),l13.getStartY(),l13.getEndY());
                double c = getLength(l12.getStartX(),l12.getEndX(),l12.getStartY(),l12.getEndY());
                //Update angle value
                angle1String.setStr(Double.toString(getAngle(a,b,c)));
                angle1.setLayoutX(pt1.getCenterX());
                angle1.setLayoutY(pt1.getCenterY());
			}
		});
		
		pt1.setOnMouseReleased(e->{});
		
		//Repeat for 2nd point
		Label angle2 = new Label();
		angleStr angle2String = new angleStr();
		angle2.textProperty().bind(angle2String.str);
		root.getChildren().add(angle2);
		pt2.setOnMouseDragged(e->{
			if(circ.contains(e.getX(), e.getY())){
                pt2.setCenterX(e.getSceneX());
                pt2.setCenterY(e.getSceneY());
                l23.setStartX(pt2.getCenterX());
                l23.setStartY(pt2.getCenterY());
                l12.setEndX(pt2.getCenterX());
                l12.setEndY(pt2.getCenterY());
                double b = getLength(l23.getStartX(),l23.getEndX(),l23.getStartY(),l23.getEndY());
                double a = getLength(l13.getStartX(),l13.getEndX(),l13.getStartY(),l13.getEndY());
                double c = getLength(l12.getStartX(),l12.getEndX(),l12.getStartY(),l12.getEndY());
                angle2String.setStr(Double.toString(getAngle(a,b,c)));
                angle2.setLayoutX(pt2.getCenterX());
                angle2.setLayoutY(pt2.getCenterY());
			}
		});
		pt2.setOnMouseReleased(e->{});
		
		//Repeat for 3rd point
		Label angle3 = new Label();
		angleStr angle3String = new angleStr();
		angle3.textProperty().bind(angle3String.str);
		root.getChildren().add(angle3);
		pt3.setOnMouseDragged(e->{
			if(circ.contains(e.getX(), e.getY())){
                pt3.setCenterX(e.getSceneX());
                pt3.setCenterY(e.getSceneY());
                l23.setEndX(pt3.getCenterX());
                l23.setEndY(pt3.getCenterY());
                l13.setEndX(pt3.getCenterX());
                l13.setEndY(pt3.getCenterY());
                double c = getLength(l23.getStartX(),l23.getEndX(),l23.getStartY(),l23.getEndY());
                double b = getLength(l13.getStartX(),l13.getEndX(),l13.getStartY(),l13.getEndY());
                double a = getLength(l12.getStartX(),l12.getEndX(),l12.getStartY(),l12.getEndY());
                angle3String.setStr(Double.toString(getAngle(a,b,c)));
                angle3.setLayoutX(pt3.getCenterX());
                angle3.setLayoutY(pt3.getCenterY());
			}
		});
		pt3.setOnMouseReleased(e->{});
		
		//Add points to pane
		root.getChildren().addAll(l12,l23,l13);
		root.getChildren().addAll(pt1,pt2,pt3);
		
		Scene scene = new Scene(root,400,300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Question 3: Dragging points in a circle");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);

	}
		
	double getAngle(double a, double b, double c) {
		//Calculate angle using cosine rule
		return Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
	}
	
	double getLength(double x1, double x2, double y1, double y2) {
		//Get length using starting and ending coordinates of Line
		double w = Math.abs(x1-x2);
		double h = Math.abs(y1-y2);
		return Math.sqrt(Math.pow(w,2) + Math.pow(h,2));
	}
	
	class angleStr {
	    // Define a variable to store the property
	    private StringProperty str = new SimpleStringProperty();
	 
	    // Define a getter for the property's value
	    public final String getstr(){return str.get();}
	 
	    // Define a setter for the property's value
	    public final void setStr(String value){str.set(value);}
	 
	     // Define a getter for the property itself
	    public StringProperty strProperty() {return str;}
	}
		

}
