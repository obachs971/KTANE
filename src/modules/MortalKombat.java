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

import start.BombEdgework;

public class MortalKombat 
{
	private final String[][] moveTables =
		{
				{"◀ ▶ A", "◀ ▶ B", "▼ ▼ C", "▼ ▼ ◀ C ▲ B", "◀ ◀ ◀ B B ▲", "▼ ◀ ▲ ▼ A B", "132", "213", "321", "231", "123", "312"},
				{"▲ ▼ C", "▶ ▶ B", "▼ ◀ A", "A ▼ B ▲ ◀ C", "▲ ▲ ▶ ▶ C B", "A B C ◀ ◀ ▲", "312", "213", "123", "321", "231", "132"},
				{"▶ ▶ C", "▶ ▲ A", "◀ ▼ B", "▼ ▶ B ◀ B ▼", "▶ ▶ ▼ A ▲ C", "▶ ▶ ◀ ◀ ▲ A", "231", "123", "312", "132", "213", "321"},
				{"◀ ◀ B", "▼ ▶ A", "▼ ▲ C", "A A ◀ ▲ ▶ B", "▼ ▲ ▼ ▲ B B", "C ▲ ◀ A B ▼", "321", "231", "132", "312", "213", "123"},
				{"◀ ◀ A", "◀ ▶ C", "▲ ▲ B", "▶ ▶ ▶ B B B", "▲ ▲ ▼ ◀ A C", "A ▶ B ▼ C ▼", "123", "312", "213", "321", "231", "132"},
				{"▲ ▶ A", "▼ ◀ C", "▶ ◀ B", "▶ ◀ ◀ ▶ C B", "▼ ▲ ▶ B ◀ A", "▲ ▲ ▼ ◀ A C", "321", "231", "132", "123", "312", "213"},
				{"▶ ▲ B", "▶ ▶ A", "▶ ▼ C", "◀ ▲ ▶ ▼ C C", "▶ ▼ ◀ ▲ A A", "▲ ▶ A ◀ ▲ B", "132", "213", "321", "123", "312", "231"}
		};
	private final double r;
	private final BombEdgework ew;
	public MortalKombat(double resizer, BombEdgework e)
	{
		r = resizer;
		ew = e;
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
		String input = optionPane.getValue().toString();
		int playing = characters.indexOf(input);
		characters.remove(input);
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
		input = optionPane.getValue().toString();
		int oppose = characters.indexOf(input);
		String out = "Press these buttons:";
		for(int i = 1; i <= 3; i++)
			out = out + "\n" + moveTables[playing][moveTables[playing][oppose + 6].indexOf(i + "")];
		int num;
		if(oppose < 3)
		{
			switch(playing)
			{
				case 0:
					num = (ew.findPort("PARALLEL") > 0 || ew.findPort("SERIAL") > 0) ? 3 : (ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1) ? 4 : 5;
					break;
				case 1:
					num = (ew.BD() > ew.BA()) ? 3 : (ew.numUnlit() == 0) ? 4 : 5;
					break;
				case 2:
					num = (ew.numLit() > 0) ? 3 : (ew.findPort("RCA") > 0 || ew.findPort("PS/2") > 0) ? 4 : 5;
					break;
				case 3:
					num = (ew.BAT() <= 4) ? 3 : (ew.numCharsInSN("LPT") > 0) ? 4 : 5;
					break;
				case 4:
					num = (ew.numPorts() > 3) ? 3 : (ew.BA() > ew.BD()) ? 4 : 5;
					break;
				case 5:
					num = (ew.numInd() > ew.numPorts()) ? 3 : (ew.getSNDIG(0) > ew.BAT()) ? 4 : 5;
					break;
				default:
					num = (ew.getSNSUM() % 3 == 0) ? 3 : (ew.BAT() == 0) ? 4 : 5;
					break;
			}
		}
		else	
		{
			switch(playing)
			{
				case 0:
					num = (ew.findLit("CAR") || ew.findLit("CLR") || ew.findLit("MSA") || ew.findUnlit("BOB") || ew.findUnlit("NSA") || ew.findUnlit("FRK")) ? 3 : (ew.BAT() % 2 == 0) ? 4 : 5;
					break;
				case 1:
					num = (ew.numCharsInSN("AEIOU") > 0) ? 3 : (ew.findPort("DVI-D") > 0 || ew.findPort("RJ-45") > 0) ? 4 : 5;
					break;
				case 2:
					num = (prime(ew.getSNSUM())) ? 3 : (ew.BD() == 0) ? 4 : 5;
					break;
				case 3:
					num = (ew.numInd() == 0) ? 3 : (ew.findPort("SERIAL") > 1) ? 4 : 5;
					break;
				case 4:
					num = (ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0) ? 3 : (ew.findLit("BOB") || ew.findLit("FRK") || ew.findUnlit("FRQ") || ew.findUnlit("CAR")) ? 4 : 5;
					break;
				case 5:
					num = (ew.BAT() > ew.getSNDIG(0)) ? 3 : (ew.numPorts() > ew.numInd()) ? 4 : 5;
					break;
				default:
					num = (ew.numLit() == 0) ? 3 : (ew.findPort("PARALLEL") > 0 || ew.findPort("RCA") > 0) ? 4 : 5;
					break;
			}
		}
		JOptionPane.showMessageDialog(null, out + "\n" + moveTables[playing][num]);
	}
	private JButton getButton(final JOptionPane optionPane, String name, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(name);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(name);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean prime(int n)
	{
		if(n < 2)
			return false;
		for(int i = 2; i < n; i++)
		{
			if(n % i == 0)
				return false;
		}
		return true;
	}
}
