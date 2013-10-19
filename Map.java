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
	/*
	public static Room njit() {

		Room hw = new Room("You are in a hallway. \nThere are conference rooms to the W, bathrooms to the N, \nand a stairwell to the E. with a lock on the door", "Fourth floor hallway");
		hw.putItem(Item.ItemLock);

		Room conference = new Room("You are in the conference room. You think back to all of the successful ACM meetings you had here. There looks like there's something under the carpet...", "Conference room");

		RoomLockable stairwell = new RoomLockable("You are in the stairwell. There is a couple making out, and you stand there awkwardly watching.", "Stairwell", true, Item.ItemKey);

		HashMap<Action, Room> r = new HashMap<Action,Room>();
		Room roof = new Room("You are on the roof. There is a police chopper circling overhead.\nYou can head back downstairs, or make a jump for it to the adjacent roof to the E.", "GITC Rooftop");
		roof.setAdjacentRoomTransitionMessage("You miss the roof and fall through the floor. Since everyone is using their headphones, nobody seems to notice.", Action.ActionGoEast);


		r.put(Action.ActionGoUp, roof);
		stairwell.setAdjacentRooms(r);

		RoomExcavatable dirt = new RoomExcavatable("There is a pile of dirt here.", "Dirt pile", "You have revealed a clay pot.");
		LinkedList<Item> revealableItems = new LinkedList<Item>();
		Item i = Item.ItemClayPot;
		i.setInstalledItem(Item.ItemDiamond);
		i.setInstalledItemVisible(false);
		i.setDestroyMessage("You revealed a beautiful diamond!");
		revealableItems.add(i);
		dirt.setRevealableItems(revealableItems);


		RoomElevator elevator = new RoomElevator("GITC Elevator", "Elevator");
		elevator.putItem(Item.ItemElevatorFloor1Button);
		elevator.putItem(Item.ItemElevatorFloor2Button);
		elevator.putItem(Item.ItemElevatorFloor3Button);
		elevator.putItem(Item.ItemElevatorFloor4Button);
		
		String floor1Description = "First floor of GITC. There is an elevator to the west.";
		String floor2Description = "Second floor Lobby. There is an elevator to the west";
		String floor3Description = "Third floor Lobby. There is an elevator to the west";
		String floor4Description = "Fourth floor Lobby of GITC. There is an elevator to the west";
		
		Room floor1 = new Room(floor1Description, "Lobby 1"); 
		Item b1 = Item.ItemElevatorButton;
		b1.setElevator(elevator);
		floor1.putItem(b1);

		Room floor2 = new Room(floor2Description, "Lobby 2"); 
		Item b2 = Item.ItemElevatorButton;
		b2.setElevator(elevator);
		floor2.putItem(b2);

		Room floor3 = new Room(floor3Description, "Lobby 3"); 
		Item b3 = Item.ItemElevatorButton;
		b3.setElevator(elevator);
		floor3.putItem(b3);

		Room floor4 = new Room(floor4Description, "Lobby 4"); 
		Item b4 = Item.ItemElevatorButton;
		b4.setElevator(elevator);
		floor4.putItem(b4);

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

 * RoomElevator e = new RoomElevator("GITC Elevator", "Elevator");
 * 
 * String floor1Description
 * String floor2Description
 * String floor3Description
 * Room floor1; // constructor...
 * Room floor2; // constructor...
 * Room floor3; // constructor...
 * ArrayList<Room> list = new ArrayList<Room>();
 * list.add(floor1);
 * list.add(floor2);
 * list.add(floor3);
 * ArrayList<String> descriptions = new ArrayList<String>();
 * descriptions.add(floor1Description, floor2Description, floor3Description); 
 *
 * e.setFloors(descriptions, list, Action.ActionGoWest);
 * e.call(2);
 *
 * 

		Room bathroom = new Room("You are in the 4th floor bathroom. You are overcome by the stench of a disgusting turd that was once laid. There is grafitti on the wall...", "Bathroom");
		HashMap<Action, Room> rooms = new HashMap<Action, Room>();
		rooms.put(Action.ActionGoEast, stairwell);
		rooms.put(Action.ActionGoWest, conference);
		rooms.put(Action.ActionGoNorth, bathroom);
		rooms.put(Action.ActionGoNortheast, floor4);
//		rooms.put(Action.ActionGoNortheast, dirt);
		hw.setAdjacentRooms(rooms);

		bathroom.putItem(Item.ItemGrafitti);
		
		Item fridge = Item.ItemFridge;
		RoomObscured passage = new RoomObscured("You are in a dark corridor dimly lit by torches. There are portaits of past ACM Presidents, such as Akhil and Ben Slepp. You can hear the distance sound of running water.", "Dark Corridor.", fridge);
		fridge.setPassage(passage);
		HashMap<Action, Room> hall = new HashMap<Action, Room>();
		hall.put(Action.ActionGoNorth, hw);
		hall.put(Action.ActionGoEast, passage);

		// make a RoomDark here for extension of passage
		RoomDark corridor = new RoomDark("Well lit corridor. The sound of running water is getting louder.", "Well lit corridor", "The light of the torches cannot reach here. It is completely pitch black. The sound of running water is getting louder.", "Pitch black corridor", true);
		// TODO set corridors adjacent rooms to water slide and passage
		
		Room waterslide = new Room("You have found the source of the running water! It is the waterslide that Ben Slepp promised! You can go down it if you want, but its destination is unknown", "Waterslide");
		waterslide.setAdjacentRoomTransitionMessage("Wee!!", Action.ActionGoDown);
		HashMap<Action, Room> water = new HashMap<Action, Room>();
		water.put(Action.ActionGoDown, floor1);
		water.put(Action.ActionGoWest, corridor);
		waterslide.setAdjacentRooms(water);
		HashMap<Action, Room> c = new HashMap<Action, Room>();
		c.put(Action.ActionGoWest, passage);
		corridor.setAdjacentRooms(c);

		LinkedList<Item> items = new LinkedList<Item>();
		//items.add(Item.ItemLightSwitch);
		//items.add(Item.ItemFlashLight);
		items.add(Item.ItemKey);
		//items.add(Item.ItemShovel);
		items.add(Item.ItemStatue);
		items.add(Item.ItemMicrowave);
		items.add(Item.ItemFridge);
		RoomDark acm = new RoomDark("You are in the ACM office. It is filled with students", "ACM Office", "It is dark. Perhaps you can find a way to see...", "Darkness", false);
		acm.putItems(items);
		acm.setAdjacentRooms(hall);
		
		roof.setOneWayAdjacentRoom(Action.ActionGoEast, acm);
		return acm;
		
	}
	*/
}
