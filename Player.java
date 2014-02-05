import java.util.HashMap;
import java.util.LinkedList;

public class Player {

	public Player(Room currentRoom) {
		this(currentRoom, new LinkedList<Item>());
		this.score = 0;
	}
	public Player(Room currentRoom, LinkedList<Item> items) {
		this.items = items;
		this.disguise = null;

		//this.currentRoom = currentRoom;
		this.currentRoom = currentRoom;
		this.currentRoom.player = this;

		System.out.println(this.currentRoom.description());
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

		this.pick(item);
		return true;
	}
	public void pick(Item item) {
		this.items.add(item);
	}
	public boolean hasItem(Item item) {
		if(item == null) return false;
		if(this.disguise == item) return true;
		return this.items.contains(item);
	}
	public boolean hasItemOfType(String str) {
		try {
		for(Item item : this.items) {
			if(Class.forName(str).isInstance(item)) {
				return true;
			}
		}
		}
		catch(ClassNotFoundException e) {

		}	
		return false;
	}
	public LinkedList<Item> getItems() {
		return this.items;
	}
	public void putItemInItem(Item direct, Item indirect) {
		((Hostable)indirect).install(direct);
		if(indirect instanceof ItemMagicBox && direct instanceof Valuable) {
			score((Valuable)direct);
		}
	}
	public void wearDisguise(Item disguise) {
		if(this.disguise != null) {
			this.currentRoom.putItem(this.disguise);
		}
		this.disguise = disguise;
	}
	public Item disguise() {
		return this.disguise;
	}
	public void move(Room nextRoom) {

		nextRoom.setPlayer(this);
		if(this.currentRoom != null && nextRoom.compareTo(this.currentRoom) != 0) { 
			Action directionOfTravel = this.currentRoom.getDirectionForRoom(nextRoom);
			HashMap<Action, String> messages = this.currentRoom.transitionMessages();
			String message = messages.get(directionOfTravel);
			int delay = this.currentRoom.transitionDelay();
			if(message != null) {
				if(delay != 0) {
					for(int i=0; i < 3; i++) { 
						System.out.println("...");
						try{
							Thread.sleep(delay);
						}
						catch(Exception e1) {
							// pass
						}
					}
				}
				System.out.println(message);
			}
		}
		if(nextRoom instanceof RoomRequiredItem) {
			RoomRequiredItem r = (RoomRequiredItem)nextRoom;
			if(r.diesOnEntry()) {
				System.out.println(r.deathMessage());
				this.die();
			}
		}

		this.currentRoom = nextRoom;
		System.out.println(this.currentRoom.description());
		// TODO not sure if I want to leave this line in for the final release
		//System.out.println(this.currentRoom.visibleItems());

		if(nextRoom instanceof RoomSky) {
			RoomSky sky = (RoomSky)nextRoom;
			sky.freefall();
		}
	}
	public void move(Action a) {
	
		if(this.currentRoom instanceof RoomRequiredItem) {
			RoomRequiredItem room = (RoomRequiredItem)this.currentRoom;
			
			if(room.shouldDieForAction(a)) {
				System.out.println(room.deathMessage());
				this.die();
			}
		}
		else if(this.currentRoom instanceof RoomDark) {
			RoomDark room = (RoomDark)this.currentRoom;
			if(room.isDark() && room.willDieInDirection(a) && !this.hasItemOfType("Luminous")) {
				System.out.println(room.deathMessage());
				this.die();
			}
		}

		if(this.currentRoom.canMoveToRoomInDirection(a)) {
			Room nextRoom = this.currentRoom.getRoomForDirection(a);
			// test if requires key
			if(nextRoom instanceof RoomLockable) {
				RoomLockable lockedRoom = (RoomLockable)nextRoom;
				if(lockedRoom.isLocked()) {
					if(lockedRoom.causesDeath()) {
						System.out.println(lockedRoom.deathMessage());
						this.die();
					}
					System.out.println("This door is locked.");
					return;
				}
			}
			else if(nextRoom instanceof RoomObscured) {
				RoomObscured obscuredRoom = (RoomObscured)nextRoom;
				if(obscuredRoom.isObscured()) {
					System.out.println("You can't move that way.");
					return;
				}
			}
			move(nextRoom);
		}
		else {
			System.out.println("You can't move that way.");
		}

		
	}	
	public Room currentRoom() {
		return this.currentRoom;
	}	
	public void look() {
		System.out.println(this.currentRoom.toString());
	}
	public void score(Valuable object) {
		int score = object.value();
		System.out.println("You scored " + score + " points.");
		this.score+=score;
	}
	public void die() {
		System.out.println("You scored " + this.score + " out of 90 possible points. You are dead.");
		System.exit(0);
	}
	protected int score;
	protected Item disguise;
	protected LinkedList<Item> items;
	protected Room currentRoom;
}	
