public class PlayerInterpreter {

	public PlayerInterpreter () {

	}

	public Action interpretString(String string) {

		if(isMoveCommand(string)) {
			
			return move(string);
		}
		else if(isActionCommand(string)) {
			
			return action(string);
		}
		return Action.ActionUnknown;
	}
	public boolean isMoveCommand(String string) {
		Action a;
		return (a = move(string)) != null && a != Action.ActionUnknown;
	}
	public Action move(String string) {
	
		String s = string.toLowerCase();

		if (s.compareTo("e") == 0 || s.compareTo("east") == 0) {

			return Action.ActionGoEast;
		}
		else if (s.compareTo("n") == 0 || s.compareTo("north") == 0) {

			return Action.ActionGoNorth;
		}
		else if (s.compareTo("w") == 0 || s.compareTo("west") == 0) {

			return Action.ActionGoWest;
		}
		else if (s.compareTo("s") == 0 || s.compareTo("south") == 0) {

			return Action.ActionGoSouth;
		}
		else if (s.compareTo("ne") == 0 || s.compareTo("northeast") == 0) {

			return Action.ActionGoNortheast;
		}
		else if (s.compareTo("nw") == 0 || s.compareTo("northwest") == 0) {

			return Action.ActionGoNorthwest;
		}
		else if (s.compareTo("sw") == 0 || s.compareTo("southwest") == 0) {

			return Action.ActionGoSouthwest;
		}
		else if (s.compareTo("se") == 0 || s.compareTo("southeast") == 0) {

			return Action.ActionGoSoutheast;
		}
		else if(new Character(s.charAt(0)).compareTo('g') == 0 && new Character(s.charAt(1)).compareTo('o') == 0) {
			String dir;
			try { 
				dir = s.substring(3, s.length());
				return move(dir);
			}
			catch(Exception e) {
				return Action.ActionUnknown;
			}
			
		}
		
		return null;
	}
	public Action action(String string) {

		String s = string.toLowerCase();
		if(s.compareTo("look") == 0 || s.compareTo("l") ==0 ) {
			return Action.ActionLook;
		}
		else if(s.compareTo("inventory") == 0 || s.compareTo("items") == 0) {
			return Action.ActionViewItems;
		}	
		return Action.ActionUnknown;
	}
	public boolean isActionCommand(String string) {

		return action(string) != null;
	}
}
