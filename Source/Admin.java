//Ian Berger
//CS 356
//Assignment 2
public class Admin {
	private static Admin instance;
	private AdminFrame frame;
	private Group root;
	
	private Admin() {
		root = new Group("Root");
		try {
			frame = new AdminFrame(root);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Admin getInstance() {
		if(instance == null)
			instance = new Admin();
		return instance;
	}
	
}
