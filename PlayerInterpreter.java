public class PlayerInterpreter {

	public PlayerInterpreter () {

	}

	public Action interpretString(String string) {

		String s = string.toLowerCase();
		if(s.compareTo("east") == 0) {

			return Action.ActionGoEast;
		}
		if(s.compareTo("west") == 0) {

			return Action.ActionGoWest;
		}
		if(s.compareTo("north") == 0) {

			return Action.ActionGoNorth;
		}
		if(s.compareTo("south") == 0) {

			return Action.ActionGoSouth;
		}
		if(s.compareTo("look") == 0) {

			return Action.ActionLook;
		}
		return Action.ActionUnknown;
	}
}
