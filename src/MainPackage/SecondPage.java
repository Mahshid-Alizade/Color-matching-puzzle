package MainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.lang.model.element.NestingKind;
import javax.swing.*;
import java.text.*;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class SecondPage extends JFrame {

	NecessaryMethods obj3 = new NecessaryMethods();
	static boolean yesItsMoreThanThree;// it find number of neighbour
	int p = FirstPage.getpTextField();// number of moves allowed
	DefaultTableModel model2;// about table
	static int moreThan3;// counter of neighbour
	Random rnd = new Random();
	static int moveCounter = 0;
	static JLabel timeLabel;
	static JLabel cLabel;
	static JLabel pLabel;
	static JLabel hLabel;
	static JButton picButton;
	JLabel errorLabel;
	static JLabel nameLabel;
	int saved_i;
	int saved_j;
	Thread t;
	int h;// deleted buttons
	static SecondPage frame;
	private JPanel contentPane;
	static JButton alizade[][];// array of buttons
	static boolean notChecked[][];// array of boolean
	static Random random = new Random();
	FirstPage obj;

	// Timer
	static int second = 0;
	static int minute = 0;
	static int hour = 0;
	static int millisecond = 0;
	static boolean state = true;
	static boolean enterTheGame = false;

	// the array list of colors
	static ArrayList<Color> colors = new ArrayList<Color>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				frame = new SecondPage();
				frame.setVisible(true);

			}
		});
	}

	public SecondPage() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1370, 743);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 20, 147), 3));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 141, 972, 552);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(FirstPage.getN(), FirstPage.getM(), 0, 0));

		//////////////////////////////////////////////////////////////
		alizade = new JButton[FirstPage.getN()][FirstPage.getM()];
		notChecked = new boolean[FirstPage.getN()][FirstPage.getM()];
		//////////////////////////////////////////////////////////////

		for (int i = 0; i < FirstPage.getN(); i++) {
			for (int j = 0; j < FirstPage.getM(); j++) {

				saved_i = i;
				saved_j = j;
				alizade[i][j] = new JButton("");
				alizade[i][j].setFocusable(false);
				notChecked[i][j] = true;

				// add action listener for each buttons
				alizade[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// if timer is off
						if (!enterTheGame) {
							errorLabel.setText("click the *Start*");
						}

						errorLabel.setText("");
						moreThan3 = 1;
						NecessaryMethods.makeAllTrue();

						// if the game started
						if (enterTheGame) {
							if (!NecessaryMethods.isWin(Integer.parseInt(hLabel.getText()),
									FirstPage.getM() * FirstPage.getN())) {
								if (!NecessaryMethods.checkLoss()) {

									moreThan3 = 1;
									NecessaryMethods.makeAllTrue();
									int i = 0;
									int j = 0;

									inja:
									// find i and j of clicked button
									for (int ii = 0; ii < FirstPage.getN(); ii++) {
										for (int jj = 0; jj < FirstPage.getM(); jj++) {
											int x1 = alizade[ii][jj].getX();
											int x2 = ((JButton) e.getSource()).getX();
											int y1 = alizade[ii][jj].getY();
											int y2 = ((JButton) e.getSource()).getY();

											if (x1 == x2 && y1 == y2) {
												i = ii;
												j = jj;
												break inja;
											}

										}
									}
									yesItsMoreThanThree = false;

									p--;
									moveCounter++;
									pLabel.setText(String.valueOf(p));
									cLabel.setText(String.valueOf(moveCounter));

									// if this button has more than three neighbour
									if (obj3.checkMoreThan3(i, j)) {

										NecessaryMethods.makeAllTrue();
										obj3.findNeighbour(i, j);
									} else {
										errorLabel.setText("your button should has more than 3 neighbour!!");
										p++;
										moveCounter--;
										pLabel.setText(p + "");
										cLabel.setText(moveCounter + "");
									}

									// number of deleted buttons
									hLabel.setText(String.valueOf(
											NecessaryMethods.deletedButton(FirstPage.getN(), FirstPage.getM())));

									NecessaryMethods.makeAllTrue();

									// first gravity
									for (int jj = 0; jj < FirstPage.getM(); jj++) {
										for (int ii = 0; ii < FirstPage.getN(); ii++) {
											if (!SecondPage.alizade[ii][jj].isVisible())
												obj3.firstGravity(ii, jj);
										}
									}

									// second gravity
									for (int ii = 0; ii < FirstPage.getN(); ii++) {
										for (int jj = FirstPage.getM() - 1; jj >= 0; jj--) {
											if (!SecondPage.alizade[ii][jj].isVisible())
												obj3.secondGraviti(ii, jj);
										}
									}

									// check win
									if (NecessaryMethods.checkWin()) {
										String record = NecessaryMethods.getRecord(false) + "";
										errorLabel.setText("YOU WON BABY!!!!" + " your Record is : " + record);
										String sendToFile = SecondPage.nameLabel.getText().trim() + "    " + record
												+ "    " + FirstPage.getProPic() + "\r\n";
										NecessaryMethods.sendToFile(sendToFile);
										frame.setVisible(false);
										stopTimer();
									}
									// check loos
									if (NecessaryMethods.checkLoss()) {

										String record = NecessaryMethods.getRecord(false) + "";
										errorLabel.setText("YOU LOST BABY!!!!" + " your Record is : " + record);
										String sendToFile = nameLabel.getText().trim() + "    " + record + "    "
												+ FirstPage.getProPic() + "\r\n";
										NecessaryMethods.sendToFile(sendToFile);
										stopTimer();
									}

								} else if (NecessaryMethods.checkWin()) {
									String record = NecessaryMethods.getRecord(false) + "";
									errorLabel.setText("YOU WON BABY!!!" + " your Record is : " + record);
									String sendToFile = SecondPage.nameLabel.getText().trim() + "    " + record + "    "
											+ FirstPage.getProPic() + "\r\n";
									NecessaryMethods.sendToFile(sendToFile);
									// RecordFrame.main(null);
									frame.setVisible(false);
									stopTimer();
								} else if (NecessaryMethods.checkLoss()) {
									String record = NecessaryMethods.getRecord(false) + "";
									errorLabel.setText("YOU LOST BABY!!!" + " your Record is : " + record);
									String sendToFile = nameLabel.getText().trim() + "    " + record + "    "
											+ FirstPage.getProPic() + "\r\n";
									NecessaryMethods.sendToFile(sendToFile);
									stopTimer();
								}

							}

						}

					}

				});

				panel_1.add(alizade[i][j]);
				randomColors(FirstPage.getRandomColorField());
				int x = (int) random.nextInt(FirstPage.getRandomColorField());
				alizade[i][j].setBackground(colors.get(x));
			}
		}

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 20, 147), 3));
		panel.setBackground(Color.YELLOW);
		panel.setBounds(10, 11, 119, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		// panel_1.setLayout(new GridLayout(FirstPage.getN(), FirstPage.getM(), 0, 0));

		picButton = new JButton("");
		picButton.setBounds(10, 11, 100, 99);
		panel.add(picButton);

		if (FirstPage.fromFilechooser)
			picButton.setIcon(new ImageIcon(FirstPage.image1));
		else
			picButton.setIcon(new ImageIcon(FirstPage.getProPic()));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 20, 147), 3));
		panel_2.setBackground(new Color(255, 255, 0));
		panel_2.setBounds(139, 11, 229, 50);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		nameLabel = new JLabel("");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(10, 11, 209, 28);
		panel_2.add(nameLabel);
		nameLabel.setFont(new Font("Lucida Fax", Font.BOLD, 24));

		nameLabel.setText(FirstPage.getUserName());

		JPanel timePanel = new JPanel();
		timePanel.setBackground(new Color(255, 255, 0));
		timePanel.setBorder(new LineBorder(new Color(255, 20, 147), 3));
		timePanel.setBounds(378, 11, 440, 83);
		contentPane.add(timePanel);
		timePanel.setLayout(null);

		// start button(start timer)
		JButton btnSTA = new JButton("S T A R T");
		btnSTA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAction(e);
				enterTheGame = true;
				pLabel.setText(String.valueOf(FirstPage.getpTextField()));
			}
		});

		btnSTA.setBackground(new Color(255, 20, 147));
		btnSTA.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnSTA.setBounds(243, 11, 187, 59);
		timePanel.add(btnSTA);

		timeLabel = new JLabel("");
		timeLabel.setForeground(new Color(255, 20, 147));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("Lucida Fax", Font.BOLD, 26));
		timeLabel.setBounds(10, 11, 192, 59);
		timePanel.add(timeLabel);

		JPanel pPanel = new JPanel();
		pPanel.setBorder(new TitledBorder(new LineBorder(new Color(255, 20, 147), 3), "  P  ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		pPanel.setBackground(Color.YELLOW);
		pPanel.setBounds(139, 72, 69, 58);
		contentPane.add(pPanel);
		pPanel.setLayout(null);

		pLabel = new JLabel(FirstPage.getpTextField() + "");
		pLabel.setToolTipText("Number of moves you allowed");
		pLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		pLabel.setBounds(10, 11, 49, 36);
		pPanel.add(pLabel);

		JPanel cPanel = new JPanel();
		cPanel.setBorder(new TitledBorder(new LineBorder(new Color(255, 20, 147), 3), "  C  ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		cPanel.setBackground(Color.YELLOW);
		cPanel.setBounds(218, 72, 69, 58);
		contentPane.add(cPanel);
		cPanel.setLayout(null);

		cLabel = new JLabel("0");
		cLabel.setToolTipText("Number of your moves");
		cLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		cLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cLabel.setBounds(10, 11, 49, 36);
		cPanel.add(cLabel);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(255, 20, 147), 3), "  H  ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBackground(Color.YELLOW);
		panel_5.setBounds(297, 72, 71, 58);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		hLabel = new JLabel("0");
		hLabel.setToolTipText("Number of Deleted button");
		hLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		hLabel.setBounds(10, 11, 51, 36);
		panel_5.add(hLabel);

		errorLabel = new JLabel("");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(new Color(255, 20, 147));
		errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		errorLabel.setBounds(378, 99, 440, 31);
		contentPane.add(errorLabel);

		// calculate the record
		JButton btnREC = new JButton("R E C O R D");
		btnREC.setBounds(828, 7, 154, 29);
		contentPane.add(btnREC);
		btnREC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (state)
					errorLabel.setText("The Game is not Over");
				else {
					try {
						// NecessaryMethods.readFromFile();
						NecessaryMethods.no = false;
						addRow();
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}
		});
		btnREC.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnREC.setBackground(new Color(255, 20, 147));

		// this method give us "i" and "j"
		JButton btnHEL = new JButton("H E L P");
		btnHEL.setBounds(828, 40, 154, 28);
		contentPane.add(btnHEL);
		btnHEL.setFont(new Font("Segoe UI", Font.BOLD, 21));
		btnHEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorLabel.setText("i : " + NecessaryMethods.helpI + "  j : " + NecessaryMethods.helpJ);
			}
		});
		btnHEL.setBackground(new Color(255, 20, 147));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(255, 20, 147), 3), "  Record   ",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(Color.YELLOW);
		panel_3.setBounds(992, 11, 352, 682);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 332, 648);
		panel_3.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setBackground(Color.YELLOW);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "              Name", "               Record" }));
		scrollPane.setViewportView(table);

		// back to first page
		JButton btnNewGame = new JButton("NEW GAME");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!state) {
					// FirstPage.image1 = null;
					FirstPage.main(null);
					frame.setVisible(false);
				} else
					errorLabel.setText("the Game Is Not Over");
			}
		});
		btnNewGame.setFont(new Font("Segoe UI", Font.BOLD, 21));
		btnNewGame.setBackground(new Color(255, 20, 147));
		btnNewGame.setBounds(828, 72, 154, 31);
		contentPane.add(btnNewGame);

		// stop timer and save record
		JButton btnFinish = new JButton("F i n i s h");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorLabel.setText("");
				stopTimer();
				String sendToFile = nameLabel.getText().trim() + "    " + NecessaryMethods.getRecord(enterTheGame) + "    "
						+ FirstPage.getProPic() + "\r\n";
				NecessaryMethods.sendToFile(sendToFile);
			}
		});
		btnFinish.setFont(new Font("Segoe UI", Font.BOLD, 21));
		btnFinish.setBackground(new Color(255, 20, 147));
		btnFinish.setBounds(828, 107, 154, 29);
		contentPane.add(btnFinish);

	}

	// timer
	private void jButtonAction(java.awt.event.ActionEvent evt) {

		state = true;

		t = new Thread() {

			public void run() {
				for (;;) {
					if (state) {
						try {
							sleep(1);

							if (millisecond > 1000) {
								millisecond = 0;
								second++;
							}
							if (second > 60) {
								second = 0;
								minute++;
							}
							if (minute > 60) {
								second = 0;
								minute = 0;
								hour++;
							}
							millisecond++;

							timeLabel.setText(hour + " : " + minute + " : " + second);
						} catch (Exception e) {

						}

					} else {
						break;
					}
				}
			}

		};
		t.start();
	}

	// stop timer
	void stopTimer() {
		state = false;
	}

	// Random color
	private JTable table;

	static void randomColors(int x) {
		for (int i = 0; i < x; i++) {

			if (i < x) {
				colors.add(Color.red);
				i++;
			}
			if (i < x) {
				colors.add(Color.orange);
				i++;
			}
			if (i < x) {
				colors.add(Color.yellow);
				i++;
			}
			if (i < x) {
				colors.add(Color.green);
				i++;
			}
			if (i < x) {
				colors.add(Color.blue);
				i++;
			}
			if (i < x) {
				colors.add(Color.magenta);
				i++;
			}
			if (i < x) {
				colors.add(Color.pink);
				i++;
			}
			if (i < x) {
				colors.add(Color.cyan);
				i++;
			}
			if (i < x) {
				colors.add(new Color(192, 192, 192));
				i++;
			}
			if (i < x) {
				colors.add(new Color(128, 128, 0));
				i++;
			}
			if (i < x) {
				colors.add(new Color(0, 128, 128));
				i++;
			}

		}
	}

	// add row to table
	public void addRow() throws IOException {
		model2 = (DefaultTableModel) table.getModel();
		int x = model2.getRowCount();
		for(int i = x -1 ; i >= 0 ; i --) {
			model2.removeRow(i);
		}
		if (!NecessaryMethods.no) {

			try {
				NecessaryMethods.readFromFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			for (int i = 0; i < NecessaryMethods.readArrayList.size(); i++) {
				String name = NecessaryMethods.readArrayList.get(i).split("    ")[0];
				String record = NecessaryMethods.readArrayList.get(i).split("    ")[1];
				model2.addRow(new Object[] { name, record });
			}
		} else
			errorLabel.setText("you are first Player");

	}
}
