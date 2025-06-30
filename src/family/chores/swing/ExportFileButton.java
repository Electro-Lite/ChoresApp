package family.chores.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportFileButton extends JButton implements ActionListener {

	public ChoresApp choresApp;

	public ExportFileButton(ChoresApp _choresApp) {
		super("Export chores");
		choresApp = _choresApp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// select file
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
		fileChooser.setFileFilter(filter);
		int option = fileChooser.showSaveDialog(choresApp);
		if (option == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			// validate file name
			String path = selectedFile.getAbsolutePath();
			if (path.toLowerCase().endsWith(".chores.txt")) {
				// Pass
			} else if (path.toLowerCase().endsWith(".chores")) {
				selectedFile = new File(path + ".txt");
			} else if (path.toLowerCase().endsWith(".txt")) {
				path = path.substring(0, path.length() - 4);
				selectedFile = new File(path + ".chores.txt");
			} else {
				selectedFile = new File(path + ".chores.txt");
			}
			System.out.println(selectedFile.getName());

			// prepare string
			String txtOutput = "";

			for (Chore chore : choresApp.choresArrList) {
				if (txtOutput != "") {
					txtOutput += System.lineSeparator();
				}
					txtOutput += chore.toString();
			}
			System.out.print("content: "+txtOutput);

			// print to file
			try (FileWriter writer = new FileWriter(selectedFile)) {
				writer.write(txtOutput);
				MessageUtil.showDismissableMessage(choresApp, "file exported");
			} catch (IOException e1) {
				e1.printStackTrace();
				MessageUtil.showDismissableMessage(choresApp, "Error");
			}
		}
	}

}
