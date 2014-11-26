//Ian Berger
//CS 356
//Assignment 2
public class CountPositiveMessagesVisitor implements UserGroupVisitor {
	private int messageCount;
	private int positiveMessageCount;
	//lower case phrases used to determine a positive message
	private static final String[] positivePhrases = {"good", "great", "excellent", "cool"};
	
	public CountPositiveMessagesVisitor() {
		positiveMessageCount = 0;
	}
	
	public int getPositiveMessagePercentage() {
		//Calculate and return positive message percentage
		return (int)(100*(double)positiveMessageCount/messageCount);
	}
	
	@Override
	public void visitUser(User user) {
		NewsFeed newsFeed = user.getNewsFeed();
		//Increase messageCount
		messageCount += newsFeed.size();
		///Increase  positiveMessageCount by the number of positive messages in user's news feed
		for(int i=0; i<newsFeed.size(); i++) {
			boolean notPositive = true;
			String tweetMessage = newsFeed.get(i).getMessage().toLowerCase();
			//Check if any positive phrases are in tweetMessage
			for(int j=0; j<positivePhrases.length && notPositive; j++) {
				if(tweetMessage.contains(positivePhrases[j])) {
					notPositive = false;
					positiveMessageCount++;
				}
			}
		}
	}

	@Override
	public void visitGroup(Group user) {
		//Groups have no messages themselves - do nothing
	}

}
