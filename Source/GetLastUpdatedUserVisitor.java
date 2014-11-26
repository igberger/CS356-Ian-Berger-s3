
public class GetLastUpdatedUserVisitor implements UserGroupVisitor {
	private User lastUpdatedUser;
	
	public GetLastUpdatedUserVisitor() {
		lastUpdatedUser = null;
	}
	
	public User getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	
	@Override
	public void visitUser(User user) {
		if(lastUpdatedUser == null || lastUpdatedUser.getLastUpdateTime()<user.getLastUpdateTime()) {
			lastUpdatedUser = user;
		}
	}

	@Override
	public void visitGroup(Group group) {
		//Looking for last updated user - do nothing
	}

}
