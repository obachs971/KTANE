package modules;
import javax.swing.JOptionPane;
import start.BombEdgework;
public class ConnectionCheck 
{
	private final BombEdgework ew;
	public ConnectionCheck(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String numbers = JOptionPane.showInputDialog("Enter the 4 number\npairs in reading order:");
		boolean v = valid(numbers);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			numbers = JOptionPane.showInputDialog("Enter the 4 number\npairs in reading order:");
			v = valid(numbers);
		}
		numbers = numbers.replace(" ", "").replace(",", "").replace("/", "");
		char snchar;
		if(numbers.chars().filter(n -> n == '1').count() == 1 && numbers.chars().filter(n -> n == '2').count() == 1 && numbers.chars().filter(n -> n == '3').count() == 1 && numbers.chars().filter(n -> n == '4').count() == 1 && numbers.chars().filter(n -> n == '5').count() == 1 && numbers.chars().filter(n -> n == '6').count() == 1 && numbers.chars().filter(n -> n == '7').count() == 1)
			snchar = ew.getSNCHAR(5);
		else if(numbers.chars().filter(n -> n == '1').count() > 1)
			snchar = ew.getSNCHAR(0);
		else if(numbers.chars().filter(n -> n == '7').count() > 1)
			snchar = ew.getSNCHAR(5);
		else if(numbers.chars().filter(n -> n == '2').count() >= 3)
			snchar = ew.getSNCHAR(1);
		else if(numbers.chars().filter(n -> n == '5').count() == 0)
			snchar = ew.getSNCHAR(4);
		else if(numbers.chars().filter(n -> n == '8').count() == 2)
			snchar = ew.getSNCHAR(2);
		else if(ew.BAT() > 6 || ew.BAT() == 0)
			snchar = ew.getSNCHAR(5);
		else
			snchar = ew.getSNCHAR(ew.BAT() - 1);
		String out = "248 1367 2467 135 468 2357 2368 157";
		System.out.println(snchar);
		switch(snchar)
		{
			case 'S':case 'L':case 'I':case 'M':
				out = "236 16 146 35678 467 12345 458 47";
				break;
			case '1':case '5':case 'B':case 'R':case 'O':
				out = "27 17 48 38 67 57 1256 34";
				break;
			case '2':case '0':case 'D':case 'G':case 'T':
				out = "23 147 157 2678 367 458 2345 46";
				break;
			case '3':case '4':case 'X':case 'Y':case 'Z':
				out = "246 134 2 127 6 1578 468 67";
				break;
			case '7':case 'H':case 'P':case 'J':
				out = "23 13 12 67 67 45 45 -";
				break;
			case '6':case 'W':case 'U':case 'F':
				out = "267 1378 2568 578 3467 1357 124568 2347";
				break;
			case '8':case 'C':case 'A':case 'K':case 'E':
				out = "2368 146 14678 23567 478 1234 3458 1357";
				break;
		}
		String[] map = out.split(" ");
		out = "";
		for(int aa = 0; aa < 4; aa++)
		{
			if(map["12345678".indexOf(numbers.charAt(aa * 2))].contains(numbers.charAt((aa * 2) + 1) + ""))
				out = out + "Green ";
			else
				out = out + "Red ";
		}
		JOptionPane.showMessageDialog(null, "Set the leds to these colors:\n" + out);
	}
	private boolean valid(String i)
	{
		String temp = i.replace(" ", "").replace(",", "").replace("/", "");
		if(temp.length() == 8)
		{
			for(int aa = 0; aa < temp.length(); aa++)
			{
				if("12345678".indexOf(temp.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
