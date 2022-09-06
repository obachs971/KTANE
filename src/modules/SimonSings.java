package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import misc.MTK;
import start.BombEdgework;

public class SimonSings 
{
	private final String[] noteOrder = {
		"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"
	};
	private final BombEdgework ew;
	public SimonSings(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String[][] notes = new String[3][];
		int[][] numbers = new int[3][2];
		String[][] press = new String[3][2];
		MTK mtk = new MTK();
		String half = ew.numCharsInSN("AEIOU") > 0 ? "LR" : "RL";
		for(int stage = 1; stage <= 3; stage++)
		{
			String input = JOptionPane.showInputDialog("Enter the 8 notes (spaces):").replace("'", "#").replace("-", "#").toUpperCase();
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the 8 notes (spaces):").replace("'", "#").replace("-", "#").toUpperCase();
				v = valid(input);
			}
			notes[stage - 1] = input.split(" ");
			String bin = "";
			for(int i = 0; i < notes[stage - 1].length; i++)
			{
				switch(notes[stage - 1][i])
				{
					case "C":
						bin += (i == 0 || i == 3 || i == 4 || i == 7) ? "1" : "0";
						break;
					case "C#": case "DB":
						bin += (i == 1 || i == 2 || i == 5 || i == 6) ? "1" : "0";
						break;
					case "D":
						bin += (i == 0) ? (ew.getSNDIG(ew.numSNDIGS() - 1) % 2) + "" : (bin.charAt(i - 1) == '0') ? "1" : "0";
						break;
					case "D#": case "EB":
						bin += ((ew.numPlates() - 1) == (i % 4)) ? "1" : "0";
						break;
					case "E":
						if(ew.numPlates() == 0)
							bin += ((ew.BAT() % 2) + "");
						else
						{
							int best = 0;
							ArrayList<ArrayList<String>> ports = ew.getPorts();
							for(ArrayList<String> plate : ports)
							{
								if(plate.size() > best)
									best = plate.size();
							}
							bin += ((best - 1) == (i % 4)) ? "1" : "0";
						}
						break;
					case "F":
						bin += (stage == 3) ? "1" : "0";
						break;
					case "F#": case "GB": 
						bin += (stage == (ew.numSNLETS() - 1)) ? "1" : "0";
						break;
					case "G":
						bin += (i % 4 == 0) ? ((ew.numInd() % 2) + "") : (notes[stage - 1][(i / 4) * 4].length() == 2) ? "1" : "0";
						break;
					case "G#": case "AB":
						if(stage == 1)
							bin += ((ew.numPorts() % 2) + "");
						else
						{
							String ga = "0";
							for(int j = 0; j < 7; j++)
							{
								if(notes[stage - 2][j].length() == 2 && notes[stage - 2][j + 1].length() == 2)
								{
									ga = "1";
									break;
								}
							}
							bin += ga;
						}
						break;
					case "A":
						bin += (stage == 1) ? (((ew.numInd() + 1) % 2) + "") : (numbers[stage - 2][0] < 5 || numbers[stage - 2][1] < 5) ? "1" : "0";
						break;
					case "A#": case "BB":
						int pos = (i / 4) * 4;
						String ab = "0";
						for(int j = 0; j < 4; j++)
						{
							if(notes[stage - 1][pos + j].equals("F") || notes[stage - 1][pos + j].equals("F#") || notes[stage - 1][pos + j].equals("GB"))
							{
								ab = "1";
								break;
							}
						}
						bin += ab;
						break;
					default:
						bin += "?";
				}
			}
			for(int i = 0; i < 2; i++)
			{
				String tempBin = bin.substring(i * 4, (i + 1) * 4);
				if(tempBin.contains("?"))
				{
					int check = Integer.parseInt(mtk.baseConvert(tempBin.replace("?", "1"), 2, 10));
					if(check == 2 || check == 3 || check == 5 || check == 7 || check == 11 || check == 13)
						numbers[stage - 1][i] = check;
					else
						numbers[stage - 1][i] = Integer.parseInt(mtk.baseConvert(tempBin.replace("?", "0"), 2, 10));
				}
				else
					numbers[stage - 1][i] = Integer.parseInt(mtk.baseConvert(tempBin, 2, 10));
				if(numbers[stage - 1][i] >= 12)
					press[stage - 1][i] = half.charAt(i) + " " + notes[stage - 1][numbers[stage - 1][i] - 12 + (i * 4)]; 
				else
					press[stage - 1][i] = half.charAt(i) + " " + noteOrder[numbers[stage - 1][i]];
			}
			String out = press[0][0] + ", " + press[0][1];
			for(int i = 1; i < stage; i++)
				out = out + "\n" + press[i][0] + ", " + press[i][1];
			JOptionPane.showMessageDialog(null, "Press these keys:\n" + out);
		}
		String souv = "";
		for(int i = 0; i < notes.length; i++)
		{
			souv = souv + "STAGE #" + (i + 1) + ": ";
			for(int j = 0; j < notes[i].length; j++)
				souv = souv + "" + notes[i][j];
			if(i < 2)
				souv += "\n";
		}
		return souv;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 8)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				switch(conv[aa])
				{
					case "C":
					case "C#": case "DB":
					case "D":
					case "D#": case "EB":
					case "E":
					case "F":
					case "F#": case "GB":
					case "G":
					case "G#": case "AB":
					case "A":
					case "A#": case "BB":
					case "B":
						break;
					default:
						return false;
				}
				for(int bb = aa + 1; bb < conv.length; bb++)
				{
					if(conv[aa].equals(conv[bb]))
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
