package family.chores.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollJPanel extends JPanel {

	GridBagConstraints gbc;

	public ScrollJPanel() 
	{
		setLayout(new GridBagLayout());
		setBackground(Color.DARK_GRAY);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 20, 10, 20); // top, left, bottom, right

		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 0;
	}

	public JScrollPane createJScrollPane() 
	{
		JScrollPane pane = new JScrollPane(this);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBorder(BorderFactory.createEmptyBorder());
		pane.setPreferredSize(new Dimension(300, 400));
		return pane;
	}

	public void add(JLabel label) 
	{
		gbc.insets = new Insets(5, 20, 0, 20); // top, left, bottom, right
		this.add(label, gbc);
		gbc.insets = new Insets(10, 20, 10, 20); // top, left, bottom, right
		gbc.gridy += 1;
	}
	public void add(JComponent component) 
	{
		this.add(component, gbc);
		gbc.gridy += 1;
	}

}
