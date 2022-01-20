package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SymbolCycle 
{
	public String run()
	{
		String[][] symbols = new String[2][];
		String[] pos = {"LEFT", "RIGHT"};
		for(int aa = 0; aa < 2; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter the symbols on the " + pos[aa] + "\nscreen (separate with commas):");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the symbols on the\n" + pos[aa] + " screen (separate with commas):");
				v = v1(input);
			}
			symbols[aa] = input.split(",");
			String out = "";
			for(int bb = 0; bb < symbols[aa].length; bb++)
				out = out + "" + (bb + 1) + ": " + symbols[aa][bb] + "\n";
			input = JOptionPane.showInputDialog(out + "Enter the symbol and the\nnumber that it occurs on (spaces):");
			v = v2(input, symbols[aa]);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog(out + "Enter the symbol and the\nnumber that it occurs on (spaces):");
				v = v2(input, symbols[aa]);
			}
			String[] spl = input.split(" "), temp = new String[symbols[aa].length];
			int tempStart = Integer.parseInt(spl[1]) % temp.length, symStart = Integer.parseInt(spl[0]) - 1;
			for(int bb = 0; bb < temp.length; bb++)
				temp[(bb + tempStart) % temp.length] = symbols[aa][(bb + symStart) % symbols[aa].length];
			symbols[aa] = temp;
		}
		String souv = "LEFT: " + symbols[0].length + "\nRIGHT: " + symbols[1].length;
		String input = JOptionPane.showInputDialog("Flip the switch and enter the number:");
		boolean v = isNum(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Flip the switch and enter the number:");
			v = isNum(input);
		}
		int number = Integer.parseInt(input);
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[2];
		optionPane.setLayout(new GridLayout(2, 1));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		jButton[0] = getButton(optionPane, "Changing Images");
		optionPane.add(jButton[0]);
		jButton[1] = getButton(optionPane, "Changing Number");
		optionPane.add(jButton[1]);
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the mode");
		dialog.setVisible(true);
		String mode = (String)optionPane.getValue();
		if(mode.equals("Changing Images"))
			JOptionPane.showMessageDialog(null, "Set it to these images:\n" + symbols[0][number % symbols[0].length] + "\n" + symbols[1][number % symbols[1].length]);
		else
		{
			int[] mods = new int[2];
			for(int aa = 0; aa < symbols.length; aa++)
			{
				String out = "";
				for(int bb = 0; bb < symbols[aa].length; bb++)
					out = out + "" + (bb + 1) + ": " + symbols[aa][bb] + "\n";
				input = JOptionPane.showInputDialog(out + "Enter the " + pos[aa] + " symbol:");
				v = v3(input, symbols[aa]);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog(out + "Enter the " + pos[aa] + " symbol:");
					v = v3(input, symbols[aa]);
				}
				mods[aa] = "12345".indexOf(input);
			}
			while(true)
			{
				if(number % symbols[0].length == mods[0] && number % symbols[1].length == mods[1])
					break;
				number++;
			}
			JOptionPane.showMessageDialog(null, "Set it to this number:\n" + number);
		}
		return souv;
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(",");
		return (conv.length > 1 && conv.length < 6);
	}
	private boolean v2(String i, String[] sym)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
			return (v3(conv[0], sym) && isNum(conv[1]));
		return false;
	}
	private boolean v3(String i, String[] sym)
	{
		switch(i)
		{
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
				break;
			default:
				return false;
		}
		if("012345".indexOf(i) > sym.length)
			return false;
		return true;
	}
	private boolean isNum(String i)
	{
		if(i.length() > 0 && i.length() < 9)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("0123456789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private JButton getButton(final JOptionPane optionPane, String text) {
	    final JButton button = new JButton();
	    button.setText(text);
	    button.setSize(50, 50);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
