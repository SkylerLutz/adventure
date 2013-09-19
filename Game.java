public class Game {

	protected java.util.Scanner scanner;
	protected PlayerInterpreter interpreter;
	protected Room currentRoom;
	
	public Game() {

		this(null);
	}
	public Game(java.io.File save) {
	
		// Parse room from file
		Room startingRoom = null;
		
		this.scanner = new java.util.Scanner(System.in);
		this.interpreter = new PlayerInterpreter();
		
		if(startingRoom != null) {
			this.currentRoom = startingRoom;
		}
		else { 
			this.currentRoom = Map.level1();
		}
	}

	public void start() {

		String input="";
		while(input.compareTo("quit") != 0) {
	
			System.out.print(">>>");
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

					System.out.println(this.currentRoom.description());
					break;
				default:
					System.out.println("I don't understand that");
					break; 
			}
		}

		System.out.println("Quitting game...");
	}
	public void move(Action a) {
	
		if(this.currentRoom.canMoveToRoomInDirection(a)) {
			this.currentRoom = this.currentRoom.getRoomForDirection(a);
			System.out.println(this.currentRoom);
		}
		else {
			System.out.println("You can't move that way");
		}
	}
}
