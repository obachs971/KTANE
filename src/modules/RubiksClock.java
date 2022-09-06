package modules;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class RubiksClock 
{
	private final int TL = 0, TR = 1, BL = 2, BR = 3;
	private final int[][][] instructions =
		{
				{
					{TL, BR, BL, 6},{TL, TR, BR, 6},
					{BL, BR, TL, -2},{TL, TR, BL, 6},
					{TR, BL, TR, 1},{TL, TR, TL, 6}
				},
				{
					{TL, BR, BL, -4},{TL, BR, BR, 1},
					{TR, BL, TR, 4},{TR, BL, TR, 3},
					{TR, BR, BR, -4},{TL, BL, TL, -3}
				},
				{
					{BL, BR, TL, 4},{TL, BL, BR, 1},
					{TL, BL, BL, -1},{TL, BL, BR, -5},
					{TL, TR, TR, 5},{BL, BR, BR, -4}
				},
				{
					{TL, BL, TR, 5},{TL, TR, TL, -3},
					{TR, BR, TL, 2},{TR, BR, BL, 3},
					{TL, BR, BL, 2},{TL, BL, BR, -5}
				},
				{
					{BL, BR, TR, 4},{BL, BR, BR, 2},
					{TR, BL, TR, -1},{TL, BR, TL, -5},
					{BL, BR, BL, -3},{TR, BL, BR, 6}
				},
				{
					{TR, BL, BL, 3},{TR, BR, BL, 5},
					{TR, BR, TR, -2},{TL, BR, TL, -2},
					{TR, BR, TR, 6},{TL, TR, TL, -1}
				}
		};
	private int[] leftRow = new int[2];
	private int[] rightRow = new int[2];
	private int[] amounts = new int[6];
	public RubiksClock(BombEdgework e)
	{
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		amounts[0] = e.BA() + 1;
		amounts[1] = e.numLit() + 1;
		amounts[2] = e.BAT() + 1;
		amounts[3] = e.numUnlit() + 1;
		amounts[4] = e.BD() + 1;
		amounts[5] = e.numInd() + 1;
		leftRow[0] = alpha.indexOf(e.getSNCHAR(0)) / 3;
		rightRow[0] = mod(alpha.indexOf(e.getSNCHAR(1)) / 3, 6);
		leftRow[1] = alpha.indexOf(e.getSNCHAR(2)) / 3;
		rightRow[1] = mod(alpha.indexOf(e.getSNCHAR(3)) / 3, 6);
	}
	public void run()
	{
		String[] pins = {"TL", "TR", "BL", "BR"};
		for(int stage = 0; stage < 4; stage++)
		{
			String input = JOptionPane.showInputDialog("TL, TM, TR,\nML, MM, MR,\nBL, BM, BR\nEnter the lit up\nclock and pin:").toUpperCase().replace(" ", "");
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("TL, TM, TR,\nML, MM, MR,\nBL, BM, BR\nEnter the lit up\nclock and pin:").toUpperCase().replace(" ", "");
				v = valid(input);
			}
			if(input.length() == 0)
				break;
			int[] rc = {"TMB".indexOf(input.charAt(0)) * 2, "LMR".indexOf(input.charAt(1)) * 2};
			switch(input.substring(2))
			{
				case "TR":
					rc[1]++;
					break;
				case "BL":
					rc[0]++;
					break;
				case "BR":
					rc[0]++;
					rc[1]++;
					break;
			}
			//System.out.println(leftRow[0] + " " + rightRow[0]);
			//System.out.println(leftRow[1] + " " + rightRow[1]);
			//System.out.println(rc[0] + " " + rc[1]);
			int[] mods = getModifications(rc[0], rc[1], leftRow, new int[] {amounts[rightRow[0]], amounts[rightRow[1]]});
			int[] instruction = instructions[mods[0]][mods[1]];
			if(mods[3] == 1)
				instruction[3] *= -1;
			instruction[3] += mods[2];
			if(mods[4] == 1)
			{
				ArrayList<String> list = new ArrayList<String>(Arrays.asList(new String[] {"0", "1", "2", "3"}));
				for(int i = 0; i < 2; i++)
					list.remove(instruction[i] + "");
				for(int i = 0; i < 2; i++)
					instruction[i] = Integer.parseInt(list.get(i));
			}
			JOptionPane.showMessageDialog(null, "Press these pins: " + pins[instruction[0]] + " " + pins[instruction[1]] + "\nRotate " + pins[instruction[2]] + " Gear " + instruction[3] + "\nTurn Over");
			for(int i = 0; i < 2; i++)
			{
				leftRow[i] = mod(leftRow[i] + 1, 12);
				rightRow[i] = mod(rightRow[i] + 1, 6);
			}
		}	
		
	}
	private int[] getModifications(int row, int col, int[] actions, int[] amounts)
	{
		//Row, Col, Offset, Change Direction, Change Other Pins
		int[] mods = {row, col, 0, 0, 0};
		for(int i = 0; i < actions.length; i++)
		{
			switch(actions[i])
			{
				case 0:
					mods[1] = mod(mods[1] + (amounts[i] * 2), 6);
					break;
				case 1:
					mods[0] = mod(mods[0] + amounts[i], 6);
					break;
				case 2:
					mods[4] = (amounts[i] + 1) % 2;
					break;
				case 3:
					mods[0] = mod(mods[0] - (amounts[i] * 2), 6);
					break;
				case 4:
					mods[1] = mod(mods[1] + amounts[i], 6);
					break;
				case 5:
					mods[3] = amounts[i] % 2; 
					break;
				case 6:
					mods[1] = mod(mods[1] - (amounts[i] * 2), 6);
					break;
				case 7:
					mods[0] = mod(mods[0] - amounts[i], 6);
					break;
				case 8:
					mods[2] = mods[2] + amounts[i];
					break;
				case 9:
					mods[0] = mod(mods[0] + (amounts[i] * 2), 6);
					break;
				case 10:
					mods[1] = mod(mods[1] - amounts[i], 6);
					break;
				default:
					mods[2] = mods[2] - amounts[i];
					break;
			}
		}
		return mods;
	}
	private boolean valid(String i)
	{
		if(i.length() == 0)
			return true;
		if(i.length() == 4)
		{
			switch(i.substring(0, 2))
			{
				case "TL": case "TM": case "TR":
				case "ML": case "MM": case "MR":
				case "BL": case "BM": case "BR":
					break;
				default:
					return false;
			}
			switch(i.substring(2))
			{
				case "TL": case "TR":
				case "BL": case "BR":
					return true;
				default:
					return false;
			}
		}
		return false;
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
}
