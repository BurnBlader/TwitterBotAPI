package me.burnblader.botapi;

import twitter4j.User;

public class TwitterUser {
	
	private User user;
	
	public TwitterUser(User user) {
		this.user = user;
	}
	
	/**
	 * Get the Twitter @ Handle.
	 * 
	 * @return
	 */
	public String getHandle() {
		return user.getScreenName();
	}
	
	/**
	 * Get the name specified on the user's profile.
	 * 
	 * @return
	 */
	public String getProfileName() {
		return user.getName();
	}
	
	/**
	 * Get the user's Id.
	 * 
	 * @return
	 */
	public long getId() {
		return user.getId();
	}
	
}
