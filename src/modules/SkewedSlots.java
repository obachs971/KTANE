package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class SkewedSlots 
{
	private final BombEdgework ew;
	public SkewedSlots(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		//Input the 3 numbers
		String input = JOptionPane.showInputDialog("Enter the 3 numbers").replace(" ", "").replace("/", "").replace(",", "");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR");
			input = JOptionPane.showInputDialog("Enter the 3 numbers").replace(" ", "").replace("/", "").replace(",", "");
			v = valid(input);
		}
		String souv = "NUMBERS: " + input.toUpperCase();
		int[] nums = {"0123456789".indexOf(input.charAt(0)), "0123456789".indexOf(input.charAt(1)), "0123456789".indexOf(input.charAt(2))};
		//All Slots
		for(int aa = 0; aa < 3; aa++)
		{
			if(nums[aa] == 2 || nums[aa] == 7)
				nums[aa] = (nums[aa] + 3) % 10;
			nums[aa] = nums[aa] + ew.numLit() - ew.numUnlit();
			if(nums[aa] % 3 == 0)
				nums[aa] += 4;
			else if(nums[aa] > 7)
				nums[aa] *= 2;
			else if(nums[aa] < 3 && nums[aa] % 2 == 0)
				nums[aa] /= 2;
			else if(ew.findPort("RCA") == 0 && ew.findPort("PS/2") == 0)
				nums[aa] = "0123456789".indexOf(input.charAt(aa)) + ew.BAT();
		}
		//1st Slot
		if(nums[0] % 2 == 0 && nums[0] > 5)
			nums[0] /= 2;
		else if(prime(nums[0]))
			nums[0] += ew.getSNDIG(ew.numSNDIGS() - 1);
		else if(ew.findPort("PARALLEL") > 0)
			nums[0] *= (-1);
		else if("13579".indexOf(input.charAt(1)) < 0)
			nums[0] -= 2;
		//2nd Slot
		if(!(ew.findUnlit("BOB")))
		{
			if(nums[1] == 0)
				nums[1] = "0123456789".indexOf(input.charAt(0));
			else if(fibonacci(nums[1]) != -1)
				nums[1] += fibonacci(nums[1]);
			else if(nums[1] >= 7)
				nums[1] += 4;
			else
				nums[1] *= 3;
		}
		//3rd Slot
		if(ew.findPort("SERIAL") > 0)
			nums[2] += ew.getLargestSNDIG();
		else if(input.charAt(2) != input.charAt(0) && input.charAt(2) != input.charAt(1))
		{
			if(nums[2] >= 5)
				nums[2] = getBinarySum("0123456789".indexOf(input.charAt(2)));
			else
				nums[2]++;
		}
		JOptionPane.showMessageDialog(null, "Submit these numbers: " + mod(nums[0], 10) + " " + mod(nums[1], 10) + " " + mod(nums[2], 10));
		return souv;
	}
	//Checks if the number is prime
	private boolean prime(int n)
	{
		if(n <= 1)
			return false;
		for(int aa = 2; aa < n; aa++)
		{
			if(n % aa == 0)
				return false;
		}
		return true;
	}
	//Checks if the number is part of the Fibonacci Sequence
	private int fibonacci(int n)
	{
		if(n == 1)
			return 1;
		ArrayList<Integer> fibo = new ArrayList<Integer>();
		fibo.add(1);
		fibo.add(1);
		while(fibo.get(fibo.size() - 1) < n)
			fibo.add(fibo.get(fibo.size() - 1) + fibo.get(fibo.size() - 2));
		System.out.println(fibo.get(fibo.size() - 1));
		if(fibo.contains(n))
			return (fibo.get(fibo.size() - 1) + fibo.get(fibo.size() - 2));
		return -1;
	}
	//Converts to Binary and sums up the 1s
	private int getBinarySum(int n)
	{
		int sum = 0;
		while(n > 0)
		{
			if(n % 2 == 1)
				sum++;
			n /= 2;
		}
		return sum;
	}
	//Modulos the number the correct way
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	//Validation for input
	private boolean valid(String i)
	{
		if(i.length() == 3)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("0123456789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
