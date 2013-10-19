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
		
		Room start = new Room("There is a tree, with a building to the west. There is a lock on the door.", "Tree" );
		start.setAdjacentRoom(Action.ActionGoNorth, mid1);
		start.setAdjacentRoom(Action.ActionGoEast, mid2);
		start.setAdjacentRoom(Action.ActionGoWest, end);
		start.putItems(startItems);
		return start;
	}
	public static Room njit() {

		String hallwayDesription = "You are in a hallway. \nThere are conference rooms to the W, bathrooms to the N, \nand a stairwell to the E. with a lock on the door";
		String hallwayShortDescription = "Fourth floor hallway";

		String conferenceRoomDescription = "You are in the conference room. You think back to all of the successful ACM meetings you had here.";
		String conferenceRoomShortDescription = "Conference room";

		String stairwellDescription = "You are in the stairwell. There is a couple making out, and you stand there awkwardly watching.";
		String stairwellShortDescription = "Stairwell";

		String roofDescription = "You are on the roof. There is a police chopper circling overhead.\nYou can head back downstairs, or make a jump for it to the adjacent roof to the E.";
		String roofShortDescription = "GITC Rooftop";

		String dirtRoomDescription = "There is a pile of dirt here.";
		String dirtRoomShortDescription = "Dirt pile";
		String dirtRoomRevealMessage = "You have revealed a clay pot.";

		String elevatorDescription = "GITC Elevator";
		String elevatorShortDescription = "Elevator";

		String floor1Description = "First floor of GITC. There is an elevator to the west.";
		String floor2Description = "Second floor Lobby. There is an elevator to the west";
		String floor3Description = "Third floor Lobby. There is an elevator to the west";
		String floor4Description = "Fourth floor Lobby of GITC. There is an elevator to the west";
		String floor1ShortDescription = "First floor of GITC.";
		String floor2ShortDescription = "Second floor Lobby.";
		String floor3ShortDescription = "Third floor Lobby.";
		String floor4ShortDescription = "Fourth Floor Lobby";

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

		Room conference = new Room(conferenceRoomDescription, conferenceRoomShortDescription);
		RoomLockable stairwell = new RoomLockable(stairwellDescription, stairwellShortDescription, true, Item.ItemKey);

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
		b1.setElevator(elevator);
		floor1.putItem(b1);

		Room floor2 = new Room(floor2Description, floor2ShortDescription); 
		Item b2 = Item.ItemElevatorButton;
		b2.setElevator(elevator);
		floor2.putItem(b2);

		Room floor3 = new Room(floor3Description, floor3ShortDescription); 
		Item b3 = Item.ItemElevatorButton;
		b3.setElevator(elevator);
		floor3.putItem(b3);

		Room floor4 = new Room(floor4Description, floor4ShortDescription); 
		Item b4 = Item.ItemElevatorButton;
		b4.setElevator(elevator);
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
		
		elevator.setFloors(descriptions, list, Action.ActionGoEast);
		elevator.call(floor3);


		Room bathroom = new Room(bathroomDescription, bathroomShortDescription);
		hallway.setAdjacentRoom(Action.ActionGoEast, stairwell);
		hallway.setAdjacentRoom(Action.ActionGoWest, conference);
		hallway.setAdjacentRoom(Action.ActionGoNorth, bathroom);
		hallway.setAdjacentRoom(Action.ActionGoNortheast, dirt);

		bathroom.putItem(Item.ItemGrafitti);
		
		Item fridge = Item.ItemFridge;
		RoomObscured passage = new RoomObscured(passageDescription ,passageShortDescription, fridge);
		fridge.setPassage(passage);

		RoomDark corridor = new RoomDark(corridorDescription, corridorShortDescription, corridorDarkDescription, corridorDarkShortDescription, true);
		corridor.setSafeDirection(Action.ActionGoWest);
		
		Room waterslide = new Room(waterslideDescription, waterslideShortDescription);
		waterslide.setAdjacentRoomTransitionMessageWithDelay("Wee!!", Action.ActionGoDown, 1000);

		waterslide.setAdjacentRoom(Action.ActionGoDown, floor1);
		waterslide.setAdjacentRoom(Action.ActionGoWest, corridor);
		passage.setAdjacentRoom(Action.ActionGoEast, corridor);

		LinkedList<Item> items = new LinkedList<Item>();
		//items.add(Item.ItemLightSwitch);
		items.add(Item.ItemFlashLight);
		items.add(Item.ItemKey);
		//items.add(Item.ItemShovel);
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
}
