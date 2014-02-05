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
		ItemWatchMenu m4 = new ItemWatchMenu("Demo");

		//menu.add(m1);
		//menu.add(m2);
		menu.add(m3);
		menu.add(m4);

		System.out.println(menu.toString());

		int choice = 0;
		while(true) {
			System.out.print("> ");
			String input = this.scanner.nextLine();
			try {
				choice = Integer.parseInt(input) - 1;
			}
			catch(Exception e) {
				System.out.println("Invalid selection.");
				continue;
			}
			if(choice > menu.count() || choice < 0) {
				System.out.println("Invalid selection.");
				continue;
			}
			switch(choice) {
				/*case 0:
					return Map.level1();
				case 1:
					return Map.njit();
				*/case 0:
					return Map.mission();
				case 1:
					return Map.demo();
				default:
					System.out.println("Invalid selection.");
					continue;
			}
		}
	}
	public void start() throws NullPointerException {

		try {
		String input = "";
		while(input.compareTo("quit") != 0) {
			System.out.print("> ");
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
									System.out.println("Taken.");
									this.player.currentRoom.remove(o);
									this.player.pickup(o);
								}
								else {
									System.out.println("You cannot pick up this item.");
								}
							}
							else if((container = containerForItem(o)) != null) {
								
								System.out.println("Taken.");
								((Hostable)container).uninstall(o);
								this.player.pickup(o);
							}
							else if(this.player.hasItem(o)) {
								System.out.println("You already have that item in your inventory.");
							}
							else {
								System.out.println("I don't see that here.");
							}
							break;
						}
						case ActionBreak: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item) || this.player.hasItem(item)) {
								if(item instanceof Destroyable) {
									System.out.println("Smashed.");
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
									System.out.println("You cannot break this item.");
								}
							}
							else {
								System.out.println("I don't see that here.");
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
									System.out.println("You cannot inspect this item.");
								}
							}
							else {
								System.out.println("I don't see that here.");
							}
							break;
						}
						case ActionDrop: {
							Item item = a.directObject();
							if(this.player.hasItem(item)) {
								if(item instanceof Holdable) {
									System.out.println("Dropped.");
									this.player.drop(item);
									this.player.currentRoom.putItem(item);
								}
								else {
									System.out.println("You cannot drop this item.");
								}
							}
							else {
								System.out.println("You don't have that item to drop.");
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
									System.out.println("Thrown.");
									((Chuckable)item).chuck();
									this.player.drop(item);
									this.player.currentRoom.putItem(item);
								}
								else {
									System.out.println("You cannot throw this item.");
								}
							}
							else {
								System.out.println("You don't have that item to throw.");
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
									System.out.println("I don't know how to do that.");
								}
							}
							else {
								System.out.println("I don't see that here.");
							}
							break;
						}
						case ActionEnable: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item) || this.player.hasItem(item)) {
								if(item instanceof Startable) {
									System.out.println("Done.");
									((Startable)item).start();
								}
								else {
									System.out.println("I don't know how to do that.");
								}
							}
							else {
								System.out.println("I don't see that here.");
							}
							break;

						}
						case ActionPush: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item) || this.player.hasItem(item)) {
								if(item instanceof Pushable) {
									((Pushable)item).push();
									if(item.relatedRoom() instanceof RoomElevator) { // player is next to an elevator
										((RoomElevator)item.relatedRoom()).call(this.player.currentRoom);
									}
									else if(this.player.currentRoom instanceof RoomElevator) { // player is in an elevator
										((RoomElevator)this.player.currentRoom).call(Integer.parseInt(item.getAliases()[0])-1);
									}
									else {
										// for teleportation machine
										this.player.move(item.relatedRoom());
									}
								}
								else {
									System.out.println("Nothing happens.");
								}
							}
							else {
								System.out.println("I don't see that here.");
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
									if(item instanceof Holdable) {
										System.out.println("As you forcefully shove the " + a.directObject() + " down your throat, you begin to choke.");
										this.player.die();
									}
									else {
										System.out.println("That cannot be consumed.");
									}
								}
							}


							break;

						}

						case ActionWear: {
							Item item = a.directObject();
							if(this.player.currentRoom.hasItem(item)) {
								if(item instanceof Wearable) {
									System.out.println("Worn.");
									this.player.currentRoom.remove(item);
									this.player.wearDisguise(item);
								}
								else {
									System.out.println("You can not wear this item.");
								}
							}
							else if(this.player.disguise() == item) {
								System.out.println("You are already wearing that.");
							}
							else {
								System.out.println("I don't see that here.");
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
									System.out.println("You cannot kill this.");
								}
							}
							else {
								System.out.println("I don't see that here.");
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
									System.out.println("You cannot open this.");
								}
							}
							else {
								System.out.println("I don't see that here.");
							}
							break;
						}
						case ActionDetonate: {
							Item dynamite = a.directObject();
							if(this.player.hasItem(dynamite) || this.player.currentRoom.hasItem(dynamite)) {
								if(dynamite instanceof Explodable) {
									if(this.player.currentRoom.isAdjacentToRoom(dynamite.relatedRoom())) {
										((Explodable)dynamite).explode();
									}
									else {
										System.out.println("There isn't anything to blow up here.");
									}
								}		
								else {
									System.out.println("That item is not an explosive.");
								}	
							}	
							else {
								System.out.println("You do not have that item in your inventory.");
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
								System.out.println("You don't have that object in your inventory.");
								break;
							}
							else if(itemToBePutInto == null) {
								System.out.println("You must supply an indirect object.");
								break;
							}
							else if(!this.player.currentRoom.hasItem(itemToBePutInto)) {
								System.out.println("That object doesn't exist in this room.");
								break;
							}
							else if(itemToBePutInto instanceof ItemMagicBox && !(itemToPut instanceof Valuable)) {
								System.out.println("This item has no value--putting it in this " + itemToBePutInto + " will not score you any points.");
							}
							else if(!(itemToBePutInto instanceof Hostable) || !(itemToPut instanceof Installable)) {
								System.out.println("You cannot put a " + itemToPut + " into this " + itemToBePutInto);
							}
							else {
								System.out.println("Done.");
								this.player.drop(itemToPut);
								this.player.putItemInItem(itemToPut, itemToBePutInto);
							}
							break;
						}
						case ActionTake: {
							Item contents = a.directObject();
							Item container = a.indirectObject();
							if(!this.player.currentRoom.hasItem(container)) {
								System.out.println("I don't see that here.");
							}
							else if(!(container instanceof Hostable)) {
								System.out.println("You can't have an item inside that.");
							}		
							else {
								if(((Hostable)container).installedItem() == contents) {
									((Hostable)container).uninstall(contents);
									this.player.pickup(contents);
									System.out.println("Taken.");
								}	
								else {
									System.out.println("That item is not inside this " + container);
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
								System.out.println("You are not allowed to dig here");
							}

							break;
						case ActionClimb:
							move(Action.ActionGoUp);
							break;
						case ActionJump:
							move(Action.ActionGoDown);
							break;
						case ActionViewItems: 
							LinkedList<Item> items = this.player.getItems();
							if(this.player.disguise() != null) {
								System.out.println("You are wearing a " + this.player.disguise() + ".");
							}
							if (items.size() == 0) {
								System.out.println("You don't have any items.");
							}
							else {
								for(Item item : this.player.getItems()) {
									System.out.println("You have a " + item.detailDescription() + ".");
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
							System.out.println("I don't understand that.");
							break;
						}
						case ActionUnknown: {
							System.out.println("I don't understand that.");
							break;
						}
					}
					break;
				}
				default:
					System.out.println("I don't understand that");
					break; 
				}
			}
		}catch(Exception n) {
			System.out.println("I don't understand that \n\nException: \n" + n);
			n.printStackTrace();
			start();
		}

		System.out.println("Quitting game...");
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

		System.out.println(" -- Text RPG Help Menu -- ");
		
		System.out.println("To view your current items: type \"inventory\"");
		System.out.println("To travel in a direction like north, type \"Go North\"");
		System.out.println("You can inspect an inspectable item by typing \"Inspect <item>\"");
	}
	public static void print(String s) {
		/*
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
		}*/
		System.out.println(s);
	}
}
