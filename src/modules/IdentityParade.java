package modules;

import java.util.ArrayList;

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
	public String run()
	{
		String souv = "";
		ArrayList<String> hair = new ArrayList<String>(), build = new ArrayList<String>(), attire = new ArrayList<String>();
		for(int aa = 0; aa < 3; aa++)
		{
			
			String input = JOptionPane.showInputDialog("Enter hair color #" + (aa + 1) + ":").toUpperCase();
			boolean v = v1(input, " RED BLONDE BROWN BLACK GREY GRAY WHITE ", hair);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter hair color #" + (aa + 1) + ":").toUpperCase();
				v = v1(input, " RED BLONDE BROWN BLACK GREY GRAY WHITE ", hair);
			}
			hair.add(input.replace("GRAY", "GREY"));
			souv = souv + "HAIR COLOR #" + (aa + 1) + ": " + hair.get(aa) + "\n";
		}
		for(int aa = 0; aa < 3; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter body build #" + (aa + 1) + ":").toUpperCase();
			boolean v = v1(input, " SHORT TALL FAT SLIM MUSCULAR HUNCHED ", build);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter body build #" + (aa + 1) + ":").toUpperCase();
				v = v1(input, " SHORT TALL FAT SLIM MUSCULAR HUNCHED ", build);	
			}
			build.add(input.toUpperCase());
			souv = souv + "BUILD #" + (aa + 1) + ": " + build.get(aa) + "\n";
		}
		for(int aa = 0; aa < 3; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter attire #" + (aa + 1) + ":").toUpperCase();
			boolean v = v1(input, attire);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter attire #" + (aa + 1) + ":").toUpperCase();
				v = v1(input, attire);
			}
			attire.add(input.replace("T SHIRT", "T-SHIRT"));
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
			String out = "";
			for(int aa = 0; aa < list.size(); aa++)
				out = out + "\n" + (aa + 1) + ": " + list.get(aa)[0];
			String input = JOptionPane.showInputDialog("Select the present name:" + out);
			boolean v = v2(input, list);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Select the present name:" + out);
				v = v2(input, list);
			}
			String[] temp = list.get(Integer.parseInt(input) - 1);
			list.clear();
			list.add(temp);
		}
		JOptionPane.showMessageDialog(null, "Submit this config:\nHair: " + list.get(0)[1] + "\nBuild: " + list.get(0)[2] + "\nAttire: " + list.get(0)[3] + "\nName: " + list.get(0)[0]);
		return souv;
	}
	private boolean v1(String i, String op, ArrayList<String> l)
	{
		return (op.indexOf((" " + i + " ")) >= 0 && !(l.contains(i)));
	}
	private boolean v1(String i, ArrayList<String> l)
	{
		switch(i)
		{
			case "T-SHIRT":
			case "T SHIRT":
			case "BLAZER":
			case "JUMPER":
			case "TANK TOP":
			case "HOODIE":
			case "SUIT":
				return !(l.contains(i));
		}
		return false;
	}
	private boolean v2(String i, ArrayList<String[]> l)
	{
		if(i.length() > 0)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("0123456789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return (Integer.parseInt(i) >= 1 && Integer.parseInt(i) <= l.size());
		}
		return false;
	}
}
