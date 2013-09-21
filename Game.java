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
			switch(a) {
				case ActionGoEast:
					move(a);
					break; 
				case ActionGoWest:
					move(a);
					break; 
				case ActionGoNorth:
					move(a);
					break; 
				case ActionGoSouth:
					move(a);
					break; 
				case ActionGoSoutheast: 
					move(a);
					break;
				case ActionGoSouthwest: 
					move(a);
					break;
				case ActionGoNortheast: 
					move(a);
					break;
				case ActionGoNorthwest: 
					move(a);
					break;
				case ActionLook:
					System.out.println(this.player.currentRoom.description());
					break;
				case ActionViewItems: 
					for(Item item : this.player.getItems()) {
						System.out.println("You have a " + item.detailDescription());
					}
					break;	
				case ActionPickUp:
					Item itemToTake = a.directObject();
					Item takenItem = this.player.currentRoom.takeItem(itemToTake);
					if (takenItem == null) {
						break;
					}
					this.player.pickup(takenItem);
					break;
				case ActionDig:
					System.out.println("There is nothing here to dig");
					break;
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
					
					Item itemToDrop= a.directObject();
					Item droppedItem = this.player.drop(itemToDrop);
					if (droppedItem == null) {
						System.out.println("No " + itemToDrop + " to drop.");
						break;
					}
					this.player.currentRoom.putItem(droppedItem);

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
				case ActionJump:
					break;
				case ActionShake:
					break;
				case ActionError:
					break;
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
						Item dropped = this.player.drop(itemToPut);
						this.player.currentRoom.installItemIntoItem(dropped, itemToBePutInto);
					}
					break;
				default:
					System.out.println("I don't understand that");
					break; 
			}
		}

		System.out.println("Quitting game...");
	}
	public void move(Action a) {
	
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
}
