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
		//Room startingRoom = Map.njit();
		//Room startingRoom = Map.mission();
		this.scanner = new Scanner(System.in);

		Room startingRoom = chooseLevel();

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
	private Room chooseLevel() {
		ItemWatchMenu menu = new ItemWatchMenu("Choose a level from the options to below: \n");
		
		ItemWatchMenu m1 = new ItemWatchMenu("Dunnet");
		ItemWatchMenu m2 = new ItemWatchMenu("NJIT");
		ItemWatchMenu m3 = new ItemWatchMenu("Spy Mission");

		menu.add(m1);
		menu.add(m2);
		menu.add(m3);

		Game.print(menu.toString());

		int choice = 0;
		while(true) {
			System.out.print(">>> ");
			String input = this.scanner.nextLine();
			try {
				choice = Integer.parseInt(input) - 1;
			}
			catch(Exception e) {
				Game.print("Invalid selection.");
				continue;
			}
			if(choice > menu.count() || choice < 0) {
				Game.print("Invalid selection.");
				continue;
			}
			switch(choice) {
				case 0:
					return Map.level1();
				case 1:
					return Map.njit();
				case 2:
					return Map.mission();
				default:
					Game.print("Invalid selection.");
					continue;
			}
		}
	}
	public void start() throws NullPointerException {

		try {
		String input = "";
		while(input.compareTo("quit") != 0) {
			System.out.print(">>> ");
			input = this.scanner.nextLine();
			Action a = this.interpreter.interpretString(input);
			switch(a.type()) {
				case TYPE_DIRECTIONAL:
					move(a);
					break;
				case TYPE_HASDIRECTOBJECT:
					switch(a) {
						case ActionPickUp: {
							Item o = a.directObject();
							Item container = null;
							if(this.player.currentRoom.hasItem(o)) {
								if(o instanceof Holdable) {
									Game.print("Taken.");
									this.player.currentRoom.remove(o);
									this.player.pickup(o);
								}
								else {
									Game.print("You cannot pick up this item.");
								}
							}
							else if((container = containerForItem(o)) != null) {
								
								Game.print("Taken.");
								((Hostable)container).uninstall(o);
								this.player.pickup(o);
							}
							else if(this.player.hasItem(o)) {
								Game.print("You already have that item in your inventory.");
							}
							else {
								Game.print("I don't see that here.");
							}
							break;
						}
						case ActionBreak: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item) || this.player.hasItem(item)) {
								if(item instanceof Destroyable) {
									Game.print("Smashed.");
									((Destroyable)item).destroy();
									item.setDescription("broken " + item.toString());
									item.setDetailDescription("broken " + item.detailDescription());
									if(((Destroyable)item).disappears()) {
										this.player.drop(item);
										this.player.currentRoom.remove(item);
									}

									if(item instanceof Hostable) {
										this.player.currentRoom.putItem(((Hostable)item).installedItem());
										((Hostable)item).uninstall(((Hostable)item).installedItem());
									}
								}
								else {
									Game.print("You cannot break this item.");
								}
							}
							else {
								Game.print("I don't see that here.");
							}
							break;
						}
						case ActionInspect: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item) || this.player.hasItem(item)) {
								if(item instanceof Inspectable) {
									((Inspectable)item).inspect();
								}
								else {
									Game.print("You cannot inspect this item.");
								}
							}
							else {
								Game.print("I don't see that here.");
							}
							break;
						}
						case ActionDrop: {
							Item item = a.directObject();
							if(this.player.hasItem(item)) {
								if(item instanceof Holdable) {
									Game.print("Dropped.");
									this.player.drop(item);
									this.player.currentRoom.putItem(item);
								}
								else {
									Game.print("You cannot drop this item.");
								}
							}
							else {
								Game.print("You don't have that item to drop.");
							}
							if(this.player.currentRoom instanceof RoomRequiredItem) {
								RoomRequiredItem r = (RoomRequiredItem)this.player.currentRoom;
								r.playerDidDropRequiredItem();
							}
							break;
						}
						case ActionThrow: {
							Item item = a.directObject();
							if(this.player.hasItem(item)) {
								if(item instanceof Chuckable) {
									Game.print("Thrown.");
									((Chuckable)item).chuck();
									this.player.drop(item);
									this.player.currentRoom.putItem(item);
								}
								else {
									Game.print("You cannot throw this item.");
								}
							}
							else {
								Game.print("You don't have that item to throw.");
							}
							break;
						}
						case ActionShake: {
				
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item) || this.player.hasItem(item)) {
								if(item instanceof Shakeable) {
									((Shakeable)item).shake();
									if(((Shakeable)item).deadly()) {
										this.player.die();
									}
								}
								else {
									Game.print("I don't know how to do that.");
								}
							}
							else {
								Game.print("I don't see that here.");
							}
							break;
						}
						case ActionEnable: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item) || this.player.hasItem(item)) {
								if(item instanceof Startable) {
									Game.print("Done.");
									((Startable)item).start();
								}
								else {
									Game.print("I don't know how to do that.");
								}
							}
							else {
								Game.print("I don't see that here.");
							}
							break;

						}
						case ActionPush: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item) || this.player.hasItem(item)) {
								if(item instanceof Pushable) {
									Game.print("Pushed.");
									((Pushable)item).push();
									if(item.relatedRoom() instanceof RoomElevator) { // player is next to an elevator
										((RoomElevator)item.relatedRoom()).call(this.player.currentRoom);
									}
									else if(this.player.currentRoom instanceof RoomElevator) { // player is in an elevator
										((RoomElevator)this.player.currentRoom).call(Integer.parseInt(item.getAliases()[0])-1);
									}

								}
								else {
									Game.print("Nothing happens.");
								}
							}
							else {
								Game.print("I don't see that here.");
							}
							break;
						}
						case ActionEat: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item) || this.player.hasItem(item)) {
								if(item instanceof Edible) {
									((Edible)item).eat();
								}
								else {
									Game.print("As you forcefully shove the " + a.directObject() + " down your throat, you begin to choke.");
									this.player.die();
								}
							}


							break;

						}

						case ActionWear: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item)) {
								if(item instanceof Wearable) {
									Game.print("Worn.");
									this.player.currentRoom.remove(item);
									this.player.wearDisguise(item);
								}
								else {
									Game.print("You can not wear this item.");
								}
							}
							else if(this.player.disguise() == item) {
								Game.print("You are already wearing that.");
							}
							else {
								Game.print("I don't see that here.");
							}
							break;
						}
						case ActionKill: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item)) {
								if(item instanceof Killable) {
									((Killable)item).kill();
								}
								else {
									Game.print("You cannot kill this.");
								}
							}
							else {
								Game.print("I don't see that here.");
							}
							break;
						}
						case ActionOpen: {
							Item item = a.directObject();
							if(this.player.hasItem(item) || this.player.currentRoom.hasItem(item)) {
								if(item instanceof Openable) {
									((Openable)item).open();
								}
								else {
									Game.print("You cannot open this.");
								}
							}
							else {
								Game.print("I don't see that here.");
							}
							break;
						}

					}
				case TYPE_HASINDIRECTOBJECT:
					switch(a) {
						case ActionPut: {
							Item itemToPut = a.directObject();
							Item itemToBePutInto = a.indirectObject();
							if(!this.player.hasItem(itemToPut)) {
								System.out.println(itemToPut);
								Game.print("You don't have that object in your inventory.");
								break;
							}
							else if(!this.player.currentRoom.hasItem(itemToBePutInto)) {
								Game.print("That object doesn't exist in this room.");
								break;
							}
							else if(itemToBePutInto instanceof ItemMagicBox && !(itemToPut instanceof Valuable)) {
								Game.print("This item has no value--putting it in this " + itemToBePutInto + " will not score you any points.");
							}
							else if(!(itemToBePutInto instanceof Hostable) || !(itemToPut instanceof Installable)) {
								Game.print("You cannot put a " + itemToPut + " into this " + itemToBePutInto);
							}
							else {
								Game.print("Done.");
								this.player.drop(itemToPut);
								this.player.putItemInItem(itemToPut, itemToBePutInto);
							}
							break;
						}
						case ActionTake: {
							Item contents = a.directObject();
							Item container = a.indirectObject();
							if(!this.player.currentRoom.hasItem(container)) {
								Game.print("I don't see that here.");
							}
							else if(!(container instanceof Hostable)) {
								Game.print("You can't have an item inside that.");
							}		
							else {
								if(((Hostable)container).installedItem() == contents) {
									((Hostable)container).uninstall(contents);
									this.player.pickup(contents);
									Game.print("Taken.");
								}	
								else {
									Game.print("That item is not inside this " + container);
								}
							}
							break;
						}
					}
				case TYPE_HASNOOBJECT: {
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
								Game.print("You are not allowed to dig here");
							}

							break;
						case ActionJump:
							move(Action.ActionGoDown);
							break;
						case ActionViewItems: 
							LinkedList<Item> items = this.player.getItems();
							if(this.player.disguise() != null) {
								Game.print("You are wearing a " + this.player.disguise() + ".");
							}
							if (items.size() == 0) {
								Game.print("You don't have any items.");
							}
							else {
								for(Item item : this.player.getItems()) {
									Game.print("You have a " + item.detailDescription() + ".");
								}
							}
							break;
						case ActionSuicide:
							this.player.die();
							break;
						case ActionHelp:
							help();
							break;	
					}
					break;
					}
				case TYPE_UNKNOWN: {
					switch(a) {
						case ActionPass: {
							// intentionally blank
							break;
						}
						case ActionError: {
							Game.print("I don't understand that.");
							break;
						}
						case ActionUnknown: {
							Game.print("I don't understand that.");
							break;
						}
					}
					break;
				}
				default:
					Game.print("I don't understand that");
					break; 
				}
			}
		}catch(Exception n) {
			Game.print("I don't understand that \n\nException: \n" + n);
			n.printStackTrace();
			start();
		}

		Game.print("Quitting game...");
	}
	private void move(Action a) {
		this.player.move(a);
	}
	private Item containerForItem(Item item) {
		for(Item i : this.player.currentRoom.items) {
			if(i instanceof Hostable) {
				if(item == ((Hostable)i).installedItem() && item.isVisible()) {
					return i;
				}	
			}
		}
		return null;
	}
	private void help() {

		Game.print(" -- Text RPG Help Menu -- ");
		
		Game.print("To view your current items: type \"inventory\"");
		Game.print("To travel in a direction like north, type \"Go North\"");
		Game.print("You can inspect an inspectable item by typing \"Inspect <item>\"");
	}
	public static void print(String s) {
		for(String line : s.split("\n")) {
			int c = 0;
			for(String word : line.split(" ")) {
				System.out.print(word + " ");
				c += word.length();
				if(c > 80) {
					System.out.println();
					c = 0;
				}
			}
			System.out.println();
		}
	}
}
