import java.util.LinkedList;

public class Player {

	public Player(Room currentRoom) {
		this(currentRoom, new LinkedList<Item>());
	}
	public Player(Room currentRoom, LinkedList<Item> items) {
		this.currentRoom = currentRoom;
		this.items = items;
	}	
	public void pickup(Item item) {
		this.items.add(item);
	}
	public Item drop(Item item) {
		if(this.items.remove(item)) {
			return item;
		}
		else {
			return null;
		}
	}
	public boolean hasItem(Item item) {
		return this.items.contains(item);
	}
	public LinkedList<Item> getItems() {
		return this.items;
	}
	public void move(Room room) {
		this.currentRoom = room;
	}	
	public Room currentRoom() {
		return this.currentRoom;
	}	
	protected LinkedList<Item> items;
	protected Room currentRoom;
}	
