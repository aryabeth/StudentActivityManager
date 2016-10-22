package rplproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Help extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5171969406938089923L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help frame = new Help();
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
	 * @throws IOException 
	 */
	public Help() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Arya\\workspace\\RPL-edit - Copy\\bin\\image\\introduction-icon.png"));
		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
		
		setResizable(false);
		setVisible(false);
		setBounds(700, 100, 415, 402);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(UIManager.getBorder("OptionPane.border"));
		setContentPane(contentPane);
		
		final String text1En = "Introducing"
				+ "\r\n==========="
				+ "\r\nStudent Activity Manager (SAM) is an application that help managing some task."
				+ " This application intended to ease scheduling student tasks.";
		final String text1In = "Perkenalan"
				+ "\r\n========="
				+ "\r\nStudent Activity Manager (SAM) adalah sebuah aplikasi yang membantu penjadwalan tugas."
				+ " Aplikasi ini ditujukan untuk mempermudah penjadwalan tugas-tugas pelajar/mahasiswa.";
		final String text2En = "Adding Task"
				+ "\r\n=========="
				+ "\r\nThere are 3 types of task that can be added :"
				+ "\r\n • Timed Task is type of task that's scheduled from start day until end day."
				+ "\r\n • Deadline Task is type of task that's need completely to finish at the end day."
				+ "\r\n • Floating Task is type of task that's not scheduled.";
		final String text2In = "Menambahkan Tugas"
				+ "\r\n================="
				+ "\r\nAda 3 tipe tugas yang dapat ditambahkan :"
				+ "\r\n • Timed Task adalah tipe tugas yang terjadwalkan dari hari mulai hingga hari akhir."
				+ "\r\n • Deadline Task adalah tipe tugas yang bersifat untuk diselesaikan pada hari pengumpulan."
				+ "\r\n • Floating Task adalah tipe tugas yang tidak ada penjadwalan hari.";
		final String text3En = "Look & edit task"
				+ "\r\n============="
				+ "\r\nBy pressing button \"Details\", you can look all information from one of the selected tasks."
				+ "\r\nNext, you can edit information of task by pressing button \"Edit\" and confirm changes by pressing button \"Update\".";
		final String text3In = "Melihat & merubah tugas"
				+ "\r\n===================="
				+ "\r\nDengan menekan tombol \"Details\", anda dapat melihat semua informasi dari salah satu tugas yang dipilih."
				+ "\r\nSelanjutnya, anda dapat mengubah informasi tugas dengan menekan tombol \"Edit\" dan mengkonfirmasi perubahan dengan tombol \"Update\".";
		final String text4En = "Logging"
				+ "\r\n======="
				+ "\r\nContains notes about the activity that has been carried out, such as adding tasks, delete tasks, change task information, etc.";
		final String text4In = "Pendataan"
				+ "\r\n========="
				+ "\r\nBerisi catatan tentang aktivitas yang telah dilakukan, seperti menambahkan tugas, menghapus tugas, mengubah informasi tugas, dll.";
		final String text5En = "Deleting & Clear Task"
				+ "\r\n=================="
				+ "\r\nbutton \"Delete\" will remove a task from the list that is displayed, you must select the tasks that will be deleted."
				+ "\r\nbutton \"Clear\" will remove all tasks in the list that is displayed.";
		final String text5In = "Penghapusan & Pengosongan daftar tugas"
				+ "\r\n=================================="
				+ "\r\ntombol \"Delete\" akan menghapus tugas dari daftar yang ditampilkan, anda harus memilih tugas yang akan dihapus."
				+ "\r\ntombol \"Clear\" akan mengosongkan semua tugas yang ada di daftar yang ditampilkan.";
		final String text6En = "Searching Task"
				+ "\r\n============="
				+ "\r\nYou can type the keywords in the search field, this application will instantly display the tasks related to keywords.";
		final String text6In = "Pencarian tugas"
				+ "\r\n============="
				+ "\r\nAnda dapat mengetikkan kata kunci di kotak pencarian, Aplikasi ini akan langsung menampilkan tugas yang berkaitan dengan kata kunci.";
		
		final JTextArea textHelp = new JTextArea();
		textHelp.setWrapStyleWord(true);
		textHelp.setLineWrap(true);
		textHelp.setEditable(false);
		textHelp.setBounds(10, 193, 389, 168);
		contentPane.add(textHelp);
		textHelp.setText(text1En); //set text awal Introducing english
		
		String[] languages = { "English", "Indonesian"};
		contentPane.setLayout(null);
		final JComboBox comboBox = new JComboBox(languages);
		
		comboBox.setBounds(285, 34, 114, 20);
		comboBox.setMaximumRowCount(2);
		contentPane.add(comboBox);
		
		JLabel lblLanguage = new JLabel("Language :");
		lblLanguage.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblLanguage.setHorizontalAlignment(SwingConstants.LEFT);
		lblLanguage.setBounds(213, 38, 62, 14);
		contentPane.add(lblLanguage);
		
				
		JButton btnIntroduction = new JButton();
		btnIntroduction.setHorizontalAlignment(SwingConstants.LEFT);
		btnIntroduction.setIcon(new ImageIcon(System
				.getProperty("user.dir")
				+ "\\bin\\"
				+ "\\image\\introduction-icon.png"));
		btnIntroduction.setText("Introduction");
		btnIntroduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("English")){
					textHelp.setText(text1En);
				}else{
					textHelp.setText(text1In);
				}
			}
		});
		btnIntroduction.setForeground(Color.BLACK);
		btnIntroduction.setBackground(Color.WHITE);

		btnIntroduction.setBounds(10, 65, 185, 32);
		contentPane.add(btnIntroduction);
		
		JButton btnAdding = new JButton();
		btnAdding.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdding.setIcon(new ImageIcon(System
				.getProperty("user.dir")
				+ "\\bin\\"
				+ "\\image\\adding-help.png"));
		btnAdding.setText("Adding Task");
		btnAdding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("English")){
					textHelp.setText(text2En);
				}
				else{
					textHelp.setText(text2In);
				}
			}
		});
		btnAdding.setForeground(Color.BLACK);
		btnAdding.setBackground(Color.WHITE);
		btnAdding.setBounds(10, 100, 185, 32);
		contentPane.add(btnAdding);
		
		JButton btnLook = new JButton();
		btnLook.setIcon(new ImageIcon(System
				.getProperty("user.dir")
				+ "\\bin\\"
				+ "\\image\\lookEdit-help.png"));
		btnLook.setHorizontalAlignment(SwingConstants.LEFT);
		btnLook.setText("Look & Edit Task");
		btnLook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("English")){
					textHelp.setText(text3En);
				}
				else{
					textHelp.setText(text3In);
				}
			}
		});
		btnLook.setForeground(Color.BLACK);
		btnLook.setBackground(Color.WHITE);
		btnLook.setBounds(10, 135, 185, 32);
		contentPane.add(btnLook);
		
		JButton btnLog = new JButton();
		btnLog.setIcon(new ImageIcon(System
				.getProperty("user.dir")
				+ "\\bin\\"
				+ "\\image\\logging-help.png"));
		btnLog.setHorizontalAlignment(SwingConstants.LEFT);
		btnLog.setText("Logging");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("English")){
					textHelp.setText(text4En);
				}
				else{
					textHelp.setText(text4In);
				}
			}
		});
		btnLog.setForeground(Color.BLACK);
		btnLog.setBackground(Color.WHITE);
		btnLog.setBounds(214, 66, 185, 32);
		contentPane.add(btnLog);
		
		JButton btnDel = new JButton();
		btnDel.setIcon(new ImageIcon(System
				.getProperty("user.dir")
				+ "\\bin\\"
				+ "\\image\\delete-help.png"));
		btnDel.setHorizontalAlignment(SwingConstants.LEFT);
		btnDel.setText("Deleting & Clear Task");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("English")){
					textHelp.setText(text5En);
				}
				else{
					textHelp.setText(text5In);
				}
			}
		});
		btnDel.setForeground(Color.BLACK);
		btnDel.setBackground(Color.WHITE);
		btnDel.setBounds(214, 101, 185, 32);
		contentPane.add(btnDel);
		
		JButton btnSearch = new JButton();
		btnSearch.setIcon(new ImageIcon(System
				.getProperty("user.dir")
				+ "\\bin\\"
				+ "\\image\\search-help.png"));
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.setText("Searching Task");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("English")){
					textHelp.setText(text6En);
				}
				else{
					textHelp.setText(text6In);
				}
			}
		});
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(214, 136, 185, 32);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(System
				.getProperty("user.dir")
				+ "\\bin\\"
				+ "\\image\\label-help.png"));
		lblNewLabel_1.setBounds(0, 0, 409, 54);
		contentPane.add(lblNewLabel_1);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//agar langsung pindah bahasa
				if (comboBox.getSelectedItem().equals("English")){
					textHelp.setText(text1En);
				}else{
					textHelp.setText(text1In);
				}
			}
		});
	}
}
