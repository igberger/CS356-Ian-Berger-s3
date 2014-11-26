//Ian Berger
//CS 356
//Assignment 2
public class GetGroupVisitor implements UserGroupVisitor {
	private String id;
	private Group group;
	private boolean hasGroup;
	
	public GetGroupVisitor(String id) {
		this.id = id;
		group = null;
		hasGroup = false;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public boolean hasGroup() {
		return hasGroup;
	}
	
	@Override
	public void visitUser(User user) {
		//Checking for group not user - do nothing
	}

	@Override
	public void visitGroup(Group group) {
		if(group.toString().equals(id)) {
			this.group = group;
			hasGroup = true;
		}
	}

}
