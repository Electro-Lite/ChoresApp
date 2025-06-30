package family.chores.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Chore extends JButton {
	public enum Priority {
		LOW, MEDIUM, HIGH, URGENT
	}
	public enum Status {
		PENDING, COMPLETE, CANCELED
	}

	public 	String 			title, description, fulfillmentCriteria, assignment;
	public 	LocalDateTime 	deadline;
	public 	Priority 		chorePriority;
	public 	Status 			status;

	public Chore() {
		this("new Chore");
		setStatus(Status.PENDING);
	}

	public Chore(String title) {
		super(title);

		this.setBounds(0, 0, 60, 30);
		this.setVisible(true);
		this.setBackground(Color.LIGHT_GRAY);
		this.setForeground(Color.BLACK);
		this.setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createMatteBorder(0, 0, 4, 4, new Color(0, 0, 0, 50)), // fake shadow
			    BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding
			));
		this.chorePriority = Priority.LOW;
	}
	public void setDeadline(String deadLineText)
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
		 deadline = LocalDateTime.parse(deadLineText,formatter);
	}
	public void setStatus(Status _status)
	{
		status = _status;
		Font font 		= getFont();
		Map attributes 	= font.getAttributes();
		switch(status)
		{
			case PENDING:
				attributes.put(TextAttribute.STRIKETHROUGH, false);
				setBackground(Color.lightGray);
				break;
			case COMPLETE:
				attributes.put(TextAttribute.STRIKETHROUGH, false);
				setBackground( new Color(60, 100, 60));
				break;
			case CANCELED:
				attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
				setBackground(new Color(100, 100, 100));
				break;
		}
		setFont(font.deriveFont(attributes));
	}
	public void setPriority(Priority _priority)
	{
		chorePriority = _priority;
		switch(_priority)
		{
			case LOW:
				this.setBorder(BorderFactory.createCompoundBorder(
					    BorderFactory.createMatteBorder(0, 0, 4, 4, new Color(0, 0, 0, 50)), // fake shadow
					    BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding
					));
				break;
			case MEDIUM:
				this.setBorder(BorderFactory.createCompoundBorder(
					    BorderFactory.createMatteBorder(0, 4, 0, 0, Color.YELLOW), // red left + black bottom/right fake shadow
					    BorderFactory.createCompoundBorder(
					        BorderFactory.createMatteBorder(0, 0, 4, 4, new Color(0, 0, 0, 50)), // right fake shadow
					        BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding
					    )
					));
				break;
			case HIGH:
				this.setBorder(BorderFactory.createCompoundBorder(
					    BorderFactory.createMatteBorder(0, 4, 0, 0, Color.ORANGE), // red left + black bottom/right fake shadow
					    BorderFactory.createCompoundBorder(
					        BorderFactory.createMatteBorder(0, 0, 4, 4, new Color(0, 0, 0, 50)), // right fake shadow
					        BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding
					    )
					));
				break;
			case URGENT:
				this.setBorder(BorderFactory.createCompoundBorder(
					    BorderFactory.createMatteBorder(0, 4, 0, 0, Color.RED), // red left + black bottom/right fake shadow
					    BorderFactory.createCompoundBorder(
					        BorderFactory.createMatteBorder(0, 0, 4, 4, new Color(0, 0, 0, 50)), // right fake shadow
					        BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding
					    )
					));
				break;
		}
	}
	@Override
    public String toString() {
		String strOut = "";
		
		strOut+=	  title.replace(";", "");
		strOut+=";" + description.replace(";", "");
		strOut+=";" + fulfillmentCriteria.replace(";", "");
		strOut+=";" + assignment.replace(";", "");
		strOut+=";" + chorePriority;
		strOut+=";" + status;
		
        return strOut;
    }
	public static Chore newFromString(String csvStr) {
		String strOut = "";
		
		String[] parts = csvStr.split(";", 6);
        if (parts.length != 6) {
            throw new IllegalArgumentException(
                "Invalid input, expected 6 segments but got " + parts.length);
        }
        Chore chore = new Chore();
        chore.title               = parts[0]; chore.setText(chore.title);
        chore.description         = parts[1];
        chore.fulfillmentCriteria = parts[2];
        chore.assignment          = parts[3];
        // if these are enums, convert from their name():
        chore.setPriority( Priority.valueOf(parts[4]));
        chore.setStatus(   Status.valueOf(parts[5]));
        return chore;
    }
}
