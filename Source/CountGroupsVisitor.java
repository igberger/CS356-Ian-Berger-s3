//Ian Berger
//CS 356
//Assignment 2
public class CountGroupsVisitor implements UserGroupVisitor {
	private int groupCount;
	
	public CountGroupsVisitor() {
		groupCount = 0;
	}
	
	public int getGroupCount() {
		return groupCount;
	}
	
	@Override
	public void visitUser(User user) {
		//Only counting groups - do nothing at User
	}

	@Override
	public void visitGroup(Group group) {
		groupCount++;
	}
	
}
