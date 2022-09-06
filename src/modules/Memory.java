package modules;

import javax.swing.JOptionPane;

import misc.PlayType;

public class Memory 
{
	private final PlayType PT;
	public Memory(PlayType pt)
	{
		PT = pt;
	}
	private int[][] info;
	private String[] displays;
	public String run()
	{
		info = new int[4][2];
		displays = new String[4];
		stage1();
		stage2();
		stage3();
		stage4();
		stage5();
		String souv = "STAGE 1:\nDISPLAYED: " + displays[0] + "\nPOSITION: " + (info[0][0] + 1) + "\nLABEL: " + info[0][1] + "\n--------------------\n";
		souv = souv + "STAGE 2:\nDISPLAYED: " + displays[1] + "\nPOSITION: " + (info[1][0] + 1) + "\nLABEL: " + info[1][1] + "\n--------------------\n";
		souv = souv + "STAGE 3:\nDISPLAYED: " + displays[2] + "\nPOSITION: " + (info[2][0] + 1) + "\nLABEL: " + info[2][1] + "\n--------------------\n";
		souv = souv + "STAGE 4:\nDISPLAYED: " + displays[3] + "\nPOSITION: " + (info[3][0] + 1) + "\nLABEL: " + info[3][1];
		return souv;
	}
	private void stage1()
	{
		if(PT == PlayType.Team)
		{
			String display = JOptionPane.showInputDialog("Enter the displayed number:");
			boolean v = (display.length() == 1 && "1234".indexOf(display) >= 0);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				display = JOptionPane.showInputDialog("Enter the displayed number:");
				v = (display.length() == 1 && "1234".indexOf(display) >= 0);
			}
			displays[0] = display.toUpperCase();
			String label;
			switch(display)
			{
				case "1":
				case "2":
					info[0][0] = 1;
					label = JOptionPane.showInputDialog("Enter the button label\nin the 2nd position\nThen press it.");
					v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						label = JOptionPane.showInputDialog("Enter the button label\nin the 2nd position\nThen press it.");
						v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					}
					info[0][1] = Integer.parseInt(label);
					break;
				case "3":
					info[0][0] = 2;
					label = JOptionPane.showInputDialog("Enter the button label\nin the 3rd position\nThen press it.");
					v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						label = JOptionPane.showInputDialog("Enter the button label\nin the 3rd position\nThen press it.");
						v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					}
					info[0][1] = Integer.parseInt(label);
					break;
				default:
					info[0][0] = 3;
					label = JOptionPane.showInputDialog("Enter the button label\nin the 4th position\nThen press it.");
					v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						label = JOptionPane.showInputDialog("Enter the button label\nin the 4th position\nThen press it.");
						v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					}
					info[0][1] = Integer.parseInt(label);
					break;
			}
		}
		else
		{
			String input = JOptionPane.showInputDialog("Enter the displayed number\nAnd the buttons (spaced):");	
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the displayed number\nAnd the buttons (spaced):");	
				v = valid(input);
			}
			String[] temp = input.split(" ");
			displays[0] = temp[0].toUpperCase();
			switch(temp[0])
			{
				case "1":
				case "2":
					JOptionPane.showMessageDialog(null, "Press the button in\nthe 2nd position");
					info[0][0] = 1;
					info[0][1] = Character.getNumericValue(temp[1].charAt(info[0][0]));
					break;
				case "3":
					JOptionPane.showMessageDialog(null, "Press the button in\nthe 3rd position");
					info[0][0] = 2;
					info[0][1] = Character.getNumericValue(temp[1].charAt(info[0][0]));
					break;
				default:
					JOptionPane.showMessageDialog(null, "Press the button in\nthe 4th position");
					info[0][0] = 3;
					info[0][1] = Character.getNumericValue(temp[1].charAt(info[0][0]));
					break;
			}
		}
	}
	private void stage2()
	{
		String[] pos = {"1st", "2nd", "3rd", "4th"};
		if(PT == PlayType.Team)
		{
			String display = JOptionPane.showInputDialog("Enter the displayed number:");
			boolean v = (display.length() == 1 && "1234".indexOf(display) >= 0);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				display = JOptionPane.showInputDialog("Enter the displayed number:");
				v = (display.length() == 1 && "1234".indexOf(display) >= 0);
			}
			displays[1] = display.toUpperCase();
			String label;
			String position;
			switch(display)
			{
				case "1":
					info[1][1] = 4;
					position = JOptionPane.showInputDialog("Enter the button position\nthat is labeled 4\nThen press it.");
					v = (position.length() == 1 && "1234".indexOf(position) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						position = JOptionPane.showInputDialog("Enter the button position\nthat is labeled 4\nThen press it.");
						v = (position.length() == 1 && "1234".indexOf(position) >= 0);
					}
					info[1][0] = Integer.parseInt(position) - 1;
					break;
				case "2":
				case "4":
					info[1][0] = info[0][0];
					label = JOptionPane.showInputDialog("Enter the button label\nin the " + pos[info[1][0]] + " position\nThen press it.");
					v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						label = JOptionPane.showInputDialog("Enter the button label\nin the " + pos[info[1][0]] + " position\nThen press it.");
						v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					}
					info[1][1] = Integer.parseInt(label);
					break;
				default:
					info[1][0] = 0;
					label = JOptionPane.showInputDialog("Enter the button label\nin the 1st position\nThen press it.");
					v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						label = JOptionPane.showInputDialog("Enter the button label\nin the 1st position\nThen press it.");
						v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					}
					info[1][1] = Integer.parseInt(label);
					break;
			}
		}
		else
		{
			String input = JOptionPane.showInputDialog("Enter the displayed number\nAnd the buttons (spaced):");	
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the displayed number\nAnd the buttons (spaced):");	
				v = valid(input);
			}
			String[] temp = input.split(" ");
			displays[1] = temp[0].toUpperCase();
			switch(temp[0])
			{
				case "1":
					info[1][0] = temp[1].indexOf("4");
					info[1][1] = 4;
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[1][0]] + " position");		
					break;
				case "2":
				case "4":
					info[1][0] = info[0][0];
					info[1][1] = Character.getNumericValue(temp[1].charAt(info[1][0]));
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[1][0]] + " position");		
					break;
				default:
					info[1][0] = 0;
					info[1][1] = Character.getNumericValue(temp[1].charAt(info[1][0]));
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[1][0]] + " position");		
					break;
			}
		}
	}
	private void stage3()
	{
		String[] pos = {"1st", "2nd", "3rd", "4th"};
		if(PT == PlayType.Team)
		{
			String display = JOptionPane.showInputDialog("Enter the displayed number:");
			boolean v = (display.length() == 1 && "1234".indexOf(display) >= 0);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				display = JOptionPane.showInputDialog("Enter the displayed number:");
				v = (display.length() == 1 && "1234".indexOf(display) >= 0);
			}
			displays[2] = display.toUpperCase();
			String label;
			String position;
			switch(display)
			{
				case "1":
					info[2][1] = info[1][0];
					position = JOptionPane.showInputDialog("Enter the button position\nthat is labeled " + info[2][1] + "\nThen press it.");
					v = (position.length() == 1 && "1234".indexOf(position) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						position = JOptionPane.showInputDialog("Enter the button position\nthat is labeled " + info[2][1] + "\nThen press it.");
						v = (position.length() == 1 && "1234".indexOf(position) >= 0);
					}
					info[2][0] = Integer.parseInt(position) - 1;
					break;
				case "2":
					info[2][1] = info[0][0];
					position = JOptionPane.showInputDialog("Enter the button position\nthat is labeled " + info[2][1] + "\nThen press it.");
					v = (position.length() == 1 && "1234".indexOf(position) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						position = JOptionPane.showInputDialog("Enter the button position\nthat is labeled " + info[2][1] + "\nThen press it.");
						v = (position.length() == 1 && "1234".indexOf(position) >= 0);
					}
					info[2][0] = Integer.parseInt(position) - 1;
					break;
				case "3":
					info[2][0] = 2;
					label = JOptionPane.showInputDialog("Enter the button label\nin the 3rd position\nThen press it.");
					v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						label = JOptionPane.showInputDialog("Enter the button label\nin the 3rd position\nThen press it.");
						v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					}
					info[2][1] = Integer.parseInt(label);
					break;
				default:
					info[2][1] = 4;
					position = JOptionPane.showInputDialog("Enter the button position\nthat is labeled 4\nThen press it.");
					v = (position.length() == 1 && "1234".indexOf(position) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						position = JOptionPane.showInputDialog("Enter the button position\nthat is labeled 4\nThen press it.");
						v = (position.length() == 1 && "1234".indexOf(position) >= 0);
					}
					info[2][0] = Integer.parseInt(position) - 1;
					break;
			}
		}
		else
		{
			String input = JOptionPane.showInputDialog("Enter the displayed number\nAnd the buttons (spaced):");	
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the displayed number\nAnd the buttons (spaced):");	
				v = valid(input);
			}
			String[] temp = input.split(" ");
			displays[2] = temp[0].toUpperCase();
			switch(temp[0])
			{
				case "1":
					info[2][0] = temp[1].indexOf(info[1][1] + "");
					info[2][1] = info[1][1];
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[2][0]] + " position");		
					break;
				case "2":
					info[2][0] = temp[1].indexOf(info[0][1] + "");
					info[2][1] = info[0][1];
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[2][0]] + " position");		
					break;
				case "3":
					info[2][0] = 2;
					info[2][1] = Character.getNumericValue(temp[1].charAt(info[2][0]));
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[2][0]] + " position");		
					break;
				default:
					info[2][0] = temp[1].indexOf("4");
					info[2][1] = 4;
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[2][0]] + " position");		
					break;
			}
		}
	}
	private void stage4()
	{
		String[] pos = {"1st", "2nd", "3rd", "4th"};
		if(PT == PlayType.Team)
		{
			String display = JOptionPane.showInputDialog("Enter the displayed number:");
			boolean v = (display.length() == 1 && "1234".indexOf(display) >= 0);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				display = JOptionPane.showInputDialog("Enter the displayed number:");
				v = (display.length() == 1 && "1234".indexOf(display) >= 0);
			}
			displays[3] = display.toUpperCase();
			String label;
			switch(display)
			{
				case "1":
					info[3][0] = info[0][0];
					label = JOptionPane.showInputDialog("Enter the button label\nin the " + pos[info[3][0]] + " position\nThen press it.");
					v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						label = JOptionPane.showInputDialog("Enter the button label\nin the " + pos[info[3][0]] + " position\nThen press it.");
						v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					}
					info[3][1] = Integer.parseInt(label);
					break;
				case "2":
					info[3][0] = 0;
					label = JOptionPane.showInputDialog("Enter the button label\nin the 1st position\nThen press it.");
					v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						label = JOptionPane.showInputDialog("Enter the button label\nin the 1st position\nThen press it.");
						v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					}
					info[3][1] = Integer.parseInt(label);
					break;
				default:
					info[3][0] = info[1][0];
					label = JOptionPane.showInputDialog("Enter the button label\nin the " + pos[info[3][0]] + " position\nThen press it.");
					v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						label = JOptionPane.showInputDialog("Enter the button label\nin the " + pos[info[3][0]] + " position\nThen press it.");
						v = (label.length() == 1 && "1234".indexOf(label) >= 0);
					}
					info[3][1] = Integer.parseInt(label);
					break;
			}
		}
		else
		{
			String input = JOptionPane.showInputDialog("Enter the displayed number\nAnd the buttons (spaced):");	
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the displayed number\nAnd the buttons (spaced):");	
				v = valid(input);
			}
			String[] temp = input.split(" ");
			displays[3] = temp[0].toUpperCase();
			switch(temp[0])
			{
				case "1":
					info[3][0] = info[0][0];
					info[3][1] = Character.getNumericValue(temp[1].charAt(info[3][0]));
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[3][0]] + " position");		
					break;
				case "2":
					info[3][0] = 0;
					info[3][1] = Character.getNumericValue(temp[1].charAt(info[3][0]));
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[3][0]] + " position");		
					break;
				default:
					info[3][0] = info[1][0];
					info[3][1] = Character.getNumericValue(temp[1].charAt(info[3][0]));
					JOptionPane.showMessageDialog(null, "Press the button in\nthe " + pos[info[3][0]] + " position");		
					break;
			}
		}
	}
	private void stage5()
	{
		String input = JOptionPane.showInputDialog("Enter the displayed number:");	
		boolean v = (input.length() == 1 && "1234".indexOf(input) >= 0);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the displayed number:");
			v = (input.length() == 1 && "1234".indexOf(input) >= 0);
		}
		String[] temp = input.split(" ");
		switch(temp[0])
		{
			case "1":
				JOptionPane.showMessageDialog(null, "Press the button\nlabeled " + info[0][1]);		
				break;
			case "2":
				JOptionPane.showMessageDialog(null, "Press the button\nlabeled " + info[1][1]);	
				break;
			case "3":
				JOptionPane.showMessageDialog(null, "Press the button\nlabeled " + info[3][1]);	
				break;
			default:
				JOptionPane.showMessageDialog(null, "Press the button\nlabeled " + info[2][1]);	
				break;
		}
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			if(conv[0].length() == 1 && conv[1].length() == 4)
			{
				String possnums = "1234";
				if(possnums.contains(conv[0]))
				{
					boolean[] flags = {false, false, false, false};
					for(int aa = 0; aa < 4; aa++)
					{
						switch(conv[1].charAt(aa))
						{
							case '1':
								flags[0] = true;
								break;
							case '2':
								flags[1] = true;
								break;
							case '3':
								flags[2] = true;
								break;
							case '4':
								flags[3] = true;
								break;
						}
					}
					return (flags[0] && flags[1] && flags[2] && flags[3]);
				}
			}
		}
		return false;
	}
}
