import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

public class Map {

	public static Room level1() {

		Room mid1 = new Room("There is a fork", "Fork");
		Room mid2 = new Room("Ferocious bear", "bear");
		Room end = new RoomLockable("You are inside of a building", "Building interior", true, Item.ItemKey);
		
		end.setAdjacentRoom(Action.ActionGoNortheast, mid1);
		
		LinkedList<Item> startItems = new LinkedList<Item>();
		startItems.add(Item.ItemBrick);
		startItems.add(Item.ItemKey);
		startItems.add(Item.ItemLock);
		
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

		String dirtRoomDescription = "There is a pile of dirt here.";
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
		hallway.putItem(Item.ItemLock);

		Room hallway2 = new Room(hallway2Desription, hallway2ShortDescription);
		Room hallway1 = new Room(hallway1Desription, hallway1ShortDescription);

		Room conference = new Room(conferenceRoomDescription, conferenceRoomShortDescription);
		RoomLockable stairwell = new RoomLockable(stairwellDescription, stairwellShortDescription, true, Item.ItemKey);
		Room stairwell3 = new Room(stairwell3Description, stairwell3ShortDescription);
		stairwell3.setAdjacentRoom(Action.ActionGoUp, stairwell);
		Room stairwell2 = new Room(stairwell2Description, stairwell2ShortDescription);
		stairwell2.setAdjacentRoom(Action.ActionGoUp, stairwell3);
		stairwell2.setAdjacentRoom(Action.ActionGoWest, hallway2);
		Room stairwell1 = new Room(stairwell1Description, stairwell1ShortDescription);
		stairwell1.setAdjacentRoom(Action.ActionGoUp, stairwell2);
		stairwell1.setAdjacentRoom(Action.ActionGoWest, hallway1);
		

		Room roof = new Room(roofDescription, roofShortDescription);
		roof.setAdjacentRoomTransitionMessage("You miss the roof and fall through the floor. Since everyone is using their headphones, nobody seems to notice.", Action.ActionGoEast);
		roof.setAdjacentRoom(Action.ActionGoDown, stairwell);

		RoomExcavatable dirt = new RoomExcavatable(dirtRoomDescription, dirtRoomShortDescription, dirtRoomRevealMessage);
		LinkedList<Item> revealableItems = new LinkedList<Item>();
		Item i = Item.ItemClayPot;
		i.setInstalledItem(Item.ItemDiamond);
		i.setInstalledItemVisible(false);
		i.setDestroyMessage("You revealed a beautiful diamond!");
		revealableItems.add(i);
		dirt.setRevealableItems(revealableItems);


		RoomElevator elevator = new RoomElevator(elevatorDescription, elevatorShortDescription);
		elevator.putItem(Item.ItemElevatorFloor1Button);
		elevator.putItem(Item.ItemElevatorFloor2Button);
		elevator.putItem(Item.ItemElevatorFloor3Button);
		elevator.putItem(Item.ItemElevatorFloor4Button);
		
		
		Room floor1 = new Room(floor1Description, floor1ShortDescription ); 
		Item b1 = Item.ItemElevatorButton;
		b1.setRelatedRoom(elevator);
		floor1.putItem(b1);
		floor1.setAdjacentRoom(Action.ActionGoSouth, hallway1);

		Room floor2 = new Room(floor2Description, floor2ShortDescription); 
		Item b2 = Item.ItemElevatorButton;
		b2.setRelatedRoom(elevator);
		floor2.putItem(b2);
		floor2.setAdjacentRoom(Action.ActionGoSouth, hallway2);

		Room floor3 = new Room(floor3Description, floor3ShortDescription); 
		Item b3 = Item.ItemElevatorButton;
		b3.setRelatedRoom(elevator);
		floor3.putItem(b3);

		Room floor4 = new Room(floor4Description, floor4ShortDescription); 
		Item b4 = Item.ItemElevatorButton;
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
		descriptions.add("Inside elevator -- floor 2");
		descriptions.add("Inside elevator -- floor 3");
		descriptions.add("Inside elevator -- floor 4");
		
		elevator.setFloors(descriptions, list, Action.ActionGoEast, 1);
		ArrayList<Integer> restrictedFloors = new ArrayList<Integer>();
		restrictedFloors.add(2);
		elevator.setRestrictedFloors(restrictedFloors);


		Room gitcExterior = new Room(gitcExteriorDescription, gitcExteriorShortDescription);
		gitcExterior.setAdjacentRoom(Action.ActionGoNorth, hallway1);
		gitcExterior.putItem(Item.ItemGold);


		Room bathroom = new Room(bathroomDescription, bathroomShortDescription);
		hallway.setAdjacentRoom(Action.ActionGoEast, stairwell);
		hallway.setAdjacentRoom(Action.ActionGoWest, conference);
		hallway.setAdjacentRoom(Action.ActionGoNorth, bathroom);
		hallway.setAdjacentRoom(Action.ActionGoNortheast, dirt);

		bathroom.putItem(Item.ItemGrafitti);
		
		Item fridge = Item.ItemFridge;
		RoomObscured passage = new RoomObscured(passageDescription ,passageShortDescription, fridge);
		fridge.setRelatedRoom(passage);

		RoomDark corridor = new RoomDark(corridorDescription, corridorShortDescription, corridorDarkDescription, corridorDarkShortDescription, true);
		corridor.setSafeDirection(Action.ActionGoWest);
		corridor.setAdjacentRoomTransitionMessage("You proceed back toward the dim light of the dim torches", Action.ActionGoWest);
		
		Room waterslide = new Room(waterslideDescription, waterslideShortDescription);
		waterslide.setAdjacentRoomTransitionMessageWithDelay("Wee!!", Action.ActionGoDown, 1000);

		waterslide.setAdjacentRoom(Action.ActionGoDown, floor1);
		waterslide.setAdjacentRoom(Action.ActionGoWest, corridor);
		passage.setAdjacentRoom(Action.ActionGoEast, corridor);

		LinkedList<Item> items = new LinkedList<Item>();
		//items.add(Item.ItemLightSwitch);
		items.add(Item.ItemFlashLight);
		items.add(Item.ItemKey);
		items.add(Item.ItemShovel);
		items.add(Item.ItemStatue);
		items.add(Item.ItemMicrowave);
		items.add(Item.ItemFridge);
		RoomDark acm = new RoomDark(acmDescription, acmShortDescription, acmDarkDescription, acmDarkShortDescription, false);
		acm.putItems(items);
		acm.setAdjacentRoom(Action.ActionGoNorth, hallway);
		acm.setAdjacentRoom(Action.ActionGoEast, passage);
		
		roof.setOneWayAdjacentRoom(Action.ActionGoEast, acm);
		return acm;
		
	}
	public static Room mission() {

		String planeDescription = "You are on an airplane. You are on a top secret mission. Your objective is to parachute into a field, nearby a CIA complex, and retrieve the dossier on Amrid Al-Asad. You have twenty minutes to complete the mission, otherwise Seal Team 6 will need to leave without you.";
		String planeShortDescription = "Mercenary Airplane";

		String skyDescription = "You are overcome by the sensation of wind rushing into your face at terminal velocity. You are decending quickly. You will need to slow yourself down if you want to survive the fall.";
		String skyShortDescription = "Skydiving";

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

		String compElectricD= "You are in front of an electrical junction box. There are very thick cables traveling into it. There is a keycard reader attached.";
		String compElectricSD= "Junction Box";

		String boothD = "You are inside the operator's booth.";
		String boothSD = "Operator Booth";



		Room plane = new Room(planeDescription, planeShortDescription);
		
		RoomSky sky = new RoomSky(skyDescription, skyShortDescription, 5);
		plane.setOneWayAdjacentRoom(Action.ActionGoDown, sky);
		Item chute = Item.ItemParachute;
		chute.setRelatedRoom(sky);
		plane.putItem(chute);

		Room landing = new Room(landingDescription, landingShortDescription);
		sky.setOneWayAdjacentRoom(Action.ActionGoDown, landing);
		landing.putItem(Item.ItemGhillieSuit);

		RoomRequiredItem approach= new RoomRequiredItem(approachDescription, approachShort, warning, shortWarning, Item.ItemGhillieSuit);
		approach.setAdjacentRoom(Action.ActionGoSouth, landing);
		approach.setSafeDirection(Action.ActionGoSouth);
		approach.setDeathMessage(deathMessage);

		RoomRequiredItem f1dr = new RoomRequiredItem(f1d, fShort, warning, shortWarning, Item.ItemGhillieSuit);
		f1dr.setAdjacentRoom(Action.ActionGoSoutheast, approach);
		f1dr.setDeathMessage(deathMessage);

		RoomRequiredItem f2dr = new RoomRequiredItem(f2d, fShort, warning, shortWarning, Item.ItemGhillieSuit);
		f2dr.setAdjacentRoom(Action.ActionGoSouth, approach);
		f2dr.setDeathMessage(deathMessage);

		RoomRequiredItem f3dr = new RoomRequiredItem(f3d, fShort, warning, shortWarning, Item.ItemGhillieSuit);
		f3dr.setAdjacentRoom(Action.ActionGoSouthwest, approach);
		f3dr.setDeathMessage(deathMessage);
		f2dr.setAdjacentRoom(Action.ActionGoWest, f1dr);
		f2dr.setAdjacentRoom(Action.ActionGoEast, f3dr);

		RoomRequiredItem mineRoom1 = new RoomRequiredItem(mine, shortMine, Item.ItemArmor);
		mineRoom1.setAdjacentRoom(Action.ActionGoSoutheast, f2dr);
		mineRoom1.setAdjacentRoom(Action.ActionGoSouth, f1dr);
		mineRoom1.setDeathMessage(mineDeath);
		mineRoom1.setPlayerDiesOnEntry(true);

		RoomRequiredItem mineRoom2 = new RoomRequiredItem(mine, shortMine, Item.ItemArmor);
		mineRoom2.setAdjacentRoom(Action.ActionGoSouthwest, f1dr);
		mineRoom2.setAdjacentRoom(Action.ActionGoSouth, f2dr);
		mineRoom2.setAdjacentRoom(Action.ActionGoSoutheast, f3dr);
		mineRoom2.setDeathMessage(mineDeath);
		mineRoom2.setPlayerDiesOnEntry(true);

		RoomRequiredItem mineRoom3 = new RoomRequiredItem(mine, shortMine, Item.ItemArmor);
		mineRoom3.setAdjacentRoom(Action.ActionGoSouth, f3dr);
		mineRoom3.setAdjacentRoom(Action.ActionGoSouthwest, f2dr);
		mineRoom3.setDeathMessage(mineDeath);
		mineRoom3.setPlayerDiesOnEntry(true);

		mineRoom2.setAdjacentRoom(Action.ActionGoWest, mineRoom1);
		mineRoom2.setAdjacentRoom(Action.ActionGoEast, mineRoom3);

		RoomRequiredItem f11dr = new RoomRequiredItem(f11d, fShort, warning, shortWarning, Item.ItemGhillieSuit);
		f11dr.setAdjacentRoom(Action.ActionGoSoutheast, f1dr);
		f11dr.setAdjacentRoom(Action.ActionGoEast, mineRoom1);
		f11dr.setDeathMessage(deathMessage);
		
		RoomRequiredItem f32dr = new RoomRequiredItem(f32d, fShort, warning, shortWarning, Item.ItemGhillieSuit);
		f32dr.setAdjacentRoom(Action.ActionGoSouthwest, f3dr);
		f32dr.setAdjacentRoom(Action.ActionGoWest, mineRoom3);
		f32dr.setDeathMessage(deathMessage);
		
		RoomRequiredItem complexW = new RoomRequiredItem(compWD, compWSD, warning, shortWarning, Item.ItemGhillieSuit);
		RoomRequiredItem complex = new RoomRequiredItem(compD, compSD, warning, shortWarning, Item.ItemGhillieSuit);
		RoomRequiredItem complexE = new RoomRequiredItem(compED, compESD, warning, shortWarning, Item.ItemGhillieSuit);
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
		complexE.putItem(Item.ItemGhillieSuit);

		RoomRequiredItem compEntrance = new RoomRequiredItem(compEntranceD, compEntranceSD, warning, shortWarning, Item.ItemGhillieSuit);
		compEntrance.setAdjacentRoom(Action.ActionGoWest, complexE);
		compEntrance.setDeathMessage(deathMessage);

		RoomRequiredItem compFront = new RoomRequiredItem(compFrontD, compFrontSD, warning, shortWarning, Item.ItemGhillieSuit);
		compFront.setAdjacentRoom(Action.ActionGoSouth, compEntrance);
		compFront.setDeathMessage(deathMessage);
		
		RoomRequiredItem compElectric = new RoomRequiredItem(compElectricD, compElectricSD, warning, shortWarning, Item.ItemGhillieSuit);
		compElectric.setAdjacentRoom(Action.ActionGoEast, compFront);
		compElectric.setDeathMessage(deathMessage);
		// add junction box
		compElectric.putItem(Item.ItemCardReader);

		Room booth = new Room(boothD, boothSD);
		booth.setAdjacentRoom(Action.ActionGoSouth, compFront);
		Item guard = Item.ItemGuard1;
		guard.setInstalledItem(Item.ItemKeyCard);
		guard.setRelatedRoom(booth);
		booth.putItem(guard);
		return complexE;
	}
}
