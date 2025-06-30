package family.chores.swing;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MessageUtil {
    public static void showDismissableMessage(JFrame frame, String messageText) {
        SwingUtilities.invokeLater(() -> {
            // Create the popup message window
            JWindow message = new JWindow(frame);
            JLabel label = new JLabel(messageText, SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            label.setOpaque(true);
            label.setBackground(Color.LIGHT_GRAY);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            message.getContentPane().add(label);
            message.setSize(250, 50);
            message.setLocationRelativeTo(frame);
            message.setVisible(true);

            // Global mouse listener to dismiss the message
            MouseAdapter clickToDismiss = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    message.setVisible(false);
                    message.dispose();
                    message.setLocation(frame.getLocation());
                    frame.getGlassPane().removeMouseListener(this);
                    frame.getGlassPane().setVisible(false);
                }
            };

            // Use glass pane to catch all clicks
            frame.getGlassPane().setVisible(true);
            frame.getGlassPane().addMouseListener(clickToDismiss);
        });
    }
}
