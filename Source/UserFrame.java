import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

//Ian Berger
//CS 356
//Assignment 2
public class UserFrame extends JFrame implements Observer {

	private User user;
	private Group root;
	private JPanel contentPane;
	private JTextArea txtrUserId;
	private JTextArea txtrTweetMessage;
	private JList listFollowing;
	private JList listNewsFeed;
	private JLabel lblLastUpdateTime;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
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
	public UserFrame(User u, Group r) {
		user = u;
		root = r;
		//Get notified whenever a new tweet appears in user's news feed
		user.getNewsFeed().addObserver(this);	
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Follow user with user id in txtrUserId when btnFollowUser is clicked 
		JButton btnFollowUser = new JButton("Follow User");
		btnFollowUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GetUserVisitor v = new GetUserVisitor(txtrUserId.getText());
				root.accept(v);
				if(v.hasUser()) {
					user.follow(v.getUser());
					listFollowing.updateUI();
				} else {
					JOptionPane.showMessageDialog(null, "Enter an existing user ID");
				}
			}
		});
		btnFollowUser.setBounds(327, 6, 117, 29);
		contentPane.add(btnFollowUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 40, 438, 66);
		contentPane.add(scrollPane);
		
		listFollowing = new JList(user.getCurrentlyFollowingListModel());
		scrollPane.setViewportView(listFollowing);
		
		JTextArea txtrCurrentlyFollowing = new JTextArea();
		txtrCurrentlyFollowing.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		txtrCurrentlyFollowing.setText("Currently Following");
		scrollPane.setColumnHeaderView(txtrCurrentlyFollowing);
		//Tweet message in txtrTweetMessage when btnPostTweet is clicked 
		JButton btnPostTweet = new JButton("Post Tweet");
		btnPostTweet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				user.tweet(txtrTweetMessage.getText());
			}
		});
		btnPostTweet.setBounds(327, 118, 117, 29);
		contentPane.add(btnPostTweet);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 152, 434, 116);
		contentPane.add(scrollPane_1);
		
		listNewsFeed = new JList(user.getNewsFeed().getListModel());
		scrollPane_1.setViewportView(listNewsFeed);
		
		JTextArea txtrNewsFeed = new JTextArea();
		txtrNewsFeed.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		txtrNewsFeed.setText("News Feed");
		scrollPane_1.setColumnHeaderView(txtrNewsFeed);
		
		txtrUserId = new JTextArea();
		txtrUserId.setText("User ID");
		txtrUserId.setBounds(6, 6, 309, 29);
		contentPane.add(txtrUserId);
		
		txtrTweetMessage = new JTextArea();
		txtrTweetMessage.setText("Tweet Message");
		txtrTweetMessage.setBounds(6, 118, 309, 29);
		contentPane.add(txtrTweetMessage);
		
		JLabel lblCreationTimePrompt = new JLabel("Creation Time: ");
		lblCreationTimePrompt.setBounds(6, 280, 96, 16);
		contentPane.add(lblCreationTimePrompt);
		
		JLabel lblLastUpdateTimePrompt = new JLabel("Last Update TIme:");
		lblLastUpdateTimePrompt.setBounds(6, 306, 117, 16);
		contentPane.add(lblLastUpdateTimePrompt);
		
		lblLastUpdateTime = new JLabel(((Long)user.getLastUpdateTime()).toString());
		lblLastUpdateTime.setBounds(135, 306, 309, 16);
		contentPane.add(lblLastUpdateTime);
		
		JLabel lblCreationTime = new JLabel(((Long)user.getCreationTime()).toString());
		lblCreationTime.setBounds(135, 280, 309, 16);
		contentPane.add(lblCreationTime);
	}

	@Override
	public void update(Observable o, Object arg) {
		//update listNewsFeed and lbLastUpdateTime whenever user's newsFeed is changed
		lblLastUpdateTime.setText(((Long)user.getLastUpdateTime()).toString());
		lblLastUpdateTime.updateUI();
		listNewsFeed.updateUI();
	}
}
