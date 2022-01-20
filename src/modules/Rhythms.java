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

import start.BombEdgework;

public class Rhythms 
{
	private final String[][] table =
		{
				{"Q2E0", "----", "S2E0", "B2E0"},
                {"Q1S0", "E0Q0", "B0E1", "B1S1"},
				{"B1E0", "E1B1", "Q0S0", "S0S1"},
				{"E0B0", "S1Q0", "S1S0", "B1E0"},
				{"E1B0", "S0B0", "Q1E0", "S0B1"},
				{"Q1S1", "E0E1", "B0E0", "B0Q1"},
				{"Q0E1", "B0B1", "S0S1", "E0E0"}
		};
	private final BombEdgework ew;
	private final double r;
	public Rhythms(BombEdgework e, double resizer)
	{
		ew = e;
		r = resizer;
	}
	public String run()
	{
		String[] notes = {"Quarter", "Single 8th", "Double 8th", "Double 16th"}, colors = {"BLUE", "RED", "GREEN", "YELLOW"};
		String color = JOptionPane.showInputDialog("Red, Blue, Yellow, Green\nEnter the light's color:").toUpperCase();
		int col = getCol(color);
		while(col == -1)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			color = JOptionPane.showInputDialog("Enter the light's color:").toUpperCase();
			col = getCol(color);
		}
		String souv = "COLOR: " + colors[col].toUpperCase();
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[7];
		JButton[] jButton = new JButton[7];
		optionPane.setLayout(new GridLayout(7,1));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int bb = 0; bb < 7; bb++)
		{
			icon[bb] = new ImageIcon("img/Rhythms" + (bb + 1) + ".jpg");
			Image image = icon[bb].getImage();
			image = image.getScaledInstance((int)(icon[bb].getIconWidth() / r), (int)(icon[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[bb] = new ImageIcon(image);
			jButton[bb] = getButton(optionPane, (bb + ""), icon[bb]);
			optionPane.add(jButton[bb]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the rhythm:");
		dialog.setVisible(true);
		int row = Integer.parseInt((String)optionPane.getValue());
		String instruct = table[row][col];
		if(instruct.equals("----"))
			JOptionPane.showMessageDialog(null, "Mash the buttons");
		else
		{
			String out = "";
			if(row == 6 && ew.BAT() > 1)
				instruct = instruct.substring(0, 2) + "" + instruct.substring(0, 2);
			if(col == 3)
			{
				for(int aa = 0; aa < 4; aa += 2)
				{
					int num = "0123456789".indexOf(instruct.charAt(aa + 1)) + ew.numLit();
					if(num == 0)
						out = out + "Tap " + notes["QEBS".indexOf(instruct.charAt(aa))] + "\n";
					else
						out = out + "Hold " + notes["QEBS".indexOf(instruct.charAt(aa))] + " for " + num + " beeps\n";
				}
			}
			else
			{
				for(int aa = 0; aa < 4; aa += 2)
				{
					if(instruct.charAt(aa + 1) == '0')
						out = out + "Tap " + notes["QEBS".indexOf(instruct.charAt(aa))] + "\n";
					else
						out = out + "Hold " + notes["QEBS".indexOf(instruct.charAt(aa))] + " for " + instruct.charAt(aa + 1) + " beeps\n";
				}
			}
			JOptionPane.showMessageDialog(null, out);
		}
		return souv;
	}
	private int getCol(String i)
	{
		switch(i)
		{
			case "BLUE":
			case "B":
				return 0;
			case "RED":
			case "R":
				return 1;
			case "GREEN":
			case "G":
				return 2;
			case "YELLOW":
			case "Y":
				return 3;
		}
		return -1;
	}
	private JButton getButton(final JOptionPane optionPane, String value, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(value.toUpperCase());
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
