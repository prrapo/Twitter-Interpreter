import java.io.IOException;
import java.util.*;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import twitter4j.*;

public class Twitter_Interpreter {
	
	public static boolean isEligible(Status tweet){
		if(!tweet.getLang().equalsIgnoreCase("en")){return false;}
		else if(tweet.isRetweet()){return false;}
		else if(tweet.isTruncated()){return false;}
		else if(tweet.getUserMentionEntities().length > 0){return false;}
		else if(tweet.getExtendedMediaEntities().length > 0){return false;}
		else if(tweet.getMediaEntities().length > 0){return false;}
		else if(tweet.getHashtagEntities().length > 0){return false;}
		else if(tweet.getSymbolEntities().length > 0){return false;}
		else if(tweet.getURLEntities().length > 0){return false;}
		else{return true;}
	}

	public static void main(String[] args) throws TwitterException, IOException {
		Twitter twitter = TwitterFactory.getSingleton();
		JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
		List<String> twts = new ArrayList<String>();
		for(String arg : args){
			Query query = new Query(arg);
			QueryResult result;
			int counter = 0;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					if(isEligible(tweet)){
						System.out.println(tweet.getText());
						twts.add(tweet.getText());
						counter++;
					}
				}
			} while((query = result.nextQuery()) != null && counter < 10);
		}
		for(String str : twts){
			List<RuleMatch> matches = langTool.check(str);
			for (RuleMatch match : matches) {
//				System.out.println(match.getShortMessage());
				if(match.getShortMessage().equalsIgnoreCase("Spelling mistake") && !match.getSuggestedReplacements().isEmpty()){	
					System.out.println(match.getSuggestedReplacements().get(0));
				}
//				System.out.println("Potential error at line " +
//						match.getLine() + ", column " +
//						match.getColumn() + ": " + match.getMessage());
//				System.out.println("Suggested correction: " +
//						match.getSuggestedReplacements());
			}
		}
//		NeuralNetwork network = new NeuralNetwork(15, 15, 15);
//		Trainer t = new Trainer(network, 1000, "tako", "taco");
//		System.out.println(network.process("tako"));
	}
}
