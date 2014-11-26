import java.util.ArrayList;

//Ian Berger
//CS 356
//Assignment 2

//Composite class to User
public class Group implements UserGroupComponent{
	private long creationTime;
	private String id;
	private ArrayList<UserGroupComponent> components;
	
	public Group(String id) {
		creationTime = System.currentTimeMillis();
		this.id = id;
		components = new ArrayList<UserGroupComponent>();
	}
	
	public long getCreationTime() {
		return creationTime;
	}
	
	public String toString() {
		return id;
	}
	
	@Override
	public void accept(UserGroupVisitor visitor) {
		//Send visitor to this Group and then to each of its components
		visitor.visitGroup(this);
		for(int i=0; i<components.size(); i++) {
			components.get(i).accept(visitor);
		}
	}
	
	public void add(UserGroupComponent component) {
		components.add(component);
	}
}
