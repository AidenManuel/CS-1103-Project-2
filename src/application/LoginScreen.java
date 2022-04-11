package application;

// matt smiley :))

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class LoginScreen extends Application{
	String checkUser, checkPw;
	User activeUser;
	Scene kattisRank, kattisSub;
	Random rand = new Random();
	static ArrayList<User> userList = new ArrayList<User>();
	
	public static void main(String[] args) {
		User Matt = new User("brother", "bababooey", 1, "New Brunswick", "Canada", 42069);      // These users can be pulled from the database
		User Jeremy = new User("SkullyBot", "mrBusiness", 1, "New Brunswick", "Canada", 99999); // instead of hardcoded. This was for testing.
		User Aiden = new User("CriticalMonkey", "1234Assword", 1, "New Brunswick", "Canada", 0); 
		User Admin = new User("Admin", "0", 0, "", "", 0);
		
		userList.add(Aiden);
		userList.add(Jeremy);
		userList.add(Matt);
		userList.add(Admin);
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//////////////////////////////////////////////////////
			///// LOGIN SCENE ////////////////////////////////////
			//////////////////////////////////////////////////////
			
			FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "\\Kattis_Kat.png");
			
			primaryStage.setTitle("Kattniss Login");
			
			///// BorderPane /////////////////////////
			
			BorderPane loginBP = new BorderPane();
			loginBP.setPadding(new Insets(20, 50, 50, 50));

			///// TilePane /////////////////////////

			TilePane loginTP = new TilePane(Orientation.VERTICAL);
			loginTP.setPadding(new Insets(20, 50, 50, 50));
			loginTP.setVgap(5);
			loginTP.setMaxWidth(100); 
		    loginTP.setPrefColumns(1);
		    
			///// GridPane /////////////////////////

		    GridPane loginGP = new GridPane();
			loginGP.setPadding(new Insets(20, 50, 50, 50));
		    loginGP.setHgap(5);
			loginGP.setVgap(5);
			loginBP.setCenter(loginGP); 
			//loginGP.setGridLinesVisible(true); <-- Just a little thing to help me with debugging

			///// Makin' Nodes /////////////////////////

			Text loginTitle = new Text("Log in to Kattniss");
			loginTitle.setFont(Font.font(null, 30));
			GridPane.setHalignment(loginTitle, HPos.CENTER);
			
			Label UNlabel = new Label("Username or e-mail");
			UNlabel.setFont(Font.font(null, FontWeight.BOLD, 14));
			final TextField UNtbox = new TextField();
			UNlabel.setLabelFor(UNtbox);
			
			Label PWlabel = new Label("Password");
			PWlabel.setFont(Font.font(null, FontWeight.BOLD, 14));
			PWlabel.setAlignment(Pos.CENTER);
			final PasswordField PWtbox = new PasswordField();
			
			Button btnLogin = new Button("Submit");
			Label errorMessage = new Label();
			
			Image i = new Image(input);
			ImageView iw = new ImageView(i);
			Label pictureLogin = new Label("", iw);
			
			///// Putting Nodes in TilePane /////////////////////////
			
			loginTP.getChildren().add(UNlabel);
			loginTP.getChildren().add(UNtbox);
			loginTP.getChildren().add(PWlabel);
			loginTP.getChildren().add(PWtbox);
			loginTP.getChildren().add(btnLogin);
			loginTP.getChildren().add(errorMessage);
			
			///// Putting More Nodes in GridPane /////////////////////////
			
			loginGP.add(loginTitle, 0, 0, 2, 1);
			loginGP.add(pictureLogin, 0, 1);
			loginGP.add(loginTP, 1, 1);

			///// Giving ID's for CSS /////////////////////////
			
			loginBP.setId("borderPane");
			loginTP.setId("tilePane");
			loginGP.setId("gridPane");
			btnLogin.setId("btnLogin");
			
			///// Scene Creation /////////////////////////
			
			Scene login = new Scene(loginBP, 1000, 450);
			login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(login);
			primaryStage.show();
			
			//////////////////////////////////////////////////////
			///// KATTIS SCENES //////////////////////////////////
			//////////////////////////////////////////////////////

			FileInputStream inputRank = new FileInputStream(System.getProperty("user.dir") + "\\Kattis_Kat_smol.png");
			
			BorderPane kattisBPRank = new BorderPane();
			
			VBox kattisBoxRank = new VBox();
			
			GridPane kattisGPRank = new GridPane();
			kattisGPRank.setHgap(0);
			kattisGPRank.setVgap(0);
			kattisBPRank.setCenter(kattisGPRank); 
			//kattisGPRank.setGridLinesVisible(true); <-- Listen, it's useful for debugging okay...
			
			Button ranksRank = new Button("RANKS");
			ranksRank.setFont(Font.font(null, FontWeight.BOLD, 14));
			
			Button submissionRank = new Button("SUBMISSIONS");
			submissionRank.setFont(Font.font(null, FontWeight.BOLD, 14));
				
			///// Creating the Table /////////////////////////	
			
			TableView rankTable = new TableView();
			rankTable.setEditable(true);
			TableColumn rankNum = new TableColumn("#");    //
			rankNum.setMinWidth(200);                      // JavaFX allows you to create a table element
			TableColumn user = new TableColumn("USER");    // by defining it's columns and later defining
			user.setMinWidth(500);                         // what they consist of. These are the columns
			TableColumn score = new TableColumn("SCORE");  //
			score.setMinWidth(300);
			rankTable.getColumns().addAll(rankNum, user, score);
			
			/// Note: For populating the table, I wasn't quite sure how it would interact with JDBC, so
			///       I'm leaving that to you. It's a little funky to get working properly, but this 
			///       link (https://docs.oracle.com/javafx/2/ui_controls/table-view.htm) should hopefully 
			///       clear it up, as it's very well explained. Good luck :D
				
			///// Smol Kattis Cat Icon /////////////////////////	
			
			Image jRank = new Image(inputRank);
			ImageView jwRank = new ImageView(jRank);
			Label pictureKattisRank = new Label("", jwRank);
			
			///// Adding Some Labels /////////////////////////
			
			Label tableTitle = new Label("Ranklist");
			tableTitle.setFont(Font.font(null, FontWeight.BOLD, 21));
			
			Label kattisTitleRank = new Label("Kattniss");
			kattisTitleRank.setFont(Font.font(null, 26));
			
			Label activeUserRank = new Label("");
			activeUserRank.setFont(Font.font(null, 14));
				
			///// Making the Orange Bar /////////////////////////
			
			Rectangle rectRank = new Rectangle(1000,1000,1920*2,5);
			rectRank.setStroke(Color.web("#f0b034"));
			rectRank.setFill(Color.web("#f0b034"));
			
			///// Adding Our Nodes /////////////////////////

			kattisGPRank.add(activeUserRank, 0, 0);			
			kattisGPRank.add(pictureKattisRank, 0, 1, 1, 2);  //
			kattisGPRank.add(kattisTitleRank, 1, 1);          // The header bar is organized in a GridPane,
			kattisGPRank.add(ranksRank, 1, 2);                // and then I will add the table below using
			kattisGPRank.add(submissionRank, 2, 2);           // a basic VBox. For design and organization
			kattisGPRank.add(rectRank, 0, 3, 5, 1);           //
			
			///// Compiling it in the VBox /////////////////////////

			kattisBoxRank.getChildren().addAll(kattisGPRank, tableTitle, rankTable);
			
			///// Setting Id's for CSS /////////////////////////
			
			ranksRank.setId("activeBtn");
			submissionRank.setId("inactiveBtn");
			kattisTitleRank.setId("kattisTitle");
			activeUserRank.setId("activeUser");
			tableTitle.setId("tableTitle");
			kattisGPRank.setId("kattisGP");
			
			///// Finally, we make the Scene /////////////////////////
			
			kattisRank = new Scene(kattisBoxRank, 1000, 700);
			kattisRank.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//////////////////////////////////////////////////////////////////////////////////////
			///// Now, we get on to one of the parts of JavaFX that really grinds my gears.  /////
			///// You see, I was planning on having the header part with the tab bar remain  /////
			///// static, and then reuse it for the Submission tab, to save some space and   /////
			///// minimize the amount of code required. Well, JavaFX said no... Basically,   /////
			///// it turns out you can't reuse nodes for different Scenes, or even different /////
			///// Panes. So, since I'm too far gone now, I decided to just copy the above    /////
			///// code, change all the variable names, and call it a night. No, it's not the /////
			///// best way to do it. But I'm workin with what I've got here. Thanks for      /////
			///// listening to my TED Talk.                                                  /////
			//////////////////////////////////////////////////////////////////////////////////////
			
			FileInputStream inputSub = new FileInputStream(System.getProperty("user.dir") + "\\Kattis_Kat_smol.png");
			
			BorderPane kattisBPSub = new BorderPane();
			
			VBox kattisBoxSub = new VBox();
			
			GridPane kattisGPSub = new GridPane();
			kattisGPSub.setHgap(0);
			kattisGPSub.setVgap(0);
			kattisBPSub.setCenter(kattisGPSub); 
			//kattisGPSub.setGridLinesVisible(true); <-- still a debugging tool, I'm keeping it >:)
			
			Button ranksSub = new Button("RANKS");
			ranksSub.setFont(Font.font(null, FontWeight.BOLD, 14));
			
			Button submissionSub = new Button("SUBMISSIONS");
			submissionSub.setFont(Font.font(null, FontWeight.BOLD, 14));
			
			///// Creating the Choice Boxes /////////////////////////	
			
			/// Note: These are hardcoded, but if we need to pull these from the database
			///       we can just use an ArrayList instead (so we don't have to worry about
			///       number of problems) and pull the values in through JDBC.
			/// Note: If you do pull values from the database, don't forget to add "Malboge"
			///       back as a choice in the java, so that the database will reject it.
			
			
			String langs[] = {("C"), ("C#"), ("C++"), ("COBOL"), ("F#"), ("Go"),
							  ("Haskell"), ("Java"), ("Node.js"), ("SpiderMonkey"), ("Kotlin"), 
							  ("Common Lisp"), ("Malboge"), ("Objective-C"), ("OCaml"), ("Pascal"), ("PHP"), 
							  ("Prolog"), ("Python 2"), ("Python 3"), ("Ruby"), ("Rust")};
			ChoiceBox language = new ChoiceBox(FXCollections.observableArrayList(langs));
			language.setMinWidth(600);
			
			String probs[] = {"Easy", "Killer", "R2", "Speeding", "3-Sided Dice"};
			ChoiceBox problems = new ChoiceBox(FXCollections.observableArrayList(probs));
			problems.setMinWidth(600);
			
			///// Creating the Code Input Area /////////////////////////	
			
			TextArea codeInput = new TextArea();
			codeInput.setMinHeight(100);
			codeInput.setMaxWidth(600);
			codeInput.setWrapText(true);
			
			///// Creating the Code Submit Button /////////////////////////	
			
			Button submit = new Button("Submit!");
			submissionSub.setFont(Font.font(null, FontWeight.BOLD, 14));
			Label submitResult = new Label();
			
			///// Creating Other Nodes /////////////////////////	
			
			Image jSub = new Image(inputSub);
			ImageView jwSub = new ImageView(jSub);
			Label pictureKattisSub = new Label("", jwSub);
			
			Label kattisTitleSub = new Label("Kattniss");
			kattisTitleSub.setFont(Font.font(null, 26));
			
			Label activeUserSub = new Label("");
			activeUserSub.setFont(Font.font(null, 14));
			
			Label Problem = new Label("Problems");
			Problem.setFont(Font.font(null, FontWeight.BOLD, 21));
			
			Label Language = new Label("Languages");
			Language.setFont(Font.font(null, FontWeight.BOLD, 21));
			
			Label Code = new Label("copy/paste code here");
			Code.setFont(Font.font(null, FontWeight.BOLD, 21));
			
			Rectangle rectSub = new Rectangle(1000,1000,1920*2,5);
			rectSub.setStroke(Color.web("#f0b034"));
			rectSub.setFill(Color.web("#f0b034"));
			
			kattisGPSub.add(activeUserSub, 0, 0);
			kattisGPSub.add(pictureKattisSub, 0, 1, 1, 2);
			kattisGPSub.add(kattisTitleSub, 1, 1);
			kattisGPSub.add(ranksSub, 1, 2);
			kattisGPSub.add(submissionSub, 2, 2);
			kattisGPSub.add(rectSub, 0, 3, 5, 1);

			kattisBoxSub.getChildren().addAll(kattisGPSub, Problem, problems, Language, language, Code, codeInput, submit, submitResult);
			
			ranksSub.setId("inactiveBtn");
			submissionSub.setId("activeBtn");
			kattisTitleSub.setId("kattisTitle");
			activeUserSub.setId("activeUser");
			submit.setId("submit");
			kattisGPSub.setId("kattisGP");
			
			kattisSub = new Scene(kattisBoxSub, 1000, 700);
			kattisSub.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			///// Setting Up Button Actions /////////////////////////
			
			ranksSub.setOnAction(new EventHandler<>() {
				public void handle(ActionEvent event) {
					primaryStage.setScene(kattisRank);  // This is for switching to the Ranks Tab
				}
			});
			
			submissionRank.setOnAction(new EventHandler<>() {
				public void handle(ActionEvent event) {
	        		primaryStage.setScene(kattisSub);   // This is for switching to the Submissions Tab
				}
			});
			
			
			// This handles and gives console output for the submission, which you can integrate with JDBC
			
			submit.setOnAction(new EventHandler<>() {
				public void handle(ActionEvent event) {
					String problemChoice = "" + problems.getValue();
					String langChoice = "" + language.getValue();
					String code = codeInput.getText();
					int random = rand.nextInt(100);
					
					System.out.println("Problem Selected : " + problemChoice);
					System.out.println("Submission Language : " + langChoice);
					System.out.println("Submitted Code : \n" + code);
					
					/// Note: This is where you'll wanna take your query information for a submission
					///       from. All the information of the submission is stored in those three
					///       variables for you to put into the query.
					
					// Just doing a random 50/50 for output right now, but you can integrate this with the stored procedure
					
					if (langChoice.equals("" + "Malboge")) {
						submitResult.setTextFill(Color.DARKRED);
						submitResult.setText("Sorry, language not supported D:"); // This should be handled by the stored procedure
					} else if (random <= 50) {
						submitResult.setTextFill(Color.DARKRED);
						submitResult.setText("Submission Incorrect...");
					} else if (random > 50) {
						submitResult.setTextFill(Color.GREEN);
						submitResult.setText("Submission Correct!");
					}
				}
			});
			
			///// Login Submit Button /////////////////////////
			
					btnLogin.setOnAction(new EventHandler<>() {
						public void handle(ActionEvent event) {
			        		checkUser = UNtbox.getText().toString();
			        		checkPw = PWtbox.getText().toString();
			        		Boolean check = false;
			        		
			        		// Not sure how you guys wanna go about logins via JDBC, but as long as you
			        		// add all the users in through userList (the ArrayList declared up in main)
			        		// then this code should still work fine.
			        		
			        		for (User u : userList) {
				        		if(checkUser.equals(u.getName()) && checkPw.equals(u.getPassword())){ 
				        			errorMessage.setText("WOOOOOOOOOOOO");
				        			errorMessage.setTextFill(Color.GREEN);
				        			primaryStage.setScene(kattisRank);
				        			primaryStage.setTitle("Kattniss");
				        			check = true;
				        			activeUser = u;
				        			activeUserRank.setText("Logged in as " + activeUser.getName());
				        			activeUserSub.setText("Logged in as " + activeUser.getName());
				        			
				        		}
			        		}

			        		if (!check){
			        			errorMessage.setText("Something's wrong here...");
			        			errorMessage.setTextFill(Color.DARKRED);
			        		}
			        		
			        		UNtbox.setText("");
			        		PWtbox.setText("");
						}
					});
			
			///// Haha, remember login? I sure didn't the first few times I ran this... /////////////////////////
			
			primaryStage.setScene(login);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
