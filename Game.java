import java.util.Scanner;
import java.util.LinkedList;

public class Game {

	protected Scanner scanner;
	protected PlayerInterpreter interpreter;
	protected Player player;
	
	public Game() {

		this(null);
	}
	public Game(java.io.File save) {
	
		// Parse room from file
		Room startingRoom = Map.njit();
		//Room startingRoom = Map.level1();
		
		this.scanner = new Scanner(System.in);
		this.interpreter = new PlayerInterpreter();
		
		// Parse inventory from file
		LinkedList<Item> items = new LinkedList<Item>();
		if(items != null) {
			this.player = new Player(startingRoom, items);
		}
		else {
			this.player = new Player(startingRoom);
		}
	}

	public void start() {

		String input = "";
		while(input.compareTo("quit") != 0) {
			System.out.print(">>> ");
			input = this.scanner.nextLine();
			Action a = this.interpreter.interpretString(input);
			switch(a.type()){
				case TYPE_DIRECTIONAL:
					move(a);
					break;
				case TYPE_HASDIRECTOBJECT:
					switch(a) {

						case ActionPickUp:
							this.player.pickup(a.directObject());
							break;
						case ActionBreak:
							Item item = a.directObject();
							this.player.drop(item);
							Item destroyed = item.destroy();
							this.player.currentRoom.putItem(destroyed);
							break;
						case ActionInspect:
							
							Item itemToInspect = a.directObject();
							if(this.player.hasItem(itemToInspect) || this.player.currentRoom.hasItem(itemToInspect)) {
								System.out.println(itemToInspect.toDetailString());
							}
							else {
								System.out.println("I don't see that here.");
							}

							break;
						case ActionDrop:
							
							if(this.player.dropItem(a.directObject())){
								System.out.println("Dropped");
							}
							break;
						case ActionThrow:
							if(this.player.dropItem(a.directObject())) {
								System.out.println("Thrown");
							}
							break;
						case ActionShake:
							this.player.die();
							break;
						case ActionEnable:
							
							Item enabledItem = a.directObject();
							if(this.player.currentRoom.hasItem(enabledItem)) {
								if(enabledItem == Item.ItemLightSwitch && (this.player.currentRoom instanceof RoomDark)) {
									RoomDark room = (RoomDark)this.player.currentRoom;
									room.setDark(false);
									this.player.look();
								}
								else {
					System.out.println("failed" + enabledItem + " " + (this.player.currentRoom instanceof RoomDark));
								}
							}
							else {
								System.out.println("I don't see that here");
							}
							break;
					}
					break;
				case TYPE_HASINDIRECTOBJECT:
					switch(a) {
	
						case ActionPut:
							Item itemToPut = a.directObject();
							Item itemToBePutInto = a.indirectObject();
							if(!this.player.hasItem(itemToPut)) {
								System.out.println("You don't have that object in your inventory.");
								break;
							}
							else if(!this.player.currentRoom.hasItem(itemToBePutInto)) {
								System.out.println("That object doesn't exist in this room.");
								break;
							}
							else if(!itemToBePutInto.canInstallItems()) {
								System.out.println("You cannot install a " + itemToPut + " into this " + itemToBePutInto);
							}
							else {
								this.player.putItemInItem(itemToPut, itemToBePutInto);
								if(itemToBePutInto == Item.ItemLock1) {
									// this restricts me to one locked adjacent room at a time
									
									for(Action action : Action.values()) {
										
										Room adjacentRoom = this.player.currentRoom.getRoomForDirection(action);
										if(adjacentRoom instanceof RoomLockable) {
											((RoomLockable)adjacentRoom).unlock(itemToPut);
											break;
										}
									}
								}	
							}
							break;
							}

					break;
				case TYPE_HASNOOBJECT:
					switch(a) {
	
						case ActionLook:

							this.player.look();
							break;
						case ActionDig:
							if(this.player.currentRoom instanceof RoomExcavatable) {
								RoomExcavatable curr = (RoomExcavatable)this.player.currentRoom;
								curr.dig();
							}
							else {
								System.out.println("You are not allowed to dig here");
							}

							break;
						case ActionJump:
							break;
						case ActionViewItems: 
							LinkedList<Item> items = this.player.getItems();
							if (items.size() == 0) {
								System.out.println("You don't have any items");
							}
							else {
								for(Item item : this.player.getItems()) {
									System.out.println("You have a " + item.detailDescription());
								}
							}
							break;
						case ActionHelp:
							help();
							break;	
					}
					break;
				default:
					System.out.println("I don't understand that");
					break; 
			}
		}

		System.out.println("Quitting game...");
	}
	private void move(Action a) {
	
		// test if room is dark
		if(this.player.currentRoom instanceof RoomDark) {
			if(((RoomDark)this.player.currentRoom).isDark() && !this.player.hasItem(Item.ItemFlashLight)) {
				System.out.println("As you take your first step within the dark room, you trip on a mysterious object. You fall toward the floor, and hit your head against a large rock");
				this.player.die();
			}
		}
		if(this.player.currentRoom.canMoveToRoomInDirection(a)) {

			Room nextRoom = this.player.currentRoom.getRoomForDirection(a);
			// test if requires key
			if(nextRoom instanceof RoomLockable) {

				RoomLockable lockedRoom = (RoomLockable)nextRoom;
				if(lockedRoom.isLocked()) {

					if(lockedRoom.causesDeath()) {
						System.out.println(lockedRoom.deathMessage());
						this.player.die();
					}
					System.out.println("This door is locked. You must unlock it.");
					return;
				}
			}
			this.player.move(nextRoom);
		}
		else {
			System.out.println("You can't move that way");
		}
	}
	private void help() {

		System.out.println(" -- Text RPG Help Menu -- ");
		
		System.out.println("To view your current items: type \"inventory\"");
		System.out.println("To travel in a direction like north, type \"Go North\"");
		System.out.println("You can inspect an inspectable item by typing \"Inspect <item>\"");
	}
}
