//Ian Berger
//CS 356
//Assignment 2
public class CountUsersVisitor implements UserGroupVisitor{
	private int userCount;
	
	public CountUsersVisitor() {
		userCount = 0;
	}
	
	public int getUserCount() {
		return userCount;
	}
	
	@Override
	public void visitUser(User user) {
		userCount++;
	}
	
	@Override
	public void visitGroup(Group group) {
		//Only counting users - do nothing at Group
	}
}
