package chlod.randoms.ConsoleTweet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import twitter4j.TwitterException;

public class TweetStringHandler {
	
	public static String handleTweetString(String a) {
		String x = a;
		//formattedDate //will i show (//all dates, //month, //day, //year, //all times, //hour, //minute,
		//second, //24 hour hours, //meridian, //day of the week, //time zone)
		if (x.contains("--datetime--")) {
			x = x.replaceAll("--datetime--", formattedDate(true, true, true, true, true, true, true, false, false, true, false, false));
		} if (x.contains("--date--")) {
			x = x.replaceAll("--date--", formattedDate(true, true, true, true, false, true, true, false, false, true, false, false));
		} if (x.contains("--time--")) {
			x = x.replaceAll("--time--", formattedDate(false, true, true, true, true, true, true, false, false, true, false, false));
		} if (x.contains("--24datetime--")) {
			x = x.replaceAll("--24datetime--", formattedDate(true, true, true, true, true, true, true, false, true, true, false, false));
		} if (x.contains("--24date--")) {
			x = x.replaceAll("--24date--", formattedDate(true, true, true, true, false, true, true, false, true, true, false, false));
		} if (x.contains("--24time--")) {
			x = x.replaceAll("--24time--", formattedDate(false, true, true, true, true, true, true, false, true, true, false, false));
		} if (x.contains("--datetimes--")) {
			x = x.replaceAll("--datetimes--", formattedDate(true, true, true, true, true, true, true, true, false, true, false, false));
		} if (x.contains("--times--")) {
			x = x.replaceAll("--times--", formattedDate(false, true, true, true, true, true, true, true, false, true, false, false));
		} if (x.contains("--24datetimes--")) {
			x = x.replaceAll("--24datetimes--", formattedDate(true, true, true, true, true, true, true, true, true, true, false, false));
		} if (x.contains("--24times--")) {
			x = x.replaceAll("--24times--", formattedDate(false, true, true, true, true, true, true, true, true, true, false, false));
		} if (x.contains("--dydatetime--")) {
			x = x.replaceAll("--dydatetime--", formattedDate(true, true, true, true, true, true, true, false, false, true, true, false));
		} if (x.contains("--dydate--")) {
			x = x.replaceAll("--dydate--", formattedDate(true, true, true, true, false, true, true, false, false, true, true, false));
		} if (x.contains("--dy24datetime--")) {
			x = x.replaceAll("--dy24datetime--", formattedDate(true, true, true, true, true, true, true, false, true, true, true, false));
		} if (x.contains("--dy24date--")) {
			x = x.replaceAll("--dy24date--", formattedDate(true, true, true, true, false, true, true, false, true, true, true, false));
		} if (x.contains("--dydatetimes--")) {
			x = x.replaceAll("--dydatetimes--", formattedDate(true, true, true, true, true, true, true, true, false, true, true, false));
		} if (x.contains("--dy24datetimes--")) {
			x = x.replaceAll("--dy24datetimes--", formattedDate(true, true, true, true, true, true, true, true, true, true, true, false));
		} try {
			//TWITTER COMMANDS
			if (x.contains("--displayname--")) {
				x = x.replaceAll("--displayname--", ConsoleTweet.getTwitter().verifyCredentials().getName());
			} if (x.contains("--followers--")) {
				x = x.replaceAll("--followers--","" + ConsoleTweet.getTwitter().verifyCredentials().getFollowersCount());
			} if (x.contains("--following--")) {
				x = x.replaceAll("--following--","" + ConsoleTweet.getTwitter().verifyCredentials().getFriendsCount());
			} if (x.contains("--username--")) {
				x = x.replaceAll("--username--", ConsoleTweet.getTwitter().verifyCredentials().getScreenName());
			} if (x.contains("--tweetcount--")) {
				x = x.replaceAll("--tweetcount--", "" + ConsoleTweet.getTwitter().verifyCredentials().getStatusesCount());
			} if (x.contains("--usermade--")) {
				DateFormat df = new SimpleDateFormat("EEE MM/dd/yyyy hh:mm:ss a");
				x = x.replaceAll("--usermade--", df.format(ConsoleTweet.getTwitter().verifyCredentials().getCreatedAt()));
			} if (x.contains("--usertz--")) {
				x = x.replaceAll("--usertz--", ConsoleTweet.getTwitter().verifyCredentials().getTimeZone());
			} if (x.contains("--userdesc--")) {
				x = x.replaceAll("--userdesc--", ConsoleTweet.getTwitter().verifyCredentials().getDescription());
			} if (x.contains("--userid--")) {
				x = x.replaceAll("--userid--", "" + ConsoleTweet.getTwitter().verifyCredentials().getId());
			} if (x.contains("--userloc--")) {
				x = x.replaceAll("--userloc--", ConsoleTweet.getTwitter().verifyCredentials().getLocation());
			} if (x.contains("--favorites--")) {
				x = x.replaceAll("--favorites--", "" + ConsoleTweet.getTwitter().verifyCredentials().getFavouritesCount());
			} if (x.contains("--lists--")) {
				x = x.replaceAll("--lists--", "" + ConsoleTweet.getTwitter().verifyCredentials().getListedCount());
			} if (x.contains("--userlang--")) {
				x = x.replaceAll("--userlang--", ConsoleTweet.getTwitter().verifyCredentials().getLang());
			} if (x.contains("--userlink--")) {
				x = x.replaceAll("--userlink--", ConsoleTweet.getTwitter().verifyCredentials().getURL());
			} if (x.contains("--userprofile--")) {
				x = x.replaceAll("--userprofile--", "https://twitter.com/" + ConsoleTweet.getTwitter().verifyCredentials().getName());
			} if (x.contains("--userdp--")) {
				x = x.replaceAll("--userdp--", ConsoleTweet.getTwitter().verifyCredentials().getOriginalProfileImageURL());
			} if (x.contains("--userpcolor--")) {
				x = x.replaceAll("--userpcolor--", ConsoleTweet.getTwitter().verifyCredentials().getProfileBackgroundColor());
			} if (x.contains("--userbanner--")) {
				x = x.replaceAll("--userbanner--", ConsoleTweet.getTwitter().verifyCredentials().getProfileBannerURL());
			}
		} catch (TwitterException e) {
			ConsoleTweet.logAppend(getTime() + "User profile banner link retrieval failed");
			e.printStackTrace();
			return "!--ERR:twe:break:now:hndlfl--!";
		}
		if (x.contains("\n") || x.contains("%n") || x.contains("\\\\n")) {
			x = a.replaceAll("\n", " ");
			x = x.replaceAll("%n", " ");
			return x.replaceAll("\\\\n", " ");
		}
		return x;
	}

