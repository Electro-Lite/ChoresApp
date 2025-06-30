package family.chores.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import family.chores.swing.Chore.Priority;
import family.chores.swing.Chore.Status;

public class FilterMenu extends JPanel {

	public ChoresApp choresApp;
	private ScrollJPanel CommandsContainer = new ScrollJPanel();
	private JPanel uiContainer = new JPanel();

	public FilterMenu(ChoresApp _choresApp) {
		super();
		choresApp = _choresApp;
		choresApp.currentPage = this;
		this.setBackground(Color.DARK_GRAY);

		// Commands Container
		// Sorting
		JLabel sortLabel = new JLabel("Sort", SwingConstants.CENTER);
		sortLabel.setForeground(Color.LIGHT_GRAY);
		// Sort aplha
		JButton sortAlpha = new JButton("Sort by title");
		sortAlpha = ChoresApp.formatButton(sortAlpha);
		sortAlpha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choresApp.choresArrList.sort(Comparator.comparing(c -> ((Chore) c).getText().toLowerCase()));
				choresApp.choresArrList.store();
				MessageUtil.showDismissableMessage(choresApp, "sorting complete!");
			}
		});
		// Sort assigned
		JButton sortAssigned = new JButton("Sort by assignation");
		sortAssigned = ChoresApp.formatButton(sortAssigned);
		sortAssigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choresApp.choresArrList.sort(Comparator.comparing(c -> ((Chore) c).assignment.toLowerCase()));
				choresApp.choresArrList.store();
				MessageUtil.showDismissableMessage(choresApp, "sorting complete!");
			}
		});
		// Sort priority
		JButton sortPriority = new JButton("Sort by priority");
		sortPriority = ChoresApp.formatButton(sortPriority);
		sortPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choresApp.choresArrList.sort(Comparator.comparing(c -> ((Chore) c).chorePriority).reversed());
				choresApp.choresArrList.store();
				MessageUtil.showDismissableMessage(choresApp, "sorting complete!");
			}
		});
		// Sort status
		JButton sortStatus = new JButton("Sort by status");
		sortStatus = ChoresApp.formatButton(sortStatus);
		sortStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choresApp.choresArrList.sort(Comparator.comparing(c -> ((Chore) c).status));
				choresApp.choresArrList.store();
				MessageUtil.showDismissableMessage(choresApp, "sorting complete!");
			}
		});

		// Filter
		JLabel filterLabel = new JLabel("Filter", SwingConstants.CENTER);
		filterLabel.setForeground(Color.LIGHT_GRAY);

		// Filter priority
		JButton filterPriority = new JButton("filter by priority");
		filterPriority = ChoresApp.formatButton(filterPriority);
		filterPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priorityFilterDialog();
			}
		});
		
		// Filter by Assignment
		/*
				JButton filterPriority = new JButton("filter by priority");
				filterPriority = ChoresApp.formatButton(filterPriority);
				filterPriority.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						priorityFilterDialog();
					}
				});
		*/
		// Remove
		JLabel removeLabel = new JLabel("Delete", SwingConstants.CENTER);
		removeLabel.setForeground(Color.LIGHT_GRAY);
		// remove completed
		JButton deleteComplete = new JButton("remove completed");
		deleteComplete = ChoresApp.formatButton(deleteComplete);
		deleteComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				ArrayList<Chore> choresToDelete = new ArrayList<Chore>();
				for (Chore chore : choresApp.choresArrList) {
					if (chore.status == Status.COMPLETE) {
						choresToDelete.add(chore);
						i+=1;
					}
				}
				choresApp.choresArrList.removeAll(choresToDelete);
				choresApp.choresArrList.store();
				MessageUtil.showDismissableMessage(choresApp, "deleted "+i+" chores");
			}
		});

		// remove canceled
		JButton deleteCanceled = new JButton("remove canceled");
		deleteCanceled = ChoresApp.formatButton(deleteCanceled);
		deleteCanceled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				ArrayList<Chore> choresToDelete = new ArrayList<Chore>();
				for (Chore chore : choresApp.choresArrList) {
					if (chore.status == Status.CANCELED) {
						choresToDelete.add(chore);
						i+=1;
					}
				}
				choresApp.choresArrList.removeAll(choresToDelete);
				choresApp.choresArrList.store();
				MessageUtil.showDismissableMessage(choresApp, "deleted "+i+" chores");
			}
		});

		JButton filterReset = new JButton("reset filters");
		filterReset = ChoresApp.formatButton(filterReset);
		filterReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choresApp.choresArrList.stream().forEach(c -> c.setVisible(true));
				MessageUtil.showDismissableMessage(choresApp, "filters reseted!");
			}
		});

		// File IO
		JLabel fileLabel = new JLabel("File", SwingConstants.CENTER);
		fileLabel.setForeground(Color.LIGHT_GRAY);
		// Import File
		ImportFileButton importButton = new ImportFileButton(choresApp);
		importButton = (ImportFileButton) ChoresApp.formatButton(importButton);

		// Export File
		ExportFileButton exportButton = new ExportFileButton(choresApp);
		exportButton = (ExportFileButton) ChoresApp.formatButton(exportButton);

		// add to container
		CommandsContainer.add(sortLabel);
		CommandsContainer.add(sortAlpha);
		CommandsContainer.add(sortAssigned);
		CommandsContainer.add(sortPriority);
		CommandsContainer.add(sortStatus);
		CommandsContainer.add(filterLabel);
		CommandsContainer.add(filterPriority);
		CommandsContainer.add(filterReset);
		CommandsContainer.add(removeLabel);
		CommandsContainer.add(deleteComplete);
		CommandsContainer.add(deleteCanceled);
		CommandsContainer.add(fileLabel);
		CommandsContainer.add(importButton);
		CommandsContainer.add(exportButton);
		add(CommandsContainer.createJScrollPane());

		// ui Container
		JButton backButton = new JButton("Back");
		backButton = ChoresApp.formatButton(backButton);
		backButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choresApp.returnToMainMenu();
			}
		});
		uiContainer.setBackground(Color.darkGray);
		uiContainer.add(backButton);
		add(uiContainer);
	}

	private void priorityFilterDialog() {
		JComboBox<Priority> comboBox = new JComboBox<Priority>(Priority.values());
		comboBox.setSelectedItem(Priority.LOW);

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Hide chores below priority:");
		panel.add(label);
		panel.add(comboBox);

		JOptionPane optionPane = new JOptionPane();
		int result = JOptionPane.showConfirmDialog(choresApp, panel, "Select Option", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			Priority selected = (Priority) comboBox.getSelectedItem();
			choresApp.choresArrList.stream().filter(c -> c.chorePriority.compareTo(selected) < 0)
					.forEach(c -> c.setVisible(false));
			choresApp.filterActive = true;
			MessageUtil.showDismissableMessage(choresApp, "filtering complete!");
		}
	}
}
