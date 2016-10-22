package rplproject;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.net.URL;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import javax.swing.ImageIcon;

public class Notif extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notif frame = new Notif();
					frame.setVisible(false);
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
	public Notif() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}

		Database db = new Database();
		int temp = db.count();
		String angka = Integer.toString(temp);
		final JFrame frame = new JFrame();
		JButton cloesButton = new JButton(new AbstractAction("x") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(final ActionEvent e) {
				frame.dispose();
			}
		});
		cloesButton.setText("");
		cloesButton.setIcon(new ImageIcon(Main.class.getResource("/image/exit-icon-notif.png")));
		cloesButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		cloesButton.setBounds(252, 5, 35, 35);
		frame.setSize(300, 125);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		cloesButton.setMargin(new Insets(1, 4, 1, 4));
		cloesButton.setFocusable(false);
		frame.getContentPane().add(cloesButton);

		JLabel lblNewLabel = new JLabel("Gambar");
		lblNewLabel
				.setIcon(new ImageIcon(Main.class.getResource("/image/label-notif.png")));
		lblNewLabel.setBounds(0, 0, 60, 125);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("You Have");
		lblNewLabel_2.setBounds(63, 26, 60, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setText(angka);
		lblNewLabel_3.setBounds(120, 26, 29, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Task that should be done tommorow");
		lblNewLabel_4.setBounds(63, 51, 224, 14);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Please Check Your Task Now");
		lblNewLabel_5.setBounds(63, 78, 174, 14);
		frame.getContentPane().add(lblNewLabel_5);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		if (temp != 0) {
			try {
				File f = new File("src/sound/tick.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(f);
				AudioFormat format;
				format = audio.getFormat();
				SourceDataLine auline;
				DataLine.Info info = new DataLine.Info(SourceDataLine.class,
						format);
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
				frame.setVisible(true);
				}
			} catch (Exception E) {
				System.out.println(E.getMessage());

			}

		} else {
			frame.setVisible(false);
		}

		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(
				frame.getGraphicsConfiguration());// height of the task bar
		frame.setLocation(scrSize.width - frame.getWidth(), scrSize.height
				- toolHeight.bottom - frame.getHeight());
		frame.setAlwaysOnTop(true);
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(7000); // time after which pop up will be
										// disappeared.
					frame.dispose();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

}
