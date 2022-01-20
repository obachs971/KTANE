package modules;

import javax.swing.JOptionPane;

import misc.MTK;

public class ColoredSwitches 
{
	public String run()
	{
		MTK mtk = new MTK();
		String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nG - Green\nO - Orange\nP - Purple\nT - Turquoise\nEnter the colors:").toUpperCase();
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R - Red\nB - Blue\nG - Green\nO - Orange\nP - Purple\nT - Turquoise\nEnter the colors:").toUpperCase();
			v = v1(input);
		}
		String[][] maze = getMaze(input);
		String current = JOptionPane.showInputDialog("U - Up\nD - Down\n12345 - Up positions\nEnter switches' positions:").toUpperCase();
		v = v2(current);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			current = JOptionPane.showInputDialog("U - Up\nD - Down\nEnter switches' positions:").toUpperCase();
			v = v2(current);
		}
		if(!(current.length() == 5 && "UD".indexOf(current.charAt(0)) >= 0))
			current = convert(current);
		String souv = "INITIAL: " + current.toUpperCase();
		String dir = "";
		for(int aa = 0; aa < 3; aa++)
		{
			for(int bb = 0; bb < maze.length; bb++)
			{
				if(maze[bb][0].equals(current))
				{
					dir = dir + "" + maze[bb][1].charAt(0);
					current = maze[bb][1].substring(1);
					break;
				}
			}
		}
		String goal = JOptionPane.showInputDialog("U - Up\nD - Down\nFlip these switches: " + dir + "\nEnter lights' positions:").toUpperCase();
		v = v2(goal);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			goal = JOptionPane.showInputDialog("U - Up\nD - Down\nFlip these switches: " + dir + "Enter lights' positions:").toUpperCase();
			v = v2(goal);
		}
		if(!(goal.length() == 5 && "UD".indexOf(goal.charAt(0)) >= 0))
			goal = convert(goal);
		souv = souv + "\nLEDS: " + goal.toUpperCase();
		dir = mtk.getMazeSolution(maze, 1, current, goal);
		String out = "";
		for(int aa = 0; aa < dir.length(); aa++)
		{
			out = out + "" + dir.charAt(aa);
			if((aa + 1) % 12 == 0)
				out = out + "\n";
			else if((aa + 1) % 3 == 0)
				out = out + " ";
		}
		JOptionPane.showMessageDialog(null, "Flip these switches:\n" + out);
		return souv;
	}
	private String[][] getMaze(String c)
	{
		String[][] template = 
			{
					{"DDDDD", "1"},{"DDDDU", "3"},{"DDDUD", "23"},{"DDDUU", "3"},
					{"DDUDD", "5"},{"DDUDU", "5"},{"DDUUD", "4"},{"DDUUU", "2"},
					{"DUDDD", "2"},{"DUDDU", "23"},{"DUDUD", "2"},{"DUDUU", "2"},
					{"DUUDD", "4"},{"DUUDU", "5"},{"DUUUD", "1"},{"DUUUU", "1"},
					{"UDDDD", "5"},{"UDDDU", "2"},{"UDDUD", "5"},{"UDDUU", "4"},
					{"UDUDD", "4"},{"UDUDU", "5"},{"UDUUD", "5"},{"UDUUU", "4"},
					{"UUDDD", "14"},{"UUDDU", "1"},{"UUDUD", "24"},{"UUDUU", "1"},
					{"UUUDD", "5"},{"UUUDU", "5"},{"UUUUD", "4"},{"UUUUU", "3"}
			};
		if("GB".indexOf(c.charAt(4)) >= 0)
			template[3][1] = template[3][1] + "5";
		if("OGB".indexOf(c.charAt(0)) >= 0)
			template[4][1] = template[4][1] + "1";
		if("TRP".indexOf(c.charAt(0)) >= 0)
			template[5][1] = template[5][1] + "1";
		if("P".indexOf(c.charAt(3)) >= 0)
			template[5][1] = template[5][1] + "4";
		if("P".indexOf(c.charAt(4)) >= 0)
			template[7][1] = template[7][1] + "5";
		if("G".indexOf(c.charAt(3)) >= 0)
			template[9][1] = template[9][1] + "4";
		if("OBT".indexOf(c.charAt(0)) >= 0)
			template[18][1] = template[18][1] + "1";
		if("RTP".indexOf(c.charAt(2)) >= 0)
			template[20][1] = template[20][1] + "3";
		if("OB".indexOf(c.charAt(2)) >= 0)
			template[21][1] = template[21][1] + "3";
		if("G".indexOf(c.charAt(2)) >= 0)
			template[22][1] = template[22][1] + "3";
		if("O".indexOf(c.charAt(3)) >= 0)
			template[25][1] = template[25][1] + "4";
		if("BPG".indexOf(c.charAt(4)) >= 0)
			template[25][1] = template[25][1] + "5";
		if("PGR".indexOf(c.charAt(0)) >= 0)
			template[26][1] = template[26][1] + "1";
		if("RTO".indexOf(c.charAt(4)) >= 0)
			template[27][1] = template[27][1] + "5";
		if("BT".indexOf(c.charAt(1)) >= 0)
			template[28][1] = template[28][1] + "2";
		if("OPG".indexOf(c.charAt(3)) >= 0)
			template[28][1] = template[28][1] + "4";
		if("OP".indexOf(c.charAt(1)) >= 0)
			template[29][1] = template[29][1] + "2";
		if("RBT".indexOf(c.charAt(3)) >= 0)
			template[29][1] = template[29][1] + "4";
		if("RG".indexOf(c.charAt(1)) >= 0)
			template[30][1] = template[30][1] + "2";
		
		
		String[][] maze = new String[template.length][];
		for(int aa = 0; aa < maze.length; aa++)
		{
			maze[aa] = new String[template[aa][1].length() + 1];
			maze[aa][0] = template[aa][0].toUpperCase();
			for(int bb = 0; bb < template[aa][1].length(); bb++)
				maze[aa][bb + 1] = template[aa][1].charAt(bb) + "" + getToggle(template[aa][0], "12345".indexOf(template[aa][1].charAt(bb)));
		}
		return maze;
	}
	private String getToggle(String d, int pos)
	{
		if(d.charAt(pos) == 'U')
			return (d.substring(0, pos) + "D" + d.substring(pos + 1));
		else
			return (d.substring(0, pos) + "U" + d.substring(pos + 1));
	}
	private String convert(String i)
	{
		String temp = "DDDDD";
		for(int aa = 0; aa < i.length(); aa++)
		{
			int num = "12345".indexOf(i.charAt(aa));
			temp = temp.substring(0, num) + "U" + temp.substring(num + 1);
		}
		return temp;
	}
	private boolean v1(String i)
	{
		if(i.length() == 5)
		{
			for(int aa = 0; aa < 5; aa++)
			{
				if("OGPTBR".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 5 && "UD".indexOf(i.charAt(0)) >= 0)
		{
			for(int aa = 0; aa < 5; aa++)
			{
				if("UD".indexOf(i.charAt(aa)) < 0)
					return false;
			}
		}
		else
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("12345".indexOf(i.charAt(aa)) < 0)
					return false;
				for(int bb = aa + 1; bb < i.length(); bb++)
				{
					if(i.charAt(aa) == i.charAt(bb))
						return false;
				}
			}
		}
		return true;
	}
}
