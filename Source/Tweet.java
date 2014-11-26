//Ian Berger
//CS 356
//Assignment 2
public class Tweet {
	private User user;
	private String message;
	
	public Tweet(User user, String message) {
		this.user = user;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public User getUser() {
		return user;
	}
	
	public String toString() {
		return user + ": " + message;
	}
	
}
