package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import misc.MTK;

public class PolyhedralMaze 
{
	private final String[][] TDI =
		{
				{"00", "02", "01", "34", "05", "26", "08", "30"},
				{"01", "13", "--", "04", "--", "00", "--", "--"},
				{"02", "00", "--", "--", "--", "--", "--", "--"},
				{"03", "31", "--", "--", "39", "--", "--", "--"},
				{"04", "01", "--", "--", "39", "--", "--", "--"},
				{"05", "31", "--", "--", "00", "--", "--", "--"},
				{"06", "24", "--", "30", "--", "--", "--", "--"},
				{"07", "35", "--", "29", "--", "40", "--", "--"},
				{"08", "00", "--", "--", "35", "--", "--", "--"},
				{"09", "10", "35", "--", "26", "--", "31", "--"},
				{"10", "29", "--", "35", "09", "--", "--", "--"},
				{"11", "31", "--", "25", "--", "--", "--", "--"},
				{"12", "13", "--", "--", "--", "--", "--", "--"},
				{"13", "01", "38", "12", "14", "36", "16", "28"},
				{"14", "18", "--", "15", "--", "13", "--", "--"},
				{"15", "14", "27", "23", "33", "17", "16", "36"},
				{"16", "13", "--", "15", "--", "39", "--", "--"},
				{"17", "15", "--", "--", "--", "--", "--", "--"},
				{"18", "37", "--", "--", "14", "--", "--", "--"},
				{"19", "20", "--", "--", "29", "--", "--", "--"},
				{"20", "19", "--", "32", "--", "24", "--", "--"},
				{"21", "23", "22", "41", "--", "25", "--", "--"},
				{"22", "21", "--", "37", "--", "29", "--", "--"},
				{"23", "21", "--", "15", "--", "--", "--", "--"},
				{"24", "06", "20", "--", "--"},
				{"25", "21", "11", "--", "--"},
				{"26", "00", "--", "09", "--"},
				{"27", "15", "--", "--", "--"},
				{"28", "13", "--", "--", "--"},
				{"29", "19", "07", "10", "22"},
				{"30", "00", "--", "06", "--"},
				{"31", "05", "03", "11", "09"},
				{"32", "20", "--", "--", "--"},
				{"33", "15", "--", "--", "--"},
				{"34", "00", "--", "--", "--"},
				{"35", "07", "08", "09", "10"},
				{"36", "13", "--", "15", "--"},
				{"37", "18", "--", "22", "--"},
				{"38", "13", "--", "--", "--"},
				{"39", "03", "04", "16", "--"},
				{"40", "07", "--", "--", "--"},
				{"41", "21", "--", "--", "--"},
				{"--"}
		};
	private final String[][] CD =
		{
				{"00", "02", "01", "38", "26", "08", "34"},
				{"01", "04", "--", "00", "--", "--", "--"},
				{"02", "00", "--", "--", "--", "--", "--"},
				{"03", "31", "--", "--", "35", "--", "--"},
				{"04", "01", "--", "31", "--", "05", "--"},
				{"05", "04", "--", "35", "--", "--", "--"},
				{"06", "24", "--", "08", "--", "--", "--"},
				{"07", "08", "--", "--", "29", "--", "--"},
				{"08", "39", "07", "06", "--", "00", "--"},
				{"09", "26", "--", "35", "--", "--", "--"},
				{"10", "29", "--", "--", "--", "--", "--"},
				{"11", "35", "--", "--", "--", "--", "--"},
				{"12", "13", "--", "--", "24", "--", "--"},
				{"13", "40", "16", "28", "30", "12", "14"},
				{"14", "13", "--", "36", "--", "--", "--"},
				{"15", "23", "37", "17", "16", "40", "27"},
				{"16", "15", "--", "31", "--", "13", "--"},
				{"17", "31", "--", "15", "--", "--", "--"},
				{"18", "20", "--", "41", "27", "--", "--"},
				{"19", "29", "--", "41", "--", "--", "--"},
				{"20", "18", "--", "24", "--", "--", "--"},
				{"21", "25", "--", "--", "22", "--", "--"},
				{"22", "29", "--", "21", "--", "--", "--"},
				{"23", "15", "--", "--", "--", "--", "--"},
				{"24", "06", "20", "--", "12", "--", "--"},
				{"25", "37", "21", "--", "35", "--", "--"},
				{"26", "00", "--", "--", "09", "--", "--"},
				{"27", "15", "--", "--", "18", "--", "--"},
				{"28", "13", "--", "31", "--", "--", "--"},
				{"29", "19", "32", "07", "10", "33", "22"},
				{"30", "13", "--", "--", "--", "--"},
				{"31", "04", "28", "16", "17", "03"},
				{"32", "29", "--", "--", "--", "--"},
				{"33", "29", "--", "--", "--", "--"},
				{"34", "00", "--", "--", "--", "--"},
				{"35", "09", "05", "03", "25", "11"},
				{"36", "14", "--", "--", "--", "--"},
				{"37", "25", "--", "15", "--", "--"},
				{"38", "00", "--", "--", "--", "--"},
				{"39", "08", "--", "--", "--", "--"},
				{"40", "15", "--", "13", "--", "--"},
				{"41", "18", "19", "--", "--", "--"},
				{"--"}
		};
	private final String[][] CI =
		{
				{"00", "01", "32", "08", "19", "30", "20"},
				{"01", "00", "--", "--", "--", "--", "--"},
				{"02", "44", "--", "13", "--", "--", "--"},
				{"03", "31", "--", "--", "--", "--", "--"},
				{"04", "23", "43", "--", "05", "35", "--"},
				{"05", "29", "--", "35", "04", "--", "--"},
				{"06", "15", "--", "--", "--", "--", "--"},
				{"07", "48", "--", "15", "--", "--", "--"},
				{"08", "00", "--", "--", "24", "--", "--"},
				{"09", "21", "15", "--", "--", "--", "--"},
				{"10", "31", "--", "21", "47", "--", "--"},
				{"11", "31", "--", "--", "33", "--", "--"},
				{"12", "38", "23", "13", "--", "--", "--"},
				{"13", "14", "34", "02", "20", "42", "12"},
				{"14", "13", "--", "35", "--", "--", "--"},
				{"15", "06", "36", "07", "16", "37", "09"},
				{"16", "15", "--", "49", "--", "--", "--"},
				{"17", "24", "--", "--", "--", "--", "--"},
				{"18", "22", "--", "46", "--", "--", "--"},
				{"19", "00", "--", "46", "--", "--", "--"},
				{"20", "13", "00", "--", "--", "--", "--"},
				{"21", "09", "--", "10", "22", "--", "--"},
				{"22", "18", "40", "21", "23", "38", "--"},
				{"23", "04", "12", "--", "22", "--", "--"},
				{"24", "48", "08", "--", "41", "17", "--"},
				{"25", "29", "--", "41", "--", "--", "--"},
				{"26", "44", "--", "29", "--", "--", "--"},
				{"27", "49", "--", "28", "--", "--", "--"},
				{"28", "33", "27", "29", "--", "--", "--"},
				{"29", "05", "45", "28", "25", "39", "26"},
				{"30", "00", "--", "--"},
				{"31", "03", "10", "11"},
				{"32", "00", "--", "--"},
				{"33", "28", "11", "--"},
				{"34", "13", "--", "--"},
				{"35", "14", "04", "05"},
				{"36", "15", "--", "--"},
				{"37", "15", "--", "--"},
				{"38", "12", "22", "--"},
				{"39", "29", "--", "--"},
				{"40", "22", "--", "--"},
				{"41", "24", "25", "--"},
				{"42", "13", "--", "--"},
				{"43", "04", "--", "--"},
				{"44", "02", "26", "--"},
				{"45", "29", "--", "--"},
				{"46", "18", "19", "--"},
				{"47", "10", "--", "--"},
				{"48", "24", "07", "--"},
				{"49", "27", "16", "--"},
				{"--"},
		};
	private final String[][] DH =
		{
				{"00", "52", "14", "04", "01"},
				{"01", "26", "51", "00", "--"},
				{"02", "03", "--", "--", "--"},
				{"03", "02", "04", "43", "--"},
				{"04", "42", "03", "00", "--"},
				{"05", "09", "--", "--", "--"},
				{"06", "07", "--", "46", "--"},
				{"07", "29", "--", "06", "--"},
				{"08", "28", "--", "09", "--"},
				{"09", "08", "05", "15", "--"},
				{"10", "11", "--", "--", "--"},
				{"11", "10", "12", "31", "--"},
				{"12", "11", "13", "--", "--"},
				{"13", "38", "12", "14", "53"},
				{"14", "13", "--", "00", "--"},
				{"15", "19", "16", "57", "09"},
				{"16", "15", "--", "--", "--"},
				{"17", "35", "--", "18", "--"},
				{"18", "17", "19", "48", "--"},
				{"19", "18", "15", "--", "--"},
				{"20", "29", "--", "21", "--"},
				{"21", "20", "22", "--", "--"},
				{"22", "44", "--", "21", "--"},
				{"23", "43", "--", "--", "--"},
				{"24", "25", "--", "--", "--"},
				{"25", "24", "29", "26", "--"},
				{"26", "01", "25", "27", "--"},
				{"27", "59", "--", "26", "--"},
				{"28", "29", "08", "--", "--"},
				{"29", "28", "25", "20", "07"},
				{"30", "31", "--", "--", "--"},
				{"31", "11", "30", "32", "41"},
				{"32", "31", "--", "49", "--"},
				{"33", "34", "--", "--", "--"},
				{"34", "35", "--", "33", "--"},
				{"35", "17", "34", "39", "36"},
				{"36", "35", "--", "56", "--"},
				{"37", "38", "54", "55", "--"},
				{"38", "37", "--", "13", "--"},
				{"39", "35", "--", "--", "--"},
				{"40", "44", "41", "--", "--"},
				{"41", "31", "40", "42", "--"},
				{"42", "04", "--", "41", "--"},
				{"43", "23", "03", "--", "--"},
				{"44", "22", "--", "40", "--"},
				{"45", "49", "--", "--", "--"},
				{"46", "47", "06", "--", "--"},
				{"47", "46", "48", "--", "--"},
				{"48", "18", "47", "49", "--"},
				{"49", "32", "48", "45", "--"},
				{"50", "59", "--", "--", "--"},
				{"51", "01", "--", "--", "--"},
				{"52", "53", "--", "00", "--"},
				{"53", "13", "52", "--", "--"},
				{"54", "37", "--", "--", "--"},
				{"55", "59", "56", "37", "--"},
				{"56", "36", "55", "--", "--"},
				{"57", "15", "--", "58", "--"},
				{"58", "57", "59", "--", "--"},
				{"59", "55", "50", "27", "58"},
				{"--"}
		};
	private final String[][] DD =
		{
				{"00", "07", "01", "23"},
				{"01", "00", "02", "38"},
				{"02", "01", "03", "--"},
				{"03", "24", "02", "--"},
				{"04", "31", "--", "--"},
				{"05", "06", "42", "--"},
				{"06", "05", "07", "--"},
				{"07", "16", "06", "00"},
				{"08", "09", "19", "15"},
				{"09", "10", "46", "08"},
				{"10", "45", "09", "--"},
				{"11", "12", "28", "--"},
				{"12", "11", "13", "--"},
				{"13", "12", "14", "34"},
				{"14", "15", "33", "13"},
				{"15", "08", "20", "14"},
				{"16", "17", "07", "--"},
				{"17", "16", "18", "--"},
				{"18", "47", "17", "19"},
				{"19", "08", "18", "--"},
				{"20", "15", "--", "--"},
				{"21", "22", "32", "--"},
				{"22", "39", "21", "--"},
				{"23", "00", "--", "--"},
				{"24", "31", "25", "03"},
				{"25", "24", "26", "--"},
				{"26", "25", "27", "35"},
				{"27", "26", "28", "--"},
				{"28", "29", "11", "27"},
				{"29", "44", "28", "30"},
				{"30", "29", "31", "--"},
				{"31", "30", "24", "04"},
				{"32", "33", "21", "--"},
				{"33", "14", "32", "--"},
				{"34", "35", "13", "--"},
				{"35", "34", "36", "26"},
				{"36", "35", "37", "--"},
				{"37", "36", "38", "--"},
				{"38", "01", "37", "39"},
				{"39", "22", "38", "--"},
				{"40", "47", "41", "--"},
				{"41", "40", "42", "--"},
				{"42", "05", "41", "43"},
				{"43", "42", "44", "--"},
				{"44", "29", "43", "--"},
				{"45", "10", "--", "--"},
				{"46", "09", "--", "--"},
				{"47", "40", "18", "--"},
				{"--"}
		};
	private final String[][] JR =
		{
				{"00", "03", "01", "36", "26"},
				{"01", "38", "00", "--", "--"},
				{"02", "30", "44", "--", "--"},
				{"03", "00", "24", "--", "--"},
				{"04", "33", "29", "--", "--"},
				{"05", "06", "45", "35", "--"},
				{"06", "05", "07", "--", "--"},
				{"07", "27", "41", "06", "--"},
				{"08", "32", "24", "--", "--"},
				{"09", "31", "--", "10", "--"},
				{"10", "09", "11", "29", "--"},
				{"11", "25", "--", "10", "--"},
				{"12", "37", "15", "13", "--"},
				{"13", "12", "14", "47", "39"},
				{"14", "15", "43", "46", "13"},
				{"15", "14", "12", "38", "42"},
				{"16", "17", "--", "--", "--"},
				{"17", "27", "16", "--", "--"},
				{"18", "19", "37", "--", "--"},
				{"19", "26", "--", "18", "--"},
				{"20", "21", "44", "--", "--"},
				{"21", "20", "22", "--", "--"},
				{"22", "21", "23", "35", "--"},
				{"23", "22", "--", "31", "--"},
				{"24", "25", "08", "03", "--"},
				{"25", "11", "24", "--", "--"},
				{"26", "00", "19", "--", "--"},
				{"27", "17", "07", "29", "--"},
				{"28", "29", "--", "--", "--"},
				{"29", "10", "28", "27", "04"},
				{"30", "02", "--", "31", "--"},
				{"31", "23", "30", "32", "09"},
				{"32", "08", "31", "--", "--"},
				{"33", "04", "35", "--", "--"},
				{"34", "35", "--", "--", "--"},
				{"35", "05", "22", "34", "33"},
				{"36", "00", "--", "--", "--"},
				{"37", "12", "18", "--", "--"},
				{"38", "01", "15", "--", "--"},
				{"39", "13", "--", "41", "--"},
				{"40", "41", "--", "--", "--"},
				{"41", "40", "39", "07", "--"},
				{"42", "44", "43", "15", "--"},
				{"43", "14", "42", "--", "--"},
				{"44", "42", "02", "20", "--"},
				{"45", "05", "--", "--", "--"},
				{"46", "14", "--", "--", "--"},
				{"47", "13", "--", "--", "--"},
				{"--"}
		};
	private final String[][] JSC =
		{
				{"00", "03", "01", "56", "26"},
				{"01", "00", "02", "50", "--"},
				{"02", "44", "01", "--", "--"},
				{"03", "00", "--", "--", "--"},
				{"04", "35", "--", "--", "--"},
				{"05", "06", "--", "47", "--"},
				{"06", "41", "05", "07", "--"},
				{"07", "29", "06", "--", "--"},
				{"08", "24", "11", "--", "--"},
				{"09", "31", "--", "10", "--"},
				{"10", "09", "11", "49", "--"},
				{"11", "10", "08", "52", "--"},
				{"12", "42", "15", "13", "--"},
				{"13", "14", "55", "37", "12"},
				{"14", "13", "15", "51", "--"},
				{"15", "46", "14", "12", "54"},
				{"16", "17", "52", "--", "--"},
				{"17", "16", "18", "59", "--"},
				{"18", "55", "--", "17", "--"},
				{"19", "56", "--", "--", "--"},
				{"20", "21", "53", "34", "--"},
				{"21", "20", "22", "--", "--"},
				{"22", "54", "--", "21", "--"},
				{"23", "57", "--", "--", "--"},
				{"24", "26", "25", "08", "--"},
				{"25", "24", "--", "--", "--"},
				{"26", "24", "00", "--", "--"},
				{"27", "29", "--", "--", "--"},
				{"28", "29", "--", "--", "--"},
				{"29", "49", "28", "27", "07"},
				{"30", "58", "--", "31", "--"},
				{"31", "53", "30", "32", "09"},
				{"32", "48", "31", "--", "--"},
				{"33", "49", "35", "--", "--"},
				{"34", "35", "20", "--", "--"},
				{"35", "04", "57", "34", "33"},
				{"36", "37", "--", "--", "--"},
				{"37", "36", "38", "13", "--"},
				{"38", "37", "--", "--", "--"},
				{"39", "51", "--", "--", "--"},
				{"40", "55", "--", "41", "--"},
				{"41", "40", "--", "06", "--"},
				{"42", "12", "50", "--", "--"},
				{"43", "54", "--", "44", "--"},
				{"44", "02", "58", "43", "--"},
				{"45", "46", "--", "--", "--"},
				{"46", "45", "47", "15", "--"},
				{"47", "05", "--", "46", "--"},
				{"48", "32", "--", "--", "--"},
				{"49", "33", "10", "29", "--"},
				{"50", "01", "42", "--", "--"},
				{"51", "39", "14", "--", "--"},
				{"52", "11", "--", "16", "--"},
				{"53", "20", "31", "--", "--"},
				{"54", "15", "43", "22", "--"},
				{"55", "13", "40", "18", "--"},
				{"56", "00", "--", "19", "--"},
				{"57", "23", "35", "--", "--"},
				{"58", "44", "--", "30", "--"},
				{"59", "17", "--", "--", "--"},
				{"--"}
		};
	private final String[][] CRSC =
		{
				{"00", "46", "52", "24", "38", "30"},
				{"01", "50", "34", "54", "38", "--"},
				{"02", "39", "31", "--", "53", "--"},
				{"03", "39", "25", "51", "35", "--"},
				{"04", "40", "--", "48", "--", "--"},
				{"05", "56", "40", "--", "52", "--"},
				{"06", "49", "--", "25", "--", "--"},
				{"07", "37", "--", "41", "--", "--"},
				{"08", "38", "--", "--", "--", "--"},
				{"09", "35", "--", "42", "--", "--"},
				{"10", "55", "26", "43", "31", "--"},
				{"11", "34", "--", "43", "--", "--"},
				{"12", "40", "--", "27", "--", "--"},
				{"13", "27", "57", "37", "61", "44"},
				{"14", "33", "--", "57", "--", "--"},
				{"15", "60", "45", "27", "56", "36"},
				{"16", "59", "--", "46", "--", "--"},
				{"17", "28", "60", "--", "--", "--"},
				{"18", "58", "29", "--", "31", "--"},
				{"19", "53", "47", "29", "--", "--"},
				{"20", "29", "--", "32", "--", "--"},
				{"21", "50", "48", "29", "--", "--"},
				{"22", "49", "33", "--", "--", "--"},
				{"23", "28", "59", "35", "--", "--"},
				{"24", "00", "--", "--", "--"},
				{"25", "06", "03", "--", "--"},
				{"26", "10", "--", "--", "--"},
				{"27", "13", "12", "15", "--"},
				{"28", "23", "--", "17", "--"},
				{"29", "21", "20", "19", "18"},
				{"30", "00", "--", "--"},
				{"31", "18", "02", "10"},
				{"32", "20", "--", "--"},
				{"33", "14", "22", "--"},
				{"34", "11", "01", "--"},
				{"35", "23", "09", "03"},
				{"36", "15", "--", "--"},
				{"37", "13", "07", "--"},
				{"38", "08", "00", "01"},
				{"39", "02", "03", "--"},
				{"40", "05", "12", "04"},
				{"41", "07", "--", "--"},
				{"42", "09", "--", "--"},
				{"43", "10", "11", "--"},
				{"44", "13", "--", "--"},
				{"45", "15", "--", "--"},
				{"46", "00", "16", "--"},
				{"47", "19", "--", "--"},
				{"48", "21", "04", "--"},
				{"49", "06", "22", "--"},
				{"50", "21", "01", "--"},
				{"51", "03", "--", "--"},
				{"52", "05", "00", "--"},
				{"53", "02", "19", "--"},
				{"54", "01", "--", "--"},
				{"55", "10", "--", "--"},
				{"56", "05", "15", "--"},
				{"57", "13", "14", "--"},
				{"58", "18", "--", "--"},
				{"59", "23", "16", "--"},
				{"60", "15", "17", "--"},
				{"61", "13", "--", "--"},
				{"--"}
		};
	private final String[][] PH =
		{
				{"00", "10", "14", "52", "01", "04"},
				{"01", "02", "00", "--", "51", "--"},
				{"02", "01", "--", "25", "--", "--"},
				{"03", "23", "--", "04", "--", "--"},
				{"04", "00", "03", "43", "--", "--"},
				{"05", "06", "--", "15", "--", "--"},
				{"06", "21", "--", "05", "--", "--"},
				{"07", "29", "--", "--", "--", "--"},
				{"08", "29", "--", "--", "--", "--"},
				{"09", "58", "--", "15", "--", "--"},
				{"10", "00", "--", "42", "--", "--"},
				{"11", "31", "--", "--", "--", "--"},
				{"12", "31", "30", "--", "13", "--"},
				{"13", "38", "53", "14", "12", "39"},
				{"14", "00", "--", "13", "--", "--"},
				{"15", "05", "09", "57", "16", "19"},
				{"16", "15", "--", "--", "--", "--"},
				{"17", "35", "--", "18", "--", "--"},
				{"18", "17", "--", "--", "--", "--"},
				{"19", "47", "--", "15", "--", "--"},
				{"20", "21", "24", "--", "29", "--"},
				{"21", "22", "20", "--", "06", "--"},
				{"22", "23", "21", "46", "45", "--"},
				{"23", "22", "44", "--", "03", "--"},
				{"24", "20", "--", "--", "--", "--"},
				{"25", "02", "26", "29", "--", "--"},
				{"26", "25", "--", "--", "--", "--"},
				{"27", "50", "--", "28", "--", "--"},
				{"28", "29", "27", "--", "--", "--"},
				{"29", "25", "28", "08", "07", "20"},
				{"30", "12", "31", "--", "35", "--"},
				{"31", "11", "41", "32", "30", "12"},
				{"32", "31", "--", "--", "--", "--"},
				{"33", "34", "--", "49", "--", "--"},
				{"34", "35", "--", "33", "--", "--"},
				{"35", "36", "39", "30", "34", "17"},
				{"36", "37", "35", "--", "--", "--"},
				{"37", "38", "36", "--", "55", "--"},
				{"38", "13", "--", "37", "--", "--"},
				{"39", "35", "--", "13", "--", "--"},
				{"40", "41", "44", "--", "--", "--"},
				{"41", "40", "--", "31", "--", "--"},
				{"42", "10", "--", "--", "--", "--"},
				{"43", "04", "--", "--", "--", "--"},
				{"44", "40", "--", "23", "--", "--"},
				{"45", "22", "--", "49", "--", "--"},
				{"46", "22", "--", "--", "--", "--"},
				{"47", "19", "48", "--", "--", "--"},
				{"48", "49", "47", "--", "--", "--"},
				{"49", "45", "48", "33", "--", "--"},
				{"50", "59", "27", "51", "54", "--"},
				{"51", "01", "--", "50", "--", "--"},
				{"52", "00", "--", "--", "--", "--"},
				{"53", "13", "--", "54", "--", "--"},
				{"54", "50", "53", "--", "--", "--"},
				{"55", "37", "--", "--", "--", "--"},
				{"56", "57", "--", "--", "--", "--"},
				{"57", "56", "--", "15", "--", "--"},
				{"58", "59", "--", "09", "--", "--"},
				{"59", "50", "--", "58", "--", "--"},
				{"--"}
		};
	private String[][] OPC =
		{
				{"00", "08", "02", "01", "32"},
				{"01", "00", "--", "--", "--"},
				{"02", "00", "--", "12", "--"},
				{"03", "04", "41", "17", "--"},
				{"04", "03", "05", "35", "--"},
				{"05", "04", "--", "31", "--"},
				{"06", "07", "45", "20", "--"},
				{"07", "06", "--", "33", "--"},
				{"08", "00", "--", "--", "--"},
				{"09", "11", "10", "34", "--"},
				{"10", "44", "--", "09", "--"},
				{"11", "09", "28", "--", "--"},
				{"12", "13", "43", "02", "--"},
				{"13", "12", "14", "37", "16"},
				{"14", "25", "18", "13", "--"},
				{"15", "17", "16", "38", "23"},
				{"16", "42", "13", "15", "--"},
				{"17", "03", "--", "15", "--"},
				{"18", "19", "36", "14", "20"},
				{"19", "18", "--", "--", "--"},
				{"20", "18", "--", "06", "--"},
				{"21", "23", "22", "47", "--"},
				{"22", "39", "--", "21", "--"},
				{"23", "21", "29", "15", "--"},
				{"24", "27", "25", "--"},
				{"25", "24", "26", "14"},
				{"26", "25", "--", "--"},
				{"27", "24", "--", "--"},
				{"28", "29", "11", "31"},
				{"29", "23", "28", "30"},
				{"30", "29", "31", "--"},
				{"31", "05", "30", "28"},
				{"32", "35", "33", "00"},
				{"33", "32", "34", "07"},
				{"34", "33", "35", "09"},
				{"35", "34", "32", "04"},
				{"36", "18", "39", "--"},
				{"37", "13", "--", "--"},
				{"38", "15", "--", "--"},
				{"39", "36", "22", "--"},
				{"40", "43", "--", "--"},
				{"41", "42", "03", "--"},
				{"42", "16", "41", "--"},
				{"43", "40", "12", "--"},
				{"44", "45", "10", "--"},
				{"45", "44", "46", "06"},
				{"46", "45", "--", "--"},
				{"47", "21", "--", "--"},
				{"--"}
		};
	private final String[][] PD =
		{
				{"00", "01", "10", "04"},
				{"01", "00", "02", "--"},
				{"02", "01", "--", "--"},
				{"03", "04", "24", "--"},
				{"04", "00", "43", "03"},
				{"05", "15", "--", "--"},
				{"06", "07", "47", "--"},
				{"07", "06", "08", "--"},
				{"08", "29", "07", "09"},
				{"09", "58", "08", "--"},
				{"10", "11", "00", "--"},
				{"11", "10", "12", "42"},
				{"12", "13", "31", "11"},
				{"13", "39", "12", "14"},
				{"14", "53", "13", "--"},
				{"15", "05", "19", "16"},
				{"16", "17", "57", "15"},
				{"17", "16", "18", "36"},
				{"18", "17", "--", "--"},
				{"19", "15", "48", "--"},
				{"20", "21", "--", "--"},
				{"21", "20", "22", "--"},
				{"22", "21", "23", "46"},
				{"23", "44", "22", "--"},
				{"24", "03", "--", "--"},
				{"25", "29", "--", "--"},
				{"26", "27", "--", "--"},
				{"27", "28", "51", "26"},
				{"28", "27", "29", "--"},
				{"29", "25", "08", "28"},
				{"30", "31", "35", "--"},
				{"31", "12", "30", "32"},
				{"32", "41", "31", "33"},
				{"33", "32", "34", "49"},
				{"34", "33", "--", "--"},
				{"35", "36", "30", "39"},
				{"36", "17", "35", "37"},
				{"37", "56", "36", "--"},
				{"38", "39", "--", "--"},
				{"39", "38", "35", "13"},
				{"40", "45", "--", "--"},
				{"41", "42", "32", "--"},
				{"42", "11", "41", "--"},
				{"43", "44", "04", "--"},
				{"44", "23", "43", "--"},
				{"45", "40", "49", "--"},
				{"46", "47", "22", "--"},
				{"47", "06", "46", "48"},
				{"48", "49", "19", "47"},
				{"49", "45", "33", "48"},
				{"50", "54", "--", "--"},
				{"51", "52", "27", "--"},
				{"52", "51", "53", "--"},
				{"53", "54", "14", "52"},
				{"54", "53", "50", "--"},
				{"55", "59", "56", "--"},
				{"56", "37", "55", "--"},
				{"57", "16", "--", "--"},
				{"58", "59", "09", "--"},
				{"59", "58", "55", "--"},
				{"--"}
		};
	private final String[][] RR =
		{
				{"00", "18", "23", "22", "19"},
				{"01", "25", "24", "21", "--"},
				{"02", "26", "29", "28", "--"},
				{"03", "32", "31", "30", "--"},
				{"04", "36", "35", "34", "--"},
				{"05", "38", "41", "40", "--"},
				{"06", "18", "--", "--", "--"},
				{"07", "21", "29", "--", "--"},
				{"08", "33", "--", "--", "--"},
				{"09", "24", "--", "31", "--"},
				{"10", "35", "--", "26", "--"},
				{"11", "29", "--", "38", "--"},
				{"12", "31", "--", "34", "--"},
				{"13", "39", "40", "32", "33"},
				{"14", "35", "--", "--", "--"},
				{"15", "25", "20", "36", "37"},
				{"16", "18", "38", "--", "--"},
				{"17", "41", "--", "24", "--"},
				{"18", "16", "00", "06", "--"},
				{"19", "42", "--", "00", "--"},
				{"20", "15", "--", "--", "--"},
				{"21", "07", "01", "--", "--"},
				{"22", "00", "--", "46", "--"},
				{"23", "48", "--", "00", "--"},
				{"24", "17", "01", "09", "--"},
				{"25", "01", "15", "--", "--"},
				{"26", "02", "10", "--", "--"},
				{"27", "42", "--", "--", "--"},
				{"28", "02", "--", "--", "--"},
				{"29", "02", "07", "45", "11"},
				{"30", "03", "--", "--", "--"},
				{"31", "09", "47", "12", "03"},
				{"32", "03", "13", "49", "--"},
				{"33", "13", "--", "08", "--"},
				{"34", "46", "12", "04", "--"},
				{"35", "04", "10", "42", "14"},
				{"36", "04", "15", "43", "--"},
				{"37", "15", "--", "--", "--"},
				{"38", "44", "11", "05", "16"},
				{"39", "13", "--", "--", "--"},
				{"40", "13", "05", "--", "--"},
				{"41", "45", "17", "05", "--"},
				{"42", "19", "35", "27"},
				{"43", "36", "--", "--"},
				{"44", "38", "--", "--"},
				{"45", "41", "29", "--"},
				{"46", "34", "22", "--"},
				{"47", "31", "--", "--"},
				{"48", "23", "--", "--"},
				{"49", "32", "--", "--"},
				{"--"}
		};
	private final String[][] R =
		{
				{"00", "12", "16", "32", "40", "28"},
				{"01", "13", "29", "--", "33", "--"},
				{"02", "41", "34", "18", "--", "--"},
				{"03", "31", "15", "19", "35", "--"},
				{"04", "12", "20", "--", "--", "--"},
				{"05", "15", "23", "--", "13", "--"},
				{"06", "26", "24", "--", "--", "--"},
				{"07", "37", "17", "25", "27", "--"},
				{"08", "38", "20", "--", "29", "--"},
				{"09", "23", "31", "--", "22", "--"},
				{"10", "32", "--", "39", "--", "--"},
				{"11", "26", "--", "35", "--", "--"},
				{"12", "00", "54", "04", "--"},
				{"13", "55", "01", "43", "05"},
				{"14", "44", "--", "56", "--"},
				{"15", "57", "05", "45", "03"},
				{"16", "00", "42", "--", "--"},
				{"17", "07", "--", "--", "--"},
				{"18", "02", "--", "--", "--"},
				{"19", "61", "03", "--", "--"},
				{"20", "04", "--", "08", "--"},
				{"21", "55", "--", "--", "--"},
				{"22", "46", "09", "--", "--"},
				{"23", "09", "--", "05", "--"},
				{"24", "58", "06", "--", "--"},
				{"25", "07", "--", "--", "--"},
				{"26", "11", "--", "06", "--"},
				{"27", "61", "07", "49", "--"},
				{"28", "00", "--", "--", "--"},
				{"29", "08", "50", "01", "55"},
				{"30", "51", "--", "56", "--"},
				{"31", "03", "51", "09", "57"},
				{"32", "58", "10", "52", "00"},
				{"33", "59", "01", "52", "--"},
				{"34", "60", "02", "53", "--"},
				{"35", "03", "61", "11", "53"},
				{"36", "44", "--", "42", "--"},
				{"37", "43", "07", "--", "--"},
				{"38", "46", "08", "47", "--"},
				{"39", "10", "48", "--", "--"},
				{"40", "00", "--", "--", "--"},
				{"41", "02", "51", "--", "--"},
				{"42", "36", "16", "--"},
				{"43", "37", "13", "--"},
				{"44", "36", "14", "--"},
				{"45", "15", "--", "--"},
				{"46", "38", "22", "--"},
				{"47", "38", "--", "--"},
				{"48", "39", "--", "--"},
				{"49", "27", "--", "--"},
				{"50", "29", "--", "--"},
				{"51", "31", "41", "30"},
				{"52", "32", "33", "--"},
				{"53", "35", "34", "--"},
				{"54", "12", "--", "--"},
				{"55", "21", "29", "13"},
				{"56", "30", "14", "--"},
				{"57", "15", "31", "--"},
				{"58", "24", "32", "--"},
				{"59", "33", "--", "--"},
				{"60", "34", "--", "--"},
				{"61", "35", "19", "27"},
				{"--"}
		};
	private final String[][] TI =
		{
				{"00", "02", "01", "38"},
				{"01", "48", "00", "--"},
				{"02", "00", "06", "--"},
				{"03", "04", "--", "--"},
				{"04", "53", "03", "05"},
				{"05", "39", "04", "--"},
				{"06", "02", "08", "--"},
				{"07", "08", "56", "--"},
				{"08", "42", "07", "06"},
				{"09", "47", "11", "10"},
				{"10", "57", "09", "--"},
				{"11", "09", "--", "--"},
				{"12", "13", "44", "--"},
				{"13", "12", "14", "16"},
				{"14", "36", "13", "--"},
				{"15", "17", "16", "41"},
				{"16", "13", "15", "--"},
				{"17", "15", "45", "--"},
				{"18", "19", "50", "--"},
				{"19", "20", "22", "18"},
				{"20", "54", "19", "--"},
				{"21", "59", "--", "--"},
				{"22", "23", "19", "--"},
				{"23", "22", "--", "--"},
				{"24", "26", "25", "--"},
				{"25", "31", "24", "--"},
				{"26", "24", "37", "--"},
				{"27", "43", "29", "--"},
				{"28", "29", "--", "--"},
				{"29", "28", "27", "46"},
				{"30", "31", "49", "--"},
				{"31", "25", "30", "32"},
				{"32", "52", "31", "--"},
				{"33", "58", "35", "--"},
				{"34", "35", "--", "--"},
				{"35", "55", "34", "33"},
				{"36", "37", "14", "--"},
				{"37", "36", "38", "26"},
				{"38", "00", "37", "--"},
				{"39", "41", "40", "05"},
				{"40", "39", "--", "--"},
				{"41", "39", "15", "--"},
				{"42", "43", "08", "--"},
				{"43", "27", "42", "44"},
				{"44", "12", "43", "--"},
				{"45", "47", "46", "17"},
				{"46", "29", "45", "--"},
				{"47", "45", "09", "--"},
				{"48", "01", "--", "--"},
				{"49", "50", "30", "--"},
				{"50", "18", "49", "--"},
				{"51", "53", "--", "--"},
				{"52", "53", "32", "--"},
				{"53", "52", "51", "04"},
				{"54", "55", "20", "--"},
				{"55", "54", "56", "35"},
				{"56", "07", "55", "--"},
				{"57", "58", "10", "--"},
				{"58", "33", "57", "59"},
				{"59", "21", "58", "--"},
				{"--"}
		};
	private final double r;
	public PolyhedralMaze(double resizer)
	{
		r = resizer;
	}
	public String run()
	{
		String[] mazeNames = {
				"PH", "CI", "TDI", "CD", "R", "CRSC", "OPC", 
				"RR", "PD", "DD", "TI", "DH", "JSC", "JR", 
				};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[mazeNames.length];
		JButton[] jButton = new JButton[mazeNames.length];
		optionPane.setLayout(new GridLayout(5, 3));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < mazeNames.length; aa++)
		{
			icon[aa] = new ImageIcon("img/PolyhedralMaze" + mazeNames[aa] + ".jpg");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, mazeNames[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the maze type:");
		dialog.setVisible(true);
		String mazeName = (String)(optionPane.getValue());
		String[][] maze;
		switch(mazeName)
		{
			case "CD":
				maze = CD;
				break;
			case "CI":
				maze = CI;
				break;
			case "CRSC":
				maze = CRSC;
				break;
			case "DD":
				maze = DD;
				break;
			case "DH":
				maze = DH;
				break;
			case "JR":
				maze = JR;
				break;
			case "JSC":
				maze = JSC;
				break;
			case "OPC":
				maze = OPC;
				break;
			case "PD":
				maze = PD;
				break;
			case "PH":
				maze = PH;
				break;
			case "R":
				maze = R;
				break;
			case "RR":
				maze = RR;
				break;
			case "TDI":
				maze = TDI;
				break;
			default:
				maze = TI;
				break;
		}
		String input = JOptionPane.showInputDialog("Enter the 2 numbers (spaces):");
		boolean v = valid(input, maze);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 2 numbers (spaces):");
			v = valid(input, maze);
		}
		String[] spl = input.split(" ");
		String souv = "STARTING POS: " + spl[0].toUpperCase();
		for(int aa = 0; aa < spl.length; aa++)
		{
			if(spl[aa].length() == 1)
				spl[aa] = "0" + spl[aa];
		}
		MTK mtk = new MTK();
		String paths = mtk.getMazeSolution(maze, 0, spl[0], spl[1]);
		//System.out.println(paths);
		String prev = spl[0];
		String out = "Start at " + paths.substring(0, 2) + ":\n", route = "";
	
		while(paths.length() > 2)
		{
			String curr = paths.substring(0, 2);
			paths = paths.substring(2);
			int index = Integer.parseInt(curr);
			ArrayList<String> list = new ArrayList<String>();
			for(int aa = 1; aa < maze[index].length; aa++)
				list.add(maze[index][aa]);
			int from = list.indexOf(prev);
			int to = list.indexOf(paths.substring(0, 2));
			index = to - from;
			while(index < 0)
				index += list.size();
			route = route + "" + index;
			prev = curr.toUpperCase();
		}
		//System.out.println(out + "" + route);
		for(int aa = 0; aa < route.length(); aa++)
		{
			out = out + "" + route.charAt(aa);
			if((aa + 1) % 12 == 0)
				out = out + "\n";
			else if((aa + 1) % 3 == 0)
				out = out + " ";
		}
		JOptionPane.showMessageDialog(null, out);
		return souv;
	}
	private JButton getButton(final JOptionPane optionPane, String name, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(name);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean valid(String i, String[][] m)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			for(int aa = 0; aa < 2; aa++)
			{
				if(conv[aa].length() == 1)
					conv[aa] = "0" + conv[aa];
				boolean flag = true;
				for(int bb = 0; bb < m.length - 1; bb++)
				{
					if(m[bb][0].equals(conv[aa]))
					{
						flag = false;
						break;
					}
				}
				if(flag)
					return false;
			}
			return true;
		}
		return false;
	}
}