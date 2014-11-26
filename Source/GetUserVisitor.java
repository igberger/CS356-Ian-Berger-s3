//Ian Berger
//CS 356
//Assignment 2
public class GetUserVisitor implements UserGroupVisitor {
	private String id;
	private User user;
	private boolean hasUser;
	
	public GetUserVisitor(String id) {
		this.id = id;
		user = null;
		hasUser = false;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean hasUser() {
		return hasUser;
	}
	
	@Override
	public void visitUser(User user) {
		if(user.toString().equals(id)) {
			this.user = user;
			hasUser = true;
		}
	}

	@Override
	public void visitGroup(Group group) {
		//Checking for user not group - do nothing
	}

}
