package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.BindException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.hsqldb.Server;

import Exceptions.ConnectionAlreadyExistsException;
import Exceptions.ImmortalParentsException;
import Exceptions.NotAvailableException;
import Exceptions.NotToBeClassmatesException;
import Exceptions.NotToBeColleaguesException;
import Exceptions.NotToBeCoupledException;
import Exceptions.NotToBeFriendsException;
import Exceptions.SameAccountException;
import Exceptions.SameFamilyException;
import Exceptions.TooYoungException;
import Main.Driver;
import Main.MiniNet;
import Objects.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class Driver extends Application {
	
	Stage window;
	Scene scene1, scene_AddUser, scene_ManageUser, scene_DisplayUsers, scene_Test;
	
	public String edit = "f";
	 
	public static void main(String[] args) {
		try {
		//	Driver.DBConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// System.out.println("DB Connection already in use.");
		}
		
		try {
			Driver.TextLoad();
			System.out.println("Loaded Text Data");
			Driver.RelCombine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to locate account data from text file.");
		}
		
		launch(args);
		// System.out.println("Why did you call me? I don't do anything...");
	}
	// test
@SuppressWarnings("unchecked")
public void start(Stage primaryStage) throws Exception {

		window = primaryStage;

	// =============  View and Manage Users ======================
		
	// ================ User Management Menu ==========================
		
		Text tTestrHeader = new Text(20, 20, "MiniNet - User Management");
		tTestrHeader.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
		
		FindAccount("", "m");

		TextField textSearchName = new TextField();
		textSearchName.setPromptText("Search for a user..");
		
		ListView<String> listView = new ListView<String>(userArrayList);
		ListView<String> friendsView = new ListView<String>(friendshipArray);
		ListView<String> classmateView = new ListView<String>(classmateArray );
		ListView<String> colleagueView = new ListView<String>(colleagueArray );
		ListView<String> childrenView = new ListView<String>(childrenArray);
	//	ListView<String> parentView = new ListView<String>(parentArray);
		
		
		classmateView.setItems(classmateArray);
		colleagueView.setItems(colleagueArray);
		childrenView.setItems(childrenArray);
		
		textSearchName.textProperty().addListener((observable, oldValue, newValue) -> {
			String SearchName = textSearchName.getText();
		//	System.out.println(oldValue + " -> " + newValue);
			FindAccount(SearchName, "m");
			
			listView.setItems(userArrayList);
			;
		});
		
		 // Create profile viewer Grid-pane
		 GridPane ProfileViewerPane = new GridPane();
		 ProfileViewerPane.setAlignment(Pos.TOP_LEFT);
	//	 ProfileViewerPane.setAlignment(Pos.TOP_CENTER);
		 ProfileViewerPane.setPadding(new Insets(11.5, 2.5, 13.5, 44.5));
		 ProfileViewerPane.setHgap(2);
		 ProfileViewerPane.setVgap(5.5);

		 Label lName = new Label();
		 Label lAge = new Label();
		 Label lStatus = new Label();
		 Label lGender = new Label();
		 Label lState = new Label();
		 Label lImage = new Label();
		 
		 Label tlPartner = new Label("Partner:");
		 Label lPartner = new Label();
		 		 
		 TextField tName2 = new TextField();
		 TextField tAge2 = new TextField();
		 TextField tStatus2 = new TextField();
		 TextField tGender2 = new TextField();
		 TextField tState2 = new TextField();
		 TextField tImage2 = new TextField();
		 
		 ProfileViewerPane.add(textSearchName, 0, 0);
		 ProfileViewerPane.add(new Label("Name:"), 0, 2);
		 ProfileViewerPane.add(new Label("Age:"), 0, 3);
		 ProfileViewerPane.add(new Label("Status:"), 0, 4);
		 ProfileViewerPane.add(new Label("Gender:"), 0, 5);
		 ProfileViewerPane.add(new Label("State:"), 0, 6);
		 ProfileViewerPane.add(new Label("Image:"), 0, 7);
		 
		 ProfileViewerPane.add(tlPartner, 0, 10);
		 
		 if (edit == "f") {
			 // Edit mode off - display values as labels
		 ProfileViewerPane.add(lName, 1, 2);
		 ProfileViewerPane.add(lAge, 1, 3);		 
		 ProfileViewerPane.add(lStatus, 1, 4);		
		 ProfileViewerPane.add(lGender, 1, 5);		
		 ProfileViewerPane.add(lState, 1, 6);
		 ProfileViewerPane.add(lImage, 1, 7);
		 
		 ProfileViewerPane.add(lPartner, 1, 10);
		 				}
		 
		else if (edit != "f") {
			// Edit mode on, display values as textboxes
			 ProfileViewerPane.add(tName2, 1, 2);
			 ProfileViewerPane.add(tAge2, 1, 3);		 
			 ProfileViewerPane.add(tStatus2, 1, 4);		
			 ProfileViewerPane.add(tGender2, 1, 5);		
			 ProfileViewerPane.add(tState2, 1, 6);
			 ProfileViewerPane.add(tImage2, 1, 7);		
		}
		 
		 
		 ProfileViewerPane.add(new Label("Friends:"), 0, 13);
		 ProfileViewerPane.add(friendsView, 0, 14);
		 ProfileViewerPane.add(new Label("Children:"), 0, 17);
		 ProfileViewerPane.add(childrenView, 0, 18);
		 
		 ProfileViewerPane.add(new Label("Classmates:"), 1, 13);
		 ProfileViewerPane.add(classmateView, 1, 14);
		 ProfileViewerPane.add(new Label("Colleagues:"), 1, 17);
		 ProfileViewerPane.add(colleagueView, 1, 18);
		 
		 
		 listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    	String sToken = "";
			    	
			    	// Get ID from string, remove characters, change to int
			    	
			    	
			    try {
				        sToken = newValue.substring(4, 10); ;
				        sToken = sToken.replaceAll("[^\\d.]", "");
				        SelectedID = Integer.parseInt(sToken);
				        // System.out.println(SelectedID);
				        // Get real ID from array
				        SelectedID = SelectedID - 1;
				        

				        // We can use this ID to bring up other details of the user on the side of our GUI
				        lName.setText(accountList[SelectedID].getName());
				        lAge.setText(Integer.toString(accountList[SelectedID].getAge()));
				        lStatus.setText(accountList[SelectedID].getStatus());
				        lGender.setText(accountList[SelectedID].getGender());
				        lState.setText(accountList[SelectedID].getState());
				        lImage.setText(accountList[SelectedID].getImage());
				            
				//      System.out.println("Selected ID is: " + SelectedID);
				        lPartner.setText ("");
				        

				        
					        if (accountList[SelectedID] instanceof Adult) {
					        	tlPartner.setText("Partner:");
					        	try {
						        	int partnerID = ((Adult)accountList[SelectedID]).getPartner();
						        	String sPartner = accountList[partnerID].getName();
						        	lPartner.setText(sPartner);
						    	 } catch (Exception e1) {
						    		lPartner.setText("");}
					        	
					        }
						    	// Driver.FindFriends(SelectedID);
					        	
					        if (!(accountList[SelectedID] instanceof Adult)) {
					        	tlPartner.setText("Parents:");
					        	try {
					        		
					        		int sendID = accountList[SelectedID].getID();
					        		Driver.ViewParents(sendID);
					        		lPartner.setText(parentList);
					        		

						    	 } catch (Exception e1) {
						    		lPartner.setText("");}
					        }
					        
					        } catch (Exception e2) {
					        	// Do nothing, just don't cry
					        } finally {
						        friendshipArray.clear();
						        childrenArray.clear();
						        classmateArray.clear();
						        colleagueArray.clear();
						        
						        Driver.FindFriends(SelectedID);
						        Driver.ViewChildren(SelectedID);
						        Driver.ViewClassmates(SelectedID);
						        Driver.ViewColleagues(SelectedID);
						        			        
						        friendsView.setItems(friendshipArray);
						        childrenView.setItems(childrenArray);
						        classmateView.setItems(classmateArray);
						        colleagueView.setItems(colleagueArray);
					        }
		 }}
			);
		 
			Button bExit = new Button("Exit Application");

			bExit.setOnAction(e ->
			{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Exit Application");
				alert.setHeaderText("You will lose all newly entered data.");
				alert.setContentText("Are you sure you want to exit?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
				    // ... user chose OK
					System.out.println("Closing MiniNet. You can run but you can't hide!");
					primaryStage.close();
				} else {
				    // ... user chose CANCEL or closed the dialog
				}

			});
		 
			 	// Set Manage User top pane
				GridPane ManageUserTopPane = new GridPane();
				ManageUserTopPane.setAlignment(Pos.TOP_LEFT);
				ManageUserTopPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
				ManageUserTopPane.setHgap(5.5);
				ManageUserTopPane.setVgap(5.5);
				
				ManageUserTopPane.add(tTestrHeader, 0, 0);
				ManageUserTopPane.add(bExit, 0, 1);
				
				 // Set user manage button pane
				 GridPane ProfileButtonPane = new GridPane();
				 ProfileButtonPane.setAlignment(Pos.TOP_RIGHT);
				 ProfileButtonPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
				 ProfileButtonPane.setHgap(5.5);
				 ProfileButtonPane.setVgap(5.5);
				 
				 Button bAddAccount = new Button("Add a Profile");
				 Button bEditRelationships = new Button("Add a Connection");
				 Button bEditProfile = new Button("Edit User Details");
				 Button bDeleteProfile = new Button("Remove User");
				 
				 ProfileButtonPane.add(bAddAccount, 0, 0);
				 ProfileButtonPane.add(bEditRelationships, 0, 1);
				 ProfileButtonPane.add(bEditProfile, 0, 2);
				 ProfileButtonPane.add(bDeleteProfile, 0, 3);
				 
				 
				 bAddAccount.setOnAction(e -> {
					 
						// Create a pane and set its properties
						Dialog<String> dialog = new Dialog<>();
						dialog.setTitle("Account Creation Form");
						dialog.setHeaderText("Type in the details of your new account to create.");
						dialog.setResizable(true);
						
						GridPane AddUserPane3 = new GridPane();
						AddUserPane3.setAlignment(Pos.CENTER);
						AddUserPane3.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
						AddUserPane3.setHgap(5.5);
						AddUserPane3.setVgap(5.5);


						ComboBox cGenders3 = new ComboBox<>();
						cGenders3.getItems().addAll(
							    "",
							    "F",
							    "M"
							);
						
						ComboBox cState3 = new ComboBox<>();
						cState3.getItems().addAll(
							    "",
							    "ACT",
							    "NSW",
							    "NT",
							    "QLD",
							    "SA",
							    "TAS",
							    "VIC",
							    "WA"
							);	
						
						// Create input fields for create account form
						TextField tName = new TextField();
						tName.setPromptText("Enter your full name.");
						TextField tAge = new TextField();
						tAge.setPromptText("Enter your age.");
						TextField tStatus = new TextField();	
						tStatus.setPromptText("Enter a status message.");
						TextField tImage = new TextField();
						tImage.setPromptText("Add an image URL");
						
						// Create input fields for create account form
						TextField tName3 = new TextField();
						tName3.setPromptText("Enter your full name.");
						TextField tAge3 = new TextField();
						tAge3.setPromptText("Enter your age.");
						TextField tStatus3 = new TextField();	
						tStatus3.setPromptText("Enter a status message.");
						cGenders3.setPromptText("Select your Gender");
						cState3.setPromptText("Select your State");
						TextField tImage3 = new TextField();
						tImage3.setPromptText("Add an image URL");
						
						// Place nodes in the pane

						AddUserPane3.add(new Label("Name:"), 0, 0);
						AddUserPane3.add(tName3, 1, 0);
						AddUserPane3.add(new Label("Age:"), 0, 1);
						AddUserPane3.add(tAge3, 1, 1);
						AddUserPane3.add(new Label("Status:"), 0, 2);
						AddUserPane3.add(tStatus3, 1, 2);		
						AddUserPane3.add(new Label("Gender:"), 0, 3);
						AddUserPane3.add(cGenders3, 1, 3);		
							
						AddUserPane3.add(new Label("State:"), 0, 4);
						AddUserPane3.add(cState3, 1, 4);			
						AddUserPane3.add(new Label("Image URL:"), 0, 5);
						AddUserPane3.add(tImage3, 1, 5);	
					 
							dialog.getDialogPane().setContent(AddUserPane3);
									
							ButtonType buttonTypeOk = new ButtonType("Create User");
							dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

							Optional<String> result = dialog.showAndWait();
							
							String Name = "";
							String SAge = "";
							String Status = "";
							String Gender = "";
							String State = "";
							String Image = "";
							
							char exceptionRaised = 'n';
							
							int Age = 0;
							

							if (result.isPresent()){
								try {
									Name = tName3.getText();
									SAge = tAge3.getText();
									Status = tStatus3.getText();
									Image = tImage3.getText();
									
									if (cGenders3.getValue() == null) {
										Gender = "";
									} else {
										Gender = cGenders3.getValue().toString();
									}
									
									if (cState3.getValue() == null) {
										State = "";
									} else {
										State =  cState3.getValue().toString();
									}
									
									Age = Integer.parseInt(SAge);
								
									if (Age < 0 | Age > 140) {
										throw new NumberFormatException();
									}
								////////	

									
								} catch (NullPointerException ne1) {
						        	Alert dataAlert = new Alert(AlertType.ERROR);
						        	exceptionRaised = 'y';
						        	
						        	dataAlert.setTitle("Incorrect Data!");
						        	dataAlert.setHeaderText("Ensure you type in correct values into each field.");
						        	dataAlert.setContentText("Remember to put correct values in fields!");
						        	dataAlert.showAndWait();
						        	
									} catch (NumberFormatException ne2) {
							        	Alert numberAlert = new Alert(AlertType.ERROR);
							        	exceptionRaised = 'y';
							        	
							        	numberAlert.setTitle("Incorrect data");
							        	numberAlert.setHeaderText("Ensure you type in correct data.");
							        	numberAlert.setContentText("Ensure you don't leave blanks, and type in an appropriate age value.");
							        	numberAlert.showAndWait();
									}
								}
							
							try {
								if (Name == "") {
									// exceptionRaised = 'y';
									throw new NullPointerException();
								}
								
							} catch (NullPointerException e12) {
					        	Alert numberAlert = new Alert(AlertType.ERROR);
					        	exceptionRaised = 'y';
					        	
					        	numberAlert.setTitle("Incorrect data");
					        	numberAlert.setHeaderText("Ensure you type in correct data.");
					        	numberAlert.setContentText("Ensure you don't leave blanks, and type in an appropriate age value.");
					        	numberAlert.showAndWait();

							}
							
							if (exceptionRaised == 'y') {
								// Do nothing
							} else {
								
							// Continue with account creation...
								
							if (Age > 16) {
								Driver.CreateAccount(Name,Age,Status,Image,Gender,State);
								FindAccount("","m");
								
							} else if (Age <= 16) {
								// parentArrayList
								
								// Check if already couple
								// If couple, add child
							
								// If not already couple, check if can add as couple
								// If can add as couple, add as couple
								// Then add child
								
								// If cannot add child to selected adults, display error
															
								Driver.EligibleParents();
								
								Dialog<String> addParentsDialog = new Dialog<>();
								addParentsDialog.setTitle("Select two parents");
								addParentsDialog.setHeaderText("If the selected adults are not in a couple, this form will assign them as a couple.");
								addParentsDialog.setResizable(true);

								Label label1 = new Label("Parent 1: ");
								Label label2 = new Label("Parent 2: ");

								ComboBox<String> parent1 = new ComboBox();
								parent1.getItems().addAll(parentArrayList);
								
								ComboBox<String> parent2 = new ComboBox();
								parent2.getItems().addAll(parentArrayList);

								GridPane grid = new GridPane();
								grid.add(label1, 1, 1);
								grid.add(parent1, 2, 1);
								grid.add(label2, 1, 2);
								grid.add(parent2, 2, 2);
								addParentsDialog.getDialogPane().setContent(grid);
										
								ButtonType buttonTypeOk2 = new ButtonType("Add");
								addParentsDialog.getDialogPane().getButtonTypes().add(buttonTypeOk2);

								Optional<String> result2 = addParentsDialog.showAndWait();
								
								String sToken1 = "";
								String sToken2 = "";
								
								int Token1 = 0;
								int Token2 = 0;
								
								if (result2.isPresent()){
									
									try {
										sToken1 = parent1.getValue().toString();
										sToken1 = sToken1.substring(4,10);
										sToken1 = sToken1.replaceAll("[^\\d.]", "");
								        Token1 = Integer.parseInt(sToken1);
								    	Token1 = Token1 - 1;
								    	
								    	sToken2 = parent2.getValue().toString();
										sToken2 = sToken2.substring(4,10);
										sToken2 = sToken2.replaceAll("[^\\d.]", "");
									    Token2 = Integer.parseInt(sToken2);
								        Token2 = Token2 - 1;
								        
									} catch (Exception e8) {
							        	Alert noCouple = new Alert(AlertType.ERROR);
							        	noCouple.setTitle("Invalid Selection");
							        	noCouple.setHeaderText("You must select two separate people to create a couple.");
							        	noCouple.setContentText("You must select two separate people to create a couple. Science has gone far, but this app is back in the stone age!");
							        	noCouple.showAndWait();
							        	exceptionRaised = 'y';
									}

							        if (exceptionRaised == 'y') {
							        	// Do nothing, end essentially
							        } else {
							        try {
							        	Driver.AddCouple(Token1, Token2, "check");
							        	
							        	int childID = count;
							        	Driver.CreateAccount(Name,Age,Status,Image,Gender,State);
							        	Driver.AddChild(Token1, Token2, childID);
							        	
							        } 	catch (NotAvailableException e12) {
							      		Alert coupleAlert2 = new Alert(AlertType.ERROR);
							        	coupleAlert2.setTitle("Not Available Exception!");
							        	coupleAlert2.setHeaderText("You can't add these two people as partners!");
							        	coupleAlert2.setContentText("At least one of these people is in a happy relationship... perhaps both!");
							        	coupleAlert2.showAndWait();
							        	
							        } catch (ConnectionAlreadyExistsException e8) {
							        	Alert coupleAlert2 = new Alert(AlertType.ERROR);
							        	coupleAlert2.setTitle("You can't have a sole parent!");
							        	coupleAlert2.setHeaderText("You can't have a sole parent!!");
							        	coupleAlert2.setContentText("A child must be reared by two adults, not one!");
							        	coupleAlert2.showAndWait();
							        	
							        } catch (NotToBeCoupledException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (SameAccountException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							        	       
						        	FindAccount("","m");
				        	
						        
								}

							}
							}
							}
				 });
				 
				 bEditProfile.setOnAction(e -> {
					 
					 if (SelectedID == -1) {
						 
					 } else
					 
					 if (edit == "f") {
						 edit = "t";
		 
						 // Change "Edit Button" to "Save Changes"
						 bEditProfile.setText("Save Changes");
						 
							// Edit mode on, display values as textboxes
						 
						 // Remove label boxes
						 ProfileViewerPane.getChildren().remove(lName);
						 ProfileViewerPane.getChildren().remove(lAge);
						 ProfileViewerPane.getChildren().remove(lStatus);
						 ProfileViewerPane.getChildren().remove(lGender);
						 ProfileViewerPane.getChildren().remove(lState);
						 ProfileViewerPane.getChildren().remove(lImage);
						 
						 // Add text boxes
						 ProfileViewerPane.add(tName2, 1, 2);
						 ProfileViewerPane.add(tAge2, 1, 3);		 
						 ProfileViewerPane.add(tStatus2, 1, 4);		
						 ProfileViewerPane.add(tGender2, 1, 5);		
						 ProfileViewerPane.add(tState2, 1, 6);
						 ProfileViewerPane.add(tImage2, 1, 7);
						 

						 
						 // Set currently selected user as textbox values
						 try {
							 tName2.setText(accountList[SelectedID].getName());
							 tAge2.setText(Integer.toString(accountList[SelectedID].getAge()));
							 tStatus2.setText(accountList[SelectedID].getStatus());
							 tGender2.setText(accountList[SelectedID].getGender());
							 tState2.setText(accountList[SelectedID].getState());
							 tImage2.setText(accountList[SelectedID].getImage());
						 } catch (ArrayIndexOutOfBoundsException e2) {
							 // Do nothing - nothing needs to be done
						 }
						 	 
					 }
						else if (edit != "f") {
							edit = "f";
						// Change label back to "Edit User Details"
						bEditProfile.setText("Edit User Details");
							
						// Save currently set values

							
							String Name = tName2.getText();
							String textAge = tAge2.getText();
							int Age = Integer.parseInt(textAge);
							String Status = tStatus2.getText();
							String Gender = tGender2.getText();
							String State = tState2.getText();
							String Image = tImage2.getText();
							
							try {
								UpdateAccount(SelectedID, 'u',Name,Age,Status,Image,Gender,State);
							} catch (ImmortalParentsException e1) {
								// TODO Auto-generated catch block
								// This exception will never throw, as an update ('u') command does not throw this exception
							}
							
							 // Edit mode off - display values as labels
							
						// Remove text boxes	
							ProfileViewerPane.getChildren().remove(tName2);
							ProfileViewerPane.getChildren().remove(tAge2);
							ProfileViewerPane.getChildren().remove(tStatus2);
							ProfileViewerPane.getChildren().remove(tGender2);
							ProfileViewerPane.getChildren().remove(tState2);
							ProfileViewerPane.getChildren().remove(tImage2);
						
						// Add label boxes
							ProfileViewerPane.add(lName, 1, 2);
							ProfileViewerPane.add(lAge, 1, 3);		 
							ProfileViewerPane.add(lStatus, 1, 4);		
							ProfileViewerPane.add(lGender, 1, 5);		
							ProfileViewerPane.add(lState, 1, 6);
							ProfileViewerPane.add(lImage, 1, 7);
							
							 FindAccount("","m");
							 listView.setItems(userArrayList);
							 
					    // Pull new user details to labels
						        lName.setText(accountList[SelectedID].getName());
						        lAge.setText(Integer.toString(accountList[SelectedID].getAge()));
						        lStatus.setText(accountList[SelectedID].getStatus());
						        lGender.setText(accountList[SelectedID].getGender());
						        lState.setText(accountList[SelectedID].getState());
						        lImage.setText(accountList[SelectedID].getImage());
							
						}
					// System.out.println(edit);
				 });				 
				 
				 bEditRelationships.setOnAction(e -> {
					 
					 if (SelectedID == -1) {
						 // Do nothing
					 } else {
						 
					 List<String> userList = new ArrayList<>(userArrayList);
					 
					 List<String> choices = new ArrayList<>();
					 choices.add("Friend");
					 choices.add("Classmate");
					 choices.add("Colleague");
					 choices.add("Couple");

					 ChoiceDialog<String> dialog = new ChoiceDialog<>("Friend", choices);
					 dialog.setTitle("Add a Connection");
					 dialog.setHeaderText("What kind of connection do you wish to add?");
					 dialog.setContentText("Select a connection type:");

					 try {
					 Optional<String> result = dialog.showAndWait();
					 if (result.isPresent()){
						 if(result.get() == "Friend") {
							 
							 ChoiceDialog<String> friendDialog = new ChoiceDialog<>("Select a User", userList);
							 friendDialog.setTitle("Add a Friend");
							 friendDialog.setHeaderText("Select a friend from the dropdown");
							 friendDialog.setContentText("Friend to add:");

							 // Traditional way to get the response value.
							 try {
								 

							 Optional<String> friendResult = friendDialog.showAndWait();
							 if (friendResult.isPresent()){
							 //    System.out.println("Your choice: " + result.get());
							     
							        String sToken = friendResult.get().substring(4, 10); ;
							        sToken = sToken.replaceAll("[^\\d.]", "");
							        int friend2Add = Integer.parseInt(sToken);
							        friend2Add = friend2Add - 1;

							        	Driver.AddFriend(SelectedID, friend2Add);
							        	
							        }} catch (NotToBeFriendsException ef1) {
							        	Alert friendAlert = new Alert(AlertType.ERROR);
							        	friendAlert.setTitle("Not to be Friends Exception!");
							        	friendAlert.setHeaderText("You can't add these two people as friends!");
							        	friendAlert.setContentText("You cannot add friends between adults, children and infants!");
							        	friendAlert.showAndWait();
							        	
							        } catch (ConnectionAlreadyExistsException ef2) {
							        	Alert friendAlert = new Alert(AlertType.ERROR);
							        	friendAlert.setTitle("Connection already exists!");
							        	friendAlert.setHeaderText("You can't add these two people as friends!");
							        	friendAlert.setContentText("Friendship connection already exists between these two people!");
							        	friendAlert.showAndWait();
							        	
							        } catch (TooYoungException ef3) {
							        	Alert friendAlert = new Alert(AlertType.ERROR);
							        	friendAlert.setTitle("Too young exception!");
							        	friendAlert.setHeaderText("That's a baby! It can't be your friend!");
							        	friendAlert.setContentText("Maybe wait a few years...");	
							        	friendAlert.showAndWait();
							        	
							        } catch (SameFamilyException ef4) {
							        	Alert friendAlert = new Alert(AlertType.ERROR);
							        	friendAlert.setTitle("Same family Exception!");
							        	friendAlert.setHeaderText("Siblings cannot be friends!");
							        	friendAlert.setContentText("To be honest, I get on quite well with my siblings, I'm unsure why this error is the case.");
							        	friendAlert.showAndWait();
							        	
									} catch (SameAccountException e1) {
							        	Alert friendAlert = new Alert(AlertType.ERROR);
							        	friendAlert.setTitle("Same person Exception!");
							        	friendAlert.setHeaderText("You cannot add yourself as a friend!");
							        	friendAlert.setContentText("I'd like to think in this day and age, people would recognise the importance of loving yourself... maybe that's a goal for the next generation...");
							        	friendAlert.showAndWait();
									}
							 }
						 if(result.get() == "Classmate") {
							 
							 ChoiceDialog<String> classmateDialog = new ChoiceDialog<>("Select a Classmate", userList);
							 classmateDialog.setTitle("Add a Classmate");
							 classmateDialog.setHeaderText("Select a classmate from the dropdown");
							 classmateDialog.setContentText("Classmate to add:");
							 							 
							 // Traditional way to get the response value.
							 try {
								 

							 Optional<String> classmateResult = classmateDialog.showAndWait();
							 if (classmateResult.isPresent()){
							 //    System.out.println("Your choice: " + result.get());
							     
							        String sToken = classmateResult.get().substring(4, 10); ;
							        sToken = sToken.replaceAll("[^\\d.]", "");
							        int classmate2Add = Integer.parseInt(sToken);
							        classmate2Add = classmate2Add - 1;

							        	Driver.AddClassmates(SelectedID, classmate2Add);
							        	
							        }} catch (NotToBeClassmatesException ef1) {
							        	Alert classmateAlert = new Alert(AlertType.ERROR);
							        	classmateAlert.setTitle("Not to be Classmates Exception!");
							        	classmateAlert.setHeaderText("You can't add these two people as classmates!");
							        	classmateAlert.setContentText("You cannot add classmates between adults and children!");
							        	classmateAlert.showAndWait();
							        	
							        } catch (ConnectionAlreadyExistsException ef2) {
							        	Alert classmateAlert = new Alert(AlertType.ERROR);
							        	classmateAlert.setTitle("Connection already exists!");
							        	classmateAlert.setHeaderText("You can't add these two people as classmates!");
							        	classmateAlert.setContentText("Classmate connection already exists between these two people!");
							        	classmateAlert.showAndWait();
									} catch (SameAccountException ef3) {
							        	Alert classmateAlert = new Alert(AlertType.ERROR);
							        	classmateAlert.setTitle("Same person Exception!");
							        	classmateAlert.setHeaderText("You cannot add yourself as a classmate!");
							        	classmateAlert.setContentText("It's time to go and make friends with some people in your class...");
							        	classmateAlert.showAndWait();
									}
							        
							 }

							 // The Java 8 way to get the response value (with lambda expression).
							 // result.ifPresent(letter -> System.out.println("Your choice: " + letter));

						 if(result.get() == "Colleague") {
							 ChoiceDialog<String> colleagueDialog = new ChoiceDialog<>("Select a Colleague", userList);
							 colleagueDialog.setTitle("Add a Colleague");
							 colleagueDialog.setHeaderText("Select a colleague from the dropdown");
							 colleagueDialog.setContentText("Colleague to add:");

							 // Traditional way to get the response value.
							 try {
								 

							 Optional<String> colleagueResult = colleagueDialog.showAndWait();
							 if (colleagueResult.isPresent()){
							 //    System.out.println("Your choice: " + result.get());
							     
							        String sToken = colleagueResult.get().substring(4, 10); ;
							        sToken = sToken.replaceAll("[^\\d.]", "");
							        int colleague2Add = Integer.parseInt(sToken);
							        colleague2Add = colleague2Add - 1;

							        	Driver.AddColleagues(SelectedID, colleague2Add);
							        	
							        }} catch (NotToBeColleaguesException ef1) {
							        	Alert colleagueAlert = new Alert(AlertType.ERROR);
							        	colleagueAlert.setTitle("Not to be Colleagues Exception!");
							        	colleagueAlert.setHeaderText("You can't add these two people as colleagues!");
							        	colleagueAlert.setContentText("Remember, only adults have real jobs and can be added as colleagues!");
							        	colleagueAlert.showAndWait();
							        	
							        	colleagueAlert.showAndWait();
							        } catch (ConnectionAlreadyExistsException ef2) {
							        	Alert colleagueAlert = new Alert(AlertType.ERROR);
							        	colleagueAlert.setTitle("Connection already exists!");
							        	colleagueAlert.setHeaderText("You can't add these two people as colleagues!");
							        	colleagueAlert.setContentText("Colleague connection already exists between these two people!");
							        	colleagueAlert.showAndWait();
							        } catch (SameAccountException e1) {
					        	Alert colleagueAlert = new Alert(AlertType.ERROR);
					        	colleagueAlert.setTitle("Same person Exception!");
					        	colleagueAlert.setHeaderText("You cannot add yourself as a colleague!");
					        	colleagueAlert.setContentText("This is the importance of networking. Unless you're an entrepreneur, you might need to consider meeting some new office professionals...");
					        	colleagueAlert.showAndWait();
							}
							        
						 }}
					 
						 if(result.get() == "Couple") {
							 ChoiceDialog<String> coupleDialog = new ChoiceDialog<>("Select a Partner", userList);
							 coupleDialog.setTitle("Add a Couple");
							 coupleDialog.setHeaderText("Select a partner from the dropdown");
							 coupleDialog.setContentText("Partner to add:");

							 // Traditional way to get the response value.
							 try {
								 

							 Optional<String> coupleResult = coupleDialog.showAndWait();
							 if (coupleResult.isPresent()){
							 //    System.out.println("Your choice: " + result.get());
							     
							        String sToken = coupleResult.get().substring(4, 10); ;
							        sToken = sToken.replaceAll("[^\\d.]", "");
							        int partner2Add = Integer.parseInt(sToken);
							        partner2Add = partner2Add - 1;

							        	Driver.AddCouple(SelectedID, partner2Add, "add");
							        	
							        }} catch (NotAvailableException ef1) {
							        	Alert coupleAlert = new Alert(AlertType.ERROR);
							        	coupleAlert.setTitle("Not Available Exception!");
							        	coupleAlert.setHeaderText("You can't add these two people as partners!");
							        	coupleAlert.setContentText("Some life advice - ensure someone else isn't already taking a bite from the apple in your eye!");
							        	coupleAlert.showAndWait();

							        } catch (NotToBeCoupledException ef2) {
							        	Alert coupleAlert = new Alert(AlertType.ERROR);
							        	coupleAlert.setTitle("Not to be Coupled Exception!");
							        	coupleAlert.setHeaderText("You can't add these two people as partners!");
							        	coupleAlert.setContentText("That's a minor! You can go to jail for that you know!");
							        	coupleAlert.showAndWait();
							        	
							        } catch (ConnectionAlreadyExistsException ef3) {
							        	Alert coupleAlert = new Alert(AlertType.ERROR);
							        	coupleAlert.setTitle("Connection already exists!");
							        	coupleAlert.setHeaderText("You can't add these two people as partners!");
							        	coupleAlert.setContentText("Couple connection already exists between these two people!");
							        	coupleAlert.showAndWait();
							        	
									} catch (SameAccountException ef4) {
							        	Alert coupleAlert = new Alert(AlertType.ERROR);
							        	coupleAlert.setTitle("Same person Exception!");
							        	coupleAlert.setHeaderText("You cannot add yourself as a couple!");
							        	coupleAlert.setContentText("Let us tackle the gender identity issue for now, everything's pretty complicated as it is...");
							        	coupleAlert.showAndWait();
									}
							 
					        	try {
						        	int partnerID = ((Adult)accountList[SelectedID]).getPartner();
						        	String sPartner = accountList[partnerID].getName();
						        	lPartner.setText(sPartner);
						    	 } catch (Exception e1) {
						    		lPartner.setText("");}
							 
						 }}  catch (NoSuchElementException ef2) {
					        	// Do nothing, just don't cry
					 }
					 
				        Driver.FindFriends(SelectedID);
				        Driver.ViewChildren(SelectedID);
				        Driver.ViewClassmates(SelectedID);
				        Driver.ViewColleagues(SelectedID);
				        
				        friendsView.setItems(friendshipArray);
				        childrenView.setItems(childrenArray);
				        classmateView.setItems(classmateArray);
				        colleagueView.setItems(colleagueArray);
				        
					 } 
					 
				 });
				 
				 
				 bDeleteProfile.setOnAction(e -> {
					 
					 if (SelectedID == -1) {
						 // Do nothing
						 
					 } else {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Delete User");
						alert.setHeaderText("You will not be able to recover this information.");
						alert.setContentText("Are you sure you want to delete this user?");

						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK){
						    // ... user chose OK
							
							 try {
								UpdateAccount(SelectedID, 'd', "", 0, "", "","","");
							} catch (ImmortalParentsException e1) {
								// TODO Auto-generated catch block
					        	Alert immortalParentAlert = new Alert(AlertType.ERROR);
					        	immortalParentAlert.setTitle("PARENTS ARE IMMORTAL!");
					        	immortalParentAlert.setHeaderText("You cannot break the binds of parenthood.");
					        	immortalParentAlert.setContentText("This person has a child, this connection cannot be broken!");
					        	immortalParentAlert.showAndWait();
							}
							 							 
							 // Reload Screen
							 
							 FindAccount("","m");
							 listView.setItems(userArrayList);
							 	
							 	SelectedID = -1;
							 
							 	friendshipArray.clear();
							 	childrenArray.clear();
							 	classmateArray.clear();
							 	colleagueArray.clear();
						        
						        friendsView.setItems(friendshipArray);
						        childrenView.setItems(childrenArray);
						        classmateView.setItems(classmateArray);
						        colleagueView.setItems(colleagueArray);
							 
						        lName.setText("");
						        lAge.setText("");
						        lStatus.setText("");
						        lGender.setText("");
						        lState.setText("");
						        lImage.setText("");
						            
						//      System.out.println("Selected ID is: " + SelectedID);
						        lPartner.setText ("");
							 
						        SelectedID = -1;
						        
						} else {
						    // ... user chose CANCEL or closed the dialog
						}
					 }
				 });
				 
			// Create a pane and set its properties
			BorderPane UserListPane = new BorderPane();
			UserListPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
			
			UserListPane.setLeft(listView);
			UserListPane.setCenter(ProfileViewerPane);
			UserListPane.setRight(ProfileButtonPane);
			
			
			VBox TestUsers = new VBox(40);
			TestUsers.getChildren().addAll(ManageUserTopPane, UserListPane);
			scene_Test = new Scene(TestUsers, 1024, 768);	
			

	// WindowSetScene	
		window.setScene(scene1);
		window.setTitle("MiniNet");
		window.show();
		
			// Create a scene and place it in the stage
			Scene scene = (scene_Test);
			primaryStage.setTitle("ShowGridPane");
			primaryStage.setScene(scene_Test);
			primaryStage.show();

}

