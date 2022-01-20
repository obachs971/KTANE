package modules;

import javax.swing.JOptionPane;

public class EmojiMath 
{
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the displayed text:");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the displayed text:");
			v = valid(input);
		}
		String op;
		String[] emojis = new String[2];
		int[] numbers = new int[2];
		if(input.contains("+"))
			op = "+";
		else
			op = "-";
		emojis[0] = input.substring(0, input.indexOf(op));
		emojis[1] = input.substring(input.indexOf(op) + 1);
		for(int aa = 0; aa < 2; aa++)
		{
			String tempNum = "";
			for(int bb = 0; bb < emojis[aa].length(); bb+=2)
				tempNum = tempNum + "" + emojiToNumber(emojis[aa].charAt(bb) + "" + emojis[aa].charAt(bb + 1));
			numbers[aa] = Integer.parseInt(tempNum);
		}
		if(op.equals("+"))
			JOptionPane.showMessageDialog(null, "Input this number: " + (numbers[0] + numbers[1]));
		else
			JOptionPane.showMessageDialog(null, "Input this number: " + (numbers[0] - numbers[1]));
	}
	private int emojiToNumber(String e)
	{
		switch(e)
		{
			case ":)":
				return 0;
			case "=(":
				return 1;
			case "(:":
				return 2;
			case ")=":
				return 3;
			case ":(":
				return 4;
			case "):":
				return 5;
			case "=)":
				return 6;
			case "(=":
				return 7;
			case ":|":
				return 8;
			case "|:":
				return 9;
		}
		return -1;
	}
	private boolean valid(String i)
	{
		String op = "";
		for(int aa = 0; aa < i.length(); aa++)
		{
			if(i.charAt(aa) == '+' || i.charAt(aa) == '-')
				op = op + "" + i.charAt(aa);
		}
		if(op.length() == 1)
		{
			String[] temp = new String[2];
			temp[0] = i.substring(0, i.indexOf(op));
			temp[1] = i.substring(i.indexOf(op) + 1);
			if(temp.length == 2 && temp[0].length() % 2 == 0 && temp[1].length() % 2 == 0)
			{
				for(int aa = 0; aa < 2; aa++)
				{
					for(int bb = 0; bb < temp[aa].length(); bb+=2)
					{
						if(emojiToNumber(temp[aa].charAt(bb) + "" + temp[aa].charAt(bb + 1)) < 0)
							return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}