	public static String formattedDate(Boolean dates, Boolean mon, Boolean date, Boolean year, Boolean times, Boolean hour, Boolean min, Boolean sec, Boolean h24, Boolean mer, Boolean day, Boolean zone) {
		String a = "";
		if (!hour && !min && !sec && !mer) times = false;
		if (!hour) h24 = false;
		if (!day && !mon && !date && !year) dates = false;
		if (dates) {
			if (!day && mon && date && year) a = a + "MM/dd/yyyy";
			if (!day && !mon && date && year) a = a + "dd/yyyy";
			if (!day && mon && !date && year) a = a + "MM/yyyy";
			if (!day && mon && date && !year) a = a + "MM/dd";
			if (!day && !mon && !date && year) a = a + "yyyy";
			if (!day && mon && !date && !year) a = a + "MM";
			if (!day && !mon && date && !year) a = a + "dd";
			if (day && mon && date && year) a = a + "EEE MM/dd/yyyy";
			if (day && !mon && date && year) a = a + "EEE dd/yyyy";
			if (day && mon && !date && year) a = a + "EEE MM/yyyy";
			if (day && mon && date && !year) a = a + "EEE MM/dd";
			if (day && !mon && !date && year) a = a + "EEE yyyy";
			if (day && mon && !date && !year) a = a + "EEE MM";
			if (day && !mon && date && !year) a = a + "EEE dd";
			if (times || zone) a = a + " ";
		}
		if (times) {
			if (hour && min && sec && !h24 && mer) a = a + "hh:mm:ss a";
			if (hour && min && !sec && !h24 && mer) a = a + "hh:mm a";
			if (hour && !min && sec && !h24 && mer) a = a + "hh:ss a";
			if (!hour && min && sec && !h24 && mer) a = a + "mm:ss a";
			if (hour && !min && !sec && !h24 && mer) a = a + "hh a";
			if (!hour && min && !sec && !h24 && mer) a = a + "mm a";
			if (!hour && !min && sec && !h24 && mer) a = a + "ss a";
			if (!hour && !min && !sec && !h24 && mer) a = a + "a";
			if (hour && min && sec && !h24 && !mer) a = a + "hh:mm:ss";
			if (hour && min && !sec && !h24 && !mer) a = a + "hh:mm";
			if (hour && !min && sec && !h24 && !mer) a = a + "hh:ss";
			if (!hour && min && sec && !h24 && !mer) a = a + "mm:ss";
			if (hour && !min && !sec && !h24 && !mer) a = a + "hh";
			if (!hour && min && !sec && !h24 && !mer) a = a + "mm";
			if (!hour && !min && sec && !h24 && !mer) a = a + "ss";
			if (hour && min && sec && h24 && mer) a = a + "HH:mm:ss a";
			if (hour && min && !sec && h24 && mer) a = a + "HH:mm a";
			if (hour && !min && sec && h24 && mer) a = a + "HH:ss a";
			if (!hour && min && sec && h24 && mer) a = a + "mm:ss a";
			if (hour && !min && !sec && h24 && mer) a = a + "HH a";
			if (!hour && min && !sec && h24 && mer) a = a + "mm a";
			if (!hour && !min && sec && h24 && mer) a = a + "ss a";
			if (!hour && !min && !sec && h24 && mer) a = a + "a";
			if (hour && min && sec && h24 && !mer) a = a + "HH:mm:ss";
			if (hour && min && !sec && h24 && !mer) a = a + "HH:mm";
			if (hour && !min && sec && h24 && !mer) a = a + "HH:ss";
			if (!hour && min && sec && h24 && !mer) a = a + "mm:ss";
			if (hour && !min && !sec && h24 && !mer) a = a + "HH";
			if (!hour && min && !sec && h24 && !mer) a = a + "mm";
			if (!hour && !min && sec && h24 && !mer) a = a + "ss";
			if (zone) a = a + " ";
		}
		if (zone) a = a + "z";
		DateFormat dateFormat = new SimpleDateFormat(a);
		Date datessss = new Date();
		return dateFormat.format(datessss);
	}
	
	public static String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		return "[" + dateFormat.format(date) + "] ";
	}
	
}
