package rplproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.TextArea;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

public class Main extends JFrame {

	private JMenuItem mntmUndo;
	private JMenuItem mntmRedo;

	private void cekUndo() {
		if (Database.stak.isEmpty()) {
			mntmUndo.setEnabled(false);
		} else {
			mntmUndo.setEnabled(true);
		}
	}

	private void cekRedo() {
		if (Database.stakre.isEmpty()) {
			mntmRedo.setEnabled(false);
		} else {
			mntmRedo.setEnabled(true);
		}
	}

	private JPanel contentPane;
	private static TableTask tableT = new TableTask();
	private static TableLog tableL = new TableLog();
	private JTable table_2;
	private JTextField textTitle;
	private JTextField textStart;
	private JTextField textEnd;
	private JTextField textTitled;
	private JTextField textStartd;
	private JTextField textEndd;
	private JTextField txtSearch;
	private About tentang = new About();
	private Help tolong = new Help();
	private Notif notif = new Notif();
	private TableRowSorter<TableTask> sorter1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public Main() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/image/iconSAM.png")));

		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
		// =======================================================================
		setTitle("Student Activity Manager V.1.0");
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 500);
		getContentPane().setLayout(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.awt.Toolkit.getDefaultToolkit().beep();
				int result = JOptionPane.showConfirmDialog(null,
						"Are you sure want to exit ?");
				if (result == JOptionPane.YES_OPTION) {
					try {
					
						/*File f = new File("src/sound/Windows XP Shutdown.wav");
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(f);
						AudioFormat format;
						format = audio.getFormat();
						SourceDataLine auline;
						DataLine.Info info = new DataLine.Info(
								SourceDataLine.class, format);
						auline = (SourceDataLine) AudioSystem.getLine(info);
						auline.open(format);
						auline.start();
						int nBytesRead = 0;
						byte[] abData = new byte[524288];
						while (nBytesRead != -1) {
							nBytesRead = audio.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								auline.write(abData, 0, nBytesRead);
							}
						}*/
						System.exit(0);
					} catch (Exception E) {
						System.out.println(E.getMessage());
					}
				} else {
				}
			}
		});

		JMenuItem mntmDetails = new JMenuItem("Details");
		mntmDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db = new Database();
				int temp = db.countd();
				String temp1 = Integer.toString(temp);
				String message = "You have " + temp1 + " Task in this Manager";
				java.awt.Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, message);
			}
		});
		mnFile.add(mntmDetails);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		mntmUndo = new JMenuItem("Undo");
		mntmUndo.setEnabled(false);
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db = new Database();
				db.undo();
				tableT.refresh();
				cekUndo();
				cekRedo();
			}
		});
		mnEdit.add(mntmUndo);

		mntmRedo = new JMenuItem("Redo");
		mntmRedo.setEnabled(false);
		mntmRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db = new Database();
				db.redo();
				tableT.refresh();
				cekRedo();
			}
		});
		mnEdit.add(mntmRedo);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About SAM");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Toolkit.getDefaultToolkit().beep();
				tentang.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);

		JMenuItem mntmHelp = new JMenuItem("Help Content");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Toolkit.getDefaultToolkit().beep();
				tolong.setVisible(true);
			}
		});
		mnHelp.add(mntmHelp);

		JMenuItem mntmGetUpdate = new JMenuItem("Check for Update");
		mntmGetUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"Unable to connect to the Internet ");
			}
		});
		mnHelp.add(mntmGetUpdate);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JPanel panel = new JPanel(); // ini panel utama
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 685, 450);
		contentPane.add(panel);

		// ===================================panel details
		final JPanel panelDetails = new JPanel();
		panelDetails.setBackground(SystemColor.white);
		panelDetails.setBounds(0, 0, 685, 450);
		panelDetails.setLayout(null);

		JLabel lblTitle = new JLabel("Task Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitle.setBounds(22, 93, 65, 27);
		panelDetails.add(lblTitle);

		textTitled = new JTextField();
		textTitled.setBackground(SystemColor.control);
		textTitled.setEditable(false);
		textTitled.setBounds(22, 120, 392, 33);
		panelDetails.add(textTitled);
		textTitled.setColumns(10);

		final JLabel lblStartDate = new JLabel("Start Time");
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStartDate.setBounds(22, 195, 65, 27);
		panelDetails.add(lblStartDate);

		textStartd = new DateTextField();
		textStartd.setBackground(SystemColor.control);
		textStartd.setEditable(false);
		textStartd.setBounds(118, 195, 207, 28);
		panelDetails.add(textStartd);
		textStartd.setColumns(10);

		final JLabel lblEndTime_1 = new JLabel("End Time");
		lblEndTime_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEndTime_1.setBounds(22, 233, 65, 27);
		panelDetails.add(lblEndTime_1);

		textEndd = new DateTextField();
		textEndd.setBackground(SystemColor.control);
		textEndd.setEditable(false);
		textEndd.setBounds(118, 233, 207, 28);
		panelDetails.add(textEndd);
		textEndd.setColumns(10);

		JLabel lblDekripsid = new JLabel("Description");
		lblDekripsid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDekripsid.setBounds(22, 273, 75, 27);
		panelDetails.add(lblDekripsid);

		String[] typed = { "Timed Task", "Deadline Task", "Floating Task" };
		final JComboBox comboBox1 = new JComboBox(typed);
		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBox1.getSelectedItem().equals("Timed Task")) {// semua
					lblStartDate.setVisible(true);
					lblEndTime_1.setVisible(true);
					textStartd.setVisible(true);
					textEndd.setVisible(true);
				} else if (comboBox1.getSelectedItem().equals("Deadline Task")) {// end
					lblStartDate.setVisible(false);
					textStartd.setVisible(false);
					textStartd.setText("");
					lblEndTime_1.setVisible(true);
					textEndd.setVisible(true);
				} else if (comboBox1.getSelectedItem().equals("Floating Task")) {// kosong
					lblStartDate.setVisible(false);
					textStartd.setText("");
					textStartd.setVisible(false);
					lblEndTime_1.setVisible(false);
					textEndd.setText("");
					textEndd.setVisible(false);
				}
			}
		});

		final JTextArea textDeskd = new JTextArea();
		textDeskd.setBackground(SystemColor.control);
		textDeskd.setForeground(Color.BLACK);
		textDeskd.setEditable(false);
		textDeskd.setBounds(119, 274, 296, 102);
		panelDetails.add(textDeskd);

		comboBox1.setBounds(118, 159, 115, 27);
		panelDetails.add(comboBox1);

		final JButton btnEdit = new JButton("");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnEdit.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnEdit.setBackground(new Color(255, 140, 0));
			}
		});
		btnEdit.setBackground(new Color(255, 140, 0));
		btnEdit.setIcon(new ImageIcon(Main.class.getResource("/image/edit-button.png")));

		final JButton btnBack_1 = new JButton("");
		btnBack_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnBack_1.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnBack_1.setBackground(new Color(255, 140, 0));
			}
		});
		btnBack_1.setBackground(new Color(255, 140, 0));
		btnBack_1.setIcon(new ImageIcon(Main.class.getResource("/image/home-button.png")));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					/*File f = new File("src/sound/Simple_Low.wav");
					AudioInputStream audio = AudioSystem.getAudioInputStream(f);
					AudioFormat format;
					format = audio.getFormat();
					SourceDataLine auline;
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					auline = (SourceDataLine) AudioSystem.getLine(info);
					auline.open(format);
					auline.start();
					int nBytesRead = 0;
					byte[] abData = new byte[524288];
					while (nBytesRead != -1) {
						nBytesRead = audio.read(abData, 0, abData.length);
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					}*/
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				btnBack_1.setBackground(new Color(255, 140, 0));
				contentPane.removeAll();
				contentPane.add(panel);
				repaint();
				printAll(getGraphics());
			}
		});
		btnBack_1.setBounds(300, 399, 64, 40);
		panelDetails.add(btnBack_1);

		// ===============================================panel log
		final JPanel panellog = new JPanel();
		panellog.setBackground(SystemColor.white);
		panellog.setBounds(0, 0, 685, 450);
		getContentPane().add(panellog);
		panellog.setLayout(null);

		final JTable table_1 = new JTable(tableL);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableL);
		table_1.setRowSorter(sorter);
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(10, 122, 665, 264);
		panellog.add(scrollPane_1);
		scrollPane_1.setViewportView(table_1);

		final JButton btnBack_2 = new JButton("");
		btnBack_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnBack_2.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnBack_2.setBackground(new Color(255, 140, 0));
			}
		});
		btnBack_2.setBackground(new Color(255, 140, 0));
		btnBack_2
				.setIcon(new ImageIcon(Main.class.getResource("/image/home-button.png")));
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					/*File f = new File("src/sound/Simple_Low.wav");
					AudioInputStream audio = AudioSystem.getAudioInputStream(f);
					AudioFormat format;
					format = audio.getFormat();
					SourceDataLine auline;
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					auline = (SourceDataLine) AudioSystem.getLine(info);
					auline.open(format);
					auline.start();
					int nBytesRead = 0;
					byte[] abData = new byte[524288];
					while (nBytesRead != -1) {
						nBytesRead = audio.read(abData, 0, abData.length);
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					}*/
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				btnBack_2.setBackground(new Color(255, 140, 0));
				contentPane.removeAll();
				contentPane.add(panel);
				repaint();
				printAll(getGraphics());
			}
		});
		btnBack_2.setBounds(345, 404, 89, 40);
		panellog.add(btnBack_2);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Main.class.getResource("/image/label-log.png")));
		label.setBounds(461, 40, 214, 75);
		panellog.add(label);

		JLabel lblLogIsi = new JLabel("LOG ( ISI LOGO)");
		lblLogIsi.setIcon(new ImageIcon(Main.class.getResource("/image/main.png")));
		lblLogIsi.setBounds(0, 0, 685, 75);
		panellog.add(lblLogIsi);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 397, 665, 2);
		panellog.add(separator_3);

		final JButton btnClear = new JButton("");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnClear.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnClear.setBackground(new Color(255, 140, 0));
			}
		});
		btnClear.setBackground(new Color(255, 140, 0));
		btnClear.setIcon(new ImageIcon(Main.class.getResource("/image/clear-button.png")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnClear.setBackground(new Color(255, 140, 0));
				java.awt.Toolkit.getDefaultToolkit().beep();
				int result = JOptionPane.showConfirmDialog(null,
						"Are you sure want to clear log list ?");
				if (result == JOptionPane.YES_OPTION) {
					Database db = new Database();
					db.clear();
					tableL.refreshl();
					try {/*
						File f = new File("src/sound/CetiAlpha.wav");
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(f);
						AudioFormat format;
						format = audio.getFormat();
						SourceDataLine auline;
						DataLine.Info info = new DataLine.Info(
								SourceDataLine.class, format);
						auline = (SourceDataLine) AudioSystem.getLine(info);
						auline.open(format);
						auline.start();
						int nBytesRead = 0;
						byte[] abData = new byte[524288];
						while (nBytesRead != -1) {
							nBytesRead = audio.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								auline.write(abData, 0, nBytesRead);
							}
						}*/
						JOptionPane.showMessageDialog(null,
								"Data Log berhasil dihapus");
					} catch (Exception E) {
						System.out.println(E.getMessage());
					}

				} else {
				}
			}
		});
		btnClear.setBounds(252, 404, 89, 40);
		panellog.add(btnClear);

		final JLabel lblTimer_1 = new JLabel("Timer");
		lblTimer_1.setFont(new Font("Digital-7", Font.BOLD, 25));
		lblTimer_1.setBounds(10, 80, 105, 25);
		panellog.add(lblTimer_1);

		final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String time = timeFormat.format(date);
				lblTimer_1.setText(time);
			}
		};
		Timer timer = new Timer(1000, timerListener);
		// to make sure it doesn't wait one second at the start
		timer.setInitialDelay(0);
		timer.start();

		JTable table = new JTable(new TableLog());
		scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(284, 371, 80, 33);
		contentPane.add(scrollPane_1);

		// ===============================================panel add
		final JPanel panel2 = new JPanel();
		panel2.setBackground(SystemColor.white);
		panel2.setBounds(0, 0, 685, 450);
		panel2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Task Title *");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(21, 100, 105, 27);
		panel2.add(lblNewLabel);

		textTitle = new JTextField();
		textTitle.setBackground(SystemColor.control);
		textTitle.setBounds(21, 127, 392, 27);
		panel2.add(textTitle);
		textTitle.setColumns(10);

		final JLabel lblStart = new JLabel("Start Time*");
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStart.setBounds(21, 202, 75, 27);
		panel2.add(lblStart);

		final JLabel lblEndTime = new JLabel("End Time*");
		lblEndTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEndTime.setBounds(21, 240, 75, 27);
		panel2.add(lblEndTime);

		new DateTextField("yyyy-MM-dd", new Date());
		textStart = new DateTextField();
		textStart.setEditable(true);
		textStart.setBackground(SystemColor.control);
		textStart.setBounds(117, 203, 115, 27);
		panel2.add(textStart);
		textStart.setColumns(10);

		textEnd = new DateTextField();
		textEnd.setEditable(true);
		textEnd.setBackground(SystemColor.control);
		textEnd.setBounds(117, 241, 115, 27);
		panel2.add(textEnd);
		textEnd.setColumns(10);

		JLabel lblDeskripsi = new JLabel("Description");
		lblDeskripsi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeskripsi.setBounds(21, 279, 75, 27);
		panel2.add(lblDeskripsi);

		final JTextArea textPane = new JTextArea();
		textPane.setForeground(Color.BLACK);
		textPane.setBackground(SystemColor.control);
		textPane.setBounds(118, 280, 296, 102);
		panel2.add(textPane);

		String[] type = { "Timed Task", "Deadline Task", "Floating Task" };
		final JComboBox comboBox = new JComboBox(type);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBox.getSelectedItem().equals("Timed Task")) {// semua
					lblStart.setVisible(true);
					lblEndTime.setVisible(true);
					textStart.setVisible(true);
					textEnd.setVisible(true);
				} else if (comboBox.getSelectedItem().equals("Deadline Task")) {// end
					lblStart.setVisible(false);
					textStart.setVisible(false);
					textStart.setText("");
					lblEndTime.setVisible(true);
					textEnd.setVisible(true);
				} else if (comboBox.getSelectedItem().equals("Floating Task")) {// kosong
					lblStart.setVisible(false);
					textStart.setText("");
					textStart.setVisible(false);
					lblEndTime.setVisible(false);
					textEnd.setText("");
					textEnd.setVisible(false);
				}
			}
		});
		comboBox.setBounds(117, 165, 115, 27);
		panel2.add(comboBox);

		final JButton btnS = new JButton("");
		btnS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnS.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnS.setBackground(new Color(255, 140, 0));
			}
		});
		btnS.setBackground(new Color(255, 140, 0));
		btnS.setIcon(new ImageIcon(Main.class.getResource("/image/done-button.png")));
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnS.setBackground(new Color(255, 140, 0));
				Database db = new Database();
				if (textTitle.getText().isEmpty()) {
					try {/*
						File f = new File("src/sound/Windows Error.wav");
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(f);
						AudioFormat format;
						format = audio.getFormat();
						SourceDataLine auline;
						DataLine.Info info = new DataLine.Info(
								SourceDataLine.class, format);
						auline = (SourceDataLine) AudioSystem.getLine(info);
						auline.open(format);
						auline.start();
						int nBytesRead = 0;
						byte[] abData = new byte[524288];
						while (nBytesRead != -1) {
							nBytesRead = audio.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								auline.write(abData, 0, nBytesRead);
							}
						}*/
						JOptionPane.showMessageDialog(null,
								"Task Title wajib diisi !!");
					} catch (Exception E) {
						System.out.println(E.getMessage());

					}

				} else if (comboBox.getSelectedItem().equals("Timed Task")) {
					String[] tanggalend = (textEnd.getText().split("-"));
					String[] tanggalstart = (textStart.getText().split("-"));

					int tahun = Integer.parseInt(tanggalstart[0]);
					int bulan = Integer.parseInt(tanggalstart[1]);
					int hari = Integer.parseInt(tanggalstart[2]);
					@SuppressWarnings("deprecation")
					Date selesai = new Date(
							Integer.parseInt(tanggalend[0]) - 1900, Integer
									.parseInt(tanggalend[1]) - 1, Integer
									.parseInt(tanggalend[2]));

					@SuppressWarnings("deprecation")
					Date mulai = new Date(
							Integer.parseInt(tanggalstart[0]) - 1900, Integer
									.parseInt(tanggalstart[1]) - 1, Integer
									.parseInt(tanggalstart[2]));
					Date sekarang = new Date(System.currentTimeMillis());

					@SuppressWarnings("deprecation")
					int tahuns = sekarang.getYear() + 1900;
					@SuppressWarnings("deprecation")
					int bulans = sekarang.getMonth() + 1;
					@SuppressWarnings("deprecation")
					int haris = sekarang.getDate();

					if (selesai.compareTo(mulai) <= 0) {
						try {/*
							File f = new File("src/sound/Windows Error.wav");
							AudioInputStream audio = AudioSystem
									.getAudioInputStream(f);
							AudioFormat format;
							format = audio.getFormat();
							SourceDataLine auline;
							DataLine.Info info = new DataLine.Info(
									SourceDataLine.class, format);
							auline = (SourceDataLine) AudioSystem.getLine(info);
							auline.open(format);
							auline.start();
							int nBytesRead = 0;
							byte[] abData = new byte[524288];
							while (nBytesRead != -1) {
								nBytesRead = audio.read(abData, 0,
										abData.length);
								if (nBytesRead >= 0) {
									auline.write(abData, 0, nBytesRead);
								}
							}*/
							JOptionPane.showMessageDialog(null,
									"End date must be after start date!");
						} catch (Exception E) {
							System.out.println(E.getMessage());
						}
					} else if (tahuns - tahun >= 0 && bulans - bulan >= 0
							&& haris - hari > 0) {
						try {/*
							File f = new File("src/sound/Windows Error.wav");
							AudioInputStream audio = AudioSystem
									.getAudioInputStream(f);
							AudioFormat format;
							format = audio.getFormat();
							SourceDataLine auline;
							DataLine.Info info = new DataLine.Info(
									SourceDataLine.class, format);
							auline = (SourceDataLine) AudioSystem.getLine(info);
							auline.open(format);
							auline.start();
							int nBytesRead = 0;
							byte[] abData = new byte[524288];
							while (nBytesRead != -1) {
								nBytesRead = audio.read(abData, 0,
										abData.length);
								if (nBytesRead >= 0) {
									auline.write(abData, 0, nBytesRead);
								}
							}*/
							JOptionPane.showMessageDialog(null,
									"Start date can not before today!");
						} catch (Exception E) {
							System.out.println(E.getMessage());
						}
					} else {
						try {
							db.insertTaks(textTitle.getText(),
									textStart.getText(), textEnd.getText(),
									textPane.getText(), "Timed Task");
							db.insertLog(textTitle.getText(), "ADD");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {/*
							File f = new File("src/sound/tada.wav");
							AudioInputStream audio = AudioSystem
									.getAudioInputStream(f);
							AudioFormat format;
							format = audio.getFormat();
							SourceDataLine auline;
							DataLine.Info info = new DataLine.Info(
									SourceDataLine.class, format);
							auline = (SourceDataLine) AudioSystem.getLine(info);
							auline.open(format);
							auline.start();
							int nBytesRead = 0;
							byte[] abData = new byte[524288];
							while (nBytesRead != -1) {
								nBytesRead = audio.read(abData, 0,
										abData.length);
								if (nBytesRead >= 0) {
									auline.write(abData, 0, nBytesRead);
								}
							}*/
							JOptionPane.showMessageDialog(null,
									"Task has been added");
						} catch (Exception E) {
							System.out.println(E.getMessage());
						}

						tableT.refresh();
						contentPane.removeAll();
						contentPane.add(panel);
						repaint();
						printAll(getGraphics());
						tableL.refreshl();
						cekUndo();
					}
				} else if (comboBox.getSelectedItem().equals("Deadline Task")) {
					if (textEnd.getText().isEmpty()) {
						try {/*
							File f = new File("src/sound/Windows Error.wav");
							AudioInputStream audio = AudioSystem
									.getAudioInputStream(f);
							AudioFormat format;
							format = audio.getFormat();
							SourceDataLine auline;
							DataLine.Info info = new DataLine.Info(
									SourceDataLine.class, format);
							auline = (SourceDataLine) AudioSystem.getLine(info);
							auline.open(format);
							auline.start();
							int nBytesRead = 0;
							byte[] abData = new byte[524288];
							while (nBytesRead != -1) {
								nBytesRead = audio.read(abData, 0,
										abData.length);
								if (nBytesRead >= 0) {
									auline.write(abData, 0, nBytesRead);
								}
							}*/
							JOptionPane.showMessageDialog(null,
									"End Time Wajib diisi!!");
						} catch (Exception E) {
							System.out.println(E.getMessage());
						}
					} else {
						try {
							db.insertTaks(textTitle.getText(),
									textStart.getText(), textEnd.getText(),
									textPane.getText(), "Deadline Task");
							db.insertLog(textTitle.getText(), "ADD");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {/*
							File f = new File("src/sound/tada.wav");
							AudioInputStream audio = AudioSystem
									.getAudioInputStream(f);
							AudioFormat format;
							format = audio.getFormat();
							SourceDataLine auline;
							DataLine.Info info = new DataLine.Info(
									SourceDataLine.class, format);
							auline = (SourceDataLine) AudioSystem.getLine(info);
							auline.open(format);
							auline.start();
							int nBytesRead = 0;
							byte[] abData = new byte[524288];
							while (nBytesRead != -1) {
								nBytesRead = audio.read(abData, 0,
										abData.length);
								if (nBytesRead >= 0) {
									auline.write(abData, 0, nBytesRead);
								}
							}*/
							JOptionPane.showMessageDialog(null,
									"Task has been added");

						} catch (Exception E) {
							System.out.println(E.getMessage());
						}
						tableT.refresh();
						contentPane.removeAll();
						contentPane.add(panel);
						repaint();
						printAll(getGraphics());
						tableL.refreshl();
						cekUndo();
					}
				} else {
					try {
						db.insertTaks(textTitle.getText(), textStart.getText(),
								textEnd.getText(), textPane.getText(),
								"Floating Task");
						db.insertLog(textTitle.getText(), "ADD");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {/*
						File f = new File("src/sound/tada.wav");
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(f);
						AudioFormat format;
						format = audio.getFormat();
						SourceDataLine auline;
						DataLine.Info info = new DataLine.Info(
								SourceDataLine.class, format);
						auline = (SourceDataLine) AudioSystem.getLine(info);
						auline.open(format);
						auline.start();
						int nBytesRead = 0;
						byte[] abData = new byte[524288];
						while (nBytesRead != -1) {
							nBytesRead = audio.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								auline.write(abData, 0, nBytesRead);
							}
						}*/
						JOptionPane.showMessageDialog(null, "Task has been added");
					} catch (Exception E) {
						System.out.println(E.getMessage());
					}
					tableT.refresh();
					contentPane.removeAll();
					contentPane.add(panel);
					repaint();
					printAll(getGraphics());
					tableL.refreshl();
					cekUndo();

				}

			}
		});
		btnS.setBounds(179, 393, 64, 40);
		panel2.add(btnS);

		final JButton btnBack = new JButton("");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnBack.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnBack.setBackground(new Color(255, 140, 0));
			}
		});
		btnBack.setBackground(new Color(255, 140, 0));
		btnBack.setIcon(new ImageIcon(Main.class.getResource("/image/home-button.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {/*
					File f = new File("src/sound/Simple_Low.wav");
					AudioInputStream audio = AudioSystem.getAudioInputStream(f);
					AudioFormat format;
					format = audio.getFormat();
					SourceDataLine auline;
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					auline = (SourceDataLine) AudioSystem.getLine(info);
					auline.open(format);
					auline.start();
					int nBytesRead = 0;
					byte[] abData = new byte[524288];
					while (nBytesRead != -1) {
						nBytesRead = audio.read(abData, 0, abData.length);
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					}*/
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				btnBack.setBackground(new Color(255, 140, 0));
				contentPane.removeAll();
				contentPane.add(panel);
				repaint();
				printAll(getGraphics());
			}
		});
		btnBack.setBounds(253, 393, 64, 40);
		panel2.add(btnBack);
		panel2.setLayout(null);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblType.setBounds(21, 163, 64, 28);
		panel2.add(lblType);

		final JButton btnClear_1 = new JButton("CLEAR");
		final JButton buttonAdd = new JButton("ADD");
		final JButton btnDetails = new JButton("DETAILS");
		final JButton buttonLog = new JButton("LOG");
		final JButton buttonDelete = new JButton("DELETE");

		buttonLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				buttonLog.setBackground(new Color(0, 0, 0));
				buttonAdd.setBackground(new Color(255, 140, 0));
				buttonDelete.setBackground(new Color(255, 140, 0));
				btnClear_1.setBackground(new Color(255, 140, 0));
				btnDetails.setBackground(new Color(255, 140, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				buttonLog.setBackground(new Color(255, 140, 0));
			}
		});
		buttonLog.setForeground(Color.WHITE);
		buttonLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {/*
					File f = new File("src/sound/Simple_High.wav");
					AudioInputStream audio = AudioSystem.getAudioInputStream(f);
					AudioFormat format;
					format = audio.getFormat();
					SourceDataLine auline;
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					auline = (SourceDataLine) AudioSystem.getLine(info);
					auline.open(format);
					auline.start();
					int nBytesRead = 0;
					byte[] abData = new byte[524288];
					while (nBytesRead != -1) {
						nBytesRead = audio.read(abData, 0, abData.length);
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					}*/
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				buttonLog.setBackground(new Color(255, 140, 0));
				contentPane.removeAll();
				contentPane.add(panellog);
				repaint();
				printAll(getGraphics());
				tableL.refreshl();
			}
		});
		buttonLog.setBackground(new Color(255, 140, 0));
		buttonLog.setFont(new Font("Dialog", Font.BOLD, 10));
		buttonLog.setHorizontalAlignment(SwingConstants.LEFT);
		buttonLog.setIcon(new ImageIcon(Main.class.getResource("/image/log-button.png")));
		panel.setLayout(null);
		buttonLog.setBounds(283, 404, 105, 40);
		panel.add(buttonLog);

		// delete main
		// final JButton buttonDelete = new JButton("DELETE");
		buttonDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				buttonDelete.setBackground(new Color(0, 0, 0));
				buttonAdd.setBackground(new Color(255, 140, 0));
				buttonLog.setBackground(new Color(255, 140, 0));
				btnClear_1.setBackground(new Color(255, 140, 0));
				btnDetails.setBackground(new Color(255, 140, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				buttonDelete.setBackground(new Color(255, 140, 0));
			}
		});
		buttonDelete.setForeground(Color.WHITE);
		buttonDelete.setBackground(new Color(255, 140, 0));
		buttonDelete.setFont(new Font("Dialog", Font.BOLD, 10));
		buttonDelete.setHorizontalAlignment(SwingConstants.LEFT);
		buttonDelete.setIcon(new ImageIcon(Main.class.getResource("/image/delete-button.png")));
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDelete.setBackground(new Color(255, 140, 0));
				int baris = table_2.getSelectedRow();
				int nomor = (baris);
				Database db = new Database();
				if (baris < 0) {
					try {/*
						File f = new File("src/sound/Windows Error.wav");
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(f);
						AudioFormat format;
						format = audio.getFormat();
						SourceDataLine auline;
						DataLine.Info info = new DataLine.Info(
								SourceDataLine.class, format);
						auline = (SourceDataLine) AudioSystem.getLine(info);
						auline.open(format);
						auline.start();
						int nBytesRead = 0;
						byte[] abData = new byte[524288];
						while (nBytesRead != -1) {
							nBytesRead = audio.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								auline.write(abData, 0, nBytesRead);
							}
						}*/
						JOptionPane
								.showMessageDialog(null, "Pilih data dulu!!");
					} catch (Exception E) {
						System.out.println(E.getMessage());
					}
				} else {
					Object idDelete = tableT.getIdDelete(table_2
							.convertRowIndexToModel(baris));
					if (baris != -1) {
						try {
							db.deleteTaks(idDelete.toString());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						java.awt.Toolkit.getDefaultToolkit().beep();
						int result = JOptionPane.showConfirmDialog(null,
								"Are you sure want to delete this task ?");
						if (result == JOptionPane.YES_OPTION) {
							Object titleTask = tableT.getTitleTask(table_2
									.convertRowIndexToModel(nomor));
							db.insertLog(titleTask.toString(), "DELETE");
							tableT.refresh();
							try {/*
								File f = new File("src/sound/Dribble.wav");
								AudioInputStream audio = AudioSystem
										.getAudioInputStream(f);
								AudioFormat format;
								format = audio.getFormat();
								SourceDataLine auline;
								DataLine.Info info = new DataLine.Info(
										SourceDataLine.class, format);
								auline = (SourceDataLine) AudioSystem
										.getLine(info);
								auline.open(format);
								auline.start();
								int nBytesRead = 0;
								byte[] abData = new byte[524288];
								while (nBytesRead != -1) {
									nBytesRead = audio.read(abData, 0,
											abData.length);
									if (nBytesRead >= 0) {
										auline.write(abData, 0, nBytesRead);
									}
								}*/
								JOptionPane.showMessageDialog(null,
										"Task has been deleted");
							} catch (Exception E) {
								System.out.println(E.getMessage());
							}
						} else {
						}
					}
					cekUndo();
				}
			}
		});
		buttonDelete.setBounds(387, 404, 105, 40);
		panel.add(buttonDelete);

		// final JButton buttonAdd = new JButton("ADD");
		buttonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				buttonAdd.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				buttonAdd.setBackground(new Color(255, 140, 0));
			}
		});

		buttonAdd.setForeground(Color.WHITE);
		buttonAdd.setBackground(new Color(255, 140, 0));
		buttonAdd.setFont(new Font("Dialog", Font.BOLD, 10));
		buttonAdd.setHorizontalAlignment(SwingConstants.LEFT);
		buttonAdd.setIcon(new ImageIcon(Main.class.getResource("/image/add-button.png")));
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {/*
					File f = new File("src/sound/Simple_High.wav");
					AudioInputStream audio = AudioSystem.getAudioInputStream(f);
					AudioFormat format;
					format = audio.getFormat();
					SourceDataLine auline;
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					auline = (SourceDataLine) AudioSystem.getLine(info);
					auline.open(format);
					auline.start();
					int nBytesRead = 0;
					byte[] abData = new byte[524288];
					while (nBytesRead != -1) {
						nBytesRead = audio.read(abData, 0, abData.length);
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					}*/
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				buttonAdd.setBackground(new Color(255, 140, 0));
				textTitle.setText("");
				textPane.setText("");
				contentPane.removeAll();
				contentPane.add(panel2);
				repaint();
				printAll(getGraphics());
			}
		});
		buttonAdd.setBounds(75, 404, 105, 40);
		panel.add(buttonAdd);

		table_2 = new JTable(tableT);
		sorter1 = new TableRowSorter<TableTask>(tableT);
		table_2.setRowSorter(sorter1);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane = new JScrollPane(table_2);
		scrollPane.setBounds(10, 116, 665, 274);
		panel.add(scrollPane);
		scrollPane.setViewportView(table_2);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(83, 465, 55, 23);
		panel.add(btnExit);

		JLabel lblStudentActivityManager = new JLabel(
				"Student Activity Manager (tambahin LOGO)");
		lblStudentActivityManager.setBounds(143, 469, 208, 14);
		panel.add(lblStudentActivityManager);

		// final JButton btnDetails = new JButton("DETAILS");
		btnDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnDetails.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnDetails.setBackground(new Color(255, 140, 0));
			}
		});
		btnDetails.setForeground(Color.WHITE);
		btnDetails.setBackground(new Color(255, 140, 0));
		btnDetails.setFont(new Font("Dialog", Font.BOLD, 10));
		btnDetails.setHorizontalAlignment(SwingConstants.LEFT);
		btnDetails.setIcon(new ImageIcon(Main.class.getResource("/image/details-button.png")));
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {/*
					File f = new File("src/sound/Simple_High.wav");
					AudioInputStream audio = AudioSystem.getAudioInputStream(f);
					AudioFormat format;
					format = audio.getFormat();
					SourceDataLine auline;
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					auline = (SourceDataLine) AudioSystem.getLine(info);
					auline.open(format);
					auline.start();
					int nBytesRead = 0;
					byte[] abData = new byte[524288];
					while (nBytesRead != -1) {
						nBytesRead = audio.read(abData, 0, abData.length);
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					}*/
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				btnDetails.setBackground(new Color(255, 140, 0));
				int baris = table_2.getSelectedRow();
				if (baris < 0) {
					try {/*
						File f = new File("src/sound/Windows Error.wav");
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(f);
						AudioFormat format;
						format = audio.getFormat();
						SourceDataLine auline;
						DataLine.Info info = new DataLine.Info(
								SourceDataLine.class, format);
						auline = (SourceDataLine) AudioSystem.getLine(info);
						auline.open(format);
						auline.start();
						int nBytesRead = 0;
						byte[] abData = new byte[524288];
						while (nBytesRead != -1) {
							nBytesRead = audio.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								auline.write(abData, 0, nBytesRead);
							}
						}*/
						JOptionPane
								.showMessageDialog(null, "Pilih data dulu!!");
					} catch (Exception E) {
						System.out.println(E.getMessage());
					}
				} else {
					String start;
					String end;
					int select = table_2.getSelectedRow();
					if (table_2.getValueAt(select, 1) == null
							|| table_2.getValueAt(select, 1).toString()
									.isEmpty()) {
						start = "";
					} else {
						start = table_2.getValueAt(select, 1).toString();
					}
					if (table_2.getValueAt(select, 2) == null
							|| table_2.getValueAt(select, 2).toString()
									.isEmpty()) {
						end = "";
					} else {
						end = table_2.getValueAt(select, 2).toString();
					}

					comboBox1.setSelectedItem(table_2.getValueAt(select, 4));
					textTitled
							.setText(table_2.getValueAt(select, 0).toString());
					textStartd.setText(start);
					textEndd.setText(end);
					textDeskd.setText(table_2.getValueAt(select, 3).toString());
					contentPane.removeAll();
					contentPane.add(panelDetails);
					repaint();
					printAll(getGraphics());
				}
			}
		});
		btnDetails.setBounds(179, 404, 105, 40);
		panel.add(btnDetails);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/image/main.png")));
		lblNewLabel_1.setBounds(0, 0, 685, 75);
		panel.add(lblNewLabel_1);

		final JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Main.class.getResource("/image/main.png")));
		lblNewLabel_2.setBounds(0, 0, 685, 75);
		panelDetails.add(lblNewLabel_2);

		// edit panel ====================================================// *

		final JButton btnCancel_1 = new JButton("");
		btnCancel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCancel_1.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnCancel_1.setBackground(new Color(255, 140, 0));
			}
		});
		btnCancel_1.setBackground(new Color(255, 140, 0));
		btnCancel_1.setIcon(new ImageIcon(Main.class.getResource("/image/clear-button.png")));
		final JButton btnDone = new JButton("");
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnDone.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnEdit.setBackground(new Color(255, 140, 0));
			}
		});
		btnDone.setBackground(new Color(255, 140, 0));
		btnDone.setIcon(new ImageIcon(Main.class.getResource("/image/done-button.png")));

		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDone.setBackground(new Color(255, 140, 0));
				Database db = new Database();
				if (textTitled.getText().isEmpty()) {
					try {/*
						File f = new File("src/sound/Windows Error.wav");
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(f);
						AudioFormat format;
						format = audio.getFormat();
						SourceDataLine auline;
						DataLine.Info info = new DataLine.Info(
								SourceDataLine.class, format);
						auline = (SourceDataLine) AudioSystem.getLine(info);
						auline.open(format);
						auline.start();
						int nBytesRead = 0;
						byte[] abData = new byte[524288];
						while (nBytesRead != -1) {
							nBytesRead = audio.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								auline.write(abData, 0, nBytesRead);
							}
						}*/
						JOptionPane.showMessageDialog(null,
								"Task Title wajib diisi !!");
					} catch (Exception E) {
						System.out.println(E.getMessage());
					}
				} else if (comboBox1.getSelectedItem().equals("Timed Task")) {
					if (textStartd.getText().isEmpty()
							|| textEndd.getText().isEmpty()) {
						try {/*
							File f = new File("src/sound/Windows Error.wav");
							AudioInputStream audio = AudioSystem
									.getAudioInputStream(f);
							AudioFormat format;
							format = audio.getFormat();
							SourceDataLine auline;
							DataLine.Info info = new DataLine.Info(
									SourceDataLine.class, format);
							auline = (SourceDataLine) AudioSystem.getLine(info);
							auline.open(format);
							auline.start();
							int nBytesRead = 0;
							byte[] abData = new byte[524288];
							while (nBytesRead != -1) {
								nBytesRead = audio.read(abData, 0,
										abData.length);
								if (nBytesRead >= 0) {
									auline.write(abData, 0, nBytesRead);
								}
							}*/
							JOptionPane.showMessageDialog(null,
									"Date star dan end harus diisi !");
						} catch (Exception E) {
							System.out.println(E.getMessage());
						}

					} else {
						String[] tanggalendu = (textEndd.getText().split("-"));
						String[] tanggalstartu = (textStartd.getText()
								.split("-"));

						@SuppressWarnings("deprecation")
						Date selesaiu = new Date(Integer
								.parseInt(tanggalendu[0]), Integer
								.parseInt(tanggalendu[1]), Integer
								.parseInt(tanggalendu[2]));

						@SuppressWarnings("deprecation")
						Date mulaiu = new Date(Integer
								.parseInt(tanggalstartu[0]), Integer
								.parseInt(tanggalstartu[1]), Integer
								.parseInt(tanggalstartu[2]));

						if (selesaiu.compareTo(mulaiu) <= 0) {
							try {/*
								File f = new File(System
										.getProperty("user.dir")+ "\\bin\\"	+ "\\sound\\Windows Error.wav");
								AudioInputStream audio = AudioSystem
										.getAudioInputStream(f);
								AudioFormat format;
								format = audio.getFormat();
								SourceDataLine auline;
								DataLine.Info info = new DataLine.Info(
										SourceDataLine.class, format);
								auline = (SourceDataLine) AudioSystem
										.getLine(info);
								auline.open(format);
								auline.start();
								int nBytesRead = 0;
								byte[] abData = new byte[524288];
								while (nBytesRead != -1) {
									nBytesRead = audio.read(abData, 0,
											abData.length);
									if (nBytesRead >= 0) {
										auline.write(abData, 0, nBytesRead);
									}
								}*/
								JOptionPane.showMessageDialog(null,
										"End date must be after start date!");
							} catch (Exception E) {
								System.out.println(E.getMessage());
							}

						} else {
							int select = table_2.getSelectedRow();
							Object idDelete = tableT.getIdDelete(table_2
									.convertRowIndexToModel(select));
							try {
								db.updateTaks(idDelete.toString(), textTitled
										.getText(), textStartd.getText(),
										textEndd.getText(),
										textDeskd.getText(), comboBox1
												.getSelectedItem().toString());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {/*
								File f = new File("src/sound/CrystalRing.wav");
								AudioInputStream audio = AudioSystem
										.getAudioInputStream(f);
								AudioFormat format;
								format = audio.getFormat();
								SourceDataLine auline;
								DataLine.Info info = new DataLine.Info(
										SourceDataLine.class, format);
								auline = (SourceDataLine) AudioSystem
										.getLine(info);
								auline.open(format);
								auline.start();
								int nBytesRead = 0;
								byte[] abData = new byte[524288];
								while (nBytesRead != -1) {
									nBytesRead = audio.read(abData, 0,
											abData.length);
									if (nBytesRead >= 0) {
										auline.write(abData, 0, nBytesRead);
									}
								}*/
								JOptionPane.showMessageDialog(null,
										"Task has been updated");
							} catch (Exception E) {
								System.out.println(E.getMessage());
							}

							db.insertLog(textTitled.getText(), "UPDATE");
							tableL.refreshl();
							tableT.refresh();
							contentPane.removeAll();
							contentPane.add(panel);
							repaint();
							printAll(getGraphics());
							textTitled.setEditable(false);
							textEndd.setEditable(false);
							textStartd.setEditable(false);
							textDeskd.setEditable(false);
							btnEdit.setVisible(true);
							btnCancel_1.setVisible(false);
							btnCancel_1.setEnabled(false);
							btnDone.setVisible(false);
							cekUndo();
						}
					}
				} else if (comboBox1.getSelectedItem().equals("Deadline Task")) {
					if (textEndd.getText().isEmpty()) {
						try {/*
							File f = new File("src/sound/Windows Error.wav");
							AudioInputStream audio = AudioSystem
									.getAudioInputStream(f);
							AudioFormat format;
							format = audio.getFormat();
							SourceDataLine auline;
							DataLine.Info info = new DataLine.Info(
									SourceDataLine.class, format);
							auline = (SourceDataLine) AudioSystem.getLine(info);
							auline.open(format);
							auline.start();
							int nBytesRead = 0;
							byte[] abData = new byte[524288];
							while (nBytesRead != -1) {
								nBytesRead = audio.read(abData, 0,
										abData.length);
								if (nBytesRead >= 0) {
									auline.write(abData, 0, nBytesRead);
								}
							}*/
							JOptionPane.showMessageDialog(null,
									"Date end harus diisi !");
						} catch (Exception E) {
							System.out.println(E.getMessage());
						}

					} else {
						int select = table_2.getSelectedRow();
						Object idDelete = tableT.getIdDelete(table_2
								.convertRowIndexToModel(select));
						try {
							db.updateTaks(idDelete.toString(),
									textTitled.getText(), textStartd.getText(),
									textEndd.getText(), textDeskd.getText(),
									comboBox1.getSelectedItem().toString());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {/*
							File f = new File("src/sound/CrystalRing.wav");
							AudioInputStream audio = AudioSystem
									.getAudioInputStream(f);
							AudioFormat format;
							format = audio.getFormat();
							SourceDataLine auline;
							DataLine.Info info = new DataLine.Info(
									SourceDataLine.class, format);
							auline = (SourceDataLine) AudioSystem.getLine(info);
							auline.open(format);
							auline.start();
							int nBytesRead = 0;
							byte[] abData = new byte[524288];
							while (nBytesRead != -1) {
								nBytesRead = audio.read(abData, 0,
										abData.length);
								if (nBytesRead >= 0) {
									auline.write(abData, 0, nBytesRead);
								}
							}*/
							JOptionPane.showMessageDialog(null,
									"Task has been updated");
						} catch (Exception E) {
							System.out.println(E.getMessage());
						}
						db.insertLog(textTitled.getText(), "UPDATE");
						tableL.refreshl();
						tableT.refresh();
						contentPane.removeAll();
						contentPane.add(panel);
						repaint();
						printAll(getGraphics());
						textTitled.setEditable(false);
						textEndd.setEditable(false);
						textStartd.setEditable(false);
						textDeskd.setEditable(false);
						btnEdit.setVisible(true);
						btnCancel_1.setVisible(false);
						btnCancel_1.setEnabled(false);
						btnDone.setVisible(false);
						cekUndo();
					}
				} else {
					int select = table_2.getSelectedRow();
					Object idDelete = tableT.getIdDelete(table_2
							.convertRowIndexToModel(select));
					try {
						db.updateTaks(idDelete.toString(),
								textTitled.getText(), textStartd.getText(),
								textEndd.getText(), textDeskd.getText(),
								comboBox1.getSelectedItem().toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {/*
						File f = new File("src/sound/CrystalRing.wav");
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(f);
						AudioFormat format;
						format = audio.getFormat();
						SourceDataLine auline;
						DataLine.Info info = new DataLine.Info(
								SourceDataLine.class, format);
						auline = (SourceDataLine) AudioSystem.getLine(info);
						auline.open(format);
						auline.start();
						int nBytesRead = 0;
						byte[] abData = new byte[524288];
						while (nBytesRead != -1) {
							nBytesRead = audio.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								auline.write(abData, 0, nBytesRead);
							}
						}*/
						JOptionPane.showMessageDialog(null,
								"Task has been updated");
					} catch (Exception E) {
						System.out.println(E.getMessage());
					}
					db.insertLog(textTitled.getText(), "UPDATE");
					tableL.refreshl();
					tableT.refresh();
					contentPane.removeAll();
					contentPane.add(panel);
					repaint();
					printAll(getGraphics());
					textTitled.setEditable(false);
					textEndd.setEditable(false);
					textStartd.setEditable(false);
					textDeskd.setEditable(false);
					btnEdit.setVisible(true);
					btnCancel_1.setVisible(false);
					btnCancel_1.setEnabled(false);
					btnDone.setVisible(false);
					cekUndo();
				}
			}
		});
		btnDone.setEnabled(true);
		btnDone.setVisible(false);
		btnDone.setBounds(160, 399, 60, 40);
		panelDetails.add(btnDone);

		final JLabel lblUpdate = new JLabel("update");
		lblUpdate.setIcon(new ImageIcon(Main.class.getResource("/image/label-edit.png")));
		lblUpdate.setBounds(460, 120, 207, 254);
		panelDetails.add(lblUpdate);
		lblUpdate.setVisible(false);

		final JLabel lbldetails = new JLabel("New label");
		lbldetails.setIcon(new ImageIcon(Main.class.getResource("/image/label-details.png")));
		lbldetails.setBounds(460, 120, 201, 254);
		panelDetails.add(lbldetails);

		btnCancel_1.setEnabled(false);
		btnCancel_1.setVisible(false);
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {/*
					File f = new File("src/sound/Simple_Low.wav");
					AudioInputStream audio = AudioSystem.getAudioInputStream(f);
					AudioFormat format;
					format = audio.getFormat();
					SourceDataLine auline;
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					auline = (SourceDataLine) AudioSystem.getLine(info);
					auline.open(format);
					auline.start();
					int nBytesRead = 0;
					byte[] abData = new byte[524288];
					while (nBytesRead != -1) {
						nBytesRead = audio.read(abData, 0, abData.length);
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					}*/
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				btnCancel_1.setBackground(new Color(255, 140, 0));
				lbldetails.setVisible(true);
				lblUpdate.setVisible(false);
				textTitled.setEditable(false);
				textEndd.setEditable(false);
				textStartd.setEditable(false);
				textDeskd.setEditable(false);
				btnEdit.setVisible(true);
				btnCancel_1.setVisible(false);
				btnCancel_1.setEnabled(false);
				btnDone.setVisible(false);
			}
		});
		btnCancel_1.setBounds(230, 399, 60, 40);
		panelDetails.add(btnCancel_1);

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {/*
					File f = new File("src/sound/Simple_High.wav");
					AudioInputStream audio = AudioSystem.getAudioInputStream(f);
					AudioFormat format;
					format = audio.getFormat();
					SourceDataLine auline;
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					auline = (SourceDataLine) AudioSystem.getLine(info);
					auline.open(format);
					auline.start();
					int nBytesRead = 0;
					byte[] abData = new byte[524288];
					while (nBytesRead != -1) {
						nBytesRead = audio.read(abData, 0, abData.length);
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					}*/
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				btnEdit.setBackground(new Color(255, 140, 0));
				lblUpdate.setVisible(true);
				lbldetails.setVisible(false);
				textTitled.setEditable(true);
				textEndd.setEditable(true);
				textStartd.setEditable(true);
				textDeskd.setEditable(true);
				btnEdit.setVisible(false);
				btnCancel_1.setVisible(true);
				btnCancel_1.setEnabled(true);
				btnDone.setVisible(true);
			}
		});
		btnEdit.setBounds(230, 399, 60, 40);
		panelDetails.add(btnEdit);

		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblType_1.setBounds(22, 170, 46, 14);
		panelDetails.add(lblType_1);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 86, 665, 2);
		panelDetails.add(separator_4);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Main.class.getResource("/image/main.png")));

		lblNewLabel_3.setBounds(0, 0, 685, 75);
		panel2.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Main.class.getResource("/image/label-add.png")));
		lblNewLabel_5.setBounds(475, 112, 200, 254);
		panel2.add(lblNewLabel_5);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 87, 665, 2);
		panel2.add(separator);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Main.class.getResource("/image/update.png")));

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 396, 665, 2);
		panel.add(separator_2);

		txtSearch = new JTextField();
		txtSearch.setEnabled(false);
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtSearch.setEnabled(false);
			}
		});

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				newFilter();
			}

			public void insertUpdate(DocumentEvent e) {
				newFilter();
			}

			public void removeUpdate(DocumentEvent e) {
				newFilter();
			}
		});

		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSearch.setEnabled(true);
			}
		});
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSearch.setBounds(503, 85, 172, 23);
		panel.add(txtSearch);
		txtSearch.setColumns(10);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(Main.class.getResource("/image/searc.jpg")));
		lblSearch.setBounds(473, 85, 27, 26);
		panel.add(lblSearch);

		// final JButton btnClear_1 = new JButton("CLEAR");
		btnClear_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnClear_1.setBackground(new Color(0, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnClear_1.setBackground(new Color(255, 140, 0));
			}
		});
		btnClear_1.setIcon(new ImageIcon(Main.class.getResource("/image/clear-button.png")));
		btnClear_1.setFont(new Font("Dialog", Font.BOLD, 10));
		btnClear_1.setForeground(Color.WHITE);
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {/*
					File f = new File("src/sound/Simple_High.wav");
					AudioInputStream audio = AudioSystem.getAudioInputStream(f);
					AudioFormat format;
					format = audio.getFormat();
					SourceDataLine auline;
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					auline = (SourceDataLine) AudioSystem.getLine(info);
					auline.open(format);
					auline.start();
					int nBytesRead = 0;
					byte[] abData = new byte[524288];
					while (nBytesRead != -1) {
						nBytesRead = audio.read(abData, 0, abData.length);
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					}*/
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				btnClear_1.setBackground(new Color(255, 140, 0));
				Database db = new Database();
				java.awt.Toolkit.getDefaultToolkit().beep();
				int result = JOptionPane.showConfirmDialog(null,
						"Are you sure want to format list Task?");
				if (result == JOptionPane.YES_OPTION) {
					db.cleartask();
					db.insertLog("LIST TASK", "FORMAT");
					tableT.refresh();
					cekUndo();
					cekRedo();
					try {/*
						File f = new File("src/sound/CetiAlpha.wav");
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(f);
						AudioFormat format;
						format = audio.getFormat();
						SourceDataLine auline;
						DataLine.Info info = new DataLine.Info(
								SourceDataLine.class, format);
						auline = (SourceDataLine) AudioSystem.getLine(info);
						auline.open(format);
						auline.start();
						int nBytesRead = 0;
						byte[] abData = new byte[524288];
						while (nBytesRead != -1) {
							nBytesRead = audio.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								auline.write(abData, 0, nBytesRead);
							}
						}*/
						JOptionPane.showMessageDialog(null,
								"Formated Succesfull");
					} catch (Exception E) {
						System.out.println(E.getMessage());
					}
				} else {
				}

			}
		});
		btnClear_1.setBackground(new Color(255, 140, 0));
		btnClear_1.setBounds(491, 404, 105, 40);
		panel.add(btnClear_1);

		final JLabel lblTimer = new JLabel("Timer");
		lblTimer.setFont(new Font("Digital-7", Font.BOLD, 25));
		lblTimer.setBounds(10, 80, 105, 25);
		panel.add(lblTimer);

		final DateFormat timeFormat1 = new SimpleDateFormat("HH:mm:ss");
		ActionListener timerListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String time = timeFormat1.format(date);
				lblTimer.setText(time);
			}
		};
		Timer timer1 = new Timer(1000, timerListener1);
		// to make sure it doesn't wait one second at the start
		timer1.setInitialDelay(0);
		timer1.start();
	}

	// buat search
	private void newFilter() {
		RowFilter<TableTask, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
					3);
			filters.add(RowFilter.regexFilter(txtSearch.getText(), 0));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toLowerCase(), 0));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toUpperCase(), 0));
			filters.add(RowFilter.regexFilter(txtSearch.getText(), 1));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toLowerCase(), 1));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toUpperCase(), 1));
			filters.add(RowFilter.regexFilter(txtSearch.getText(), 2));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toLowerCase(), 2));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toUpperCase(), 2));
			filters.add(RowFilter.regexFilter(txtSearch.getText(), 3));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toLowerCase(), 3));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toUpperCase(), 3));
			filters.add(RowFilter.regexFilter(txtSearch.getText(), 4));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toLowerCase(), 4));
			filters.add(RowFilter.regexFilter(
					txtSearch.getText().toUpperCase(), 4));
			rf = RowFilter.orFilter(filters);

		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter1.setRowFilter(rf);
	}
}
