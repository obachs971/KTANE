package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import misc.PlayType;

public class LEDGrid 
{
	private final PlayType pt;
	public LEDGrid(PlayType p)
	{
		pt = p;
	}
	
	public void run()
	{
		if(pt == PlayType.Team)
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			JButton[] jButton = new JButton[5];
			optionPane.setLayout(new GridLayout(5, 1));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int aa = 0; aa < 5; aa++)
			{
				jButton[aa] = getButton(optionPane, aa);
				optionPane.add(jButton[aa]);
			}
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the number of unlit leds");
			dialog.setVisible(true);
			JOptionPane.showMessageDialog(null, "Press these buttons: " + getPress(Integer.parseInt(optionPane.getValue().toString())));
		}
		else
		{
			String grid = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nI - Pink\nW - White\nK - Black\nEnter the colors\nin reading order:").replace(" ", "").toUpperCase();
			boolean v = valid(grid);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				grid = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nI - Pink\nW - White\nK - Black\nEnter the colors\nin reading order:").replace(" ", "").toUpperCase();
				v = valid(grid);
			}
			JOptionPane.showMessageDialog(null, "Press these buttons: " + getPress(grid));
		}
	}
	private String getPress(String grid)
	{
		String colorOrder = "ROYGBPIWK";
		int numPairs = 0;
		int[] colorAmounts = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		for(char color : grid.toCharArray())
			colorAmounts[colorOrder.indexOf(color)]++;
		for(int amount : colorAmounts)
		{
			if(amount == 2)
				numPairs++;
		}
		switch(colorAmounts[colorOrder.indexOf("K")])
		{
			case 0:
				if(grid.indexOf("O") < 0)
					return "C D A B";
				else if(colorAmounts[colorOrder.indexOf("R")] >= 3)
					return "D A C B";
				else if(numPairs >= 2)
					return "B A C D";
				else if(grid.charAt(6) == grid.charAt(7) && grid.charAt(6) == grid.charAt(8))
					return "A C D B";
				return "B C D A";
			case 1:
				if(numPairs == 0)
					return "D C B A";
				else if(grid.charAt(0) == grid.charAt(1) && grid.charAt(0) == grid.charAt(2))
					return "A D B C";
				else if(colorAmounts[colorOrder.indexOf("R")] == 3 || colorAmounts[colorOrder.indexOf("I")] == 3 || colorAmounts[colorOrder.indexOf("P")] == 3)
					return "C B A D";
				else if(colorAmounts[colorOrder.indexOf("W")] == 1 || colorAmounts[colorOrder.indexOf("B")] == 2 || colorAmounts[colorOrder.indexOf("Y")] == 3)
					return "B A D C";
				return "D B A C";
			case 2:
				if(colorAmounts[colorOrder.indexOf("P")] >= 3)
					return "A D C B";
				else if(numPairs == 2)
					return "B C A D";
				else if(grid.contains("W") && grid.contains("O") && grid.contains("I"))
					return "D B C A";
				else if(colorAmounts[colorOrder.indexOf("G")] == 1 || colorAmounts[colorOrder.indexOf("Y")] == 2 || colorAmounts[colorOrder.indexOf("R")] == 3 || colorAmounts[colorOrder.indexOf("B")] == 4)
					return "C A D B";
				return "C D B A";
			case 3:
				if(colorAmounts[colorOrder.indexOf("O")] == 2)
					return "B D A C";
				else if(numPairs > 1)
					return "C A B D";
				else if(grid.indexOf("P") < 0)
					return "D C A B";
				else if(grid.contains("R") && grid.contains("Y"))
					return "A C B D";
				return "B D C A";
			default:
				if(grid.charAt(3) == grid.charAt(4) && grid.charAt(3) == grid.charAt(5))
					return "B C D A";
				else if(colorAmounts[colorOrder.indexOf("G")] >= 2)
					return "A B D C";
				else if(numPairs == 2)
					return "C B D A";
				else if(grid.indexOf("I") < 0)
					return "D A B C";
				return "A B C D";
		}
	}
	private String getPress(int numUnlit)
	{
		switch(numUnlit)
		{
			case 0:
				if(JOptionPane.showConfirmDialog(null, "No Orange?") == 0)
					return "C D A B";
				else if(JOptionPane.showConfirmDialog(null, "3 or More Red?") == 0)
					return "D A C B";
				else if(JOptionPane.showConfirmDialog(null, "2 or More Pairs?") == 0)
					return "B A C D";
				else if(JOptionPane.showConfirmDialog(null, "Bottom Row Same Color?") == 0)
					return "A C D B";
				return "B C D A";
			case 1:
				if(JOptionPane.showConfirmDialog(null, "Every LED is Unique?") == 0)
					return "D C B A";
				else if(JOptionPane.showConfirmDialog(null, "Top Row Same Color?") == 0)
					return "A D B C";
				else if(JOptionPane.showConfirmDialog(null, "Exactly 3 Red,\nor 3 Pink, or 3 Purple?") == 0)
					return "C B A D";
				else if(JOptionPane.showConfirmDialog(null, "Exactly 1 White,\nor 2 Blue, or 3 Yellow?") == 0)
					return "B A D C";
				return "D B A C";
			case 2:
				if(JOptionPane.showConfirmDialog(null, "3 or More Purple?") == 0)
					return "A D C B";
				else if(JOptionPane.showConfirmDialog(null, "Exactly 2 Pairs?") == 0)
					return "B C A D";
				else if(JOptionPane.showConfirmDialog(null, "At Least 1 White,\n1 Orange, and 1 Pink?") == 0)
					return "D B C A";
				else if(JOptionPane.showConfirmDialog(null, "Exactly 1 Green, or 2 Yellow,\nor 3 Red, or 4 Blue?") == 0)
					return "C A D B";
				return "C D B A";
			case 3:
				if(JOptionPane.showConfirmDialog(null, "Exactly 2 Orange?") == 0)
					return "B D A C";
				else if(JOptionPane.showConfirmDialog(null, "More Than 1 Pair?") == 0)
					return "C A B D";
				else if(JOptionPane.showConfirmDialog(null, "No Purple?") == 0)
					return "D C A B";
				else if(JOptionPane.showConfirmDialog(null, "At Least 1 Red and 1 Yellow?") == 0)
					return "A C B D";
				return "B D C A";
			default:
				if(JOptionPane.showConfirmDialog(null, "Center Row Same Color?") == 0)
					return "B C D A";
				else if(JOptionPane.showConfirmDialog(null, "2 or More Green?") == 0)
					return "A B D C";
				else if(JOptionPane.showConfirmDialog(null, "Exactly 2 Pairs?") == 0)
					return "C B D A";
				else if(JOptionPane.showConfirmDialog(null, "No Pink?") == 0)
					return "D A B C";
				return "A B C D";
		}
	}
	private boolean valid(String i)
	{
		if(i.length() == 9)
		{
			int unlit = 0;
			for(int aa = 0; aa < 9; aa++)
			{
				if("ROYGBPIWK".indexOf(i.charAt(aa)) < 0)
					return false;
				if(i.charAt(aa) == 'K')
					unlit++;
			}
			return (unlit < 5);
		}
		return false;
	}
	private JButton getButton(final JOptionPane optionPane, int value) {
	    final JButton button = new JButton();
	    button.setText(value + "");
	    button.setSize(50, 50);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(value);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
