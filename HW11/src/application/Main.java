/* In the code we are using Serialization.
 * Where we use this technique to write on files. Using objectoutputstream
 * we insert them in a file The file however will reset overtime since a new Objectoutput stream is created.
 * Using javafx we create a GUI with this having 5 buttons .
 * Crate button to add objects into the file
 * Retrieve to display the objects from the file.
 * Delete to delete the object from the file
 * Update to update the information matching the name.  
 * Finally an exit where it will just exit the gui 
 * 
 * 
 *	Assignment : HW 11 
 * 	Date:4/10/2023
 * 
 * 
 * @author Manny Villegas
 *
 */



package application;
	

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Creating the output for files 
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Person.bin"));
			// Styling using Grid pane to hold all the nodes
			GridPane grid = new GridPane();
			grid.add(new Label("Name"), 0, 0);
			TextField name = new TextField();
			grid.add(new Label("Phone Number"),0,1);
			TextField pNumber = new TextField();
			grid.add(pNumber, 1, 1);
			grid.add(name, 1, 0);
			grid.add(new Label("Date of Birth"), 0, 2);
			DatePicker pick = new DatePicker();
			grid.add(pick, 1, 2);
			grid.add(new Label("Email Address"), 0,3);
			TextField email = new TextField();
			grid.add(email, 1, 3);
			BorderPane root = new BorderPane();
			
			// 4 buttons were created for this program a create, Retrieve,delete,update and exit. 
			Button add = new Button("Create");
			Button retrieve = new Button("Retrieve");
			Button delete = new Button("Delete");
			Button update = new Button("Update");
			Button exit = new Button("Exit");
			grid.add(add, 0, 4);
			grid.add(retrieve, 1, 4);
			grid.add(delete, 2, 4);
			grid.add(update, 3, 4);
			grid.add(exit, 4, 4);
			root.setCenter(grid);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			add.setOnAction(e->{
			
				try {
					// Formatting the date so it can be readable 
					LocalDate localDate = pick.getValue();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String dateString = formatter.format(localDate);
					// Creating a new person to add into the file 
					info newPerson = new info(name.getText(),Integer.parseInt(pNumber.getText()),dateString,email.getText());
					// Write it into the file
					output.writeObject(newPerson);
					
					System.out.println("PERSON'S INFORMATION SAVED");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			
			});
			retrieve.setOnAction(e->{
			      try {
			            //object input stream
			    	  ObjectInputStream input = new ObjectInputStream(new FileInputStream("Person.bin"));
			            
			            // Read and display each person's information from the file
			            Object obj;
			            System.out.println("LIST OF CURRENT PEOPLE");
			            while ((obj = input.readObject()) != null) {
			                	info person = (info) obj;
			                    System.out.println(person);
			                
			            }
			            
			            // Close the object input stream and file input stream
			            input.close();
			            output.close();
			         
			        } catch (EOFException e1) {
			            // End of file reached
			        } catch (IOException | ClassNotFoundException e1) {
			            System.err.println("Error reading from file: " + e1.getMessage());
			        }
			    
		    
			});
			
			delete.setOnAction(e->{
				// ArrayList for objects to hold them from the file 
				ArrayList<Object> objects = new ArrayList<>();
				
				try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Person.bin"))) {
				   
				    Object obj;
				 
				    // Reads the file and adds the objects into an array list
				    while ((obj = input.readObject()) != null) {
				        objects.add(obj);
				    }
				} catch (ClassNotFoundException e1) {
				   e1.printStackTrace();
				} catch (IOException e1) {
				    
				}

			
	

			// Modify the desired object in the ArrayList
			int count  = 0 ;
			int remove = -1 ; 
			// Finding a match with the name text field and removing 
			for(Object  o : objects){
				if(((info) o).getName().equals(name.getText())) {
					// There was a match and we store the remove depending on the count because we cant remove it inside a for each loop
					System.out.println("REMOVED OBJECT");
					
					remove = count;
				}
					count++;
				}
			// Open the file for writing
			if(count>=0) {
				objects.remove(remove);
			}
			
			try {
				
				 output.close();
				// Write all the objects in the ArrayList to the file
				 ObjectOutputStream newOutput = new ObjectOutputStream(new FileOutputStream("Person.bin"));
				 // Adding the values form the file into a arrayList
				for (Object o : objects) {
					newOutput.writeObject(o);
					
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			

		});
		
		exit.setOnAction(e->{
			System.exit(0);
			
			
		});
		
				
			
			update.setOnAction(e->{
	
				  // Needed for the formatting the date
					LocalDate localDate = pick.getValue();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String dateString = formatter.format(localDate);
					ArrayList<Object> objects = new ArrayList<>();
					
					try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Person.bin"))) {
					   
					    Object obj;
					 // We add the values from the file to an arrayList
					    while ((obj = input.readObject()) != null) {
					        objects.add(obj);
					    }
					} catch (ClassNotFoundException e1) {
					   e1.printStackTrace();
					} catch (IOException e1) {
					    
					}

		

				// Modify the desired object in the ArrayList
				int count  = 0 ;
				
				for(Object  o : objects){
					if(((info) o).getName().equals(name.getText())) {
						// If the name in the text field is found in the arrayList we modify it by getting the fields and adding 
						// it into that spot 
						System.out.println("UPDATED OBJECT");
						
						objects.set(count,new info(name.getText(),Integer.parseInt(pNumber.getText()),dateString,email.getText()));
					}
						count++;
					}
				
				
				
				try {
					
					 output.close();
					// Write all the objects in the ArrayList to the file
					 ObjectOutputStream newOutput = new ObjectOutputStream(new FileOutputStream("Person.bin"));
					for (Object o : objects) {
						newOutput.writeObject(o);
						
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				

				
				

			});
			
			exit.setOnAction(e->{
				System.exit(0);
				
				
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	


	public static void main(String[] args) {
		launch(args);
	}
}
