import java.util.ArrayList;

public class Map {

	public static Room level1() {

		Room mid1 = new Room("There is a fork", "Fork", null);
		Room mid2 = new Room("Ferocious bear", "bear", null);
		Room end = new Room("There is a building", "Building front", null);
		
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(mid1);
		rooms.add(mid2);
		rooms.add(end);
		
		Room start = new Room("There is a tree", "Tree", rooms);
		return start;
	}
}
