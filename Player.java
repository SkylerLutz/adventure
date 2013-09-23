import java.util.LinkedList;

public class Player {

	public Player(Room currentRoom) {
		this(currentRoom, new LinkedList<Item>());
	}
	public Player(Room currentRoom, LinkedList<Item> items) {
		this.currentRoom = currentRoom;
		this.items = items;
	}	
	private Item drop(Item item) {
		if(this.items.remove(item)) {
			return item;
		}
		else {
			return null;
		}
	}
	public boolean dropItem(Item item) {

		Item dropped = drop(item);
		if (dropped == null) {
			System.out.println("You don't have this item to drop");
			return false;
		}
		this.currentRoom.putItem(dropped);
		System.out.println("Dropped");
		return true;
	}
	public boolean pickup(Item item){ 

		Item takenItem = this.currentRoom.takeItem(item);
		if (takenItem == null) {
			System.out.println("I don't see that here");
			return false;
		}
		this.pick(takenItem);
		System.out.println("Picked up");
		return true;
	}
	private void pick(Item item) {
		this.items.add(item);
	}
	public boolean hasItem(Item item) {
		return this.items.contains(item);
	}
	public LinkedList<Item> getItems() {
		return this.items;
	}
	public boolean putItemInItem(Item direct, Item indirect) {
		
		if(drop(direct) == null) {
			return false;
		}
		if(indirect.setInstalledItem(direct)){
			System.out.println(direct + " installed");
			return true;
		}
		else {
			return false;
		}
	}
	public void move(Room room) {
		this.currentRoom = room;
	}	
	public Room currentRoom() {
		return this.currentRoom;
	}	
	public void die() {
		System.out.println("You score 0 out of 90 possible points. You are dead.");
		System.exit(0);
	}
	protected LinkedList<Item> items;
	protected Room currentRoom;
}	
