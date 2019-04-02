
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
	public boolean turn = false;
	public boolean nameknown = false;
	boolean jobknown = false;
	public String username = "Human";
	public String chatbotname = "CHATBOTNAME";
	public String botoutput = "";
	public String data = "";
	public String qdata = "";
	public boolean afterAsk = false;
	boolean questionAsked = false;
	public String qresponse[] = new String[2];
	public String question[] = new String[2];
	public String userinput = "";
	public boolean askName = false;
	public String result;

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

		Button actionBtn = new Button("Respond");
		actionBtn.setPrefWidth(100);
		actionBtn.setPrefHeight(50);

		TextArea outField = new TextArea("You are on a blind date. Would you like to date a man or a woman?");
		outField.setPrefWidth(800);
		outField.setPrefHeight(2);

		TextField test = new TextField("Text text text");
		test.setPrefWidth(500);
		test.setPrefHeight(10);

		hb.getChildren().addAll(outField);
		hb.setAlignment(Pos.TOP_LEFT);
		hb.setPadding(new Insets(20, 80, 80, 20));
		hb2.getChildren().add(actionBtn);
		hb2.setAlignment(Pos.BOTTOM_LEFT);
		hb2.setPadding(new Insets(20, 20, 20, 20));
		hb3.getChildren().add(test);
		hb3.setAlignment(Pos.BOTTOM_RIGHT);
		hb3.setPadding(new Insets(20, 80, 30, 150));

		StackPane layout = new StackPane();
		layout.getChildren().addAll(hb, hb3, hb2);
		Scene scene = new Scene(layout, 600, 400);
		// layout.setStyle("-fx-background-image:
		// url('https://media.giphy.com/media/nr6Uz7bTJo9uE/giphy.gif');");
		primaryStage.setScene(scene);
		primaryStage.show();

		Image image = new Image(
				"https://upload.wikimedia.org/wikipedia/en/thumb/9/98/Blank_button.svg/1124px-Blank_button.svg.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(1);
		imageView.setFitWidth(1);
//    	TextInputDialog inField = new TextInputDialog();	   
//	    inField.setWidth(200);
//	    inField.setHeight(50);
//	    inField.setHeaderText(username + ":");
//	    inField.setTitle("Response");
//	    inField.setGraphic(imageView);
//	    inField.initStyle(StageStyle.UNDECORATED);
		test.requestFocus();
		// ***********************************************************************

		actionBtn.addEventHandler(chatEvent.CHAT_EVENT, new chatEventHandler() {

			@Override
			public void onEvent1(int param0) {
				System.out.println("integer parameter: " + param0);
			}

			@Override
			public void onEvent2(String param0) {
				System.out.println("string parameter: " + param0);
			}

		});
		actionBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				test.requestFocus();
//		    	TextInputDialog inField = new TextInputDialog();	   
//			    inField.setWidth(200);
//			    inField.setHeight(50);
//			    inField.setHeaderText(username + ":");
//			    inField.setTitle("Response");
//			    inField.setGraphic(imageView);
//			    inField.initStyle(StageStyle.UNDECORATED);
				// System.out.print("\n" + username + ":");
				// String userinput = inputHandler.getUserInput();
				// Loop is called after desired gender and name are chosen, and begins to loop
				// through response/questions
				result = test.getText();
				outField.appendText("\n" + username + ": " + result);
				if (genderchosen == true && nameknown == true && jobknown == true) {
					data = inputHandler.parseInput(result);

					if (result.endsWith("?") && questionAsked == true)
						botoutput = outputDeterminer.respond(inputHandler.keywordConvert(qdata), p);
					else if (data.equals("nothing")) {

						if (questionAsked == true) {
							qresponse = inputHandler.parseQResponse(result.toString(), qdata, p);
							botoutput = questionAsker.afterAsk(qresponse, qdata);
							afterAsk = true;
						} else if (questionAsked == false) {
							question = questionAsker.ask();
							botoutput = question[0];
							qdata = question[1];
							questionAsked = true;
							afterAsk = false;
						}
						if (afterAsk == true)
							questionAsked = false;

					} else
						botoutput = outputDeterminer.respond(data, p);

					outField.appendText("\n" + chatbotname + ": " + botoutput);
				}
				// Determine desired gender from user
				if (genderchosen == false) {
					String gender = inputHandler.checkGender(result);
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
					username = inputHandler.parseName(result).substring(0, 1).toUpperCase()
							+ inputHandler.parseName(result).substring(1);
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
							+ outputDeterminer.occupation(inputHandler.checkOccupation(result)));
					jobknown = true;
				}
				// End conversation if user types "bye"
				if (inputHandler.parseInput(userinput).equals("bye"))
					turn = !turn;
			}
		});
	}
}