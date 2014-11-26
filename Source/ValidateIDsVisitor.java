import java.util.ArrayList;



public class ValidateIDsVisitor implements UserGroupVisitor {
	private ArrayList<String> userIds;
	private ArrayList<String> groupIds;
	private boolean valid;
	
	public ValidateIDsVisitor() {
		userIds = new ArrayList<String>();
		groupIds = new ArrayList<String>();
		valid = true;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	@Override
	public void visitUser(User user) {
		for(int i=0; i<userIds.size(); i++)
			if(user.toString().contains(" ") || userIds.get(i).toString().equals(user.toString()))
				valid = false;
		userIds.add(user.toString());
	}

	@Override
	public void visitGroup(Group group) {
		for(int i=0; i<groupIds.size(); i++)
			if(group.toString().contains(" ") || groupIds.get(i).toString().equals(group.toString()))
				valid = false;
		groupIds.add(group.toString());
	}

}
