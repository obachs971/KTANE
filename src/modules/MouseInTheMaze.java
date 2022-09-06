package modules;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.*;

import misc.PlayType;
public class MouseInTheMaze 
{
	private final PlayType playType;
	private final double resize;
	public MouseInTheMaze(PlayType pt, double r)
	{
		playType = pt;
		resize = r;
	}
	public String run()
	{
		String souv;
		String sphereColor = JOptionPane.showInputDialog("B - Blue\nY - Yellow\nG - Green\nW - White\nEnter the color of a sphere:");
		sphereColor = sphereColor.toLowerCase();
		String img = "null";
		switch(sphereColor)
		{
			case "b":
				img = "MITMBlue.jpg";
				break;
			case "g":
				img = "MITMGreen.jpg";
				break;
			case "w":
				img = "MITMWhite.jpg";
				break;
			case "y":
				img = "MITMYellow.jpg";
				break;
		}
		while(img.equals("null"))
		{
			JOptionPane.showMessageDialog(null, "ERROR");
			sphereColor = JOptionPane.showInputDialog("B - Blue\nY - Yellow\nG - Green\nW - White\nEnter the color of a sphere:");
			sphereColor = sphereColor.toLowerCase();
			switch(sphereColor)
			{
				case "b":
					img = "MITMBlue.jpg";
					break;
				case "g":
					img = "MITMGreen.jpg";
					break;
				case "w":
					img = "MITMWhite.jpg";
					break;
				case "y":
					img = "MITMYellow.jpg";
					break;
			}
		}
		ImageIcon i = new ImageIcon("img/" + img);
		Image image = i.getImage();
		image = image.getScaledInstance((int)(i.getIconWidth() / resize), (int)(i.getIconHeight() / resize), java.awt.Image.SCALE_SMOOTH);
		i = new ImageIcon(image);
		JLabel l = new JLabel();
		l.setIcon(i);
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		f.add(l, BorderLayout.CENTER);
		f.pack();
		if(playType == PlayType.Team)
		{
			String[][] table = {
					{"WHITE", "YELLOW", "GREEN", "BLUE"},
					{"YELLOW", "WHITE", "GREEN", "BLUE"},
					{"GREEN", "BLUE", "YELLOW", "WHITE"},
					{"WHITE", "BLUE", "YELLOW", "GREEN"},
					{"GREEN", "YELLOW", "BLUE", "WHITE"},
					{"GREEN", "WHITE", "BLUE", "YELLOW"}
			};
			String[] colorOrder = {"BLUE", "YELLOW", "WHITE", "GREEN"}; 
			f.setVisible(true);
			String mazeType = JOptionPane.showInputDialog("Enter the maze #:");
			f.setVisible(false);
			boolean v = v1(mazeType);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				f.setVisible(true);
				mazeType = JOptionPane.showInputDialog("Enter the maze #:");
				f.setVisible(false);
				v = v1(mazeType);
			}
			String torus = JOptionPane.showInputDialog("G - Green\nB - Blue\nY - Yellow\nW - White\nEnter the torus color:").toLowerCase();
			v = v2(torus);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				torus = JOptionPane.showInputDialog("G - Green\nB - Blue\nY - Yellow\nW - White\nEnter the torus color:").toLowerCase();
				v = v2(torus);
			}
			String sc = table["123456".indexOf(mazeType)]["bywg".indexOf(torus)];
			JOptionPane.showMessageDialog(null, "Submit on the " + sc + " sphere");
			souv = "TORUS: " + colorOrder["bywg".indexOf(torus)] + "\nGOAL: " + sc.toUpperCase();
		}
		else
		{
			f.setVisible(true);
			String input = JOptionPane.showInputDialog("N - North\nW - West\nE - East\nS - South\nEnter the maze # and direction you're facing (spaces):");
			f.setVisible(false);
			input = input.toLowerCase();
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR");
				f.setVisible(true);
				input = JOptionPane.showInputDialog("N - North\nW - West\nE - East\nS - South\nEnter the maze # and direction you're facing (spaces):");
				f.setVisible(false);
				input = input.toLowerCase();
				v = valid(input);
			}
			String[] spl = input.split(" ");
			switch(spl[0])
			{
				case "1":
					souv = maze1(sphereColor, spl[1]);
					break;
				case "2":
					souv = maze2(sphereColor, spl[1]);
					break;
				case "3":
					souv = maze3(sphereColor, spl[1]);
					break;
				case "4":
					souv = maze4(sphereColor, spl[1]);
					break;
				case "5":
					souv = maze5(sphereColor, spl[1]);
					break;
				default:
					souv = maze6(sphereColor, spl[1]);
					break;
			}
		}
		return souv;
	}
	private boolean valid(String i)
	{
		String[] spl = i.split(" ");
		if(spl.length == 2)
		{
			boolean b1 = false;
			boolean b2 = false;
			switch(spl[0])
			{
				case "1":
					b1 = true;
					break;
				case "2":
					b1 = true;
					break;
				case "3":
					b1 = true;
					break;
				case "4":
					b1 = true;
					break;
				case "5":
					b1 = true;
					break;
				case "6":
					b1 = true;
					break;
			}
			switch(spl[1])
			{
				case "n":
					b2 = true;
					break;
				case "e":
					b2 = true;
					break;
				case "s":
					b2 = true;
					break;
				case "w":
					b2 = true;
					break;
			}
			return (b1 && b2);
		}
		return false;
	}
	private String maze1(String sc, String d) //WORKING
	{
		String souv = "null";
		String move = "";
		String torus;
		boolean v;
		String[][] colors = 
			{
					{"g", "TORUS: GREEN\nGOAL: BLUE"},
					{"b", "TORUS: BLUE\nGOAL: WHITE"},
					{"w", "TORUS: WHITE\nGOAL: GREEN"},
					{"y", "TORUS: YELLOW\nGOAL: YELLOW"}
			};
		switch(sc)
		{
			case "g":
				switch(d)
				{
					case "n":
						move = "RR";
						break;
					case "w":
						move = "L";
						break;
					case "e":
						move = "R";
						break;
				}
				move = move + "FL\nFFFR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "FFLF\nLFFR\nFLF";
						break;
					case "b":
						move = "FFRF\nRFLF\nLFFR\nFLF";
						break;
					case "w":
						move = "RFFF\nRF";
						break;
					default:
						move = "FFRF\nLFLF\nFRFL\nF";
						break;
				}
				break;
			case "b":
				switch(d)
				{
					case "n":
						move = "RR";
						break;
					case "w":
						move = "L";
						break;
					case "e":
						move = "R";
						break;
				}
				move = move + "FR\nFLFF\nR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "RFFR\nFLF";
						break;
					case "b":
						move = "FFRF\nLFLF\nFRFL\nF";
						break;
					case "w":
						move = "FRFF\nLFFF\nRF";
						break;
					default:
						move = "FFLF\nLFFR\nFLF";
						break;
				}
				break;
			case "w":
				switch(d)
				{
					case "s":
						move = "RR";
						break;
					case "w":
						move = "R";
						break;
					case "e":
						move = "L";
						break;
				}
				move = move + "FR\nFLFF\nR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "FRFL\nFFLF\nFRFL\nF";
						break;
					case "b":
						move = "RFFR\nFLF";
						break;
					case "w":
						move = "FRFL\nFLFF\nLFFF\nRF";
						break;
					default:
						move = "FRFF\nLFFR\nFLF";
						break;
				}
				break;
			default:
				switch(d)
				{
					case "s":
						move = "R";
						break;
					case "n":
						move = "L";
						break;
					case "e":
						move = "RR";
						break;
				}
				move = move + "FR\nFLFF\nR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "FRFF\nLFFR\nFLF";
						break;
					case "b":
						move = "FFLF\nLFFR\nFLF";
						break;
					case "w":
						move = "FRFL\nFFLF\nFFRF";
						break;
					default:
						move = "RFFR\nFLF";
						break;
				}
				break;
		}
		JOptionPane.showMessageDialog(null, move);
		for(int aa = 0; aa < 4; aa++)
		{
			if(torus.equals(colors[aa][0]))
			{
				souv = colors[aa][1];
				break;
			}
		}
		return souv;
	}
	private String maze2(String sc, String d) //WORKING
	{
		String souv = "null";
		String move = "";
		String torus;
		boolean v;
		String[][] colors = 
			{
					{"g", "TORUS: GREEN\nGOAL: BLUE"},
					{"b", "TORUS: BLUE\nGOAL: YELLOW"},
					{"w", "TORUS: WHITE\nGOAL: GREEN"},
					{"y", "TORUS: YELLOW\nGOAL: WHITE"}
			};
		switch(sc)
		{
			case "g":
				switch(d)
				{
					case "n":
						move = "R";
						break;
					case "w":
						move = "RR";
						break;
					case "s":
						move = "L";
						break;
				}
				move = move + "FR\nFRFF\nLFL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "FFFL\nFRFF\nFLF";
						break;
					case "b":
						move = "LFRF\nFLFL\nFFFL\nFFFL\nFFFR\nFRFF\nFLFF\nFLFF\nLFF";
						break;
					case "w":
						move = "LFRF\nFLFL\nF";
						break;
					default:
						move = "FFFF\nFFRF\nFF";
						break;
				}
				break;
			case "b":
				switch(d)
				{
					case "n":
						move = "RR";
						break;
					case "w":
						move = "L";
						break;
					case "e":
						move = "R";
						break;
				}
				move = move + "FR\nFFFL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "LFFF\nLF";
						break;
					case "b":
						move = "FRFF\nFRFR\nFFLF\nLFFF\nLFFF\nLFFF\nRFRF\nFFLF\nFFLF\nFLFF";
						break;
					case "w":
						move = "FRFF\nFRFR\nFFLF\nLF";
						break;
					default:
						move = "FLFF\nFRFF\nF";
						break;
				}
				break;
			case "w":
				switch(d)
				{
					case "s":
						move = "RR";
						break;
					case "w":
						move = "R";
						break;
					case "e":
						move = "L";
						break;
				}
				move = move + "FF\nFL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "FFFR\nFRFF\nFLF";
						break;
					case "b":
						move = "FFFF\nFFRF\nRFFL\nFLFF\nFLFF\nFLFF\nFRFR\nFFFL\nFFFL\nFFLF\nF";
						break;
					case "w":
						move = "FFFF\nFFRF\nRFFL\nFLF";
						break;
					default:
						move = "LFFF";
						break;
				}
				break;
			default:
				switch(d)
				{
					case "n":
						move = "RR";
						break;
					case "w":
						move = "L";
						break;
					case "e":
						move = "R";
						break;
				}
				move = move + "FF\nRFFR\nFFFR\nFFFL\nFLFF\nFRFF\nFRFF\nFRFR\nFFLF\nL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "FFFL\nFRFF\nFLF";
						break;
					case "b":
						move = "LFRF\nFLFL\nFFFL\nFFFL\nFFFR\nFRFF\nFLFF\nFLFF\nLFF";
						break;
					case "w":
						move = "LFRF\nFLFL\nF";
						break;
					default:
						move = "FFFF\nFFRF\nFF";
						break;
				}
				break;
		}
		JOptionPane.showMessageDialog(null, move);
		for(int aa = 0; aa < 4; aa++)
		{
			if(torus.equals(colors[aa][0]))
			{
				souv = colors[aa][1];
				break;
			}
		}
		return souv;
	}
	private String maze3(String sc, String d) //WORKING
	{
		String souv = "null";
		String move = "";
		String torus;
		boolean v;
		String[][] colors = 
			{
					{"w", "TORUS: WHITE\nGOAL: YELLOW"},
					{"g", "TORUS: GREEN\nGOAL: WHITE"},
					{"b", "TORUS: BLUE\nGOAL: GREEN"},
					{"y", "TORUS: YELLOW\nGOAL: BLUE"}
			};
		switch(sc)
		{
			case "g":
				switch(d)
				{
					case "n":
						move = "RR";
						break;
					case "w":
						move = "L";
						break;
					case "e":
						move = "R";
						break;
				}
				move = move + "FR\nFFFF\nLFRF\nFLFF\nFLFF\nLFLF\nRFR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "w":
						move = "RFLF\nRFRF\nFRFF\nFFRF\nLF";
						break;
					case "g":
						move = "FFRF\nFFRF\nFFFL\nFLFF\nFFFF\nLFFR\nF";
						break;
					case "b":
						move = "RFLF\nRFRF\nFRFF\nFRFF\nLFRF\nFFFL\nF";
						break;
					default:
						move = "RFLF\nRFRF";
						break;
				}
				break;
			case "b":
				switch(d)
				{
					case "n":
						move = "R";
						break;
					case "w":
						move = "RR";
						break;
					case "s":
						move = "L";
						break;
				}
				move = move + "FL\nFLFR\nFR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "w":
						move = "RFLF\nRFRF\nFRFF\nFFRF\nLF";
						break;
					case "g":
						move = "FFRF\nFFRF\nFFFL\nFLFF\nFFFF\nLFFR\nF";
						break;
					case "b":
						move = "RFLF\nRFRF\nFRFF\nFRFF\nLFRF\nFFFL\nF";
						break;
					default:
						move = "RFLF\nRFRF";
						break;
				}
				break;
			case "w":
				switch(d)
				{
					case "s":
						move = "R";
						break;
					case "n":
						move = "L";
						break;
					case "e":
						move = "RR";
						break;
				}
				move = move + "FL\nFFRF\nR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "w":
						move = "LFFF\nFFRF\nFFFF\nFRFF\nLF";
						break;
					case "g":
						move = "RFLF\nFRF";
						break;
					case "b":
						move = "LFFF\nFFRF\nFFFF\nFRFR\nFLFF\nLFRFF\nFFLF";
						break;
					default:
						move = "LFFF\nFFRF\nFFFF\nFRFR\nFFFF\nLF";
						break;
				}
				break;
			default:
				switch(d)
				{
					case "n":
						move = "RR";
						break;
					case "w":
						move = "L";
						break;
					case "e":
						move = "R";
						break;
				}
				move = move + "FR\nFFLF\nFFFF\nLFFF\nFL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "w":
						move = "LFFF\nFRFF\nFFFR\nFFLF";
						break;
					case "g":
						move = "LFFF\nFLFL\nFFFF\nFFLF\nFRF";
						break;
					case "b":
						move = "LFFF\nFRFF\nFFFR\nFRFL\nFFLF\nRFFF\nFLF";
						break;
					default:
						move = "FFFL\nFFLF\nLFRF\nRF";
						break;
				}
				break;
		}
		JOptionPane.showMessageDialog(null, move);
		for(int aa = 0; aa < 4; aa++)
		{
			if(torus.equals(colors[aa][0]))
			{
				souv = colors[aa][1];
				break;
			}
		}
		return souv;
	}
	private String maze4(String sc, String d) //WORKING
	{
		String souv = "null";
		String[][] colors = 
			{
					{"w", "TORUS: WHITE\nGOAL: YELLOW"},
					{"g", "TORUS: GREEN\nGOAL: GREEN"},
					{"b", "TORUS: BLUE\nGOAL: WHITE"},
					{"y", "TORUS: YELLOW\nGOAL: BLUE"}
			};
		String move = "";
		String torus;
		boolean v;
		switch(sc)
		{
			case "g":
				switch(d)
				{
					case "n":
						move = "R";
						break;
					case "w":
						move = "RR";
						break;
					case "s":
						move = "L";
						break;
				}
				move = move + "FL\nFLFR\nFFFL\nFLFF\nR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "w":
						move = "RFFF\nLFRF\nRFFL\nFRFF\nRFRF\nLFRF";
						break;
					case "g":
						move = "RFFR\nFRFF\nFLFR\nFRF";
						break;
					case "b":
						move = "RFFF\nLFRF\nLFLF\nRFF";
						break;
					default:
						move = "FLFR\nFFRF\nLFLF\nF";
						break;
				}
				break;
			case "b":
				switch(d)
				{
					case "e":
						move = "L";
						break;
					case "w":
						move = "R";
						break;
					case "s":
						move = "RR";
						break;
				}
				move = move + "FF\nR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "w":
						move = "FRFL\nFFLF\nRFLF\nFFLF\nRFRF\nFLFR\nFFRF\nRFLF\nRF";
						break;
					case "g":
						move = "FRFL\nFFLF\nRFLF\nFRFR\nFFFL\nFRFR\nF";
						break;
					case "b":
						move = "FFLF\nLFFR\nFRFF\nLFLF\nF";
						break;
					default:
						move = "RFF";
						break;
				}
				break;
			case "w":
				switch(d)
				{
					case "s":
						move = "L";
						break;
					case "n":
						move = "R";
						break;
					case "w":
						move = "RR";
						break;
				}
				move = move + "FF\nRFRF\nFLFL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "w":
						move = "LFRF\nFLFF\nRFFF\nLFRF\nFRFR\nFLFR\nF";
						break;
					case "g":
						move = "FFRF\nRFLF\nLFFL\nFRFL\nFFRF\nRFFF\nLFRF\nRF";
						break;
					case "b":
						move = "LFRF\nFLFL\nFF";
						break;
					default:
						move = "FFRF\nRFFL\nFF";
						break;
				}
				break;
			default:
				switch(d)
				{
					case "n":
						move = "R";
						break;
					case "w":
						move = "RR";
						break;
					case "s":
						move = "L";
						break;
				}
				move = move + "FF\nRFRF\nLFLF\nRFRF\nFRFF\nLFLF\nFR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "w":
						move = "RFFF\nLFRF\nRFFL\nFRFF\nRFRF\nLFRF";
						break;
					case "g":
						move = "RFFR\nFRFF\nFLFR\nFRF";
						break;
					case "b":
						move = "RFFF\nLFRF\nLFLF\nRFF";
						break;
					default:
						move = "FLFR\nFFRF\nLFLF\nF";
						break;
				}
				break;
		}
		JOptionPane.showMessageDialog(null, move);
		for(int aa = 0; aa < 4; aa++)
		{
			if(torus.equals(colors[aa][0]))
			{
				souv = colors[aa][1];
				break;
			}
		}
		return souv;
	}
	private String maze5(String sc, String d) //WORKING
	{
		String souv = "null";
		String move = "";
		String torus;
		boolean v;
		String[][] colors = 
			{
					{"w", "TORUS: WHITE\nGOAL: BLUE"},
					{"g", "TORUS: GREEN\nGOAL: WHITE"},
					{"b", "TORUS: BLUE\nGOAL: GREEN"},
					{"y", "TORUS: YELLOW\nGOAL: YELLOW"}
			};
		switch(sc)
		{
			case "g":
				switch(d)
				{
					case "n":
						move = "L";
						break;
					case "e":
						move = "RR";
						break;
					case "s":
						move = "R";
						break;
				}
				move = move + "FR\nFRFF\nFL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "y":
						move = "FFFL\nFFFF\nRFFF\nRFFF\nRFRF\nFLFL\nF";
						break;
					case "b":
						move = "LFFF\nLFLF";
						break;
					case "g":
						move = "FFFL\nFFFF\nRFFF\nRFFF\nRFFL\nFLFF\nRFFF\nRFRF\nFLFL\nFF";
						break;
					default:
						move = "LFFF\nLFLF\nFFRF\nFLFL\nFRFR\nFLFL\nFRFF\nLFLF\nF";
						break;
				}
				break;
			case "b":
				switch(d)
				{
					case "e":
						move = "L";
						break;
					case "w":
						move = "R";
						break;
					case "s":
						move = "RR";
						break;
				}
				move = move + "FR\nFLFL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "y":
						move = "FFFR\nFFFF\nRFFL\nFLFF\nFLFF\nRFRF\nLFFL\nFLF";
						break;
					case "b":
						move = "LFRF\nLFLF\nFRFR\nFFLF\nRFRF\nLFLF\nRFRF\nFLFF";
						break;
					case "g":
						move = "FFFR\nFFFR\nFF";
						break;
					default:
						move = "LFRF\nLF";
						break;
				}
				break;
			case "w":
				switch(d)
				{
					case "s":
						move = "R";
						break;
					case "n":
						move = "L";
						break;
					case "e":
						move = "RR";
						break;
				}
				move = move + "FF\nL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "y":
						move = "RRFR\nFFLF\nLFFF\nLFFR\nFRFL\nFFLF\nLF";
						break;
					case "b":
						move = "FFFL\nFFFR\nFRFL\nFLFF\nRFRF\nFLFR\nFRFL\nFLFR\nFRFF\nLFF";
						break;
					case "g":
						move = "LFF";
						break;
					default:
						move = "FFFL\nFFFR\nFRFL\nF";
						break;
				}
				break;
			default:
				switch(d)
				{
					case "n":
						move = "L";
						break;
					case "e":
						move = "RR";
						break;
					case "s":
						move = "R";
						break;
				}
				move = move + "FR\nFRFF\nLFLF\nFFLF\nFFLF\nFFFR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "y":
						move = "RFFF\nFRFF\nFRFF\nFRFR\nFFLF\nLF";
						break;
					case "b":
						move = "FFFR\nFFFL\nFLF";
						break;
					case "g":
						move = "RFFF\nFRFF\nFRFF\nFRFF\nLFLF\nFRFF\nFRFR\nFFLF\nLFF";
						break;
					default:
						move = "FFFR\nFFFL\nFLFF\nFRFF\nLFLF\nRFRF\nLFLF\nRFFL\nFLFF";
						break;
				}
				break;
		}
		JOptionPane.showMessageDialog(null, move);
		for(int aa = 0; aa < 4; aa++)
		{
			if(torus.equals(colors[aa][0]))
			{
				souv = colors[aa][1];
				break;
			}
		}
		return souv;
	}
	private String maze6(String sc, String d) //WORKING
	{
		String souv = "null";
		String move = "";
		String torus;
		boolean v;
		String[][] colors = 
			{
					{"w", "TORUS: WHITE\nGOAL: BLUE"},
					{"g", "TORUS: GREEN\nGOAL: YELLOW"},
					{"b", "TORUS: BLUE\nGOAL: GREEN"},
					{"y", "TORUS: YELLOW\nGOAL: WHITE"}
			};
		switch(sc)
		{
			case "g":
				switch(d)
				{
					case "n":
						move = "L";
						break;
					case "e":
						move = "RR";
						break;
					case "s":
						move = "R";
						break;
				}
				move = move + "FL\nFLFR\nFLFF\nFLFL\nFFRF\nRFFL\nFLFR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "FFFL\nFRFR\nFFFF";
						break;
					case "b":
						move = "RFRF\nRFFL\nFLFF\nRFRF\nFFRF\nLFRF\nRF";
						break;
					case "w":
						move = "FFFL\nFRFR\nFLFR\nFLFL\nFFLF\nRFLF";
						break;
					default:
						move = "RFRF\nLFRF\nFLFF\nFLFL\nFFRF";
						break;
				}
				break;
			case "b":
				switch(d)
				{
					case "e":
						move = "L";
						break;
					case "w":
						move = "R";
						break;
					case "s":
						move = "RR";
						break;
				}
				move = move + "FR\nFLFR\nFFRF\nRFLF\nRFLF\nLFR";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "RFRF\nRFFF\nF";
						break;
					case "b":
						move = "FFFL\nFRFR\nFFLF\nLFFR\nFRFF\nFRFL\nFRFR\nF";
						break;
					case "w":
						move = "RFRF\nRFLF\nRFLF\nLFFL\nFRFL\nF";
						break;
					default:
						move = "FFFL\nFRFL\nFRFF\nLFFF\nLFLF\nFRF";
						break;
				}
				break;
			case "w":
				switch(d)
				{
					case "w":
						move = "R";
						break;
					case "e":
						move = "L";
						break;
					case "s":
						move = "RR";
						break;
				}
				move = move + "FL\nFRFL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "FRFF\nRFLF\nRF";
						break;
					case "b":
						move = "LFLF\nRFFL\nFFRF\nRFFF\nRFFL\nFFFL\nFLFF\nRFRF\nFFRF\nLFRF\nRF";
						break;
					case "w":
						move = "FRFF\nRFLF\nLFFR\nFRFL\nFLFF\nLFRF\nLF";
						break;
					default:
						move = "LFLF\nRF";
						break;
				}
				break;
			default:
				switch(d)
				{
					case "n":
						move = "L";
						break;
					case "e":
						move = "RR";
						break;
					case "s":
						move = "R";
						break;
				}
				move = move + "FL\nFRFL";
				torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
				v = v2(torus);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					torus = JOptionPane.showInputDialog("Follow these directions:\n" + move + "\nThen enter the torus color:").toLowerCase();
					v = v2(torus);
				}
				switch(torus)
				{
					case "g":
						move = "LFLF\nRF";
						break;
					case "b":
						move = "LFLF\nLFFF\nLFLF\nRFFF\nLFRF\nRFFL\nFLFF\nRFRF\nFFRF\nLFRF\nRF";
						break;
					case "w":
						move = "LFLF\nLFFR\nFRFL\nFLFF\nLFRF\nLF";
						break;
					default:
						move = "FFLF\nRFLF\nRF";
						break;
				}
				break;
		}
		JOptionPane.showMessageDialog(null, move);
		for(int aa = 0; aa < 4; aa++)
		{
			if(torus.equals(colors[aa][0]))
			{
				souv = colors[aa][1];
				break;
			}
		}
		return souv;
	}
	private boolean v1(String i)
	{
		switch(i)
		{
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
				return true;
			default:
				return false;
		}
	}
	private boolean v2(String i)
	{
		switch(i)
		{
			case "w":
			case "b":
			case "g":
			case "y":
				return true;
			default:
				return false;
		}
	}
}
