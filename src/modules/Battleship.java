package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Battleship 
{
	private final BombEdgework ew;
	public Battleship(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		//Starting input
		char[][] board =
			{
					{'N', 'N', 'N', 'N', 'N'},
					{'N', 'N', 'N', 'N', 'N'},
					{'N', 'N', 'N', 'N', 'N'},
					{'N', 'N', 'N', 'N', 'N'},
					{'N', 'N', 'N', 'N', 'N'}
			};
		int[][] numbers = new int[2][5];
		int[] ships = new int[4];
		String[] display = {"TOP", "LEFT"};
		for(int aa = 0; aa < 2; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter the number on the " + display[aa] + ":");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the number on the " + display[aa] + ":");
				v = v1(input);
			}
			for(int bb = 0; bb < input.length(); bb++)
				numbers[aa][bb] = "01234".indexOf(input.charAt(bb));
		}
		String input = JOptionPane.showInputDialog("Enter the boat amounts (4 - 1):");
		boolean v = v2(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the boat amounts (4 - 1):");
			v = v2(input);
		}
		for(int aa = 0; aa < input.length(); aa++)
			ships[aa] = "0123456789".indexOf(input.charAt(aa));
		
		//Start filling some things out before doing radar
		board = getDefiniteBoard(board, numbers);
		
		//Time to calculate what spots to radar, and to see if that spot hasn't been filled in yet
		ArrayList<int[]> radar = new ArrayList<int[]>();
		for(int aa = 0; aa < ew.numSNLETS() && aa < ew.numSNDIGS(); aa++)
		{
			radar.add(new int[] {"ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNLET(aa)) % 5, "1234567890".indexOf((ew.getSNDIG(aa) + "")) % 5});
			if(board[radar.get(radar.size() - 1)[1]][radar.get(radar.size() - 1)[0]] != 'N')
				radar.remove(radar.size() - 1);
		}
		int[] temp = {ew.numPorts(), ew.numInd() + ew.BAT()};
		for(int aa = 0; aa < 2; aa++)
		{
			while(temp[aa] < 1)
				temp[aa] += 5;
			while(temp[aa] > 5)
				temp[aa] -= 5;
			temp[aa]--;
		}
		if(board[temp[1]][temp[0]] == 'N')
			radar.add(temp);
		for(int aa = 0; aa < radar.size(); aa++)
		{
			for(int bb = aa + 1; bb < radar.size(); bb++)
			{
				if(radar.get(aa)[0] == radar.get(bb)[0] && radar.get(aa)[1] == radar.get(bb)[1])
				{
					radar.remove(bb);
					bb--;
				}
			}
		}
		//Get input if the radar does have some spaces to uncover
		if(radar.size() > 0)
		{
			String out = "";
			for(int aa = 0; aa < radar.size(); aa++)
				out = out + "" + "ABCDE".charAt(radar.get(aa)[0]) + "" + "12345".charAt(radar.get(aa)[1]) + " ";
			input = JOptionPane.showInputDialog("W - Water\nS - Ship\nRadar these spots: " + out + "\nIn that order, enter the spots:").toUpperCase();
			v = v3(input, radar.size());
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("W - Water\nS - Ship\nRadar these spots: " + out + "\nIn that order, enter the spots:").toUpperCase();
				v = v3(input, radar.size());
			}
			for(int aa = 0; aa < radar.size(); aa++)
				board = placePiece(board, radar.get(aa)[1], radar.get(aa)[0], input.charAt(aa));
		}
		board = getSolution(board, numbers, ships);
		if(!(isValid(board, numbers, ships)))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			run();
		}
		else
		{
			String out = "Torpedo these spots:";
			for(int aa = 0; aa < 5; aa++)
			{
				out = out + "\nRow #" + (aa + 1) + ": ";
				String tempout = "";
				for(int bb = 0; bb < 5; bb++)
				{
					if(board[aa][bb] == 'S')
						tempout = tempout + "" + (bb + 1) + " ";
				}
				if(tempout.length() == 0)
					out = out + "SKIP";
				else
					out = out + "" + tempout.toUpperCase();
			}
			JOptionPane.showMessageDialog(null, out);
		}
	}
	//Returns the solution of the puzzle
	private char[][] getSolution(char[][] board, int[][] numbers, int[] ships)
	{
		board = getDefiniteBoard(board, numbers);
		//print(board);
		boolean done = isFinished(board);
		while(!(done)) 
		{
			board = guessing(board, numbers, ships);
			board = getDefiniteBoard(board, numbers);
			//print(board);
			done = isFinished(board);
		}
		return board;
	}
	private char[][] guessing(char[][] board, int[][] numbers, int[] ships)
	{
		char[][] temp = new char[5][5];
		for(int aa = 0; aa < 5; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
				temp[aa][bb] = board[aa][bb];
		}
		int[] cur = {-1, -1};
		for(int aa = 0; aa < 5; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
			{
				if(temp[aa][bb] == 'N')
				{
					cur[0] = aa;
					cur[1] = bb;
					break;
				}
			}
			if(cur[0] >= 0)
				break;
		}
		temp = placePiece(temp, cur[0], cur[1], 'W');
		temp = getDefiniteBoard(temp, numbers);
		boolean done = isFinished(temp);
		while(!(done))
		{
			temp = guessing(temp, numbers, ships);
			temp = getDefiniteBoard(temp, numbers);
			done = isFinished(temp);
		}
		done = isValid(temp, numbers, ships);
		if(!(done))
			board = placePiece(board, cur[0], cur[1], 'S');
		else
			board = placePiece(board, cur[0], cur[1], 'W');
		return board;
	}
	//Returns a board that fills in spaces that are definitely water/ships
	private char[][] getDefiniteBoard(char[][] board, int[][] numbers)
	{
		boolean flag = true;
		while(flag)
		{
			flag = false;
			int[][] shipsPlaced = getSum(board, 'S');
			int[][] waterPlaced = getSum(board, 'W');
			for(int aa = 0; aa < 2; aa++)
			{
				for(int bb = 0; bb < 5; bb++)
				{
					if((shipsPlaced[aa][bb] + waterPlaced[aa][bb]) < 5)
					{
						if(shipsPlaced[aa][bb] == numbers[aa][bb])
						{
							flag = true;
							if(aa == 0)
							{
								for(int cc = 0; cc < 5; cc++)
								{
									if(board[cc][bb] == 'N')
										board = placePiece(board, cc, bb, 'W');
								}
							}
							else
							{
								for(int cc = 0; cc < 5; cc++)
								{
									if(board[bb][cc] == 'N')
										board = placePiece(board, bb, cc, 'W');
								}
							}
							break;
						}//end of placing waters
						else if((5 - waterPlaced[aa][bb]) == numbers[aa][bb])
						{
							flag = true;
							if(aa == 0)
							{
								for(int cc = 0; cc < 5; cc++)
								{
									if(board[cc][bb] == 'N')	
										board = placePiece(board, cc, bb, 'S');
								}
							}
							else
							{
								for(int cc = 0; cc < 5; cc++)
								{
									if(board[bb][cc] == 'N')
										board = placePiece(board, bb, cc, 'S');
								}
							}
							break;
						}//End of placing ships
					}//End of placing in empty spaces
				}//End of For Loop B
			}//End of For Loop A
		}//End of While Loop
		return board;
	}
	//Returns the sum of the char in the board for each col/row
	private int[][] getSum(char[][] board, char c)
	{
		int[][] sum = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
		for(int aa = 0; aa < 5; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
			{
				if(board[aa][bb] == c)
					sum[0][bb]++;
				if(board[bb][aa] == c)
					sum[1][bb]++;
			}
		}
		return sum;
	}
	private char[][] placePiece(char[][] board, int row, int col, char p)
	{
		if(p == 'W')
			board[row][col] = 'W';
		else
		{
			board[row][col] = 'S';
			if(row > 0 && col > 0)
				board[row - 1][col - 1] = 'W';
			if(row < 4 && col > 0)
				board[row + 1][col - 1] = 'W';
			if(row > 0 && col < 4)
				board[row - 1][col + 1] = 'W';
			if(row < 4 && col < 4)
				board[row + 1][col + 1] = 'W';
		}
		return board;
	}
	private boolean isFinished(char[][] board)
	{
		for(int aa = 0; aa < 5; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
			{
				if(board[aa][bb] == 'N')
					return false;
			}
		}
		return true;
	}
	private boolean isValid(char[][] board, int[][] numbers, int[] ships)
	{
		int[][] sums = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
		char[][] temp = new char[5][5];
		for(int aa = 0; aa < 5; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
			{
				if(board[aa][bb] == 'S')
					sums[0][bb]++;
				if(board[bb][aa] == 'S')
					sums[1][bb]++;
				temp[aa][bb] = board[aa][bb];
			}
		}
		for(int aa = 0; aa < 2; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
			{
				if(sums[aa][bb] != numbers[aa][bb])
					return false;
			}
		}
		int[] shipSum = {0, 0, 0, 0};
		for(int aa = 0; aa < 5; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
			{
				if(temp[aa][bb] == 'S')
				{
					int[] cur = {aa, bb};
					int length = 3;
					temp[aa][bb] = 'N';
					if(cur[0] < 4 && temp[cur[0] + 1][cur[1]] == 'S')
					{
						cur[0]++;
						while(cur[0] < 5 && temp[cur[0]][cur[1]] == 'S')
						{
							temp[cur[0]][cur[1]] = 'N';
							length--;
							cur[0]++;
						}
					}
					else if(cur[1] < 4 && temp[cur[0]][cur[1] + 1] == 'S')
					{
						cur[1]++;
						while(cur[1] < 5 && temp[cur[0]][cur[1]] == 'S')
						{
							temp[cur[0]][cur[1]] = 'N';
							length--;
							cur[1]++;
						}
					}
					if(length < 0)
						return false;
					shipSum[length]++;
				}
			}
		}
		for(int aa = 0; aa < 4; aa++)
		{
			if(ships[aa] != shipSum[aa])
				return false;
		}
		return true;
	}
	private boolean v1(String i)
	{
		if(i.length() == 5)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("01234".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 4)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("012346789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v3(String i, int size)
	{
		if(i.length() == size)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("WS".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	/*private void print(char[][] board)
	{
		for(int aa = 0; aa < 5; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
			{
				System.out.print(board[aa][bb] + " ");
			}
			System.out.println("");
		}
		System.out.println("---------");
	}*/
}
