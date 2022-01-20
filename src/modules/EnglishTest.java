package modules;

import javax.swing.JOptionPane;

public class EnglishTest 
{
	public void run()
	{
		for(int aa = 0; aa < 3; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter a word:");
			input = input.toLowerCase();
			String out = getStrings(input);
			while(out.equals("null"))
			{
				JOptionPane.showMessageDialog(null, "ERROR");
				input = JOptionPane.showInputDialog("Enter a word:");
				input = input.toLowerCase();
				out = getStrings(input);
			}
			JOptionPane.showMessageDialog(null, out);
		}
	}
	private String getStrings(String i)
	{
		String message;
		if((i.equals("their")) || (i.equals("there")) || (i.equals("they're")))
		{
			message = "You need to be at THEIR wedding.\n" +
					"He said he'll get THERE in a hurry.\n" +
					"I don't know what THEY'RE thinking.\n" +
					"How much does this count from THEIR point of view?\n" +
					"Look over THERE!\n" +
					"It depends on whether THEY'RE still thinking about it.";
		}
		else if((i.equals("your")) || (i.equals("you're")))
		{
			message = "If you don't do well, YOUR grades will be lowered.\n" +
					"If you don't do well, YOU'RE getting a lower grade.\n" +
					"YOUR pizza is on its way.\n" +
					"YOU'RE delivering the lines quite well.\n" +
					"I borrowed YOUR ballpoint pen.\n" +
					"I saw YOU'RE quite nice.";
		}
		else if((i.equals("she and i")) || (i.equals("her and me")) || (i.equals("he and i")) || (i.equals("him and me")))
		{
			message = "SHE AND I both drink cappuccino.\n" +
					"He recognized HER AND ME in the photo.\n" +
					"HE AND I tried to get the cat out of the tree.\n" +
					"The cat pounced on HIM AND ME to get off the tree.";
		}
		else if((i.equals("he")) || (i.equals("him")) || (i.equals("she")) || (i.equals("her")))
		{
			message = "I can swim as much as HE.\n" +
					"He'd rather go with me than with HIM.\n" +
					"I can run as much as SHE.\n" +
					"She'd rather go with me than with HER.";
		}
		else if((i.equals("less")) || (i.equals("fewer")))
		{
			message = "This recipe needs LESS sugar.\n" +
					"This recipe needs FEWER sugar cubes.\n" +
					"I run LESS than he.\n" +
					"I run FEWER kilometers than he.";
		}
		else if((i.equals("who")) || (i.equals("whom")))
		{
			message = "The detective already knew WHO killed him, but I had no idea.\n" +
					"With WHOM were you texting last night?\n" +
					"WHO is our newest employee?\n" +
					"WHOM did we hire most recently?";
		}
		else if((i.equals("definitely")) || (i.equals("defiantly")))
		{
			message = "Say what you will, but Star Trek is DEFINITELY better than Star Wars!\n" +
					"The teenager DEFIANTLY refused to clean is room.\n" +
					"Yes, we DEFINITELY talked about this before.\n" +
					"She DEFIANTLY stared at him, but stopped herself from yelling at him.";
		}
		else if((i.equals("lead")) || (i.equals("led")))
		{
			message = "This old paint was laced with LEAD.\n" +
					"The metal refinery was LED by professionals.";
		}
		else if((i.equals("site")) || (i.equals("cite")) || (i.equals("sight")))
		{

			message = "You need to CITE that Web page.\n" +
					"You need to include the URL to the SITE you quoted.\n" +
					"Did you SIGHT the bird yet?";
		}
		else if((i.equals("lie")) || (i.equals("lay")) || (i.equals("laid")))
		{
			message = "I watched the cat LIE down on the plush.\n" +
					"The cat LAY on the plush all morning.\n" +
					"Peter should LIE on the ground.\n" +
					"Peter should LAY the box down there.\n" +
					"Peter LAID the box down there.";
		}
		else if((i.equals("of")) || (i.equals("have")))
		{
			message = "You shouldn't HAVE ended the paragraph there.\n" +
					"I just don't know what I should HAVE done to resolve the conflict.\n" +
					"Oh, you should HAVE seen his face when I broke the news to him!\n" +
					"If you told me about it, I would HAVE looked it up.\n" +
					"I wouldn't HAVE done that if I were you.\n" +
					"The boy said that he may or may not HAVE broken the vase.\n" +
					"You might HAVE been a genius all along.\n" +
					"I could HAVE been there if I weren't busy.\n" +
					"He couldn't HAVE known what would be the better idea.\n" +
					"I asked every question I could OF him.";
		}
		else if((i.equals("its")) || (i.equals("it's")))
		{
			message = "ITS wings span five meters.\n" +
					"IT'S a bird with five-meter wings.\n" +
					"The anime's story is bland, but at least ITS effects are good.\n" +
					"The anime's story is bland, but at least IT'S not dubbed.";
		}
		else if((i.equals("capital")) || (i.equals("capitol")))
		{
			message = "Let's travel to the CAPITAL city.\n" +
					"Let's see the view from the CAPITOL.\n" +
					"The CAPITAL of Japan is Tokyo.\n" +
					"Let's visit CAPITOL Hill, Washington D.C.";
		}
		else if((i.equals("effect")) || (i.equals("affect")) || (i.equals("impact")) || (i.equals("affected")) || (i.equals("effected")) || (i.equals("impacted")))
		{
			message = "This will AFFECT our business negatively.\n" +
					"This will have a negative EFFECT on our business.\n" +
					"He sported a snarky AFFECT at the question asked to him.\n" +
					"Don't let him AFFECT you!\n" +
					"Use the low-pass EFFECT to cut out the tinny sound.\n" +
					"You can see from the dent that the boulder IMPACTED the roof of the car.\n" +
					"The IMPACT sound effect sounds a bit too weak.";
		}
		else if((i.equals("i.e.")) || (i.equals("e.g.")))
		{
			message = "For geometric construction you only need two things, I.E. a compass and a straightedge.\n" +
					"Electric devices, E.G. smartphones, should be turned off in the theater.\n" +
					"America's personal savings rate was negative in 2005-I.E. consumers spent more than they made!\n" +
					"Prohibitions of illegal substances (E.G. LSD and meth) has never worked.";
		}
		else if((i.equals("piqued")) || (i.equals("peek")) || (i.equals("peaked")))
		{
			message = "The story PIQUED my interest.\n" +
					"Let's take an exclusive sneak PEEK at the movie!\n" +
					"His glare PEAKED my anxiety.";
		}
		else if((i.equals("a lot")) || (i.equals("allot")) || (i.equals("alot")))
		{
			message = "I think about this A LOT.\n" +
					"A LOT of you have been asking me the same thing.\n" +
					"ALLOT some more time for homework.";
		}
		else if((i.equals("lose")) || (i.equals("loose")))
		{
			message = "My belt is LOOSE.\n" +
					"Don't LOSE your focus.\n" +
					"My favorite Eminem song is LOSE Yourself.\n" +
					"My favorite D4 song is Get LOOSE.";
		}
		else if((i.equals("than")) || (i.equals("then")))
		{
			message = "I'd rather be with you THAN without you.\n" +
				"Tonight I'll eat dinner THEN take a bath.\n" +
					"I like red better THAN yellow.\n" +
					"Paint this one red THEN paint the next one yellow.";
		}
		else if((i.equals("complimented")) || (i.equals("complemented")))
		{
			message = "He COMPLIMENTED her eyes.\n" +
					"Her eyeshadow COMPLEMENTED her eyes.";
		}
		else if((i.equals("farther")) || (i.equals("further")))
		{
			message = "The batter hit the ball FARTHER than the last batter did.\n" +
					"I don't know how to get FURTHER past this level.\n" +
					"No FURTHER comment has been provided.\n" +
					"My house is FARTHER from the school than yours.";
		}
		else if((i.equals("number")) || (i.equals("amount")))
		{
			message = "Our youth spend an alarming NUMBER of hours browsing the Internet.\n" +
					"Our youth spend an alarming AMOUNT of time browsing the Internet.\n" +
					"You should reduce the NUMBER of eggs in the recipe.\n" +
					"You should reduce the AMOUNT of milk in this recipe.";
		}
		else if((i.equals("to")) || (i.equals("too")) || (i.equals("two")))
		{
			message = "This dress is TO be worn at prom.\n" +
					"This dress is TOO tight for me.\n" +
					"This dress is TWO sizes bigger than my size."; 
		}
		else if((i.equals("accept")) || (i.equals("except")))
		{
			message = "I won't ACCEPT failure.\n" +
					"I won't settle for anything EXCEPT success.\n" +
					"His first choice wouldn't ACCEPT him.\n" +
					"She didn't send the email to anyone EXCEPT him."; 
		}
		else if((i.equals("through")) || (i.equals("threw")) || (i.equals("though")) || (i.equals("thought")) || (i.equals("thorough")))
		{
			message = "Jesus helped me THROUGH tough times, reported the devout Christian.\n" +
					"Upon being denied a candy cane, the toddler THREW a tantrum.\n" +
					"Wow, I never thought you'd actually get THROUGH with it.\n" +
					"It's quite simple, THOUGH, if you're familiar with English.\n" +
					"I've never really THOUGHT about that...\n" +
					"My favorite Lonely Island song is I THREW It on the Ground."; 
		}
		else if((i.equals("diffused")) || (i.equals("defuse")) || (i.equals("defused")) || (i.equals("diffuse")))
		{
			message = "The fluorescent light DIFFUSED on the bomb's surface.\n" +
					"At this rate, we can't DEFUSE the bomb on time!";
		}
		else if((i.equals("statue")) || (i.equals("stature")) || (i.equals("statute")))
		{
			message = "Let's visit the STATUE of Liberty.\n" +
					"At the hospital, she measured her weight and STATURE.\n" +
					"We cannot work on Sundays because of the STATUTE regulating it."; 
		}
		else if((i.equals("stationary")) || (i.equals("stationery")))
		{
			message = "When parking, pull down the side brake to make sure your car stays STATIONARY.\n" +
					"Don't steal the office STATIONERY!";
		}
		else if((i.equals("by")) || (i.equals("buy")) || (i.equals("bye")))
		{
			message = "This plank of wood is three BY twelve inches.\n" +
					"This book is written BY Edgar Allan Poe.\n" +
					"You shouldn't BUY so much stuff on Amazon Prime.\n" +
					"BYE, I'll see you tomorrow."; 
		}
		else if((i.equals("breath")) || (i.equals("breathe")))
		{
			message = "I'm out of BREATH.\n" +
					"I find it difficult to BREATHE without an inhaler.";
		}
		else if((i.equals("drunk")) || (i.equals("drank")) || (i.equals("drink")))
		{
			message = "Take a DRINK if you're thirsty.\n" +
					"She looks like she once DRANK from the spring of youth.\n" +
					"This kind of tea was DRUNK in Japan since the early eighth century.\n" +
					"Just a sip of alcohol makes me feel too DRUNK."; 
		}
		else if((i.equals("discreet")) || (i.equals("discrete")))
		{
			message = "The politician made a statement, but forgot to be DISCREET about it.\n" +
					"Don't confuse these two DISCRETE problems.\n" +
					"Without encryption, we wouldn't be able to send DISCREET messages online.\n" +
					"It's perfectly normal and healthy for a couple to sleep in DISCRETE bedrooms."; 
		}
		else if((i.equals("seas")) || (i.equals("sees")) || (i.equals("seize")) || (i.equals("c's")))
		{
			message = "I've sailed the seven SEAS.\n" +
					"Let's use whichever our customer SEES fit.\n" +
					"The government cannot SEIZE your property without reason.\n" +
					"My aquatic biology grades have been under C'S."; 
		}
		else if((i.equals("weather")) || (i.equals("whether")))
		{
			message = "I don't know if the WEATHER will be nice on Saturday.\n" +
					"I don't know WHETHER I'll need an umbrella."; 
		}
		else if((i.equals("rays")) || (i.equals("raise")) || (i.equals("raze")))
		{
			message = "They could only watch the hurricane RAZE their neighborhood.\n" +
					"We should RAISE the minimum wage.\n" +
					"The RAYS of sunshine cut into the house through the window."; 
		}
		else if((i.equals("wander")) || (i.equals("wonder")))
		{
			message = "The bears WANDER through the forest.\n" +
					"The astronomers WONDER how long the rover could stay active."; 
		}
		else if((i.equals("die")) || (i.equals("dice")))
		{
			message = "I rolled a 1 on a 20-sided DIE, resulting in a critmiss.\n" +
					"Yahtzee is played with five DICE.\n" +
					"You either live or you DIE."; 
		}
		else if((i.equals("ad")) || (i.equals("add")) || (i.equals("adds")) || (i.equals("ads")))
		{
			message = "We should ADD some more information.\n" +
					"Nobody likes unskippable ADS."; 
		}
		else if((i.equals("aloud")) || (i.equals("allowed")))
		{
			message = "I accidentally said that ALOUD, didn't I?\n" +
					"Am I ALLOWED to wear sleeveless shirts?"; 
		}
		else if((i.equals("altar")) || (i.equals("alter")))
		{
			message = "We waited for him to stand behind the ALTAR\n" +
					"Can we ALTER the design of the steeple?"; 
		}
		else if((i.equals("arc")) || (i.equals("ark")))
		{
			message = "The sequel ruins her entire character ARC!\n" +
					"How much modern technology did he use to build that ARK?"; 
		}
		else if((i.equals("baited")) || (i.equals("bated")))
		{
			message = "The dog was BAITED to join the investigation.\n" +
					"The dog waited with BATED breath."; 
		}
		else if((i.equals("base")) || (i.equals("bass")))
		{
			message = "The area of a triangle is one half BASE times height.\n" +
					"The DJ is about to drop the BASS!"; 
		}
		else if((i.equals("blew")) || (i.equals("blue")))
		{
			message = "Cutting that wire BLEW the fuse.\n" +
					"Do I cut the BLUE wire?"; 
		}
		else if((i.equals("brake")) || (i.equals("break")))
		{
			message = "When turning a corner, don't forget to BRAKE your car.\n" +
					"Good grief, give me a BREAK."; 
		}
		else if((i.equals("carat")) || (i.equals("caret")) || (i.equals("carrot")) || (i.equals("karot")))
		{
			message = "This diamond ring weighs 1 CARAT.\n" +
					"Insert a space at the CARET.\n" +
					"Hey, did you know that CARROTS are good for your eyesight?\n" +
					"The diamond ring is set in 18-KARAT gold."; 
		}
		else if((i.equals("ceiling")) || (i.equals("sealing")))
		{
			message = "The instructions were unclear; I'm stuck on the CEILING fan.\n" +
					"They're SEALING the leak up there."; 
		}
		else if((i.equals("cent")) || (i.equals("scent")) || (i.equals("sent")))
		{
			message = "Abraham Lincoln's head is carved in the one-CENT coin.\n" +
					"Can you smell the SCENT of bacon?\n" +
					"I SENT you that email yesterday."; 
		}
		else if((i.equals("cereal")) || (i.equals("serial")))
		{
			message = "I found this toy in a CEREAL box.\n" +
					"The SERIAL number is even."; 
		}
		else if((i.equals("choral")) || (i.equals("coral")) || (i.equals("corral")))
		{
			message = "I heard a majestic CHORAL arrangement of this song.\n" +
					"The CORAL reefs are dying.\n" +
					"Ever since, the shootout at the O.K. CORRAL became iconic of the Wild West genre."; 
		}
		else if((i.equals("coarse")) || (i.equals("course")))
		{
			message = "First sand the wood with COARSE sandpaper.\n" +
					"Our ship is straying off our COURSE."; 
		}
		else if((i.equals("creak")) || (i.equals("creek")))
		{
			message = "The door to the mansion made a loud CREAK.\n" +
					"The birds landed to bathe themselves in the CREEK."; 
		}
		else if((i.equals("dear")) || (i.equals("deer")))
		{
			message = "That venison is pretty DEAR.\n" +
					"I froze like a DEER in the headlights."; 
		}
		else if((i.equals("discussed")) || (i.equals("disgust")))
		{
			message = "Yes, we definitely DISCUSSED this before.\n" +
					"Ugh, your Crocs DISGUST me!"; 
		}
		else if((i.equals("elicit")) || (i.equals("illicit")))
		{
			message = "Don't provoke someone just to ELICIT a reaction.\n" +
					"The politician was accused of ILLICIT activities."; 
		}
		else if((i.equals("fainted")) || (i.equals("feinted")))
		{
			message = "The Monsplode FAINTED from your attack.\n" +
					"The Monsplode FEINTED before striking for real."; 
		}
		else if((i.equals("faze")) || (i.equals("phase")) || (i.equals("fazed")) || (i.equals("phased")))
		{
			message = "Seeing dead bodies no longer FAZED the investigator.\n" +
					"It's not a PHASE, Dad!"; 
		}
		else if((i.equals("find")) || (i.equals("fined")))
		{
			message = "You will FIND 50 dollars on the street.\n" +
					"You will be FINED 50 dollars for illegal parking."; 
		}
		else if((i.equals("flair")) || (i.equals("flare")))
		{
			message = "His parkour is full of FLAIR.\n" +
					"The solar FLARE could even annihilate our civilization!"; 
		}
		else if((i.equals("flea")) || (i.equals("flee")))
		{
			message = "A FLEA and a fly in a flue were imprisoned, so what could they do?\n" +
					"Said the fly, let us FLEE; said the flea, let us fly."; 
		}
		else if((i.equals("gait")) || (i.equals("gate")))
		{
			message = "Her wide GAIT showed me how urgent she was.\n" +
					"The GATE was held wide open for me."; 
		}
		else if((i.equals("idle")) || (i.equals("idol")) || (i.equals("idyll")))
		{
			message = "We just can't be IDLE anymore.\n" +
					"The teen IDOL was stabbed to death in her own shower.\n" +
					"The poet wrote an IDYLL inspired by Heidi."; 
		}
		else if((i.equals("lighting")) || (i.equals("lightning")) || (i.equals("lightening")))
		{
			message = "This room has bad LIGHTING.\n" +
					"The LIGHTNING flared up the sky for an instant.\n" +
					"I was LIGHTENING my bag when I heard it."; 
		}
		else if((i.equals("loan")) || (i.equals("lone")) || (i.equals("lend")))
		{
			message = "I'll return my five-dollar LOAN when it's time to pay for dinner.\n" +
					"The girl is the LONE survivor of the terrorist attack.\n" +
					"Can you LEND me some money?"; 
		}
		else if((i.equals("meat")) || (i.equals("meet")) || (i.equals("mete")))
		{
			message = "Where's the MEAT of your speech?\n" +
					"I can't wait to MEET you in person!\n" +
					"The judge will METE out the punishment if he is guilty."; 
		}
		else if((i.equals("oar")) || (i.equals("or")) || (i.equals("ore")))
		{
			message = "I'd row with you if I had an extra OAR.\n" +
					"Either you're a genius, OR very bored."; 
		}
		else if((i.equals("pail")) || (i.equals("pale")))
		{
			message = "But all the water Simon had was in his mother's PAIL!\n" +
					"After I spilled that paint, my face went PALE."; 
		}
		else if((i.equals("pair")) || (i.equals("pare")) || (i.equals("pear")) || (i.equals("pairs")) || (i.equals("pares")) || (i.equals("pears")))
		{
			message = "The chef knew how to PAIR wine with cheese.\n" +
					"You need to PARE the fruit before eating it.\n" +
					"The PEARS we eat in Japan look like apples."; 
		}
		else if((i.equals("palate")) || (i.equals("palette")) || (i.equals("pallet")))
		{
			message = "The k sound is produced at the soft PALATE.\n" +
					"You need to limit the color PALETTE further.\n" +
					"The forklift can't lift that box; it's not on a PALLET."; 
		}
		else if((i.equals("poor")) || (i.equals("pore")) || (i.equals("pour")))
		{
			message = "What will the robin do then? POOR thing!\n" +
					"I watched her PORE over my essay.\n" +
					"POUR the batter into the pan."; 
		}
		else if((i.equals("praise")) || (i.equals("prays")) || (i.equals("preys")))
		{
			message = "PRAISE be onto him.\n" +
					"He PRAYS to Jesus.\n" +
					"The whale PREYS on plankton."; 
		}
		else if((i.equals("precedence")) || (i.equals("precedents")) || (i.equals("presidents")))
		{
			message = "The earlier rules have PRECEDENCE over the later ones.\n" +
					"There have been no PRECEDENTS of this kind of surgery.\n" +
					"I've got PRESIDENTS in my wallet."; 
		}
		else if((i.equals("racket")) || (i.equals("racquet")))
		{
			message = "Stop making such a RACKET in the dorm!\n" +
					"Now press the button with the ping-pong RACQUET symbol."; 
		}
		else if((i.equals("right")) || (i.equals("rite")) || (i.equals("wright")) || (i.equals("write")))
		{
			message = "I couldn't cross my T's just RIGHT.\n" +
					"Bar Mitzvah is the Jewish RITE of passage for boys.\n" +
					"He needed to bring his coach to the wheel WRIGHT to have it fixed.\n" +
					"Oh no, I forgot to WRITE my essay!"; 
		}
		else if((i.equals("road")) || (i.equals("rode")) || (i.equals("rowed")))
		{
			message = "I walk a lonely ROAD, the only one that I have ever known.\n" +
					"I RODE into town on a horse with no name.\n" +
					"I ROWED the boat as hard as I could!"; 
		}
		else if((i.equals("ring")) || (i.equals("wring")))
		{
			message = "If you liked it, then you should have put a RING on it!\n" +
					"I'm going to WRING this cloth; it's too damp for me to keep wiping."; 
		}
		else if((i.equals("role")) || (i.equals("roll")))
		{
			message = "The organization plays an integral ROLE in fish trading.\n" +
					"I'm on a ROLL today. Bring the bombs on!"; 
		}
		else if((i.equals("seams")) || (i.equals("seems")))
		{
			message = "He cracked open his skull and needed over twenty SEAMS.\n" +
					"It SEEMS that the clothes have started to fall apart."; 
		}
		else if((i.equals("stairs")) || (i.equals("stares")))
		{
			message = "I went down the STAIRS to see everyone glaring at me.\n" +
					"If looks could kill, those STARES would have demolished me."; 
		}
		else if((i.equals("steal")) || (i.equals("steel")))
		{
			message = "A box full of stuff for less than 20 dollars a month? What a STEAL!\n" +
					"Superman is also known as the Man of STEEL."; 
		}
		else if((i.equals("straight")) || (i.equals("strait")))
		{
			message = "Let me get the record STRAIGHT: I'm not gay!\n" +
					"Swimming across the Bering STRAIT is not a simple task."; 
		}
		else if((i.equals("vain")) || (i.equals("vane")) || (i.equals("vein")))
		{
			message = "I've tried, but it was all in VAIN.\n" +
					"The weather VANE fell off the top of the tower.\n" +
					"The anger made his VEIN pop out."; 
		}
		else if((i.equals("vary")) || (i.equals("very")))
		{
			message = "Stories VARY wildly.\n" +
					"This is bad, VERY bad."; 
		}
		else if((i.equals("wait")) || (i.equals("weight")) || (i.equals("waited")) || (i.equals("weighted")))
		{
			message = "I can't WAIT to eat this bagel!\n" +
					"He has a WEIGHTED blanket."; 
		}
		else if((i.equals("weak")) || (i.equals("week")))
		{
			message = "Sleep is for the WEAK.\n" +
					"I'm sleeping for a WEEK."; 
		}
		else if((i.equals("everyday")) || (i.equals("every day")))
		{
			message = "This is an EVERYDAY occurrence.\n" +
					"This happens EVERY DAY."; 
		}
		else if((i.equals("perfect")) || (i.equals("prefect")))
		{
			message = "The employee feared that when the PREFECT became the CEO, he would have his pay cut.\n" +
					"Wow, you look PERFECT today!"; 
		}
		else
		{
			message = "null";
		}
		return message;
	}
}

