import java.util.HashMap;
import java.util.LinkedList;

public class Map {

	public static Room level1() {

		Room mid1 = new Room("There is a fork", "Fork");
		Room mid2 = new Room("Ferocious bear", "bear");
		Room end = new Room("You are inside of a building", "Building interior", Item.ItemKey);
		
		end.setAdjacentRoom(Action.ActionGoNortheast, mid1);
		
		HashMap<Action, Room> rooms = new HashMap<Action, Room>();
		rooms.put(Action.ActionGoNorth, mid1);
		rooms.put(Action.ActionGoEast, mid2);
		rooms.put(Action.ActionGoWest, end);

		LinkedList<Item> startItems = new LinkedList<Item>();
		startItems.add(Item.ItemBrick);
		startItems.add(Item.ItemShovel);
		startItems.add(Item.ItemFood);
		startItems.add(Item.ItemKey);
		
		Room start = new Room("There is a tree, with a building to the west", "Tree", rooms, startItems, null);
		return start;
	}
}
