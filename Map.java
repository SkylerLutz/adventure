import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Map {

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

		String acmDescription = "You are in the ACM office. It is filled with students";
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
		ItemRMS wax = (ItemRMS)Item.getInstance("rms");
		ItemDiamond diamond = (ItemDiamond)Item.getInstance("diamond");
		diamond.setValue(10);
		wax.setMeltItem(diamond);
		items.add(wax);
		items.add(Item.getInstance("microwave"));
		items.add(Item.getInstance("fridge"));
		RoomDark acm = new RoomDark(acmDescription, acmShortDescription, acmDarkDescription, acmDarkShortDescription, false);
		acm.putItems(items);
		acm.setAdjacentRoom(Action.ActionGoNorth, hallway);
		acm.setAdjacentRoom(Action.ActionGoEast, passage);
		acm.setDeathMessage("As you take your first step within the dark room, you trip on a mysterious object. You fall toward the floor, and hit your head against a large rock.");
		
		roof.setOneWayAdjacentRoom(Action.ActionGoEast, acm);
		return acm;
		
	}

	public static Room mission() {


		String planeDescription = "You are on an airplane. You are on a top secret mission. Your objective is to parachute into a field, nearby a CIA complex, and retrieve the dossier on Amrid Al-Asad. You have twenty minutes to complete the mission, otherwise Seal Team 6 will need to leave without you.";
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
	
		String compWD = "You are at the base of a fence on West end of the complex. You will be spotted if you attempt to climb the fence. You will need to find another way.";
		String compWSD = "West";
		String compD = "You are at the base of a fence in the center of the complex. You will be spotted if you attempt to climb the fence. You will need to find another way.";
		String compSD = "Center";
		String compED = "You are at the base of a fence on East end of the complex. You will be spotted if you attempt to climb the fence. There appears to be an entrance to the complex to the East."; 
		String compESD = "East";

		String compEntranceD = "The complex entrance is now in sight. There is a sleeping guard in the entrance booth. He may need to be taken care of...";
		String compEntranceSD = "Complex Entrance Exterior.";

		String compFrontD= "You are almost at the entrance booth. There is an electrical junction box and power meter to the west.";
		String compFrontSD= "Complex Entrance front.";

		String compElectricD= "You are in front of an electrical junction box. There are very thick cables traveling into it.";
		String compElectricSD= "Junction Box";

		String boothD = "You are inside the operator's booth.";
		String boothSD = "Operator Booth";

		String eBoothD = "You take cover behind the eastern-most concrete wall of the complex. There appears to be a service entrance to the North.";
		String eBoothSD = "Eastern wall.";

		String serviceED = "You are a few meters away from a service entrance, to the West. The door is slightly cracked open. The lights are out, and you may proceed completely undetected.";
		String serviceESD = "Service entrance";
		String serviceEDD = "You are a few meters awway from a service entrance, to the West. There is a lookout spotlight overhead. The chances of being spotted are likely.";
		String serviceEDSD = "Service entrance.";

		String boxDestroy = "The wires short circuit and the complex lights go out. The guards appear to be on high alert.";

		String serviceD = "You are inside the complex. The room has no lights, except for a dim emergency light above the loading area. The security cameras have been disabled.";
		String serviceSD = "Service area.";
		



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
	
		plane.putItem(watch);

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
		complexE.putItem(suit);

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
		//return complexE;
		return plane;
	}
}
