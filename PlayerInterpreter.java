import java.util.Arrays;

public class PlayerInterpreter {

	public PlayerInterpreter () {

	}

	public Action interpretString(String string) {

		return action(string.toLowerCase().split(" "));
	}
	public Action action(String[] string) throws ArrayIndexOutOfBoundsException {
		
		if(string[0].compareTo("go") == 0 || string[0].compareTo("travel") == 0){
			String[] command = Arrays.copyOfRange(string, 1, string.length);
			return action(command);
		}
		else {

			// input could be northeast, put cpu in vax, throw shovel, examine bin
			
			String s = string[0];
			Action action = null;
			out:{
				for(Action a : Action.values()) {
					for(String alias : a.getAliases()) {

						if(s.compareTo(alias) == 0) {
							action = a;
							break out;
						}
					}
				}
			}
			if(action == null) {
				return Action.ActionUnknown;
			}
			switch(action.type()) {
				case TYPE_DIRECTIONAL:
					return action;
				case TYPE_HASDIRECTOBJECT:
					// test direct object
					// "throw SHOVEL"
					if(string.length > 1) {

						String d = string[1];
						Item item = null;
						for(Item i : Item.values()) {
							if(d.compareTo(i.toString()) == 0) {
								item = i;
								break;
							}
						}
						// item is the direct object of the action
						action.setDirectObject(item);
						return action;
					}
					else {
						System.out.println("You must supply a direct object");
						return Action.ActionError;
					}
				case TYPE_HASINDIRECTOBJECT:
				
					// test if it has indirect object
					// "put CPU IN VAX"
					break;
				case TYPE_HASNOOBJECT:
					return action;
				case TYPE_UNKNOWN:
					return Action.ActionUnknown;
				default:
					System.out.println("Unknown type");
					break;
			}
		}
		
		return Action.ActionUnknown;
	}
}
