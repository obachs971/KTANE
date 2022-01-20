package start;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BombEdgework 
{
	private final int bat;
	private final int bh;
	private final int ba;
	private final int bd;
	private final ArrayList<String> lit;
	private final ArrayList<String> unlit;
	private ArrayList<ArrayList<String>>ports;
	private final String sn;
	private String snlets;
	private String sndigs;
	private final int tf;
	private final int dom;
	public BombEdgework(double r)
	{
		String input = JOptionPane.showInputDialog("Enter B/BH");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter B/BH");
			v = v1(input);
		}
		String[] temp = input.split("/");
		bat = Integer.parseInt(temp[0]);
		bh = Integer.parseInt(temp[1]);
		ba = (bat - bh) * 2;
		bd = bat - ba;
		
		lit = new ArrayList<String>();
		unlit = new ArrayList<String>();
		input = JOptionPane.showInputDialog("* - Lit/On\nEnter Indicators:");
		v = v2(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("* - Lit/On\nEnter Indicators:");
			v = v2(input);
		}
		if(!(input.equals("")))
		{
			temp = input.split(" ");
			for(String i : temp)
			{
				if(i.length() == 4)
					lit.add(i.toUpperCase().replace("*", ""));
				else
					unlit.add(i.toUpperCase());
			}
		}
		input = JOptionPane.showInputDialog("Enter the number of port plates:");
		v = isNum(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the number of port plates:");
			v = isNum(input);
		}
		ports = new ArrayList<ArrayList<String>>();
		int numPlates = Integer.parseInt(input);
		if(numPlates > 0)
		{
			JFrame frame = new JFrame();
			ImageIcon i = new ImageIcon("img/PortList.png");
			Image image = i.getImage();
			image = image.getScaledInstance((int)(i.getIconWidth() / r), (int)(i.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			i = new ImageIcon(image);
			JLabel l = new JLabel();
			l.setIcon(i);
			frame.setLayout(new BorderLayout());
			frame.add(l, BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
			for(int aa = 0; aa < numPlates; aa++)
			{
				input = JOptionPane.showInputDialog("Enter the ports on \nplate #" + (aa + 1) + " (spaces):").toUpperCase();
				v = v3(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter the ports on \nplate #" + (aa + 1) + " (spaces):").toUpperCase();
					v = v3(input);
				}
				if(input.equals("") || input.equals("EMPTY"))
					ports.add(new ArrayList<String>());
				else
					ports.add(getArrayListPort(input.split(" ")));
			}
			frame.setVisible(false);
		}
		input = JOptionPane.showInputDialog("Enter the serial number:").toUpperCase();
		v = v4(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the serial number:").toUpperCase();
			v = v4(input);
		}
		sn = input.toUpperCase();
		sndigs = "";
		snlets = "";
		for(int aa = 0; aa < 6; aa++)
		{
			if("0123456789".indexOf(sn.charAt(aa)) >= 0)
				sndigs = sndigs + "" + sn.charAt(aa);
			else
				snlets = snlets + "" + sn.charAt(aa);
		}
		input = JOptionPane.showInputDialog("Enter the number of\ntwo-factors:");
		v = isNum(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the number of\ntwo-factors:");
			v = isNum(input);
		}
		tf = Integer.parseInt(input);
		input = JOptionPane.showInputDialog("Enter the number of\ndate of manufactures:");
		v = isNum(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the number of\ndate of manufactures:");
			v = isNum(input);
		}
		dom = Integer.parseInt(input);
	}
	public int BA(){return ba;}
	public int BD(){return bd;}
	public int BAT(){return bat;}
	public int BH(){return bh;}
	
	public boolean findLit(String ind){return lit.contains(ind.toUpperCase());}
	public boolean findUnlit(String ind){return unlit.contains(ind.toUpperCase());}
	public boolean findInd(String ind){return (findLit(ind) || findUnlit(ind));}
	public int numLit() {return lit.size();}
	public int numUnlit() {return unlit.size();}
	public int numInd() {return (lit.size() + unlit.size());}
	public int numLitWithChars(String l)
	{
		l = l.toUpperCase();
		int sum = 0;
		for(int aa = 0; aa < lit.size(); aa++)
		{
			for(int bb = 0; bb < l.length(); bb++)
			{
				if(lit.get(aa).contains(l.charAt(bb) + ""))
				{
					sum++;
					break;
				}
			}
		}
		return sum;
	}
	public int numUnlitWithChars(String l)
	{
		l = l.toUpperCase();
		int sum = 0;
		for(int aa = 0; aa < unlit.size(); aa++)
		{
			for(int bb = 0; bb < l.length(); bb++)
			{
				if(unlit.get(aa).contains(l.charAt(bb) + ""))
				{
					sum++;
					break;
				}
			}
		}
		return sum;
	}
	public int numIndWithChars(String l){return (numLitWithChars(l) + numUnlitWithChars(l));}
	
	public int numCharsInLit(String l)
	{
		l = l.toUpperCase();
		int sum = 0;
		for(int aa = 0; aa < lit.size(); aa++)
		{
			for(int bb = 0; bb < 3; bb++)
			{
				if(l.contains(lit.get(aa).charAt(bb) + ""))
					sum++;
			}
		}
		return sum;
	}
	public int numCharsInUnlit(String l)
	{
		l = l.toUpperCase();
		int sum = 0;
		for(int aa = 0; aa < unlit.size(); aa++)
		{
			for(int bb = 0; bb < 3; bb++)
			{
				if(l.contains(unlit.get(aa).charAt(bb) + ""))
					sum++;
			}
		}
		return sum;
	}
	public int numCharsInInd(String l){return (numCharsInLit(l) + numCharsInUnlit(l));}
	public String getLitChars()
	{
		String temp = "";
		for(int aa = 0; aa < lit.size(); aa++)
			temp = temp + "" + lit.get(aa);
		return temp;
	}
	public String getUnlitChars()
	{
		String temp = "";
		for(int aa = 0; aa < unlit.size(); aa++)
			temp = temp + "" + unlit.get(aa);
		return temp;
	}
	public String getIndChars(){return (getLitChars() + "" + getUnlitChars());}
	public int numPlates(){return ports.size();}
	public int numPorts()
	{
		int sum = 0;
		for(int aa = 0; aa < ports.size(); aa++)
			sum += ports.get(aa).size();
		return sum;
	}
	public int numEmpty()
	{
		int sum = 0;
		for(int aa = 0; aa < ports.size(); aa++)
		{
			if(ports.get(aa).size() == 0)
				sum++;
		}
		return sum;
	}
	/**
	 * Counts the number of ports on the bomb.
	 * List of Port Types: Parallel, Serial, DVI-D, RJ-45, PS/2, RCA, AC, Component, HDMI, Composite, VGA, USB, PCMCIA
	 * @param p the port type you are counting.
	 * @return the number of ports with that port type.
	 */
	public int findPort(String p)
	{
		p = p.toUpperCase();
		int sum = 0;
		for(int aa = 0; aa < ports.size(); aa++)
		{
			if(ports.get(aa).contains(p))
				sum++;
		}
		return sum;
	}
	public int findPorts(String[] p)
	{
		for(int aa = 0; aa < p.length; aa++)
			p[aa] = p[aa].toUpperCase();
		int sum = 0;
		for(int aa = 0; aa < ports.size(); aa++)
		{
			boolean flag = true;
			for(int bb = 0; bb < p.length; bb++)
			{
				if(!(ports.get(aa).contains(p[bb])))
				{
					flag = false;
					break;
				}
			}
			if(flag)
				sum++;
		}
		return sum;
	}
	public int numUniPorts()
	{
		ArrayList<String> unique = new ArrayList<String>();
		ArrayList<String> dup = new ArrayList<String>();
		for(int aa = 0; aa < ports.size(); aa++)
		{
			for(int bb = 0; bb < ports.get(aa).size(); bb++)
			{
				if(unique.contains(ports.get(aa).get(bb)))
				{
					dup.add(ports.get(aa).get(bb).toUpperCase());
					unique.remove(ports.get(aa).get(bb).toUpperCase());
				}
				else if(!(dup.contains(ports.get(aa).get(bb))))
				{
					unique.add(ports.get(aa).get(bb).toUpperCase());
				}
			}
		}
		return unique.size();
	}
	public int numDupPorts()
	{
		ArrayList<String> unique = new ArrayList<String>();
		ArrayList<String> dup = new ArrayList<String>();
		for(int aa = 0; aa < ports.size(); aa++)
		{
			for(int bb = 0; bb < ports.get(aa).size(); bb++)
			{
				if(unique.contains(ports.get(aa).get(bb)))
				{
					dup.add(ports.get(aa).get(bb).toUpperCase());
					unique.remove(ports.get(aa).get(bb).toUpperCase());
				}
				else if(!(dup.contains(ports.get(aa).get(bb))))
				{
					unique.add(ports.get(aa).get(bb).toUpperCase());
				}
			}
		}
		return dup.size();
	}
	public int numPortTypes()
	{
		ArrayList<String> types = new ArrayList<String>();
		for(int aa = 0; aa < ports.size(); aa++)
		{
			for(int bb = 0; bb < ports.get(aa).size(); bb++)
			{
				if(!(types.contains(ports.get(aa).get(bb))))
					types.add(ports.get(aa).get(bb));
			}
		}
		return types.size();
	}
	public ArrayList<ArrayList<String>> getPorts() {return ports;}
	public int numSNDIGS(){return sndigs.length();}
	public int numSNLETS(){return snlets.length();}
	public char getSNLET(int i) 
	{
		if(i < 0)
			i = 0;
		if(i >= snlets.length())
			i = snlets.length() - 1;
		return snlets.charAt(i);
	}
	public int getSNDIG(int i) 
	{
		if(i < 0)
			i = 0;
		if(i >= sndigs.length())
			i = sndigs.length() - 1;
		return Character.getNumericValue(sndigs.charAt(i));
	}
	public char getSNCHAR(int i)
	{
		if(i < 0)
			i = 0;
		if(i >= 6)
			i = 5;
		return sn.charAt(i);
	}
	public boolean isSNDIG(int i)
	{
		if(i < 0)
			i = 0;
		if(i >= 6)
			i = 5;
		return ("0123456789".indexOf(sn.charAt(i)) >= 0);
	}
	public int getSNSUM()
	{
		int sum = 0;
		for(int aa = 0; aa < sndigs.length(); aa++)
			sum += Character.getNumericValue(sndigs.charAt(aa));
		return sum;
	}
	public int numCharsInSN(String l)
	{
		int sum = 0;
		for(int aa = 0; aa < 6; aa++)
		{
			if(l.contains(sn.charAt(aa) + ""))
				sum++;
		}
		return sum;
	}
	/**
	 * 
	 * @return The largest digit in the serial number.
	 */
	public int getLargestSNDIG()
	{
		char order[] = sndigs.toCharArray();
		Arrays.sort(order);
		return Character.getNumericValue(order[order.length - 1]);
	}
	/**
	 * 
	 * @return The smallest digit in the serial number.
	 */
	public int getSmallestSNDIG()
	{
		char order[] = sndigs.toCharArray();
		Arrays.sort(order);
		return Character.getNumericValue(order[0]);
	}
	public String getSN(){return sn.toUpperCase();}
	public int numTF(){return tf;}
	public int numDOM(){return dom;}
	private boolean v1(String i)
	{
		String[] conv = i.split("/");
		if(conv.length == 2)
			return (isNum(conv[0]) && isNum(conv[1]));
		return false;
	}
	private boolean v2(String i)
	{
		if(i.equals(""))
			return true;
		String[] conv = i.split(" ");
		for(int aa = 0; aa < conv.length; aa++)
		{
			if(conv[aa].length() == 4)
				conv[aa] = conv[aa].replace("*", "");
			if(conv[aa].length() != 3)
				return false;
		}
		return true;
	}
	private boolean v3(String i)
	{
		if(i.equals("EMPTY") || i.equals(""))
			return true;
		String[] conv = i.split(" ");
		for(int aa = 0; aa < conv.length; aa++)
		{
			switch(conv[aa])
			{
				case "PARALLEL":
				case "PARA":
				case "SERIAL":
				case "SER":
				case "DVI":
				case "DVI-D":
				case "DVID":
				case "RJ":
				case "RJ-45":
				case "RJ45":
				case "PS/2":
				case "PS":
				case "PS2":
				case "STEREO RCA":
				case "RCA":
				case "AC":
				case "COMPONENT":
				case "HDMI":
				case "COMPOSITE":
				case "VGA":
				case "USB":
				case "PCMCIA":
					break;
				default:
					return false;
			}
		}
		return true;
	}
	private boolean v4(String i)
	{
		String[] possible = {
				"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ",
				"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ",
				"0123456789",
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
				"0123456789"
		};
		if(i.length() == 6)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if(possible[aa].indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
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
	private ArrayList<String> getArrayListPort(String[] l)
	{
		ArrayList<String> temp = new ArrayList<String>();
		for(int aa = 0; aa < l.length; aa++)
		{
			switch(l[aa])
			{
				case "PARALLEL":
				case "PARA":
					temp.add("PARALLEL");
					break;
				case "SERIAL":
				case "SER":
					temp.add("SERIAL");
					break;
				case "DVI":
				case "DVI-D":
				case "DVID":	
					temp.add("DVI-D");
					break;
				case "RJ":
				case "RJ-45":
				case "RJ45":	
					temp.add("RJ-45");
					break;
				case "PS/2":
				case "PS":
				case "PS2":	
					temp.add("PS/2");
					break;
				case "STEREO RCA":
				case "RCA":
					temp.add("RCA");
					break;
				case "AC":
					temp.add("AC");
					break;
				case "COMPONENT":
					temp.add("COMPONENT");
					break;
				case "HDMI":
					temp.add("HDMI");
					break;
				case "COMPOSITE":
					temp.add("COMPOSITE");
					break;
				case "VGA":
					temp.add("VGA");
					break;
				case "USB":
					temp.add("USB");
					break;
				case "PCMCIA":
					temp.add("PCMCIA");
					break;
			}
		}
		return temp;
	}
}
