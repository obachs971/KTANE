package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class Flags 
{
	private String[][] table =
		{
				{"Algeria", "NAF", "ALGIERS", "DZD", "DZA", "213"},
				{"Australia", "OC", "CANBERRA", "AUD", "AUS", "061"},
				{"Austria", "EU", "VIENNA", "EUR", "AUT", "043"},
				{"Belgium", "EU", "BRUSSELS", "EUR", "BEL", "032"},
				{"Brazil", "SAM", "BRASILIA", "BRL", "BRA", "055"},
				{"Canada", "NAM", "OTTAWA", "CAD", "CAN", "001"},
				{"Chile", "SAM", "SANTIAGO", "CLP", "CHL", "056"},
				{"China", "EA", "BEIJING", "CNY", "CHN", "086"},
				{"Colombia", "SAM", "BOGOTA", "COP", "COL", "057"},
				{"Cuba", "NAM", "HAVANA", "CUP", "CUB", "053"},
				{"Czech Republic", "EU", "PRAGUE", "CZK", "CZE", "420"},
				{"Denmark", "EU", "COPENHAGEN", "DKK", "DNK", "045"},
				{"Finland", "EU", "HELSINKI", "EUR", "FIN", "358"},
				{"France", "EU", "PARIS", "EUR", "FRA", "033"},
				{"Germany", "EU", "BERLIN", "EUR", "DEU", "049"},
				{"Greenland", "NAM", "NUUK", "DKK", "GRL", "299"},
				{"Iceland", "EU", "REYKJAVIK", "ISK", "ISL", "354"},
				{"India", "SAS", "NEWDELHI", "INR", "IND", "091"},
				{"Japan", "EA", "TOKYO", "JPY", "JPN", "081"},
				{"Mexico", "NAM", "MEXICOCITY", "MXN", "MEX", "052"},
				{"Morocco", "NAF", "RABAT", "MAD", "MAR", "212"},
				{"Netherlands", "EU", "AMSTERDAM", "EUR", "NLD", "031"},
				{"New Zealand", "OC", "WELLINGTON", "NZD", "NZL", "064"},
				{"Norway", "EU", "OSLO", "NOK", "NOR", "047"},
				{"Panama", "CA", "PANAMACITY", "PAB", "PAN", "507"},
				{"Peru", "SAM", "LIMA", "PEN", "PER", "051"},
				{"Poland", "EU", "WARSAW", "PLN", "POL", "048"},
				{"Samoa", "OC", "APIA", "WST", "WSM", "685"},
				{"Senegal", "WA", "DAKAR", "XOF", "SEN", "221"},
				{"South Korea", "EA", "SEOUL", "KRW", "KOR", "082"},
				{"Spain", "EU", "MADRID", "EUR", "ESP", "034"},
				{"Sudan", "NAF", "KHARTOUM", "SDG", "SDN", "249"},
				{"Sweden", "EU", "STOCKHOLM", "SEK", "SWE", "046"},
				{"Thailand", "SEA", "BANGKOK", "THB", "THA", "066"},
				{"United Kingdom", "EU", "LONDON", "GBP", "GBR", "044"},
				{"United States", "NAM", "WASHINGTONDC", "USD", "USA", "001"}
		};
	private ArrayList<String> flags;
	private final double r;
	private final BombEdgework ew;
	public Flags(double resizer, BombEdgework e)
	{
		r = resizer;
		ew = e;
	}
	public String run()
	{
		flags = new ArrayList<String>();
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[36];
		JButton[] jButton = new JButton[36];
		optionPane.setLayout(new GridLayout(12, 3));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < table.length; aa++)
		{
			icon[aa] = new ImageIcon("img/Flags" + table[aa][0].replace(" ", "") + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, table[aa][0], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the main flag:");
		dialog.setVisible(true);
		String main = (flags.get(0) + "");
		flags.remove(0);
		for(int aa = 0; aa < 7; aa++)
		{
			dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select flag #" + (aa + 1) + ":");
			dialog.setVisible(true);
			if(aa < 6)
			{
				optionPane.removeAll();
				JButton[] temp = new JButton[jButton.length - 1];
				int cursor = 0;
				for(int bb = 0; bb < jButton.length; bb++)
				{
					if(!(jButton[bb].equals(optionPane.getValue())))
					{
						temp[cursor] = jButton[bb];
						cursor++;
					}
				}
				jButton = temp;
				for(int bb = 0; bb < jButton.length; bb++)
					optionPane.add(jButton[bb]);
			}
		}
		String input = JOptionPane.showInputDialog("Enter the number:");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the number:");
			v = valid(input);
		}
		int index = "1234567".indexOf(input);
		String souv = "MAIN FLAG: " + main.toUpperCase();
		souv = souv + "\nNUMBER: " + input;
		for(int aa = 0; aa < flags.size(); aa++)
			souv = souv + "\nFLAG #" + (aa + 1) + ": " + flags.get(aa).toUpperCase();
		if(ew.findUnlit("BOB") && ew.numCharsInSN("WHITEFLAG") > 0 && flags.contains("France"))
			index = flags.indexOf("France");
		else
		{
			
			String mainCont = getInfo(main, 1), mainDial = getInfo(main, 5), mainCurr = getInfo(main, 3), mainCapital = getInfo(main, 2);
			main = main.toUpperCase();
			if(mainCont.equals("NAM") && ew.numLit() == 0)
				flags = sort(flags, 0);
			else if(mainDial.compareTo("100") > 0 && ew.findPort("RJ-45") > 0)
				flags = sort(flags, 5);
			else if(main.contains(mainCurr.charAt(2) + ""))
				flags = sort(flags, 4);
			else if(mainCapital.length() > 9)
				flags = sort(flags, 2);
			else if(mainCont.equals("EU") && !(mainCurr.equals("EUR")))
				flags = sort(flags, 1);
			else
			{
				ArrayList<String> conts = new ArrayList<String>();
				for(int aa = 0; aa < flags.size(); aa++)
					conts.add(getInfo(flags.get(aa), 1));
				if(conts.contains(mainCont))
					flags = sort(flags, 3);
				else
					flags = sort(flags, 0);
			}
			System.out.println(flags.toString());
		}
		JFrame out = new JFrame();
		ImageIcon outIcon = new ImageIcon("img/Flags" + flags.get(index) + ".png");
		Image image = outIcon.getImage();
		image = image.getScaledInstance((int)(outIcon.getIconWidth() / r), (int)(outIcon.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		outIcon = new ImageIcon(image);
		JLabel outLabel = new JLabel();
		outLabel.setIcon(outIcon);
		out.add(outLabel);
		out.pack();
		out.setVisible(true);
		JOptionPane.showMessageDialog(null, "Submit " + flags.get(index));
		out.setVisible(false);
		return souv;
	}
	private ArrayList<String> sort(ArrayList<String> c, int index)
	{
		System.out.println(index);
		Collections.sort(c);
		if(index == 0)
			return c;
		ArrayList<String> info = new ArrayList<String>();
		for(int aa = 0; aa < c.size(); aa++)
			info.add(getInfo(c.get(aa), index));
		ArrayList<String> sorted = new ArrayList<String>();
		while(c.size() > 0)
		{
			String best = info.get(0);
			for(int aa = 1; aa < info.size(); aa++)
			{
				if(best.compareTo(info.get(aa)) > 0)
					best = info.get(aa);
			}
			for(int aa = 0; aa < info.size(); aa++)
			{
				if(info.get(aa).equals(best))
				{
					sorted.add(c.get(aa) + "");
					c.remove(aa);
					info.remove(aa);
					aa--;
				}
			}
		}
		return sorted;
	}
	private String getInfo(String c, int index)
	{
		for(int aa = 0; aa < table.length; aa++)
		{
			if(table[aa][0].equals(c))
				return table[aa][index];
		}
		return "";
	}
	private JButton getButton(final JOptionPane optionPane, String name, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(name);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(button);
	        flags.add(name);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean valid(String i)
	{
		switch(i)
		{
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
				return true;
			default:
				return false;
		}
	}
}
