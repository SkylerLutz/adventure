import java.util.HashMap;
import java.util.LinkedList;

public class Player {

	public Player(Room currentRoom) {
		this(currentRoom, new LinkedList<Item>());
	}
	public Player(Room currentRoom, LinkedList<Item> items) {
		this.items = items;
		this.MAX_HEALTH = 100;
		this.health = this.MAX_HEALTH;

		//this.currentRoom = currentRoom;
		//this.currentRoom.player = this;
		move(currentRoom);
	}	
	public Item drop(Item item) {
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
		return true;
	}
	public boolean pickup(Item item){ 

		Item takenItem = this.currentRoom.takeItem(item);
		if (takenItem == null) {
			System.out.println("I don't see that here");
			return false;
		}
		else if(takenItem == Item.ItemUnknown) {
			return false;
		}
		this.pick(takenItem);
		System.out.println("Taken.");
		return true;
	}
	public void pick(Item item) {
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
			//System.out.println(direct + " installed");
			return true;
		}
		else {
			return false;
		}
	}
	public void move(Room room) {
		

		room.setPlayer(this);
		if(this.currentRoom == null) {
			this.currentRoom = room;
		}
		// show transition message from old room to new room
		Action directionOfTravel = this.currentRoom.getDirectionForRoom(room);
		HashMap<Action, String> messages = this.currentRoom.transitionMessages();
		String message = messages.get(directionOfTravel);
		if(message != null) {
			System.out.println(message);
		}

		room.setPlayer(this);
		this.currentRoom = room;
		room.player = this;
		
		System.out.println(room.description());
	}	
	public Room currentRoom() {
		return this.currentRoom;
	}	
	public void look() {
		System.out.println(this.currentRoom);
	}
	public void injure(int damage) {
		this.health-=damage;
		if(this.health <= 0) {
			die();
		}
	}
	public void heal(int health) {
		this.health+=health;
		if(this.health > this.MAX_HEALTH){
			this.health = this.MAX_HEALTH;
		}
	}
	public void die() {
		System.out.println("You score 0 out of 90 possible points. You are dead.");
		System.exit(0);
	}
	protected LinkedList<Item> items;
	protected Room currentRoom;
	protected int health;
	protected final int MAX_HEALTH;
}	
