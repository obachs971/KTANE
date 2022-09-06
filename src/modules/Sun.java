package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class Sun 
{
	private final BombConfig con;
	private final BombEdgework ew;
	public Sun(BombConfig cf, BombEdgework e)
	{
		con = cf;
		ew = e;
	}
	public void run()
	{
		int[] scores = {
				ew.numInd(),
				ew.numCharsInSN("BCDFGHJKLMNPQRSTVWXYZ"),
				ew.BAT(),
				ew.numSNDIGS(),
				ew.numPorts(),
				con.getNumberModules(),
				ew.BH(),
				ew.numPlates()
		};
		String[] dir = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[dir.length];
		optionPane.setLayout(new GridLayout(4, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < dir.length; aa++)
		{
			jButton[aa] = getButton(optionPane, dir[aa], aa + "");
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the LED location:");
		dialog.setVisible(true);
		String[] buttonPress = new String[8];
		int index = Integer.parseInt(optionPane.getValue().toString());
		for(int i = 0; i < 8; i++)
		{
			if((scores[i] % 7) < 2)
				buttonPress[(index + i) % 8] = "OUTER";
			else if((scores[i] % 7) < 5)
				buttonPress[(index + i) % 8] = "INNER";
			else
				buttonPress[(index + i) % 8] = "CENTER";
		}
		index = con.getNumberModules() % 8;
		String pressed = "", out = "Press these buttons:";
		for(int i = 0; i < 8; i++)
		{
			if(buttonPress[index].equals("CENTER"))
			{
				out = out + "\nCENTER";
				break;
			}
			out = out + "\n" + dir[index] + " " + buttonPress[index];
			pressed = pressed + "" + index;
			if(i < 7)
			{
				int offset = 1;
				if("0123456789-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(i % 6)) % 10 < 5)
					offset = -1;
				while(pressed.indexOf(index + "") >= 0)
					index = mod(index + offset, 8);
			}
		}
		JOptionPane.showMessageDialog(null, out);
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private JButton getButton(final JOptionPane optionPane, String text, String index) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(index);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	
}
