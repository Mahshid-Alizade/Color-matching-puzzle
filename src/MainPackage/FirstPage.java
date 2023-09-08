package MainPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class FirstPage extends JFrame {


	private static boolean MorePicBtnSetVisible = true;
	private static JTextField randomColorField;//random colors number
	private static JTextField pTextField;//moves allowed
	private JTextField columnField;//columns number field
	private JTextField rowField;//rows number field
	private JTextField nameField;
	private static String userName;
	private static String proPic ;//path of picture
	private JPanel contentPane;
	static int playersNumber = 0;
	private static int n ;//row number
	private static int m ;//column number
	private int c ;//moves number
	JLabel picErrorLabel;
	JLabel errorLabel;
	private JTable table;
	static FirstPage frame;
	static DefaultTableModel model;//it used for table
	static Image image1 = null;
	static boolean fromFilechooser = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FirstPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public FirstPage() {
		
		//default picture for pro
		setProPic(proPic = "C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_20052018_205147.jpg");
		
		setType(Type.UTILITY);
		setBackground(Color.YELLOW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 627);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 20, 147), 3), "  Sign Up  ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.YELLOW);
		panel.setBounds(10, 11, 285, 418);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		//column label
		JLabel label = new JLabel("column :");
		label.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
		label.setBounds(21, 141, 124, 27);
		panel.add(label);
		
		columnField = new JTextField();
		columnField.setColumns(10);
		columnField.setBounds(186, 139, 76, 27);
		panel.add(columnField);
		
		//row label
		JLabel label_1 = new JLabel("Row :");
		label_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
		label_1.setBounds(21, 179, 124, 27);
		panel.add(label_1);
		
		rowField = new JTextField();
		rowField.setColumns(10);
		rowField.setBounds(186, 179, 76, 27);
		panel.add(rowField);
		
		JLabel label_2 = new JLabel("fill the text fields :");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		label_2.setBounds(21, 26, 220, 37);
		panel.add(label_2);
		
		//name label
		JLabel label_3 = new JLabel("your name :");
		label_3.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
		label_3.setBounds(21, 104, 100, 14);
		panel.add(label_3);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(156, 98, 106, 27);
		panel.add(nameField);
		
		JLabel label_4 = new JLabel("(  if you are playing for the first time  )");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_4.setBounds(10, 59, 231, 27);
		panel.add(label_4);
		
		//the button for start the game
		JButton btnSignUp = new JButton("O K");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nameField.getText().equals(""))
					errorLabel.setText("enter your name");
				else if(columnField.getText().equals(""))
					errorLabel.setText("enter column number");
				else if(rowField.getText().equals(""))
					errorLabel.setText("enter row number");
				else if(pTextField.getText().equals(""))
					errorLabel.setText("fill p field");
				else if(randomColorField.getText().equals(""))
					errorLabel.setText("fill c field");
				
				else {
				setM(Integer.parseInt(columnField.getText()));
				setN(Integer.parseInt(rowField.getText()));
				setUserName(nameField.getText());
				SecondPage.main(null);
				frame.setVisible(false);
				}
			}
		});
		btnSignUp.setBackground(new Color(255, 20, 147));
		btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 40));
		btnSignUp.setBounds(47, 309, 194, 74);
		panel.add(btnSignUp);
		
		//p label(moved allowed)
		JLabel lblP = new JLabel("Allowed movements : ");
		lblP.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
		lblP.setBounds(21, 225, 165, 14);
		panel.add(lblP);
		
		pTextField = new JTextField();
		pTextField.setToolTipText("Number of moves allowed");
		pTextField.setBounds(186, 220, 76, 24);
		panel.add(pTextField);
		pTextField.setColumns(10);
		
		//color label
		JLabel lblC = new JLabel("Variaty of colors : ");
		lblC.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
		lblC.setBounds(21, 260, 135, 14);
		panel.add(lblC);
		
		randomColorField = new JTextField();
		randomColorField.setToolTipText("how many color do you want to choose?");
		randomColorField.setColumns(10);
		randomColorField.setBounds(186, 255, 76, 24);
		panel.add(randomColorField);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(new Color(255, 20, 147));
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		errorLabel.setBounds(10, 300, 265, 60);
		panel.add(errorLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 20, 147), 3), "  Set Picture For Pro  ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(305, 11, 367, 418);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		String user_dir = System.getProperty("user.dir");
		
		JButton btnMorePic = new JButton("More pictures");
		btnMorePic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int flag = chooser.showOpenDialog(null);
				fromFilechooser = true;
				if(flag == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					
					try {
						Image image;
						image = ImageIO.read(file);
						image1 = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
						proPic = file.getPath();
						picErrorLabel.setText("THAT'S OK :D ");
						MorePicBtnSetVisible = false;
					}catch (Exception e) {
						System.err.println(e);
						System.out.println("here");
					}
				}
			}
		});
		btnMorePic.setBackground(new Color(255, 20, 147));
		btnMorePic.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnMorePic.setBounds(23, 359, 320, 48);
		panel_1.add(btnMorePic);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 440, 662, 137);
		contentPane.add(scrollPane);
		
		//picturs button
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromFilechooser = false;
				setProPic(user_dir + "\\src\\MainPackage\\Images\\1.jpg");
				picErrorLabel.setText("THAT'S OK :D ");
				btnMorePic.setVisible(false);
			}
		});
		
		button.setIcon(new ImageIcon(user_dir + "\\src\\MainPackage\\Images\\1.jpg"));
		button.setBounds(133, 26, 100, 100);
		panel_1.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromFilechooser = false;
				setProPic(user_dir + "\\src\\MainPackage\\Images\\14.jpg");
				picErrorLabel.setText("THAT'S OK :D");
				btnMorePic.setVisible(false);
			}
		});
		button_1.setBounds(243, 137, 100, 100);
		button_1.setIcon(new ImageIcon(user_dir + "\\src\\MainPackage\\Images\\14.jpg"));
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromFilechooser = false;
				setProPic(user_dir + "\\src\\MainPackage\\Images\\5.jpg");
				picErrorLabel.setText("THAT'S OK :D ");
				btnMorePic.setVisible(false);
			}
		});
		
		button_2.setIcon(new ImageIcon(user_dir + "\\src\\MainPackage\\Images\\5.jpg"));
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_2.setBounds(243, 26, 100, 100);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromFilechooser = false;
				setProPic(user_dir + "\\src\\MainPackage\\Images\\9.jpg");
				picErrorLabel.setText("THAT'S OK :D ");
				btnMorePic.setVisible(false);
			}
		});
		button_3.setIcon(new ImageIcon(user_dir + "\\src\\MainPackage\\Images\\9.jpg"));
		button_3.setBounds(23, 26, 100, 100);
		panel_1.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromFilechooser = false;
				proPic = user_dir + "\\src\\MainPackage\\Images\\4.jpg";
				picErrorLabel.setText("THAT'S OK :D ");
				btnMorePic.setVisible(false);
			}
		});
		button_4.setIcon(new ImageIcon(user_dir + "\\src\\MainPackage\\Images\\4.jpg"));
		button_4.setBounds(23, 137, 100, 100);
		panel_1.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromFilechooser = false;
				setProPic(user_dir + "\\src\\MainPackage\\Images\\7.jpg");
				picErrorLabel.setText("THAT'S OK :D");
				btnMorePic.setVisible(false);
			}
		});
		button_5.setIcon(new ImageIcon(user_dir + "\\src\\MainPackage\\Images\\7.jpg"));
		button_5.setBounds(243, 248, 100, 100);
		panel_1.add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromFilechooser = false;
				setProPic(user_dir + "\\src\\MainPackage\\Images\\8.jpg");
				picErrorLabel.setText("THAT'S OK :D");
				btnMorePic.setVisible(false);
			}
		});
		button_6.setIcon(new ImageIcon(user_dir + "\\src\\MainPackage\\Images\\8.jpg"));
		button_6.setBounds(133, 248, 100, 100);
		panel_1.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromFilechooser = false;
				setProPic(user_dir + "\\src\\MainPackage\\Images\\13.jpg");
				picErrorLabel.setText("THAT'S OK :D");
				btnMorePic.setVisible(false);
			}
		});
		button_7.setIcon(new ImageIcon(user_dir + "\\src\\MainPackage\\Images\\13.jpg"));
		button_7.setBounds(133, 137, 100, 100);
		panel_1.add(button_7);

		
		JButton button_8 = new JButton("");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromFilechooser = false;
				setProPic(user_dir + "\\src\\MainPackage\\Images\\6.jpg");
				picErrorLabel.setText("THAT'S OK :D");
				btnMorePic.setVisible(false);
			}
		});
		button_8.setIcon(new ImageIcon(user_dir + "\\src\\MainPackage\\Images\\6.jpg"));
		button_8.setBounds(23, 248, 100, 100);
		panel_1.add(button_8);
		
		picErrorLabel = new JLabel("");
		picErrorLabel.setBounds(23, 359, 161, 48);
		panel_1.add(picErrorLabel);
		picErrorLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
		picErrorLabel.setForeground(new Color(255, 20, 147));
		
		
		
		//about table
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				nameField.setText(model.getValueAt(table.getSelectedRow(), 0).toString());
				proPic = NecessaryMethods.readArrayList.get(table.getSelectedRow()).split("    ")[2];
			}
		});
		table.setBackground(new Color(255, 255, 0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"                 Name", "                  Record"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		scrollPane.setViewportView(table);
		
		
		try {
			NecessaryMethods.readFromFile();
			addRow();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//getter methods
	public static int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public static int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public static String getProPic() {
		return proPic;
	}

	public void setProPic(String proPic) {
		this.proPic = proPic;
	}

	public static int getRandomColorField() {
		return Integer.parseInt(randomColorField.getText());
	}

	public void setRandomColorField(JTextField randomColorField) {
		this.randomColorField = randomColorField;
	}

	public static String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static int getpTextField() {
		return Integer.parseInt(pTextField.getText());
	}

	//about adding row to table in this page
	public void addRow() throws IOException {
		 model = (DefaultTableModel) table.getModel();
		 
		//if file was not emptty
		if(!NecessaryMethods.no) {
			try {
				NecessaryMethods.readFromFile();
			} catch (IOException e1) {
			}	
		
		//add information to table
		for(int i = 0 ; i < NecessaryMethods.readArrayList.size() ; i ++) {
			String name =  NecessaryMethods.readArrayList.get(i).split("    ")[0];
			String record = NecessaryMethods.readArrayList.get(i).split("    ")[1];
		model.addRow(new Object[] { name , record});
		}
		}
		//if file was empty
		else 
			errorLabel.setText("you are first Player");
			
	}
}
