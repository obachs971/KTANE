package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import misc.MTK;
import start.BombEdgework;

public class Maze3D 
{
	private final String[][] mazeABC =
		{
				{"00", "R01", "D10"},{"01", "D11", "L00"},{"02", "U72", "R03", "D12A"},{"03", "U73", "D13", "L02"},{"04", "U74A", "R05A"},{"05A", "R06", "D15", "L04"},{"06", "D16", "L05A"},{"07", "U77", "D17B"},
				{"10", "U00", "R11", "D20A"},{"11", "U01", "L10"},{"12A", "U02", "R13", "D22"},{"13", "U03", "R14", "L12A"},{"14", "L13"},{"15", "U05A", "R16"},{"16", "U06", "R17B", "D26", "L15"},{"17B", "U07", "D27", "L16"},
				{"20A", "U10", "R21", "D30", "L27"},{"21", "R22", "D31C", "L20A"},{"22", "U12A", "L21"},{"23B", "R24", "D33"},{"24", "R25C", "D34", "L23B"},{"25C", "R26", "D35", "L24"},{"26", "U16", "L25C"},{"27", "U17B", "R20A"},
				{"30", "U20A", "L37B"},{"31C", "U21", "R32", "D41"},{"32", "D42", "L31C"},{"33", "U23B", "R34"},{"34", "U24", "L33"},{"35", "U25C", "D45"},{"36", "R37B", "D46"},{"37B", "R30", "L36"},
				{"40", "D50", "L47"},{"41", "U31C", "R42", "D51B"},{"42", "U32", "D52", "L41"},{"43", "R44A", "D53C"},{"44A", "R45", "D54", "L43"},{"45", "U35", "R46", "L44A"},{"46", "U36", "D56B", "L45"},{"47", "R40", "D57"},
				{"50", "U40", "D60", "L57"},{"51B", "U41", "D61"},{"52", "U42", "R53C"},{"53C", "U43", "R54", "L52"},{"54", "U44A", "D64", "L53C"},{"55", "R56B", "D65"},{"56B", "U46", "R57", "L55"},{"57", "U47", "R50", "D67", "L56B"},
				{"60", "U50", "L67"},{"61", "U51B", "R62C"},{"62C", "R63", "D72", "L61"},{"63", "D73", "L62C"},{"64", "U54", "D74A"},{"65", "U55", "R66", "D75"},{"66", "D76C", "L65"},{"67", "U57", "R60"},
				{"70", "R71", "L77"},{"71", "L70"},{"72", "U62C", "R73", "D02"},{"73", "U63", "D03", "L72"},{"74A", "U64", "D04"},{"75", "U65", "R76C"},{"76C", "U66", "R77", "L75"},{"77", "R70", "D07", "L76C"}
		};
	private final String[][] mazeABD =
		{
				{"00A", "R01", "D10"},{"01", "U71D", "R02", "L00A"},{"02", "R03B", "L01"},{"03B", "U73", "R04", "D13", "L02"},{"04", "U74", "R05", "D14", "L03B"},{"05", "U75", "L04"},{"06A", "R07", "D16"},{"07", "D17", "L06A"},
				{"10", "U00A", "D20"},{"11", "R12D", "D21"},{"12D", "R13", "D22", "L11"},{"13", "U03B", "R14", "L12D"},{"14", "U04", "L13"},{"15", "R16", "D25D"},{"16", "U06A", "R17", "L15"},{"17", "U07", "D27B", "L16"},
				{"20", "U10", "R21", "D30", "L27B"},{"21", "U11", "R22", "D31A", "L20"},{"22", "U12D", "L21"},{"23", "R24", "D33B"},{"24", "R25D", "D34", "L23"},{"25D", "U15", "R26", "D35", "L24"},{"26", "D36", "L25D"},{"27B", "U17", "R20", "D37"},
				{"30", "U20", "L37"},{"31A", "U21", "R32"},{"32", "R33B", "L31A"},{"33B", "U23", "R34", "D43", "L32"},{"34", "U24", "D44", "L33B"},{"35", "U25D", "R36"},{"36", "U26", "R37", "L35"},{"37", "U27B", "R30", "D47", "L36"},
				{"40", "R41", "D50D"},{"41", "R42", "D51", "L40"},{"42", "D52", "L41"},{"43", "U33B", "R44"},{"44", "U34", "R45", "D54A", "L43"},{"45", "R46A", "L44"},{"46A", "D56", "L45"},{"47", "U37", "D57"},
				{"50D", "U40", "R51", "L57"},{"51", "U41", "R52", "D61", "L50D"},{"52", "U42", "L51"},{"53", "R54A", "D63"},{"54A", "U44", "D64", "L53"},{"55", "R56", "D65D"},{"56", "U46A", "R57", "D66", "L55"},{"57", "U47", "R50D", "L56"},
				{"60", "D70", "L67"},{"61", "U51", "R62B", "D71D"},{"62B", "R63", "D72", "L61"},{"63", "U53", "R64", "L62B"},{"64", "U54A", "L63"},{"65D", "U55", "R66", "D75"},{"66", "U56", "D76", "L65D"},{"67", "R60", "D77B"},
				{"70", "U60", "R71D", "L77B"},{"71D", "U61", "R72", "D01", "L70"},{"72", "U62B", "L71D"},{"73", "R74", "D03B"},{"74", "D04", "L73"},{"75", "U65D", "R76", "D05"},{"76", "U66", "R77B", "L75"},{"77B", "U67", "R70", "L76"}
		};
	private final String[][] mazeABH =
		{
				{"00B", "R01", "D10"},{"01", "R02", "L00B"},{"02", "D12H", "L01"},{"03", "U73H", "R04"},{"04", "R05A", "L03"},{"05A", "U75", "R06", "D15", "L04"},{"06", "R07H", "D16", "L05A"},{"07H", "D17", "L06"},
				{"10", "U00B", "L17"},{"11", "R12H", "D21"},{"12H", "U02", "R13", "D22", "L11"},{"13", "R14", "D23", "L12H"},{"14", "R15", "L13"},{"15", "U05A", "R16", "L14"},{"16", "U06", "D26", "L15"},{"17", "U07H", "R10"},
				{"20B", "R21", "D30", "L27"},{"21", "U11", "L20B"},{"22", "U12H", "D32"},{"23", "U13", "R24B"},{"24B", "R25", "D34", "L23"},{"25", "D35", "L24B"},{"26", "U16", "D36H"},{"27", "R20B", "D37A"},
				{"30", "U20B", "D40"},{"31", "R32", "D41A"},{"32", "U22", "D42", "L31"},{"33", "R34", "D43H"},{"34", "U24B", "L33"},{"35", "U25", "R36H", "D45"},{"36H", "U26", "D46", "L35"},{"37A", "U27", "D47"},
				{"40", "U30", "D50"},{"41A", "U31", "R42", "D51"},{"42", "U32", "L41A"},{"43H", "U33", "R44"},{"44", "R45", "D54A", "L43H"},{"45", "U35", "L44"},{"46", "U36H", "R47", "D56B"},{"47", "U37A", "D57", "L46"},
				{"50", "U40", "D60"},{"51", "U41A", "D61B"},{"52", "R53"},{"53", "R54A", "D63", "L52"},{"54A", "U44", "R55", "L53"},{"55", "R56B", "L54A"},{"56B", "U46", "R57", "L55"},{"57", "U47", "D67", "L56B"},
				{"60", "U50", "R61B", "D70A", "L67"},{"61B", "U51", "R62", "L60"},{"62", "L61B"},{"63", "U53", "R64", "D73H"},{"64", "D74", "L63"},{"65", "R66", "D75"},{"66", "D76", "L65"},{"67", "U57", "R60", "D77"},
				{"70A", "U60", "R71", "L77"},{"71", "R72", "L70A"},{"72", "R73H", "L71"},{"73H", "U63", "R74", "D03", "L72"},{"74", "U64", "R75", "L73H"},{"75", "U65", "R76", "D05A", "L74"},{"76", "U66", "L75"},{"77", "U67", "R70A"}
		};
	private final String[][] mazeACD =
		{
				{"00D", "R01", "D10", "L07"},{"01", "U71", "R02", "L00D"},{"02", "R03", "L01"},{"03", "D13", "L02"},{"04", "R05", "D14D"},{"05", "U75C", "R06", "L04"},{"06", "R07", "L05"},{"07", "R00D", "D17C", "L06"},
				{"10", "U00D", "D20"},{"11", "R12C", "D21"},{"12C", "R13", "D22", "L11"},{"13", "U03", "D23", "L12C"},{"14D", "U04", "D24"},{"15", "R16"},{"16", "R17C", "L15"},{"17C", "U07", "L16"},
				{"20", "U10", "D30", "L27"},{"21", "U11"},{"22", "U12C", "D32"},{"23", "U13", "R24", "D33"},{"24", "U14D", "R25C", "D34", "L23"},{"25C", "D35", "L24"},{"26", "R27", "D36"},{"27", "R20", "D37", "L26"},
				{"30", "U20", "R31A"},{"31A", "R32", "L30"},{"32", "U22", "L31A"},{"33", "U23", "D43C"},{"34", "U24", "R35"},{"35", "U25C", "R36", "L34"},{"36", "U26", "L35"},{"37", "U27", "D47"},
				{"40D", "R41", "D50"},{"41", "R42", "L40D"},{"42", "R43C", "L41"},{"43C", "U33", "D53", "L42"},{"44", "R45D", "D54"},{"45D", "R46", "L44"},{"46", "D56", "L45D"},{"47", "U37", "D57A"},
				{"50", "U40D", "D60", "L57A"},{"51", "D61"},{"52A", "R53", "D62"},{"53", "U43C", "R54", "L52A"},{"54", "U44", "D64", "L53"},{"55", "R56"},{"56", "U46", "L55"},{"57A", "U47", "R50", "D67"},
				{"60", "U50", "D70A", "L67"},{"61", "U51", "R62"},{"62", "U52A", "L61"},{"63A", "R64", "D73"},{"64", "U54", "R65", "L63A"},{"65", "R66D", "L64"},{"66D", "R67", "D76", "L65"},{"67", "U57A", "R60", "D77", "L66D"},
				{"70A", "U60", "R71"},{"71", "R72", "D01", "L70A"},{"72", "R73", "L71"},{"73", "U63A", "R74", "L72"},{"74", "R75C", "L73"},{"75C", "D05", "L74"},{"76", "U66D", "R77"},{"77", "U67", "L76"}
		};
	private final String[][] mazeACH =
		{
				{"00H", "U70", "R01"},{"01", "U71", "R02C", "L00H"},{"02C", "R03", "D12", "L01"},{"03", "R04", "L02C"},{"04", "D14H", "L03"},{"05", "D15"},{"06A", "U76", "R07", "D16"},{"07", "U77", "D17", "L06A"},
				{"10", "R11", "D20"},{"11", "R12", "L10"},{"12", "U02C", "L11"},{"13", "R14H", "D23"},{"14H", "U04", "R15", "D24", "L13"},{"15", "U05", "R16", "L14H"},{"16", "U06A", "L15"},{"17", "U07", "D27C"},
				{"20", "U10", "D30", "L27C"},{"21", "R22", "D31A"},{"22", "D32", "L21"},{"23", "U13", "R24", "D33"},{"24", "U14H", "D34", "L23"},{"25", "R26", "D35H"},{"26", "D36", "L25"},{"27C", "U17", "R20", "D37"},
				{"30", "U20", "R31A", "D40C"},{"31A", "U21", "R32", "L30"},{"32", "U22", "R33", "D42H", "L31A"},{"33", "U23", "R34", "D43", "L32"},{"34", "U24", "R35H", "D44C", "L33"},{"35H", "U25", "R36", "L34"},{"36", "U26", "D46A", "L35H"},{"37", "U27C", "D47"},
				{"40C", "U30", "D50"},{"41", "R42H", "D51"},{"42H", "U32", "R43", "D52", "L41"},{"43", "U33", "R44C", "D53", "L42H"},{"44C", "U34", "R45", "D54", "L43"},{"45", "R46A", "D55", "L44C"},{"46A", "U36", "R47", "D56", "L45"},{"47", "U37", "L46A"},
				{"50", "U40C", "L57A"},{"51", "U41", "R52"},{"52", "U42H", "L51"},{"53", "U43", "R54", "D63C"},{"54", "U44C", "D64", "L53"},{"55", "U45", "R56", "D65H"},{"56", "U46A", "L55"},{"57A", "R50", "D67"},
				{"60", "R61", "D70"},{"61", "R62", "L60"},{"62", "D72A", "L61"},{"63C", "U53", "R64"},{"64", "U54", "R65H", "L63C"},{"65H", "U55", "R66", "D75", "L64"},{"66", "R67", "L65H"},{"67", "U57A", "L66"},
				{"70", "U60", "D00H", "L77"},{"71", "R72A", "D01"},{"72A", "U62", "R73", "L71"},{"73", "R74", "L72A"},{"74", "R75", "L73"},{"75", "U65H", "L74"},{"76", "R77", "D06A"},{"77", "R70", "D07", "L76"}
		};
	private final String[][] mazeADH =
		{
				{"00D", "U70", "R01", "D10"},{"01", "D11", "L00D"},{"02D", "R03", "D12"},{"03", "R04", "D13", "L02D"},{"04", "U74", "L03"},{"05", "R06"},{"06", "U76", "R07", "L05"},{"07", "D17A", "L06"},
				{"10", "U00D", "D20"},{"11", "U01", "D21"},{"12", "U02D", "D22H"},{"13", "U03", "R14H", "D23"},{"14H", "R15", "D24", "L13"},{"15", "R16", "L14H"},{"16", "R17A", "L15"},{"17A", "U07", "L16"},
				{"20", "U10", "D30A"},{"21", "U11", "R22H"},{"22H", "U12", "D32", "L21"},{"23", "U13", "D33D"},{"24", "U14H", "R25"},{"25", "R26A", "L24"},{"26A", "R27", "D36", "L25"},{"27", "D37", "L26A"},
				{"30A", "U20", "D40", "L37"},{"31", "D41"},{"32", "U22H"},{"33D", "U23", "R34"},{"34", "R35", "D44H", "L33D"},{"35", "R36", "L34"},{"36", "U26A", "R37", "D46", "L35"},{"37", "U27", "R30A", "L36"},
				{"40", "U30A", "R41"},{"41", "U31", "R42", "L40"},{"42", "R43", "L41"},{"43", "R44H", "D53", "L42"},{"44H", "U34", "D54", "L43"},{"45D", "R46", "D55"},{"46", "U36", "D56", "L45D"},{"47", "D57A"},
				{"50", "R51", "D60D"},{"51", "R52H", "L50"},{"52H", "R53", "L51"},{"53", "U43", "D63", "L52H"},{"54", "U44H", "D64"},{"55", "U45D", "D65"},{"56", "U46", "R57A", "D66"},{"57A", "U47", "D67", "L56"},
				{"60D", "U50", "R61", "L67"},{"61", "R62", "L60D"},{"62", "D72", "L61"},{"63", "U53", "D73A"},{"64", "U54"},{"65", "U55", "D75H"},{"66", "U56", "D76"},{"67", "U57A", "R60D", "D77"},
				{"70", "R71", "D00D"},{"71", "R72", "L70"},{"72", "U62", "R73A", "L71"},{"73A", "U63", "R74", "L72"},{"74", "D04", "L73A"},{"75H", "U65", "R76"},{"76", "U66", "D06", "L75H"},{"77", "U67"}
		};
	private final String[][] mazeBCD =
		{
				{"00", "U70D", "R01", "D10C"},{"01", "R02", "L00"},{"02", "U72", "L01"},{"03", "R04", "D13"},{"04", "R05B", "L03"},{"05B", "R06", "D15", "L04"},{"06", "R07", "L05B"},{"07", "U77", "L06"},
				{"10C", "U00", "R11", "D20", "L17"},{"11", "R12D", "L10C"},{"12D", "R13", "L11"},{"13", "U03", "D23B", "L12D"},{"14", "R15", "D24"},{"15", "U05B", "L14"},{"16", "R17", "D26C"},{"17", "R10C", "D27", "L16"},
				{"20", "U10C"},{"21", "D31C"},{"22", "R23B", "D32"},{"23B", "U13", "D33", "L22"},{"24", "U14", "R25", "D34"},{"25", "R26C", "L24"},{"26C", "U16", "R27", "L25"},{"27", "U17", "L26C"},
				{"30", "R31C", "L37"},{"31C", "U21", "R32", "D41", "L30"},{"32", "U22", "R33", "L31C"},{"33", "U23B", "L32"},{"34", "U24", "R35", "D44C"},{"35", "R36B", "D45", "L34"},{"36B", "R37", "L35"},{"37", "R30", "L36B"},
				{"40", "R41", "L47D"},{"41", "U31C", "D51", "L40"},{"42", "R43", "D52"},{"43", "R44C", "L42"},{"44C", "U34", "R45", "L43"},{"45", "U35", "D55D", "L44C"},{"46", "R47D", "D56"},{"47D", "R40", "L46"},
				{"50B", "R51", "D60", "L57"},{"51", "U41", "R52", "L50B"},{"52", "U42", "L51"},{"53", "R54", "D63"},{"54", "R55D", "D64", "L53"},{"55D", "U45", "R56", "D65", "L54"},{"56", "U46", "L55D"},{"57", "R50B", "D67"},
				{"60", "U50B", "R61C", "L67"},{"61C", "R62", "D71", "L60"},{"62", "R63", "L61C"},{"63", "U53", "R64", "L62"},{"64", "U54", "L63"},{"65", "U55D", "D75"},{"66D", "R67", "D76"},{"67", "U57", "R60", "L66D"},
				{"70D", "R71", "D00", "L77"},{"71", "U61C", "L70D"},{"72", "R73B", "D02"},{"73B", "R74", "L72"},{"74", "R75", "L73B"},{"75", "U65", "R76", "L74"},{"76", "U66D", "L75"},{"77", "R70D", "D07"}
		};
	private final String[][] mazeBCH =
		{
				{"00C", "R01", "D10"},{"01", "U71C", "R02", "L00C"},{"02", "R03", "D12C", "L01"},{"03", "R04H", "L02"},{"04H", "U74", "D14", "L03"},{"05", "U75H", "R06"},{"06", "R07", "L05"},{"07", "L06"},
				{"10", "U00C", "L17H"},{"11", "R12C", "D21"},{"12C", "U02", "R13", "L11"},{"13", "D23", "L12C"},{"14", "U04H", "R15"},{"15", "R16", "L14"},{"16", "R17H", "D26", "L15"},{"17H", "R10", "D27", "L16"},
				{"20", "D30B", "L27"},{"21", "U11", "D31"},{"22", "D32"},{"23", "U13", "D33H"},{"24B", "R25", "D34"},{"25", "R26", "L24B"},{"26", "U16", "L25"},{"27", "U17H", "R20", "D37"},
				{"30B", "U20", "R31", "D40"},{"31", "U21", "R32", "L30B"},{"32", "U22", "R33H", "L31"},{"33H", "U23", "L32"},{"34", "U24B", "R35"},{"35", "R36", "L34"},{"36", "R37", "L35"},{"37", "U27", "D47C", "L36"},
				{"40", "U30B", "R41H", "D50"},{"41H", "R42", "D51", "L40"},{"42", "R43", "L41H"},{"43", "D53", "L42"},{"44", "R45B", "D54"},{"45B", "R46", "D55", "L44"},{"46", "R47C", "L45B"},{"47C", "U37", "D57", "L46"},
				{"50", "U40", "L57"},{"51", "U41H", "D61"},{"52", "R53", "D62B"},{"53", "U43", "L52"},{"54", "U44", "D64C"},{"55", "U45B", "R56"},{"56", "D66", "L55"},{"57", "U47C", "R50", "D67"},
				{"60", "D70", "L67"},{"61", "U51", "D71C"},{"62B", "U52", "R63", "D72"},{"63", "D73", "L62B"},{"64C", "U54", "R65", "D74"},{"65", "R66", "L64C"},{"66", "U56", "L65"},{"67", "U57", "R60", "D77B"},
				{"70", "U60", "R71C", "L77B"},{"71C", "U61", "R72", "D01", "L70"},{"72", "U62B", "L71C"},{"73", "U63", "R74"},{"74", "U64C", "R75H", "D04H", "L73"},{"75H", "D05", "L74"},{"76", "R77B"},{"77B", "U67", "R70", "L76"}
		};
	private final String[][] mazeBDH =
		{
				{"00", "U70D", "D10", "L07H"},{"01", "U71", "D11"},{"02D", "R03", "D12"},{"03", "U73", "L02D"},{"04B", "U74", "R05", "D14"},{"05", "U75B", "R06", "D15", "L04B"},{"06", "U76", "R07H", "D16D", "L05"},{"07H", "R00", "L06"},
				{"10", "U00"},{"11", "U01", "R12"},{"12", "U02D", "D22H", "L11"},{"13", "R14"},{"14", "U04B", "L13"},{"15", "U05", "D25"},{"16D", "U06", "D26"},{"17", "D27B"},
				{"20", "R21", "L27B"},{"21", "R22H", "L20"},{"22H", "U12", "R23", "D32", "L21"},{"23", "R24", "L22H"},{"24", "L23"},{"25", "U15", "D35B"},{"26", "U16D", "R27B"},{"27B", "U17", "R20", "L26"},
				{"30D", "R31", "D40", "L37"},{"31", "D41", "L30D"},{"32", "U22H", "R33", "D42"},{"33", "R34", "L32"},{"34", "R35B", "D44D", "L33"},{"35B", "U25", "R36", "D45", "L34"},{"36", "R37", "L35B"},{"37", "R30D", "L36"},
				{"40", "U30D", "R41", "L47H"},{"41", "U31", "D51", "L40"},{"42", "U32", "D52B"},{"43", "R44D", "D53"},{"44D", "U34", "D54", "L43"},{"45", "U35B", "R46", "D55"},{"46", "R47H", "D56", "L45"},{"47H", "R40", "D57", "L46"},
				{"50", "R51", "D60"},{"51", "U41", "R52B", "L50"},{"52B", "U42", "R53", "L51"},{"53", "U43", "D63H", "L52B"},{"54", "U44D"},{"55", "U45"},{"56", "U46", "R57", "D66H"},{"57", "U47H", "D67", "L56"},
				{"60", "U50", "D70D"},{"61", "R62"},{"62", "R63H", "D72", "L61"},{"63H", "U53", "R64", "D73", "L62"},{"64", "R65", "L63H"},{"65", "R66H", "L64"},{"66H", "U56", "R67", "L65"},{"67", "U57", "L66H"},
				{"70D", "U60", "R71", "D00", "L77"},{"71", "R72", "D01", "L70D"},{"72", "U62", "L71"},{"73", "U63H", "D03"},{"74", "R75B", "D04B"},{"75B", "D05", "L74"},{"76", "R77", "D06"},{"77", "R70D", "L76"}
		};
	private final String[][] mazeCDH =
		{
				{"00", "U70", "D10"},{"01", "R02H", "D11"},{"02H", "U72", "R03", "L01"},{"03", "D13", "L02H"},{"04", "R05D", "D14C"},{"05D", "U75", "R06", "L04"},{"06", "D16", "L05D"},{"07", "U77C", "D17"},
				{"10", "U00", "D20"},{"11", "U01", "D21"},{"12", "D22"},{"13", "U03", "R14C", "D23H"},{"14C", "U04", "D24", "L13"},{"15", "D25"},{"16", "U06", "D26"},{"17", "U07", "D27D"},
				{"20", "U10", "R21", "D30H", "L27D"},{"21", "U11", "L20"},{"22", "U12", "R23H", "D32"},{"23H", "U13", "R24", "D33", "L22"},{"24", "U14C", "R25", "D34", "L23H"},{"25", "U15", "D35D", "L24"},{"26", "U16", "R27D"},{"27D", "U17", "R20", "D37", "L26"},
				{"30H", "U20", "R31", "D40", "L37"},{"31", "R32", "L30H"},{"32", "U22", "D42C", "L31"},{"33", "U23H", "D43"},{"34", "U24", "D44"},{"35D", "U25", "R36", "D45"},{"36", "R37", "L35D"},{"37", "U27D", "R30H", "D47", "L36"},
				{"40", "U30H", "R41", "D50C"},{"41", "R42C", "L40"},{"42C", "U32", "L41"},{"43", "U33", "D53D"},{"44", "U34", "D54"},{"45", "U35D", "R46"},{"46", "R47", "L45"},{"47", "U37", "D57H", "L46"},
				{"50C", "U40", "R51", "D60"},{"51", "R52", "L50C"},{"52", "R53D", "D62", "L51"},{"53D", "U43", "L52"},{"54", "U44", "R55C"},{"55C", "R56", "D65", "L54"},{"56", "R57H", "L55C"},{"57H", "U47", "D67", "L56"},
				{"60", "U50C"},{"61D", "R62", "D71"},{"62", "U52", "R63", "L61D"},{"63", "R64H", "D73", "L62"},{"64H", "R65", "D74", "L63"},{"65", "U55C", "R66", "L64H"},{"66", "D76", "L65"},{"67", "U57H"},
				{"70", "R71", "D00", "L77C"},{"71", "U61D", "L70"},{"72", "R73", "D02H"},{"73", "U63", "L72"},{"74", "U64H", "R75"},{"75", "D05D", "L74"},{"76", "U66", "R77C"},{"77C", "R70", "D07", "L76"}
		};
	private final BombEdgework ew;
	public Maze3D(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		int row = (ew.getSNDIG(0) + ew.numUnlitWithChars("MAZEGAMER")) % 8;
		int col = (ew.getSNDIG(ew.numSNDIGS() - 1) + ew.numLitWithChars("HELPIMLOST")) % 8;
		String input = JOptionPane.showInputDialog("Enter the 3 letters:").toUpperCase();
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 3 letters:").toUpperCase();
			v = v1(input);
		}
		input = order(input, "ABCDH");
		String souv = "MARKINGS: " + input.charAt(0) + " " + input.charAt(1) + " " + input.charAt(2) + "\nCARDINAL: ";
		String[][] maze;
		ArrayList<String> stars = new ArrayList<String>();
		switch(input)
		{
			case "ABC":
				maze = mazeABC;
				stars.add("11");
				stars.add("34");
				stars.add("60");
				break;
			case "ABD":
				maze = mazeABD;
				stars.add("07");
				stars.add("42");
				stars.add("74");
				break;
			case "ABH":
				maze = mazeABH;
				stars.add("10");
				stars.add("34");
				stars.add("65");
				break;
			case "ACD":
				maze = mazeACD;
				stars.add("15");
				stars.add("21");
				stars.add("55");
				break;
			case "ACH":
				maze = mazeACH;
				stars.add("10");
				stars.add("26");
				stars.add("51");
				break;
			case "ADH":
				maze = mazeADH;
				stars.add("05");
				stars.add("21");
				stars.add("50");
				break;
			case "BCD":
				maze = mazeBCD;
				stars.add("16");
				stars.add("21");
				stars.add("64");
				break;
			case "BCH":
				maze = mazeBCH;
				stars.add("22");
				stars.add("34");
				stars.add("53");
				break;
			case "BDH":
				maze = mazeBDH;
				stars.add("13");
				stars.add("24");
				stars.add("67");
				break;
			default:
				maze = mazeCDH;
				stars.add("15");
				stars.add("60");
				stars.add("66");
				break;
		}
		String coord = JOptionPane.showInputDialog("If you know the coordinate\nEnter the coordinate:").toUpperCase();
		v = v3(coord);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			coord = JOptionPane.showInputDialog("If you know the coordinate\nEnter the coordinate:").toUpperCase();
			v = v3(coord);
		}
		input = JOptionPane.showInputDialog("- - Blank\nEnter a line:").toUpperCase();
		input = getPos(maze, input);
		while(input.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("- - Blank\nEnter a line:").toUpperCase();
			input = getPos(maze, input);
		}
		String facing = input.split(" ")[0];
		String cur = input.split(" ")[1];
		MTK mtk = new MTK();
		if(coord.length() == 0)
		{
			String[] s1 = {mtk.getMazeSolution(maze, 1, cur, stars.get(0)), mtk.getMazeSolution(maze, 1, cur, stars.get(1)), mtk.getMazeSolution(maze, 1, cur, stars.get(2))};
			int best = s1[0].length();
			int bestcur = 0;
			for(int aa = 1; aa < 3; aa++)
			{
				if(s1[aa].length() < best)
				{
					best = stars.get(aa).length();
					bestcur = aa;
				}
			}
			input = getMoves(facing, s1[bestcur], cur, maze, stars);
			cur = stars.get(bestcur).toUpperCase();
			facing = input.substring(0, 1);
			String out = getOuput(input.substring(1));
			input = JOptionPane.showInputDialog("Follow these moves:\n" + out + "\nThen enter the coordinate:").toUpperCase();
			v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Follow these moves:\n" + out + "\nThen enter the coordinate:").toUpperCase();
				v = v2(input);
			}
			coord = input.toUpperCase();
		}
		souv = souv + "" + coord.toUpperCase();
		coord = coord.replace('N', 'U').replace('E', 'R').replace('S', 'D').replace('W', 'L');
		String[] goals = getGoals(maze, coord, row, col);
		String[] solutions = {mtk.getMazeSolution(maze, 1, cur, goals[0]) + "" + coord, mtk.getMazeSolution(maze, 1, cur, goals[1]) + "" + "URDL".charAt(("URDL".indexOf(coord) + 2) % 4)};
		if(solutions[0].length() < solutions[1].length())
			JOptionPane.showMessageDialog(null, getOuput(getMoves(facing, solutions[0], cur, maze, stars).substring(1)));
		else
			JOptionPane.showMessageDialog(null, getOuput(getMoves(facing, solutions[1], cur, maze, stars).substring(1)));
		return souv;
	}
	private int getCursor(String[][] maze, String c)
	{
		for(int aa = 0; aa < maze.length; aa++)
		{
			if(maze[aa][0].equals(c))
				return aa;
		}
		return -1;
	}
	private String getPos(String[][] maze, String l)
	{
		ArrayList<String> prevStart = new ArrayList<String>();
		ArrayList<String> allPoss = new ArrayList<String>();
		for(int aa = 0; aa < maze.length; aa++)
		{
			String dir = "URDL";
			for(int dirCur = 0; dirCur < 2; dirCur++)
			{
				boolean flag = true;
				int cur = aa + 0;
				while(flag)
				{
					flag = false;
					for(int bb = 1; bb < maze[cur].length; bb++)
					{
						if(maze[cur][bb].charAt(0) == dir.charAt((dirCur + 2) % 4))
						{
							flag = true;
							cur = getCursor(maze, maze[cur][bb].substring(1));
							break;
						}
					}
				}
				String check = dir.charAt(dirCur) + "" + maze[cur][0];
				if(!(prevStart.contains(check)))
				{
					prevStart.add(check.toUpperCase());
					flag = true;
					String line;
					if(maze[cur][0].length() == 2)
						line = "-";
					else
						line = "" + maze[cur][0].charAt(2);
					String revline = "";
					while(flag)
					{
						flag = false;
						for(int bb = 1; bb < maze[cur].length; bb++)
						{
							if(maze[cur][bb].charAt(0) == dir.charAt(dirCur))
							{
								flag = true;
								if(maze[cur][bb].length() == 3)
									line = line + "-";
								else
									line = line + "" + maze[cur][bb].charAt(3);
								cur = getCursor(maze, maze[cur][bb].substring(1));
								break;
							}
						}
					}
					for(int bb = 0; bb < line.length(); bb++)
						revline = line.charAt(bb) + "" + revline;
					if(line.equals(l))
					{
						if(dirCur == 0)
							allPoss.add("N " + maze[cur][0]);
						else
							allPoss.add("E " + maze[cur][0]);
					}
					if(revline.equals(l))
					{
						if(dirCur == 0)
							allPoss.add("S " + check.substring(1));
						else
							allPoss.add("W " + check.substring(1));
					}
				}
			}
		}
		if(allPoss.size() == 1)
			return allPoss.get(0);
		else
			return "";
	}
	private String getMoves(String f, String d, String cur, String[][] maze, ArrayList<String> stars)
	{
		String moves = "";
		int row = "01234567".indexOf(cur.charAt(0));
		int col = "01234567".indexOf(cur.charAt(1));
		for(int aa = 0; aa < d.length(); aa++)
		{
			switch(d.charAt(aa))
			{
				case 'U':
					switch(f)
					{
						case "N":
							moves = moves + "F";
							break;
						case "E":
							moves = moves + "LF";
							f = "N";
							break;
						case "S":
							moves = moves + "RRF";
							f = "N";
							break;
						case "W":
							moves = moves + "RF";
							f = "N";
							break;
					}
					f = "N";
					row = mod(row - 1, 8);
					break;
				case 'R':
					switch(f)
					{
						case "N":
							moves = moves + "RF";
							break;
						case "E":
							moves = moves + "F";
							break;
						case "S":
							moves = moves + "LF";
							break;
						case "W":
							moves = moves + "RRF";
							break;
					}
					f = "E";
					col = mod(col + 1, 8);
					break;
				case 'D':
					switch(f)
					{
						case "N":
							moves = moves + "RRF";
							break;
						case "E":
							moves = moves + "RF";
							break;
						case "S":
							moves = moves + "F";
							break;
						case "W":
							moves = moves + "LF";
							break;
					}
					f = "S";
					row = mod(row + 1, 8);
					break;
				case 'L':
					switch(f)
					{
						case "N":
							moves = moves + "LF";
							break;
						case "E":
							moves = moves + "RRF";
							break;
						case "S":
							moves = moves + "RF";
							break;
						case "W":
							moves = moves + "F";
							break;
					}
					f = "W";
					col = mod(col - 1, 8);
					break;
			}
			if(aa < d.length() - 1)
			{
				if(maze[(row * 8) + col][0].length() == 3)
					moves = moves + "" + maze[(row * 8) + col][0].charAt(2); 
				else if(stars.contains(maze[(row * 8) + col][0]))
					moves = moves + "Z";
			}
		}
		return (f + "" + moves);
	}
	private String[] getGoals(String[][] maze, String coord, int row, int col)
	{
		String[] goal = {maze[(row * 8) + col][0], ""};
		boolean flag;
		do
		{
			flag = false;
			int c = getCursor(maze, goal[0]);
			for(int bb = 1; bb < maze[c].length; bb++)
			{
				if(maze[c][bb].charAt(0) == coord.charAt(0))
				{
					flag = true;
					goal[0] = maze[c][bb].substring(1);
				}
			}
		}while(flag);
		row = "01234567".indexOf(goal[0].charAt(0));
		col = "01234567".indexOf(goal[0].charAt(1));
		switch(coord)
		{
			case "U":
				row = mod(row - 1, 8);
				break;
			case "R":
				col = mod(col + 1, 8);
				break;
			case "D":
				row = mod(row + 1, 8);
				break;
			case "L":
				col = mod(col - 1, 8);
				break;
		}
		goal[1] = maze[(row * 8) + col][0].toUpperCase();
		return goal;
	}
	private String order(String i, String alpha)
	{
		String conv = "";
		for(int aa = 0; aa < alpha.length(); aa++)
		{
			if(i.indexOf(alpha.charAt(aa)) >= 0)
				conv = conv + "" + alpha.charAt(aa);
		}
		return conv;
	}
	private String getOuput(String i)
	{
		String out = "";
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("FRL".indexOf(i.charAt(aa)) >= 0)
				out = out + "" + i.charAt(aa) + " ";
			else if(i.charAt(aa) == 'Z')
				out = out + "*\n";
			else
				out = out + "" + i.charAt(aa) + "\n";
		}
		return out;
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private boolean v1(String i)
	{
		if(i.length() == 3) 
		{
			String conv = order(i, "ABCDH");
			switch(conv)
			{
				case "ABC":
				case "ABD":
				case "ABH":
				case "ACD":
				case "ACH":
				case "ADH":
				case "BCD":
				case "BCH":
				case "BDH":
				case "CDH":
					return true;
			}
		}
		return false;
	}
	private boolean v2(String i)
	{
		switch(i)
		{
			case "N":
			case "E":
			case "W":
			case "S":
				return true;
			default:
				return false;
		}
	}
	private boolean v3(String i)
	{
		switch(i)
		{
			case "N":
			case "E":
			case "W":
			case "S":
			case "":
				return true;
			default:
				return false;
		}
	}
}
