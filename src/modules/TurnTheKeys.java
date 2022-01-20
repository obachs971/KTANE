package modules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class TurnTheKeys 
{
	private final BombEdgework bombEW;
	private final double resizer;
	private final boolean souv;
	private final int playType;
	private final ArrayList<String> timeModuleList;
	private final ArrayList<int[]> timeWarns;
	
	public TurnTheKeys(BombEdgework e, double r, boolean s, int pt, ArrayList<String> tml, ArrayList<int[]> tw)
	{
		bombEW = e;
		resizer = r;
		souv = s;
		playType = pt;
		timeModuleList = tml;
		timeWarns = tw;
	}
	public ArrayList<String[]> run()
	{
		ArrayList<String[]> souvenirList = new ArrayList<String[]>();
		boolean loop = true;
		do
		{
			String module = JOptionPane.showInputDialog("Morse Code\nWires\nTwo Bits\nThe Button\nColour Flash\nRound Keypad\nEnter a module from the list:").toUpperCase();
			switch(module)
			{
				case "MORSE CODE":
					MorseCode morseCode = new MorseCode();
					morseCode.run();
					break;
				case "WIRES":
					Wires wires = new Wires(bombEW);
					wires.run();
					break;
				case "TWO BITS":
					TwoBits twoBits = new TwoBits(bombEW);
					souvenirList.add(new String[]{"TWO BITS", twoBits.run()});
					break;
				case "BUTTON":
				case "THE BUTTON":
					Button button = new Button(bombEW, souv, playType);
					souvenirList.add(new String[]{"BUTTON", button.run()});
					break;
				case "COLOUR FLASH":
					ColourFlash colourFlash = new ColourFlash(playType);
					souvenirList.add(new String[]{"COLOUR FLASH", colourFlash.run()});
					break;
				case "ROUND KEYPAD":
					RoundKeypad roundKeypad = new RoundKeypad(resizer);
					roundKeypad.run();
					break;
				case "":
					loop = false;
					break;
			}
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        String conv = sdf.format(cal.getTime());
	        String[] split = conv.split(":");
	        int[] current = new int[2];
	        current[0] = Integer.parseInt(split[0]);
	        current[1] = Integer.parseInt(split[1]);
	        for(int aa = 0; aa < timeWarns.size(); aa++)
	        {
	        	if(timeWarns.get(aa)[0] == current[0] && timeWarns.get(aa)[1] <= (current[1] + 1))
	        	{
	        		JOptionPane.showMessageDialog(null, "This module requires attention:\n" + timeModuleList.get(aa), "", JOptionPane.WARNING_MESSAGE);
	        		timeWarns.remove(aa);
	        		timeModuleList.remove(aa);
	        	}
	        		
	        }
		}while(loop);
		JOptionPane.showMessageDialog(null, "Turn all the right keys\nin descending order");
		loop = true;
		do
		{
			String module = JOptionPane.showInputDialog("Password\nWho's On First\nCrazy Talk\nKeypad\nListening\nOrientation Cube\nEnter a module from the list:").toUpperCase();
			switch(module)
			{
				case "PASSWORD":
					Password password = new Password(playType);
					password.run();
					break;
				case "WHO'S ON FIRST":
					WhosOnFirst whosOnFirst = new WhosOnFirst(playType);
					souvenirList.add(new String[]{"WHO'S ON FIRST", whosOnFirst.run()});
					break;
				case "CRAZY TALK":
					CrazyTalk crazyTalk = new CrazyTalk();
					crazyTalk.run();
					break;
				case "KEYPAD":
					Keypad keypad = new Keypad(resizer);
					keypad.run();
					break;
				case "LISTENING":
					Listening listening = new Listening();
					souvenirList.add(new String[]{"LISTENING", listening.run()});
					break;
				case "ORIENTATION CUBE":
					OrientationCube orientationCube = new OrientationCube(bombEW);
					souvenirList.add(new String[]{"ORIENTATION CUBE", orientationCube.run()});
					break;
				case "":
					loop = false;
					break;
			}
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        String conv = sdf.format(cal.getTime());
	        String[] split = conv.split(":");
	        int[] current = new int[2];
	        current[0] = Integer.parseInt(split[0]);
	        current[1] = Integer.parseInt(split[1]);
	        for(int aa = 0; aa < timeWarns.size(); aa++)
	        {
	        	if(timeWarns.get(aa)[0] == current[0] && timeWarns.get(aa)[1] <= (current[1] + 1))
	        	{
	        		JOptionPane.showMessageDialog(null, "This module requires attention:\n" + timeModuleList.get(aa), "", JOptionPane.WARNING_MESSAGE);
	        		timeWarns.remove(aa);
	        		timeModuleList.remove(aa);
	        	}
	        }
		}while(loop);
		JOptionPane.showMessageDialog(null, "Turn all the left keys\nin ascending order");
		return souvenirList;
	}
}
