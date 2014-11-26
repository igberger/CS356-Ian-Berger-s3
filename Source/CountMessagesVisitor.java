//Ian Berger
//CS 356
//Assignment 2
public class CountMessagesVisitor implements UserGroupVisitor {
	private int messageCount;
	
	public CountMessagesVisitor() {
		messageCount = 0;
	}
	
	public int getMessageCount() {
		return messageCount;
	}

	@Override
	public void visitUser(User user) {
		//Increase messageCount by the number of messages in user's news feed
		messageCount += user.getNewsFeed().size();
	}
	
	@Override
	public void visitGroup(Group group) {
		//Groups have no messages themselves - do nothing
	}

}
