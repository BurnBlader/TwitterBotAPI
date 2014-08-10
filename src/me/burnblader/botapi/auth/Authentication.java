package me.burnblader.botapi.auth;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Authentication {
	
	private String consumerKey;
	private String secretConsumerKey;
	
	private String accessToken;
	private String secretAccessToken;
	
	private Configuration configuration;
	
	public Authentication(String consumerKey, String secretConsumerKey, String accessToken, String secretAccessToken) {
		this.consumerKey = consumerKey;
		this.secretConsumerKey = secretConsumerKey;
		this.accessToken = accessToken;
		this.secretAccessToken = secretAccessToken;
	}
	
	public Authentication() {
		
	}
	
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	
	public void setSecretConsumerKey(String secretConsumerKey) {
		this.secretConsumerKey = secretConsumerKey;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public void setSecretAccessToken(String secretAccessToken) {
		this.secretAccessToken = secretAccessToken;
	}
	
	/**
	 * Authenticates your bot with Twitter.
	 * 
	 */
	public void authenticate() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
			.setOAuthConsumerSecret(secretConsumerKey)
			.setOAuthAccessToken(accessToken)
			.setOAuthAccessTokenSecret(secretAccessToken);
		configuration = cb.build();
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
	
}
