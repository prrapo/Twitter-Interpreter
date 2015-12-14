import java.io.IOException;
import java.util.*;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.Rule;
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
		for (Rule rule : langTool.getAllRules()) {
			if (!rule.isDictionaryBasedSpellingRule()){
				langTool.disableRule(rule.getId());
			}
		}
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
			} while((query = result.nextQuery()) != null && counter < 5);
		}
		for(String str : twts){
			List<RuleMatch> matches = langTool.check(str);
			for (RuleMatch match : matches) {
				if(match.getSuggestedReplacements().size() > 0){
					NeuralNetwork network = new NeuralNetwork(15, 15, 15);
					Trainer t = new Trainer(network, 1000, str.substring(match.getFromPos(), match.getToPos()).toLowerCase(), match.getSuggestedReplacements().get(0).toLowerCase(), true);
					System.out.println(network.process(str.substring(match.getFromPos(), match.getToPos()).toLowerCase()));
				}
			}
		}
	}
	
}
