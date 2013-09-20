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
					
					// view Inventory
					for(Item item : this.player.getItems()) {
						System.out.println("You have a " + item);
					}
					break;	
				case ActionPickUp:

					Item itemToTake = a.directObject();
					Item takenItem = this.player.currentRoom.takeItem(itemToTake);
					if (takenItem == null) {
						System.out.println("I don't see that here.");
						break;
					}
					this.player.pickup(takenItem);
					
					break;
				case ActionDig:
					break;
				case ActionBreak:
					break;
				case ActionInspect:
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
				case ActionJump:
					break;
				case ActionShake:
					break;
				case ActionError:
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
			this.player.currentRoom = this.player.currentRoom.getRoomForDirection(a);
			System.out.println(this.player.currentRoom);
		}
		else {
			System.out.println("You can't move that way");
		}
	}
}
