TwitterBotAPI
=============

Using Twitter4j, you can create a Twitter bot using this api.


Example Usage
=============
```java

    Authentication auth = new Authentication();
		auth.setConsumerKey("cosumerKey");
		auth.setSecretConsumerKey("secretConsumerKey");
		auth.setAccessToken("accessToken");
		auth.setSecretAccessToken("secretAccessToken");
		auth.authenticate();
		bot = new TwitterBot(auth, new TweetListener() {

			@Override
			public void onTweetReceive(Tweet tweet) {
				System.out.println("Received tweet by @" + tweet.getUser().getHandle() + ": " + tweet.getTweetContent());
			}

			@Override
			public boolean onTweet(StatusUpdate s) {
				return true;
			}

			@Override
			public boolean onFavourite(Tweet tweet) {
				return true;
			}

			@Override
			public boolean onRetweet(Tweet tweet) {
				return true;
			}

			@Override
			public void onException(Exception e) {
				e.printStackTrace();
			}
			
		}, "#swag");
		bot.beginListening();
		```
