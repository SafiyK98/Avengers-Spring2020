import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


public class UprootGUI extends JFrame{
	
	//Used to store the output for the player
	private JLabel displayText;
	
	//Used to take input from the player
	private JTextField playerInput;
	
	//Menu buttons for directions
	private JPanel buttonsPanel; 
	
	private JMenu menu;
	
	private ImageIcon map;

	
	
	//Creates the panel and components for the JFrame
	public UprootGUI() {
		super("UPROOT");

		
		//create the menuBar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("Menu");
		
		JMenuItem startNewButton = new JMenuItem("Start New Game");
		menu.add(startNewButton);
		
		JMenuItem saveButton = new JMenuItem("Save Game");
		menu.add(saveButton);
		
		JMenuItem exitButton = new JMenuItem("Exit");
		menu.add(exitButton);
		menuBar.add(menu);
		
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3,5));
		buttonsPanel.setSize(200, 200);
		
		String[] buttonStrings = {
				"UP"," ", " ", "^", " ",
				" "," ", "<", " ", ">",
				"DOWN"," ", " ", "v", " "
		};
		
		for (String s: buttonStrings) {
			if(!s.equals(""))
				buttonsPanel.add(new JButton(s));
		}
		
		

		
		//Create the textfield box for player
		playerInput = new JTextField(100);
		playerInput.setSize(100, 200);
	
		
		// create the display
		JPanel displayPanel = new JPanel();
		displayText = new JLabel("Welcome to UPROOT!");
		displayPanel.add(displayText);
		displayPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		displayPanel.setSize(300, 200);
	
		
		
		
		//create the direction buttons
		buttonsPanel = new JPanel();
		
		//add image of map to top right corner
//		map = new ImageIcon("/Users/sophiehoare/Desktop/Avengers/Avengers-Spring2020/Floor0.png");
//		buttonsPanel.add(new JLabel(map), BorderLayout.NORTH);
		
		
		add(displayPanel);
		add(buttonsPanel);
		add(playerInput);
		
	}
	
	
	//register the controller as the listener to the menu items, buttons and textfield
	public void registerListener(UprootController controller) {
		Component[] components = buttonsPanel.getComponents(); 
		for (Component component : components) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(controller);
			}
		}
		
		components = menu.getMenuComponents();
		for (Component component : components) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(controller);
			}
		}
		
		playerInput.addActionListener(controller);
		
	}
	
	
	//display the new output to the player in the JLabel
	public void updateText(String value) {
		displayText.setText(displayText + "\n" + value);
	}
	
	//change the image of the map when the player changes floor
//	public void updateImage(int level) {
//		if (level == 1) {
//			map = new ImageIcon("addpath to image");
//			displayPanel.add(map, BorderLayout.NORTHEAST);
//		}
//		
//		if (level == 2) {
//			map = new ImageIcon("addpath to image");
//			displayPanel.add(map, BorderLayout.NORTHEAST);
//		}
//		
//		if (level == 3) {
//			map = new ImageIcon("addpath to image");
//			displayPanel.add(map, BorderLayout.NORTHEAST);
//		}
//	}
	
}
