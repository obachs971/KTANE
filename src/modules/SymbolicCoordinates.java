package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SymbolicCoordinates 
{
	private final double r;
	public SymbolicCoordinates(double resizer)
	{
		r = resizer;
	}
	public String run()
	{
		String souv = "";
		for(int stage = 0; stage < 3; stage++)
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			ImageIcon[] icon = new ImageIcon[5];
			JButton[] jButton = new JButton[5];
			optionPane.setLayout(new GridLayout(5, 1));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int aa = 0; aa < 5; aa++)
			{
				icon[aa] = new ImageIcon("img/SymbolicCoordinates" + aa + ".png");
				Image image = icon[aa].getImage();
				image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[aa] = new ImageIcon(image);
				jButton[aa] = getButton(optionPane, (aa + ""), icon[aa]);
				optionPane.add(jButton[aa]);
			}
			String[] names = {"TUNNEL", "PLUTO", "FLAG", "VORTEX", "STAIN"};
			souv = souv + "STAGE #" + (stage + 1) + ": ";
			String syms = "", list = "01234";
			for(int aa = 0; aa < 3; aa++)
			{
				JDialog dialog = optionPane.createDialog(frame, "");
				dialog.setTitle("Select symbol #" + (aa + 1) + ":");
				dialog.setVisible(true);
				syms = syms + "" + (String)optionPane.getValue();
				optionPane.remove(list.indexOf(syms.charAt(aa)));
				list = list.replace(syms.charAt(aa) + "", "");
				souv = souv + "" + names["01234".indexOf(syms.charAt(aa))];
			}
			souv = souv + "\n";
			String colors = JOptionPane.showInputDialog("A - Aqua\nG - Green\nP - Purple\nY - Yellow\nEnter the 3 colors\nin reading order:").toUpperCase();
			boolean v = valid(colors);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				colors = JOptionPane.showInputDialog("A - Aqua\nG - Green\nP - Purple\nY - Yellow\nEnter the 3 colors\nin reading order:").toUpperCase();
				v = valid(colors);
			}
			JOptionPane.showMessageDialog(null, "Enter this coordinate: " + getLetter(syms) + " " + getNumber(colors));
		}
		return souv;
	}
	private String getLetter(String syms)
	{
		switch(syms)
		{
			case "430":	return "A";
			case "130":	return "B";
			case "304":	return "C";
			case "012":	return "D";
			case "132":	return "E";
			case "123":	return "F";
			case "230":	return "G";
			case "403":	return "H";
			case "413":	return "I";
			case "034":	return "J";
			case "321":	return "K";
			case "342":	return "L";
			case "203":	return "M";
			case "421":	return "N";
			case "314":	return "O";
			case "023":	return "P";
			case "032":	return "Q";
			case "324":	return "R";
			case "241":	return "S";
			case "412":	return "T";
			case "103":	return "U";
			case "142":	return "V";
			case "243":	return "W";
			case "402":	return "X";
			case "013":	return "Y";
			case "214":	return "Z";
			default:	return "*";
		}
	}
	private String getNumber(String colors)
	{
		switch(colors)
		{
			case "AGY":	return "0";
			case "GYP":	return "1";
			case "GPY":	return "2";
			case "YAP":	return "3";
			case "AGP":	return "4";
			case "PAG":	return "5";
			case "YGP":	return "6";
			case "GAP":	return "7";
			case "APY":	return "8";
			case "PGA":	return "9";
			default:	return "*";
		}
	}
	private JButton getButton(final JOptionPane optionPane, String value, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	    	  optionPane.setValue(value);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean valid(String i)
	{
		if(i.length() == 3)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("AGPY".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
