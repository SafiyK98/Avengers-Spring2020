import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UprootGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JScrollPane displayPane = new JScrollPane();

	//Used to store the output for the player
	private JLabel display = new JLabel("<HTML>Welcome to UPROOT!</HTML>");
	
	//Used to take input from the player
	private TextField input = new TextField();
	
	private Panel buttons = new Panel();
	
	private JLabel inventoryLabel = new JLabel("<HTML>Player Inventory:</HTML>");
	
	private JLabel inventory = new JLabel("");
	
	private JMenu menu;
	
	//Used to store the image of the map
	private JLabel map;
	
	//Use to hold the map image
	private ImageIcon image;
	
	int height= 850;
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
		
		JMenuItem saveButton = new JMenuItem("Save Game");
		menu.add(saveButton);
		
		JMenuItem exitButton = new JMenuItem("Exit");
		menu.add(exitButton);
		menuBar.add(menu);
		
		

		buttons.setLayout(new GridLayout(3,4));
		buttons.setPreferredSize(new Dimension(490,(int) ((height-500)/3)));
		
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
		map.setPreferredSize(new Dimension(490, 500));
	
		
		Border border = LineBorder.createBlackLineBorder();
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setPreferredSize(new Dimension(490,(int) ((height-500)/3)));
		inventoryPanel.setBackground(Color.DARK_GRAY);
		
		
		inventoryLabel.setOpaque(true);
		inventoryLabel.setPreferredSize(new Dimension(490, 20));
		inventoryLabel.setBackground(Color.WHITE);
		inventoryLabel.setBorder(border);
		inventoryLabel.setVerticalAlignment(JLabel.TOP);
		
		inventory.setOpaque(true);
		inventory.setPreferredSize(new Dimension(500, (int) ((height-500)/3) - 20));
		inventory.setBackground(Color.WHITE);
		inventory.setBorder(border);
		inventory.setVerticalAlignment(JLabel.TOP);
		
		inventoryPanel.add(inventoryLabel);
		inventoryPanel.add(inventory);
		
		Panel sidePanel = new Panel();
		sidePanel.setPreferredSize(new Dimension(500,800));
		sidePanel.setBackground(Color.DARK_GRAY);
		sidePanel.add("North", map);
		sidePanel.add("Center", inventoryPanel);
		sidePanel.add("South", buttons);
		

		
		
		//Create the textfield box for player
		input.setPreferredSize(new Dimension(50, 50));
	
		display.setOpaque(true);
		display.setBackground(Color.WHITE);
		display.setBorder(border);
		display.setPreferredSize(new Dimension(width-520, display.getText().length()));
		display.setVerticalAlignment(JLabel.TOP);
		
		displayPane.setPreferredSize(new Dimension(width-500,900));
		displayPane.setViewportView(display);
		displayPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		displayPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollBar vertical = displayPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
		
		// create the display
		add("Center", displayPane);
		add("South", input);
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
		
		input.addActionListener(controller);
	}
	
	
	//display the new output to the player in the JLabel	
	public void setDisplay(String s){ 
		int textLength = display.getText().length()-7;
		String newText = display.getText().substring(0, textLength) + "<BR><BR>" + s + "</HTML>";
		display.setText(newText);
		display.setPreferredSize(new Dimension(width-550,(display.getText().length()/2)));
		displayPane.setPreferredSize(new Dimension(width-500, display.getHeight()));
		}
	
	//Method to clear the input textField
	public void clearInput() {
		String defaultValue = "";
		input.setText(" ");
		input.setText(defaultValue);
	}
	
	//Method to update the inventory
	public void updateInventory(ArrayList<Item> s) {
		if(s.isEmpty()) {
			return;
		}
		String newText = "<HTML>-" + s.get(0).getName();
		for(int i = 1; i<s.size(); i++) {
			newText = newText +"<BR>- " + s.get(i).getName();		 
		}
		newText = newText + "</HTML>";
		inventory.setText(newText);
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
