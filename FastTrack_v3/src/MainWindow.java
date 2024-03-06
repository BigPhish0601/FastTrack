import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Dimension;

import java.util.LinkedList;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.WorldMapLayer;
import gov.nasa.worldwind.render.Polyline;

import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.media.nativewindow.WindowClosingProtocol.WindowClosingMode;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class MainWindow {

	private JFrame mainFrame;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JTextField tfSearch;
	private JPanel loginPanel;
	private JPanel searchPanel;
	private JPanel findPanel;
	private JPanel deviceInfoPanel;
	private JPanel addDevicePanel;
	//private WorldWindowGLCanvas world;
	
	private User adminUser;
	private Device adminDevice;

	// background images
	private Image loginImg;
	private Image searchImage;
	private Image bagImage;
	
	// image icons
	private Icon loginIcon;
	private Icon bagIcon;
	private Icon searchIcon;
	
	private JTextField tfDeviceNumber;
	private JTextField tfOwnersFirstName;
	private JTextField tfOwnersLastName;
	private JTextField tfOwnersPhone;
	private JTextField tfBagNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}// end main

	/**
	 * Frame constructor
	 */
	public MainWindow() {
		// try to load the login image from the file path
		try {
			loginImg = ImageIO.read(new File("loginPic.jpg"));
		} catch (IOException ex) {// catch IO exceptions
			System.err.println("Image not found");
			System.exit(1);
		} // end try
		loginIcon = new ImageIcon(loginImg);
		
		// try to load the bag image from the file path
		try {
			bagImage = ImageIO.read(new File("golfBagPic.jpg"));
		} catch (IOException ex) {// catch IO exceptions
			System.err.println("Image not found");
			System.exit(1);
		} // end try
		//ad the image to the icon		
		bagIcon = new ImageIcon(bagImage);
		
		// try to load the bag image from the file path
		try {
			searchImage = ImageIO.read(new File("golf image.gif"));
		} catch (IOException ex) {// catch IO exceptions
			System.err.println("Image not found");
			System.exit(1);
		} // end try
		//ad the image to the icon		
		searchIcon = new ImageIcon(searchImage);
		// create a new user
		adminUser = new User("admin", "admin");
		
		//create a new device
		adminDevice = new Device( 001, 001, "John", "Smith");
		
		initialize();
		
	}// end constructor

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Fast Track User Interface");
		mainFrame.setBounds(100, 100, 588, 351);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new CardLayout(0, 0));
		mainFrame.setLocationRelativeTo(null);

		loginPanel = new JPanel();
		loginPanel.setBackground(Color.LIGHT_GRAY);
		loginPanel.setSize(480, 300);
		mainFrame.getContentPane().add(loginPanel, "name_64990862466658");
		mainFrame.setBounds(loginPanel.getBounds());
		loginPanel.setLayout(null);
		loginPanel.setVisible(true);

		searchPanel = new JPanel();
		searchPanel.setSize(700, 500);
		mainFrame.getContentPane().add(searchPanel, "name_64992942419921");
		searchPanel.setLayout(null);
		searchPanel.setVisible(false);

		findPanel = new JPanel();
		mainFrame.getContentPane().add(findPanel, "name_64995373782778");
		findPanel.setLayout(null);
		findPanel.setVisible(false);
		//world = new WorldWindowGLCanvas();
		
		
		deviceInfoPanel = new JPanel();
		mainFrame.getContentPane().add(deviceInfoPanel, "name_64997640354417");
		deviceInfoPanel.setLayout(null);
		deviceInfoPanel.setVisible(false);
		
		addDevicePanel = new JPanel();
		mainFrame.getContentPane().add(addDevicePanel, "name_74438332129471");
		addDevicePanel.setLayout(null);
		
		JLabel label = new JLabel("Welcome To Fast Track");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 16, 420, 20);
		loginPanel.add(label);

		JLabel label_1 = new JLabel("Please sign in with your username and password");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 41, 446, 20);
		loginPanel.add(label_1);

		JLabel label_2 = new JLabel("Username:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setForeground(Color.WHITE);
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(58, 77, 115, 20);
		loginPanel.add(label_2);

		JLabel label_3 = new JLabel("Password:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setForeground(Color.WHITE);
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(68, 125, 105, 20);
		loginPanel.add(label_3);

		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(188, 74, 146, 26);
		loginPanel.add(tfUsername);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(188, 122, 146, 26);
		loginPanel.add(pfPassword);

		//login panel components
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if the text typed in the username and password textfields
				// matches any of the users, then log that user in else
				// show an error message
				if (tfUsername.getText().equals(adminUser.getUserName())
						&& pfPassword.getText().equals(adminUser.getPassword())) {
					loginPanel.setVisible(false);
					searchPanel.setVisible(true);
					mainFrame.setSize(700, 500);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid user name or password please try again", "Login Error",
							JOptionPane.ERROR_MESSAGE);
					// reset user name and password
					tfUsername.setText("");
					pfPassword.setText("");
				}
			}
		});
		btnLogin.setBounds(15, 190, 115, 29);
		loginPanel.add(btnLogin);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(316, 190, 115, 29);
		loginPanel.add(btnCancel);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfUsername.setText("");
				pfPassword.setText("");

			}
		});
		btnClear.setBounds(168, 190, 115, 29);
		loginPanel.add(btnClear);
		
		JLabel lblLoginBackground = new JLabel(loginIcon);
		lblLoginBackground.setLocation(0, 0);
		lblLoginBackground.setSize(loginPanel.getSize());
		loginPanel.add(lblLoginBackground);
		
		JLabel lblsearchLabel = new JLabel("Select the device you are"
									+ " searching for from the list below.");
		lblsearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblsearchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblsearchLabel.setBounds(14, 16, 616, 20);
		searchPanel.add(lblsearchLabel);

		JButton btnFind = new JButton("Find Selected Bag");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPanel.setVisible(false);
				findPanel.setVisible(true);
				mainFrame.setSize(700, 600);
				
			}
		});
		btnFind.setBounds(346, 246, 179, 29);
		searchPanel.add(btnFind);

		JButton btnSortName = new JButton("Sort by Last Name");
		btnSortName.setBounds(346, 66, 179, 29);
		searchPanel.add(btnSortName);

		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		tfSearch.setBounds(15, 67, 186, 26);
		searchPanel.add(tfSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSearch.setBounds(216, 66, 115, 29);
		searchPanel.add(btnSearch);

		JButton btnSortID = new JButton("Sort by Tracker ID");
		btnSortID.setBounds(346, 111, 179, 29);
		searchPanel.add(btnSortID);

		JButton btnAdd = new JButton("Add A Tracker");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPanel.setVisible(false);
				addDevicePanel.setVisible(true);
				
			}
		});
		btnAdd.setBounds(346, 156, 180, 29);
		searchPanel.add(btnAdd);

		JButton btnRemove = new JButton("Remove a Tracker");
		btnRemove.setBounds(346, 201, 179, 29);
		searchPanel.add(btnRemove);

		JScrollPane SpDeviceList = new JScrollPane();
		SpDeviceList.setBounds(14, 109, 314, 166);
		searchPanel.add(SpDeviceList);

		JList<String> listDevice = new JList<String>();
		listDevice.setModel(new AbstractListModel() {
			String[] values = new String[] {"Device #0001", "Device #0002", "Device #0003", "Device #0004", "Device #0005", "Device #0006", "Device #0007", "Device #0008", "Device #0009", "Device #0010"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		SpDeviceList.setViewportView(listDevice);
		
		//search panel components
		JLabel lblSearchBackground = new JLabel(searchIcon);
		lblSearchBackground.setSize(searchPanel.getSize());
		searchPanel.add(lblSearchBackground);

		// find panel components
		JLabel lblFindLabel = new JLabel("The Location of the Bag is shown on the map");
		lblFindLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblFindLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFindLabel.setBounds(62, 16, 540, 20);
		findPanel.add(lblFindLabel);

		JLabel lblFindLabel2 = new JLabel("Use buttons below for more options");
		lblFindLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblFindLabel2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFindLabel2.setBounds(62, 48, 540, 20);
		findPanel.add(lblFindLabel2);

		JButton btnAlarmOff = new JButton("Turn Off Alarm");
		btnAlarmOff.setBounds(15, 460, 198, 29);
		findPanel.add(btnAlarmOff);

		JButton btnAlarmOn = new JButton("Sound Device Alarm");
		btnAlarmOn.setBounds(15, 415, 198, 29);
		findPanel.add(btnAlarmOn);

		JButton btnBack = new JButton("Track A Different Bag");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findPanel.setVisible(false);
				searchPanel.setVisible(true);
			}
		});
		btnBack.setBounds(424, 415, 198, 29);
		findPanel.add(btnBack);

		JButton btnDeviceInfo = new JButton("Show Device Info");
		btnDeviceInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findPanel.setVisible(false);
				deviceInfoPanel.setVisible(true);
				
			}
		});
		btnDeviceInfo.setBounds(424, 460, 198, 29);
		findPanel.add(btnDeviceInfo);
		
		 // Create the WorldWindow and set its preferred size.
		Dimension worldSize = new Dimension();
		WorldWindowGLCanvas world = new WorldWindowGLCanvas();
		world.setPreferredSize(worldSize);
		
		// Create the default model as described in the current worldwind properties.
		Model m = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
		world.setModel(m);
		
		 // Setup a select listener for the worldmap click-and-go feature
		world.addSelectListener(new ClickAndGoSelectListener(world, WorldMapLayer.class));
		world.setBounds(15, 80, 640, 400);
		
		  // Add the WorldWindow to findPanel
		findPanel.add(world);
		
		
		//device info panel components
		JLabel lblTheBagInformation = new JLabel("The Bag Information is shown below");
		lblTheBagInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheBagInformation.setBounds(0, 16, 443, 20);
		deviceInfoPanel.add(lblTheBagInformation);

		JButton btnBackToMap = new JButton("Back To Map");
		btnBackToMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deviceInfoPanel.setVisible(false);
				findPanel.setVisible(true);
			}
		});
		btnBackToMap.setBounds(203, 453, 145, 29);
		deviceInfoPanel.add(btnBackToMap);
		
		JLabel lblBagOwner = new JLabel("Bag Owner:");
		lblBagOwner.setBounds(303, 120, 114, 20);
		deviceInfoPanel.add(lblBagOwner);
		
		JLabel lblDeviceNumber = new JLabel("Bag Device Number:");
		lblDeviceNumber.setBounds(303, 53, 145, 20);
		deviceInfoPanel.add(lblDeviceNumber);
		
		JLabel lblLocation = new JLabel("Tracker Coordninates\r\n:");
		lblLocation.setBounds(303, 192, 170, 20);
		deviceInfoPanel.add(lblLocation);
		
		JLabel lblBagImage = new JLabel(bagIcon);
		lblBagImage.setBounds(10, 52, 276, 301);
		deviceInfoPanel.add(lblBagImage);
		
		JLabel lblNumber = new JLabel("0001");
		lblNumber.setBounds(313, 84, 189, 20);
		deviceInfoPanel.add(lblNumber);
		
		JLabel lblName = new JLabel(adminDevice.getFirstName() + " " + adminDevice.getLastName());
		lblName.setBounds(313, 156, 189, 20);
		deviceInfoPanel.add(lblName);
		
		JLabel lblN = new JLabel("N 26\u00B0 21' 6.077'' W 81\u00B0 48' 49.87''");
		lblN.setBounds(313, 228, 268, 20);
		deviceInfoPanel.add(lblN);
		
		
		// add device panel components
		JLabel lblDeviceNumber_1 = new JLabel("Device Number");
		lblDeviceNumber_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDeviceNumber_1.setBounds(78, 76, 167, 20);
		addDevicePanel.add(lblDeviceNumber_1);
		
		JLabel lblBagOwnersFirst = new JLabel("Bag Owners First Name");
		lblBagOwnersFirst.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBagOwnersFirst.setBounds(64, 125, 181, 20);
		addDevicePanel.add(lblBagOwnersFirst);
		
		JLabel lblBagOwnersLast = new JLabel("Bag Owner's Last Name");
		lblBagOwnersLast.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBagOwnersLast.setBounds(64, 182, 181, 20);
		addDevicePanel.add(lblBagOwnersLast);
		
		JLabel lblBagNumber = new JLabel("Bag Number");
		lblBagNumber.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBagNumber.setBounds(64, 293, 181, 20);
		addDevicePanel.add(lblBagNumber);
		
		JLabel lblOwnersPhoneNumber = new JLabel("Owner's Phone Number");
		lblOwnersPhoneNumber.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOwnersPhoneNumber.setBounds(64, 239, 181, 20);
		addDevicePanel.add(lblOwnersPhoneNumber);
		
		JLabel lblFillInThe = new JLabel("Fill in the boxes below then click "
									+ "add to add the new bag to the bag list");
		lblFillInThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblFillInThe.setBounds(15, 16, 540, 20);
		addDevicePanel.add(lblFillInThe);
		
		tfDeviceNumber = new JTextField();
		tfDeviceNumber.setBounds(275, 73, 181, 20);
		addDevicePanel.add(tfDeviceNumber);
		tfDeviceNumber.setColumns(10);
		
		tfOwnersFirstName = new JTextField();
		tfOwnersFirstName.setColumns(10);
		tfOwnersFirstName.setBounds(275, 122, 181, 20);
		addDevicePanel.add(tfOwnersFirstName);
		
		tfOwnersLastName = new JTextField();
		tfOwnersLastName.setColumns(10);
		tfOwnersLastName.setBounds(275, 179, 181, 20);
		addDevicePanel.add(tfOwnersLastName);
		
		tfOwnersPhone = new JTextField();
		tfOwnersPhone.setColumns(10);
		tfOwnersPhone.setBounds(275, 236, 181, 20);
		addDevicePanel.add(tfOwnersPhone);
		
		tfBagNumber = new JTextField();
		tfBagNumber.setColumns(10);
		tfBagNumber.setBounds(275, 290, 181, 20);
		addDevicePanel.add(tfBagNumber);
		
		JButton btnAddBack = new JButton("Back");
		btnAddBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDevicePanel.setVisible(false);
				searchPanel.setVisible(true);
			}
		});
		btnAddBack.setBounds(15, 363, 115, 29);
		addDevicePanel.add(btnAddBack);
		
		JButton btnAddBag = new JButton("Add Bag");
		btnAddBag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDevicePanel.setVisible(false);
				searchPanel.setVisible(true);
			}
		});
		btnAddBag.setBounds(440, 363, 115, 29);
		addDevicePanel.add(btnAddBag);
		
		JButton btnAddClear = new JButton("Clear Fields");
		btnAddClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfDeviceNumber.setText("");
				tfOwnersFirstName.setText("");
				tfOwnersLastName.setText("");
				tfOwnersPhone.setText("");
				tfBagNumber.setText("");
			}
		});
		btnAddClear.setBounds(228, 363, 115, 29);
		addDevicePanel.add(btnAddClear);
	}
}
