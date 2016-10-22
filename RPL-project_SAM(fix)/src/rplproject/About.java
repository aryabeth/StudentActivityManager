package rplproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class About extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public About() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
		
		setBackground(Color.ORANGE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				System.getProperty("user.dir") + "\\bin\\"
						+ "image\\iconSAM.png"));
		setResizable(false);
		setBounds(100, 100, 346, 198);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ "\\bin\\" + "image\\iconSAM.png"));
		lblNewLabel.setBounds(10, 0, 48, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblStudentActivityManager = new JLabel("STUDENT ACTIVITY MANAGER");
		lblStudentActivityManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentActivityManager.setFont(new Font("Arial", Font.BOLD, 16));
		lblStudentActivityManager.setBounds(68, 11, 240, 25);
		contentPane.add(lblStudentActivityManager);
		
		JLabel lblNewLabel_1 = new JLabel("Version : 1.0 ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(68, 31, 240, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("This software created by The Sentinel");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(10, 70, 298, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("71120060 - I Made Arya Beta Widyatmika");
		lblNewLabel_3.setBounds(10, 110, 284, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblAndreas = new JLabel("71120054 - Andreas Isnawan");
		lblAndreas.setBounds(10, 95, 284, 14);
		contentPane.add(lblAndreas);
		
		JLabel lblMichael = new JLabel("71120067 - Michael Abadi Santoso");
		lblMichael.setBounds(10, 125, 284, 14);
		contentPane.add(lblMichael);
	}
}
