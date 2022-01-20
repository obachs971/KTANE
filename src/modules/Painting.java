package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Painting 
{
	private final BombEdgework ew;
	public Painting(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		if(ew.findPort("DVI-D") == 2 && ew.findPort("RJ-45") == 1 && ew.findLit("CLR"))
			JOptionPane.showMessageDialog(null, "Change every color into a different color\nBob wants to see your creativity :)");
		else
		{
			int type = -1;
			String[] blindness = {"PROTANOMALY", "DEUTERANOMALY", "TRITANOPIA"};
			
			int sum = ew.BAT() + ew.numInd() + ew.numPorts() + 2;
			for(int aa = 0; aa < 3; aa++)
			{
				if(sum == blindness[aa].length())
					type = aa;
			}
			if(type == -1)
			{
				String alpha = ew.getIndChars();
				for(int aa = 0; aa < alpha.length(); aa++)
				{
					for(int bb = aa + 1; bb < alpha.length(); bb++)
					{
						if(alpha.charAt(aa) == alpha.charAt(bb))
							alpha = alpha.substring(0, bb) + "" + alpha.substring(bb + 1);
					}
				}
				System.out.println(alpha);
				int best = 0, numBest = 1;
				for(int aa = 0; aa < blindness.length; aa++)
				{
					int score = 0;
					for(int bb = 0; bb < blindness[aa].length(); bb++)
					{
						if(alpha.indexOf(blindness[aa].charAt(bb)) >= 0)
							score++;
					}
					if(score > best)
					{
						type = aa;
						best = score;
						numBest = 1;
					}
					else if(score == best)
						numBest++;
				}
				if(numBest > 1)
					type = 0;
			}
			switch(type)
			{
				case 0:
					JOptionPane.showMessageDialog(null, "Purple <> Pink\nBlack > Red\nOrange > Red\nBlue > Red\nGreen > Orange\nBrown > Green");
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Green <> Yellow\nPurple <> Brown\nRed > Green\nPink > Gray\nBlue > Pink");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Blue <> Gray\nPurple <> Black\nOrange <> Red\nGreen > Blue");
					break;
			}
		}
	}
}
