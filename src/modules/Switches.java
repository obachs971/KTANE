package modules;
import javax.swing.JOptionPane;
import misc.MTK;

public class Switches 
{
	private final String[][] switchMaze =
		{
				{"DDDDD", "1UDDDD", "2DUDDD", "4DDDUD", "5DDDDU"},  
				{"DDDDU", "1UDDDU", "2DUDDU", "3DDUDU", "4DDDUU", "5DDDDD"},  
				{"DDDUD", "2DUDUD", "3DDUUD", "4DDDDD", "5DDDUU"}, 
				{"DDDUU", "3DDUUU", "4DDDDU", "5DDDUD"},  
				{"DDUDU", "1UDUDU", "2DUUDU", "3DDDDU", "4DDUUU"},  
				{"DDUUD", "1UDUUD", "2DUUUD", "3DDDUD", "5DDUUU"},  
				{"DDUUU", "3DDDUU", "4DDUDU", "5DDUUD"},  
				{"DUDDD", "2DDDDD", "3DUUDD", "4DUDUD", "5DUDDU"},  
				{"DUDDU", "1UUDDU", "2DDDDU", "3DUUDU", "5DUDDD"},  
				{"DUDUD", "2DDDUD", "3DUUUD", "4DUDDD"},  
				{"DUUDD", "3DUDDD", "4DUUUD", "5DUUDU"},  
				{"DUUDU", "1UUUDU", "2DDUDU", "3DUDDU", "5DUUDD"},  
				{"DUUUD", "2DDUUD", "3DUDUD", "4DUUDD"},  
				{"UDDDD", "1DDDDD", "3UDUDD", "5UDDDU"},  
				{"UDDDU", "1DDDDU", "2UUDDU", "3UDUDU", "5UDDDD"}, 
				{"UDUDD", "3UDDDD", "4UDUUD", "5UDUDU"},  
				{"UDUDU", "1DDUDU", "2UUUDU", "3UDDDU", "5UDUDD"}, 
				{"UDUUD", "1DDUUD", "4UDUDD"}, 
				{"UUDDU", "1DUDDU", "2UDDDU", "3UUUDU", "4UUDUU"}, 
				{"UUDUU", "3UUUUU", "4UUDDU"},
				{"UUUDU", "1DUUDU", "2UDUDU", "3UUDDU", "4UUUUU"}, 
				{"UUUUU", "3UUDUU", "4UUUDU"}
		};
	public String run()
	{
		String current = JOptionPane.showInputDialog("U - Up\nD - Down\n12345 - Up positions\nEnter switches' positions:").toUpperCase();
		boolean v = valid(current);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			current = JOptionPane.showInputDialog("U - Up\nD - Down\nEnter switches' positions:").toUpperCase();
			v = valid(current);
		}
		if(!(current.length() == 5 && "UD".indexOf(current.charAt(0)) >= 0))
			current = convert(current);
		String souv = "INITIAL POSITION: " + current.toUpperCase();
		String goal = JOptionPane.showInputDialog("U - Up\nD - Down\nEnter lights' positions:").toUpperCase();
		v = valid(goal);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			goal = JOptionPane.showInputDialog("U - Up\nD - Down\nEnter lights' positions:").toUpperCase();
			v = valid(goal);
		}
		if(!(goal.length() == 5 && "UD".indexOf(goal.charAt(0)) >= 0))
			goal = convert(goal);
		MTK mtk = new MTK();
		String solution = mtk.getMazeSolution(switchMaze, 1, current, goal);
		String out = solution.charAt(0) + "";
		for(int aa = 1; aa < solution.length(); aa++)
			out = out + " " + solution.charAt(aa);
		JOptionPane.showMessageDialog(null, "Toggle these switches:\n" + out);
		return souv;
	}
	private String convert(String i)
	{
		String temp = "DDDDD";
		for(int aa = 0; aa < i.length(); aa++)
		{
			int num = "12345".indexOf(i.charAt(aa));
			temp = temp.substring(0, num) + "U" + temp.substring(num + 1);
		}
		return temp;
	}
	private boolean valid(String i)
	{
		if(i.length() == 5 && "UD".indexOf(i.charAt(0)) >= 0)
		{
			for(int aa = 0; aa < 5; aa++)
			{
				if("UD".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			for(int aa = 0; aa < switchMaze.length; aa++)
			{
				if(switchMaze[aa][0].equals(i))
					return true;
			}
		}
		else
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("12345".indexOf(i.charAt(aa)) < 0)
					return false;
				for(int bb = aa + 1; bb < i.length(); bb++)
				{
					if(i.charAt(aa) == i.charAt(bb))
						return false;
				}
			}
			String temp = convert(i);
			for(int aa = 0; aa < switchMaze.length; aa++)
			{
				if(switchMaze[aa][0].equals(temp))
					return true;
			}
		}
		return false;
	}
}
