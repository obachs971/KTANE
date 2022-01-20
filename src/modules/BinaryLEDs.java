package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BinaryLEDs 
{
	
	private final String[][] table =
		{
				{"15", "2345", "34", "4", "12", "2", "124", "125", "135", "12", "5", "2345", "14", "2"},
				{"14", "2345", "145", "12345", "23", "34", "145", "135", "245", "1", "145", "4", "5", "1235"},
				{"2", "125", "5", "2345", "13", "2345", "25", "45", "34", "12", "5", "12", "35", "124"},
				{"135", "1245", "34", "23", "1245", "13", "345", "5", "145", "2345", "45", "235", "25", "123"},
				{"45", "135", "234", "134", "345", "123", "1", "1245", "134", "15", "124", "4", "12345", "2345"},
				{"2", "134", "1234", "145", "5", "125", "12345", "1", "25", "345", "34", "235", "25", "345"},
				{"35", "14", "23", "345", "35", "23", "12345", "1", "24", "2345", "15", "25", "23", "125"},
				{"3", "13", "14", "125", "13", "3", "12", "1235", "15", "1", "23", "1", "1235", "145"}	
		};
	private final String[][] outs =
		{
				{"Cut RED at 4, 12, 2\nCut GREEN at 4\nCut BLUE at 125", "Cut RED at 2, 24, 8\nCut GREEN at 2\nCut BLUE at 25"},
				{"Cut RED at 4\nCut GREEN at 245\nCut BLUE at 23", "Cut RED at 2\nCut GREEN at 11\nCut BLUE at 12"},
				{"Cut RED at 45, 34, 12\nCut GREEN at 125\nCut BLUE at 2, 125, 5", "Cut RED at 3, 6, 24\nCut GREEN at 25\nCut BLUE at 8, 25, 1"},
				{"Cut RED at 145\nCut GREEN at 25\nCut BLUE at 5", "Cut RED at 19\nCut GREEN at 9\nCut BLUE at 1"},
				{"Cut RED at 124\nCut GREEN at 123\nCut BLUE at 1, 1245, 134", "Cut RED at 26\nCut GREEN at 28\nCut BLUE at 16, 27, 22"},
				{"Cut RED at 2\nCut GREEN at 34\nCut BLUE at 12345", "Cut RED at 8\nCut GREEN at 6\nCut BLUE at 31"},
				{"Cut RED at 35, 14, 23\nCut GREEN at 345, 35, 23\nCut BLUE at 2345", "Cut RED at 5, 18, 12\nCut GREEN at 7, 5, 12\nCut BLUE at 15"},
				{"Cut RED at 1235, 15, 1\nCut GREEN at 125, 13, 3\nCut BLUE at 23", "Cut RED at 29, 17, 16\nCut GREEN at 25, 20, 4\nCut BLUE at 12"},
		};
	private final String[] souvData =
		{
				"RED: 8\nGREEN: 2\nBLUE: 25",
				"RED: 2\nGREEN: 11\nBLUE: 12",
				"RED: 24\nGREEN: 25\nBLUE: 1",
				"RED: 19\nGREEN: 9\nBLUE: 1",
				"RED: 26\nGREEN: 28\nBLUE: 22",
				"RED: 8\nGREEN: 6\nBLUE: 31",
				"RED: 12\nGREEN: 12\nBLUE: 15",
				"RED: 16\nGREEN: 4\nBLUE: 12"
		};
	private final int pt;
	public BinaryLEDs(int p)
	{
		pt = p;
	}
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter 3 led numbers:");
		ArrayList<Integer> row = valid(input);
		while(row.size() != 1)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter 3 led numbers:");
			row = valid(input);
		}
		JOptionPane.showMessageDialog(null, outs[row.get(0)][pt / 2]);
		String souv = souvData[row.get(0)];
		return souv;
	}
	private ArrayList<Integer> getRow(String[] numbers)
	{
		ArrayList<Integer> rows = new ArrayList<Integer>();
		for(int aa = 0; aa < table.length; aa++)
		{
			int[] cursors = new int[numbers.length];
			int[] offsets = new int[numbers.length];
			for(int bb = 0; bb < cursors.length; bb++)
			{
				cursors[bb] = bb;
				offsets[bb] = 1;
			}
			for(int bb = 0; bb < 27; bb++)
			{
				boolean flag = true;
				for(int cc = 0; cc < cursors.length; cc++)
				{
					if(!(table[aa][cursors[cc]].equals(numbers[cc])))
					{
						flag = false;
						break;
					}
				}
				if(flag)
				{
					rows.add(aa);
					break;
				}
				for(int cc = 0; cc < cursors.length; cc++)
				{
					cursors[cc] += offsets[cc];
					if(cursors[cc] >= table[aa].length)
					{
						cursors[cc] = table[aa].length - 2;
						offsets[cc] = -1;
					}
					else if(cursors[cc] < 0)
					{
						cursors[cc] = 1;
						offsets[cc] = 1;
					}
				}
			}
		}
		return rows;
	}
	private ArrayList<Integer> valid(String i)
	{
		ArrayList<Integer> row = new ArrayList<Integer>();
		String[] conv = i.split(" ");
		if(conv.length == 3)
			row = getRow(conv);
		return row;
	}
}
