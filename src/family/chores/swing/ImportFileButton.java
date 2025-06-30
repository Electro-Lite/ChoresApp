package family.chores.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportFileButton extends JButton implements ActionListener {

	public ChoresApp choresApp;

	public ImportFileButton(ChoresApp _choresApp) {
		super("Import chores");
		choresApp = _choresApp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// select file
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Chore files", "txt");
		fileChooser.setFileFilter(filter);
		int option = fileChooser.showOpenDialog(choresApp);
		if (option == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			// validate file type
			if (!selectedFile.getAbsolutePath().toLowerCase().endsWith(".chores.txt")) {
				MessageUtil.showDismissableMessage(choresApp, "Bad file type");
				return;
			}
			// add new chores
			try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
				String line;
				int choreCount = 0;
				while ((line = reader.readLine()) != null) {
					choreCount += 1;
					choresApp.choresArrList.add(Chore.newFromString(line));
				}
				MessageUtil.showDismissableMessage(choresApp, "loaded " + choreCount + " chores");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			// give info message

		}
	}
}
