package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Synonyms 
{
	private final BombEdgework ew;
	public Synonyms(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		int[][] table =
			{
				{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0},
				{0, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1},
				{2, 3, 5, 7, 9, 1, 4, 6, 8, 0, 4},
				{4, 2, 1, 8, 6, 5, 9, 3, 0, 7, 2},
				{5, 1, 2, 4, 9, 0, 6, 9, 3, 8, 7},
				{8, 4, 2, 1, 9, 3, 1, 6, 5, 7, 0},
				{6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 0},
				{5, 6, 7, 5, 1, 3, 9, 0, 2, 4, 8},
				{7, 1, 2, 3, 7, 5, 6, 4, 8, 9, 0},
				{3, 7, 0, 2, 8, 0, 1, 4, 6, 9, 5},
				{1, 3, 2, 4, 7, 5, 6, 0, 8, 9, 3}	
			};
		if(ew.findLit("IND") && ew.getSNDIG(ew.numSNDIGS() - 1) == 5)
		{
			for(int i = 0; i < 11; i++)
			{
				int temp = table[1][i] + 0;
				table[1][i] = table[6][i] + 0;
				table[6][i] = temp + 0;
			}
		}
		if(ew.numEmpty() == 2)
		{
			for(int i = 0; i < 11; i++)
				table[i][6] = (table[i][6] * 2) % 10;
		}
		String[][] temp = {
				{"CANCEL", "ANNUL", "ERASE", "DELETE", "STOP", "OPPOSE", "DISCARD", "REJECT", "DECLINE", "REFUSE", "NO"},
				{"OK", "OKAY", "CONFIRM", "ENTER", "EXECUTE", "VERIFY", "SEND", "APPROVE", "SUBMIT", "SELECT", "YES"}
		};
		ArrayList<String> rows = new ArrayList<String>();
		ArrayList<String> cols = new ArrayList<String>();
		for(int i = 0; i < 11; i++)
		{
			rows.add(temp[0][i].toUpperCase());
			cols.add(temp[1][i].toUpperCase());
		}
		String input = JOptionPane.showInputDialog("Enter the number:");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the number:");
			v = v1(input);
		}
		int number = Integer.parseInt(input);
		boolean flag = true;
		for(int i = 0; i < 10; i++)
		{
			int[] rc = getRowCol(i + 1, rows, cols);
			while(rc == null)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				rc = getRowCol(i + 1, rows, cols);
			}
			if(table[rc[0]][rc[1]] == number)
			{
				flag = false;
				output(number, cols.get(rc[1]), rows.get(rc[0]));
				break;
			}
			rows.set(rc[0], " ");
			cols.set(rc[1], " ");
		}
		if(flag)
		{
			int[] rc = new int[2];
			for(int i = 0; i < 11; i++)
			{
				if(!(rows.get(i).equals(" ")))
					rc[0] = i;
				if(!(cols.get(i).equals(" ")))
					rc[1] = i;
			}
			if(table[rc[0]][rc[1]] == number)
				output(number, cols.get(rc[1]), rows.get(rc[0]));
			else
				JOptionPane.showMessageDialog(null, "Press EXECUTE");
		}
		return ("DISPLAYED NUMBER: " + number);
	}
	private boolean v1(String i)
	{
		switch(i)
		{
			case "0": case "1": case "2": case "3": case "4": 
			case "5": case "6": case "7": case "8": case "9":
				return true;
			default:
				return false;
		}
	}
	private int[] getRowCol(int cur, ArrayList<String> rows, ArrayList<String> cols)
	{
		String input = JOptionPane.showInputDialog("Enter word pair #" + cur + ":").toUpperCase();
		while(input.contains("  "))
			input = input.replace("  ", " ");
		String[] conv = input.split(" ");
		if(conv.length == 2)
		{
			if(rows.indexOf(conv[0]) >= 0 && cols.indexOf(conv[1]) >= 0)
				return new int[] {rows.indexOf(conv[0]), cols.indexOf(conv[1])};
			else if(rows.indexOf(conv[1]) >= 0 && cols.indexOf(conv[0]) >= 0)
				return new int[] {rows.indexOf(conv[1]), cols.indexOf(conv[0])};
		}
		return null;
	}
	private void output(int number, String even, String odd)
	{
		if(number % 2 == 0)
			JOptionPane.showMessageDialog(null, "Press " + even);
		else
			JOptionPane.showMessageDialog(null, "Press " + odd);
	}
}
