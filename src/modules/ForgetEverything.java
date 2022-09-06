package modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;

public class ForgetEverything 
{
	public void run()
	{
		String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nStage 1: 10 Digit Dial Value\nStage 2+: Color, Present? TP/FA, Digit at (Stage % 10)\n:Enter the stages (spaces):").toUpperCase();
		while(input.contains("  "))
			input.replace("  ", " ");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nStage 1: 10 Digit Dial Value\nStage 2+: Color, Present? TP/FA, Digit at (Stage % 10)\n:Enter the stages (spaces):").toUpperCase();
			while(input.contains("  "))
				input.replace("  ", " ");
			v = valid(input);
		}
		String[] spl = input.split(" ");
		int[] digits = new int[10];
		ArrayList<Boolean> bin = new ArrayList<Boolean>();
		bin.add(true);
		if(spl[0].length() == 10)//Inputting in Stage Order
		{
			for(int i = 0; i < 10; i++)
				digits[i] = "0123456789".indexOf(spl[0].charAt(i));
			for(int z = 0; z < 10; z++)
				System.out.print(digits[z]);
			System.out.println();
			for(int i = 1; i < spl.length; i++)
			{
				digits[i % 10] = getStageResult(digits[i % 10], bin, spl[i]);
				for(int z = 0; z < 10; z++)
					System.out.print(digits[z]);
				System.out.println();
			}
		}
		else//Inputting in Reading Order
		{
			for(int i = 0; i < spl.length; i+=2)
			{
				if(spl[i].length() < 2)
					spl[i] = "0" + spl[i];
			}
			ArrayList<String> temp = new ArrayList<String>(Arrays.asList(spl));
			int index = temp.indexOf("01") + 1;
			for(int i = 0; i < 10; i++)
				digits[i] = "0123456789".indexOf(temp.get(index).charAt(i));
			for(int z = 0; z < 10; z++)
				System.out.print(digits[z]);
			System.out.println();
			for(int i = 2; i <= (temp.size() / 2); i++)
			{
				if(i < 10) index = temp.indexOf("0" + i) + 1;
				else index = temp.indexOf("" + i) + 1;
				digits[(i - 1) % 10] = getStageResult(digits[(i - 1) % 10], bin, temp.get(index));
				for(int z = 0; z < 10; z++)
					System.out.print(digits[z]);
				System.out.println();
			}//End of For Loop
		}
		String out = digits[0] + "";
		for(int i = 1; i < digits.length; i++)
			out = out + "" + digits[i];
		JOptionPane.showMessageDialog(null, "Submit this number:\n" + out);
	}
	//Returns the Result of the Stage
	private int getStageResult(int d, ArrayList<Boolean> bin, String info)
	{
		String colors = "";
		char present = 'F';
		int digit = 0;
		for(int j = 0; j < info.length(); j++)
		{
			switch(info.charAt(j))
			{
				case 'R':case 'B':case 'Y':case 'G':
					colors = colors + "" + info.charAt(j);
					break;
				case 'T':case 'P':case 'F':case 'A':
					present = info.charAt(j);
					break;
				default:
					digit = "0123456789".indexOf(info.charAt(j));
			}
		}
		//Check if the Stage is Valid
		if(bin.size() >= 2)
		{
			if(bin.get(bin.size() - 2) == bin.get(bin.size() - 1))
				bin.add(!(bin.get(bin.size() - 2)));
			else if("TP".indexOf(present) >= 0)
				bin.add(true);
			else
				bin.add(false);
		}
		else if("TP".indexOf(present) >= 0)
			bin.add(true);
		else
			bin.add(false);
		System.out.print(colors + "" + present + "" + digit + " " + (bin.get(bin.size() - 1) ? "V" : "-") + " ");
		//If the Stage is Valid, Perform the Operation
		if(bin.get(bin.size() - 1))
		{
			if(colors.length() == 3)//If there is 3 colors, figure out which color to use
			{
				if(colors.indexOf(colors.charAt(2)) < 2)
					colors = "" + colors.charAt(2);
				else if(colors.indexOf(colors.charAt(1)) < 1)
					colors = "" + colors.charAt(1);
				else
				{
					String colorTemp = "RBYG";
					for(char c : colors.toCharArray())
						colorTemp = colorTemp.replace(c + "", "");
					colors = colorTemp;
				}
			}
			//Perform Operation
			switch(colors)
			{
				case "R":
					return mod(d + digit, 10);
				case "Y":
					return mod(d - digit, 10);
				case "G":
					return mod(d + digit + 5, 10);
				default:
					return mod(digit - d, 10);
			}
		}
		return d;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length > 0 && isNum(conv[0]))
		{
			if(conv[0].length() == 10)
			{
				for(int aa = 1; aa < conv.length; aa++)
				{
					if(!(validStageInput(conv[aa])))
						return false;
				}
				return true;
			}
			else if(conv[0].length() < 3 && conv.length % 2 == 0)
			{
				ArrayList<Integer> nums = new ArrayList<Integer>();
				for(int aa = 0; aa < conv.length; aa+=2)
				{
					if(!(conv[aa].length() < 3 && isNum(conv[aa])))
						return false;
					if(conv[aa].equals("1") || conv[aa].equals("01"))
					{
						if(!(conv[aa + 1].length() == 10 && isNum(conv[aa + 1])))
							return false;
					}
					else if (!(validStageInput(conv[aa + 1])))
						return false;
					nums.add(Integer.parseInt(conv[aa]));
				}
				Collections.sort(nums);
				if(nums.get(0) == 1 && nums.get(nums.size() - 1) == (conv.length / 2))
				{
					for(int aa = 1; aa < nums.size(); aa++)
					{
						if((nums.get(aa - 1) + 1) != nums.get(aa))
							return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	private boolean validStageInput(String i)
	{
		String colors = "";
		String digit = "";
		String present = "";
		for(int aa = 0; aa < i.length(); aa++)
		{
			switch(i.charAt(aa))
			{
				case 'R':case 'B':case 'Y':case 'G':
					colors = colors + "" + i.charAt(aa);
					break;
				case 'T':case 'P':case 'F':case 'A':
					present = present + "" + i.charAt(aa);
					break;
				case '0':case '1':case '2':case '3':case '4':
				case '5':case '6':case '7':case '8':case '9':
					digit = digit + "" + i.charAt(aa);
					break;
				default:
					return false;
			}
		}
		return ((colors.length() == 3 || colors.length() == 1) && present.length() == 1 && digit.length() == 1);
	}
	private boolean isNum(String i)
	{
		if(i.length() > 0)
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
	private int mod(int n, int m)
	{
		while(n < 0)
			n += 10;
		return (n % 10);
	}
}
