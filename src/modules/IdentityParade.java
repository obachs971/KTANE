package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class IdentityParade 
{
	private final String[][] table =
		{
				{"ANDY", "BROWN", "HUNCHED", "SUIT"},
				{"BEN", "GREY", "TALL", "T-SHIRT"},
				{"CHRISSIE", "RED", "HUNCHED", "HOODIE"},
				{"DYLAN", "BLONDE", "SHORT", "TANK TOP"},
				{"EDDIE", "GREY", "SLIM", "SUIT"},
				{"FIONA", "BROWN", "TALL", "HOODIE"},
				{"GEMMA", "GREY", "SHORT", "BLAZER"},
				{"HARRIET", "BLACK", "FAT", "T-SHIRT"},
				{"IAN", "WHITE", "TALL", "JUMPER"},
				{"JAMES", "RED", "MUSCULAR", "TANK TOP"},
				{"KAYLEIGH", "WHITE", "SHORT", "TANK TOP"},
				{"LOUISE", "BLONDE", "FAT", "SUIT"},
				{"MEGAN", "BROWN", "SLIM", "BLAZER"},
				{"NATE", "RED", "FAT", "JUMPER"},
				{"OSCAR", "BLACK", "SLIM", "HOODIE"},
				{"PENNY", "BLONDE", "MUSCULAR", "T-SHIRT"},
				{"QUENTIN", "WHITE", "HUNCHED", "BLAZER"},
				{"RHIANNON", "BLACK", "MUSCULAR", "JUMPER"}
		};
	private String sub;
	public String run()
	{
		String souv = "";
		ArrayList<String> hair = new ArrayList<String>(), build = new ArrayList<String>(), attire = new ArrayList<String>();
		String[] temp = {"Red", "Blonde", "Brown", "Black", "Grey", "White"};
		for(int aa = 0; aa < 3; aa++)
		{
			JDialog dialog = getDialog(temp, 2);
			dialog.setTitle("Select Hair Color #" + (aa + 1) + ":");
			dialog.setVisible(true);
			hair.add(sub.toUpperCase());
			temp = remove(temp, sub);
			souv = souv + "HAIR COLOR #" + (aa + 1) + ": " + hair.get(aa) + "\n";
		}
		temp = new String[] {"Short", "Tall", "Fat", "Slim", "Muscular", "Hunched"};
		for(int aa = 0; aa < 3; aa++)
		{
			JDialog dialog = getDialog(temp, 2);
			dialog.setTitle("Select Build #" + (aa + 1) + ":");
			dialog.setVisible(true);
			build.add(sub.toUpperCase());
			temp = remove(temp, sub);
			souv = souv + "BUILD #" + (aa + 1) + ": " + build.get(aa) + "\n";
		}
		temp = new String[] {"Suit", "T-Shirt", "Hoodie", "Tank Top", "Blazer", "Jumper"};
		for(int aa = 0; aa < 3; aa++)
		{
			
			JDialog dialog = getDialog(temp, 2);
			dialog.setTitle("Select Attire #" + (aa + 1) + ":");
			dialog.setVisible(true);
			attire.add(sub.toUpperCase());
			temp = remove(temp, sub);
			souv = souv + "ATTIRE #" + (aa + 1) + ": " + attire.get(aa) + "\n";
		}
		ArrayList<String[]> list = new ArrayList<String[]>();
		for(int aa = 0; aa < table.length; aa++)
		{
			if(hair.contains(table[aa][1]) && build.contains(table[aa][2]) && attire.contains(table[aa][3]))
				list.add(table[aa]);
		}
		if(list.size() > 1)
		{
			JDialog dialog = getDialog(list, 2);
			dialog.setTitle("Select the Present Name:");
			dialog.setVisible(true);
			temp = list.get(Integer.parseInt(sub));
			list.clear();
			list.add(temp);
		}
		JOptionPane.showMessageDialog(null, "Submit this config:\nHair: " + list.get(0)[1] + "\nBuild: " + list.get(0)[2] + "\nAttire: " + list.get(0)[3] + "\nName: " + list.get(0)[0]);
		return souv;
	}
	private JDialog getDialog(String[] arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.length];
		optionPane.setLayout(new GridLayout(arr.length / div + (arr.length % div), div));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < arr.length; aa++)
		{
			jButton[aa] = getButton(optionPane, arr[aa]);
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String text) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        sub = text.toUpperCase();
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private JDialog getDialog(ArrayList<String[]> arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.size()];
		optionPane.setLayout(new GridLayout(arr.size() / div + (arr.size() % div), div));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < arr.size(); aa++)
		{
			jButton[aa] = getButton(optionPane, arr.get(aa)[0], (aa + ""));
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String text, String val) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        sub = val;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private String[] remove(String[] arr, String str)
	{
		String[] conv = new String[arr.length - 1];
		int cur = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(!(arr[i].equalsIgnoreCase(str)))
				conv[cur++] = arr[i];
		}
		return conv;
	}
}
