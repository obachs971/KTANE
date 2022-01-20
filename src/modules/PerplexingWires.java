package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class PerplexingWires 
{
	private final BombEdgework ew;
	public PerplexingWires(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String alpha = "LITQICUBMFLJIHJQWUVWPCDPVRRTFHBD";
		String[] info = new String[4];
		info[0] = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nW - White\nK - Black\nEnter the wires' colors:").toUpperCase();
		boolean v = v1(info[0]);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			info[0] = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nW - White\nK - Black\nEnter the wires' colors:").toUpperCase();
			v = v1(info[0]);
		}
		info[1] = JOptionPane.showInputDialog("Enter the wires that share\nthe same color as its arrow:");
		v = v2(info[1]);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			info[1] = JOptionPane.showInputDialog("Enter the wires that share\nthe same color as its arrow:");
			v = v2(info[1]);
		}
		info[2] = JOptionPane.showInputDialog("Enter the wires that are\nconnected to a black star:");
		v = v2(info[2]);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			info[2] = JOptionPane.showInputDialog("Enter the wires that are\nconnected to a black star:");
			v = v2(info[2]);
		}
		info[3] = JOptionPane.showInputDialog("Enter the wires that\ncrosses another wire:");
		v = v2(info[3]);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			info[3] = JOptionPane.showInputDialog("Enter the wires that\ncrosses another wire:");
			v = v2(info[3]);
		}
		String beg = "", mid = "", las = "", LEDS = "";
		for(int aa = 0; aa < 6; aa++)
		{
			String index = (aa + 1) + "";
			int sum = 0;
			if("RYBW".indexOf(info[0].charAt(aa)) >= 0)
				sum += 1;
			if(info[1].contains(index))
				sum += 2;
			if(info[2].contains(index))
				sum += 4;
			if(aa % 2 == 1)
				sum += 8;
			if(info[3].contains(index))
				sum += 16;
			switch(alpha.charAt(sum))
			{
				case 'C':
					mid = mid + "" + index + " ";
					break;
				case 'F':
					beg = beg + "" + index + " ";
					break;
				case 'L':
					las = las + "" + index + " ";
					break;
				case 'W':
					if(LEDS.length() == 0)
						LEDS = getLEDS();
					if((LEDS.charAt(0) == '1' && LEDS.charAt(1) == '1') || (LEDS.charAt(0) == '1' && LEDS.charAt(2) == '1') || (LEDS.charAt(1) == '1' && LEDS.charAt(2) == '1'))
						mid = mid + "" + index + " ";
					break;
				case 'T':
					if(LEDS.length() == 0)
						LEDS = getLEDS();
					if(LEDS.charAt(0) == '1')
						mid = mid + "" + index + " ";
					break;
				case 'U':
					if(JOptionPane.showConfirmDialog(null, "Does arrow #" + index + " point Up/Down?") == 0)
						mid = mid + "" + index + " ";
					break;
				case 'M':
					if(JOptionPane.showConfirmDialog(null, "Does arrow #" + index + " point Down/Right?") == 0)
						mid = mid + "" + index + " ";
					break;
				case 'H':
					if(JOptionPane.showConfirmDialog(null, "Does wire #" + index + " shares a star?") == 0)
						mid = mid + "" + index + " ";
					break;
				case 'P':
					if((aa + 1) == ew.numPorts())
						mid = mid + "" + index + " ";
					break;
				case 'B':
					if((aa + 1) == ew.BAT())
						mid = mid + "" + index + " ";
					break;
				case 'I':
					if((aa + 1) == ew.numInd())
						mid = mid + "" + index + " ";
					break;
				case 'Q':
					sum = 0;
					for(int bb = 0; bb < 6; bb++)
					{
						if(info[0].charAt(aa) == info[0].charAt(bb))
							sum++;
					}
					if(sum == 1)
						mid = mid + "" + index + " ";
					break;
				case 'J':
					if(aa == 0)
					{
						if("OP".indexOf(info[0].charAt(1)) >= 0)
							mid = mid + "" + index + " ";
					}
					else if(aa == 5)
					{
						if("OP".indexOf(info[0].charAt(4)) >= 0)
							mid = mid + "" + index + " ";
					}
					else
					{
						if("OP".indexOf(info[0].charAt(aa + 1)) >= 0 || "OP".indexOf(info[0].charAt(aa - 1)) >= 0)
							mid = mid + "" + index + " ";
					}
					break;
				case 'V':
					if(ew.numCharsInSN("AEIOU") > 0 || ew.findPort("USB") > 0)
						mid = mid + "" + index + " ";
					break;
				case 'R':
					if(JOptionPane.showConfirmDialog(null, "Is arrow's #" + index + " direction unique?") == 0)
						mid = mid + "" + index + " ";
					break;
			}
		}
		JOptionPane.showMessageDialog(null, "Cut these wires:\n" + beg + "" + mid + "" + las);
	}
	private String getLEDS()
	{
		String LEDS = JOptionPane.showInputDialog("0 - Off\n1 - On\nEnter the leds:");
		boolean v = v3(LEDS);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			LEDS = JOptionPane.showInputDialog("0 - Off\n1 - On\nEnter the leds:");
			v = v3(LEDS);
		}
		return LEDS;
	}
	private boolean v1(String i)
	{
		if(i.length() == 6)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ROYGBPWK".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("123456".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
	private boolean v3(String i)
	{
		if(i.length() == 3)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("01".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
