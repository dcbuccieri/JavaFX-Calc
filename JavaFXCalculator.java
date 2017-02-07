import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;
import javafx.geometry.NodeOrientation;
import java.lang.*;

public class JavaFXCalculator extends Application {
	// Class is modified version of ZyBooks 17.3 Input and event handlers example from ZyBooks.com Introduction to Java module
	// TODO: Handle large numbers without scientific notation
	// 	 Fix the scrolling top TextField with "large" strings of input
	
	// Displays text field at the top 
	private TextField display;
	private TextField displayB; 	

	// declare calculator buttons
	private Button nine;
	private Button eight;
	private Button seven;
	private Button six;
	private Button five;
	private Button four;
	private Button three;
	private Button two;
	private Button one;
	private Button zero;
	private Button plus;
	private Button minus;
	private Button multiply;
	private Button divide;
	private Button decimal;
	private Button equals;
	private Button clear;
	private Button back;
   
	// create inputs, one for the first part of equation, one for second, one to hold the operator, one to hold result
	String inputOne = "";
	String inputTwo = "";
	String operationType = "";
	String result;
	
	// alternate display, shows in the top TextField so user can track their equation
	String alternateText = "";
	
	// true if we're working on inputOne, false if inputTwo
	boolean firstInput = true;
	   
	public void clear(){
		operationType = "";
		inputOne = "";
		inputTwo = "";
		display.setText("");
		displayB.setText("");
		alternateText = "";
		firstInput = true;
	}
   
