package family.chores.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MainMenu extends JPanel {
	public JPanel 		choresContainer;
	public JPanel 		uiContainer;
	public ChoresApp 	choresApp;
	public MainMenu 	mainMenu;
	
	public MainMenu(ChoresApp _choresApp)
	{
		super();
		this.mainMenu = this;
		this.choresApp 	= _choresApp;
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.setBackground(Color.DARK_GRAY);
		choresContainer = new JPanel();
		uiContainer 	= new JPanel();
		choresContainer.setLayout(new GridBagLayout());

	//Filter label
		JLabel filterLabel = new JLabel("filtered",SwingConstants.CENTER);
		filterLabel.setForeground(Color.LIGHT_GRAY);
		filterLabel.setBackground(Color.DARK_GRAY);
		filterLabel.setVisible(choresApp.filterActive);
	//UI cont
		uiContainer.setLayout(new FlowLayout());
		uiContainer.setBackground(Color.darkGray);
		
		JButton filterButton = new JButton("manage");
		filterButton.setBackground(Color.LIGHT_GRAY);
		filterButton.setForeground(Color.BLACK);
		filterButton.setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0, 0, 0, 50)), // fake shadow
			    BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding
			));
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choresApp.openFilterMenu();
			}});
		filterButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		filterButton.setVisible(true);
		uiContainer.add(filterButton);
		
		
		JButton newChore = new JButton("  New  ");
		newChore.setBackground(Color.LIGHT_GRAY);
		newChore.setForeground(Color.BLACK);
		newChore.setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0, 0, 0, 50)), // fake shadow
			    BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding
			));
		newChore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	choresApp.createChore();
            }});
		newChore.setFont(new Font("SansSerif", Font.BOLD, 18));
		newChore.setVisible(true);
		uiContainer.add(newChore);
		
		
	//Chore cont
		choresContainer.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill 	= GridBagConstraints.HORIZONTAL;
        gbc.insets 	= new Insets(10, 20, 10, 20); // top, left, bottom, right
        
        gbc.gridx 		= 0;
        gbc.gridwidth	= 1;
        gbc.gridheight	= 1;
        gbc.gridy 		= 0;
        
        Chore chore;
        for (Object tmpChore : choresApp.choresArrList) 
        {
        	chore = (Chore) tmpChore;
        	for(ActionListener a : chore.getActionListeners()) //remove listeners to avoid duplicates
        	{
        		chore.removeActionListener(a);
        	}
        	
        	chore.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                	choresApp.selectChore((Chore) tmpChore);
                }
                });
        	choresContainer.add(chore, gbc);
            gbc.gridy += 1;
        }
        
        JScrollPane pane = new JScrollPane(choresContainer);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBorder(BorderFactory.createEmptyBorder());
		pane.setPreferredSize(new Dimension(300, 400));
		
		
	//Add to this
		gbc.gridx   = 0;
		gbc.insets  = new Insets(5,5,5,5);
		gbc.fill    = GridBagConstraints.BOTH;

		// 1) filterLabel on top, minimal height
		gbc.gridy   = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;               // no extra vertical space
		add(filterLabel, gbc);

		// 2) pane in center, takes all remaining space
		gbc.gridy   = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.9;              // ~5/6 of total
		add(pane, gbc);

		// 3) uiContainer on bottom, takes about 1/6 of space
		gbc.gridy   = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;              // ~1/6 of total
		add(uiContainer, gbc);
	}
	

}
