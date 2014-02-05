import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Map {

	public static Room demo() {
		Room room1 = new Room("You are in the first room. There seems to be a room to the North.", "First Room.");
		Room room2 = new Room("You are in the second room. You can go South to return to the beginning.", "Second Room.");
		// player would type 'go north'
		room1.setAdjacentRoom(Action.ActionGoNorth, room2);

		// player would type 'drink coffee'
		room2.putItem(Item.getInstance("coffee"));
		return room1;
	}

	public static Room level1() {

		Room mid1 = new Room("There is a fork", "Fork");
		Room mid2 = new Room("Ferocious bear", "bear");
		Room end = new RoomLockable("You are inside of a building", "Building interior", true, Item.getInstance("key"));
		
		end.setAdjacentRoom(Action.ActionGoNortheast, mid1);
		
		LinkedList<Item> startItems = new LinkedList<Item>();
		startItems.add(Item.getInstance("brick"));
		startItems.add(Item.getInstance("key"));
		startItems.add(Item.getInstance("lock"));
		
		Room start = new Room("There is a tree, with a building to the West. There is a lock on the door.", "Tree" );
		start.setAdjacentRoom(Action.ActionGoNorth, mid1);
		start.setAdjacentRoom(Action.ActionGoEast, mid2);
		start.setAdjacentRoom(Action.ActionGoWest, end);
		start.putItems(startItems);
		return start;
	}
	public static Room njit() {

		String hallwayDesription = "You are in a hallway. \nThere are conference rooms to the W, bathrooms to the N, \nand a stairwell to the E. with a lock on the door";
		String hallwayShortDescription = "Fourth floor hallway";

		String hallway2Desription = "You are in the second floor hallway. This floor of the GITC seems pointless, as it is completely desolate and have never done anything here ever. There is the stairwell to the East, and the elevator to the North.";
		String hallway2ShortDescription = "Second floor hallway";

		String hallway1Desription = "You are in the first floor lobby. There is the stairwell to the West, and the elevators to the North.";
		String hallway1ShortDescription = "First floor lobby";

		String conferenceRoomDescription = "You are in the conference room. You think back to all of the successful ACM meetings you had here.";
		String conferenceRoomShortDescription = "Conference room";

		String stairwellDescription = "You are in the stairwell. There is a couple making out, and you stand there awkwardly watching. You can go up, down, or back to the West.";
		String stairwellShortDescription = "Fourth floorStairwell";

		String stairwell3Description = "You are in the third floor stairwell. It is very quiet. The door leading to the third floor hallway is blocked.";
		String stairwell3ShortDescription = "Third floor Stairwell";

		String stairwell2Description = "You are in the second floor stairwell. You can go up, down, or to the second floor hallway to the West";
		String stairwell2ShortDescription = "Second floor Stairwell";

		String stairwell1Description = "You are in the first floor stairwell. You can go up, down, or to the first floor lobby to the West";
		String stairwell1ShortDescription = "First floor Stairwell";

		String roofDescription = "You are on the roof. There is a police chopper circling overhead.\nYou can head back downstairs, or make a jump for it to the adjacent roof to the E.";
		String roofShortDescription = "GITC Rooftop";

		String dirtRoomDescription = "Giant pile of dirt.";
		String dirtRoomShortDescription = "Dirt pile";
		String dirtRoomRevealMessage = "You have revealed a clay pot.";

		String elevatorDescription = "GITC Elevator";
		String elevatorShortDescription = "Elevator";

		String floor1Description = "First floor of GITC. There is an elevator to the West, and the first floor lobby to the South.";
		String floor2Description = "Second floor Lobby. There is an elevator to the West, and the second floor hallway to the South.";
		String floor3Description = "Third floor Lobby. There is an elevator to the West";
		String floor4Description = "Fourth floor Lobby of GITC. There is an elevator to the West, and the fourth floor hallway to the South.";
		String floor1ShortDescription = "First floor Elevator Front.";
		String floor2ShortDescription = "Second floor Elevator Front";
		String floor3ShortDescription = "Third floor Elevator Front";
		String floor4ShortDescription = "Fourth Floor Elevator Front";

		String gitcExteriorDescription = "You are outside the GITC building. You are blocked in because of construction purposes. The GITC is to the North";
		String gitcExteriorShortDescription = "GITC Exterior";
		

		String bathroomDescription = "You are in the 4th floor bathroom. You are overcome by the stench of a disgusting turd that was once laid. There is grafitti on the wall...";
		String bathroomShortDescription = "Bathroom";

		String passageDescription = "You are in a dark corridor dimly lit by torches. There are portaits of past ACM Presidents, such as Akhil and Ben Slepp. You can hear the distance sound of running water.";
		String passageShortDescription = "Dark Corridor.";

		String corridorDescription = "Well lit corridor. The sound of running water is getting louder.";
		String corridorShortDescription = "Well lit corridor";
		String corridorDarkDescription = "The light of the torches cannot reach here. It is completely pitch black. The sound of running water is getting louder.";	
		String corridorDarkShortDescription = "Pitch black corridor";	

		String waterslideDescription = "You have found the source of the running water! It is the waterslide that Ben Slepp promised! You can go down it if you want, but its destination is unknown";
		String waterslideShortDescription = "Waterslide";

		String acmDescription = "You are in the ACM office. There is a mysterious hole in the floor, with a sign next to it that says, \"Put valuables in here to score points!\".";
		String acmShortDescription = "ACM Office";
		String acmDarkDescription = "It is dark. Perhaps you can find a way to see...";
		String acmDarkShortDescription = "Darkness";








		Room hallway = new Room(hallwayDesription, hallwayShortDescription);
		Room hallway2 = new Room(hallway2Desription, hallway2ShortDescription);
		Room hallway1 = new Room(hallway1Desription, hallway1ShortDescription);

		Room conference = new Room(conferenceRoomDescription, conferenceRoomShortDescription);
		RoomLockable stairwell = new RoomLockable(stairwellDescription, stairwellShortDescription, true, Item.getInstance("key"));
		Room stairwell3 = new Room(stairwell3Description, stairwell3ShortDescription);
		stairwell3.setAdjacentRoom(Action.ActionGoUp, stairwell);
		Room stairwell2 = new Room(stairwell2Description, stairwell2ShortDescription);
		stairwell2.setAdjacentRoom(Action.ActionGoUp, stairwell3);
		stairwell2.setAdjacentRoom(Action.ActionGoWest, hallway2);
		Room stairwell1 = new Room(stairwell1Description, stairwell1ShortDescription);
		stairwell1.setAdjacentRoom(Action.ActionGoUp, stairwell2);
		stairwell1.setAdjacentRoom(Action.ActionGoEast, hallway1);
		
		Item lock = Item.getInstance("lock");
		lock.setRelatedRoom(stairwell);
		hallway.putItem(lock);

		Room roof = new Room(roofDescription, roofShortDescription);
		roof.setAdjacentRoomTransitionMessage("You miss the roof and fall through the floor. Since everyone is using their headphones, nobody seems to notice.", Action.ActionGoEast);
		roof.setAdjacentRoom(Action.ActionGoDown, stairwell);

		RoomExcavatable dirt = new RoomExcavatable(dirtRoomDescription, dirtRoomShortDescription, dirtRoomRevealMessage);
		LinkedList<Item> revealableItems = new LinkedList<Item>();
		ItemClayPot i = (ItemClayPot)Item.getInstance("pot");
		i.install(Item.getInstance("diamond"));
		i.setDestroyMessage("You revealed a beautiful diamond!");
		i.setDisappears(true);
		revealableItems.add(i);
		dirt.setRevealableItems(revealableItems);


		RoomElevator elevator = new RoomElevator(elevatorDescription, elevatorShortDescription);
		elevator.putItem(Item.getInstance("1"));
		elevator.putItem(Item.getInstance("2"));
		elevator.putItem(Item.getInstance("3"));
		elevator.putItem(Item.getInstance("4"));
		
		

		Room floor1 = new Room(floor1Description, floor1ShortDescription); 
		Item b1 = Item.getInstance("button");


		b1.setRelatedRoom(elevator);
		floor1.putItem(b1);
		floor1.setAdjacentRoom(Action.ActionGoSouth, hallway1);

		Room floor2 = new Room(floor2Description, floor2ShortDescription); 

		Item b2 = Item.getInstance("button");


		b2.setRelatedRoom(elevator);
		floor2.putItem(b2);
		floor2.setAdjacentRoom(Action.ActionGoSouth, hallway2);

		Room floor3 = new Room(floor3Description, floor3ShortDescription); 

		Item b3 = Item.getInstance("button");


		b3.setRelatedRoom(elevator);
		floor3.putItem(b3);
		// restricted floor

		Room floor4 = new Room(floor4Description, floor4ShortDescription); 

		Item b4 = Item.getInstance("button");


		b4.setRelatedRoom(elevator);
		floor4.putItem(b4);
		floor4.setAdjacentRoom(Action.ActionGoSouth, dirt);

		ArrayList<Room> list = new ArrayList<Room>();
		list.add(floor1);
		list.add(floor2);
		list.add(floor3);
		list.add(floor4);

		ArrayList<String> descriptions = new ArrayList<String>();
		descriptions.add("Inside Elevator -- floor 1.");
		descriptions.add("Inside Elevator -- floor 2");
		descriptions.add("Inside Elevator -- floor 3");
		descriptions.add("Inside Elevator -- floor 4");
		
		elevator.setFloors(descriptions, list, Action.ActionGoEast, 1);
		ArrayList<Integer> restrictedFloors = new ArrayList<Integer>();
		restrictedFloors.add(2);
		elevator.setRestrictedFloors(restrictedFloors);


		Room gitcExterior = new Room(gitcExteriorDescription, gitcExteriorShortDescription);
		gitcExterior.setAdjacentRoom(Action.ActionGoNorth, hallway1);
		ItemGold gold = (ItemGold)Item.getInstance("gold");
		gold.setValue(10);
		gitcExterior.putItem(gold);


		Room bathroom = new Room(bathroomDescription, bathroomShortDescription);
		hallway.setAdjacentRoom(Action.ActionGoEast, stairwell);
		hallway.setAdjacentRoom(Action.ActionGoWest, conference);
		hallway.setAdjacentRoom(Action.ActionGoNorth, bathroom);
		hallway.setAdjacentRoom(Action.ActionGoNortheast, dirt);

		bathroom.putItem(Item.getInstance("grafitti"));
		
		Item fridge = Item.getInstance("fridge");
		RoomObscured passage = new RoomObscured(passageDescription ,passageShortDescription, fridge);
		passage.setObscureMessage("You have revealed a secret passage to the east!");
		passage.putItem(Item.getInstance("torch"));
		fridge.setRelatedRoom(passage);

		RoomDark corridor = new RoomDark(corridorDescription, corridorShortDescription, corridorDarkDescription, corridorDarkShortDescription, true);
		corridor.setSafeDirection(Action.ActionGoWest);
		corridor.setDeathMessage("As you take your first step within the dark room, you trip on a mysterious object. You fall toward the floor, and hit your head against a large rock.");
		corridor.setAdjacentRoomTransitionMessage("You proceed back toward the dim light of the dim torches", Action.ActionGoWest);
		
		Room waterslide = new Room(waterslideDescription, waterslideShortDescription);
		waterslide.setAdjacentRoomTransitionMessageWithDelay("Wee!!", Action.ActionGoDown, 1000);

		waterslide.setOneWayAdjacentRoom(Action.ActionGoDown, floor1);
		waterslide.setAdjacentRoom(Action.ActionGoWest, corridor);
		passage.setAdjacentRoom(Action.ActionGoEast, corridor);

		LinkedList<Item> items = new LinkedList<Item>();
		//items.add(Item.ItemLightSwitch);
		items.add(Item.getInstance("flashlight"));
		items.add(Item.getInstance("key"));
		items.add(Item.getInstance("shovel"));
		items.add(Item.getInstance("hole"));
		ItemRMS wax = (ItemRMS)Item.getInstance("rms");
		ItemDiamond diamond = (ItemDiamond)Item.getInstance("diamond");
		diamond.setValue(10);
		wax.setMeltItem(diamond);
		items.add(wax);
		items.add(Item.getInstance("microwave"));
		fridge.setInspectMessage("It appears to be leaking from the bottom. Perhaps you should get a better look.");
		items.add(fridge);
		RoomDark acm = new RoomDark(acmDescription, acmShortDescription, acmDarkDescription, acmDarkShortDescription, false);
		acm.putItems(items);
		acm.setAdjacentRoom(Action.ActionGoNorth, hallway);
		acm.setAdjacentRoom(Action.ActionGoEast, passage);
		acm.setDeathMessage("As you take your first step within the dark room, you trip on a mysterious object. You fall toward the floor, and hit your head against a large rock.");
		
		roof.setOneWayAdjacentRoom(Action.ActionGoEast, acm);
		return acm;
		
	}

	public static Room mission() {


		String planeDescription = "You are on an airplane. You are on a top secret mission. Your objective is to parachute into a field, nearby a CIA complex, and retrieve the dossier on Amrid Al-Asad. You must hurry, because B-2 Bombers are on their way to annihilate the complex.";
		String planeShortDescription = "Mercenary Airplane";

		String skyDescription = "You are overcome by the sensation of wind rushing into your face at terminal velocity. You are decending quickly. You will need to slow yourself down if you want to survive the fall.";
		String skyShortDescription = "Skydiving";
		String skyLanding = "You land softly in the grass.";
		String broken = "You deploy your parachute, and your feelings of fear immediately turn into bliss. You admire the view from here, as you gracefully decend toward the soft brush field below.";

		String landingDescription = "You are in a meadow, surrounded by shoulder high brush on all sides. In the distance, you can see the top secret complex to the North.";
		String landingShortDescription = "Landing site.";

		String warning = "You are likely to be seen by a sniper";
		String shortWarning = "Danger";
		String deathMessage = "You were spotted, and killed instantly by a trained sniper.";

		String mine = "Landmine -- boom.";
		String shortMine = "Landmine";
		String mineDeath = "As you make your approach, you notice something shiny. Upon closer inspection, you realize it is a landmine. It explodes.";

		String approachDescription = "You are nearing the complex, but as you move closer you realize that there are trained snipers watching the field. On your approach, you also dodge a landmine by mere inches. You suspect there are more in the field. You will need a way to proceed without being spotted.";
		String approachShort = "You can see the CIA complex in the distance";
			
		String f1d= "You are prone in a grass field. It is difficult to see over the brush, so you are crawling blindly.";
		String fShort = "Sniper-watched field";

		String f2d = "You are prone in a grass field. You can feel the sweat dripping down your forehead.";
		
		String f3d = "You are prone in a grass field. The annoying flies are buzzing around your head.";
		String f11d = "You are prone in a grass field. You nearly have a heart attack because you thought you were prone on a landmine, but it was just igneous rock.";

		String f32d = "You are prone in a grass field. You are very close to the complex.";
	
		String compWD = "You are at the base of a fence on West end of the complex. A steep cliff is to your left.";
		String compWSD = "West end of complex exterior.";
		String compD = "You are at the base of a fence in the center of the complex. You will be spotted if you attempt to climb the fence. You will need to find another way.";
		String compSD = "Center of complex exterior.";
		String compED = "You are at the base of a fence on East end of the complex. You will be spotted if you attempt to climb the fence. There appears to be an entrance to the complex to the East."; 
		String compESD = "East end of complex exterior.";

		String compEntranceD = "The complex entrance is now in sight. There is an entrance booth to the North.";
		String compEntranceSD = "Complex exterior..";

		String compFrontD= "You are almost at the entrance booth, which is a just a few more steps to the North. There is a sleeping guard inside it. There is also an electrical junction box and power meter to the West.";
		String compFrontSD= "Complex Entrance front.";

		String compElectricD= "You are in front of an electrical junction box. There are very thick cables traveling into it. It requires authentication to open it.";
		String compElectricSD= "Junction Box.";

		String boothD = "You are inside the operator's booth.";
		String boothSD = "Operator Booth.";

		String eBoothD = "You take cover behind the eastern-most concrete wall of the complex. There appears to be a service entrance to the North.";
		String eBoothSD = "Eastern wall.";

		String serviceED = "You are a few meters away from a service entrance, to the West. The door is slightly cracked open. The lights are out, and you may proceed completely undetected.";
		String serviceESD = "Service entrance";
		String serviceEDD = "You are a few meters awway from a service entrance, to the West. There is a lookout spotlight overhead. The chances of being spotted are likely.";
		String serviceEDSD = "Service entrance.";

		String boxDestroy = "The wires short circuit and the complex lights go out. The guards appear to be on high alert.";

		String serviceD = "You are inside the complex. The room has no lights, except for a dim emergency light above the loading area. There is a door to the West.";
		String serviceSD = "Service area.";
		

		String breakRoomD= "You are in the break room. It smells like day-old coffee in here. There is a doorway to the East.";
		String breakRoomSD = "Break room.";

		String hallD = "You are at the South end of a long hallway. There are doors to the East and West.";
		String hallSD = "South end of long hallway.";

		String nhallD = "You are at the North end of a long hallway. There are doors to the West and to the North.";
		String nhallSD = "North end of long hallway.";

		String centerD = "You are in the Command Center. You are surrounded by giant television screens. The backup power must have kicked in, since the TV's are on. You see yourself on one of the screens, and you wave to yourself. There is a room to the West.";
		String centerSD = "Command Center.";

		String cOfficeD = "You are in a room with no furniture. An air duct leads to the South, but it is being blocked by the metal grate cover.";
		String cOfficeSD = "Furnitureless room.";

		String duct1D = "The ducts appear to deviate in all directions.";
		String duct1SD = "Air Duct.";

		String duct2D = "The ducts appear to deviate in all directions.";
		String duct2SD = "Air Duct.";

		String duct3D = "The ducts appear to deviate in all directions.";
		String duct3SD = "Air Duct.";

		String duct4D = "The ducts appear to deviate in all directions.";
		String duct4SD = "Air Duct.";

		String duct5D = "The ducts appear to deviate in all directions.";
		String duct5SD = "Air Duct.";

		String duct6D = "The ducts appear to deviate in all directions. There is a loud whirring noise to the West.";
		String duct6SD = "Air Duct.";

		String duct7D = "The whirring noise seems to be getting louder.";
		String duct7SD = "Air Duct.";

		String duct8D = "There is a hole in the shaft. You can drop down.";
		String duct8SD = "Air Duct.";
		String duct8WD = "The whirring noise is extremely loud.";
		String duct8WSD = "Air Duct.";


		String aliensHallD = "You are at the West end of a hallway. There are four large tubes, containing cryogenically frozen life forms. ";
		String aliensHallSD = "West end of hallway.";

		String aliensAD = "You admire the solace of the being in the tubes. Upon closer inspection, you realize it is of the likeness of Richard Stallman, the author of the famous emacs text editor. You notice he isn't wearing any shoes.";
		String aliensASD = "Tube A.";

		String aliensBD = "This one is of the likeness of Julian Assange, the infamous Wikileaks founder.";
		String aliensBSD = "Tube B.";

		String aliensCD = "This one is of the likeness of Barack Obama.";
		String aliensCSD = "Tube C.";

		String aliensDD = "This one is of the likeness of Walt Disney.";
		String aliensDSD = "Tube D.";

		String aliens2D = "You are at the East end of the long hallway. There is a room to the North.";
		String aliens2SD = "East end of hallway.";

		String teleED = "You are in a completely white room. There is a desk to your left, and a large machine in front of you with a steel podium.";
		String teleESD = "White Room.";

		String teleD = "You are inside a teleportation machine. There are various beeping noises coming from the electronics. There are several destination buttons on the wall.";
		String teleSD = "Teleport Machine.";

		String archivesD = "You are in the archives. There are many shelves to the East, all of which are several feet high. Al-Asad's file must be in here somewhere...";
		String archivesSD = "Archives.";

		String archivesAFD = "You are standing in front of shelves with documents on CIA suspects with last names A-F. Al-Asads folder is sitting on the shelf.";
		String archivesAFSD = "CIA Suspects A-F.";

		String archivesGMD = "You are standing in front of shelves with documents on CIA suspects with last names G-M.";
		String archivesGMSD = "CIA Suspects G-M.";

		String archivesNTD = "You are standing in front of shelves with documents on CIA suspects with last names N-T.";
		String archivesNTSD = "CIA Suspects N-T.";

		String archivesUZD = "You are standing in front of shelves with documents on CIA suspects with last names U-Z.";
		String archivesUZSD = "CIA Suspects U-Z.";
		
		String archivesEndD = "You are in a reading area with tables and chairs. There are offices to the North, East, and South.";
		String archivesEndSD = "Reading Area.";


		String rooftopD = "You are on the roof of the complex. The chopper is hovering at the edge of the roof several meters to the North.";
		String rooftopSD = "Complex Rooftop.";
		String rooftopWD = "You have not completed the mission. The dossier is still somewhere inside the complex. You can crawl into a ventilation duct to the East, and it will lead you back into the complex.";
		String rooftopWSD = "Go back and retrieve the dossier.";
	
		String roofEdgeD = "The chopper is hovering steadily. Jump for the chopper!";
		String roofEdgeSD = "Jump for the chopper!";

		String chopperTransitionMessage = "You sprint the last few meters toward the edge of the roof, and jump to the chopper. You manage to grab hold of the landing strut with one hand, as the B-2 Bombers obliterate the complex.";
		String chopperD = "You are hanging from the edge of the helicopter. Climb yourself up to enter the cabin.";
		String chopperSD = "You are hanging by one arm on the chopper landing strut.";

		String cabinD = "";
		String cabinDeathMessage = "You are pulled into the chopper by a comrade's arm. You are now inside the chopper. You produce the dossier, and hand it off to a crew member. As they take it from you, you identify them to be Amrid Al-Asad. He stabs you in the stomach with a knife, and throws your lifeless body out the door of the chopper.";
		String cabinSD = "Chopper Cabin.";
			
		String officeD = "You are in an office. It seems that the occupant has only recently left.";
		String officeSD = "Office.";

		String locked1D= "How the hell did you get in here?";
		String locked1SD = "Type 'look'";
		String locked2D = "How the hell did you get in here?";
		String locked2SD = "Type 'look'";


		Room plane = new Room(planeDescription, planeShortDescription);
		
		RoomSky sky = new RoomSky(skyDescription, skyShortDescription, 5);
		sky.setLandMessage(skyLanding);
		sky.setFallBrokenMessage(broken);
		
		plane.setOneWayAdjacentRoom(Action.ActionGoDown, sky);
		Item chute = Item.getInstance("parachute");
		chute.setRelatedRoom(sky);
		plane.putItem(chute);
		ItemWatch watch = (ItemWatch)Item.getInstance("watch");
		ItemWatchMenu main = new ItemWatchMenu("CIA SmartWatch V3.2.6.");

		String watchtext = "";
		try {
			File f = new File("commissioner.txt");
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()) {
				watchtext = watchtext + scan.nextLine() + "\n";
			}
		}
		catch(FileNotFoundException e) {
			// pass
		}
		main.setText(watchtext);
		ItemWatchMenu sub1 = new ItemWatchMenu("Objectives");
		sub1.setText(" * Steal dossier on Amrid Al-Asad.\n * Get the hell out of there!");
		ItemWatchMenu sub2 = new ItemWatchMenu("About the author");
		sub2.setText("This game was written by Skyler Lutz. Feel free to contribute to this game at www.github.com/SkylerLutz/adventure.git.");
		main.add(sub1);
		main.add(sub2);
		watch.setMenu(main);
	
		//plane.putItem(watch);

		Room landing = new Room(landingDescription, landingShortDescription);
		sky.setOneWayAdjacentRoom(Action.ActionGoDown, landing);
		Item suit = Item.getInstance("suit"); 
		landing.putItem(suit);

		RoomRequiredItem approach= new RoomRequiredItem(approachDescription, approachShort, warning, shortWarning, suit);
		approach.setAdjacentRoom(Action.ActionGoSouth, landing);
		approach.setSafeDirection(Action.ActionGoSouth);
		approach.setDeathMessage(deathMessage);

		RoomRequiredItem f1dr = new RoomRequiredItem(f1d, fShort, warning, shortWarning, suit);
		f1dr.setAdjacentRoom(Action.ActionGoSoutheast, approach);
		f1dr.setDeathMessage(deathMessage);

		RoomRequiredItem f2dr = new RoomRequiredItem(f2d, fShort, warning, shortWarning, suit);
		f2dr.setAdjacentRoom(Action.ActionGoSouth, approach);
		f2dr.setDeathMessage(deathMessage);

		RoomRequiredItem f3dr = new RoomRequiredItem(f3d, fShort, warning, shortWarning, suit);
		f3dr.setAdjacentRoom(Action.ActionGoSouthwest, approach);
		f3dr.setDeathMessage(deathMessage);
		f2dr.setAdjacentRoom(Action.ActionGoWest, f1dr);
		f2dr.setAdjacentRoom(Action.ActionGoEast, f3dr);

		RoomRequiredItem mineRoom1 = new RoomRequiredItem(mine, shortMine, Item.getInstance("armor"));
		mineRoom1.setAdjacentRoom(Action.ActionGoSoutheast, f2dr);
		mineRoom1.setAdjacentRoom(Action.ActionGoSouth, f1dr);
		mineRoom1.setDeathMessage(mineDeath);
		mineRoom1.setPlayerDiesOnEntry(true);

		RoomRequiredItem mineRoom2 = new RoomRequiredItem(mine, shortMine, Item.getInstance("armor"));
		mineRoom2.setAdjacentRoom(Action.ActionGoSouthwest, f1dr);
		mineRoom2.setAdjacentRoom(Action.ActionGoSouth, f2dr);
		mineRoom2.setAdjacentRoom(Action.ActionGoSoutheast, f3dr);
		mineRoom2.setDeathMessage(mineDeath);
		mineRoom2.setPlayerDiesOnEntry(true);

		RoomRequiredItem mineRoom3 = new RoomRequiredItem(mine, shortMine, Item.getInstance("armor"));
		mineRoom3.setAdjacentRoom(Action.ActionGoSouth, f3dr);
		mineRoom3.setAdjacentRoom(Action.ActionGoSouthwest, f2dr);
		mineRoom3.setDeathMessage(mineDeath);
		mineRoom3.setPlayerDiesOnEntry(true);

		mineRoom2.setAdjacentRoom(Action.ActionGoWest, mineRoom1);
		mineRoom2.setAdjacentRoom(Action.ActionGoEast, mineRoom3);

		RoomRequiredItem f11dr = new RoomRequiredItem(f11d, fShort, warning, shortWarning, suit);
		f11dr.setAdjacentRoom(Action.ActionGoSoutheast, f1dr);
		f11dr.setAdjacentRoom(Action.ActionGoEast, mineRoom1);
		f11dr.setDeathMessage(deathMessage);
		
		RoomRequiredItem f32dr = new RoomRequiredItem(f32d, fShort, warning, shortWarning, suit);
		f32dr.setAdjacentRoom(Action.ActionGoSouthwest, f3dr);
		f32dr.setAdjacentRoom(Action.ActionGoWest, mineRoom3);
		f32dr.setDeathMessage(deathMessage);
		
		RoomRequiredItem complexW = new RoomRequiredItem(compWD, compWSD, warning, shortWarning, suit);
		RoomRequiredItem complex = new RoomRequiredItem(compD, compSD, warning, shortWarning, suit);
		RoomRequiredItem complexE = new RoomRequiredItem(compED, compESD, warning, shortWarning, suit);
		RoomRequiredItem fence = new RoomRequiredItem("", "", Item.getInstance("unknown"));
		fence.setPlayerDiesOnEntry(true);
		fence.setDeathMessage("You begin to climb the fence, and a sniper shoots you in the head.");
		complex.setAdjacentRoom(Action.ActionGoWest, complexW);
		complex.setAdjacentRoom(Action.ActionGoEast, complexE);
		complexW.setAdjacentRoom(Action.ActionGoSouth, f11dr);
		complexW.setAdjacentRoom(Action.ActionGoSoutheast, mineRoom2);
		complex.setAdjacentRoom(Action.ActionGoSouth, mineRoom2);
		complexE.setAdjacentRoom(Action.ActionGoSouth, f32dr);
		complexE.setAdjacentRoom(Action.ActionGoSouthwest, mineRoom2);
		complex.setDeathMessage(deathMessage);
		complexE.setDeathMessage(deathMessage);
		complexW.setDeathMessage(deathMessage);

		complexW.setOneWayAdjacentRoom(Action.ActionGoUp, fence);
		complex.setOneWayAdjacentRoom(Action.ActionGoUp, fence);
		complexE.setOneWayAdjacentRoom(Action.ActionGoUp, fence);

		RoomRequiredItem compEntrance = new RoomRequiredItem(compEntranceD, compEntranceSD, warning, shortWarning, suit);
		compEntrance.setAdjacentRoom(Action.ActionGoWest, complexE);
		compEntrance.setDeathMessage(deathMessage);

		RoomRequiredItem compFront = new RoomRequiredItem(compFrontD, compFrontSD, warning, shortWarning, suit);
		compFront.setAdjacentRoom(Action.ActionGoSouth, compEntrance);
		compFront.setDeathMessage(deathMessage);
		
		RoomRequiredItem compElectric = new RoomRequiredItem(compElectricD, compElectricSD, warning, shortWarning, suit);
		compElectric.setAdjacentRoom(Action.ActionGoEast, compFront);
		compElectric.setDeathMessage(deathMessage);

		ItemJunctionBox box = (ItemJunctionBox)Item.getInstance("box");
		box.setVisible(false);
		box.setDestroyMessage(boxDestroy);
		ItemKeycardReader reader = (ItemKeycardReader)Item.getInstance("reader");
		reader.setRelatedItem(box);
		reader.setInstallMessage("Authentication Complete.\nThe junction box door swings open");
		compElectric.putItem(box);
		compElectric.putItem(reader);

		Room booth = new Room(boothD, boothSD);
		booth.setAdjacentRoom(Action.ActionGoSouth, compFront);
		ItemGuard guard = (ItemGuard)Item.getInstance("guard");
		guard.install(Item.getInstance("card"));
		guard.setRelatedRoom(booth);
		guard.setDeathMessage("You fight with the guard, as you attempt to strangle the life out of him. You can see his life flashing before his eyes. His eyes roll back and his tongue is ejected from his mouth. He is dead.");
		booth.putItem(guard);

		RoomRequiredItem eBooth = new RoomRequiredItem(eBoothD, eBoothSD, warning, shortWarning, suit);
		eBooth.setAdjacentRoom(Action.ActionGoWest, compFront);

		RoomDark serviceE = new RoomDark(serviceED, serviceESD, serviceEDD, serviceEDSD, true);
		serviceE.setAdjacentRoom(Action.ActionGoSouth, eBooth);
		serviceE.setSafeDirection(Action.ActionGoSouth);
		serviceE.setDeathMessage("As you take your first step, you suddenly become engulfed in a bright light. You look up and see a guard shining a serach light on you. He shoots you in the head.");
		box.setRelatedRoom(serviceE);

		Room service = new Room(serviceD, serviceSD);
		service.setAdjacentRoom(Action.ActionGoEast, serviceE);


		Room hall = new Room(hallD, hallSD);
		hall.setAdjacentRoom(Action.ActionGoEast, service);

		Room breakRoom = new Room(breakRoomD, breakRoomSD);
		breakRoom.setAdjacentRoom(Action.ActionGoEast, hall);
		breakRoom.putItem(Item.getInstance("machine"));


		Room nhall = new Room(nhallD, nhallSD);
		nhall.setAdjacentRoom(Action.ActionGoSouth, hall);
		
		Room center = new Room(centerD, centerSD);
		center.setAdjacentRoom(Action.ActionGoEast, nhall);

		Room cOffice = new Room(cOfficeD, cOfficeSD);
		cOffice.setAdjacentRoom(Action.ActionGoEast, center);
		Item grate = Item.getInstance("grate");
		grate.setInspectMessage("It looks like there is something behind the grate.");
		cOffice.putItem(grate);
		cOffice.putItem(Item.getInstance("pole"));
		

		RoomObscured duct1 = new RoomObscured(duct1D, duct1SD, grate);
		duct1.setAdjacentRoom(Action.ActionGoNorth, cOffice);
		grate.setRelatedRoom(duct1);

		Room duct2 = new Room(duct2D, duct2SD);
		duct2.setAdjacentRoom(Action.ActionGoNorth, duct1);
		
		Room duct3 = new Room(duct3D, duct3SD);
		duct3.setAdjacentRoom(Action.ActionGoEast, duct1);
		
		Room duct4 = new Room(duct4D, duct4SD);
		duct4.setAdjacentRoom(Action.ActionGoWest, duct1);
		
		Room duct5 = new Room(duct5D, duct5SD);
		duct5.setAdjacentRoom(Action.ActionGoNorth, duct2);
		
		Room duct6 = new Room(duct6D, duct6SD);
		duct6.setAdjacentRoom(Action.ActionGoEast, duct5);
		
		Room duct7 = new Room(duct7D, duct7SD);
		duct7.setAdjacentRoom(Action.ActionGoEast, duct6);
		
		
		RoomLockable duct8 = new RoomLockable(duct8D, duct8SD, true, Item.getInstance("pole"));
		duct8.setAdjacentRoom(Action.ActionGoEast, duct7);
		duct8.setCausesDeath(true, "You are chopped up into a million bite-size pieces.");
		duct8.setUnlockMessage("You broke the fan. The ventilation shaft appears to continue behind it.");
		Item fan = Item.getInstance("fan");
		fan.setRelatedRoom(duct8);
		duct7.putItem(fan);

		Room aliens = new Room(aliensHallD, aliensHallSD);
		duct8.setOneWayAdjacentRoom(Action.ActionGoDown, aliens);
		duct8.setAdjacentRoomTransitionMessage("You fall for a few seconds, and land on a pillow. You come to your senses, and stand up.", Action.ActionGoDown);
		
		
		Room aliensA = new Room(aliensAD, aliensASD);
		aliensA.setAdjacentRoom(Action.ActionGoWest, aliens);

		Room aliensB = new Room(aliensBD, aliensBSD);
		aliensB.setAdjacentRoom(Action.ActionGoWest, aliensA);

		Room aliensC = new Room(aliensCD, aliensCSD);
		aliensC.setAdjacentRoom(Action.ActionGoWest, aliensB);

		Room aliensD = new Room(aliensDD, aliensDSD);
		aliensD.setAdjacentRoom(Action.ActionGoWest, aliensC);


		Room aliens2 = new Room(aliens2D, aliens2SD);
		aliens2.setAdjacentRoom(Action.ActionGoWest, aliensD);

		Room teleE = new Room(teleED, teleESD);
		teleE.setAdjacentRoom(Action.ActionGoSouth, aliens2);
		Item list = Item.getInstance("list");	
		list.setInspectMessage("GUEST LIST:\n\nJoe Biden\nSkyler Lutz\nMichael J. Fox\n\n\n");
		teleE.putItem(list);

		Room tele = new Room(teleD, teleSD);
		tele.setAdjacentRoom(Action.ActionGoSouth, teleE);


		RoomRequiredItem rooftop = new RoomRequiredItem(rooftopD, rooftopSD, rooftopWD, rooftopWSD, Item.getInstance("document"));
		rooftop.setOneWayAdjacentRoom(Action.ActionGoEast, breakRoom);
		rooftop.setSafeDirection(Action.ActionGoEast);
		rooftop.setDeathMessage("The complex exploded before you could escape with the dossier. You have failed your mission.");

		Room roofEdge = new Room(roofEdgeD, roofEdgeSD);
		roofEdge.setAdjacentRoom(Action.ActionGoSouth, rooftop);

		Room chopper = new Room(chopperD, chopperSD);
		roofEdge.setOneWayAdjacentRoom(Action.ActionGoDown, chopper);
		roofEdge.setAdjacentRoomTransitionMessage(chopperTransitionMessage, Action.ActionGoDown);

		RoomRequiredItem cabin = new RoomRequiredItem(cabinD, cabinSD, cabinD, cabinSD, Item.getInstance("document"));
		chopper.setOneWayAdjacentRoom(Action.ActionGoUp, cabin);
		cabin.setDeathMessage(cabinDeathMessage);
		cabin.setPlayerDiesOnEntry(true);
		

		String pushMessage = "The beeping stops. A red light completely blinds you. You travel through space and time, and land on your stomach. Somewhat disoriented, you stand up.\n";
		Room school = njit();
		Room dunnet = level1();
		Item schoolB = Item.getInstance("njit");
		schoolB.setRelatedRoom(school);
		((ItemButton)schoolB).setPushMessage(pushMessage);
		Item dunnetB = Item.getInstance("dunnet");
		dunnetB.setRelatedRoom(dunnet);
		((ItemButton)dunnetB).setPushMessage(pushMessage);
		Item rooftopB = Item.getInstance("rooftop");
		rooftopB.setRelatedRoom(rooftop);
		((ItemButton)rooftopB).setPushMessage(pushMessage);
		Item obamaB = Item.getInstance("obama");
		obamaB.setRelatedRoom(aliensC);
		((ItemButton)obamaB).setPushMessage(pushMessage);
		Item centerB = Item.getInstance("center");
		centerB.setRelatedRoom(center);
		((ItemButton)centerB).setPushMessage(pushMessage);
		
		//tele.putItem(schoolB);
		//tele.putItem(dunnetB);
		tele.putItem(rooftopB);
		tele.putItem(obamaB);
		tele.putItem(centerB);
		

		Room archives= new Room(archivesD, archivesSD);
		archives.setAdjacentRoom(Action.ActionGoSouth, nhall);

		Room archivesAF = new Room(archivesAFD, archivesAFSD);
		archivesAF.setAdjacentRoom(Action.ActionGoWest, archives);

		String openMessage ="The folder is empty. The contents must have been removed."; 
		Item folder = Item.getInstance("folder");
		folder.setInspectMessage(openMessage);
		((ItemFolder)folder).setOpenMessage(openMessage);
		archivesAF.putItem(folder);

		Room archivesGM = new Room(archivesGMD, archivesGMSD);
		archivesGM.setAdjacentRoom(Action.ActionGoWest, archivesAF);

		Room archivesNT = new Room(archivesNTD, archivesNTSD);
		archivesNT.setAdjacentRoom(Action.ActionGoWest, archivesGM);

		Room archivesUZ = new Room(archivesUZD, archivesUZSD);
		archivesUZ.setAdjacentRoom(Action.ActionGoWest, archivesNT);

		Room archivesEnd = new Room(archivesEndD, archivesEndSD);
		archivesEnd.setAdjacentRoom(Action.ActionGoWest, archivesUZ);

		RoomLockable locked1 = new RoomLockable(locked1D,locked1SD, true, null);
		locked1.setAdjacentRoom(Action.ActionGoSouth, archivesEnd);
		
		RoomLockable locked2 = new RoomLockable(locked2D,locked2SD, true, null);
		locked2.setAdjacentRoom(Action.ActionGoWest, archivesEnd);

		Room office = new Room(officeD, officeSD);
		office.setAdjacentRoom(Action.ActionGoNorth, archivesEnd);
		ItemComputer computer = (ItemComputer)Item.getInstance("computer");
		computer.setInspectMessage("You flip the computer's keyboard over, and unsuprisingly encounter a yellow sticky note. It reads:\n\n9292\n");
		ItemSafe safe = (ItemSafe)Item.getInstance("safe");
		safe.setInspectMessage("This safe appears to require a 4 digit PIN number.");
		safe.setPIN("9292");
		Item document = Item.getInstance("document");
		document.setVisible(false);
		document.setInspectMessage("The document is encrypted with a cipher. The cryptographers at the CIA will need to decrypt it.");
		safe.install(document);
		office.putItem(safe);
		office.putItem(computer);
		office.putItem(Item.getInstance("coffee"));
		office.putItem(Item.getInstance("light"));
		return office;
	}
}
