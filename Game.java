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
		this.currentRoom = startingRoom ? startingRoom : new Room(); // should be beginning of game
	}

	public void start() {

		String input="";
		while(input.compareTo("quit") != 0) {
	
			System.out.print(">>>");
			input = this.scanner.next();
			Action a = this.interpreter.interpretString(input);
			switch(a) {
				case ActionGoEast:
					
					break; 
				case ActionGoWest:

					break; 
				case ActionGoNorth:

					break; 
				case ActionGoSouth:

					break; 
				case ActionLook:

					System.out.println(this.currentRoom);
					break;
				default:
					System.out.println("I don't understand that");
					break; 
			}
		}

		System.out.println("Quitting game...");
	}
}
