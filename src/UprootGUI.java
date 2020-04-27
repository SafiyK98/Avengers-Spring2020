import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UprootGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JScrollPane displayPane = new JScrollPane();

	//Used to store the output for the player
	private JTextArea display = new JTextArea("Welcome to UPROOT!");
	
	//Used to take input from the player
	private TextField input = new TextField();
	
	private Panel buttons = new Panel();
	
	private JTextArea inventoryLabel = new JTextArea("Player Inventory:");
	
	private JTextArea inventory = new JTextArea("");
	
	private JScrollPane inventoryPane = new JScrollPane();
	
	private JButton explore = new JButton();
	
	private JMenu menu;
	
	//Used to store the image of the map
	private JLabel map;
	
	//Use to hold the map image
	private ImageIcon image;
	
	int height= 800;
	int width = 1000;
	
	
	//Creates the panel and components for the JFrame
	@SuppressWarnings("deprecation")
	public UprootGUI(){
		super("UPROOT");
		this.setSize(width, height);
		
		//create the menuBar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("Menu");
		
		JMenuItem startNewButton = new JMenuItem("Start New Game");
		menu.add(startNewButton);
		
		JMenuItem loadGame = new JMenuItem("Load Saved Game");
		menu.add(loadGame);
		
		JMenuItem saveButton = new JMenuItem("Save Game");
		menu.add(saveButton);
		
		JMenuItem exitButton = new JMenuItem("Exit");
		menu.add(exitButton);
		menuBar.add(menu);
		
		

		buttons.setLayout(new GridLayout(3,4));
		buttons.setPreferredSize(new Dimension(440,(int) ((height-450)/3)));
		
		String[] buttonStrings = {
				"Up", "s", "North", "s",
				"s", "West", "s", "East",
				"Down", "s", "South", "s"
		};
		
		for (String s: buttonStrings) {
			JButton button = new JButton(s);
			if(s.equalsIgnoreCase("s")) {
				button = new JButton(" ");
				button.disable();
			}
			button.setBackground(Color.WHITE);
			buttons.add(button);

		}
		
		File f = new File("/Users/sophiehoare/Desktop/Avengers/Avengers-Spring2020/Floor1.png");
		
		image = new ImageIcon(f.getName());
		map = new JLabel(image);
		map.setPreferredSize(new Dimension(440, 450));
	
		
		Border border = LineBorder.createBlackLineBorder();
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setPreferredSize(new Dimension(440,(int) ((height-450)/3)));
		inventoryPanel.setBackground(Color.DARK_GRAY);
		
		
		inventoryLabel.setOpaque(true);
		inventoryLabel.setPreferredSize(new Dimension(440, 20));
		inventoryLabel.setBackground(Color.WHITE);
		inventoryLabel.setBorder(border);
		
		inventoryPane.setPreferredSize(new Dimension(450, (int) ((height-450)/3) - 20));
		inventoryPane.setViewportView(inventory);
		inventoryPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		inventoryPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		inventory.setEditable(false);
		inventory.setLineWrap(true);
		inventory.setWrapStyleWord(true);
		inventory.setOpaque(true);
		inventory.setAutoscrolls(true);
		
		inventoryPanel.add(inventoryLabel);
		inventoryPanel.add(inventoryPane);
		
		Panel sidePanel = new Panel();
		sidePanel.setPreferredSize(new Dimension(450,800));
		sidePanel.setBackground(Color.DARK_GRAY);
		sidePanel.add("North", map);
		sidePanel.add("Center", inventoryPanel);
		sidePanel.add("South", buttons);
		
		explore = new JButton("Explore");
		explore.setBackground(Color.WHITE);
		explore.setPreferredSize(new Dimension(100,40));
		
		Panel inputPanel = new Panel();
		inputPanel.setPreferredSize(new Dimension(1000,50));
		inputPanel.setBackground(Color.WHITE);
		
		input.setPreferredSize(new Dimension(880,40));
		
		//Create the textfield box for player
		inputPanel.setPreferredSize(new Dimension(800, 50));
		FlowLayout flow = new FlowLayout();
		inputPanel.setLayout(flow);
		inputPanel.add(input);
		inputPanel.add(explore);
	

		
		displayPane.setPreferredSize(new Dimension(width-450,900));
		displayPane.setViewportView(display);
		displayPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		displayPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		display.setEditable(false);
		display.setLineWrap(true);
		display.setWrapStyleWord(true);
		display.setOpaque(true);
		display.setAutoscrolls(true);
		
		JScrollBar vertical = displayPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() - 1 );
		
		
		// create the display
		add("Center", displayPane);
		add("South", inputPanel);
		add("East", sidePanel);
		
		input.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			 
			@Override
			public void keyPressed(KeyEvent e) {;
			} 
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					clearInput();
				}
			}
		});

	
		
	}
	
	
	//register the controller as the listener to the menu items, buttons and textfield
	public void registerListener(UprootController controller) {
		Component[] components = buttons.getComponents(); 
		for (Component component : components) {
			if (component instanceof JButton) {
				JButton button = (JButton) component;
				if (button.getText().equals(" ")){
					continue;
				}
				else {
					button.addActionListener(controller);
				}
			}
		}
		
		components = menu.getMenuComponents();
		for (Component component : components) {
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.addActionListener(controller);
			}
		}
		
		input.addActionListener(controller);
		explore.addActionListener(controller);
	}
	
	
	//display the new output to the player in the JLabel	
	public void setDisplay(String s){ 
		String newText = display.getText()+ "\n \n" + s;
		display.setText(newText);
		displayPane.setPreferredSize(new Dimension(width-450, display.getHeight()));
		}
	
	//Method to clear the input textField
	public void clearInput() {
		String defaultValue = "";
		input.setText(" ");
		input.setText(defaultValue);
	}
	
	//Method to update the inventory
	public void updateInventory(ArrayList<Item> s, ArrayList<Equipable> eq) {
		if(s.isEmpty()) {
			return;
		}
		String newText = "";
		for(int i = 0; i<s.size(); i++) {
			if(eq.contains(s.get(i)))
				newText = newText + " \n - " + s.get(i).getName() + " (E)";
			else
				newText = newText +" \n - " + s.get(i).getName();		 
		}
		inventory.setText(newText);
	}
	
	//Method to start next game
	public void startNewGame() {
		display.setText("");
		displayPane.setPreferredSize(new Dimension(width-500, display.getHeight()));
	}
	
	//Method to update the map image for the GUI
	public void updateImage(int level) {
		File f = new File("/Users/sophiehoare/Desktop/Avengers/Avengers-Spring2020/Floor"+level+".png");
		image = new ImageIcon(f.getName());
		map.setIcon(image);
	}
	
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
	
	
}
