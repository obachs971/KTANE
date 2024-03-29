package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import misc.MTK;

public class Hexamaze 
{
	private final String[][] hexamaze =
		{
				{"0012"},
				{"0111", "20212"},{"0113"},
				{"0210"},{"0212", "40311", "50111"},{"0214"},
				{"0309"},{"0311", "10212", "30511"},{"0313", "30513"},{"0315"},
				{"0408"},{"0410", "30610", "40509"},{"0412", "30612", "40511"},{"0414", "20515", "30614"},{"0416", "40515"},
				{"0507", "20608"},{"0509", "10410", "40608"},{"0511", "00311", "10412", "40610"},{"0513", "00313", "30713"},{"0515", "10416", "20616", "50414"},{"0517"},
				{"0606"},{"0608", "10509", "40707", "50507"},{"0610", "00410", "10511", "40709"},{"0612", "00412", "20713", "40711"},{"0614", "00414", "40713"},{"0616", "20717", "50515"},{"0618"},
				{"0705"},{"0707", "10608", "30907", "40806"},{"0709", "10610", "40808"},{"0711", "10612", "40810"},{"0713", "00513", "10614", "20814", "50612"},{"0715", "30915"},{"0717", "20818", "50616"},{"0719", "30919"},
				{"0804", "31004"},{"0806", "10707", "40905"},{"0808", "10709", "20909"},{"0810", "10711", "40909"},{"0812", "20913", "40911"},{"0814", "31014", "40913", "50713"},{"0816", "31016", "40915"},{"0818", "40917", "50717"},{"0820"},
				{"0903"},{"0905", "10806", "21006"},{"0907", "00707", "21008"},{"0909", "10810", "31109", "50808"},{"0911", "10812", "31111R"},{"0913", "10814", "41012", "50812"},{"0915", "00715", "10816", "41014"},{"0917", "10818", "21018", "41016"},{"0919", "00719", "41018"},{"0921"},
				{"1002"},{"1004", "00804", "21105", "41103"},{"1006", "21107", "50905"},{"1008", "21109", "50907"},{"1010", "21111R", "31210"},{"1012", "10913", "41111R"},{"1014", "00814", "10915", "41113"},{"1016", "00816", "10917", "41115"},{"1018", "10919", "41117", "50917"},{"1020", "31220"},{"1022"},
				{"1101"},{"1103", "11004"},{"1105", "21206", "51004"},{"1107", "31307", "51006"},{"1109", "00909", "31309", "51008"},{"1111R", "00911", "11012", "21212", "51010"},{"1113", "11014", "31313"},{"1115", "11016", "41214U"},{"1117", "11018", "31317"},{"1119", "21220", "41218"},{"1121", "21222", "31321"},{"1123", "31323"},
				{"1200", "21301"},{"1202", "31402", "41301"},{"1204", "21305"},{"1206", "21307", "51105"},{"1208", "21309", "41307"},{"1210", "01010", "21311", "31410", "41309"},{"1212", "21313", "51111R"},{"1214U", "11115", "21315", "41313"},{"1216", "21317", "41315"},{"1218", "11119", "31418L"},{"1220", "01020", "21321", "51119"},{"1222", "21323", "51121"},{"1224"},
				{"1301", "11202", "51200"},{"1303", "21404"},{"1305", "21406", "51204"},{"1307", "01107", "11208", "41406", "51206"},{"1309", "01109", "11210", "41408", "51208"},{"1311", "21412", "31511", "51210"},{"1313", "01113", "11214U", "21414", "51212"},{"1315", "11216", "31515", "51214U"},{"1317", "01117", "21418L", "51216"},{"1319", "21420", "31519"},{"1321", "01121", "21422", "51220"},{"1323", "01123", "51222"},
				{"1400"},{"1402", "01202", "31602", "41501"},{"1404", "21505", "51303"},{"1406", "11307", "31606", "51305"},{"1408", "11309", "41507"},{"1410", "01210", "31610"},{"1412", "21513", "31612", "51311"},{"1414", "31614", "51313"},{"1416", "21517", "41515"},{"1418L", "01218", "21519", "31618", "51317"},{"1420", "21521", "51319"},{"1422", "21523", "51321"},{"1424"},
				{"1501", "11402"},{"1503", "21604"},{"1505", "21606", "51404"},{"1507", "11408", "31707"},{"1509D", "21610", "31709", "41608"},{"1511", "01311", "31711"},{"1513", "21614", "31713", "51412"},{"1515", "01315", "11416", "31715"},{"1517", "31717", "51416"},{"1519", "01319", "31719", "51418L"},{"1521", "21622", "51420"},{"1523", "41622", "51422"},
				{"1600"},{"1602", "01402", "31802"},{"1604", "21705", "31804", "41703", "51503"},{"1606", "01406", "21707", "51505"},{"1608", "11509D", "41707"},{"1610", "01410", "21711", "51509D"},{"1612", "01412", "31812"},{"1614", "01414", "21715", "51513"},{"1616", "31816", "41715"},{"1618", "01418L", "31818", "41717"},{"1620", "41719"},{"1622", "11523", "41721", "51521"},{"1624"},
				{"1701", "31901"},{"1703", "11604", "31903", "41802"},{"1705", "21806", "51604"},{"1707", "01507", "11608", "41806", "51606"},{"1709", "01509D", "21810", "41808"},{"1711", "01511", "31911", "51610"},{"1713", "01513", "21814", "31913"},{"1715", "01515", "11616", "21816", "51614"},{"1717", "01517", "11618", "41816"},{"1719", "01519", "11620", "31919"},{"1721", "11622", "21822", "41820"},{"1723", "41822"},
				{"1800"},{"1802", "01602", "11703", "41901"},{"1804", "01604", "32004"},{"1806", "11707", "41905", "51705"},{"1808", "11709", "21909", "41907"},{"1810", "21911", "51709"},{"1812", "01612", "32012"},{"1814", "21915", "51713"},{"1816", "01616", "11717", "32016", "51715"},{"1818", "01618", "41917C"},{"1820", "11721", "21921", "41919"},{"1822", "11723", "21923", "51721"},{"1824", "41923"},
				{"1901", "01701", "11802"},{"1903", "01703", "32103", "42002"},{"1905", "11806", "22006C"},{"1907", "11808", "22008", "32107", "42006C"},{"1909", "22010", "51808"},{"1911", "01711", "32111", "51810"},{"1913", "01713", "22014", "42012"},{"1915", "22016", "51814"},{"1917C", "11818", "32117", "42016"},{"1919", "01719", "11820", "42018"},{"1921", "42020", "51820"},{"1923", "11824", "32123", "51822"},
				{"2000"},{"2002", "11903", "42101"},{"2004", "01804", "22105"},{"2006C", "11907", "32206", "51905"},{"2008", "22109", "32208", "51907"},{"2010", "22111", "51909"},{"2012", "01812", "11913", "42111"},{"2014", "22115", "32214", "42113", "51913"},{"2016", "01816", "11917C", "32216", "51915"},{"2018", "11919", "42117"},{"2020", "11921", "22121", "32220", "42119"},{"2022", "42121"},{"2024"},
				{"2101", "12002", "42200"},{"2103", "01903", "22204", "42202"},{"2105", "22206", "52004"},{"2107", "01907", "32307"},{"2109", "22210", "52008"},{"2111", "01911", "12012", "22212", "32311", "52010"},{"2113", "12014", "32313", "42212"},{"2115", "22216", "32315H", "52014"},{"2117", "01917C", "12018", "32317"},{"2119", "12020", "42218"},{"2121", "12022", "52020"},{"2123", "01923", "42222"},
				{"2200", "12101"},{"2202", "12103", "32402", "42301"},{"2204", "22305", "32404", "52103"},{"2206", "02006C", "32406", "52105"},{"2208", "02008", "22309U", "32408"},{"2210", "22311", "52109"},{"2212", "12113", "32412H", "52111"},{"2214", "02014", "32414"},{"2216", "02016", "32416", "52115"},{"2218", "12119", "42317"},{"2220", "02020", "42319"},{"2222", "12123", "42321"},{"2224"},
				{"2301", "12202", "32501"},{"2303H", "22404"},{"2305", "22406", "52204"},{"2307", "02107", "32507"},{"2309U", "22410", "52208"},{"2311", "02111", "32511", "52210"},{"2313", "02113", "22414", "32513"},{"2315H", "02115", "22416", "42414"},{"2317", "02117", "12218", "42416"},{"2319", "12220", "22420H", "42418"},{"2321", "12222", "42420H"},{"2323", "42422"},
				{"2400"},{"2402", "02202", "32602"},{"2404", "02204", "32604", "52303H"},{"2406", "02206", "32606C", "42505", "52305"},{"2408", "02208", "32608", "42507"},{"2410", "22511", "42509", "52309U"},{"2412H", "02212", "32612"},{"2414", "02214", "12315H", "22515", "52313"},{"2416", "02216", "12317", "22517", "52315H"},{"2418", "12319", "22519", "42517"},{"2420H", "12321", "32620", "52319"},{"2422", "12323", "42521"},{"2424"},
				{"2501", "02301", "32701"},{"2503", "22604", "42602"},{"2505", "12406", "22606C"},{"2507", "02307", "12408", "42606C"},{"2509", "12410", "42608"},{"2511", "02311", "32711", "42610", "52410"},{"2513", "02313", "22614", "42612"},{"2515", "22616", "42614", "52414"},{"2517", "12418", "22618", "32717D", "52416"},{"2519", "22620", "52418"},{"2521", "12422", "22622", "42620"},{"2523", "42622"},
				{"2600", "22701"},{"2602", "02402", "12503"},{"2604", "02404", "22705", "52503"},{"2606C", "02406", "12507", "42705", "52505"},{"2608", "02408", "12509", "42707"},{"2610", "12511", "42709"},{"2612", "02412H", "12513", "22713", "32812"},{"2614", "12515", "22715", "52513"},{"2616", "22717D", "52515"},{"2618", "22719", "52517"},{"2620", "02420H", "12521", "22721", "52519"},{"2622", "12523", "52521"},{"2624", "42723"},
				{"2701", "02501", "32901", "52600"},{"2703", "22804", "42802"},{"2705", "12606C", "42804", "52604"},{"2707", "12608", "32907", "42806"},{"2709", "12610", "42808"},{"2711", "02511", "22812", "42810"},{"2713", "22814", "32913", "52612"},{"2715", "22816", "52614"},{"2717D", "02517", "22818", "52616"},{"2719", "22820", "32919", "52618"},{"2721", "42820", "52620"},{"2723", "12624", "42822"},
				{"2800"},{"2802", "12703", "42901"},{"2804", "12705", "33004L", "42903", "52703"},{"2806", "12707", "42905"},{"2808", "12709", "42907"},{"2810", "12711", "22911", "42909"},{"2812", "02612", "33012", "52711"},{"2814", "22915", "52713"},{"2816", "22917", "33016", "52715"},{"2818", "42917", "52717D"},{"2820", "12721", "33020", "52719"},{"2822", "12723", "42921"},{"2824"},
				{"2901", "02701", "12802", "33101"},{"2903", "12804", "43002"},{"2905", "12806", "43004L"},{"2907", "02707", "12808", "23008", "43006"},{"2909", "12810", "43008"},{"2911", "23012", "43010", "52810"},{"2913", "02713", "23014", "33113"},{"2915", "23016", "33115", "43014", "52814"},{"2917", "12818", "23018", "33117", "52816"},{"2919", "02719", "33119", "43018"},{"2921", "12822", "23022", "33121", "43020"},{"2923", "23024", "43022"},
				{"3000"},{"3002", "12903"},{"3004L", "02804", "12905", "33204"},{"3006", "12907", "43105"},{"3008", "12909", "43107", "52907"},{"3010", "12911", "43109"},{"3012", "02812", "33212", "43111", "52911"},{"3014", "12915", "33214", "52913"},{"3016", "02816", "23117", "33216", "52915"},{"3018", "12919", "52917"},{"3020", "02820", "12921", "33220"},{"3022", "12923", "33222", "52921"},{"3024", "52923"},
				{"3101", "02901", "33301"},{"3103", "23204"},{"3105", "13006", "33305"},{"3107", "13008", "23208", "43206"},{"3109", "13010", "23210", "33309", "43208"},{"3111", "13012", "43210"},{"3113", "02913", "33313"},{"3115", "02915", "23216", "33315", "43214"},{"3117", "02917", "53016"},{"3119", "02919", "23220", "33319", "43218"},{"3121", "02921", "33321"},{"3123", "43222"},
				{"3200"},{"3202", "23303"},{"3204", "03004L", "33404", "53103"},{"3206", "13107", "23307", "43305"},{"3208", "13109", "33408", "53107"},{"3210", "13111", "23311R", "53109"},{"3212", "03012", "33412"},{"3214", "03014", "13115", "33414C"},{"3216", "03016", "53115"},{"3218", "13119", "43317"},{"3220", "03020", "53119"},{"3222", "03022", "13123"},{"3224"},
				{"3301", "03101", "43400"},{"3303", "23404", "33503", "53202"},{"3305", "03105", "13206"},{"3307", "23408", "33507H", "43406", "53206"},{"3309", "03109", "33509"},{"3311R", "23412", "43410", "53210"},{"3313", "03113", "33513"},{"3315", "03115", "33515"},{"3317", "13218", "43416"},{"3319", "03119", "23420", "33519", "43418"},{"3321", "03121", "33521"},{"3323", "43422"},
				{"3400", "13301"},{"3402", "23503", "43501"},{"3404", "03204", "23505", "53303"},{"3406", "13307", "33606", "43505"},{"3408", "03208", "33608", "53307"},{"3410", "13311R", "23511", "43509"},{"3412", "03212", "23513", "53311R"},{"3414C", "03214", "43513"},{"3416", "13317", "23517R", "33616", "43515"},{"3418", "13319", "33618", "43517R"},{"3420", "23521", "53319"},{"3422", "13323", "33622", "43521"},{"3424"},
				{"3501", "13402"},{"3503", "03303", "43602", "53402"},{"3505", "13406", "43604", "53404"},{"3507H", "03307", "33707"},{"3509", "03309", "13410", "43608"},{"3511", "23612", "33711", "43610", "53410"},{"3513", "03313", "13414C", "23614", "33713", "53412"},{"3515", "03315", "13416", "43614"},{"3517R", "13418", "53416"},{"3519", "03319", "23620", "33719"},{"3521", "03321", "13422", "53420"},{"3523", "43622"},
				{"3600"},{"3602", "13503"},{"3604", "13505", "33804"},{"3606", "03406", "33806", "43705"},{"3608", "03408", "13509"},{"3610", "13511", "33810C", "43709"},{"3612", "23713", "33812", "53511"},{"3614", "13515", "23715", "33814", "53513"},{"3616", "03416", "23717", "33816"},{"3618", "03418", "33818"},{"3620", "33820", "53519"},{"3622", "03422", "13523"},{"3624"},
				{"3701"},{"3703", "23804"},{"3705", "13606"},{"3707", "03507H", "33907"},{"3709", "13610", "33909", "43808"},{"3711", "03511"},{"3713", "03513", "33913", "53612"},{"3715", "23816", "33915", "53614"},{"3717", "53616"},{"3719", "03519"},{"3721", "23822", "43820"},{"3723"},
				{"3802"},{"3804", "03604", "23905", "34004", "53703"},{"3806", "03606"},{"3808", "13709", "34008", "43907"},{"3810C", "03610", "23911", "34010"},{"3812", "03612", "34012", "43911"},{"3814", "03614", "34014"},{"3816", "03616", "23917", "53715"},{"3818", "03618", "23919"},{"3820", "03620", "13721"},{"3822", "53721"},
				{"3903"},{"3905", "24006", "53804"},{"3907", "03707", "13808"},{"3909", "03709"},{"3911", "13812", "34111", "53810C"},{"3913", "03713"},{"3915", "03715", "24016", "34115"},{"3917", "53816"},{"3919", "44018", "53818"},{"3921"},
				{"4004", "03804"},{"4006", "24107", "34206", "53905"},{"4008", "03808", "34208", "44107"},{"4010", "03810C", "44109"},{"4012", "03812", "34212"},{"4014", "03814", "44113"},{"4016", "24117", "34216", "53915"},{"4018", "13919", "44117"},{"4020"},
				{"4105"},{"4107", "14008", "54006"},{"4109", "14010", "34309"},{"4111", "03911", "34311", "44210"},{"4113", "14014", "24214", "44212"},{"4115", "03915"},{"4117", "14018", "34317", "54016"},{"4119"},
				{"4206", "04006"},{"4208", "04008"},{"4210", "14111"},{"4212", "04012", "14113", "34412"},{"4214", "24315", "34414", "44313", "54113"},{"4216", "04016"},{"4218"},
				{"4307"},{"4309", "04109", "24410", "44408"},{"4311", "04111", "34511"},{"4313", "14214"},	{"4315", "54214"},{"4317", "04117"},
				{"4408", "14309"},{"4410", "54309"},{"4412", "04212"},{"4414", "04214", "24515", "44513"},{"4416"},
				{"4509"},{"4511", "04311", "24612"},{"4513", "14414"},{"4515", "54414"},
				{"4610"},{"4612", "54511"},	{"4614"},
				{"4711"},{"4713"},
				{"4812"}
		};
	public String run()
	{
		//Setting up the maze
		int counter = 1;
		String template = "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN";
		while(true)
		{
			String input = JOptionPane.showInputDialog("C - Circle\nURDL - Triangle\nH - Hexagon\nEnter the shape info of shape #" + (counter) + "\n(leave it blank to stop):").toUpperCase().replace(" ", "");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("C - Circle\nURDL - Triangle\nH - Hexagon\nEnter the shape info of shape #" + (counter) + "\n(leave it blank to stop):").toUpperCase().replace(" ", "");
				v = v1(input);
			}
			if(input.length() == 0)
				break;
			int cur = getPosition(input.substring(1));
			template = template.substring(0, cur) + "" + input.charAt(0) + "" + template.substring(cur + 1);
			counter++;
		}
		int[] mazeInfo = getMazePosition(template);
		while(mazeInfo == null)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			counter = 1;
			template = "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN";
			while(true)
			{
				String input = JOptionPane.showInputDialog("C - Circle\nURDL - Triangle\nH - Hexagon\nEnter the shape info of shape #" + (counter) + "\n(leave it blank to stop):").toUpperCase().replace(" ", "");
				boolean v = v1(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("C - Circle\nURDL - Triangle\nH - Hexagon\nEnter the shape info of shape #" + (counter) + "\n(leave it blank to stop):").toUpperCase().replace(" ", "");
					v = v1(input);
				}
				if(input.length() == 0)
					break;
				int cur = getPosition(input.substring(1));
				template = template.substring(0, cur) + "" + input.charAt(0) + "" + template.substring(cur + 1);
				counter++;
			}
			mazeInfo = getMazePosition(template);
		}
		String[][] maze = getMaze(mazeInfo[0], mazeInfo[1]);
		//System.out.println(maze[0][0]);
		//Get the pawn's info
		String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nP - Pink\nC - Cyan\nEnter the pawn's color/position:").toUpperCase().replace(" ", "");
		boolean v = v2(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nP - Pink\nC - Cyan\nEnter the pawn's color/position:").toUpperCase().replace(" ", "");
			v = v2(input);
		}
		//Setting up the current position to get solved
		String current = getPawnPosition(maze, mazeInfo[2], input.substring(1));
		//System.out.println(current);
		
		//Getting the list of goals
		ArrayList<String> goals = new ArrayList<String>();
		String souv = "PAWN COLOR: ";
		//System.out.println(maze[0][0]);
		switch(input.charAt(0))
		{
			case 'R':
				goals.add(maze[0][0].toUpperCase());
				goals.add(maze[1][0].toUpperCase());
				goals.add(maze[3][0].toUpperCase());
				goals.add(maze[6][0].toUpperCase());
				goals.add(maze[10][0].toUpperCase());
				souv = souv + "RED";
				break;
			case 'Y':
				goals.add(maze[0][0].toUpperCase());
				goals.add(maze[2][0].toUpperCase());
				goals.add(maze[5][0].toUpperCase());
				goals.add(maze[9][0].toUpperCase());
				goals.add(maze[14][0].toUpperCase());
				souv = souv + "YELLOW";
				break;
			case 'G':
				goals.add(maze[14][0].toUpperCase());
				goals.add(maze[23][0].toUpperCase());
				goals.add(maze[32][0].toUpperCase());
				goals.add(maze[41][0].toUpperCase());
				goals.add(maze[50][0].toUpperCase());
				souv = souv + "GREEN";
				break;
			case 'C':
				goals.add(maze[50][0].toUpperCase());
				goals.add(maze[54][0].toUpperCase());
				goals.add(maze[57][0].toUpperCase());
				goals.add(maze[59][0].toUpperCase());
				goals.add(maze[60][0].toUpperCase());
				souv = souv + "CYAN";
				break;
			case 'B':
				goals.add(maze[46][0].toUpperCase());
				goals.add(maze[51][0].toUpperCase());
				goals.add(maze[55][0].toUpperCase());
				goals.add(maze[58][0].toUpperCase());
				goals.add(maze[60][0].toUpperCase());
				souv = souv + "BLUE";
				break;
			case 'P':
				goals.add(maze[10][0].toUpperCase());
				goals.add(maze[19][0].toUpperCase());
				goals.add(maze[28][0].toUpperCase());
				goals.add(maze[37][0].toUpperCase());
				goals.add(maze[46][0].toUpperCase());
				souv = souv + "PINK";
				break;
		}
		//Time to get the best solution for the maze
		
		MTK mtk = new MTK();
		String solution = mtk.getMazeSolution(maze, 1, current, goals);
		
		/*
		 * Now it's time to rotate the directions of the best solution
		 * And since they are numbers, we're just gonna add the
		 * number of times needed, mod 6
		 */
		String[] dir = {"N", "NE", "SE", "S", "SW", "NW"};
		String out = "";
		for(int aa = 0; aa < solution.length(); aa++)
		{
			out = out + "" + dir[mod("012345".indexOf(solution.charAt(aa)) - mazeInfo[2], 6)] + " ";
			if((aa + 1) % 4 == 0)
				out = out + "\n";
		}
		JOptionPane.showMessageDialog(null, out);
		return souv;
	}
	private int getPosition(String i)
	{
		String[][] indexes =
			{
					                   {"D1", "41"},
					             {"C1", "31"},{"E1", "51"},
				          {"B1", "21"},{"D2", "42"},{"F1", "61"},
					{"A1", "11"},{"C2", "32"},{"E2", "52"},{"G1", "71"},
					      {"B2", "22"},{"D3", "43"},{"F2", "62"},
					{"A2", "12"},{"C3", "33"},{"E3", "53"},{"G2", "72"},
					      {"B3", "23"},{"D4", "44"},{"F3", "63"},
					{"A3", "13"},{"C4", "34"},{"E4", "54"},{"G3", "73"},
					      {"B4", "24"},{"D5", "45"},{"F4", "64"},
					{"A4", "14"},{"C5", "35"},{"E5", "55"},{"G4", "74"},
					      {"B5", "25"},{"D6", "46"},{"F5", "65"},
					             {"C6", "36"},{"E6", "56"},
					                   {"D7", "47"}
			};
		for(int aa = 0; aa < indexes.length; aa++)
		{
			for(int bb = 0; bb < indexes[aa].length; bb++)
			{
				if(indexes[aa][bb].equals(i))
					return aa;
			}
		}
		return -1;
	}
	//Returns the position you are in the maze
	private int[] getMazePosition(String template)
	{
		int[][] spaces = 
		{
				{12},
				{11, 13},
				{10, 12, 14},
				{9, 11, 13, 15},
				{8, 10, 12, 14, 16},
				{7, 9, 11, 13, 15, 17},
				{6, 8, 10, 12, 14, 16, 18},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{4, 6, 8, 10, 12, 14, 16, 18, 20},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{4, 6, 8, 10, 12, 14, 16, 18, 20},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{4, 6, 8, 10, 12, 14, 16, 18, 20},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{4, 6, 8, 10, 12, 14, 16, 18, 20},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{4, 6, 8, 10, 12, 14, 16, 18, 20},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{4, 6, 8, 10, 12, 14, 16, 18, 20},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{4, 6, 8, 10, 12, 14, 16, 18, 20},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{4, 6, 8, 10, 12, 14, 16, 18, 20},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{4, 6, 8, 10, 12, 14, 16, 18, 20},
				{5, 7, 9, 11, 13, 15, 17, 19},
				{6, 8, 10, 12, 14, 16, 18},
				{7, 9, 11, 13, 15, 17},
				{8, 10, 12, 14, 16},
				{9, 11, 13, 15},
				{10, 12, 14},
				{11, 13},
				{12}
		};
		int[][] offsets =
			{
					{0},
					{-1, 1},
					{-2, 0, 2},
					{-3, -1, 1, 3},
					{-2, 0, 2},
					{-3, -1, 1, 3},
					{-2, 0, 2},
					{-3, -1, 1, 3},
					{-2, 0, 2},
					{-3, -1, 1, 3},
					{-2, 0, 2},
					{-1, 1},
					{0}
			};
		for(int aa = 0; aa < spaces.length; aa++)
		{
			for(int bb = 0; bb < spaces[aa].length; bb++)
			{
				String temp = "";
				for(int cc = 0; cc < offsets.length; cc++)
				{
					for(int dd = 0; dd < offsets[cc].length; dd++)
						temp = temp + "" + getSpaceSymbol(aa + cc + 2, spaces[aa][bb] + offsets[cc][dd]);
				}
				//System.out.println(temp);
				for(int cc = 0; cc < 6; cc++)
				{
					if(temp.equals(template))
						return new int[] {aa, spaces[aa][bb], cc};
					temp = rotateTemplate(temp);
				}
			}
		}
		return null;
	}
	//Returns the template that is rotated clockwise
	private String rotateTemplate(String template)
	{
		int[] pos = {
				9,
				5, 16,
				2, 12, 23,
				0, 8, 19, 30,
				4, 15, 26,
				1, 11, 22, 33,
				7, 18, 29,
				3, 14, 25, 35,
				10, 21, 32,
				6, 17, 28, 36,
				13, 24, 34,
				20, 31,
				27
		};
		String temp = "";
		for(int aa = 0; aa < pos.length; aa++)
		{
			switch(template.charAt(pos[aa]))
			{
				case 'U':
					temp = temp + "D";
					break;
				case 'R':
					temp = temp + "L";
					break;
				case 'D':
					temp = temp + "U";
					break;
				case 'L':
					temp = temp + "R";
					break;
				default:
					temp = temp + "" + template.charAt(pos[aa]);
					break;
			}
		}
		return temp;
	}
	//Returns the space's symbol using the row and col number
	private String getSpaceSymbol(int row, int col)
	{
		String temp;
		if(row < 10)
			temp = "0" + row;
		else
			temp = row + "";
		if(col < 10)
			temp = temp + "0" + col;
		else
			temp = temp + "" + col;
		//System.out.println(temp);
		for(int aa = 0; aa < hexamaze.length; aa++)
		{
			if(hexamaze[aa][0].contains(temp))
			{
				if(hexamaze[aa][0].length() > 4)
					return hexamaze[aa][0].substring(4);
				else
					return "N";
			}
		}
		return "N";
	}
	//Returns the maze based on the row and col
	private String[][] getMaze(int row, int col)
	{
		int[][] offsets = {
				{0},
				{-1, 1},
				{-2, 0, 2},
				{-3, -1, 1 ,3},
				{-4, -2, 0, 2, 4},
				{-3, -1, 1, 3},
				{-4, -2, 0, 2, 4},
				{-3, -1, 1, 3},
				{-4, -2, 0, 2, 4},
				{-3, -1, 1, 3},
				{-4, -2, 0, 2, 4},
				{-3, -1, 1, 3},
				{-4, -2, 0, 2, 4},
				{-3, -1, 1, 3},
				{-2, 0, 2},
				{-1, 1},
				{0}
		};
		String[][] maze = new String[61][];
		int cursor = 0;
		for(int aa = 0; aa < offsets.length; aa++)
		{
			for(int bb = 0; bb < offsets[aa].length; bb++)
			{
				maze[cursor] = getSpaceInfo(row + aa, col + offsets[aa][bb]);
				cursor++;
			}
		}
		int[] pos = {0, 1, 2, 3, 5, 6, 9, 10, 14, 19, 23, 28, 32, 37, 41, 46, 50, 51, 54, 55, 57, 58, 59, 60};
		for(int aa = 0; aa < pos.length; aa++)
			maze[pos[aa]] = new String[] {maze[pos[aa]][0]};
		return maze;
	}
	//Returns the info for that space
	private String[] getSpaceInfo(int row, int col)
	{
		String temp;
		if(row < 10)
			temp = "0" + row;
		else
			temp = row + "";
		if(col < 10)
			temp = temp + "0" + col;
		else
			temp = temp + "" + col;
		for(int aa = 0; aa < hexamaze.length; aa++)
		{
			if(hexamaze[aa][0].contains(temp))
				return hexamaze[aa];
		}
		return null;
	}
	//Returns the space name the pawn is currently on
	private String getPawnPosition(String[][] maze, int rotate, String coord)
	{
		String[] temp =
			{
				maze[4][0],
				maze[7][0], maze[8][0],
				maze[11][0], maze[12][0], maze[13][0],
				maze[15][0], maze[16][0], maze[17][0], maze[18][0], 
				maze[20][0], maze[21][0], maze[22][0], 
				maze[24][0], maze[25][0], maze[26][0], maze[27][0], 
				maze[29][0], maze[30][0], maze[31][0], 
				maze[33][0], maze[34][0], maze[35][0], maze[36][0], 
				maze[38][0], maze[39][0], maze[40][0], 
				maze[42][0], maze[43][0], maze[44][0], maze[45][0], 
				maze[47][0], maze[48][0], maze[49][0], 
				maze[52][0], maze[53][0], 
				maze[56][0]
			};
		String template = "0123456789abcdefghijklmnopqrstuvwxyz!";
		char c = template.charAt(getPosition(coord));
		//System.out.println(c);
		for(int aa = 0; aa < (6 - rotate); aa++)
			template = rotateTemplate(template);
		//System.out.println(template);
		return temp[template.indexOf(c)];
	}
	//Validation for entering shape info
	private boolean v1(String i)
	{
		if(i.length() == 0)
			return true;
		if(i.length() == 3)
		{
			if("URDLCH".indexOf(i.charAt(0)) < 0)
				return false;
			if(getPosition(i.substring(1)) < 0)
				return false;
			return true;
		}
		return false;
	}
	//Validation for entering the pawn's info
	private boolean v2(String i)
	{
		if(i.length() == 3)
		{
			if("RBYGPC".indexOf(i.charAt(0)) < 0)
				return false;
			if(getPosition(i.substring(1)) < 0)
				return false;
			return true;
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
