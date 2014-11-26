import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;

//Ian Berger
//CS 356
//Assignment 2
public class User extends Observable implements Observer, UserGroupComponent{
	private long creationTime;
	private long lastUpdateTime;
	private String id;
	private NewsFeed newsFeed;
	private DefaultListModel<User> currentlyFollowing;
	
	public User(String id) {
		creationTime = System.currentTimeMillis();
		lastUpdateTime = creationTime;
		this.id = id;
		newsFeed = new NewsFeed();
		currentlyFollowing = new DefaultListModel<User>();
		follow(this);//Set user as a follower of themselves
	}
	
	public long getCreationTime() {
		return creationTime;
	}
	
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	public String toString() {
		return id;
	}
	
	public NewsFeed getNewsFeed() {
		return newsFeed;
	}
	
	public DefaultListModel<User> getCurrentlyFollowingListModel() {
		return currentlyFollowing;
	}
	
	//arg is of type Tweet
	@Override
	public void update(Observable o, Object arg) {
		lastUpdateTime = System.currentTimeMillis();
		newsFeed.add((Tweet)arg);
	}
	
	@Override
	public void accept(UserGroupVisitor visitor) {
		//Send visitor to this User
		visitor.visitUser(this);
	}
	
	public void tweet(String message) {
		setChanged();
		notifyObservers(new Tweet(this, message));
	}
	
	public void follow(User user) {
		//Only follow user if they are not already being followed
		if(!currentlyFollowing.contains(user)) {
			user.addObserver(this);
			currentlyFollowing.addElement(user);
		}
	}
}
