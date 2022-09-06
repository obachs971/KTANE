package modules;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import misc.MTK;

public class Tangrams 
{
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the type/digits:").toUpperCase().replace(" ", "");
		String[][] circuit = createCircuit(input);
		while(circuit == null)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the type/digits:").toUpperCase().replace(" ", "");
			circuit = createCircuit(input);
		}
		String alpha = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[][] temp = new String[circuit[0][1].length()][2];
		for(int i = 0; i < temp.length; i++)
		{
			temp[i][0] = alpha.charAt(i) + "";
			temp[i][1] = "";
		}
		for(int i = 1; i < circuit.length; i++)
		{
			for(int j = 0; j < circuit[i].length; j++)
			{
				int index = alpha.indexOf(circuit[i][j].charAt(0));
				temp[index][1] = temp[index][1] + circuit[i][j].substring(1);	
			}
		}
		String goals = circuit[0][0].toUpperCase();
		circuit = new String[temp.length][];
		for(int i = 0; i < temp.length; i++)
		{
			//System.out.println(temp[i][0] + ": " + temp[i][1]);
			circuit[i] = new String[temp[i][1].length() + 1];
			circuit[i][0] = temp[i][0];
			for(int j = 0; j < temp[i][1].length(); j++)
				circuit[i][j + 1] = temp[i][1].charAt(j) + "";
		}
		ArrayList<String> solutions = new ArrayList<String>();
		MTK mtk = new MTK();
		for(int i = 0; i < goals.length(); i++)
		{
			ArrayList<String> g = new ArrayList<String>(Arrays.asList(goals.replace(goals.charAt(i) + "", "").split("")));
			g.remove("");
			//System.out.println(g.toString());
			String sol = mtk.getMazeSolution(circuit, 0, goals.charAt(i) + "", g);
			if(sol.length() > 0)
				solutions.add((alpha.indexOf(goals.charAt(i)) + 1) + " " + (alpha.indexOf(sol.charAt(sol.length() - 1)) + 1));
		}
		while(solutions.size() < 3)
			solutions.add("NULL");
		JOptionPane.showMessageDialog(null, "Press these pins:\n" + solutions.get(0) + ", " + solutions.get(1) + ", " + solutions.get(2));
	}
	private String[][] createCircuit(String input)
	{
		if(input.length() > 1)
		{
			switch(input.charAt(0))
			{
				case 'D': return TAND(input.substring(1));
				case 'S': return TANS(input.substring(1));
			}
		}
		return null;
	}
	private String[][] TANS(String digs)
	{
		String[][] map;
		switch(digs.length())
		{
			case 7:
				String[][] seven = {
						new String[] {"1234567890ABCDEF", "1234567890ABCDEFGHIJKLMNOPQR"},
						BT(digs.charAt(0), "GILQCDEF".toCharArray()),
						BT(digs.charAt(1), "HJIG1234".toCharArray()),
						MT(digs.charAt(2), "7890RN".toCharArray()),
						ST(digs.charAt(3), "KH56".toCharArray()),
						ST(digs.charAt(4), "LMPO".toCharArray()),
						SQ(digs.charAt(5), "KNMJ".toCharArray()),
						PL(digs.charAt(6), "RABQOP".toCharArray())
				};
				map = seven;
				break;
			case 8:
				String[][] eight = {
						new String[] {"1234567890ABCDEF", "1234567890ABCDEFGHIJKLMNOPQRS"},
						BT(digs.charAt(0), "GKMQCDEF".toCharArray()),
						BT(digs.charAt(1), "SNLJ5678".toCharArray()),
						ST(digs.charAt(2), "HG12".toCharArray()),
						ST(digs.charAt(3), "JI34".toCharArray()),
						ST(digs.charAt(4), "MNPO".toCharArray()),
						ST(digs.charAt(5), "RS90".toCharArray()),
						SQ(digs.charAt(6), "ILKH".toCharArray()),
						PL(digs.charAt(7), "RABQOP".toCharArray()),
				};
				map = eight;
				break;
			case 9:
				String[][] nine = {
						new String[] {"1234567890ABCDEF", "1234567890ABCDEFGHIJKLMNOPQRSTU"},
						BT(digs.charAt(0), "TQRU90AB".toCharArray()),
						MT(digs.charAt(1), "3456NI".toCharArray()),
						MT(digs.charAt(2), "CDOPQT".toCharArray()),
						ST(digs.charAt(3), "JKPO".toCharArray()),
						ST(digs.charAt(4), "GJEF".toCharArray()),
						ST(digs.charAt(5), "HG12".toCharArray()),
						ST(digs.charAt(6), "RLMS".toCharArray()),
						SQ(digs.charAt(7), "ILKH".toCharArray()),
						PL(digs.charAt(8), "N78USM".toCharArray())
				};
				map = nine;
				break;
				default:
					return null;
		}
		for(int i = 0; i < map.length; i++)
		{
			if(map[i] == null)
				return null;
		}
		return map;
	}
	private String[][] TAND(String digs)
	{
		String[][] map;
		switch(digs.length())
		{
			case 6:
				String[][] six = {
						new String[] {"12345678", "1234567890ABCDEFG"},
						MT(digs.charAt(0), "ABEG56".toCharArray()),
						ST(digs.charAt(1), "79BA".toCharArray()),
						ST(digs.charAt(2), "02DC".toCharArray()),
						ST(digs.charAt(3), "F4GE".toCharArray()),
						ST(digs.charAt(4), "3FCD".toCharArray()),
						SQ(digs.charAt(5), "1098".toCharArray())
				};
				map = six;
				break;
			case 7:
				String[][] seven = {
						new String[] {"1234567890AB", "1234567890ABCDEFGHIJKLMNOPQ"},
						BT(digs.charAt(0), "5678NOPQ".toCharArray()),
						BT(digs.charAt(1), "LHIMQPON".toCharArray()),
						BT(digs.charAt(2), "AB12FEDC".toCharArray()),
						MT(digs.charAt(3), "KGCDHL".toCharArray()),
						ST(digs.charAt(4), "JIEF".toCharArray()),
						ST(digs.charAt(5), "90GK".toCharArray()),
						SQ(digs.charAt(6), "J34M".toCharArray())
				};
				map = seven;
				break;
			case 9:
				String[][] nine = {
						new String[] {"1234567890AB", "1234567890ABCDEFGHIJKLMNOPQRST"},
						BT(digs.charAt(0), "890AEHLQ".toCharArray()),
						BT(digs.charAt(1), "2345TPKG".toCharArray()),
						MT(digs.charAt(2), "CDGKJF".toCharArray()),
						ST(digs.charAt(3), "MRQL".toCharArray()),
						ST(digs.charAt(4), "JONI".toCharArray()),
						ST(digs.charAt(5), "SOPT".toCharArray()),
						ST(digs.charAt(6), "B1DC".toCharArray()),
						SQ(digs.charAt(7), "S67R".toCharArray()),
						PL(digs.charAt(8), "FINMHE".toCharArray())
				};
				map = nine;
				break;
				default:
					return null;
		}
		for(int i = 0; i < map.length; i++)
		{
			if(map[i] == null)
				return null;
		}
		return map;
	}
	private String[] SQ(char d, char[] p)
	{
		String[] c = new String[4];
		switch(d)
		{
			case '1':
				c[0] = p[0] + "" + p[3];
				c[1] = p[1] + "" + p[0] + "" + p[3];
				c[2] = p[2] + "";
				c[3] = p[3] + "" + p[0];
				break;
			case '2':
				c[0] = p[0] + "";
				c[1] = p[1] + "" + p[0];
				c[2] = p[2] + "" + p[0] + "" + p[1];
				c[3] = p[3] + "" + p[0];
				break;
			case '3':
				c[0] = p[0] + "" + p[2];
				c[1] = p[1] + "" + p[3];
				c[2] = p[2] + "" + p[0];
				c[3] = p[3] + "" + p[1];
				break;
			default:
				return null;
		}
		return c;
	}
	private String[] PL(char d, char[] p)
	{
		String[] c = new String[6];
		switch(d)
		{
			case '1':
				c[0] = p[0] + "" + p[3] + "" + p[5];
				c[1] = p[1] + "" + p[2] + "" + p[4];
				c[2] = p[2] + "" + p[1] + "" + p[4];
				c[3] = p[3] + "";
				c[4] = p[4] + "";
				c[5] = p[5] + "" + p[0] + "" + p[3];
				break;
			case '2':
				c[0] = p[0] + "" + p[1];
				c[1] = p[1] + "" + p[0];
				c[2] = p[2] + "";
				c[3] = p[3] + "" + p[0] + "" + p[1];
				c[4] = p[4] + "" + p[2] + "" + p[5];
				c[5] = p[5] + "";
				break;
			case '3':
				c[0] = p[0] + "" + p[3];
				c[1] = p[1] + "" + p[4];
				c[2] = p[2] + "" + p[5];
				c[3] = p[3] + "" + p[0];
				c[4] = p[4] + "" + p[1];
				c[5] = p[5] + "" + p[2];
				break;
			case '4':
				c[0] = p[0] + "" + p[1] + "" + p[2] + "" + p[3] + "" + p[4];
				c[1] = p[1] + "";
				c[2] = p[2] + "";
				c[3] = p[3] + "" + p[2] + "" + p[4];
				c[4] = p[4] + "";
				c[5] = p[5] + "" + p[0] + "" + p[1] + "" + p[2] + "" + p[3] + "" + p[4];
				break;
			default:
				return null;
		}
		return c;
	}
	private String[] ST(char d, char[] p)
	{
		String[] c = new String[4];
		switch(d)
		{
			case '1':
				c[0] = p[0] + "" + p[2];
				c[1] = p[1] + "" + p[3];
				c[2] = p[2] + "" + p[0];
				c[3] = p[3] + "" + p[1];
				break;
			case '2':
				c[0] = p[0] + "" + p[1];
				c[1] = p[1] + "" + p[0];
				c[2] = p[2] + "" + p[3];
				c[3] = p[3] + "" + p[2];
				break;
			case '3':
				c[0] = p[0] + "" + p[2] + "" + p[3];
				c[1] = p[1] + "" + p[2] + "" + p[3];
				c[2] = p[2] + "" + p[3];
				c[3] = p[3] + "" + p[2];
				break;
			case '4':
				c[0] = p[0] + "" + p[3];
				c[1] = p[1] + "" + p[2];
				c[2] = p[2] + "" + p[1];
				c[3] = p[3] + "" + p[0];
				break;
			case '5':
				c[0] = p[0] + "";
				c[1] = p[1] + "" + p[3];
				c[2] = p[2] + "";
				c[3] = p[3] + "" + p[1];
				break;
			case '6':
				c[0] = p[0] + "" + p[1] + "" + p[2] + "" + p[3];
				c[1] = p[1] + "" + p[2];
				c[2] = p[2] + "" + p[1];
				c[3] = p[3] + "" + p[0] + "" + p[1] + "" + p[2];
				break;
			case '7':
				c[0] = p[0] + "";
				c[1] = p[1] + "" + p[2] + "" + p[3];
				c[2] = p[2] + "";
				c[3] = p[3] + "" + p[1] + "" + p[2];
				break;
			case '8':
				c[0] = p[0] + "";
				c[1] = p[1] + "" + p[2];
				c[2] = p[2] + "" + p[1];
				c[3] = p[3] + "" + p[1] + "" + p[2];
				break;
			default:
				return null;
		}
		return c;
	}
	private String[] MT(char d, char[] p)
	{
		String[] c = new String[6];
		switch(d)
		{
			case '1':
				c[0] = p[0] + "" + p[1] + "" + p[3] + "" + p[4];
				c[1] = p[1] + "";
				c[2] = p[2] + "" + p[5];
				c[3] = p[3] + "" + p[0] + "" + p[1] + "" + p[4];
				c[4] = p[4] + "" + p[0] + "" + p[1] + "" + p[3];
				c[5] = p[5] + "" + p[2];
				break;
			case '2':
				c[0] = p[0] + "";
				c[1] = p[1] + "" + p[3];
				c[2] = p[2] + "";
				c[3] = p[3] + "";
				c[4] = p[4] + "" + p[0] + "" + p[2];
				c[5] = p[5] + "" + p[0] + "" + p[2] + "" + p[4];
				break;
			case '3':
				c[0] = p[0] + "" + p[5];
				c[1] = p[1] + "" + p[2];
				c[2] = p[2] + "" + p[1];
				c[3] = p[3] + "" + p[1] + "" + p[2] + "" + p[4];
				c[4] = p[4] + "" + p[1] + "" + p[2] + "" + p[3];
				c[5] = p[5] + "" + p[0];
				break;
			case '4':
				c[0] = p[0] + "" + p[1];
				c[1] = p[1] + "" + p[0];
				c[2] = p[2] + "" + p[0] + "" + p[1];
				c[3] = p[3] + "";
				c[4] = p[4] + "" + p[0] + "" + p[1] + "" + p[2];
				c[5] = p[5] + "" + p[0] + "" + p[1] + "" + p[2];
				break;
			default:
				return null;
		}
		return c;
	}
	private String[] BT(char d, char[] p)
	{
		String[] c = new String[8];
		switch(d)
		{
			case '1':
				c[0] = p[0] + "" + p[1] + "" + p[5];
				c[1] = p[1] + "";
				c[2] = p[2] + "" + p[6];
				c[3] = p[3] + "" + p[4];
				c[4] = p[4] + "" + p[3];
				c[5] = p[5] + "" + p[0] + "" + p[1];
				c[6] = p[6] + "" + p[2];
				c[7] = p[7] + "" + p[2] + "" + p[6];
				break;
			case '2':
				c[0] = p[0] + "" + p[6];
				c[1] = p[1] + "" + p[2] + "" + p[3] + "" + p[7];
				c[2] = p[2] + "";
				c[3] = p[3] + "" + p[2] + "" + p[7];
				c[4] = p[4] + "";
				c[5] = p[5] + "" + p[0] + "" + p[6];
				c[6] = p[6] + "" + p[0];
				c[7] = p[7] + "" + p[2] + "" + p[3];
				break;
			case '3':
				c[0] = p[0] + "" + p[2] + "" + p[7];
				c[1] = p[1] + "";
				c[2] = p[2] + "";
				c[3] = p[3] + "" + p[2] + "" + p[4];
				c[4] = p[4] + "" + p[2] + "" + p[3];
				c[5] = p[5] + "";
				c[6] = p[6] + "" + p[0] + "" + p[2] + "" + p[7];
				c[7] = p[7] + "" + p[0] + "" + p[2];
				break;
			case '4':
				c[0] = p[0] + "" + p[3];
				c[1] = p[1] + "";
				c[2] = p[2] + "";
				c[3] = p[3] + "";
				c[4] = p[4] + "" + p[2];
				c[5] = p[5] + "";
				c[6] = p[6] + "";
				c[7] = p[7] + "" + p[2];
				break;
			default:
				return null;
		}
		return c;
	}
}
