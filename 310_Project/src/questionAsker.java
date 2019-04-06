
public class questionAsker {
	// Attributes to track which questions have already been asked
	public boolean movies = false;
	public boolean music = false;
	public boolean howru = false;
	public boolean countries = false;
	public boolean hobbies = false;
	public boolean dislikes = false;
	public boolean sports = false;
	public boolean rps = false;
	public boolean food = false;
	public boolean animals = false;

	public void setBoolean(boolean input, String data) {
		if (data.equals("movies"))
			movies = input;
		if (data.equals("music"))
			music = input;
		if (data.equals("howru"))
			howru = input;
		if (data.equals("countries"))
			countries = input;
		if (data.equals("hobbies"))
			hobbies = input;
		if (data.equals("dislikes"))
			dislikes = input;
		if (data.equals("sports"))
			sports = input;
		if (data.equals("rps"))
			rps = input;
		if (data.equals("food"))
			food = input;
		if (data.equals("animals"))
			animals = input;
	}

	/**
	 * Returns an array containing a question to ask user and a keyword for data
	 * processing
	 * 
	 * @param void
	 * @return Array containing question and question keyword
	 */
	public String[] ask(scoring score) {
		String outputArray[] = new String[2];
		int random = (int) (Math.random() * 2 + 1);
		if (howru == false) {
			// Picks one of three questions, randomly.
			if (random == 1)
				outputArray[0] = "How are you today?";
			else if (random == 2)
				outputArray[0] = "How are you feeling?";
			else
				outputArray[0] = "How are you doing?";
			outputArray[1] = "howru";
		} else if (movies == false) {
			// Picks one of three questions, randomly.
			if (random == 1)
				outputArray[0] = "What movies do you like?";
			else if (random == 2)
				outputArray[0] = "Seen any good movies lately?";
			else
				outputArray[0] = "What are some of your favourite movies?";
			outputArray[1] = "movies";
		} else if (music == false) {
			// Picks one of three questions, randomly.
			if (random == 1)
				outputArray[0] = "What music do you like?";
			else if (random == 2)
				outputArray[0] = "What genres of music do you listen to?";
			else
				outputArray[0] = "Heard any good music lately?";
			outputArray[1] = "music";
		} else if (countries == false) {
			// Picks one of three questions, randomly.
			if (random == 1)
				outputArray[0] = "What countries do you plan on traveling to?";
			else if (random == 2)
				outputArray[0] = "What countries would you like to travel to?";
			else
				outputArray[0] = "Where would you travel if you could travel anywhere?";
			outputArray[1] = "countries";
		} else if (hobbies == false) {
			// Picks one of three questions, randomly.
			if (random == 1)
				outputArray[0] = "What are your hobbies?";
			else if (random == 2)
				outputArray[0] = "What do you like to do in your spare time?";
			else
				outputArray[0] = "What do you like to do?";
			outputArray[1] = "hobbies";
		} else if (dislikes == false) {
			// Picks one of three questions, randomly.
			if (random == 1)
				outputArray[0] = "What do you hate?";
			else if (random == 2)
				outputArray[0] = "What do you dislike?";
			else
				outputArray[0] = "What are some things you try to avoid in life?";
			outputArray[1] = "dislikes";
		} else if (sports == false) {
			// Picks one of three questions, randomly.
			if (random == 1)
				outputArray[0] = "Do you play any sports?";
			else if (random == 2)
				outputArray[0] = "What are some of your favourite sports?";
			else
				outputArray[0] = "See any good sports games lately?";
			outputArray[1] = "sports";
		} else if (food == false) {
			// Picks one of three questions, randomly.
			if (random == 1)
				outputArray[0] = "What are your favourite foods?";
			else if (random == 2)
				outputArray[0] = "What are some of your favourite foods?";
			else
				outputArray[0] = "What is the best thing to eat after a long day?";
			outputArray[1] = "food";
		} else if (animals == false) {
			// Picks one of three questions, randomly.
			if (random == 1)
				outputArray[0] = "What are your favourite animals?";
			else if (random == 2)
				outputArray[0] = "Do you have any pets?";
			else
				outputArray[0] = "If you could have any pet, what would it be?";
			outputArray[1] = "animals";
		} else if (rps == false) {
			outputArray[0] = "Let's play rock paper scissors! Pick rock, paper or scissors.";
			outputArray[1] = "rps";
		} else {
			if (score.getScore() > 5)
				outputArray[0] = "Sorry, I'm out of things to talk about! This has been really fun though, we have a lot of things in common! I really want to go on another date.";
			else if (score.getScore() > 2)
				outputArray[0] = "Sorry, I'm out of things to talk about! This has been fun though. We should go on another date sometime.";
			else if (score.getScore() < 1)
				outputArray[0] = "Sorry, I'm out of things to talk about! I didn't really see a connection, so I'd prefer not to go on another date.";
			else if(score.getScore() < -3)
				outputArray[0] = "We really don't have anything in common. This date is over.";
			outputArray[1] = "invalid";
		}
		return outputArray;
	}

