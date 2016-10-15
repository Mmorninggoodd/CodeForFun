/*

	Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

	postTweet(userId, tweetId): Compose a new tweet.
	getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
	follow(followerId, followeeId): Follower follows a followee.
	unfollow(followerId, followeeId): Follower unfollows a followee.
	Example:

	Twitter twitter = new Twitter();

	// User 1 posts a new tweet (id = 5).
	twitter.postTweet(1, 5);

	// User 1's news feed should return a list with 1 tweet id -> [5].
	twitter.getNewsFeed(1);

	// User 1 follows user 2.
	twitter.follow(1, 2);

	// User 2 posts a new tweet (id = 6).
	twitter.postTweet(2, 6);

	// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
	// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
	twitter.getNewsFeed(1);

	// User 1 unfollows user 2.
	twitter.unfollow(1, 2);

	// User 1's news feed should return a list with 1 tweet id -> [5],
	// since user 1 is no longer following user 2.
	twitter.getNewsFeed(1);

*/

public class Twitter {

    class User {
        Tweet tweetHead;
        Set<User> followees;
        User(){
            this.followees = new HashSet<>();
            this.tweetHead = null;
        }
    }
    class Tweet {
        int timestamp, id;
        Tweet next;
        Tweet(int timestamp, int id) {
            this.timestamp = timestamp;
            this.id = id;
        }
    }
    /** Initialize your data structure here. */
    int timestamp;
    HashMap<Integer, User> users;
    public Twitter() {
        this.timestamp = 0;
        this.users = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!this.users.containsKey(userId)) this.users.put(userId, new User());
        Tweet newTweet = new Tweet(timestamp++, tweetId);
        newTweet.next = this.users.get(userId).tweetHead;
        this.users.get(userId).tweetHead = newTweet;
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if(!this.users.containsKey(userId)) this.users.put(userId, new User());
        PriorityQueue<Tweet> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.timestamp, o1.timestamp));
        User curUser = this.users.get(userId);
        if(curUser.tweetHead != null) pq.offer(curUser.tweetHead);
        for(User followee : curUser.followees) {
            if(followee.tweetHead != null) pq.offer(followee.tweetHead);
        }
        List<Integer> feed = new ArrayList<>();
        while(!pq.isEmpty() && feed.size() < 10) {
            Tweet tweet = pq.poll();
            feed.add(tweet.id);
            if(tweet.next != null) pq.offer(tweet.next);
        }
        return feed;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!this.users.containsKey(followerId)) this.users.put(followerId, new User());
        if(!this.users.containsKey(followeeId)) this.users.put(followeeId, new User());
        if(followerId == followeeId) return;   // cannot follow himself
        User follower = this.users.get(followerId), followee = this.users.get(followeeId);
        follower.followees.add(followee);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!this.users.containsKey(followerId)) this.users.put(followerId, new User());
        if(!this.users.containsKey(followeeId)) this.users.put(followeeId, new User());
        User follower = this.users.get(followerId), followee = this.users.get(followeeId);
        follower.followees.remove(followee);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */