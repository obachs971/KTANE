package modules;

import javax.swing.JOptionPane;

import misc.MTK;

public class ColorMorse 
{
	public String run()
	{
		MTK mtk = new MTK();
		String alpha = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nW - White\nEnter the expression:").toUpperCase().replace(" ", "");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nW - White\nEnter the expression:").toUpperCase().replace(" ", "");
			v = v1(input);
		}
		boolean first = false;
		if(input.charAt(0) == '(')
			first = true;
		input = input.replace("(", "").replace(")", "");
		String colors = input.charAt(0) + "" + input.charAt(2) + "" + input.charAt(4), oper = input.charAt(1) + "" + input.charAt(3);
		String[] names = {"RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE", "WHITE"};
		String souv = "COLORS: " + names["ROYGBPW".indexOf(colors.charAt(0))];
		for(int aa = 1; aa < 3; aa++)
			souv = souv + ", " + names["ROYGBPW".indexOf(colors.charAt(aa))];
		input = JOptionPane.showInputDialog("Enter the morse code (spaces)").toUpperCase();
		v = v2(input, mtk);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the morse code (spaces)").toUpperCase();
			v = v2(input, mtk);
		}
		String charas = mtk.morseToMessage(input.split(" "));
		souv = souv + "\nCHARACTERS: " + charas.charAt(0) + ", " + charas.charAt(1) + ", " + charas.charAt(2);
		int[] values = new int[3];
		//Applying the color's operation
		for(int aa = 0; aa < 3; aa++)
		{
			values[aa] = alpha.indexOf(charas.charAt(aa));
			switch(colors.charAt(aa))
			{
				case 'R':
					if(values[aa] % 2 == 1)
						values[aa] *= 2;
					else
						values[aa] /= 2;
					break;
				case 'O':
					if(values[aa] % 3 == 0)
						values[aa] /= 3;
					else
					{
						for(int bb = 0; bb < 3; bb++)
						{
							if("RBY".indexOf(colors.charAt(bb)) >= 0)
								values[aa]++;
						}
					}
					break;
				case 'Y':
					values[aa] *= values[aa];
					break;
				case 'G':
					first = !(first);
					break;
				case 'B':
					values[aa] *= 3;
					while(values[aa] >= 10)
					{
						String temp = values[aa] + "";
						values[aa] = 0;
						for(int bb = 0; bb < temp.length(); bb++)
							values[aa] += "0123456789".indexOf(temp.charAt(bb));
					}
					break;
				case 'P':
					values[aa] = 10 - values[aa];
					break;
			}
		}
		int result;
		//Solve the expression
		switch(oper)
		{
			case "//":
				if(first)
					result = values[0] / (values[2] * values[1]);
				else
					result = (values[0] * values[2]) / values[1];
				break;
			case "/*":
				if(first)
					result = (values[0] * values[2]) / values[1];
				else
					result = values[0] / (values[1] * values[2]);
				break;
			case "*/":
				result = (values[0] * values[1]) / values[2];
				break;
			default:
				if(first)
					result = getResult(getResult(values[0], values[1], oper.charAt(0)), values[2], oper.charAt(1));
				else
					result = getResult(values[0], getResult(values[1], values[2], oper.charAt(1)), oper.charAt(0));
				break;
		}
		result = result % 1000;
		//System.out.println(values[0]);
		//System.out.println(values[1]);
		//System.out.println(values[2]);
		//System.out.println(result);
		String[] morse = mtk.messageToMorse(result + "");
		String out = morse[0];
		for(int aa = 1; aa < morse.length; aa++)
			out = out + "\n" + morse[aa];
		JOptionPane.showMessageDialog(null, "Transmit this code:\n" + out);
		return souv;
	}
	private int getResult(int a, int b, char o)
	{
		switch(o)
		{
			case '+':
				return (a + b);
			case '-':
				return (a - b);
			case '*':
				return (a * b);
			default:
				return (a / b);	
		}
	}
	private boolean v1(String i)
	{
		if(i.length() == 7)
		{
			if((i.charAt(0) == '(' && i.charAt(4) == ')') || (i.charAt(2) == '(' && i.charAt(6) == ')'))
			{
				String conv = i.replace("(", "").replace(")", "");
				for(int aa = 0; aa < 3; aa++)
				{
					if("ROYGBPW".indexOf(conv.charAt(aa * 2)) < 0)
						return false;
				}
				for(int aa = 0; aa < 2; aa++)
				{
					switch(conv.charAt((aa * 2) + 1))
					{
						case '+':
						case '-':
						case '/':
						case '*':
							break;
						default:
							return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	private boolean v2(String i, MTK mtk)
	{
		String[] conv = i.split(" ");
		if(conv.length == 3)
		{
			String temp = mtk.morseToMessage(conv);
			if(temp.length() == 3)
			{
				String alpha = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				for(int aa = 0; aa < 3; aa++)
				{
					if(alpha.indexOf(temp.charAt(aa)) < 0)
						return false;
				}
				return true;
			}
		}
		return false;
	}
}
