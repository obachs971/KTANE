package modules;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class TicTacToe 
{
	private final String[][] table =
		{
				{"9", "3", "3", "9", "8", "1"},
				{"5", "6", "6", "7", "1", "2"},
				{"7", "8", "2", "1", "5", "8"},
				{"4", "5", "7", "8", "9", "6"},
				{"1", "4", "1", "6", "7", "3"},
				{"8", "7", "5", "2", "4", "4"},
				{"6", "1", "8", "4", "3", "9"},
				{"2", "2", "9", "5", "2", "5"},
				{"3", "9", "4", "3", "6", "7"}
		};
	private final BombEdgework ew;
	public TicTacToe(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		//Finding the starting row
		int row, col, x = 0, o = 0;
		String nums, board = "";
		if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0)
			nums = "56789";
		else
			nums = "1234";
		if(ew.findPort("PARALLEL") > 0)
			nums = nums.replace("1234", "24").replace("56789", "68");
		else
			nums = nums.replace("1234", "13").replace("56789", "579");
		if(ew.numUnlit() > ew.numLit())
			row = "123456789".indexOf(nums.charAt(0));
		else if(ew.numLit() > ew.numUnlit())
			row = "123456789".indexOf(nums.charAt(nums.length() - 1));
		else
		{
			row = 0;
			for(int aa = 0; aa < nums.length(); aa++)
				row += ("0123456789".indexOf(nums.charAt(aa)));
			row /= nums.length();
			row--;
		}
		//Entering the board
		for(int aa = 0; aa < 3; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter row #" + (aa + 1) + ":").toUpperCase();
			boolean v = v1(input, board);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter row #" + (aa + 1) + ":").toUpperCase();
				v = v1(input, board);
			}
			board = board + "" + input.toUpperCase();
		}
		String souv = "BOARD SETUP:\n" + board.charAt(0) + " " + board.charAt(1) + " " + board.charAt(2) + "\n" + board.charAt(3) + " " + board.charAt(4) + " " + board.charAt(5) + "\n" + board.charAt(6) + " " + board.charAt(7) + " " + board.charAt(8);
		for(int aa = 0; aa < board.length(); aa++)
		{
			if(board.charAt(aa) == 'X')
				x++;
			else if(board.charAt(aa) == 'O')
				o++;
		}
		String[] pos = {"TL", "TM", "TR", "ML", "MM", "MR", "BL", "BM", "BR"};
		while((x + o) < 9)
		{
			String out, input = JOptionPane.showInputDialog("Enter the next letter:").toUpperCase();
			boolean v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the next letter:").toUpperCase();
				v = v2(input);
			}
			//Everytiume a new letter is generated, a new column and letter is generated.
			col = getNewCol(board, x, o, input);
			row = getNewRow(board, row, col);
			//If there is a Tic Tac Toe, check if the next would be a Tic Tac Toe.
			if(isTicTacToe(board.replace(table[row][col], input), board.indexOf(table[row][col])))
			{
				//What happens when you pass is the the next letter will ALWAYS be the opposite letter. X -> O and O -> X
				//From this, we can predict what the next move should be
				input = "XO".charAt(("XO".indexOf(input) + 1) % 2) + "";
				col = getNewCol(board, x, o, input);
				row = getNewRow(board, row, col);
				//If there is another Tic Tac Toe, perform a double pass
				if(isTicTacToe(board.replace(table[row][col], input), board.indexOf(table[row][col])))
					out = "Double Pass";
				else //Otherwise, pass then press the space with that number
				{
					out = "Pass\nPress " + pos[board.indexOf(table[row][col])];
					board = board.replace(table[row][col], input);
				}
			}
			else //Otherwise, press the space with that number
			{
				out = "Press " + pos[board.indexOf(table[row][col])];
				board = board.replace(table[row][col], input);
			}
			//This checks if the rest of the board can be completed with double passes.
			if(isFinished(board))
			{
				for(int aa = x + o; aa < 8; aa++)
					out = out + "\nDouble Pass";
				x = 9;
				JOptionPane.showMessageDialog(null, out);
			}
			else if((x + o) == 7 && canPlace(board))
			{
				for(int aa = 0; aa < board.length(); aa++)
				{
					if("123456789".indexOf(board.charAt(aa)) >= 0)
					{
						out = out + "\nPress " + pos[aa];
						break;
					}
				}
				x = 9;
				JOptionPane.showMessageDialog(null, out);
			}
			else if(out.equalsIgnoreCase("DOUBLE PASS"))//Otherwise, checks if the move is a double pass.
			{
				//Doing a double pass will cause the board to randomly pick a number that is not being used and replace it with either an X or an O
				//So we need the input of where and what the letter is
				input = JOptionPane.showInputDialog("TL, TM, TR, ML, MM,\nMR, BL, BM, BR\n" + out + "\nEnter the letter and position (spaces):").toUpperCase();
				v = v3(input, board);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("TL, TM, TR, ML, MM,\nMR, BL, BM, BR\n" + out + "\nEnter the letter and position (spaces):").toUpperCase();
					v = v3(input, board);
				}
				String[] temp = input.split(" ");
				input = temp[0];
				for(int aa = 0; aa < pos.length; aa++)
				{
					if(pos[aa].equals(temp[1]))
					{
						board = board.replace(board.charAt(aa), input.charAt(0));
						break;
					}
				}
				//Rechecking to see if the rest of the board can be completed
				if(isFinished(board) && (x + o) < 8) 
				{
					out = "";
					for(int aa = x + o; aa < 8; aa++)
						out = out + "Double Pass\n";
					x = 9;
					JOptionPane.showMessageDialog(null, out);
				}
				else if((x + o) == 7 && canPlace(board))
				{
					for(int aa = 0; aa < board.length(); aa++)
					{
						if("123456789".indexOf(board.charAt(aa)) >= 0)
						{
							out = "Press " + pos[aa];
							break;
						}
					}
					x = 9;
					JOptionPane.showMessageDialog(null, out);
				}
			}
			else //Outputs the spot to press.
				JOptionPane.showMessageDialog(null, out);
			//Adding the number of Xs and Os to exit out of the loop.
			if(input.equals("X"))
				x++;
			else
				o++;
			//Everytime a move set is completed, go to the next row, wrapping around.
			row = (row + 1) % 9;
		}
		return souv;
	}
	//Gets the next row to check
	private int getNewRow(String board, int row, int col)
	{
		while(!(board.contains(table[row][col])))
			row = (row + 1) % 9;
		return row;
	}
	//Gets the next col to check
	private int getNewCol(String board, int x, int o, String next)
	{
		int col;
		if(next.equals("X"))
			col = 0;
		else
			col = 1;
		if(x == o)
			col += 2;
		else if(x < o)
			col += 4;
		return col;
	}
	//Returns true if there is a Tic Tac Toe on the board.
	private boolean isTicTacToe(String board, int index)
	{
		String[] all = {"012", "036", "048", "147", "246", "258", "345", "678"};
		for(int aa = 0; aa < all.length; aa++)
		{
			if(all[aa].contains(index + ""))
			{
				String temp = board.charAt("012345678".indexOf(all[aa].charAt(0))) + "" + board.charAt("012345678".indexOf(all[aa].charAt(1))) + "" + board.charAt("012345678".indexOf(all[aa].charAt(2)));
				if(temp.equals("OOO") || temp.equals("XXX"))
					return true;
			}
		}
		return false;
	}
	//Returns true if the rest of the board can't be filled with an X or an O
	private boolean isFinished(String board)
	{
		String nums = "";
		for(int aa = 0; aa < board.length(); aa++)
		{
			if("123456789".indexOf(board.charAt(aa)) >= 0)
				nums = nums + "" + board.charAt(aa);
		}
		for(int aa = 0; aa < nums.length(); aa++)
		{
			if(!(isTicTacToe(board.replace(nums.charAt(aa), 'X'), board.indexOf(nums.charAt(aa)))) || !(isTicTacToe(board.replace(nums.charAt(aa), 'O'), board.indexOf(nums.charAt(aa)))))
				return false;
		}
		return true;
	}
	//Return true if the last space on the board can be filled in with an X or an O
	private boolean canPlace(String board)
	{
		String nums = "";
		for(int aa = 0; aa < board.length(); aa++)
		{
			if("123456789".indexOf(board.charAt(aa)) >= 0)
			{
				nums = nums + "" + board.charAt(aa);
				break;
			}
		}
		//System.out.println(board);
		//System.out.println(nums);
		//System.out.println(isTicTacToe(board.replace(nums.charAt(0), 'X'), board.indexOf(nums.charAt(0))));
		//System.out.println(isTicTacToe(board.replace(nums.charAt(0), 'O'), board.indexOf(nums.charAt(0))));
		return !(isTicTacToe(board.replace(nums.charAt(0), 'X'), board.indexOf(nums.charAt(0))) || isTicTacToe(board.replace(nums.charAt(0), 'O'), board.indexOf(nums.charAt(0))));
	}
	//Validation of the board input
	private boolean v1(String i, String board)
	{
		if(i.length() == 3)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("XO".indexOf(i.charAt(aa)) < 0)
				{
					if("123456789".indexOf(i.charAt(aa)) < 0 || board.indexOf(i.charAt(aa)) >= 0)
						return false;
				}
			}
			return true;
		}
		return false;
		
	}
	//Validation of letter input
	private boolean v2(String i)
	{
		switch(i)
		{
			case "X":
			case "O":
				return true;
			default:
				return false;
		}
	}
	//Validation of double passing, making the board place a letter at a random number location
	private boolean v3(String i, String board)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2 && (conv[0].equals("X") || conv[0].equals("O")))
		{
			String[] pos = {"TL", "TM", "TR", "ML", "MM", "MR", "BL", "BM", "BR"};
			for(int aa = 0; aa < pos.length; aa++)
			{
				if(pos[aa].equals(conv[1]))
				{
					if("123456789".indexOf(board.charAt(aa)) >= 0)
						return true;
					return false;
				}
			}
		}
		return false;
	}
}
