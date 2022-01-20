package modules;

import javax.swing.JOptionPane;

public class Jukebox 
{
	private String[] songList =
		{ 
				" A LITTLE LESS CONVERSATION A LITTLE MORE ACTION PLEASE ALL THIS AGGRAVATION AND SATISFACTION IN ME ",
				" I HEAR THE DRUMS ECHOING TONIGHT SHE HEARS ONLY WHISPERS OF SOME QUIET CONVERSATION ",
				" ALL THE SMALL THINGS TRUE CARE TRUTH BRINGS I'LL TAKE ONE LIFT YOUR RIDE BEST TRIP ",
				" DO YOU HEAR THE PEOPLE SING SINING THE SONGS OF ANGRY MEN IT IS THE MUSIC OF A PEOPLE WHO WILL NOT BE SLAVES AGAIN ",
				" IF I LEAVE HERE TOMORROW WILL YOU STILL REMEMBER ME CAUSE I MUST BE TRAVELLING ON NOW CAUSE THERE'S TOO MANY PLACES I'VE GOT TO SEE ",
				" I DON'T CARE IF MONDAY'S BLUE TUESDAY'S GREY AND WEDNESDAY TOO THURSDAY I DON'T CARE ABOUT YOU IT'S FRIDAY I'M IN LOVE ",
				" THERE'S A HOLE IN YOUR LOGIC YOU WHO KNOW ALL THE ANSWERS YOU CLAIM SCIENCE AIN'T MAGIC AND EXPECT ME TO BUY IT ",
				" THERE'S A PLACE IN YOUR HEART AND I KNOW THAT IT IS LOVE AND THIS PLACE COULD BE MUCH BRIGHTER THAN TOMORROW ",
				" HELLO IT'S ME I WAS WONDERING IF AFTER ALL THESE YEARS YOU'D LIKE TO MEET ",
				" STEP ONE YOU SAY WE NEED TO TALK HE WALKS YOU SAY SIT DOWN IT'S JUST A TALK ",
				" NOBODY CAN TELL YA THERE'S ONLY ONE SONG WORTH SINGING ",
				" TIME ON YOUR SIDE IT'LL NEVER END THE MOST BEAUTIFUL THING YOU CAN EVER SPEND BUT YOUR WORK IN A SHIRT WITH YOUR NAMETAG ON IT ",
				" I FOUND A LOVE FOR ME DARLING JUST DIVE RIGHT IN AND FOLLOW MY LEAD ",
				" I'VE CROSSED THE DESERTS FOR MILES SWAM WATER FOR TIME SEARCHING PLACES TO FIND A PIECE OF SOMETHING TO CALL MINE ",
				" I NEED MORE DREAMS AND LESS LIFE I NEED THAT DARK IN A LITTLE MORE LIGHT ",
				" SAY SOMETHING I'M GIVING UP ON YOU I'LL BE THE ONE IF YOU WANT ME TO ",
				" WHEN I HEARD THAT SOUND WHEN THE WALLS CAME DOWN I WAS THINKING ABOUT YOU ",
				" IF YOU CHANGE YOUR MIND I'M THE FIRST IN LINE HONEY I'M STILL FREE TAKE A CHANCE ON ME ",
				" MY LOVER'S GOT HUMOUR SHE'S THE GIGGLE AT THE FUNERAL KNOWS EVERYBODY'S DISAPPROVAL I SHOULD HAVE WORSHIPPED HER SOONER ",
				" WE'RE TALKING AWAY I DON'T KNOW WHAT I'M TO SAY I'LL SAY IT ANYWAY TODAY'S ANOTHER DAY TO FIND YOU SHYING AWAY ",
				" STANDING ON THE EDGE OF FOREVER AT THE START OF WHATEVER SHOUTING LOVE AT THE WORLD ",
				" WHO KNOWS WHAT TOMORROW BRINGS IN A WORLD FEW HEARTS SURVIVE ",
				" THERE'S NOT TIME FOR US THERE'S NO PLACE FOR US WHAT IS THIS THING THAT BUILDS OUR DREAMS YET SLIPS AWAY FROM US ",
				" YOUNG MAN THERE'S NO NEED TO FEEL DOWN I SAID YOUNG MAN GET YOURSELF OFF THE GROUND I SAID YOUNG MAN CAUSE YOU'RE IN A NEW TOWN ",
				" YOU GIVE YOUR HAND TO ME AND THEN YOU SAY HELLO AND I CAN HARDLY SPEAK MY HEART IS BEATING SO "
		};
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the 3 words (spaces):").toUpperCase();
		String[] ans = getAnswer(input.split(" "));
		while(ans == null)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 3 words (spaces):").toUpperCase();
			ans = getAnswer(input.split(" "));
		}
		JOptionPane.showMessageDialog(null, "Press these words:\n" + ans[0] + "\n" + ans[1] + "\n" + ans[2]);
	}
	private String[] getAnswer(String[] words)
	{
		for(int aa = 0; aa < words.length; aa++)
			words[aa] = " " + words[aa] + " ";
		for(int aa = 0; aa < songList.length; aa++)
		{
			if(songList[aa].contains(words[0]) && songList[aa].contains(words[1]) && songList[aa].contains(words[2]))
			{
				if(songList[aa].indexOf(words[0]) < songList[aa].indexOf(words[1]) && songList[aa].indexOf(words[0]) < songList[aa].indexOf(words[2]))
				{
					if(songList[aa].indexOf(words[1]) < songList[aa].indexOf(words[2]))
						return new String[] {words[0], words[1], words[2]};
					else
						return new String[] {words[0], words[2], words[1]};
				}
				else if(songList[aa].indexOf(words[1]) < songList[aa].indexOf(words[0]) && songList[aa].indexOf(words[1]) < songList[aa].indexOf(words[2]))
				{
					if(songList[aa].indexOf(words[0]) < songList[aa].indexOf(words[2]))
						return new String[] {words[1], words[0], words[2]};
					else
						return new String[] {words[1], words[2], words[0]};
				}
				else
				{
					if(songList[aa].indexOf(words[0]) < songList[aa].indexOf(words[1]))
						return new String[] {words[2], words[0], words[1]};
					else
						return new String[] {words[2], words[1], words[0]};
				}
			}
		}
		return null;
	}
}
