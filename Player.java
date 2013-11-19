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

		Game.print(this.currentRoom.description());
		// TODO not sure if I want to leave this line in for the final release
		//Game.print(this.currentRoom.visibleItems());
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
			Game.print("You don't have this item to drop");
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
	public void move(Action a) {
	
		if(this.currentRoom instanceof RoomRequiredItem) {
			RoomRequiredItem room = (RoomRequiredItem)this.currentRoom;
			
			if(room.shouldDieForAction(a)) {
				Game.print(room.deathMessage());
				this.die();
			}
		}
		else if(this.currentRoom instanceof RoomDark) {
			RoomDark room = (RoomDark)this.currentRoom;
			if(room.isDark() && room.willDieInDirection(a) && !this.hasItem(Item.getInstance("flashlight"))) {
				Game.print(room.deathMessage());
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
						Game.print(lockedRoom.deathMessage());
						this.die();
					}
					Game.print("This door is locked. You must unlock it.");
					return;
				}
			}
			else if(nextRoom instanceof RoomObscured) {
				RoomObscured obscuredRoom = (RoomObscured)nextRoom;
				if(obscuredRoom.isObscured()) {
					Game.print("You can't move that way");
					return;
				}
			}
			//this.move(nextRoom);

			nextRoom.setPlayer(this);
			if(this.currentRoom != null && nextRoom.compareTo(this.currentRoom) != 0) { 
				Action directionOfTravel = this.currentRoom.getDirectionForRoom(nextRoom);
				HashMap<Action, String> messages = this.currentRoom.transitionMessages();
				String message = messages.get(directionOfTravel);
				int delay = this.currentRoom.transitionDelay();
				if(message != null) {
					if(delay != 0) {
						for(int i=0; i < 3; i++) { 
							Game.print("...");
							try{
								Thread.sleep(delay);
							}
							catch(Exception e1) {
								// pass
							}
						}
					}
					Game.print(message);
				}
			}
			if(nextRoom instanceof RoomRequiredItem) {
				RoomRequiredItem r = (RoomRequiredItem)nextRoom;
				if(r.diesOnEntry()) {
					Game.print(r.deathMessage());
					this.die();
				}
			}

			this.currentRoom = nextRoom;
			Game.print(this.currentRoom.description());
			// TODO not sure if I want to leave this line in for the final release
			//Game.print(this.currentRoom.visibleItems());

			if(nextRoom instanceof RoomSky) {
				RoomSky sky = (RoomSky)nextRoom;
				sky.freefall();
			}
		}
		else {
			Game.print("You can't move that way");
		}

		
	}	
	public Room currentRoom() {
		return this.currentRoom;
	}	
	public void look() {
		Game.print(this.currentRoom.toString());
	}
	public void score(Valuable object) {
		int score = object.value();
		Game.print("You scored " + score + " points.");
		this.score+=score;
	}
	public void die() {
		Game.print("You scored " + this.score + " out of 90 possible points. You are dead.");
		System.exit(0);
	}
	protected int score;
	protected Item disguise;
	protected LinkedList<Item> items;
	protected Room currentRoom;
}	
