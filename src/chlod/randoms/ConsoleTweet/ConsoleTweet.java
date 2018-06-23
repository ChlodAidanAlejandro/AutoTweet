package chlod.randoms.ConsoleTweet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * ConsoleTweet gives any Java command line prompt the ability to
 * automatically tweet without any other permissions.
 * 
 * ConsoleTweet and ConsoleTweeter are the same thing
 * 
 * @author Chlod Aidan Alejandro
 * @version 0.1
 */
public class ConsoleTweet {

	/**
	 * TODO Emoji support TODO Better time date functioning system TODO V1
	 */

	public static final String VERSION = "v0.5";
	public static final int VERSION_CODE = 5;
	private static Twitter t;

	public static void main(String[] args) {
		// verification
		boolean proceed = true;
		// run statement
		logAppend("RUN " + getTime());
		// logger initialization
		logAppend(getTime() + "Logger Initialized.");
		//read flags
		boolean herokumode = true;
		for (String s : args) {
			if (s.equals("--normal")) herokumode = false;
		}
		// initialize Twitter module
		logAppend(getTime() + "Initializing twitter module...");
		if (herokumode) {
			t = initTwitter();
		} else {
			File f = new File("AutoTweet.cfg");
			try {
				Scanner cfg = new Scanner(new FileReader(f));
				List<String> config_lines = new ArrayList<String>();
				while (cfg.hasNextLine()) {
					config_lines.add(cfg.nextLine());
				}
				cfg.close();
				if (config_lines.size() != 4) {
					logAppend(getTime() + "Invalid config. Refer to normal mode config manual.");
					end();
				} else {
					t = initTwitter(config_lines.get(0), config_lines.get(1), config_lines.get(2), config_lines.get(3));
				}
			} catch (FileNotFoundException e) {
				logAppend(getTime() + "No config found. Refer to normal mode config manual.");
				end();
			}
		}
		logAppend(getTime() + "Twitter module initialized");
		logAppend(getTime() + "Confirming settings...");
		try {
			t.verifyCredentials();
		} catch (TwitterException e) {
			if (e.getStatusCode() == 401) {
				logAppend(getTime() + "Supplied credentials incorrect. Please retry.");
				end();
			} else {
				logAppend(getTime() + "Error reaching Twitter.");
				end();
			}
		} catch (IllegalStateException e) {
			logAppend(getTime() + "Invalid credentials. Recheck your config variables.");
			end();
		}
		// set buildup strings
		String a = "";
		String b = "";
		// piece tweet together
		logAppend(getTime() + "Starting tweet syntaxing...");
		a = TweetStringHandler.handleTweetString(args[0]);
		b = args[0];
		logAppend(getTime() + "Syntaxed tweet. Locating errors...");
		// locate errors
		// twitter exception from syntax tag
		if (a.contains("--ERR:twe:break:now:hndlfl--")) {
			logAppend(getTime() + "An error occured during tweet string handling.");
			logAppend(getTime() + "A Twitter Excpetion occured on a Twitter-related syntax tag");
			end();
		} else {
			// successful piecing
			logAppend(getTime() + "Tweet piecing successful.");
			logAppend(getTime() + "Raw input: " + b);
			logAppend(getTime() + "Syntaxed input: " + a);
		}
		// if tweet size is 0
		logAppend(getTime() + "All error checks complete. Preparing tweet...");
		if (a.length() == 0) {
			logAppend(getTime() + "No arguments input. No tweet output.");
			end();
		}
		// if tweet exceeds T4J limit
		if (a.length() > 280) {
			logAppend(getTime() + "Tweet must not exceed 140 characters (tweet has " + a.length() + ")");
			end();
		}
		// check if proceed
		if (proceed) {
			logAppend(getTime() + "Tweet is a go. Sending tweet...");
			try {
				// update status and put result into local variable
				Status s = t.updateStatus(a);
				// confirm result
				logAppend(getTime() + "Tweeted \"" + s.getText() + "\" sucessfully at " + s.getCreatedAt());
				logAppend(getTime() + "ID: " + s.getId());
			} catch (TwitterException e) {
				// catch duplicates, errors, etc.
				logAppend(getTime() + "Tweeting failed");
				e.printStackTrace();
			}
		} else {
			logAppend(getTime() + "Tweet is a no-go. Exiting program...");
		}
		// program end
		end();
	}
	
	public static void end() {
		logAppend("END " + getTime());
		System.exit(0);
	}

	public static Twitter initTwitter() {
    	ConfigurationBuilder config = new twitter4j.conf.ConfigurationBuilder()
	      .setOAuthConsumerKey(System.getenv("TWITTER-CONSUMER_KEY"))
	      .setOAuthConsumerSecret(System.getenv("TWITTER-CONSUMER_SECRET"))
	      .setOAuthAccessToken(System.getenv("TWITTER-ACCESS_TOKEN"))
	      .setOAuthAccessTokenSecret(System.getenv("TWITTER-ACCESS_TOKEN_SECRET"))
	      .setTweetModeExtended(true);
	    TwitterFactory tf = new TwitterFactory(config.build());
	    Twitter twitter = tf.getInstance();
		return twitter;
	}
	
	public static Twitter initTwitter(String ck, String cs, String at, String ats) {
    	ConfigurationBuilder config = new twitter4j.conf.ConfigurationBuilder()
	      .setOAuthConsumerKey(System.getenv("TWITTER-CONSUMER_KEY"))
	      .setOAuthConsumerSecret(System.getenv("TWITTER-CONSUMER_SECRET"))
	      .setOAuthAccessToken(System.getenv("TWITTER-ACCESS_TOKEN"))
	      .setOAuthAccessTokenSecret(System.getenv("TWITTER-ACCESS_TOKEN_SECRET"))
	      .setTweetModeExtended(true);
	    TwitterFactory tf = new TwitterFactory(config.build());
	    Twitter twitter = tf.getInstance();
		return twitter;
	}
	
	public static void logAppend(String s) {
		// output to bash/prompt/terminal
		System.out.println(s);
	}

	public static String getTime() {
		// create format
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// get date in unix timestamp
		Calendar date = Calendar.getInstance();
		if (!System.getenv("TIMEZONE").equals("")) {
			date.add(Calendar.HOUR_OF_DAY, Integer.parseInt(System.getenv("TIMEZONE")));
		}
		// format date in log mode
		return "[" + dateFormat.format(date) + "] ";
	}

	public static Boolean last(int a, int b) {
		// check if a equal to b
		if (a == b) {
			return true;
		} else {
			return false;
		}
	}

	public static Twitter getTwitter() {
		return t;
	}

}
