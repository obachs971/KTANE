package modules;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class Moon
{
	private int[] nums;
	private int[] totalLed;
	private final BombConfig con;
	private final BombEdgework ew;
	public Moon(BombConfig c, BombEdgework e)
	{
		con = c;
		ew = e;
		nums = new int[6];
		for(int cc = 0; cc < 6; cc++)
		{
			nums[cc] = numToLet(ew.getSNCHAR(cc));
			if(nums[cc] < 10)
				nums[cc] = nums[cc] * 10;
			else
				nums[cc] = ((nums[cc] % 10) * 10) + (nums[cc] / 10);
			nums[cc] = nums[cc] % 7;	
		}
		
		totalLed = new int[8];
		totalLed[0] = ew.BD();
		totalLed[1] = ew.BA();
		totalLed[2] = ew.numSNDIGS();
		totalLed[3] = ew.numPlates();
		totalLed[4] = con.getNumberModules();
		totalLed[5] = ew.numCharsInSN("BCDFGHJKLMNPQRSTVWXYZ");
		totalLed[6] = ew.numInd();
		totalLed[7] = ew.numPorts();
		for(int dd = 0; dd < 8; dd++)
		{
			totalLed[dd] = totalLed[dd] % 7;
		}
	}
	public String run()
	{
		String souv = "";
		boolean[] pressed =
			{
				false,
				false,
				false,
				false,
				false,
				false,
				false,
				false
			};
		String[] dir = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[dir.length];
		optionPane.setLayout(new GridLayout(4, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < dir.length; aa++)
		{
			jButton[aa] = getButton(optionPane, dir[aa], aa + "");
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the 1st LIT set:");
		dialog.setVisible(true);
		int cur = Integer.parseInt(optionPane.getValue().toString());
		
		
		int firstUnlit = (cur + 4) % 8;
		int counter = 0;
		for(int ss = cur; ss < (cur + 8); ss++)
		{
			if(counter < 4)
				souv = souv + "LIT " + (counter + 1) + ": " + dir[ss % 8] + "\n";
			else
				souv = souv + "UNLIT " + (counter - 3) + ": " + dir[ss % 8] + "\n";
			counter++;
		}
		int[] ledNums = new int[8];
		for(int bb = 0; bb < 8; bb++)
		{
			ledNums[cur % 8] = totalLed[bb];
			cur++;
		}
		int offset = -1;
		int sumDig = ew.getSNSUM() % 4;
		switch(sumDig)
		{
			case 0:
				offset = 3;
				break;
			case 1:
				offset = 0;
				break;
			case 2:
				offset = 1;
				break;
			case 3:
				offset = 2;
				break;
		}
		cur = (offset + firstUnlit) % 8;
		String[] order = new String[8];
		order[0] = dir[cur];
		pressed[cur] = true;
		for(int cc = 0; cc < 6; cc++)
		{
			int add;
			if(nums[cc] > 3)
				add = -2;
			else
				add = 2;
			cur += add;
			while(cur < 0)
				cur += 8;
			while(cur >= 8)
				cur -= 8;
			add = add / 2;
			while(pressed[cur])
			{
				cur += add;
				while(cur < 0)
					cur += 8;
				while(cur >= 8)
					cur -= 8;
			}
			order[cc + 1] = dir[cur];
			pressed[cur] = true;
		}
		while(pressed[cur])
		{
			cur += 1;
			while(cur < 0)
				cur += 8;
			while(cur >= 8)
				cur -= 8;
			order[7] = dir[cur];
		}
		String output = "";
		for(int dd = 0; dd < 8; dd++)
		{
			boolean center = false;
			for(int ee = 0; ee < 8; ee++)
			{
				if(order[dd].equals(dir[ee]))
				{
					if(ledNums[ee] < 3)
						output = output + "" + order[dd].toUpperCase() + " OUTER\n";
					else if(ledNums[ee] < 5)
						output = output + "" + order[dd].toUpperCase() + " INNER\n";
					else
					{
						output = output + "CENTER";
						center = true;
						break;
					}
				}
				if(center)
					break;
			}
			if(center)
				break;
		}
		JOptionPane.showMessageDialog(null, "Press these buttons:\n" + output);
		return souv;
	}
	
	private int numToLet(char l)
	{
		if("0123456789".indexOf(l) >= 0)
			return "0123456789".indexOf(l);
		else
			return "-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(l);
	}
	private JButton getButton(final JOptionPane optionPane, String text, String index) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(index);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
