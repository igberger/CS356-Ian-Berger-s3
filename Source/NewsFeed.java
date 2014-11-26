import java.util.Observable;

import javax.swing.DefaultListModel;

//Ian Berger
//CS 356
//Assignment 2
public class NewsFeed extends Observable {
	private DefaultListModel<Tweet> newsFeed;
	
	public NewsFeed() {
		newsFeed = new DefaultListModel<Tweet>();
	}
	
	public DefaultListModel<Tweet> getListModel() {
		return newsFeed;
	}
	
	public void add(Tweet tweet) {
		newsFeed.addElement(tweet);
		setChanged();
		notifyObservers(tweet);
	}
	
	public Tweet get(int i) {
		return newsFeed.get(i);
	}
	
	public int size() {
		return newsFeed.size();
	}
}
