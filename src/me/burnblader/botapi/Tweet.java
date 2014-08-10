package me.burnblader.botapi;

import twitter4j.Status;
import twitter4j.TwitterException;

public class Tweet {
	
	private Status status;
	
	public Tweet(Status status) {
		this.status = status;
	}
	
	/**
	 * Returns the user who made the tweet.
	 * 
	 * @return
	 */
	public TwitterUser getUser() {
		return new TwitterUser(status.getUser());
	}
	
	/**
	 * Returns the content within the tweet.
	 * 
	 * @return
	 */
	public String getTweetContent() {
		return status.getText();
	}
	
	/**
	 * Returns whether or not the tweet is a retweet.
	 * 
	 * @return
	 */
	public boolean isRetweet() {
		return status.isRetweet();
	}
	
	/**
	 * Returns how many retweets the tweet got.
	 * 
	 * @return
	 */
	public int getRetweetCount() {
		return status.getRetweetCount();
	}
	
	/**
	 * Returns how many favourites a tweet got.
	 * 
	 * @return
	 */
	public int getFavouriteCount() {
		return status.getFavoriteCount();
	}
	
	/**
	 * Favourites the tweet with the bot supplied.
	 * 
	 * @param b
	 */
	public void favourite(TwitterBot b) {
		try {
			if(b.getListener().onFavourite(this)) {
				b.getTwitter().createFavorite(status.getId());
			}
		} catch(TwitterException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retweets the tweet with the bot supplied.
	 * 
	 * @param b
	 */
	public void retweet(TwitterBot b) {
		try {
			if(b.getListener().onRetweet(this)) {
				b.getTwitter().retweetStatus(status.getId());
			}
		} catch(TwitterException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the Id of the tweet.
	 * 
	 * @return
	 */
	public long getTweetId() {
		return status.getId();
	}
	
}
