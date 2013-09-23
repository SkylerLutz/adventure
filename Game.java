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
		Room startingRoom = Map.level1();
		
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

		String input="";
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
						case ActionBreak:
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
							this.player.dropItem(a.directObject());
							break;
						case ActionShake:
							this.player.die();
							break;
						case ActionEnable:
							
							Item enabledItem = a.directObject();
							if(this.player.currentRoom.hasItem(enabledItem)) {
								System.out.println("Let there be light");
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
							}
							break;
							}

					break;
				case TYPE_HASNOOBJECT:
					switch(a) {
	
						case ActionLook:
							System.out.println(this.player.currentRoom.description());
							break;
						case ActionDig:
							System.out.println("There is nothing here to dig");
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
	
		if(this.player.currentRoom.canMoveToRoomInDirection(a)) {
			Room nextRoom = this.player.currentRoom.getRoomForDirection(a);
			this.player.currentRoom = nextRoom;
			System.out.println(this.player.currentRoom);
		}
		else {
			// test if requires key
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
