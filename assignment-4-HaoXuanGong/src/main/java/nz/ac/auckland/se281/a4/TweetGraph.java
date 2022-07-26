package nz.ac.auckland.se281.a4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.se281.a4.ds.Graph;
import nz.ac.auckland.se281.a4.ds.Node;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************
public class TweetGraph extends Graph {

	protected List<Tweet> tweets;
	// Change this to map
	protected Map<TwitterHandle, List<Tweet>> nodeTweets;

	public TweetGraph(List<String> edges, List<Tweet> tweets, Map<TwitterHandle, List<Tweet>> map) throws Exception {
		super(edges);
		this.tweets = tweets;
		// changed to LinkedHashMap for fixed order
		this.nodeTweets = new LinkedHashMap<>();
		nodeTweets = map;
	}

	public List<Tweet> getTweets(Node n) {
		return nodeTweets.get(n);
	}

	public List<String> getTweetsTexts(TwitterHandle n) {
		List<String> texts = new ArrayList<>(); // Only allowed to use ArrayList HERE !!!
		for (Tweet t : getTweets(n)) {
			texts.add(t.getTextString());
		}
		return texts;
	}

	// search for a keyword in a tweet starting from a given node
	public String searchTweet(TwitterHandle user, String tweetKeyword) {
		TwitterHandle n;
		String s;
		String s2;
		// when the node user has posted tweets that contain the keywords
		for (int l = 0; l < this.nodeTweets.get(user).size(); l++) {
			// search for all the related users to the node user
			if (this.nodeTweets.get(user).get(l).getTextString().contains(tweetKeyword)) {
				// store the texts of the users into a string if it contains the keyword
				s = this.nodeTweets.get(user).get(l).getTextString();
				s = "The tweet string found is: " + s + "\n" + "User " + user.getName() + " {" + user.getID()
						+ "} tweeted " + tweetKeyword;
				return s;
			}
		}
		// when it is the related user that has posted tweet containing the keyword
		for (int i = 0; i < this.adjacencyMap.get(user).size(); i++) {
			// keep creating instances of twitter handle so that we can get their id
			// information
			n = new TwitterHandle(this.adjacencyMap.get(user).get(i).getTarget().getValue());
			for (int a = 0; a < this.nodeTweets.get(n).size(); a++) {
				if (this.nodeTweets.get(n).get(a).getTextString().contains(tweetKeyword)) {
					// if a found text contain the keyword then return the desired output as
					// required
					s = this.nodeTweets.get(n).get(a).getTextString();
					s = "The tweet string found is: " + s + "\n" + "User " + n.getName() + " {" + n.getID()
							+ "} tweeted " + tweetKeyword;
					return s;
				}
			}

		}

		return null;
	}
}
