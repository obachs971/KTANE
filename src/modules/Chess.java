package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Chess 
{
	private final BombEdgework ew;
	public Chess(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		char[][] board =
			{
					{'1', '1', '1', '1', '1', '1'},
					{'1', '1', '1', '1', '1', '1'},
					{'1', '1', '1', '1', '1', '1'},
					{'1', '1', '1', '1', '1', '1'},
					{'1', '1', '1', '1', '1', '1'},
					{'1', '1', '1', '1', '1', '1'}
			};
		ArrayList<int[]> coord = new ArrayList<int[]>();
		String souv = "";
		for(int aa = 0; aa < 6; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter coordinate #" + (aa + 1) + ":").toUpperCase();
			int[] v = getCoord(input, coord);
			while(v[0] == -1 || v[1] == -1)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter coordinate #" + (aa + 1) + ":").toUpperCase();
				v = getCoord(input, coord);
			}
			coord.add(v);
			souv = souv + "COORDINATE #" + (aa + 1) + ": " + input.toUpperCase() + "\n";
		}
		int[] numRQK = {1, 0, 0};
		board[coord.get(3)[1]][coord.get(3)[0]] = 'R';
		if((coord.get(4)[0] % 2) == (coord.get(4)[1] % 2))
		{
			board[coord.get(4)[1]][coord.get(4)[0]] = 'Q';
			numRQK[1]++;
		}
		else
		{
			board[coord.get(4)[1]][coord.get(4)[0]] = 'R';
			numRQK[0]++;
		}
		if(numRQK[1] > 0)
			board[coord.get(0)[1]][coord.get(0)[0]] = 'K';
		else
			board[coord.get(0)[1]][coord.get(0)[0]] = 'B';
		if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1)
		{
			board[coord.get(1)[1]][coord.get(1)[0]] = 'R';
			numRQK[0]++;
		}
		else
		{
			board[coord.get(1)[1]][coord.get(1)[0]] = 'N';
			numRQK[2]++;
		}
		if(numRQK[0] < 2)
		{
			board[coord.get(2)[1]][coord.get(2)[0]] = 'Q';
			numRQK[1]++;
		}
		else
			board[coord.get(2)[1]][coord.get(2)[0]] = 'K';
		if(numRQK[1] == 0)
			board[coord.get(5)[1]][coord.get(5)[0]] = 'Q';
		else if(numRQK[2] == 0)
			board[coord.get(5)[1]][coord.get(5)[0]] = 'N';
		else
			board[coord.get(5)[1]][coord.get(5)[0]] = 'B';

		for(int aa = 0; aa < coord.size(); aa++)
		{
			switch(board[coord.get(aa)[1]][coord.get(aa)[0]])
			{
				case 'R':
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "N", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "E", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "S", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "W", board);
					break;
				case 'B':
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "NE", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "SE", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "SW", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "NW", board);	
					break;
				case 'Q':
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "N", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "NE", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "E", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "SE", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "S", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "SW", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "W", board);
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], "NW", board);
					break;
				case 'K':
					int[][] temp1 = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], temp1, board);
					break;
				case 'N':
					int[][] temp2 = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
					board = movePiece(coord.get(aa)[1], coord.get(aa)[0], temp2, board);
					break;
			}
		}
		String out = "";
		for(int aa = 0; aa < board.length; aa++)
		{
			for(int bb = 0; bb < board[aa].length; bb++)
			{
				if(board[aa][bb] == '1')
				{
					out = "ABCDEF".charAt(bb) + "" + "654321".charAt(aa);
					break;
				}
			}
			if(out.length() > 0)
				break;
		}
		JOptionPane.showMessageDialog(null, "Submit this coordinate: " + out);
		return souv;
	}
	private int[] getCoord(String i, ArrayList<int[]> coord)
	{
		if(i.length() == 2)
		{
			int[] temp = {"ABCDEF".indexOf(i.charAt(0)), "654321".indexOf(i.charAt(1))};
			if(!(coord.contains(temp)))
				return temp;
		}
		return new int[] {-1, -1};
	}
	private char[][] movePiece(int row, int col, String move, char[][] board)
	{
		int x, y;
		switch(move)
		{
			case "N":
				y = row - 1;
				while(y >= 0)
				{
					if(board[y][col] != '0' && board[y][col] != '1')
						break;
					board[y][col] = '0';
					y--;
				}
				break;
			case "NE":
				x = col + 1;
				y = row - 1;
				while(x < board.length && y >= 0)
				{
					if(board[y][x] != '0' && board[y][x] != '1')
						break;
					board[y][x] = '0';
					x++;
					y--;
				}
				break;
			case "E":
				x = col + 1;
				while(x < board.length)
				{
					if(board[row][x] != '0' && board[row][x] != '1')
						break;
					board[row][x] = '0';
					x++;
				}
				break;
			case "SE":
				x = col + 1;
				y = row + 1;
				while(x < board.length && y < board.length)
				{
					if(board[y][x] != '0' && board[y][x] != '1')
						break;
					board[y][x] = '0';
					x++;
					y++;
				}
				break;
			case "S":
				y = row + 1;
				while(y < board.length)
				{
					if(board[y][col] != '0' && board[y][col] != '1')
						break;
					board[y][col] = '0';
					y++;
				}
				break;
			case "SW":
				x = col - 1;
				y = row + 1;
				while(x >= 0 && y < board.length)
				{
					if(board[y][x] != '0' && board[y][x] != '1')
						break;
					board[y][x] = '0';
					y++;
					x--;
				}
				break;
			case "W":
				x = col - 1;
				while(x >= 0)
				{
					if(board[row][x] != '0' && board[row][x] != '1')
						break;
					board[row][x] = '0';
					x--;
				}
				break;
			case "NW":
				x = col - 1;
				y = row - 1;
				while(x >= 0 && y >= 0)
				{
					if(board[y][x] != '0' && board[y][x] != '1')
						break;
					board[y][x] = '0';
					x--;
					y--;
				}
				break;
		}
		return board;
	}
	private char[][] movePiece(int row, int col, int[][] offsets, char[][] board)
	{
		for(int bb = 0; bb < offsets.length; bb++)
		{
			if(col + offsets[bb][0] < 6 && col + offsets[bb][0] >= 0 && row + offsets[bb][1] < 6 && row + offsets[bb][1] >= 0)
			{
				if(board[row + offsets[bb][1]][col + offsets[bb][0]] == '1')
					board[row + offsets[bb][1]][col + offsets[bb][0]] = '0';
			}
		}
		return board;
	}
}
