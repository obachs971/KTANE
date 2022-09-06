package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FontSelect 
{
	private final double r;
	public FontSelect(double resizer)
	{
		r = resizer;
	}
	public void run()
	{
		String[] phrases = 
			{
				"Eight Ate 8", "888", "Ate, Ate, Ate", "8 ate eight", 
				"You are one", "Ewe Arr Won", "Yew R. Wonn", "U.R. 1", 
				"Jokes on you! I知 female.", "Jokes on you! I知 the mail.", "Jokes on you! I知 the male.", "Jokes on you! I知 male.", 
				"Testing, testing, 1-3", "Testing, testing, 1 to 3.", "Testing, testing, 1 two 3.", "Testing, testing, 123"
			};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[phrases.length];
		optionPane.setLayout(new GridLayout(8, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < phrases.length; aa++)
		{
			jButton[aa] = getButton(optionPane, phrases[aa], (aa + ""));
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the phrase:");
		dialog.setVisible(true);
		ImageIcon icon = new ImageIcon("img/FontSelect" + optionPane.getValue().toString() + ".png");
		Image image = icon.getImage();
		image = image.getScaledInstance((int)(icon.getIconWidth() / r), (int)(icon.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(label, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Submit the highest\nfont in the list");
		frame.setVisible(false);
	}
	private JButton getButton(final JOptionPane optionPane, String text, String val) {
	    final JButton button = new JButton();
	    button.setText(text);
	    button.setSize(50, 50);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(val);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
