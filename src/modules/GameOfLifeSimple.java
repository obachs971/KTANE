package modules;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameOfLifeSimple 
{
	private final double r;
	public GameOfLifeSimple(double resizer)
	{
		r = resizer;
	}
	public void run()
	{
		char[][] grid = 
			{
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
			};
		for(int aa = 1; aa < 9; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter the positions of the\nwhite spaces on row #" + aa + ":").toUpperCase();
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the positions of the\nwhite spaces on row #" + aa + ":").toUpperCase();
				v = valid(input);
			}
			input = input.replace("A", "1").replace("B", "2").replace("C", "3").replace("D", "4").replace("E", "5").replace("F", "6");
			for(int bb = 0; bb < input.length(); bb++)
				grid[aa]["-123456".indexOf(input.charAt(bb))] = 'W';
			//System.out.println(grid[aa][1] + "" + grid[aa][2] + "" + grid[aa][3] + "" + grid[aa][4] + "" + grid[aa][5] + "" + grid[aa][6]);
		}
		//System.out.println("-------------------------");
		grid = solveGrid(grid);
		displaySolution(grid);
	}
	private char[][] solveGrid(char[][] grid)
	{
		char[][] solution = new char[8][6];
		for(int aa = 1; aa < 9; aa++)
		{
			for(int bb = 1; bb < 7; bb++)
			{
				String temp = grid[aa - 1][bb - 1] + "" + grid[aa - 1][bb] + "" + grid[aa - 1][bb + 1] + "" + grid[aa][bb - 1] + "" + grid[aa][bb + 1] + "" + grid[aa + 1][bb - 1] + "" + grid[aa + 1][bb] + "" + grid[aa + 1][bb + 1];
				int numW = 0;
				for(int cc = 0; cc < temp.length(); cc++)
				{
					if(temp.charAt(cc) == 'W')
						numW++;
				}
				if(grid[aa][bb] == 'B')
				{
					if(numW == 3)
						solution[aa - 1][bb - 1] = 'W';
					else
						solution[aa - 1][bb - 1] = 'B';
				}	
				else
				{
					if(numW == 2 || numW == 3)
						solution[aa - 1][bb - 1] = 'W';
					else
						solution[aa - 1][bb - 1] = 'B';
				}
			}
		}
		return solution;
	}
	private void displaySolution(char[][] grid)
	{
		ImageIcon[][] solution = new ImageIcon[8][];
		JLabel[][] solutionLabel = new JLabel[8][];
		JFrame solutionFrame = new JFrame();
		solutionFrame.setLayout(new GridLayout(8,6));
		String out = "";
		for(int aa = 0; aa < 8; aa++)
		{
			out = out + "ROW #" + (aa + 1) + ": ";
			solution[aa] = new ImageIcon[6];
			solutionLabel[aa] = new JLabel[6];
			for(int bb = 0; bb < 6; bb++)
			{
				solution[aa][bb] = new ImageIcon("img/GameOfLife" + grid[aa][bb] + ".jpg");
				Image image = solution[aa][bb].getImage();
				image = image.getScaledInstance((int)(solution[aa][bb].getIconWidth() / r), (int)(solution[aa][bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				solution[aa][bb] = new ImageIcon(image);
				solutionLabel[aa][bb] = new JLabel();
				solutionLabel[aa][bb].setIcon(solution[aa][bb]);
				solutionFrame.add(solutionLabel[aa][bb]);
				if(grid[aa][bb] == 'W')
					out = out + "" + (bb + 1) + " ";
			}
			out = out + "\n";
		}
		solutionFrame.pack();
		solutionFrame.setVisible(true);
		JOptionPane.showMessageDialog(null, out);
		solutionFrame.setVisible(false);
	}
	private boolean valid(String i)
	{
		if(i.length() < 7)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ABCDEF".indexOf(i.charAt(aa)) < 0 && "123456".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;	
		}
		return false;
	}
}
