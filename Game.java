public class Game {

	protected java.util.Scanner scanner;
	protected PlayerInterpreter interpreter;
	protected Map map;
	
	public Game() {

		this.scanner = new java.util.Scanner(System.in);
		this.interpreter = new PlayerInterpreter();
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

					break;
				default:
					System.out.println("I don't understand that");
					break; 
			}
		}

		System.out.println("Quitting game...");
	}
}
