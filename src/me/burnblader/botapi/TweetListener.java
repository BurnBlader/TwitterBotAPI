package me.burnblader.botapi;

import twitter4j.StatusUpdate;

public abstract class TweetListener {
	
	public abstract void onTweetReceive(Tweet tweet);
	public abstract boolean onTweet(StatusUpdate s);
	public abstract boolean onFavourite(Tweet tweet);
	public abstract boolean onRetweet(Tweet tweet);
	public abstract void onException(Exception e);
	
}