	@Override
	public void start(Stage applicationStage) {
		// tell the user what C and B do
		Alert alert = new Alert(AlertType.INFORMATION, "C resets everything. B removes last digit of current value.");
		alert.showAndWait();

		Scene scene = null;         // Scene contains all content
		Pane pane = null;
		GridPane gridPane = null;   // Positions components within scene

		gridPane = new GridPane();   // Create an empty pane
		pane = new Pane();
		pane.setStyle("-fx-background-color: black;");
		scene = new Scene(pane); // Create scene containing the grid pane

		// create main output field
		display = new TextField(); 
		display.setEditable(false);
		display.setText("");
		display.setAlignment(Pos.CENTER_RIGHT);
		//display.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);  TODO: USE THIS?
		display.setPrefWidth(225);
		display.relocate(0,25);
		
		// create secondary output field
		displayB = new TextField(); 
		displayB.setEditable(false);
		displayB.setText("");
		displayB.setAlignment(Pos.CENTER_RIGHT);
		// displayB.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT); TODO: USE THIS?
		displayB.setPrefWidth(225);
		
		   
		// create each button with some padding
		nine = new Button("9");
		nine.setPadding(new Insets(8,8,8,8));
		nine.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		nine.setPrefSize(40,40);
		eight = new Button("8");
		eight.setPadding(new Insets(8,8,8,8));
		eight.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		eight.setPrefSize(40,40);
		seven = new Button("7");
		seven.setPadding(new Insets(8,8,8,8));
		seven.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		seven.setPrefSize(40,40);
		six = new Button("6");
		six.setPadding(new Insets(8,8,8,8));
		six.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		six.setPrefSize(40,40);
		five = new Button("5");
		five.setPadding(new Insets(8,8,8,8));
		five.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		five.setPrefSize(40,40);
		four = new Button("4");
		four.setPadding(new Insets(8,8,8,8));
		four.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		four.setPrefSize(40,40);
		three = new Button("3");
		three.setPadding(new Insets(8,8,8,8));
		three.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		three.setPrefSize(40,40);
		two = new Button("2");
		two.setPadding(new Insets(8,8,8,8));
		two.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		two.setPrefSize(40,40);
		one = new Button("1");
		one.setPadding(new Insets(8,8,8,8));
		one.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		one.setPrefSize(40,40);
		zero = new Button("0");
		zero.setPadding(new Insets(8,8,8,8));
		zero.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
		zero.setPrefSize(40,40);
		plus = new Button("+");
		plus.setPadding(new Insets(8,8,8,8));
		plus.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #0000ff;");
		plus.setPrefSize(40,40);
		minus = new Button("-");
		minus.setPadding(new Insets(8,8,8,8));
		minus.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #0000ff;");
		minus.setPrefSize(40,40);
		multiply = new Button("*");
		multiply.setPadding(new Insets(8,8,8,8));
		multiply.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #0000ff;");
		multiply.setPrefSize(40,40);
		divide = new Button("/");
		divide.setPadding(new Insets(8,8,8,8));
		divide.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #0000ff;");
		divide.setPrefSize(40,40);
		decimal = new Button(".");
		decimal.setPadding(new Insets(8,8,8,8));
		decimal.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #0000ff;");
		decimal.setPrefSize(40,40);
		equals = new Button("=");
		equals.setPadding(new Insets(8,8,8,8));
		equals.setStyle("-fx-background-color: #ff0000; -fx-font-size: 2em; -fx-text-fill: #0000ff;");
		equals.setPrefSize(40,40);
		clear = new Button("C");
		clear.setPadding(new Insets(8,8,8,8));
		clear.setStyle("-fx-background-color: #ffffff; -fx-font-size: 2em; -fx-text-fill: #ff00ff;");
		clear.setPrefSize(40,40);
		back = new Button("B");
		back.setPadding(new Insets(8,8,8,8));
		back.setStyle("-fx-background-color: #ffffff; -fx-font-size: 2em; -fx-text-fill: #00ffff;");
		back.setPrefSize(40,40);

		// add the display then the gridpane to the pane 
		pane.getChildren().addAll(display, displayB, gridPane);

		gridPane.setPadding(new Insets(50, 10, 10, 24)); // Padding around  grid
		gridPane.setHgap(4);                            // Spacing between columns
		gridPane.setVgap(4);                            // Spacing between rows

		// add each button to the grid
		gridPane.add(nine, 0, 1);
		gridPane.add(eight, 1, 1);
		gridPane.add(seven, 2, 1);
		gridPane.add(divide, 3, 1);
		gridPane.add(six, 0, 2);
		gridPane.add(five, 1, 2);
		gridPane.add(four, 2, 2);
		gridPane.add(multiply, 3, 2);
		gridPane.add(three, 0, 3);
		gridPane.add(two, 1, 3);
		gridPane.add(one, 2, 3);
		gridPane.add(minus, 3, 3);
		gridPane.add(zero, 0, 4);
		gridPane.add(equals, 1, 4);
		gridPane.add(decimal, 2, 4);
		gridPane.add(plus, 3, 4);
		gridPane.add(clear, 1, 5);
		gridPane.add(back, 2, 5);
      
		// Set event handlers to set the text of the display and add the number to the first or second input
		nine.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "9";
					 display.setText(display.getText()+"9");
				 }  else {
					 inputTwo += "9";
					 display.setText(display.getText()+"9");
				 }
				return;
			 } 
		  });
		eight.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "8";
					 display.setText(display.getText()+"8");
				 }  else {
					 inputTwo += "8";
					 display.setText(display.getText()+"8");
				 }
				return;
			 } 
		  });
		seven.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "7";
					 display.setText(display.getText()+"7");
				 }  else {
					 inputTwo += "7";
					 display.setText(display.getText()+"7");
				 }
				return;
			 } 
		  });
		six.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "6";
					 display.setText(display.getText()+"6");
				 }  else {
					 inputTwo += "6";
					 display.setText(display.getText()+"6");
				 }
				return;
			 } 
		  });
		five.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "5";
					 display.setText(display.getText()+"5");
				 }  else {
					 inputTwo += "5";
					 display.setText(display.getText()+"5");
				 }
				return;
			 } 
		  });
		four.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "4";
					 display.setText(display.getText()+"4");
				 }  else {
					 inputTwo += "4";
					 display.setText(display.getText()+"4");
				 }
				return;
			 } 
		  });
		three.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "3";
					 display.setText(display.getText()+"3");
				 }  else {
					 inputTwo += "3";
					 display.setText(display.getText()+"3");
				 }
				return;
			 } 
		  });
		two.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "2";
					 display.setText(display.getText()+"2");
				 }  else {
					 inputTwo += "2";
					 display.setText(display.getText()+"2");
				 }
				return;
			 } 
		  });
		one.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "1";
					 display.setText(display.getText()+"1");
				 }  else {
					 inputTwo += "1";
					 display.setText(display.getText()+"1");
				 }
				return;
			 } 
		  });
		zero.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += "0";
					 display.setText(display.getText()+"0");
				 }  else {
					 inputTwo += "0";
					 display.setText(display.getText()+"0");
				 }
				return;
			 } 
		  });
		decimal.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (firstInput){
					 inputOne += ".";
					 display.setText(display.getText()+".");
				 }  else {
					 inputTwo += ".";
					 display.setText(display.getText()+".");
				 }
				return;
			 } 
		  });
		  
		  // operations. each one checks current number for validity
		 plus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!inputOne.equals("")){
					operationType = "+";
					firstInput = false;
					display.setText("");
					alternateText += "("+inputOne+")"+"+";
					displayB.setText(alternateText);
				}
				return;
			} 
		});
		// contains extra validation, if used before a number is inputted, adds a "-" sign to create negative number
		minus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (firstInput == true){
					if (inputOne.equals("")){
						inputOne += "-";
						display.setText("-");
					} else if (inputOne.equals("-")){
						inputOne = "";
						display.setText("");
					} else {
						operationType = "-";
						firstInput = false;
						display.setText("");
						alternateText += "("+inputOne+")"+"-";
						displayB.setText(alternateText);
					}
				} else {
					if (inputTwo.equals("")){
						inputTwo += "-";
						display.setText("-");
					} else if (inputTwo.equals("-")){
						inputTwo = "";
						display.setText("");
					}
				}
				return;
			} 
		});
		multiply.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!inputOne.equals("")){
					operationType = "*";
					firstInput = false;
					display.setText("");
					alternateText += "("+inputOne+")"+"*";
					displayB.setText(alternateText);
				}
				return;
			} 
		});
		divide.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!inputOne.equals("")){
					operationType = "/";
					firstInput = false;
					display.setText("");
					alternateText += "("+inputOne+")"+"/";
					displayB.setText(alternateText);
				}
				return;
			} 
		});
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				clear();
				return;
			} 
		});
		// remove the last character
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String currDisplay = display.getText();
				if (!currDisplay.equals("")){
					if (firstInput){
						inputOne = inputOne.substring(0, (inputOne.length() -1));
						display.setText(inputOne);
					} else {
						inputTwo = inputTwo.substring(0, (inputTwo.length() -1));
						display.setText(inputTwo);
					}
				}
				return;
			} 
		});
		
		// this is where we do all the calculations
		equals.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// make sure we're working with a valid operation
				if (inputOne.equals("") || inputOne.equals("-") || inputTwo.equals("") || inputTwo.equals("-")){
					Alert alert = new Alert(AlertType.INFORMATION, "Invalid operation. Clearing.");
					alert.showAndWait();
					clear();
					return;
				}
				
				// catch NumberFormatException if any number is out of range we can provide the user with an error
				try{
					Integer.parseInt(inputOne);
					Integer.parseInt(inputTwo);
				} catch (NumberFormatException e){
					try{
						Double.parseDouble(inputOne);
						Double.parseDouble(inputTwo);
					}
					catch (NumberFormatException f){
						Alert alert = new Alert(AlertType.INFORMATION, "A number is out of range. Clearing.");
						alert.showAndWait();
						clear();
						return;
					}

				}
				
				// if either number is a decimal, we need to use doubles
				if (inputOne.contains(".") || inputTwo.contains(".")){
					switch (operationType){
						case "+": result = String.valueOf(Double.parseDouble(inputOne) + Double.parseDouble(inputTwo)); break;
						case "-": result = String.valueOf(Double.parseDouble(inputOne) - Double.parseDouble(inputTwo)); break;
						case "*": result = String.valueOf(Double.parseDouble(inputOne) * Double.parseDouble(inputTwo)); break;
						case "/": result = String.valueOf(Double.parseDouble(inputOne) / Double.parseDouble(inputTwo)); break;
						default : break;
					}
				} else {
					// we're simply working with ints
					switch (operationType){
						case "+": result = String.valueOf(Double.parseDouble(inputOne) + Double.parseDouble(inputTwo)); break;
						case "-": result = String.valueOf(Double.parseDouble(inputOne) - Double.parseDouble(inputTwo)); break;
						case "*": result = String.valueOf(Double.parseDouble(inputOne) * Double.parseDouble(inputTwo)); break;
						case "/": result = String.valueOf(Double.parseDouble(inputOne) / Double.parseDouble(inputTwo)); break;
						// if either number is odd, we'll get a double instead of int. check the last bit for an odd number
						// case "/": if (((Integer.parseInt(inputOne) & 1) != 0) || ((Integer.parseInt(inputTwo) & 1) != 0)){
										// result = String.valueOf(Double.parseDouble(inputOne) / Double.parseDouble(inputTwo)); break;
									// } else {
										// both numbers are even, can use integer math
										// result = String.valueOf(Integer.parseInt(inputOne) / Integer.parseInt(inputTwo)); break;
									// }
						default: break;
					}
				}
					
				// ensure result is in range
				try{
					Double.parseDouble(result);
				} catch (NumberFormatException e){
					Alert alert = new Alert(AlertType.INFORMATION, "Result is out of range. Clearing.");
					alert.showAndWait();
					clear();
					return;
				}
				
				// regex taken from http://stackoverflow.com/questions/14984664/remove-trailing-zero-in-java
				// removes trailing zeros and decimals if needed
				result = !result.contains(".") ? result : result.replaceAll("0*$", "").replaceAll("\\.$", "");
				
				// operation complete, reset or update variables
				display.setText(result);
				alternateText += " ("+inputTwo+") = ";
				displayB.setText(alternateText);
				inputOne = result;
				inputTwo = "";
				operationType = "";
				firstInput = true;
				return;
			} 
		});
	
	applicationStage.setScene(scene);    // Set window's scene  
	applicationStage.setTitle("JavaFX Calculator"); // Set window's title
	applicationStage.show();             // Display window

	return;
	}
   
	public static void main(String [] args) {
		launch(args); // Launch application

		return;
	}
}
