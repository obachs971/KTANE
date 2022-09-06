package modules;

import javax.swing.JOptionPane;

public class Mashematics 
{
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the equation:").toUpperCase().replace(" ", "");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the equation:").toUpperCase().replace(" ", "");
			v = valid(input);
		}
		String[] nums = input.replace("*", " ").replace("+", " ").replace("-", " ").split(" ");
		String oper = input.replace("0", "").replace("1", "").replace("2", "").replace("3", "").replace("4", "").replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("9", "");
		int result;
		if(oper.charAt(1) == '*')
			result = getResult(Integer.parseInt(nums[0]), getResult(Integer.parseInt(nums[1]), Integer.parseInt(nums[2]), oper.charAt(1)), oper.charAt(0));
		else
			result = getResult( getResult(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), oper.charAt(0)), Integer.parseInt(nums[2]), oper.charAt(1));
		while(result > 99)
			result -= 50;
		while(result < 0)
			result += 50;
		JOptionPane.showMessageDialog(null, "Enter this number: " + result);
		return ("EQUATION: " + input + "\nRESULT: " + result);
	}
	private int getResult(int a, int b, char o)
	{
		switch(o)
		{
			case '+': return (a + b);
			case '-': return (a - b);
			default: return (a * b);
		}
		
	}
	private boolean valid(String i)
	{
		String temp = i.replace("*", " ").replace("+", " ").replace("-", " ");
		String[] conv = temp.split(" ");
		if(conv.length == 3)
		{
			for(int aa = 0; aa < 3; aa++)
			{
				if(!(isNum(conv[aa])))
					return false;
			}
			temp = i.replace("0", "").replace("1", "").replace("2", "").replace("3", "").replace("4", "").replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("9", "");
			if(temp.length() == 2)
				return (isOper(temp.charAt(0)) && isOper(temp.charAt(1)));
		}
		return false;
	}
	private boolean isNum(String i)
	{
		if(i.length() == 0)
			return false;
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("0123456789".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
	private boolean isOper(char c)
	{
		switch(c)
		{
			case '+': case '-': case '*': 
				return true;
			default:
				return false;
		}
	}
}
