package family.chores.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import family.chores.swing.Chore.Priority;
import family.chores.swing.Chore.Status;

public class EditChoreMenu extends JPanel implements ActionListener {
	public JPanel choresContainer;
	private static final Font labelFont = new Font("SansSerif", Font.BOLD, 18);
	public JPanel uiContainer;
	public ChoresApp choresApp;
	public Chore chore;
	private JLabel titleLabel, descriptionLabel, fullfilmentLabel, priorityLabel, assignmentLabel, dateLabel;
	private JTextField titleText, descriptionText, fulfillmentText, assignmentText, dateField;
	private JComboBox<Priority> priority;
	public boolean isNew;

	public EditChoreMenu(ChoresApp _choresApp, Chore chore, boolean isNew) {
		super();
		this.isNew = isNew;
		this.chore = chore;
		this.choresApp = _choresApp;
		choresApp.currentPage = this;
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10); // top, left, bottom, right

		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 0;

		titleLabel = new JLabel("Title");
		titleText = new JTextField(chore.getText());
		formatLabel(titleLabel);
		add(titleLabel, incYGBC(gbc));
		add(titleText, incYGBC(gbc));

		descriptionLabel = new JLabel("Description");
		descriptionText = new JTextField(chore.description);
		formatLabel(descriptionLabel);
		add(descriptionLabel, incYGBC(gbc));
		add(descriptionText, incYGBC(gbc));

		fullfilmentLabel = new JLabel("Fullfilment criteria");
		fulfillmentText = new JTextField(chore.fulfillmentCriteria);
		formatLabel(fullfilmentLabel);
		add(fullfilmentLabel, incYGBC(gbc));
		add(fulfillmentText, incYGBC(gbc));

		priorityLabel = new JLabel("Priority");
		priority = new JComboBox(Priority.values());
		priority.setSelectedItem(chore.chorePriority);
		formatLabel(priorityLabel);
		add(priorityLabel, incYGBC(gbc));
		add(priority, incYGBC(gbc));

		assignmentLabel = new JLabel("Assignment");
		assignmentText = new JTextField(chore.assignment);
		formatLabel(assignmentLabel);
		add(assignmentLabel, incYGBC(gbc));
		add(assignmentText, incYGBC(gbc));

//		dateLabel 			= new JLabel("Date");
//		dateField 			= new JTextField("mm-dd-hh", 10);
//		add(dateLabel,incYGBC(gbc));
//		add(dateField,incYGBC(gbc));

		if (isNew) {
			createNewUI(gbc);
		} else {
			createEditUI(gbc);
		}

	}

	private GridBagConstraints incYGBC(GridBagConstraints gbc) {
		gbc.gridy += 1;
		return gbc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("back".equals(e.getActionCommand())) {
			choresApp.returnToMainMenu();
		} else if ("Cancel".equals(e.getActionCommand())) {
			chore.setStatus(Status.CANCELED);
			choresApp.choresArrList.store();
			choresApp.returnToMainMenu();
		} else if ("Complete".equals(e.getActionCommand())) {
			chore.setStatus(Status.COMPLETE);
			choresApp.choresArrList.store();
			choresApp.returnToMainMenu();
		} else if ("Save".equals(e.getActionCommand())) {
			chore.title = titleText.getText();
			chore.setText(chore.title);
			chore.description = descriptionText.getText();
			chore.fulfillmentCriteria = fulfillmentText.getText();
			chore.setPriority( (Priority) priority.getSelectedItem());
			chore.assignment = assignmentText.getText();
//			chore.setDeadline(dateField.getText());
			choresApp.choresArrList.store();
			MessageUtil.showDismissableMessage(choresApp, "saved!");

		} else if ("Create".equals(e.getActionCommand())) {
			chore.title = titleText.getText();
			chore.setText(chore.title);
			chore.description = descriptionText.getText();
			chore.fulfillmentCriteria = fulfillmentText.getText();
			chore.setPriority( (Priority) priority.getSelectedItem());
			chore.assignment = assignmentText.getText();
//			chore.setDeadline(dateField.getText());
			choresApp.choresArrList.add(chore);
			MessageUtil.showDismissableMessage(choresApp, "Created!");
			choresApp.choresArrList.store();
			choresApp.returnToMainMenu();
		} else if ("Delete".equals(e.getActionCommand())) {
//			chore.setDeadline(dateField.getText());
			choresApp.choresArrList.remove(chore);
			MessageUtil.showDismissableMessage(choresApp, "Deleted!");
			choresApp.choresArrList.store();
			choresApp.returnToMainMenu();
		}
	}

	private void createNewUI(GridBagConstraints gbc) {
		// UI
		uiContainer = new JPanel();
		uiContainer.setBackground(Color.DARK_GRAY);
		add(uiContainer, incYGBC(gbc));
		uiContainer.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;

		// top ui
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("back");
		cancelButton = choresApp.formatButton(cancelButton);
		cancelButton.setVisible(true);
		gbc.gridx = 0;
		gbc.gridy = 0;
		uiContainer.add(cancelButton, gbc);

		JButton okButton = new JButton("Save");
		okButton.addActionListener(this);
		okButton.setActionCommand("Create");
		okButton = choresApp.formatButton(okButton);
		okButton.setVisible(true);
		gbc.gridx = 2;
		gbc.gridy = 0;
		uiContainer.add(okButton, gbc);
	}

	private void createEditUI(GridBagConstraints gbc) {
		// UI
		uiContainer = new JPanel();
		uiContainer.setBackground(Color.DARK_GRAY);
		add(uiContainer, incYGBC(gbc));
		uiContainer.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;

		// top ui
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("Cancel");
		cancelButton = choresApp.formatButton(cancelButton);
		cancelButton.setVisible(true);
		gbc.gridx = 0;
		gbc.gridy = 0;
		uiContainer.add(cancelButton, gbc);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(this);
		backButton.setActionCommand("back");
		backButton.setVisible(true);
		backButton = choresApp.formatButton(backButton);
		gbc.gridx = 3;
		gbc.gridy = 0;
		uiContainer.add(backButton, gbc);

		// bottom ui
		JButton completeButton = new JButton("Complete");
		completeButton.addActionListener(this);
		completeButton.setActionCommand("Complete");
		completeButton = choresApp.formatButton(completeButton);
		completeButton.setVisible(true);
		gbc.gridx = 0;
		gbc.gridy = 2;
		uiContainer.add(completeButton, gbc);

		gbc.gridx = 3;
		gbc.gridy = 2;

		JButton okButton = new JButton("Save");
		okButton.addActionListener(this);
		okButton.setActionCommand("Save");
		okButton.setVisible(true);
		okButton = choresApp.formatButton(okButton);
		uiContainer.add(okButton, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;

		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.RED);
		deleteButton.addActionListener(this);
		deleteButton.setActionCommand("Delete");
		deleteButton = choresApp.formatButton(deleteButton);
		deleteButton.setVisible(true);
		uiContainer.add(deleteButton, gbc);
	}
	private void formatLabel(JLabel label)
	{
		label.setFont(labelFont);
		label.setForeground(Color.LIGHT_GRAY);
	}
}
