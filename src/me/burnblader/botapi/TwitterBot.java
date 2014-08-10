package me.burnblader.botapi;

import me.burnblader.botapi.auth.Authentication;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterBot {
	
	private Authentication authentication;
	private TweetListener listener;
	private String[] tags;
	
	private Twitter twitter;
	
	public TwitterBot(Authentication authentication, TweetListener listener, String... tags) {
		this.authentication = authentication;
		this.listener = listener;
		this.tags = tags;
		twitter = new TwitterFactory(authentication.getConfiguration()).getInstance();
	}
	
	public TwitterBot(Authentication authentication) {
		this.authentication = authentication;
		twitter = new TwitterFactory(authentication.getConfiguration()).getInstance();
	}
	
	/**
	 * Returns instance of Twitter.
	 * 
	 * @return
	 */
	public Twitter getTwitter() {
		return twitter;
	}
	
	/**
	 * Returns the Authentication of the Bot.
	 * 
	 * @return
	 */
	public Authentication getAuthentication() {
		return authentication;
	}
	
	/**
	 * Tweets using the content supplied.
	 * 
	 * @param content
	 */
	public void tweet(String content) {
		StatusUpdate statusUpdate = new StatusUpdate(content);
		if(listener != null) {
			if(listener.onTweet(statusUpdate)) {
				try {
					twitter.updateStatus(statusUpdate);
				} catch(TwitterException e) {
					listener.onException(e);
				}
			}
		} else {
			try {
				twitter.updateStatus(statusUpdate);
			} catch(TwitterException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Replies to the tweet with the content specified.
	 * 
	 * @param inReplyTo
	 * @param content
	 */
	public void tweet(Tweet inReplyTo, String content) {
		StatusUpdate statusUpdate = new StatusUpdate(content);
		statusUpdate.setInReplyToStatusId(inReplyTo.getTweetId());
		if(listener != null) {
			if(listener.onTweet(statusUpdate)) {
				try {
					twitter.updateStatus(statusUpdate);
				} catch(TwitterException e) {
					listener.onException(e);
				}
			}
		} else {
			try {
				twitter.updateStatus(statusUpdate);
			} catch(TwitterException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns the instance of the TweetListener.
	 * 
	 * @return
	 */
	public TweetListener getListener() {
		return listener;
	}
	
	/**
	 * Begins reading tweets with the tags specified.
	 * 
	 */
	public void beginListening() {
		if(listener == null) {
			System.out.println("Tried to being listening when tags and listener wasn't specified.");
			return;
		}
		StatusListener statusListener = new StatusListener() {

			@Override
			public void onException(Exception e) {
				listener.onException(e);
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {}

			@Override
			public void onScrubGeo(long arg0, long arg1) {}

			@Override
			public void onStallWarning(StallWarning arg0) {}

			@Override
			public void onStatus(Status status) {
				listener.onTweetReceive(new Tweet(status));
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {}
			
		};
		
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance(twitter.getAuthorization());
		twitterStream.addListener(statusListener);
		
		twitterStream.filter(new FilterQuery(0, null, tags));
	}
}
