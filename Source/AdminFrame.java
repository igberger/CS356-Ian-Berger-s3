import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

//Ian Berger
//CS 356
//Assignment 2
public class AdminFrame extends JFrame implements TreeSelectionListener {

	private Group root;
	private JPanel contentPane;
	private JTree tree;
	private DefaultMutableTreeNode rootNode;
	private DefaultMutableTreeNode selectedNode;
	private JTextArea txtrUserId;
	private JTextArea txtrGroupId;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public AdminFrame(Group r) {
		root = r;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 300, 558);
		contentPane.add(scrollPane);
		//Encapsulate root into rootNode and pass rootNode as the root of tree
		rootNode = new DefaultMutableTreeNode(root, true);
		tree = new JTree(rootNode, true);
		tree.addTreeSelectionListener(this);//Get notified of user selection of one of tree's nodes
		scrollPane.setViewportView(tree);
		//Add user with user id in txtrUserId to group at the currently selected node when btnAddUser is clicked
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Check that a group is selected to add the user to
				if(selectedNode != null && selectedNode.getUserObject() instanceof Group) {
					//Check that a user with the given id does not already exist
					GetUserVisitor v = new GetUserVisitor(txtrUserId.getText());
					root.accept(v);
					if(!v.hasUser()) {
						addUserNode((Group)selectedNode.getUserObject(), new User(txtrUserId.getText()));
					} else {
						JOptionPane.showMessageDialog(null, "Enter a unique user ID");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select a group to add this user to");
				}
			}
		});
		btnAddUser.setBounds(777, 10, 117, 29);
		contentPane.add(btnAddUser);
		//Add group with group id in txtrGroupId to group at the currently selected node when btnAddGroup is clicked
		JButton btnAddGroup = new JButton("Add Group");
		btnAddGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Check that a group is selected to add the user to
				if(selectedNode != null && selectedNode.getUserObject() instanceof Group) {
					//Check that a group with the given id does not already exist
					GetGroupVisitor v = new GetGroupVisitor(txtrGroupId.getText());
					root.accept(v);
					if(!v.hasGroup()) {
						addGroupNode((Group)selectedNode.getUserObject(), new Group(txtrGroupId.getText()));
					} else {
						JOptionPane.showMessageDialog(null, "Enter a unique group ID");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select a group to add this group to");
				}
			}
		});
		btnAddGroup.setBounds(777, 50, 117, 29);
		contentPane.add(btnAddGroup);
		//Open user view of user at the currently selected node when btnOpenUserView is clicked 
		JButton btnOpenUserView = new JButton("Open User View");
		btnOpenUserView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(selectedNode != null && selectedNode.getUserObject() instanceof User) {
					try {
						UserFrame frame = new UserFrame((User)selectedNode.getUserObject(), root);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select a user to open their view");
				}
			}
		});
		btnOpenUserView.setBounds(322, 90, 572, 56);
		contentPane.add(btnOpenUserView);
		//Open pop-up window with positive message percentage when btnShowPositivePercentage is clicked 
		JButton btnShowPositivePercentage = new JButton("Show Positive Percentage");
		btnShowPositivePercentage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CountPositiveMessagesVisitor v = new CountPositiveMessagesVisitor();
				root.accept(v);
				JOptionPane.showMessageDialog(null, v.getPositiveMessagePercentage() + "% of messages are positive");
			}
		});
		btnShowPositivePercentage.setBounds(618, 539, 276, 29);
		contentPane.add(btnShowPositivePercentage);
		//Open pop-up window with message total when btnShowMessagesTotal is clicked 
		JButton btnShowMessagesTotal = new JButton("Show Messages Total");
		btnShowMessagesTotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CountMessagesVisitor v = new CountMessagesVisitor();
				root.accept(v);
				JOptionPane.showMessageDialog(null, "Message total is " + v.getMessageCount());
			}
		});
		btnShowMessagesTotal.setBounds(322, 539, 276, 29);
		contentPane.add(btnShowMessagesTotal);
		//Open pop-up window with user total when btnShowUserTotal is clicked 
		JButton btnShowUserTotal = new JButton("Show User Total");
		btnShowUserTotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CountUsersVisitor v = new CountUsersVisitor();
				root.accept(v);
				JOptionPane.showMessageDialog(null, "User total is " + v.getUserCount());
			}
		});
		btnShowUserTotal.setBounds(322, 498, 276, 29);
		contentPane.add(btnShowUserTotal);
		//Open pop-up window with group total when btnShowGroupTotal is clicked  
		JButton btnShowGroupTotal = new JButton("Show Group Total");
		btnShowGroupTotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CountGroupsVisitor v = new CountGroupsVisitor();
				root.accept(v);
				JOptionPane.showMessageDialog(null, "Group total is " + v.getGroupCount());
			}
		});
		btnShowGroupTotal.setBounds(618, 498, 276, 29);
		contentPane.add(btnShowGroupTotal);
		
		txtrUserId = new JTextArea();
		txtrUserId.setText("User ID");
		txtrUserId.setBounds(322, 10, 443, 29);
		contentPane.add(txtrUserId);
		
		txtrGroupId = new JTextArea();
		txtrGroupId.setText("Group ID");
		txtrGroupId.setBounds(322, 50, 443, 29);
		contentPane.add(txtrGroupId);
		
		JButton btnShowLastUpdatedUser = new JButton("Show Last Updated User");
		btnShowLastUpdatedUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GetLastUpdatedUserVisitor v = new GetLastUpdatedUserVisitor();
				root.accept(v);
				JOptionPane.showMessageDialog(null, "LastUpdatedUser is " + v.getLastUpdatedUser());
			}
		});
		btnShowLastUpdatedUser.setBounds(322, 254, 572, 29);
		contentPane.add(btnShowLastUpdatedUser);
		
		JButton btnValidateIds = new JButton("Validate ID's");
		btnValidateIds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ValidateIDsVisitor v = new ValidateIDsVisitor();
				root.accept(v);
				if(v.isValid())
					JOptionPane.showMessageDialog(null, "All ID's are valid");
				else
					JOptionPane.showMessageDialog(null, "All ID's are not valid");
			}
		});
		btnValidateIds.setBounds(322, 213, 572, 29);
		contentPane.add(btnValidateIds);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		selectedNode = node;
	}
	
	private void addUserNode(Group group, User user) {
		group.add(user);
		selectedNode.add(new DefaultMutableTreeNode(user, false));
		tree.updateUI();
	}
	
	private void addGroupNode(Group superGroup, Group subGroup) {
		superGroup.add(subGroup);
		selectedNode.add(new DefaultMutableTreeNode(subGroup, true));
		tree.updateUI();
	}
}
