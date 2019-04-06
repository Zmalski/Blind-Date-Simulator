
//import test.Stage;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class terminalwgui extends Application {
	public handleInput inputHandler = new handleInput();
	public determineOutput outputDeterminer = new determineOutput();
	public Personality p = new Personality();
	public questionAsker questionAsker = new questionAsker();
	public boolean genderchosen = false;
	public boolean nameknown = false;
	boolean jobknown = false;
	public String username = "Human";
	public String chatbotname = "CHATBOTNAME";
	public String botoutput = "";
	public String data = "";
	public String qdata = "";
	public boolean afterAsk = false;
	boolean questionAsked = false;
	public String qresponse[] = new String[3];
	public String question[] = new String[2];
	public String userinput = "";
	public boolean askName = false;
	public scoring scorekeeper = new scoring();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Set Scene ***********************************************************
		primaryStage.setTitle("Dating Bot");
		HBox hb = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		HBox hb4 = new HBox();

		Button actionBtn = new Button("Respond");
		actionBtn.setPrefWidth(100);
		actionBtn.setPrefHeight(50);

		TextArea outField = new TextArea("You are on a blind date. Would you like to date a man or a woman?");
		outField.setPrefWidth(600);
		outField.setPrefHeight(2);

		TextField inField = new TextField("Write your response here!");
		inField.setPrefWidth(500);
		inField.setPrefHeight(10);

		Image bad = new Image("file:bad.jpg");
		Image neutralbad = new Image("file:neutralbad.jpg");
		Image neutral = new Image("file:neutral.jpg");
		Image good = new Image("file:good.jpg");
		Image great = new Image("file:great.jpg");
		
		ImageView imageView = new ImageView(neutral);
		imageView.setCache(true);
		
		hb.getChildren().addAll(outField);
		hb.setAlignment(Pos.TOP_LEFT);
		hb.setPadding(new Insets(20, 80, 80, 20));
		hb2.getChildren().add(actionBtn);
		hb2.setAlignment(Pos.BOTTOM_LEFT);
		hb2.setPadding(new Insets(20, 20, 20, 20));
		hb3.getChildren().add(inField);
		hb3.setAlignment(Pos.BOTTOM_RIGHT);
		hb3.setPadding(new Insets(20, 80, 30, 150));
		hb4.getChildren().add(imageView);
		hb4.setAlignment(Pos.TOP_RIGHT);
		hb4.setPadding(new Insets(20, 20, 20, 20));

		StackPane layout = new StackPane();
		layout.getChildren().addAll(hb, hb4, hb3, hb2);
		Scene scene = new Scene(layout, 800, 600);
		// layout.setStyle("-fx-background-image:
		// url('https://media.giphy.com/media/nr6Uz7bTJo9uE/giphy.gif');");
		primaryStage.setScene(scene);
		primaryStage.show();

		

		
		
		
//		Image image = new Image(
//				"https://upload.wikimedia.org/wikipedia/en/thumb/9/98/Blank_button.svg/1124px-Blank_button.svg.png");

//    	TextInputDialog inField = new TextInputDialog();	   
//	    inField.setWidth(200);
//	    inField.setHeight(50);
//	    inField.setHeaderText(username + ":");
//	    inField.setTitle("Response");
//	    inField.setGraphic(imageView);
//	    inField.initStyle(StageStyle.UNDECORATED);
		inField.requestFocus();
		// ***********************************************************************

		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				inField.requestFocus();
				
				
				// Change display image depending on current score
				if(scorekeeper.getScore() > 5)
					imageView.setImage(great);
				else if(scorekeeper.getScore() > 2)
					imageView.setImage(good);
				else if(scorekeeper.getScore() < -2)
					imageView.setImage(neutralbad);
				else if(scorekeeper.getScore() < -5)
					imageView.setImage(bad);
				else
					imageView.setImage(neutral);
				// System.out.print("\n" + username + ":");
				// String userinput = inputHandler.getUserInput();
				// Loop is called after desired gender and name are chosen, and begins to loop
				// through response/questions
				userinput = inField.getText();
				outField.appendText("\n" + username + ": " + userinput);
				if (genderchosen == true && nameknown == true && jobknown == true) {
					data = inputHandler.parseInput(userinput);

					if (userinput.endsWith("?") && questionAsked == true) {
						botoutput = outputDeterminer.respond(inputHandler.keywordConvert(qdata), p);
						questionAsker.setBoolean(true, qdata);
						questionAsked = false;
					}
					
					else if (data.equals("nothing")) { //If user hasn't asked a question.

						if (questionAsked == true) {
							// If question has been asked by bot, respond to response
							qresponse = inputHandler.parseQResponse(userinput.toString(), qdata, p);
							scorekeeper.updateScore(Integer.parseInt(qresponse[2]));
							botoutput = questionAsker.afterAsk(qresponse, qdata);
							afterAsk = true;
						} else if (questionAsked == false) {
							// If question hasn't been asked by bot, ask a question
							question = questionAsker.ask();
							botoutput = question[0];
							qdata = question[1];
							questionAsked = true;
							afterAsk = false;
						}
						if (afterAsk == true)
							// Just a flip, to achieve correct booleans
							questionAsked = false;

					} else
						// If user asked a question, respond.
						botoutput = outputDeterminer.respond(data, p);

					outField.appendText("\n" + chatbotname + ": " + botoutput);
				}
				// Determine desired gender from user
				if (genderchosen == false) {
					String gender = inputHandler.checkGender(userinput);
					p = new Personality(gender);
					chatbotname = p.getName();
					outField.appendText(
							"\n" + "You are now on a date with a " + gender + " named " + chatbotname + ".");
					genderchosen = true;
					outField.appendText("\n" + chatbotname + ": Hi! What's your name?");
				}
				// Determining users name, occupation from user
				else if (nameknown == false) {
					// System.out.println(username + ":");
					username = inputHandler.parseName(userinput).substring(0, 1).toUpperCase()
							+ inputHandler.parseName(userinput).substring(1);
					nameknown = true;
					int random = (int) (Math.random() * 2 + 1);
					if (random == 1)
						outField.appendText("\n" + chatbotname + ": It's nice to meet you, " + username
								+ ". What do you do for a living?");
					else
						outField.appendText("\n" + chatbotname + ": That's a lovely name, " + username
								+ ". So, what do you do for a living?");

				} else if (jobknown == false) {
					outField.appendText("\n" + chatbotname + ": "
							+ outputDeterminer.occupation(inputHandler.checkOccupation(userinput)));
					jobknown = true;
				}
				// End conversation if user types "bye"
				if (inputHandler.parseInput(userinput).equals("bye")) {
					botoutput = "Bye, " + username + "!";
					outField.appendText("\n" + chatbotname + ": " + botoutput);
				}
				inField.clear();
			}
		};
		actionBtn.setOnAction(eventHandler);
		inField.setOnAction(eventHandler);
	}
}