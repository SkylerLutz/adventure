import java.util.HashMap;
import java.util.LinkedList;

public class Map {

	public static Room level1() {

		Room mid1 = new Room("There is a fork", "Fork");
		Room mid2 = new Room("Ferocious bear", "bear");
		Room end = new RoomLockable("You are inside of a building", "Building interior", true, Item.ItemKey1);
		
		HashMap<Action, Room> l = new HashMap<Action,Room>();
		l.put(Action.ActionGoNortheast, mid1); 
		end.setAdjacentRooms(l);
		
		HashMap<Action, Room> rooms = new HashMap<Action, Room>();
		rooms.put(Action.ActionGoNorth, mid1);
		rooms.put(Action.ActionGoEast, mid2);
		rooms.put(Action.ActionGoWest, end);

		LinkedList<Item> startItems = new LinkedList<Item>();
		startItems.add(Item.ItemBrick);
		startItems.add(Item.ItemKey1);
		startItems.add(Item.ItemLock1);
		
		Room start = new Room("There is a tree, with a building to the west. There is a lock on the door.", "Tree", rooms, startItems);
		return start;
	}

	public static Room njit() {

		Room hw = new Room("You are in a hallway. \nThere are conference rooms to the W, bathrooms to the N, \nand a stairwell to the E.", "Fourth floor hallway");
		Room conference = new Room("You are in the conference room. You think back to all of the successful ACM meetings you had here. There looks like there's something under the carpet...", "Conference room");
		Room stairwell = new Room("You are in the stairwell. There is a couple making out, and you stand there awkwardly watching.", "Stairwell");
		HashMap<Action, Room> r = new HashMap<Action,Room>();
		Room roof = new Room("You are on the roof. There is a police chopper circling overhead.\nYou can head back downstairs, or make a jump for it to the adjacent roof to the E.", "GITC Rooftop");
		r.put(Action.ActionGoUp, roof);
		stairwell.setAdjacentRooms(r);
		Room bathroom = new Room("You are in the 4th floor bathroom. You are overcome by the stench of a disgusting turd that was once laid. There is grafitti on the wall...", "Bathroom");
		HashMap<Action, Room> rooms = new HashMap<Action, Room>();
		rooms.put(Action.ActionGoEast, stairwell);
		rooms.put(Action.ActionGoWest, conference);
		rooms.put(Action.ActionGoNorth, bathroom);
		hw.setAdjacentRooms(rooms);

		bathroom.putItem(Item.ItemGrafitti);
		
		HashMap<Action, Room> hall = new HashMap<Action, Room>();
		hall.put(Action.ActionGoNorth, hw);

		LinkedList<Item> items = new LinkedList<Item>();
		items.add(Item.ItemLightSwitch);
		items.add(Item.ItemFlashLight);
		RoomDark acm = new RoomDark("You are in the ACM office. It is filled with students", "ACM Office", "It is dark. Perhaps you can find a way to see...", "Darkness", hall, items, true);
		return acm;
		
	}
}
