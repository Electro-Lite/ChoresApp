package family.chores.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChoresApp  extends JFrame{
	public static void main(String[] args) 
	{
		ChoresApp app = new ChoresApp();
	}
	
	public ChoresContainer 		choresArrList 	= new ChoresContainer();
	public ArrayList			familyArrList 	= new ArrayList();
	public MainMenu 			mainMenu;
	public EditChoreMenu 		editChoreMenu;
	public JPanel				currentPage;
	public FilterMenu 			filterMenu		= null;
	public Chore 				currentChore 	= null;
	public boolean				filterActive;
	
	public ChoresApp() 
	{
		super("Chores");
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/family/chores/swing/icons/choresAppIcon.png"));
		setIconImage(icon);
		choresArrList.load();
		choresArrList.stream().forEach(c -> c.setVisible(true));
		filterActive = false;
		
		mainMenu = new MainMenu(this);
		add(mainMenu);
		setLayout(new CardLayout());
		this.setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(108*3,192*3);
        setVisible(true);
        
	}

	public void selectChore(Chore chore)
	{		
		editChoreMenu = new EditChoreMenu(this, chore, false);
		this.add(editChoreMenu);
		
		mainMenu.setVisible(false);
		remove(mainMenu);
		mainMenu = null;
		
        revalidate();           // Recalculate layout
        repaint();              // Redraw the UI
	}
	public void createChore()
	{		
		editChoreMenu = new EditChoreMenu(this, new Chore(), true);
		this.add(editChoreMenu);
		
		mainMenu.setVisible(false);
		remove(mainMenu);
		mainMenu = null;
		
        revalidate();           // Recalculate layout
        repaint();              // Redraw the UI
	}

	private void recreateChoreMenu()
	{
		mainMenu.setVisible(false);
		remove(mainMenu);
		mainMenu = null;
		
		mainMenu = new MainMenu(this);
		mainMenu.setVisible(true);
		add(mainMenu);
		
        revalidate();           // Recalculate layout
        repaint();              // Redraw the UI
	}
	public void openFilterMenu()
	{
		mainMenu.setVisible(false);
		remove(mainMenu);
		mainMenu = null;
		
		filterMenu = new FilterMenu(this);
		filterMenu.setVisible(true);
		add(filterMenu);
		
        revalidate();           // Recalculate layout
        repaint();    
	}

	public void returnToMainMenu()
	{
		mainMenu = new MainMenu(this);
		mainMenu.setVisible(true);
		add(mainMenu);
		
		currentPage.setVisible(false);
		remove(currentPage);
		currentPage = mainMenu;
		
        revalidate();           // Recalculate layout
        repaint();              // Redraw the UI
	}
	public static JButton formatButton(JButton button)
	{
		button.setVisible(true);
		button.setBackground(Color.LIGHT_GRAY);
		button.setForeground(Color.BLACK);
		button.setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0, 0, 0, 50)), // fake shadow
			    BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding
			));
		return button;
	}
}
