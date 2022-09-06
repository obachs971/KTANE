package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import misc.MTK;

public class LogicalButtons 
{
	private final int[][] table =
		{
				{1,2,3},
				{2,1,3},
				{3,2,1},
                {3,1,2},
				{2,3,1},
				{1,3,2}
		};
	private String gate;
	public String run()
	{
		MTK mtk = new MTK();
		String souv = "";
		for(int stage = 1; stage <= 3; stage++)
		{
			souv = souv + "STAGE #" + stage + ":\n";
			String[][] buttons = new String[3][2];
			for(int i = 0; i < 3; i++)
			{
				String input = JOptionPane.showInputDialog("Red, Blue, Green, Yellow, Purple,\nWhite, Orange, Cyan, Gray\nLogic, Color, Label, Button,\nWrong, Boom, No, Wait, Hmmm\nEnter Button #" + (i + 1) + "'s color/label:").toUpperCase();
				boolean v = valid(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Red, Blue, Green, Yellow, Purple,\nWhite, Orange, Cyan, Gray\nLogic, Color, Label, Button,\nWrong, Boom, No, Wait, Hmmm\nEnter Button #" + (i + 1) + "'s color/label:").toUpperCase();
					v = valid(input);
				}
				String[] temp = input.split(" ");
				if(isLabel(temp[0]))
				{
					buttons[i][0] = temp[1].toUpperCase();
					buttons[i][1] = temp[0].toUpperCase();
				}
				else
				{
					buttons[i][0] = temp[0].toUpperCase();
					buttons[i][1] = temp[1].toUpperCase();
				}
			}
			String[] temp = {"AND", "OR", "XOR", "NAND", "NOR", "XNOR"};
			ArrayList<String> gates = new ArrayList<String>();
			for(String str : temp)
				gates.add(str.toUpperCase());
			JDialog d = getDialog(gates);
			d.setTitle("Select the Gate:");
			d.setVisible(true);
			gates.remove(gate);
			boolean[] a = new boolean[3];
			boolean[] b = new boolean[3];
			for(int i = 0; i < 3; i++)
				a[i] = color(buttons, i);
			for(int i = 0; i < 3; i++)
				b[i] = label(buttons, i, a, stage);
			boolean[] results = new boolean[3];
			for(int i = 0; i < 3; i++)
				results[i] = mtk.solveLogicExpression(a[i], b[i], gate);
			boolean check = noPresses(results);
			while(check)
			{
				d = getDialog(gates);
				d.setTitle("Select a NEW Gate:");
				d.setVisible(true);
				gates.remove(gate);
				for(int i = 0; i < 3; i++)
					results[i] = mtk.solveLogicExpression(a[i], b[i], gate);
				check = noPresses(results);
			}
			int row = 0;
			if(gate.equals("NAND") || gate.equals("NOR") || gate.equals("XNOR"))
				row = 1;
			int[] order = table[row * 3 + (stage - 1)];
			String out = "";
			for(int num : order)
			{
				if(results[num - 1])
					out = out + "" + num + " ";
			}
			JOptionPane.showMessageDialog(null, "Press these button(s): " + out);
			for(int i = 0; i < 3; i++)
				souv = souv + "BUTTON #" + (i + 1) + ": " + buttons[i][0] + " " + buttons[i][1] + "\n";
			souv = souv + "GATE: " + gate.toUpperCase() + "\n------------------------------\n";
		}
		return souv;
	}
	private boolean color(String[][] buttons, int button)
	{
		switch(buttons[button][0])
		{
			case "RED":
				return !(buttons[mod(button + 1, 3)][0].equals("BLUE") || buttons[mod(button + 2, 3)][0].equals("BLUE"));
			case "BLUE":
				return (buttons[mod(button + 1, 3)][0].equals("BLUE") || buttons[mod(button + 2, 3)][0].equals("BLUE"));
			case "GREEN":
				return (buttons[mod(button - 1, 3)][0].equals("PURPLE") || buttons[mod(button - 1, 3)][0].equals("WHITE"));
			case "YELLOW":
				return !(buttons[button][1].equals("WRONG") || buttons[button][1].equals("LOGIC"));
			case "PURPLE":
				return !(isPrimary(buttons[mod(button + 1, 3)][0]) || isPrimary(buttons[mod(button + 2, 3)][0]));
			case "WHITE":
				return (isPrimary(buttons[mod(button + 1, 3)][0]) || isPrimary(buttons[mod(button + 2, 3)][0]));
			case "ORANGE":
				return !(buttons[0][0].equals("ORANGE"));
			case "CYAN":
				return (buttons[button][1].length() == 5);
			default:
				return (buttons[mod(button + 1, 3)][1].equals(buttons[button][1]) || buttons[mod(button + 2, 3)][1].equals(buttons[button][1]));
		}
	}
	private boolean label(String[][] buttons, int button, boolean[] conditions, int stage)
	{
		switch(buttons[button][1])
		{
			case "LOGIC":
				for(String[] b : buttons)
				{
					if(b[0].equals("GRAY") || b[0].equals("GREY"))
						return false;
				}
				return true;
			case "COLOR":
				return !(buttons[button][0].equals("GREEN") || buttons[button][0].equals("YELLOW") || buttons[button][0].equals("ORANGE"));
			case "LABEL":
				return (buttons[0][1].length() != 5);
			case "BUTTON":
				return !(buttons[mod(button - 1, 3)][1].equals("HMMM") || buttons[mod(button - 1, 3)][1].equals("NO"));
			case "WRONG":
				return (buttons[mod(button + 1, 3)][0].equals(buttons[button][0]));
			case "BOOM":
				return (buttons[mod(button + 1, 3)][0].equals(buttons[mod(button + 2, 3)][0]));
			case "NO":
				return !(conditions[button]);
			case "WAIT":
				return (stage == 3);
			default:
				return (conditions[1]);	
		}
	}
	private boolean isPrimary(String color)
	{
		switch(color)
		{
			case "RED":case "BLUE":case "YELLOW":
				return true;
			default:
				return false;
		}
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private boolean noPresses(boolean[] result)
	{
		for(boolean bool : result)
		{
			if(bool)
				return false;
		}
		return true;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			String[][] list = 
				{
					{
						"RED", "BLUE", "GREEN", "YELLOW", "PURPLE",
						"WHITE", "ORANGE", "CYAN", "GREY", "GRAY"
					},
					{
						"LOGIC", "COLOR", "LABEL", "BUTTON", "WRONG",
						"BOOM", "NO", "WAIT", "HMMM"
					}
				};
			boolean[] b = {false, false};
			for(String[] arr : list)
			{
				for(String str : arr)
				{
					if(str.equals(conv[0]))
					{
						b[0] = true;
						break;
					}
					if(str.equals(conv[1]))
					{
						b[1] = true;
						break;
					}
				}
			}
			return (b[0] && b[1]);
		}
		return false;
	}
	private boolean isLabel(String i)
	{
		switch(i)
		{
			case "LOGIC":case "COLOR":case "LABEL":case "BUTTON":case "WRONG":
			case "BOOM":case "NO":case "WAIT":case "HMMM":
				return true;
			default: return false;
		}
	}
	private JDialog getDialog(ArrayList<String> gates)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[gates.size()];
		optionPane.setLayout(new GridLayout(gates.size(), 1));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int i = 0; i < jButton.length; i++)
		{
			jButton[i] = getButton(optionPane, gates.get(i).toUpperCase());
			optionPane.add(jButton[i]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String value ) {
	    final JButton button = new JButton();
	    button.setText(value);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	    	optionPane.setValue(value.toUpperCase());
	    	gate = value.toUpperCase();
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
