package application;

// matt smiley :))

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import java.sql.*;
import javafx.collections.ObservableList;


public class LoginScreen extends Application{
	String checkUser, checkPw;
	User activeUser;
	Scene kattisRank, kattisSub;
	Random rand = new Random();
	static Connection con = null;
	static ObservableList<User> userList = FXCollections.observableArrayList();
	
	public static void main(String[] args) {
		
		try{
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?useSSL=false","root","beans");
			   launch(args);
			   
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
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
		///// Login Submit Button /////////////////////////
			
			btnLogin.setOnAction(new EventHandler<>() {
				public void handle(ActionEvent event) {
	        		checkUser = UNtbox.getText().toString();
	        		System.out.println("user: " + checkUser);
	        		checkPw = PWtbox.getText().toString();
	        		Boolean check = false;
	        		
	        		// Not sure how you guys wanna go about logins via JDBC, but as long as you
	        		// add all the users in through userList (the ArrayList declared up in main)
	        		// then this code should still work fine.
	        		PreparedStatement login;
					try {
						login = con.prepareStatement("SELECT COUNT(*) FROM USERS WHERE US_DISPLAY_NAME = ? AND US_PSWD = ?");
						login.setString(1, checkUser);
		        		login.setString(2, checkPw);
		        		
		        		ResultSet result = login.executeQuery();
		        		result.next();
		        		int x = Integer.parseInt(result.getString(1));
		        		if (x == 1) check = true;
		        		else;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	        		
		        	if(check == true){
		        		
		    			//////////////////////////////////////////////////////
		    			///// KATTIS SCENES //////////////////////////////////
		    			//////////////////////////////////////////////////////
		        		System.out.println("user: " + checkUser);
		    			FileInputStream inputRank = null;
						try {
							inputRank = new FileInputStream(System.getProperty("user.dir") + "\\Kattis_Kat_smol.png");
						} catch (FileNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
		    			
		    			BorderPane kattisBPRank = new BorderPane();
		    			
		    			VBox kattisBoxRank = new VBox();
		    			FlowPane headers = new FlowPane();
		    			FlowPane jerryRigEverything = new FlowPane();
		    			
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
		    			
		    			Font header = Font.font("Arial", FontWeight.BOLD , 20);
		    			Font list = Font.font("Consolas", 18);
		    			
		    			TextArea HR = new TextArea();
		    			HR.setMaxSize(100, 50);
		    			HR.setMinSize(100, 50);
		    			HR.setFont(header);
		    			HR.setText("Rank #:");
		    			HR.setEditable(false);
		    			TextArea HU = new TextArea();
		    			HU.setMaxSize(500, 50);
		    			HU.setMinSize(500, 50);
		    			HU.setFont(header);
		    			HU.setText("Display Names:");
		    			HU.setEditable(false);
		    			TextArea HS = new TextArea();
		    			HS.setMaxSize(100, 50);
		    			HS.setMinSize(100, 50);
		    			HS.setFont(header);
		    			HS.setText("Scores:");
		    			HS.setEditable(false);
		    			
		    			headers.getChildren().add(HR);
		    			headers.getChildren().add(HU);
		    			headers.getChildren().add(HS);
		    			
		    			TextArea tableRank = new TextArea();
		    			tableRank.setMaxWidth(100);
		    			tableRank.setMinWidth(100);
		    			tableRank.setFont(list);
		    			tableRank.setEditable(false);
		    			TextArea tableUsers = new TextArea();
		    			tableUsers.setMaxWidth(500);
		    			tableUsers.setMinWidth(500);
		    			tableUsers.setFont(list);
		    			tableUsers.setEditable(false);
		    			TextArea tableScore = new TextArea();
		    			tableScore.setMaxWidth(100);
		    			tableScore.setMinWidth(100);
		    			tableScore.setFont(list);
		    			tableScore.setEditable(false);
		    			
		    			PreparedStatement variable, rankList;
		    			try {
		    				variable = con.prepareStatement("SET @y = (SELECT r FROM javafx_user_ranklist WHERE u = ?)");
		    				variable.setString(1, checkUser);
		    				variable.execute();
		    				
		    				rankList = con.prepareStatement("SELECT * FROM javafx_user_ranklist WHERE r > (@y-6) AND r < (@y+3)");
		            		ResultSet result = rankList.executeQuery();    
		            		
		            		String temp1 = "Ranks:", temp2 = "Users:", temp3 = "Scores:";
		            		
		            		while (result.next()) {
		            			temp1 = tableRank.getText();
		            			temp2 = tableUsers.getText();
		            			temp3 = tableScore.getText();
		            			tableRank.setText(temp1 + "\n" + result.getInt(1));
		            			tableUsers.setText(temp2 + "\n" + result.getString(2));
		            			tableScore.setText(temp3 + "\n" + result.getDouble(3));
		            		}
		    			} catch (SQLException e) {
		    				e.printStackTrace();
		    			}
		    			
		    			jerryRigEverything.getChildren().add(tableRank);
		    			jerryRigEverything.getChildren().add(tableUsers);
		    			jerryRigEverything.getChildren().add(tableScore);
		    			
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

		    			kattisBoxRank.getChildren().addAll(kattisGPRank, tableTitle, headers, jerryRigEverything);
		    			
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
		    			
		    			FileInputStream inputSub = null;
						try {
							inputSub = new FileInputStream(System.getProperty("user.dir") + "\\Kattis_Kat_smol.png");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
		    			
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
		    			
		    			
		    			PreparedStatement loadProblems;
		    			ArrayList<String> probsAL = new ArrayList<String>();
		    			String probs[] = {};
		    			System.out.println(checkUser);
		    			try {
		    				loadProblems = con.prepareStatement("SELECT P_NAME FROM PROBLEM WHERE P_ID NOT IN (SELECT P_ID FROM user_triumph WHERE US_ID=(SELECT US_ID FROM USERS WHERE US_DISPLAY_NAME = ?))");
		    				loadProblems.setString(1, checkUser);
		            		ResultSet result = loadProblems.executeQuery();
		            		
		            		while (result.next()) {
		            			probsAL.add(result.getString(1));
		            		}
		            		
		            		probs = probsAL.toArray(probs);
		    			} catch (SQLException e) {
		    				e.printStackTrace();
		    			}
		    			
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
		    					
		    					System.out.println("Problem Selected : " + problemChoice);
		    					System.out.println("Submission Language : " + langChoice);
		    					System.out.println("Submitted Code : \n" + code);
		    					
		    					PreparedStatement submission, output;
		    					ResultSet result;
		    					try {
		    						submission = con.prepareStatement("CALL submit((SELECT US_ID FROM USERS WHERE US_DISPLAY_NAME=?), ?,(SELECT P_ID FROM PROBLEM WHERE P_NAME=?), ?, ?, @x)");
		    						submission.setString(1, checkUser);
		    						submission.setString(2, checkPw);
		    						submission.setString(3, problemChoice);
		    						submission.setString(4, langChoice);
		    						submission.setString(5, code);
		    						
		    						submission.executeQuery();
		    		        		
		    						output = con.prepareStatement("SELECT @x");
		    						result = output.executeQuery();
		    						result.next();
		    						
		    						submitResult.setTextFill(Color.BLUE);
		    						submitResult.setText(result.getString(1)); // This should be handled by the stored procedure
		    						}
		    					
		    					catch (SQLException e){
		    						System.out.println(e);
		    					}
		    				}
		    			});
		    			
		    			errorMessage.setText("WOOOOOOOOOOOO");
		        		errorMessage.setTextFill(Color.GREEN);
		        		primaryStage.setScene(kattisRank);
		        		primaryStage.setTitle("Kattniss");
		        		
		        	}

		        	else{
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
