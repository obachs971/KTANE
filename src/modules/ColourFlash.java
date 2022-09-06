package modules;

import javax.swing.JOptionPane;

import misc.PlayType;

public class ColourFlash 
{
	private final PlayType playType;
	public ColourFlash(PlayType pt)
	{
		playType = pt;
	}
	public String run()
	{
		String souv;
		if(playType == PlayType.Team)
		{
			String lastColor = JOptionPane.showInputDialog("Red, Blue, Yellow,\nGreen, White, Magenta\nEnter the last COLOR\nof the sequence:").toUpperCase();
			boolean v = v2(lastColor);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				lastColor = JOptionPane.showInputDialog("Red, Blue, Yellow,\nGreen, White, Magenta\nEnter the last COLOR\nof the sequence:").toUpperCase();
				v = v2(lastColor);
			}
			String[] condition = new String[2];
			String[] instruction = new String[3];
			switch(lastColor)
			{
				case "RED":
				case "R":
					condition[0] = "If the WORD GREEN is\nused at least 3 times.";
					condition[1] = "If the COLOR BLUE is\nused exactly once.";
					instruction[0] = "Press YES on the 3RD TIME\nGREEN is used as a WORD/COLOR.";
					instruction[1] = "Press NO when the WORD\nMAGENTA is shown.";
					instruction[2] = "Press YES the LAST TIME\nWHITE is used as a WORD/COLOR";
					souv = "LAST COLOR: RED";
					break;
				case "YELLOW":
				case "Y":
					condition[0] = "If the WORD BLUE is\nshown in GREEN COLOR.";
					condition[1] = "If the WORD WHITE is\nshown in RED/WHITE COLOR.";
					instruction[0] = "Press YES on the 1ST TIME\nGREEN is used as a COLOR.";
					instruction[1] = "Press YES on the 2ND TIME where\nthe COLOR doesn't match the WORD.";
					instruction[2] = "Press NO on the word's position\nequal to the number of MAGENTAS\nused as a WORD/COLOR";
					souv = "LAST COLOR: YELLOW";
					break;
				case "GREEN":
				case "G":
					condition[0] = "If a WORD occurs consecutively\nwith different COLORS.";
					condition[1] = "If MAGENTA is used as a word\nat least 3 times.";
					instruction[0] = "Press NO on the 5TH entry.";
					instruction[1] = "Press NO on the 1ST time YELLOW\nis used as a WORD/COLOR.";
					instruction[2] = "Press YES on any COLOR where the\nCOLOR matches the WORD.";
					souv = "LAST COLOR: GREEN";
					break;
				case "BLUE":
				case "B":
					condition[0] = "If the COLOR does not match\nthe WORD 3 times.";
					condition[1] = "If the WORD RED is shown in\nYELLOW COLOR OR the WORD YELLOW\nis shown in WHITE COLOR.";
					instruction[0] = "Press YES on the 1ST time in\nthe sequence where the COLOR\ndoes not match the WORD.";
					instruction[1] = "Press NO when the word WHITE\nis shown in RED COLOR.";
					instruction[2] = "Press YES the last time GREEN\nis used as the WORD/COLOR";
					souv = "LAST COLOR: BLUE";
					break;
				case "MAGENTA":
				case "M":
					condition[0] = "If a COLOR occurs consecutively\nwith different WORDS.";
					condition[1] = "If the number of times the WORD\nYELLOW appears greater than\nthe number of times the\nCOLOR BLUE occurs.";
					instruction[0] = "Press YES on the 3RD entry.";
					instruction[1] = "Press NO the last time the WORD\nYELLOW is in the sequence.";
					instruction[2] = "Press NO on the 1ST time where the\nCOLOR matches the word\nof the 7TH entry.";
					souv = "LAST COLOR: MAGENTA";
					break;
				default:
					condition[0] = "If the COLOR of the 3RD entry\nmatches the WORD of the\n4TH OR 5TH entry.";
					condition[1] = "If the WORD YELLOW is shown in\nRED COLOR.";
					instruction[0] = "Press NO the 1ST time that BLUE\nis used as the WORD/COLOR.";
					instruction[1] = "Press YES on the last time BLUE\nis used as the COLOR.";
					instruction[2] = "Press NO.";
					souv = "LAST COLOR: WHITE";
					break;
			}
			if(JOptionPane.showConfirmDialog(null, condition[0]) == 0)
				JOptionPane.showMessageDialog(null, instruction[0]);
			else if(JOptionPane.showConfirmDialog(null, condition[1]) == 0)
				JOptionPane.showMessageDialog(null, instruction[1]);
			else
				JOptionPane.showMessageDialog(null, instruction[2]);
		}
		else
		{
			String colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nW - White\nM - Magenta\nEnter the COLORS:").toUpperCase();
			boolean v = v1(colors);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nW - White\nM - Magenta\nEnter the COLORS:").toUpperCase();
				v = v1(colors);
			}
			String words = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nW - White\nM - Magenta\nEnter the WORDS:").toUpperCase();
			v = v1(words);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				words = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nW - White\nM - Magenta\nEnter the COLORS:").toUpperCase();
				v = v1(words);
			}
			String out;
			switch(colors.charAt(7))
			{
				case 'R':
					souv = "LAST COLOR: RED";
					if(getCount(words, 'G') >= 3)
					{
						out = "Press YES on word #";
						int counter1 = 0;
						for(int aa = 0; aa < 8; aa++)
						{
							if(words.charAt(aa) == 'G' || colors.charAt(aa) == 'G')
								counter1++;
							if(counter1 == 3)
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else if(getCount(colors, 'B') == 1)
					{
						out = "Press NO on word #";
						for(int aa = 0; aa < 8; aa++)
						{
							if(words.charAt(aa) == 'M')
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else
					{
						out = "Press YES on word #";
						for(int aa = 7; aa >= 0; aa--)
						{
							if(words.charAt(aa) == 'W' || colors.charAt(aa) == 'W')
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					break;
				case 'Y':
					souv = "LAST COLOR: YELLOW";
					if(wordInColor("B", "G", words, colors))
					{
						out = "Press YES on word #";
						for(int aa = 0; aa < 8; aa++)
						{
							if(colors.charAt(aa) == 'G')
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else if(wordInColor("W", "WR", words, colors))
					{
						out = "Press YES on word #";
						int counter2 = 0;
						for(int aa = 0; aa < 8; aa++)
						{
							if(words.charAt(aa) != colors.charAt(aa))
								counter2++;
							if(counter2 == 2)
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else
					{
						int counter3 = 0;
						for(int aa = 0; aa < 8; aa++)
						{
							if(words.charAt(aa) == 'M' || colors.charAt(aa) == 'M')
								counter3++;
						}
						out = "Press NO on word #" + counter3;
					}
					break;
				case 'G':
					souv = "LAST COLOR: GREEN";
					if(sameDiff(words, colors))
					{
						out = "Press NO on word #5";
					}
					else if(getCount(words, 'M') >= 3)
					{
						out = "Press NO on word #";
						for(int aa = 0; aa < 8; aa++)
						{
							if(words.charAt(aa) == 'Y' || colors.charAt(aa) == 'Y')
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else
					{
						out = "Press YES on word #";
						for(int aa = 0; aa < 8; aa++)
						{
							if(words.charAt(aa) == colors.charAt(aa))
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					break;
				case 'B':
					souv = "LAST COLOR: BLUE";
					boolean flag = false;
					int counter3 = 0;
					for(int aa = 0; aa < 8; aa++)
					{
						if(words.charAt(aa) != colors.charAt(aa))
							counter3++;
						if(counter3 == 3)
						{
							flag = true;
							break;
						}
					}
					if(flag)
					{
						out = "Press YES on word #";
						for(int aa = 0; aa < 8; aa++)
						{
							if(words.charAt(aa) != colors.charAt(aa))
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else if(wordInColor("R", "Y", words, colors) || wordInColor("Y", "W", words, colors))
					{
						out = "Press NO on word #";
						for(int aa = 0; aa < 8; aa++)
						{
							if(words.charAt(aa) == 'W' && colors.charAt(aa) == 'R')
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else
					{
						out = "Press YES on word #";
						for(int aa = 7; aa >= 0; aa--)
						{
							if(words.charAt(aa) == 'G' || colors.charAt(aa) == 'G')
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					break;
				case 'M':
					souv = "LAST COLOR: MAGENTA";
					if(sameDiff(colors, words))
					{
						out = "Press YES on word #3";
					}
					else if(getCount(words, 'Y') > getCount(colors, 'B'))
					{
						out = "Press NO on word #";
						for(int aa = 7; aa >= 0; aa--)
						{
							if(words.charAt(aa) == 'Y')
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else
					{
						out = "Press NO on word #";
						for(int aa = 0; aa < 8; aa++)
						{
							if(colors.charAt(aa) == words.charAt(6))
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					break;
				default:
					souv = "LAST COLOR: WHITE";
					if(colors.charAt(2) == words.charAt(3) || colors.charAt(2) == words.charAt(4))
					{
						out = "Press NO on word #";
						for(int aa = 0; aa < 8; aa++)
						{
							if(words.charAt(aa) == 'B' || colors.charAt(aa) == 'B')
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else if(wordInColor("Y", "R", words, colors))
					{
						out = "Press YES on word #";
						for(int aa = 7; aa >= 0; aa--)
						{
							if(colors.charAt(aa) == 'B')
							{
								out = out + "" + (aa + 1);
								break;
							}
						}
					}
					else
					{
						out = "Press NO";
					}
					break;
			}
			JOptionPane.showMessageDialog(null, out);
		}
		return souv;
	}
	private int getCount(String s, char c)
	{
		int count = 0;
		for(int aa = 0; aa < s.length(); aa++)
		{
			if(s.charAt(aa) == c)
				count++;
		}
		return count;
	}
	private boolean wordInColor(String w, String c, String wl, String cl)
	{
		for(int aa = 0; aa < 8; aa++)
		{
			if(w.indexOf(wl.charAt(aa)) >= 0 && c.indexOf(cl.charAt(aa)) >= 0)
				return true;
		}
		return false;
	}
	private boolean sameDiff(String same, String diff)
	{
		for(int aa = 0; aa < 7; aa++)
		{
			if(same.charAt(aa) == same.charAt(aa + 1) && diff.charAt(aa) != diff.charAt(aa + 1))
				return true;
		}
		return false;
	}
	private boolean v1(String i)
	{
		if(i.length() == 8)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("RBYGWM".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		switch(i)
		{
			case "RED":
			case "R":
			case "BLUE":
			case "B":
			case "YELLOW":
			case "Y":
			case "GREEN":
			case "G":
			case "WHITE":
			case "W":
			case "MAGENTA":
			case "M":
				return true;
			default:
				return false;
		}
	}
}
