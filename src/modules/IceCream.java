package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class IceCream 
{
	private final String[][] table =
		{
				{"150", "683", "071", "432", "361"},
				{"083", "214", "435", "267", "143"},
				{"845", "167", "256", "375", "361"},
				{"267", "014", "823", "781", "573"},
				{"341", "362", "021", "247", "856"},
				{"163", "752", "145", "420", "375"},
				{"461", "236", "157", "682", "274"},
				{"625", "417", "082", "126", "367"},
				{"426", "123", "034", "650", "478"},
				{"635", "512", "426", "710", "372"},
				{"035", "164", "548", "207", "736"},
				{"463", "102", "674", "258", "031"},
				{"371", "082", "713", "678", "451"},
				{"241", "780", "346", "103", "652"},
				{"125", "680", "321", "745", "184"},
				{"031", "257", "346", "671", "530"},
				{"812", "648", "043", "164", "325"},
				{"732", "156", "547", "340", "621"},
				{"562", "136", "347", "205", "813"},
				{"568", "210", "482", "425", "051"}
		};
	private String[] nameList = 
		{
				"Adam","Ashley","Bob","Cheryl","Dave",
				"Gary","George","Jacob","Jade","Jessica",
				"Mike","Pat","Sally","Sam","Sean",
				"Simon","Taylor","Tim","Tom","Victor"
		};
	private String sub;
	private final BombEdgework ew;
	public IceCream(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String[] order;
		if(ew.numLit() > ew.numUnlit())
			order = new String[] {"Cookies and Cream", "Neapolitan", "Tutti Frutti", "The Classic", "Rocky Road", "Double Chocolate", "Mint Chocolate Chip", "Double Strawberry", "Raspberry Ripple"};
		else if(ew.numEmpty() > 0)
			order = new String[] {"Double Chocolate", "Mint Chocolate Chip", "Neapolitan", "Rocky Road", "Tutti Frutti", "Double Strawberry", "Cookies and Cream", "Raspberry Ripple", "The Classic"};
		else if(ew.BAT() >= 3)
			order = new String[] {"Neapolitan", "Tutti Frutti", "Cookies and Cream", "Raspberry Ripple", "Double Strawberry", "Mint Chocolate Chip", "Double Chocolate", "The Classic", "Rocky Road"};
		else
			order = new String[] {"Double Strawberry", "Cookies and Cream", "Rocky Road", "The Classic", "Neapolitan", "Double Chocolate", "Tutti Frutti", "Raspberry Ripple", "Mint Chocolate Chip"};
		String souv = "";
		for(int aa = 0; aa < 3; aa++)
		{
			
			JDialog dialog = getDialog(nameList, 2);
			dialog.setTitle("Select the Name:");
			dialog.setVisible(true);
			int row = getRow(sub);
			souv = souv + "NAME: " + sub.toUpperCase();
			String allergy = table[row][ew.getSNDIG(ew.numSNDIGS() - 1) / 2];
			String[] flavorList = {"The Classic", "Cookies and Cream", "Double Chocolate", "Double Strawberry", "Mint Chocolate Chip", "Neapolitan", "Raspberry Ripple", "Rocky Road", "Tutti Frutti"};
			String[] options = new String[4];
			for(int bb = 0; bb < 4; bb++)
			{
				dialog = getDialog(flavorList, 2);
				dialog.setTitle("Select Non-Vanilla Flavor #" + (bb + 1) + ":");
				dialog.setVisible(true);
				options[bb] = sub.toUpperCase();
				flavorList = remove(flavorList, sub);
			}
			String sell = "Vanilla";
			String ignore = "";
			for(int bb = 0; bb < order.length; bb++)
			{
				if(order[bb].equalsIgnoreCase(options[0]) || order[bb].equalsIgnoreCase(options[1]) || order[bb].equalsIgnoreCase(options[2])|| order[bb].equalsIgnoreCase(options[3]))
				{
					String FA = getFlavorAllergy(order[bb]);
					boolean flag = true;
					for(int cc = 0; cc < FA.length(); cc++)
					{
						if(allergy.indexOf(FA.charAt(cc)) >= 0)
						{
							flag = false;
							break;
						}
					}
					if(flag)
					{
						sell = order[bb];
						ignore = order[bb].toUpperCase();
						break;
					}
				}
			}
			JOptionPane.showMessageDialog(null, "Sell " + sell + " at\neven number of minutes");
			for(int bb = 0; bb < options.length; bb++)
			{
				if(!(options[bb].equals(ignore)))
					souv = souv + "\n" + options[bb].toUpperCase();
			}
			souv = souv + "\n----------------------------------------\n";
		}
		return souv;
	}
	private String getFlavorAllergy(String f)
	{
		switch(f)
		{
			case "Tutti Frutti":
				return "1267";
			case "Rocky Road":
				return "038";
			case "Raspberry Ripple":
				return "26";
			case "Double Chocolate":
				return "0";
			case "Double Strawberry":
				return "16";
			case "Cookies and Cream":
				return "4";
			case "Neapolitan":
				return "016";
			case "Mint Chocolate Chip":
				return "05";
			case "The Classic":
				return "067";
		}
		return "";
	}
	
	private int getRow(String n)
	{
		switch(n)
		{
			case "MIKE":return 0;
			case "TIM":return 1;
			case "TOM":return 2;
			case "DAVE":return 3;
			case "ADAM":return 4;
			case "CHERYL":return 5;
			case "SEAN":return 6;
			case "ASHLEY":return 7;
			case "JESSICA":return 8;
			case "TAYLOR":return 9;
			case "SIMON":return 10;
			case "SALLY":return 11;
			case "JADE":return 12;
			case "SAM":return 13;
			case "GARY":return 14;
			case "VICTOR":return 15;
			case "GEORGE":return 16;
			case "JACOB":return 17;
			case "PAT":return 18;
			case "BOB":return 19;
		}
		return -1;
	}
	
	private JDialog getDialog(String[] arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.length];
		if(arr.length % div == 0)
			optionPane.setLayout(new GridLayout(arr.length / div, div));
		else
			optionPane.setLayout(new GridLayout(arr.length / div + 1, div));
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