/// ==========================================================================================================
/// ==================== Driver Code Chunk
/// ==========================================================================================================
	
	// Placeholder value for user-list return method
	public static String userList = "";
	
	// Placeholder variable for parentID finding
	public static int foundParent = -2;
	// Shared array to pass to other methods - defining parents
	public static ArrayList<Integer> foundParents = null;
	// Shared array to pass to other methods - defining friendship creation between two people
	public static int foundFriend = -1;
		public static int foundAccount = -1;
	
	// Shared UserArrayList
	public static ObservableList<String> userArrayList = FXCollections.observableArrayList();
	public static ObservableList<String> friendshipArray = FXCollections.observableArrayList();
	public static ObservableList<String> classmateArray = FXCollections.observableArrayList();
	public static ObservableList<String> colleagueArray = FXCollections.observableArrayList();
	public static ObservableList<String> childrenArray = FXCollections.observableArrayList();
	public static ArrayList<String> parentArrayList = new ArrayList<String>();
	
	public static String parentList = "";
	
	public static int SelectedID = -1;

	// Used for setting user IDs
	public static int count = 0;
	
	// Used for setting relation IDs
	public static int relcount = 0;

	// Create lists for our objects
	static User[] accountList = new User[200]; 
	static Relation[] relationList = new Relation[200];

	
	public static void DBConnection() throws Exception {
			Server hsqlServer = null;
			Connection connection = null;
			ResultSet rs = null;
			ResultSet rs2 = null;
			hsqlServer = new Server();
			hsqlServer.setLogWriter(null);
			hsqlServer.setSilent(true);
			hsqlServer.setDatabaseName(0, "TestDB");
			hsqlServer.setDatabasePath(0, "file:MYDB");
			hsqlServer.start();
			// making a connection
			
			try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection =
			DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
			/*
			connection.prepareStatement("DROP TABLE Account;").execute();
			connection.prepareStatement("DROP TABLE Relation;").execute();
			*/
			
			connection.prepareStatement("CREATE TABLE User ( \r\n" + 
					"ID char(3) primary key, Name char(40), Status char(140), Image char(100), Gender char(1), State char(6) \r\n" + 
					"); ;").execute();
			connection.prepareStatement("CREATE TABLE Relation ( \r\n" + 
					"ID char(3) primary key, Prospect char(40), Subject char(40), Relation char(12) \r\n" + 
					"); ;").execute();
			
			connection.prepareStatement("INSERT INTO User VALUES('Alex Smith','','student at RMIT','M',21,'WA');").execute();
			connection.prepareStatement("INSERT INTO User VALUES('Ben Turner','BenPhoto.jpg','manager at Coles','M',35,'VIC');").execute();
			connection.prepareStatement("INSERT INTO User VALUES('Hannah White','Hannah.png','student at RMIT','M',21,'WA');").execute();
			connection.prepareStatement("INSERT INTO User VALUES('Zoe Foster','','Founder of ZFX','F',28,'VIC');").execute();
			connection.prepareStatement("INSERT INTO User VALUES('Mark Turner','Mark.jpeg',‘student at RMIT’,‘M’,2,‘VIC’);").execute();
			
			connection.prepareStatement("INSERT INTO Relation VALUES(‘Alex Smith’,’Ben Turner’,’friends’);").execute();
			connection.prepareStatement("INSERT INTO Relation VALUES(‘Ben Turner’,’Zoe Foster’,’couple’);").execute();
			connection.prepareStatement("INSERT INTO Relation VALUES(‘Ben Turner’,’Mark Turner’,’parent’);").execute();
			connection.prepareStatement("INSERT INTO Relation VALUES(‘Mark Turner’,’Zoe Foster’,’parent’);").execute();
			
			//
			// // query from the db
			rs = connection.prepareStatement("select ID, Name from User;").executeQuery();
			rs.next();
			System.out.println(String.format("ID: %1d, Name: %1s", rs.getInt(1), rs.getString(2)));
			connection.commit();
			
			rs2 = connection.prepareStatement("select Subject, Prospect, Relation from Relation;").executeQuery();
			rs2.next();
			System.out.println(String.format("ID: %1d, Name: %1s", rs2.getInt(1), rs2.getString(2)));
			connection.commit();
			
			} catch (SQLException e2) {
				System.out.println("SQL Error. Loading data from text file.");
			} catch (ClassNotFoundException e2) {
				System.out.println("Class not found.");
			} catch (Exception e3) {
				System.out.println("Connection already in use.");
			}
			// end of stub code for in/out stub
			
			connection.close();
			}

	
	public static void TextLoad() throws FileNotFoundException {

		ArrayList<Integer> nullList = new ArrayList<Integer>();
	
		// Test parents code
		ArrayList<Integer> testParents = new ArrayList<Integer>();
		testParents.add(1);
		testParents.add(2);
		
		// Test friends code 1
		ArrayList<Integer> testFriendsOne = new ArrayList<Integer>();
		testFriendsOne.add(1);
		testFriendsOne.add(2);		
		
		// Test friends code 2
		ArrayList<Integer> testFriendsTwo = new ArrayList<Integer>();
		testFriendsTwo.add(0);
		
		
		// Relationship Information Import

	    /////////
	    Scanner relationScanner = new Scanner(new File("relations.txt"));
	    
	    String Subject = "";
	    String Prospect = "";
	    String Relation = "";
	    
	    while(relationScanner.hasNext()) {
	    	// relationLine = relationScanner.nextLine();
	    	
	    	String line = relationScanner.nextLine();
	    	
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            	Subject = lineScanner.next();
            	Prospect = lineScanner.next();
            	Relation = lineScanner.nextLine();
            	
            	Prospect = Prospect.substring(1);
            	Prospect = Prospect.replace("\\s+","");
            	
            	Relation = Relation.substring(2);
            	Relation = Relation.replace("\\s+","");
            	
            	// System.out.println("Subject: " + Subject + ", Prospect: " + Prospect +", Relation: " + Relation);
            	
            	relationList[relcount++] = new Relation(relcount, Subject, Prospect, Relation);
            
            lineScanner.close();
	    
    }
	    relationScanner.close();

		// People Information Import
		
	    Scanner input = new Scanner(new File("people.txt"));
	    input.useDelimiter(",");
	    
			String Name = "";
			String Photo = "";
			String Status = "";
			String Gender = "";
			String SAge = "";
			String State = "";
			
			int Age = 0;
			
			String FName = "";
			String LName = "";
	
		    while(input.hasNext()) {
		        Name = input.next();
		        Photo = input.next();
		        Status = input.next();
		        Gender = input.next();
		        SAge = input.next();
		        State = input.nextLine();

		        // Split out name into two parts
		        String[] names = Name.split("\\s+");
		        FName = names[0];
		        LName = names[1];
		        
		        // Remove whitespace from all strings
		        FName = FName.replace("\\s+","");
		        LName = LName.replace("\\s+","");
		        Gender = Gender.replace("\\s+","");
		        SAge = SAge.replace("\\s+","");
		        SAge = SAge.replace(" ","");
		        State = State.replace("\\s+","");
		        State = State.replace(",","");

		        // Clean talking marks from strings
		        Photo = Photo.replace("“","");
		        Photo = Photo.replace("”","");		        
		        
		        Status = Status.replace("“","");
		        Status = Status.replace("”","");
		        
		        // Remove whitespace before values
		        Gender = Gender.substring(1);
		    //  SAge = SAge.substring(1);
		        State = State.substring(1);
		        Photo = Photo.substring(1);
		        Status = Status.substring(1);
		        
		        // Convert age from string into integer
		        Age = Integer.parseInt(SAge);
		                
		        if (Age > 16) {
		        	accountList[count++] = new Adult(count, Name, Age, Status, Photo, Gender, State, -1, nullList, nullList, nullList, nullList);
		        }
		        // If account is a child/infant, ensure we locate their parents through the next method (RelCombine) - if not found, these records will not display.
		        if (Age <= 2) {
		        	accountList[count++] = new Infant(count, Name, Age, Status, Photo, Gender, State, nullList);
		        }
		        else if (Age > 2 & Age <= 16) {
		        	accountList[count++] = new Child(count, Name, Age, Status, Photo, Gender, State, nullList, nullList, nullList);
		        }
		        
		    }
	}
	
	public static void RelCombine() {
			// Script used to combine the "Relationship" table into accountList object arraylists containing relationships
		
		    // Combine Relationships and People into same arrayList
		    // if "Classmate"
		    
		    for (int r = 0; r < relcount; r++) {

		    	int ID1 = -1;
		    	int ID2 = -1;
		    	
	    		String name1 = relationList[r].getSubject().toLowerCase();
	    		// System.out.println(name1);
	    		Driver.FindAccount(name1, "s");
	    		ID1 = foundAccount;
	    		
	    		foundAccount = -1;
	    		
	    		String name2 = relationList[r].getProspect().toLowerCase();
	    		// System.out.println(name2);
	    		Driver.FindAccount(name2, "s");
	    		ID2 = foundAccount;
	    		
	    		foundAccount = -1;

	    		boolean accountTypeMatch = accountList[ID1].getClass() == accountList[ID2].getClass();
	    		
	    		
	    		if (accountTypeMatch == false) {
	    			// Setting of the parent-child relationship
	    			
	    			if (accountList[ID1] instanceof Adult & accountList[ID2] instanceof Child) {
	    				((Adult)accountList[ID1]).setChild(ID2);
	    				((Child)accountList[ID2]).setParent(ID1);
	    				
	    			} else if (accountList[ID1] instanceof Adult & accountList[ID2] instanceof Infant) {
	    				((Adult)accountList[ID1]).setChild(ID2);
	    				((Infant)accountList[ID2]).setParent(ID1);
	    				
	    			} else if (accountList[ID2] instanceof Adult & accountList[ID1] instanceof Child) {
	    				((Adult)accountList[ID2]).setChild(ID1);
	    				((Child)accountList[ID1]).setParent(ID2);
	    				
	    			} else if (accountList[ID2] instanceof Adult & accountList[ID1] instanceof Infant) {
	    				((Adult)accountList[ID2]).setChild(ID1);
	    				((Infant)accountList[ID1]).setParent(ID2);    				
	    			}
	    		}
	    		
	    		else {
	    			
	    			// Testing for the other relationships possible
		    	if (relationList[r].getRelation().toString().contains("classmate")) {
		    		if (accountList[ID1] instanceof Adult) {
		    			((Adult)accountList[ID1]).setClassmate(ID2);
		    			((Adult)accountList[ID2]).setClassmate(ID1);
		    		} else {
		    			((Child)accountList[ID1]).setClassmate(ID2);
		    			((Child)accountList[ID2]).setClassmate(ID1);
		    		}
		    		
		    	} else if (relationList[r].getRelation().toString().contains("friends")) {
		    		
     				int friend1 = ID1;
     				int friend2 = ID2;
		    		
		    		if (accountList[ID1] instanceof Adult) {
		    			((Adult)accountList[friend1]).setFriend(friend2);
		    			((Adult)accountList[friend2]).setFriend(friend1);
		    		} else {
		    			((Child)accountList[friend1]).setFriend(friend2);
		    			((Child)accountList[friend2]).setFriend(friend1);
		    		}
		    		
		    		friend1 = -1;
		    		friend2 = -1;
		    		
		    	} else if (relationList[r].getRelation().toString().contains("colleague")) {
		    			((Adult)accountList[ID1]).setColleague(ID2);
		    			((Adult)accountList[ID2]).setColleague(ID1);
		    		
		    	} else if (relationList[r].getRelation().toString().contains("couple")) {
	    				((Adult)accountList[ID1]).setPartner(ID2);
	    				((Adult)accountList[ID2]).setPartner(ID1);
		    	}
		    	}
		    }
		}

	public static void CreateAccount(String name, int age, String status, String image, String gender, String state) {

		String keyInput;
		
		String Name = name;
		int Age = age;
		String Status = status;
		String Image = image;
		String Gender = gender;
		String State = state;
		
		ArrayList<Integer> Parents = new ArrayList<Integer>();
		ArrayList<Integer> Friends = new ArrayList<Integer>();
		ArrayList<Integer> Classmates = new ArrayList<Integer>();
		ArrayList<Integer> Colleagues = new ArrayList<Integer>();
		ArrayList<Integer> Children = new ArrayList<Integer>();
		
		System.out.println(count);
		
			if (Age >= 16) {
				accountList[count++] = new Adult(count, Name, Age, Status, Image, Gender, State, -2, Friends, Children, Classmates, Colleagues);
			} 
			if (Age > 2 && Age < 16) {
				accountList[count++] = new Child(count, Name, Age, Status, Image, Gender, State, Parents, Friends, Classmates);
			} 
			if (Age < 2) {
				accountList[count++] = new Infant(count, Name, Age, Status, Image, Gender, State, Parents);
			}
			
			// Nullify found parents for cleaner usage next runtime
			foundParents = null;
	}

	
	public static String FindAccount(String SearchName, String type) {
		// Method designed for searching and identifying people
		// Used by both standard search, as well as the "FindParents" method (triggered upon creating a child or infant)

			String input = SearchName;
			input = input.toLowerCase();
			
			// Clear arraylist for next search
			userArrayList.clear();
			
			int foundUser = -1;

			for (int i = 0; i < count; i++) {
				
				String str = (accountList[i].getName()).toLowerCase();
				if (str.contains(input)) {
				if (type == "m") {
				
				// Check if valid person record
					char vValid = 'y';
					
					if (accountList[i] instanceof Child) {
						if (((Child)accountList[i]).getParents().isEmpty()) {
							// Don't return in results - invalid record	
							vValid = 'n';
					}}
						if (accountList[i] instanceof Infant) {
						if (((Infant)accountList[i]).getParents().isEmpty()) {
							// Don't return in results - invalid record
							vValid = 'n';
					}
				}

				if (vValid == 'y') {
		        	// If search type for account is "multiple"

		        	if (accountList[i].getID() != 0) {
		            // Return account ID with matching search value
		        	foundUser = i;
		        	
		        	String userFound = ("ID: " + accountList[foundUser].getID() + " "
		        	+ "Name: " + accountList[foundUser].getName() + " "
		        			+ "Age: " + accountList[foundUser].getAge());
		        	
		        	userList = userList + (userFound + "\n");
		        	
		        	// Put string into array of strings
		        	userArrayList.add(userFound);
		        	}

				}
				}
			else if (type == "s") {
	        	if (accountList[i].getID() != 0) {
		            // Return account ID with matching search value
	        		foundAccount = i;

			}
			}
				} 
				
			else
		        {
		        		/// If not found, search next record in Array.
		        } 
		    
	        if (userList == "") {
		        userList = ("No User with that name found.");
	        }
		    // Return users
	}
			return userList;
			}

	public static void ViewParents(int sendID) {
		
		// Get ID to find value for
		int findID = sendID;
		findID = findID -1;
			
		//	String parentList = "";
			List<Integer> head = null;
			int[] array = null;
			char cont = 'y';
			
			parentList = "";
			
				if (accountList[findID] instanceof Child) {
					if (((Child)accountList[findID]).getParents().size() != 2) {
						// Don't return in results - invalid record	
						cont = 'n';
				}
					else {
						head = ((Child)accountList[findID]).getParents().subList(0, 2);
					}}
					
					else if (accountList[findID] instanceof Infant) {
						if (((Infant)accountList[findID]).getParents().size() != 2) {

							// Don't return in results - invalid record
							cont = 'n';
						}
						else {
							head = ((Infant)accountList[findID]).getParents().subList(0, 2); 
					}}
				
				if (cont == 'y') {
					array = head.stream().mapToInt(i->i).toArray();
					// Split out the IDs into primitives
					int firstNum = array[0];
					int secondNum = array[1];
					
					String and = " and ";
					array = null;
					
					// Post-mortem checking - won't display parent names if only one exists, but doesn't solve the real problem
					if (accountList[firstNum].getName() == "" | accountList[secondNum].getName() == "") {
						
					} else
					parentList = (accountList[firstNum].getName() + and + accountList[secondNum].getName());
									}
				}

	
	public static void ViewChildren(int sendID) {
		
		// Get ID to find value for
		int findID = sendID;
		
		ArrayList<Integer> childrenList = null;
		childrenArray.clear();
		
				if (accountList[findID] instanceof Adult) {
					
					childrenList = ((Adult)accountList[findID]).getChildren();
					
		    		for(int elem : childrenList){
		    			// Get list of the selected objects children for display in the GUI
		    			if (accountList[elem].getID() != 0) {
		    	    			   String userFound = ("Name: " + accountList[elem].getName() + " Age: " + accountList[elem].getAge());
		    	    			   childrenArray.add(userFound);
		    			}
		    	    			}
				}
				}
	
	public static void ViewColleagues(int sendID) {
				// Get ID to find value for
		int findID = sendID;
		
		ArrayList<Integer> colleagueList = null;
		colleagueArray.clear();
		
				if (accountList[findID] instanceof Adult) {
					
					colleagueList = ((Adult)accountList[findID]).getColleagues();
					
		    		for(int elem : colleagueList){
		    			// Get list of the selected objects colleagues for display in the GUI
		    	    			   if (accountList[elem].getID() != 0) {
		    	    			   String userFound = ("Name: " + accountList[elem].getName() + " Age: " + accountList[elem].getAge());
		    	    			   colleagueArray.add(userFound);
		    	    			   }
		    	    			}
				}
				}
	
	public static void ViewClassmates(int sendID) {
		
		// Get ID to find value for
		int findID = sendID;
		
		ArrayList<Integer> classmateList = null;
		classmateArray.clear();
		
				if (accountList[findID] instanceof Adult) {
					
					classmateList = ((Adult)accountList[findID]).getClassmates();
					
		    		for(int elem : classmateList){
		    			// Get list of the selected objects classmates for display in the GUI
		    			if (accountList[elem].getID() != 0) {
		    	    			   String userFound = ("Name: " + accountList[elem].getName() + " Age: " + accountList[elem].getAge());
		    	    			   classmateArray.add(userFound);
		    	    			}
		    		}
				}
				}
	
	public static void EligibleParents() {
		// Get list of eligible parents to have children/infant accounts created next to
		parentArrayList.clear();
		int partnerID = -1;
		String partnerName = "";
		
		for (int i = 0; i < count; i++) {
			
			// Give all adults, and let us do the combination checking later..
				if (accountList[i] instanceof Adult) {

					if (accountList[i].getID() != 0) {
	            // Return account ID with matching search value
        	
						if (((Adult)accountList[i]).getPartner() != -1) {
							partnerID = ((Adult)accountList[i]).getPartner();
							partnerName = accountList[partnerID].getName();
						}
						else {
							partnerName = "(none)";
						}
						
	        	String userFound = ("ID: " + accountList[i].getID() + " "
	        	+ "Name: " + accountList[i].getName() + " "
	        			+ "Age: " + accountList[i].getAge()) + " Partner: " + partnerName;

	        	parentArrayList.add(userFound);
	        	}
			}
				partnerID = -1;
			}
	}
	
	public static void UpdateAccount(int ID, char action, String Name, int Age, String Status, String Image, String Gender, String State) throws ImmortalParentsException {
		// Updates or removes a profile
		// Still used in GUI

		int searchID = ID;
		
		// If "Delete" Action is commissioned, set the ID to 0 (other variables will also come over as null or zero)
		if (accountList[searchID] instanceof Adult) {
			if (!((Adult)accountList[searchID]).getChildren().isEmpty()) {
				throw new ImmortalParentsException();
			}
		}
		
		if (action == 'd') {
			ArrayList<Integer> nullParents = new ArrayList<Integer>();
			ArrayList<Integer> parents = new ArrayList<Integer>();
			
			if (!(accountList[searchID] instanceof Adult)) {
				
				if (accountList[searchID] instanceof Child) {
					// Get currently assigned parents of child
					parents = ((Child)accountList[searchID]).getParents();

					// Remove parent link from child
					((Child)accountList[searchID]).setParents(nullParents);
					
				} else if (accountList[searchID] instanceof Infant) {
					// Get currently assigned parents of infant
					parents = ((Infant)accountList[searchID]).getParents();
					
					// Remove parent link from infant
					((Infant)accountList[searchID]).setParents(nullParents);
				
			}
				// Remove child link from parent
				int par1 = parents.get(0);
				int par2 = parents.get(1);
				
				ArrayList<Integer> children = new ArrayList<Integer>();
				
				// Remove child from first parent
				children = ((Adult)accountList[par1]).getChildren();
				int index = children.indexOf(searchID);
			    if (index != -1) {
			    	children.remove(index);
			    }
			    ((Adult)accountList[par1]).setChildren(children);
			    
			    // Remove child from second parent
				children = ((Adult)accountList[par2]).getChildren();
				index = children.indexOf(searchID);
			    if (index != -1) {
			    	children.remove(index);
			    }
			    ((Adult)accountList[par2]).setChildren(children);
			    
			    index = 0;
			    
			}
	           	accountList[searchID].setID(0);
							}
		// Continue on overwriting current variable values with either new updates or nulls/zeroes
		        accountList[searchID].setName(Name);
		        accountList[searchID].setAge(Age);
		        accountList[searchID].setStatus(Status);
		        accountList[searchID].setImage(Image);
		        accountList[searchID].setGender(Gender);
		        accountList[searchID].setState(State);
		}
	
	public static void FindFriends(int ID) {
		
		// Friendfinder for GUI app
		int findID = ID;
		// Variable to get size of array for loop
		String userFound = "";
		friendshipArray.clear();
		
		ArrayList<Integer> friendList = null;
		
    	if (accountList[findID] instanceof Adult) {
    		friendList = ((Adult)accountList[findID]).getFriends();
    	} else if (accountList[findID] instanceof Child) {
    		friendList = ((Child)accountList[findID]).getFriends();
    	}
    	
    	if (accountList[findID] instanceof Infant) {
    		// Do nothing
    		
    	} else
    		
    		for(int elem : friendList){
    			// For each friend, get their name and age, and put it into an array of the object's friends for display on the GUI
    			if (accountList[elem].getID() != 0) {
    			   int fID = elem;
    			   userFound = ("Name: " + accountList[fID].getName() + " Age: " + accountList[fID].getAge());
    			   friendshipArray.add(userFound);
    			}
    			}
    		
    	}
	
	public static void AddFriend(int ID1, int ID2) throws NotToBeFriendsException, ConnectionAlreadyExistsException, TooYoungException, SameFamilyException, SameAccountException {
		boolean accountTypeMatch = accountList[ID1].getClass() == accountList[ID2].getClass();
		boolean sameIDMatch = accountList[ID1].getID() == accountList[ID2].getID();
		
		if (accountList[ID1].getAge() < 3 | accountList[ID2].getAge() < 3) {
			System.out.println("Test");
			throw new TooYoungException();
		} else 

		
		if (accountTypeMatch == false) {
			throw new NotToBeFriendsException();
		}
		else if (sameIDMatch == false) {
			if (accountList[ID1] instanceof Adult) {
				boolean alreadyAdded = ((Adult)accountList[ID1]).getFriends().contains(ID2);
				if (alreadyAdded == true) {
					throw new ConnectionAlreadyExistsException();
				} else {
				((Adult)accountList[ID1]).setFriend(ID2);
				((Adult)accountList[ID2]).setFriend(ID1);
			}
				} else if (accountList[ID1] instanceof Child) {
					int arg1 = accountList[ID1].getAge();
					int arg2 = accountList[ID2].getAge();
					int result = 0;
					
					// Would check if these values were null, but currently it's impossible for a child to have no parents
					ArrayList<Integer> parents1 = ((Child)accountList[ID1]).getParents();
					ArrayList<Integer> parents2 = ((Child)accountList[ID2]).getParents();
					
						int child1par1 = parents1.get(0);
						int child1par2 = parents1.get(1);
						
						int child2par1 = parents2.get(0);
						int child2par2 = parents2.get(1);

						if (child1par1 == child2par1 | child1par1 == child2par2 | child1par2 == child2par1) {
							// If children are in same family, throw exception
							throw new SameFamilyException();
						}

					boolean alreadyAdded = ((Child)accountList[ID1]).getFriends().contains(ID2);
					if (alreadyAdded == true) {
						throw new ConnectionAlreadyExistsException();
					} else if (alreadyAdded == false) {
				        if(arg1 <= arg2){
				            result = (arg2 - arg1);
				        } else if (arg1 >= arg2){
				            result = (arg1 - arg2);
				        }
				        if (result > 3) {
				        	throw new NotToBeFriendsException();
					} else {
				        
				((Child)accountList[ID1]).setFriend(ID2);
				((Child)accountList[ID2]).setFriend(ID1);
			}
					} else {
				throw new NotToBeFriendsException();
			}
		} else {
				throw new NotToBeFriendsException();
			}
		} else {
			throw new SameAccountException();
		}
	}
	
	public static void AddClassmates(int ID1, int ID2) throws NotToBeClassmatesException, ConnectionAlreadyExistsException, SameAccountException {
		boolean accountTypeMatch = accountList[ID1].getClass() == accountList[ID2].getClass();
		boolean sameIDMatch = accountList[ID1].getID() == accountList[ID2].getID();
		
		if (sameIDMatch == true) {
			throw new SameAccountException();
		}
		
		if (accountTypeMatch == true) {
			if (accountList[ID1] instanceof Adult) {
				boolean alreadyAdded = ((Adult)accountList[ID1]).getClassmates().contains(ID2);
				if (alreadyAdded == true) {
					throw new ConnectionAlreadyExistsException();
				} else {
				((Adult)accountList[ID1]).setClassmate(ID2);
				((Adult)accountList[ID2]).setClassmate(ID1);
			}
				} else if (accountList[ID1] instanceof Child) {
					boolean alreadyAdded = ((Child)accountList[ID1]).getClassmates().contains(ID2);
					if (alreadyAdded == true) {
						throw new ConnectionAlreadyExistsException();
					} else {
				((Child)accountList[ID1]).setClassmate(ID2);
				((Child)accountList[ID2]).setClassmate(ID1);
			}
					} else {
				throw new NotToBeClassmatesException();
			}}
		} 
	
	public static void AddColleagues(int ID1, int ID2) throws NotToBeColleaguesException, ConnectionAlreadyExistsException, SameAccountException {
		boolean sameIDMatch = accountList[ID1].getID() == accountList[ID2].getID();
		if (sameIDMatch == true) {
			throw new SameAccountException();
		}
		
		boolean alreadyAdded = ((Adult)accountList[ID1]).getColleagues().contains(ID2);
		if (sameIDMatch == false) {
			if (accountList[ID1] instanceof Adult && accountList[ID2] instanceof Adult) {
				if (alreadyAdded == true) {
					throw new ConnectionAlreadyExistsException();
				} else {
				((Adult)accountList[ID1]).setColleague(ID2);
				((Adult)accountList[ID2]).setColleague(ID1);
			}} else {
				throw new NotToBeColleaguesException();
			}
		}
	}
	
	public static void AddCouple(int ID1, int ID2, String type) throws NotToBeCoupledException, NotAvailableException, ConnectionAlreadyExistsException, SameAccountException {
		int parent1 = ID1;
		int parent2 = ID2;

			boolean sameIDMatch = accountList[parent1].getID() == accountList[parent2].getID();
			if (sameIDMatch == true) {
				throw new SameAccountException();
			}
			
			try {
			if (((Adult)accountList[parent1]).getPartner() == ((Adult)accountList[parent2]).getID()) {
				
			}} catch (Exception e9) {
				throw new NotToBeCoupledException();
			}
		
		if (type == "add") {
			boolean alreadyAdded = ((Adult)accountList[parent1]).getPartner() == ((Adult)accountList[parent2]).getID();	
			if (alreadyAdded == true) {
				throw new ConnectionAlreadyExistsException();
			}

			int partner1 = ((Adult)accountList[parent2]).getPartner();
			int partner2 = ((Adult)accountList[parent1]).getPartner();
			
		if (partner1 == parent1) {
			// Do nothing, is good
		} else
			
		if (((Adult)accountList[parent1]).getPartner() == -1 && ((Adult)accountList[parent2]).getPartner() == -1) {
			((Adult)accountList[parent1]).setPartner(parent2);
			((Adult)accountList[parent2]).setPartner(parent1);
		} else {
			throw new NotAvailableException();
		}

		} else if (type == "check") {
			// Don't check "Already Added", we are checking if they exist as a couple for the child-add process
			/* if (sameIDMatch == true) {
				throw new ConnectionAlreadyExistsException();
			}
			*/
			if (((Adult)accountList[parent1]).getPartner() == -1 && ((Adult)accountList[parent2]).getPartner() == -1) {
				// If both partners are unattached, we can proceed without worry
			} else {
				int partner1 = ((Adult)accountList[parent2]).getPartner();
				int partner2 = ((Adult)accountList[parent1]).getPartner();
				
				System.out.println(partner1 + " " + parent1);
				
			if (partner1 == parent1) {
				// These two are already partners, we can proceed without raising an exception
			} else {
				// Raise an exception as at least one of these partners is in a relationship
				throw new NotAvailableException();
			}
			}
		}
	}
	
	public static void AddChild(int parent1, int parent2, int child) {
		// Script used for adding child to parent upon account creation
		if (accountList[child] instanceof Child) {
			((Child)accountList[child]).setParent(parent1);
			((Child)accountList[child]).setParent(parent2);
		} else {
			((Infant)accountList[child]).setParent(parent1);
			((Infant)accountList[child]).setParent(parent2);
		}
		((Adult)accountList[parent1]).setChild(child);
		((Adult)accountList[parent2]).setChild(child);
	}
		

}