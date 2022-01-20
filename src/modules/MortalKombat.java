package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MortalKombat 
{
	private final String[][] moveTables =
		{
				{"L R A", "L R B", "D D C", "D D L C U B", "L L L B B U", "D L U D A B"},
				{"U D C", "R R B", "D L A", "A D B U L C", "U U R R C B", "A B C L L U"},
				{"R R C", "R U A", "L D B", "D R B L B D", "R R D A U C", "R R L L U A"},
				{"L L B", "D R A", "D U C", "A A L U R B", "D U D U B B", "C U L A B D"},
				{"L L A", "L R C", "U U B", "R R R B B B", "U U D L A C", "A R B D C D"},
				{"U R A", "D L C", "R L B", "R L L R C B", "D U R B L A", "U U D L A C"},
				{"R U B", "R R A", "R D C", "L U R D C C", "R D L U A A", "U R A L U B"}
		};
	private final double r;
	public MortalKombat(double resizer)
	{
		r = resizer;
	}
	public void run()
	{
		ArrayList<String> characters = new ArrayList<String>();
		characters.add("Johnny Cage");
		characters.add("Kano");
		characters.add("Liu Kang");
		characters.add("Raiden");
		characters.add("Scorpion");
		characters.add("Sonya Blade");
		characters.add("Sub-Zero");
		
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[characters.size()];
		JButton[] jButton = new JButton[characters.size()];
		optionPane.setLayout(new GridLayout(4, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < characters.size(); aa++)
		{
			icon[aa] = new ImageIcon("img/MortalKombat" + characters.get(aa).replace(" ", "") + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, characters.get(aa), icon[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the left character:");
		dialog.setVisible(true);
		String playing = (String)optionPane.getValue();
		characters.remove(playing);
		optionPane.setLayout(new GridLayout(3, 2));
		optionPane.removeAll();
		//JButton[] temp = new JButton[jButton.length - 1];
		jButton = new JButton[characters.size()];
		frame = new JFrame();
		for(int aa = 0; aa < characters.size(); aa++)
		{
			icon[aa] = new ImageIcon("img/MortalKombat" + characters.get(aa).replace(" ", "") + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, characters.get(aa), icon[aa]);
			optionPane.add(jButton[aa]);
		}
		dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the right character:");
		dialog.setVisible(true);
	}
	private JButton getButton(final JOptionPane optionPane, String name, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(name);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
