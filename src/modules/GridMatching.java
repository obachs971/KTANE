package modules;

import javax.swing.JOptionPane;

public class GridMatching 
{
	private final String[][] grids =
		{
				{"WBBBBB","WBBWWW","BBWBWW","WWWBBB","WWBWBW","BBWBBW"},
				{"BWBWWW","BBWWBB","WWWWWB","WWWWWB","BBWBBB","BBWWWW"},	
				{"WWBWBB","WBWWWB","BBWBWB","WBWWBB","BBBBWB","BWWWWB"},	
				{"BBBBWW","BBWBWW","WBWBWB","BBWWBB","BBWBWW","WWBWBW"},	
				{"BBBWWW","WWWBWW","WBBBWW","BBBBWW","WWBWWW","WWWWBB"},
				{"BWWBWB","WBBWBW","BWBBWB","BBBWBB","WWBBBW","WBWBBB"},	
				{"WWBWWB","BBBWBB","BBWBBW","BWWBBW","BWBWWB","BBBBWW"},	
				{"WWWBWB","WWWWBB","WWBWWB","BBWWWB","BWWWWB","WBWWBB"},	
				{"BBWBWW","BWBBWB","BWWBBW","WBWWBW","WBWWWW","WWWBWB"},
				{"BBWBBB","WBWBWW","BBBWBB","WBWWWW","WWBBWW","WBWBWB"},	
				{"WWWWBW","BWBWBW","WBWBBW","BBWWWB","WWWBWB","WBBWWB"},	
				{"WBWWBW","BWBBBB","BWWWWW","BWWWWW","BWBWBB","WBBWBW"},	
				{"WWWBBB","BWBWWB","BBBWBW","BBBWBW","BBWWBW","BWWBBB"},
				{"WBWBWW","BWBBWB","BWBWBW","BWWBBB","BWWWWW","BBWBBB"},	
				{"WWWBWB","BBWWWB","BWWBWB","WBWBBW","BBWBWB","WBBBBW"},	
				{"BWBWWB","BWBBBB","WWBWWB","BBWWBW","WWWBBW","WWBBBB"}
		};
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the pattern:").toUpperCase().replace(" ", "");
		String[] solution = getSolution(input);
		while(solution == null)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the pattern:").toUpperCase().replace(" ", "");
			solution = getSolution(input);
		}
		JOptionPane.showMessageDialog(null, solution[0] + "\n" + solution[1] + "\n" + solution[2]);
	}
	private String[] getSolution(String i)
	{
		if(i.length() == 16)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("WB".indexOf(i.charAt(aa)) < 0)
					return null;
			}
			for(int aa = 0; aa < 4; aa++)
			{
				String[] grid = {i.substring(0, 4), i.substring(4, 8), i.substring(8, 12), i.substring(12)};
				for(int bb = 0; bb < grids.length; bb++)
				{
					for(int cc = 0; cc < 3; cc++)
					{
						if(grids[bb][0 + cc].contains(grid[0]) && grids[bb][1 + cc].contains(grid[1]) && grids[bb][2 + cc].contains(grid[2]) && grids[bb][3 + cc].contains(grid[3]))
						{
							return new String[] {
									"Coordinate: " + "ABC".charAt(grids[bb][0 + cc].indexOf(grid[0])) + "" +  "123".charAt(cc), 
									"Clockwise: " + aa,
									"Grid: " + "ABCDEFGHIJKLMNOP".charAt(bb)
									};
						}
					}
				}
				String temp = "";
				for(int bb = 0; bb < 4; bb++)
				{
					for(int cc = 0; cc < 4; cc++)
						temp = temp + "" + i.charAt(12 - (cc * 4) + bb);
				}
				i = temp.toUpperCase();
			}
		}
		return null;
	}
}
