package family.chores.swing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ChoresContainer extends ArrayList<Chore> implements Serializable{

	public ChoresContainer() {
		super();
	}
	
	public void store()
	{
		File file 	= new File("choresContainer.ser");
		try (FileOutputStream fileOut = new FileOutputStream(file);
	             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

	            out.writeObject(this);  // Step 2: Serialize and write object
	            System.out.println(file.getAbsolutePath());
	            System.out.println("Object has been serialized to choresContainer.ser");

	        } catch (IOException e) {
	        	System.out.println("failed to save");
	            e.printStackTrace();
	        }
	}
	
	public void load()
	{
		File file 	= new File("choresContainer.ser");
		try (FileInputStream fileIn = new FileInputStream(file);
			     ObjectInputStream in = new ObjectInputStream(fileIn)) 
		{
			ChoresContainer choresContainer = (ChoresContainer) in.readObject();
		    this.addAll(choresContainer);
		} 
		catch (IOException | ClassNotFoundException e) 
		{
		    System.out.println("file not found");
		}
	}

}
