import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UprootGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Used to store the output for the player
	private JLabel display = new JLabel("<HTML>Welcome to UPROOT!</HTML>");
	
	//Used to take input from the player
	private TextField input = new TextField();
	
	private Panel buttons = new Panel();
	
	private JLabel inventory = new JLabel("<HTML>Player Inventory:</HTML>");
	
	private JMenu menu;
	
	//private ImageIcon map;
	private Component map;


	
	
	//Creates the panel and components for the JFrame
	@SuppressWarnings("deprecation")
	public UprootGUI(){
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
		
		

		buttons.setLayout(new GridLayout(3,4));
		buttons.setPreferredSize(new Dimension(490,205));
		
		String[] buttonStrings = {
				"UP", "s", "^", "s",
				"s", "<", "s", ">",
				"DOWN", "s", "v", "s"
		};
		
		for (String s: buttonStrings) {
			Button button = new Button(s);
			if(s.equalsIgnoreCase("s")) {
				button = new Button(" ");
				button.disable();
			}
			button.setBackground(Color.DARK_GRAY);
			buttons.add(button);

		}
		
		File f = new File("/Users/sophiehoare/Desktop/Avengers/Avengers-Spring2020/Floor1.png");
		
		JLabel floor1 = new JLabel(new ImageIcon(f.getName()));
		map = floor1;
		map.setPreferredSize(new Dimension(490,500));
	
		
		Border border = LineBorder.createBlackLineBorder();
		
		inventory.setOpaque(true);
		inventory.setPreferredSize(new Dimension(490,230));
		inventory.setBackground(Color.WHITE);
		inventory.setAlignmentY(TOP_ALIGNMENT);
		inventory.setBorder(border);
		inventory.setVerticalTextPosition(JLabel.TOP);
		
		Panel sidePanel = new Panel();
		sidePanel.setPreferredSize(new Dimension(500,800));
		sidePanel.setBackground(Color.DARK_GRAY);
		sidePanel.add("North", map);
		sidePanel.add("Center", inventory);
		sidePanel.add("South", buttons);
		

		
		
		//Create the textfield box for player
		input.setPreferredSize(new Dimension(100, 100));
	
		display.setOpaque(true);
		display.setBackground(Color.WHITE);
		display.setPreferredSize(new Dimension(1000,1000));
		display.setBorder(border);
		display.setVerticalTextPosition(JLabel.TOP);
		
		// create the display
		add("Center", display);
		add("South", input);
		add("East", sidePanel);
		
	
		
	}
	
	
	//register the controller as the listener to the menu items, buttons and textfield
	public void registerListener(UprootController controller) {
		Component[] components = buttons.getComponents(); 
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
		
		input.addActionListener(controller);
	}
	
	
	//display the new output to the player in the JLabel	
	public void setDisplay(String s){ 
		int textLength = display.getText().length()-7;
		display.setText(display.getText().substring(0, textLength) + "<BR><BR>" + s + "</HTML>");
		}
	public String getDisplay()
	{	return display.getText();
	}
	
	public void addToInventory(String s) {
		int textLength = inventory.getText().length()-7;
		inventory.setText(inventory.getText().substring(0, textLength) + "<BR>- " + s + "</HTML>");
	}
	
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
	
	
}
