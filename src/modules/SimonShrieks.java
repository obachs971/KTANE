package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class SimonShrieks 
{
	private final String[][][] chart =
		{
				{
					{"", "", "15", "36", "24", "", "", ""},
					{"", "", "15", "", "2346", "", "0", ""},
					{"", "", "15", "", "2346", "", "0", ""},
					{"", "", "1", "5", "236", "4", "0", ""},
					{"", "", "1", "", "2356", "", "04", ""},
					{"", "", "1", "", "2356", "", "04", ""},
					{"", "", "", "", "35", "26", "04", ""},
				},
				{
					{"0", "", "1356", "", "24", "", "", ""},
					{"", "", "15", "36", "24", "", "", "0"},
					{"", "", "15", "", "236", "4", "0", ""},
					{"", "", "15", "", "236", "", "04", ""},
					{"", "", "1", "5", "236", "", "04", ""},
					{"", "1", "", "", "35", "26", "04", ""},
					{"1", "", "", "", "35", "", "0246", ""}
				},
				{
					{"0", "", "1356", "", "24", "", "", ""},
					{"0", "", "1356", "", "2", "4", "", ""},
					{"", "", "15", "36", "2", "", "4", "0"},
					{"", "", "15", "", "236", "", "04", ""},
					{"", "1", "5", "", "3", "26", "04", ""},
					{"1", "", "", "5", "3", "", "0246", ""},
					{"1", "", "", "", "35", "", "0246", ""}
				},
				{
					{"0", "", "1356", "", "2", "", "", ""},
					{"0", "", "1356", "", "2", "", "4", ""},
					{"0", "", "1356", "", "2", "", "4", ""},
					{"", "1", "5", "3", "", "2", "4", "0"},
					{"1", "", "5", "", "3", "", "0246", ""},
					{"1", "", "5", "", "3", "", "0246", ""},
					{"1", "", "", "", "3", "", "0246", ""}
				},
				{
					{"04", "", "1356", "", "2", "", "", ""},
					{"0", "", "1356", "", "2", "", "", "4"},
					{"0", "16", "35", "", "", "2", "4", ""},
					{"016", "", "35", "", "", "", "24", ""},
					{"1", "", "5", "3", "", "", "24", "06"},
					{"1", "5", "", "", "3", "", "0246", ""},
					{"15", "", "", "", "3", "", "0246", ""}
				},
				{
					{"04", "", "1356", "", "2", "", "", ""},
					{"04", "16", "35", "", "", "2", "", ""},
					{"016", "", "35", "", "", "", "2", "4"},
					{"016", "", "35", "", "", "", "24", ""},
					{"016", "5", "3", "", "", "", "24", ""},
					{"15", "", "", "3", "", "", "24", "06"},
					{"15", "", "", "", "3", "", "0246", ""}
				},
				{
					{"04", "16", "35", "", "", "", "", ""},
					{"0146", "", "35", "", "", "", "2", ""},
					{"0146", "", "35", "", "", "", "2", ""},
					{"016", "5", "3", "", "", "", "2", "4"},
					{"0156", "", "3", "", "", "", "24", ""},
					{"0156", "", "3", "", "", "", "24", ""},
					{"15", "", "", "", "", "", "24", "06"}
				}
		};
	private final String[][][] colors =
		{
				{
					{"C B Y W", "R M G"},
					{"B Y", "M R W G C"},
					{"M R", "B W G C Y"},
					{"B R W G", "M C Y"},
					{"M Y W G", "C B R"},
					{"C G Y B R W", "M"},
					{"M G W R", "C Y B"}
				},
				{
					{"R W C Y", "M B G"},
					{"Y R", "W M B G C"},
					{"B C M Y", "W G R"},
					{"W R", "M Y C B G"},
					{"Y C B M", "R W G"},
					{"M W", "C Y B G R"},
					{"C Y R W", "B M G"}
				},
				{
					{"W R M C", "B Y G"},
					{"G R", "W B Y C M"},
					{"W B C R", "Y G M"},
					{"W Y C B M G", "R"},
					{"R G", "Y C W B M"},
					{"C Y W B G M", "R"},
					{"Y C B R M G", "W"}
				},
				{
					{"C G", "W B R M Y"},
					{"W C", "B G R M Y"},
					{"W G", "C B M Y R"},
					{"W Y B M", "C R G"},
					{"C G", "R Y B W M"},
					{"Y G M B", "C W R"},
					{"C G B R", "Y M W"}
				},
				{
					{"G M", "W B R C Y"},
					{"W M", "G R C Y B"},
					{"W Y R M", "C B G"},
					{"W Y M B", "R C G"},
					{"Y B M G", "R W C"},
					{"C G", "W Y M B R"},
					{"Y R G B", "C M W"}
				},
				{
					{"W G C Y", "R B M"},
					{"W C B M", "R G Y"},
					{"W C B M", "R Y G"},
					{"R C", "W Y M G B"},
					{"R G B C", "W Y M"},
					{"R M B C", "G W Y"},
					{"Y B C W", "M G R"}
				},
				{
					{"G C", "W B R Y M"},
					{"W R C B Y M", "G"},
					{"W R C B", "Y M G"},
					{"R C", "W Y M G B"},
					{"W R M B", "Y G C"},
					{"W R M G", "Y B C"},
					{"Y R", "G M B C W"}
				},
		};
	private final BombEdgework ew;
	public SimonShrieks(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{	
		String input = JOptionPane.showInputDialog("Enter the numbers flashing\nclockwise from the arrow:").replace(" ", "");
		boolean v = valid(input, 4);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the numbers flashing\nclockwise from the arrow:").replace(" ", "");
			v = valid(input, 4);
		}
		String souv = "FINAL SEQUENCE:\n" + input.charAt(0);
		int row = -1;
		int col = -1; 
		int colorVow = 1;
		if(ew.numCharsInSN("AEIOU") > 0)
			colorVow = 0;
		switch(input.charAt(0))
		{
			case '0':
				row = 0;
				col = 0;
				break;
			case '1':
				row = 0;
				col = 6;
				break;
			case '2':
				row = 6;
				col = 0;
				break;
			case '3':
				row = 6;
				col = 6;
				break;
			case '4':
				row = 3;
				col = 0;
				break;
			case '5':
				row = 3;
				col = 6;
				break;
			case '6':
				row = 3;
				col = 3;
				break;
		}
		String moves = "";
		for(int aa = 1; aa < 4; aa++)
		{
			souv = souv + " " + input.charAt(aa);
			moves = moves + "" + input.charAt(aa);
		}
		int direction;
		for(int bb = 0; bb < 3; bb++)
		{
			direction = getDir(moves.charAt(bb), row, col);
			switch(direction)
			{
				case 0:
					row--;
					break;
				case 1:
					row--;
					col++;
					break;
				case 2:
					col++;
					break;
				case 3:
					row++;
					col++;
					break;
				case 4:
					row++;
					break;
				case 5:
					row++;
					col--;
					break;
				case 6:
					col--;
					break;
				case 7:
					row--;
					col--;
					break;
			}
		}
		String output = colors[row][col][colorVow];
		for(int cc = 0; cc < 2; cc++)
		{
			moves = JOptionPane.showInputDialog("Press these colors: " + output + "\nEnter the 2 new flashing numbers\nclockwise from the arrow:").replace(" ", "");
			v = valid(moves, 2);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				moves = JOptionPane.showInputDialog("Press these colors: " + output + "\nEnter the 2 new flashing numbers\nclockwise from the arrow:").replace(" ", "");
				v = valid(moves, 2);
			}
			for(int dd = 0; dd < 2; dd++)
			{
				souv = souv + " " + moves.charAt(dd);
				direction = getDir(moves.charAt(dd), row, col);
				switch(direction)
				{
					case 0:
						row--;
						break;
					case 1:
						row--;
						col++;
						break;
					case 2:
						col++;
						break;
					case 3:
						row++;
						col++;
						break;
					case 4:
						row++;
						break;
					case 5:
						row++;
						col--;
						break;
					case 6:
						col--;
						break;
					case 7:
						row--;
						col--;
						break;
				}
			}
			output = colors[row][col][colorVow];
		}
		JOptionPane.showMessageDialog(null, "Press these colors: " + output);
		return souv;
	}
	private int getDir(char m, int row, int col)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < chart[row][col][i].length(); j++)
			{
				if(m == chart[row][col][i].charAt(j))
					return i;
			}
		}
		return -1;
	}

	private boolean valid(String i, int len)
	{
		if(i.length() == len)
		{
			for(char num : i.toCharArray())
			{
				if("0123456".indexOf(num) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
