package start;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import misc.PlayType;
import modules.*;
import modules.Number;

public class Driver 
{
	public static void main(String[] args) throws Exception
	{
		int convertTimeZone = 0;
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int)screenSize.getHeight();
		int screenWidth = (int)screenSize.getWidth();
		double resizer = 1920.0 / screenWidth;
		
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Dialog", Font.BOLD, (int)screenHeight / 80)));
		
		ArrayList<String[]> souvenirList = new ArrayList<String[]>();
		ArrayList<String> timeModuleList = new ArrayList<String>();
		ArrayList<int[]> timeWarns = new ArrayList<int[]>();
		ArrayList<String> swanNames = new ArrayList<String>();
		ArrayList<Integer> swanResets = new ArrayList<Integer>();
		BombConfig bombCon = new BombConfig(convertTimeZone);
		BombEdgework bombEW = new BombEdgework(resizer);
		boolean souv = (JOptionPane.showConfirmDialog(null, "Is there a souvenir?") == 0);
		
		/*
		 * 0 - EFM
		 * 1 - Team
		 * 2 - TP
		 */
		PlayType playType = PlayType.EFM;
		boolean loop = true;
		do
		{
			String module = JOptionPane.showInputDialog("Enter a module:").toUpperCase();
			switch(module)
			{
				case "ADJACENT LETTERS":
					AdjacentLetters adjacentLetters = new AdjacentLetters();
					adjacentLetters.run();
					break;
				case "ADVENTURE GAME":
					AdventureGame adventureGame = new AdventureGame(bombEW, souv);
					souvenirList.add(new String[] {"ADVENTURE GAME", adventureGame.run()});
					break;
				case "ALGEBRA":
					Algebra algebra = new Algebra(bombCon, bombEW, resizer);
					souvenirList.add(new String[] {"ALGEBRA", algebra.run()});
					break;
				case "ALPHABET":
					Alphabet alphabet = new Alphabet();
					alphabet.run();
					break;
				case "ANAGRAMS":
					Anagrams anagrams = new Anagrams();
					anagrams.run();
					break;
				case "ASTROLOGY":
					Astrology astrology = new Astrology(resizer, bombEW);
					astrology.run();
					break;
				case "BACKGROUNDS":
					Backgrounds backgrounds = new Backgrounds(bombEW);
					backgrounds.run();
					break;
				case "BATTLESHIP":
					Battleship battleship = new Battleship(resizer, bombEW);
					battleship.run();
					break;
				case "BIG CIRCLE":
					BigCircle bigCircle = new BigCircle(bombEW);
					souvenirList.add(new String[] {"BIG CIRCLE", bigCircle.run()});
					break;
				case "BINARY LEDS":
					BinaryLEDs binaryLEDs = new BinaryLEDs(playType);
					souvenirList.add(new String[] {"BINARY LEDS", binaryLEDs.run()});
					break;
				case "BITMAPS":
					Bitmaps bitmaps = new Bitmaps(bombEW);
					souvenirList.add(new String[] {"BITMAPS", bitmaps.run()});
					break;
				case "BITWISE OPERATIONS":
					BitwiseOperations bitwiseOperations = new BitwiseOperations(bombCon, bombEW);
					bitwiseOperations.run();
					break;
				case "BLIND ALLEY":
					BlindAlley blindAlley = new BlindAlley(bombEW);
					blindAlley.run();
					break;
				case "BLIND MAZE":
					BlindMaze blindMaze = new BlindMaze(bombEW);
					souvenirList.add(new String[] {"BLIND MAZE", blindMaze.run()});
					break;
				case "BOOLEAN VENN DIAGRAM":
					BooleanVennDiagram booleanVennDiagram = new BooleanVennDiagram(resizer);
					booleanVennDiagram.run();
					break;
				case "BRAILLE":
					Braille braille = new Braille(bombEW);
					souvenirList.add(new String[] {"BRAILLE", braille.run()});
					break;
				case "BROKEN BUTTONS":
					BrokenButtons brokenButtons = new BrokenButtons();
					souvenirList.add(new String[]{"BROKEN BUTTONS", brokenButtons.run()});
					break;
				case "BULB":
				case "THE BULB":
					Bulb bulb = new Bulb(bombEW);
					souvenirList.add(new String[]{"BULB", bulb.run()});
					break;
				case "BURGLAR ALARM":
					BurglarAlarm burglarAlarm = new BurglarAlarm(bombEW);
					souvenirList.add(new String[]{"BURGLAR ALARM", burglarAlarm.run()});
					break;
				case "BUTTON":
				case "THE BUTTON":
					Button button = new Button(bombEW, souv, playType);
					souvenirList.add(new String[]{"BUTTON", button.run()});
					break;
				case "BUTTON SEQUENCE":
					ButtonSequence buttonSequence = new ButtonSequence(playType);
					souvenirList.add(new String[]{"BUTTON SEQUENCE", buttonSequence.run()});
					break;
				case "CAESAR CIPHER":
					CaesarCipher caesarCipher = new CaesarCipher(bombEW);
					caesarCipher.run();
					break;
				case "CALENDAR":
					Calendars calendar = new Calendars(bombCon, bombEW, resizer);
					souvenirList.add(new String[] {"CALENDAR", calendar.run()});
					break;
				case "CHEAP CHECKOUT":
					CheapCheckout cheapCheckout = new CheapCheckout(bombCon);
					souvenirList.add(new String[] {"CHEAP CHECKOUT", cheapCheckout.run()});
					break;
				case "CHESS":
					Chess chess = new Chess(bombEW);
					souvenirList.add(new String[]{"CHESS", chess.run()});
					break;
				case "CHORD QUALITIES":
					ChordQualities chordQualities = new ChordQualities();
					souvenirList.add(new String[] {"CHORD QUALITIES", chordQualities.run()});
					break;
				case "THE CLOCK":
				case "CLOCK":
					Clock clock = new Clock(bombCon);
					clock.run();
					break;
				case "THE CODE":
				case "CODE":
					Code code = new Code(bombEW);
					souvenirList.add(new String[] {"CODE", code.run()});
					break;
				case "COLOR DECODING":
					ColorDecoding colorDecoding = new ColorDecoding(bombEW);
					souvenirList.add(new String[]{"COLOR DECODING", colorDecoding.run()});
					break;
				case "COLORED SQUARES":
					ColoredSquares coloredSquares = new ColoredSquares();
					souvenirList.add(new String[]{"COLORED SQUARES", coloredSquares.run()});
					break;
				case "COLORED SWITCHES":
					ColoredSwitches coloredSwitches = new ColoredSwitches();
					souvenirList.add(new String[] {"COLORED SWITCHES", coloredSwitches.run()});
					break;
				case "COLOR GENERATOR":
					ColorGenerator colorGenerator = new ColorGenerator(bombEW);
					colorGenerator.run();
					break;
				case "COLOR MATH":
					ColorMath colorMath = new ColorMath(bombEW);
					colorMath.run();
					break;
				case "COLOR MORSE":
					ColorMorse colorMorse = new ColorMorse();
					souvenirList.add(new String[] {"COLOR MORSE", colorMorse.run()});
					break;
				case "COLOUR FLASH":
					ColourFlash colourFlash = new ColourFlash(playType);
					souvenirList.add(new String[]{"COLOUR FLASH", colourFlash.run()});
					break;
				case "COMBINATION LOCK":
					CombinationLock combinationLock = new CombinationLock(bombCon, bombEW);
					combinationLock.run();
					break;
				case "COMPLEX KEYPAD":
					ComplexKeypad complexKeypad = new ComplexKeypad(bombEW, resizer);
					complexKeypad.run();
					break;
				case "COMPLICATED BUTTONS":
					ComplicatedButtons complicatedButtons = new ComplicatedButtons(bombEW);
					complicatedButtons.run();
					break;
				case "COMPLICATED WIRES":
					ComplicatedWires complicatedWires = new ComplicatedWires(bombEW);
					complicatedWires.run();
					break;
				case "CONNECTION CHECK":
					ConnectionCheck connectionCheck = new ConnectionCheck(bombEW);
					connectionCheck.run();
					break;
				case "COOKING":
					Cooking cooking = new Cooking(bombEW, resizer);
					cooking.run();
					break;
				case "COORDINATES":
					Coordinates coordinates = new Coordinates(resizer);
					souvenirList.add(new String[] {"COORDINATES", coordinates.run()});
					break;
				case "CRAZY TALK":
					CrazyTalk crazyTalk = new CrazyTalk();
					crazyTalk.run();
					break;
				case "CREATION":
					Creation creation = new Creation(bombEW, resizer);
					souvenirList.add(new String[] {"CREATION", creation.run()});
					break;
				case "CRUEL PIANO KEYS":
					CruelPianoKeys cruelPianoKeys = new CruelPianoKeys(bombEW, resizer);
					cruelPianoKeys.run();
					break;
				case "CRYPTOGRAPHY":
					Cryptography cryptography = new Cryptography();
					cryptography.run();
					break;
				case "THE CUBE":
				case "CUBE":
					Cube cube = new Cube(bombCon, bombEW, resizer, playType);
					souvenirList.add(new String[] {"CUBE", cube.run()});
					break;
				case "CURRICULUM":
					Curriculum curriculum = new Curriculum(playType, bombEW);
					curriculum.run();
					break;
				case "DIGITAL ROOT":
					DigitalRoot digitalRoot = new DigitalRoot();
					digitalRoot.run();
					break;
				case "DOUBLE OH":
				case "DOUBLE-OH":
					DoubleOh doubleOh = new DoubleOh(playType, souv);
					souvenirList.add(new String[] {"DOUBLE OH", doubleOh.run()});
					break;
				case "DR. DOCTOR":
				case "DR DOCTOR":
					DrDoctor drDoctor = new DrDoctor(bombCon, bombEW, convertTimeZone, souv);
					souvenirList.add(new String[] {"DR DOCTOR", drDoctor.run()});
					break;
				case "EMOJI MATH":
					EmojiMath emojiMath = new EmojiMath();
					emojiMath.run();
					break;
				case "ENGLISH TEST":
					EnglishTest englishTest = new EnglishTest();
					englishTest.run();
					break;
				case "ERROR CODES":
					ErrorCodes errorCodes = new ErrorCodes(bombEW);
					errorCodes.run();
					break;
				case "EUROPEAN TRAVEL":
					EuropeanTravel europeanTravel = new EuropeanTravel(resizer);
					europeanTravel.run();
					break;
				case "EXTENDED PASSWORD":
					ExtendedPassword extendedPassword = new ExtendedPassword(playType);
					extendedPassword.run();
					break;
				case "FAST MATH":
					FastMath fastMath = new FastMath(bombEW);
					souvenirList.add(new String[] {"FAST MATH", fastMath.run()});
					break;
				case "FAULTY BACKGROUNDS":
					FaultyBackgrounds faultyBackgrounds = new FaultyBackgrounds(bombEW);
					faultyBackgrounds.run();
					break;
				case "FESTIVE PIANO KEYS":
					FestivePianoKeys festivePianoKeys = new FestivePianoKeys(resizer, bombEW);
					festivePianoKeys.run();
					break;
				case "FIZZBUZZ":
					FizzBuzz fizzBuzz = new FizzBuzz(bombEW);
					fizzBuzz.run();
					break;
				case "FLAGS":
					Flags flags = new Flags(resizer, bombEW);
					souvenirList.add(new String[] {"FLAGS", flags.run()});
					break;
				case "FOLLOW THE LEADER":
					FollowTheLeader followTheLeader = new FollowTheLeader(bombEW, playType);
					followTheLeader.run();
					break;
				case "FONT SELECT":
					FontSelect fontSelect = new FontSelect(resizer);
					fontSelect.run();
					break;
				case "FOREIGN EXCHANGE RATES":
					ForeignExchangeRates foreignExchangeRates = new ForeignExchangeRates(bombEW);
					foreignExchangeRates.run();
					break;
				case "FORGET EVERYTHING":
					ForgetEverything forgetEverything = new ForgetEverything();
					forgetEverything.run();
					break;
				case "FORGET ME NOT":
					ForgetMeNot forgetMeNot = new ForgetMeNot(bombEW);
					forgetMeNot.run();
					break;
				case "FRIENDSHIP":
					Friendship friendship = new Friendship(resizer);
					friendship.run();
					break;
				case "GAME OF LIFE CRUEL":
					GameOfLifeCruel gameOfLifeCruel = new GameOfLifeCruel(bombCon, bombEW, playType, resizer);
					souvenirList.add(new String[] {"GAME OF LIFE CRUEL", gameOfLifeCruel.run()});
					break;
				case "GAME OF LIFE":
				case "GAME OF LIFE SIMPLE":
					GameOfLifeSimple gameOfLifeSimple = new GameOfLifeSimple(resizer);
					gameOfLifeSimple.run();
					break;
				case "THE GAMEPAD":
				case "GAMEPAD":
					Gamepad gamepad = new Gamepad(bombEW);
					souvenirList.add(new String[]{"GAMEPAD", gamepad.run()});
					break;
				case "GRAFFITI NUMBERS":
					GraffitiNumbers graffitiNumbers = new GraffitiNumbers();
					graffitiNumbers.run();
					break;
				case "GREEK CALCULUS":
					GreekCalculus greekCalculus = new GreekCalculus(bombEW, resizer);
					greekCalculus.run();
					break;
				case "GRIDLOCK":
					Gridlock gridlock = new Gridlock();
					souvenirList.add(new String[] {"GRIDLOCK", gridlock.run()});
					break;
				case "GRID MATCHING":
					GridMatching gridMatching = new GridMatching();
					gridMatching.run();
					break;
				case "GUITAR CHORDS":
					GuitarChords guitarChords = new GuitarChords(bombEW);
					guitarChords.run();
					break;
				case "HEXAMAZE":
					Hexamaze hexamaze = new Hexamaze();
					souvenirList.add(new String[]{"HEXAMAZE", hexamaze.run()});
					break;
				case "HUMAN RESOURCES":
					HumanResources humanResources = new HumanResources();
					souvenirList.add(new String[]{"HUMAN RESOURCES", humanResources.run()});
					break;
				case "HUNTING":
					Hunting hunting = new Hunting(resizer);
					souvenirList.add(new String[]{"HUNTING", hunting.run()});
					break;
				case "ICE CREAM":
					IceCream iceCream = new IceCream(bombEW);
					souvenirList.add(new String[] {"ICE CREAM", iceCream.run()});
					break;
				case "IDENTITY PARADE":
					IdentityParade identityParade = new IdentityParade();
					souvenirList.add(new String[] {"IDENTITY PARADE", identityParade.run()});
					break;
				case "THE IPHONE":
				case "IPHONE":
					IPhone iphone = new IPhone(resizer, bombEW);
					souvenirList.add(new String[] {"IPHONE", iphone.run()});
					break;
				case "THE JEWEL VAULT":
				case "JEWEL VAULT":
					JewelVault jewelVault = new JewelVault(bombEW, playType, resizer);
					souvenirList.add(new String[] {"JEWEL VAULT", jewelVault.run()});
					break;
				case "JUKEBOX":
					Jukebox jukebox = new Jukebox();
					jukebox.run();
					break;
				case "KEYPAD":
					Keypad keypad = new Keypad(resizer);
					keypad.run();
					break;
				case "LASERS":
					Lasers lasers = new Lasers(bombCon);
					souvenirList.add(new String[] {"LASERS", lasers.run()});
					break;
				case "LAUNDRY":
					Laundry laundry = new Laundry(bombCon, bombEW, resizer);
					laundry.run();
					break;
				case "LED ENCRYPTION":
					LEDEncryption ledEncryption = new LEDEncryption(playType);
					souvenirList.add(new String[] {"LED ENCRYPTION", ledEncryption.run()});
					break;
				case "LED GRID":
					LEDGrid ledGrid = new LEDGrid(playType);
					ledGrid.run();
					break;
				case "LEGOS":
					LEGOs legos = new LEGOs(resizer);
					souvenirList.add(new String[] {"LEGOS", legos.run()});
					break;
				case "LETTER KEYS":
					LetterKeys letterKeys = new LetterKeys(bombEW);
					letterKeys.run();
					break;
				case "LIGHT CYCLE":
					LightCycle lightCycle = new LightCycle(bombEW);
					lightCycle.run();
					break;
				case "LISTENING":
					Listening listening = new Listening();
					souvenirList.add(new String[]{"LISTENING", listening.run()});
					break;
				case "LOGIC":
					Logic logic = new Logic(bombEW, resizer);
					logic.run();
					break;
				case "LOGICAL BUTTONS":
					LogicalButtons logicalButtons = new LogicalButtons();
					souvenirList.add(new String[]{"LOGICAL BUTTONS", logicalButtons.run()});
					break;
				case "LOGIC GATES":
					LogicGates logicGates = new LogicGates(resizer);
					souvenirList.add(new String[]{"LOGIC GATES", logicGates.run()});
					break;
				case "THE LONDON UNDERGROUND":
				case "LONDON UNDERGROUND":
					LondonUnderground londonUnderground = new LondonUnderground();
					souvenirList.add(new String[]{"LONDON UNDERGROUND", londonUnderground.run()});
					break;
				case "MAFIA":
					Mafia mafia = new Mafia(bombCon, bombEW);
					souvenirList.add(new String[]{"MAFIA", mafia.run()});
					break;
				case "MAINTENANCE":
					Maintenance maintenance = new Maintenance();
					maintenance.run();
					break;
				case "MARBLE TUMBLE":
					MarbleTumble marbleTumble = new MarbleTumble(resizer);
					marbleTumble.run();
					break;
				case "MASHEMATICS":
					Mashematics mashematics = new Mashematics();
					souvenirList.add(new String[]{"MASHEMATICS", mashematics.run()});
					break;
				case "MASTERMIND CRUEL":
					MastermindCruel mastermindCruel = new MastermindCruel(bombCon, bombEW);
					mastermindCruel.run();
					break;
				case "MASTERMIND SIMPLE":
					MastermindSimple mastermindSimple = new MastermindSimple();
					mastermindSimple.run();
					break;
				case "MAZE":
					Maze maze = new Maze();
					souvenirList.add(new String[]{"MAZE", maze.run()});
					break;
				case "3D MAZE":
					Maze3D maze3D = new Maze3D(bombEW);
					souvenirList.add(new String[] {"3D MAZE", maze3D.run()});
					break;
				case "MEMORY":
					Memory memory = new Memory(playType);
					souvenirList.add(new String[]{"MEMORY", memory.run()});
					break;
				case "MICROCONTROLLER":
					Microcontroller microcontroller = new Microcontroller(bombEW, playType, souv);
					souvenirList.add(new String[]{"MICROCONTROLLER", microcontroller.run()});
					break;
				case "MINESWEEPER":
					Minesweeper minesweeper = new Minesweeper(bombEW);
					souvenirList.add(new String[] {"MINESWEEPER", minesweeper.run()});
					break;
				case "MODERN CIPHER":
					ModernCipher modernCipher = new ModernCipher(bombEW);
					souvenirList.add(new String[] {"MODERN CIPHER", modernCipher.run()});
					break;
				case "MODULES AGAINST HUMANITY":
					ModulesAgainstHumanity modulesAgainstHumanity = new ModulesAgainstHumanity(bombCon, bombEW);
					modulesAgainstHumanity.run();
					break;
				case "MONSPLODE FIGHT":
				case "MONSPLODE, FIGHT!":
					MonsplodeFight monsplodeFight = new MonsplodeFight(bombCon, bombEW, resizer);
					souvenirList.add(new String[]{"MONSPLODE FIGHT", monsplodeFight.run()});
					break;
				case "MONSPLODE TRADING CARDS":
					MonsplodeTradingCards monsplodeTradingCards = new MonsplodeTradingCards(bombEW, resizer);
					souvenirList.add(new String[] {"MONSPLODE TRADING CARDS", monsplodeTradingCards.run()});
					break;
				case "THE MOON":
				case "MOON":
					Moon moon = new Moon(bombCon, bombEW);
					moon.run();
					break;
				case "MORSE A MAZE":
				case "MORSE-A-MAZE":
					MorseAMaze morseAMaze = new MorseAMaze(bombCon, bombEW);
					souvenirList.add(new String[] {"MORSE A MAZE", morseAMaze.run()});
					break;
				case "MORSE CODE":
					MorseCode morseCode = new MorseCode();
					morseCode.run();
					break;
				case "MORSEMATICS":
					Morsematics morsematics = new Morsematics(bombEW);
					souvenirList.add(new String[]{"MORSEMATICS", morsematics.run()});
					break;
				case "MORTAL KOMBAT":
					MortalKombat mortalKombat = new MortalKombat(resizer, bombEW);
					mortalKombat.run();
					break;
				case "MOUSE IN THE MAZE":
					MouseInTheMaze mouseInTheMaze = new MouseInTheMaze(playType, resizer);
					souvenirList.add(new String[] {"MOUSE IN THE MAZE", mouseInTheMaze.run()});
					break;
				case "MURDER":
					Murder murder = new Murder(bombEW);
					souvenirList.add(new String[] {"MURDER", murder.run()});
					break;
				case "MYSTIC SQUARE":
					MysticSquare mysticSquare = new MysticSquare(bombEW, playType, souv);
					souvenirList.add(new String[] {"MYSTIC SQUARE", mysticSquare.run()});
					break;
				case "NEUTRALIZATION":
					Neutralization neutralization = new Neutralization(bombEW);
					souvenirList.add(new String[] {"NEUTRALIZATION", neutralization.run()});
					break;
				case "NONOGRAM":
					Nonogram nonogram = new Nonogram(resizer, bombEW);
					nonogram.run();
					break;
				case "THE NUMBER":
				case "NUMBER":
					Number number = new Number(bombCon, bombEW, convertTimeZone);
					number.run();
					break;
				case "NUMBER PAD":
					NumberPad numberPad = new NumberPad(bombEW);
					numberPad.run();
					break;
				case "ONLY CONNECT":
					OnlyConnect onlyConnect = new OnlyConnect(bombEW, resizer);
					souvenirList.add(new String[] {"ONLY CONNECT", onlyConnect.run()});
					break;
				case "ORIENTATION CUBE":
					OrientationCube orientationCube = new OrientationCube(bombEW);
					souvenirList.add(new String[]{"ORIENTATION CUBE", orientationCube.run()});
					break;
				case "PAINTING":
					Painting painting = new Painting(bombEW);
					painting.run();
					break;
				case "PASSWORD":
					Password password = new Password(playType);
					password.run();
					break;
				case "PERPLEXING WIRES":
					PerplexingWires perplexingWires = new PerplexingWires(bombEW);
					perplexingWires.run();
					break;
				case "PERSPECTIVE PEGS":
					PerspectivePegs perspectivePegs = new PerspectivePegs(bombEW, playType);
					souvenirList.add(new String[]{"PERSPECTIVE PEGS", perspectivePegs.run()});
					break;
				case "PIANO KEYS":
					PianoKeys pianoKeys = new PianoKeys(resizer, bombEW);
					pianoKeys.run();
					break;
				case "PIE":
					Pie pie = new Pie();
					souvenirList.add(new String[]{"PIE", pie.run()});
					break;
				case "PLAYFAIR CIPHER":
					PlayfairCipher playfairCipher = new PlayfairCipher(resizer, bombCon, bombEW);
					playfairCipher.run();
					break;
				case "PLUMBING":
					Plumbing plumbing = new Plumbing(bombEW);
					plumbing.run();
					break;
				case "POETRY":
					Poetry poetry = new Poetry(souv, playType, resizer);
					souvenirList.add(new String[]{"POETRY", poetry.run()});
					break;
				case "POINT OF ORDER":
					PointOfOrder pointOfOrder = new PointOfOrder(bombEW);
					pointOfOrder.run();
					break;
				case "POKER":
					Poker poker = new Poker(bombEW);
					poker.run();
					break;
				case "POLYHEDRAL MAZE":
					PolyhedralMaze polyhedralMaze = new PolyhedralMaze(resizer);
					souvenirList.add(new String[]{"POLYHEDRAL MAZE", polyhedralMaze.run()});
					break;
				case "PRESS X":
					PressX pressX = new PressX(bombEW);
					pressX.run();
					break;
				case "PROBING":
					Probing probing = new Probing();
					souvenirList.add(new String[]{"PROBING", probing.run()});
					break;
				case "RADIATOR":
					Radiator radiator = new Radiator(bombEW);
					radiator.run();
					break;
				case "RESISTORS":
					Resistors resistors = new Resistors(bombEW);
					resistors.run();
					break;
				case "RHYTHMS":
					Rhythms rhythms = new Rhythms(bombEW, resizer);
					souvenirList.add(new String[] {"RHYTHMS", rhythms.run()});
					break;
				case "ROCK PAPER SCISSORS LIZARD SPOCK":
				case "ROCK-PAPER-SCISSORS-LIZARD-SPOCK":
				case "RPS":
				case "RPSLS":
					RockPaperScissorsLizardSpock rockPaperScissorsLizardSpock = new RockPaperScissorsLizardSpock(resizer, bombEW);
					rockPaperScissorsLizardSpock.run();
					break;
				case "ROUND KEYPAD":
					RoundKeypad roundKeypad = new RoundKeypad(resizer);
					roundKeypad.run();
					break;
				case "RUBIK'S CLOCK":
					RubiksClock rubiksClock = new RubiksClock(bombEW);
					rubiksClock.run();
					break;
				case "RUBIK'S CUBE":
					RubiksCube rubiksCube = new RubiksCube(bombEW, resizer);
					rubiksCube.run();
					break;
				case "SAFETY SAFE":
					SafetySafe safetySafe = new SafetySafe(bombEW);
					safetySafe.run();
					break;
				case "THE SCREW":
				case "SCREW":
					Screw screw = new Screw(bombEW);
					screw.run();
					break;
				case "SEA SHELLS":
					SeaShells seaShells = new SeaShells();
					souvenirList.add(new String[] {"SEA SHELLS", seaShells.run()});
					break;
				case "SEMAPHORE":
					Semaphore semaphore = new Semaphore(bombEW);
					semaphore.run();
					break;
				case "S.E.T.":
				case "SET":
					SET set = new SET(playType, resizer);
					set.run();
					break;
				case "SHAPE SHIFT":
					ShapeShift shapeShift = new ShapeShift(bombEW, resizer);
					souvenirList.add(new String[]{"SHAPE SHIFT", shapeShift.run()});
					break;
				case "SILLY SLOTS":
					SillySlots sillySlots = new SillySlots();
					souvenirList.add(new String[]{"SILLY SLOTS", sillySlots.run()});
					break;
				case "SIMON SAYS":
					SimonSays simonSays = new SimonSays(bombEW);
					souvenirList.add(new String[]{"SIMON SAYS", simonSays.run()});
					break;
				case "SIMON SCREAMS":
					SimonScreams simonScreams = new SimonScreams(bombEW, playType, souv);
					souvenirList.add(new String[]{"SIMON SCREAMS", simonScreams.run()});
					break;
				case "SIMON SENDS":
					SimonSends simonSends = new SimonSends();
					souvenirList.add(new String[]{"SIMON SENDS", simonSends.run()});
					break;
				case "SIMON SHRIEKS":
					SimonShrieks simonShrieks = new SimonShrieks(bombEW);
					souvenirList.add(new String[]{"SIMON SHRIEKS", simonShrieks.run()});
					break;
				case "SIMON SINGS":
					SimonSings simonSings = new SimonSings(bombEW);
					souvenirList.add(new String[]{"SIMON SINGS", simonSings.run()});
					break;
				case "SIMON STATES":
					SimonStates simonStates = new SimonStates();
					souvenirList.add(new String[]{"SIMON STATES", simonStates.run()});
					break;
				case "SINK":
					Sink sink = new Sink(bombEW);
					sink.run();
					break;
				case "SKEWED SLOTS":
					SkewedSlots skewedSlots = new SkewedSlots(bombEW);
					souvenirList.add(new String[]{"SKEWED SLOTS", skewedSlots.run()});
					break;
				case "SKYRIM":
					Skyrim skyrim = new Skyrim(resizer, bombEW);
					souvenirList.add(new String[]{"SKYRIM", skyrim.run()});
					break;
				case "SONIC THE HEDGEHOG":
					SonicTheHedgehog sonicTheHedgehog = new SonicTheHedgehog(resizer);
					souvenirList.add(new String[]{"SONIC THE HEDGEHOG", sonicTheHedgehog.run()});
					break;
				case "SOUVENIR":
					Souvenir souvenir = new Souvenir(souvenirList);
					souvenir.run();
					break;
				case "SQUARE BUTTON":
					SquareButton squareButton = new SquareButton(bombEW, playType);
					squareButton.run();
					break;
				case "THE STOPWATCH":
				case "STOPWATCH":
					Stopwatch stopwatch = new Stopwatch(bombCon, bombEW);
					stopwatch.run();
					break;
				case "SUBWAYS":
					Subways subways = new Subways(bombEW, resizer);
					subways.run();
					break;
				case "THE SUN":
				case "SUN":
					Sun sun = new Sun(bombCon, bombEW);
					sun.run();
					break;
				case "SUPERLOGIC":
					Superlogic superlogic = new Superlogic(resizer);
					superlogic.run();
					break;
				case "THE SWAN":
				case "SWAN":
					if(swanNames.size() == 0)
						JOptionPane.showMessageDialog(null, "No Swans added");
					else
					{
						int cur = getCursor("Select the Swan Name:", swanNames);
						Swan swan = new Swan(swanNames.get(cur), swanResets.get(cur));
						swan.run();
						swanNames.remove(cur);
						swanResets.remove(cur);
					}
					break;
				case "SWAN ADD":
					swanNames.add(JOptionPane.showInputDialog("Enter a name for Swan:").toUpperCase());
					swanResets.add(0);
					break;
				case "SWAN RESET":
					if(swanNames.size() == 0)
						JOptionPane.showMessageDialog(null, "No Swans added");
					else
					{
						int cur = getCursor("Select the Swan Name:", swanNames);
						JOptionPane.showMessageDialog(null, "Name: " + swanNames.get(cur) + "\nSubmit this Sequence:\n4 8 15 16 23 42");
						swanResets.set(cur, swanResets.get(cur) + 1);
					}
					break;
				case "SWITCHES":
					Switches switches = new Switches();
					souvenirList.add(new String[]{"SWITCHES", switches.run()});
					break;
				case "SYMBOL CYCLE":
					SymbolCycle symbolCycle = new SymbolCycle();
					souvenirList.add(new String[]{"SYMBOL CYCLE", symbolCycle.run()});
					break;
				case "SYMBOLIC COORDINATES":
					SymbolicCoordinates symbolicCoordinates = new SymbolicCoordinates(resizer);
					souvenirList.add(new String[]{"SYMBOLIC COORDINATES", symbolicCoordinates.run()});
					break;
				case "SYMBOLIC PASSWORD":
					SymbolicPassword symbolicPassword = new SymbolicPassword(resizer);
					symbolicPassword.run();
					break;
				case "SYNONYMS":
					Synonyms synonyms = new Synonyms(bombEW);
					souvenirList.add(new String[]{"SYNONYMS", synonyms.run()});
					break;
				case "TANGRAMS":
					Tangrams tangrams = new Tangrams();
					tangrams.run();
					break;
				case "TAP CODE":
					TapCode tapCode = new TapCode(bombEW);
					souvenirList.add(new String[] {"TAP CODE", tapCode.run()});
					break;
				case "TAX RETURNS":
					TaxReturns taxReturns = new TaxReturns(bombEW);
					taxReturns.run();
					break;
				case "TEXT FIELD":
					TextField textField = new TextField(bombEW);
					souvenirList.add(new String[] {"TEXT FIELD", textField.run()});
					break;
				case "THIRD BASE":
					ThirdBase thirdBase = new ThirdBase(playType);
					souvenirList.add(new String[]{"THIRD BASE", thirdBase.run()});
					break;
				case "TIC TAC TOE":
					TicTacToe ticTacToe = new TicTacToe(bombEW);
					souvenirList.add(new String[]{"TIC TAC TOE", ticTacToe.run()});
					break;
				case "TIMEZONE":
					Timezone timezone = new Timezone();
					souvenirList.add(new String[]{"TIMEZONE", timezone.run()});
					break;
				case "TURN THE KEY":
					TurnTheKey turnTheKey = new TurnTheKey();
					timeWarns.add(turnTheKey.run());
					timeModuleList.add("TURN THE KEY");
					break;
				case "TURN THE KEYS":
					TurnTheKeys turnTheKeys = new TurnTheKeys(bombEW, resizer, souv, playType, timeModuleList, timeWarns);
					souvenirList.addAll(turnTheKeys.run());
					break;
				case "TURTLE ROBOT":
					TurtleRobot turtleRobot = new TurtleRobot();
					souvenirList.add(new String[]{"TURTLE ROBOT", turtleRobot.run()});
					break;
				case "TWO BITS":
					TwoBits twoBits = new TwoBits(bombEW);
					souvenirList.add(new String[]{"TWO BITS", twoBits.run()});
					break;
				case "USA MAZE":
					USAMaze usaMaze = new USAMaze(convertTimeZone, resizer);
					souvenirList.add(new String[]{"USA MAZE", usaMaze.run()});
					break;
				case "VISUAL IMPAIRMENT":
					VisualImpairment visualImpairment = new VisualImpairment(resizer);
					souvenirList.add(new String[]{"VISUAL IMPAIRMENT", visualImpairment.run()});
					break;
				case "WASTE MANAGEMENT":
					WasteManagement wasteManagement = new WasteManagement(bombCon, bombEW);
					wasteManagement.run();
					break;
				case "WEB DESIGN":
					WebDesign webDesign = new WebDesign();
					webDesign.run();
					break;
				case "WHO'S ON FIRST":
					WhosOnFirst whosOnFirst = new WhosOnFirst(playType);
					souvenirList.add(new String[]{"WHO'S ON FIRST", whosOnFirst.run()});
					break;
				case "THE WIRE":
				case "WIRE":
					Wire wire = new Wire(bombCon, bombEW);
					wire.run();
					break;
				case "WIRE PLACEMENT":
					WirePlacement wirePlacement = new WirePlacement(playType);
					wirePlacement.run();
					break;
				case "WIRES":
					Wires wires = new Wires(bombEW);
					wires.run();
					break;
				case "WIRE SEQUENCE":
					WireSequence wireSequence = new WireSequence(playType);
					souvenirList.add(new String[]{"WIRE SEQUENCE", wireSequence.run()});
					break;
				case "WORD SCRAMBLE":
					WordScramble wordScramble = new WordScramble();
					wordScramble.run();
					break;
				case "WORD SEARCH":
					WordSearch wordSearch = new WordSearch(bombEW);
					wordSearch.run();
					break;
				case "X01":
					X01 x01 = new X01(bombEW);
					x01.run();
					break;
				case "X-RAY":
					XRay xray = new XRay(resizer);
					xray.run();
					break;
				case "YAHTZEE":
					Yahtzee yahtzee = new Yahtzee(bombEW);
					souvenirList.add(new String[] {"YAHTZEE", yahtzee.run()});
					break;
				case "ZOO":
					Zoo zoo = new Zoo(bombEW, resizer);
					zoo.run();
					break;
				case "":
					loop = false;
					break;
			}
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        String conv = sdf.format(cal.getTime());
	        String[] split = conv.split(":");
	        int[] current = new int[2];
	        current[0] = Integer.parseInt(split[0]);
	        current[1] = Integer.parseInt(split[1]);
	        for(int aa = 0; aa < timeWarns.size(); aa++)
	        {
	        	if(timeWarns.get(aa)[0] == current[0] && timeWarns.get(aa)[1] <= (current[1]) + 1)
	        	{
	        		JOptionPane.showMessageDialog(null, "This module requires attention:\n" + timeModuleList.get(aa), "", JOptionPane.WARNING_MESSAGE);
	        		timeWarns.remove(aa);
	        		timeModuleList.remove(aa);
	        	}
	        		
	        }
		}while(loop);
		System.exit(0);
	}
	private static int getCursor(String out, ArrayList<String> names)
	{
		if(names.size() == 1)
			return 0;
		else
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			JButton[] jButton = new JButton[names.size()];
			optionPane.setLayout(new GridLayout((names.size() - 1) / 5, 5));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int aa = 0; aa < names.size(); aa++)
			{
				jButton[aa] = getButton(optionPane, names.get(aa));
				optionPane.add(jButton[aa]);
			}
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle(out);
			dialog.setVisible(true);
			return names.indexOf(optionPane.getValue().toString());
		}
	}
	private static JButton getButton(final JOptionPane optionPane, String text) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