	public String afterAsk(String input[], String qdata) {
		String output = "";
		boolean empty = false;
		if (input[0].equals("") && input[1].equals(""))
			empty = true;

		if (!input[0].equals(""))
			output = "I like " + input[0] + " too!";
		if (!input[0].equals("") && !input[1].equals(""))
			output = output + "\n";
		if (!input[1].equals(""))
			output = output + "I don't like " + input[1] + ", sorry.";
		if (qdata.equals("dislikes")) {
			output = "I don't like " + input[0] + " either!";
			dislikes = true;
		}
		if (qdata.equals("music")) {
			if (empty)
				output = "Oh! I haven't heard of those music genres.";
			music = true;
		}
		if (qdata.equals("movies")) {
			if (empty)
				output = "Oh! I haven't heard of those movies.";
			movies = true;
		}
		if (qdata.equals("sports")) {
			if (empty)
				output = "Oh! I haven't heard of those sports.";
			sports = true;
		}
		if (qdata.equals("food")) {
			if (empty)
				output = "Oh! I haven't heard of those foods.";
			food = true;
		}
		if (qdata.equals("animals")) {
			if (empty)
				output = "Oh! I haven't heard of those animals.";
			animals = true;
		}
		if (qdata.equals("countries")) {
			if (empty)
				output = "Oh! I haven't heard of those countries.";
			countries = true;
		}
		if (qdata.equals("howru")) {
			if (input[0].equals("bad"))
				output = "I'm sorry to hear that.";
			else
				output = "That's good to hear! :)";
			if (empty)
				output = "Sorry?";
			howru = true;
		}
		if (qdata.equals("hobbies") || qdata.equals("dislikes")) {
			if (empty)
				output = "Oh! I haven't heard of those things before.";
			hobbies = true;
		}
		if (qdata.equals("rps")) {
			int random = (int) (Math.random() * 2 + 1);
			String play = "";
			if (random == 1)
				play = "rock";
			else if (random == 2)
				play = "paper";
			else
				play = "scissors";
			output = "I pick " + play;
			if (input[0].equals("nothing"))
				output = "Well nevermind then.";
			else if (input[0].equals(play))
				output = output + ". We tied! That was so fun.";
			else if (input[0].equals("rock")) {
				if (play.equals("paper"))
					output = output + ". I win! That was so much fun.";
				if (play.equals("scissors"))
					output = output + ". You win. This game sucks anyways.";
			} else if (input[0].equals("paper")) {
				if (play.equals("scissors"))
					output = output + ". I win! That was so much fun.";
				if (play.equals("rock"))
					output = output + ". You win. This game sucks anyways.";
			} else if (input[0].equals("scissors")) {
				if (play.equals("rock"))
					output = output + ". I win! That was so much fun.";
				if (play.equals("paper"))
					output = output + ". You win. This game sucks anyways.";
			}

			rps = true;
		}
		return output;

	}

}
