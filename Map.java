import java.util.HashMap;
import java.util.LinkedList;

public class Map {

	public static Room level1() {

		Room mid1 = new Room("There is a fork", "Fork");
		Room mid2 = new Room("Ferocious bear", "bear");
		Room end = new Room("You are inside of a building", "Building interior");
		
		HashMap<Action, Room> l = new HashMap<Action,Room>();
		l.put(Action.ActionGoNortheast, mid1); 
		end.setAdjacentRooms(l);
		
		HashMap<Action, Room> rooms = new HashMap<Action, Room>();
		rooms.put(Action.ActionGoNorth, mid1);
		rooms.put(Action.ActionGoEast, mid2);
		rooms.put(Action.ActionGoWest, end);

		LinkedList<Item> startItems = new LinkedList<Item>();
		startItems.add(Item.ItemBrick);
		startItems.add(Item.ItemShovel);
		startItems.add(Item.ItemFood);
		startItems.add(Item.ItemLock);
		
		Room start = new Room("There is a tree, with a building to the west", "Tree", rooms, startItems);
		return start;
	}

	public static Room njit() {

		Room hw = new Room("You are in a hallway. There are conference rooms to the W, bathrooms to the N, and a stairwell to the east", "Fourth floor hallway");
		Room conference = new Room("You are in the conference room. It is said that the ghost of Akhil still roams these rooms, and he can still be heard discussing upcoming SIG's.", "Conference room");
		Room stairwell = new Room("You are in the stairwell. There is a couple making out, and you stand there awkwardly watching.", "Stairwell");
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
		Room acm = new Room("You are in the ACM office. The you are alone. It is dark. Perhaps you can find a way to see...", "ACM Office", hall, items);
		return acm;
		
	}
}
