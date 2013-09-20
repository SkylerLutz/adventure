public enum Action {

// No object
	ActionLook(new String[]{"look", "l"}, Type.TYPE_HASNOOBJECT),	
	ActionDig(new String[]{"dig"}, Type.TYPE_HASNOOBJECT),
	ActionJump(new String[]{"jump"}, Type.TYPE_HASNOOBJECT),

// Directional
	ActionGoEast(1, new String[]{"east", "e"}, Type.TYPE_DIRECTIONAL),
	ActionGoWest(-1, new String[]{"west", "w"}, Type.TYPE_DIRECTIONAL),
	ActionGoSouth(-2, new String[]{"south", "s"}, Type.TYPE_DIRECTIONAL),
	ActionGoNorth(2, new String[]{"north", "n"}, Type.TYPE_DIRECTIONAL),
	ActionGoNortheast(3, new String[]{"northeast", "ne"}, Type.TYPE_DIRECTIONAL),
	ActionGoNorthwest(-3, new String[]{"northwest", "nw"}, Type.TYPE_DIRECTIONAL),
	ActionGoSoutheast(4, new String[]{"southeast", "se"}, Type.TYPE_DIRECTIONAL),
	ActionGoSouthwest(-4, new String[]{"southwest", "sw"}, Type.TYPE_DIRECTIONAL),

// Diretct Object. Has one direct object e.g. Break shovel, throw lamp
	ActionPickUp(new String[]{"pickup", "pick up", "get"}, Type.TYPE_HASDIRECTOBJECT),
	ActionBreak(new String[]{"break", "smash", "destroy", "obliterate"}, Type.TYPE_HASDIRECTOBJECT),
	ActionInspect(new String[]{"inspect", "examine", "read", "view"}, Type.TYPE_HASDIRECTOBJECT),
	ActionDrop(new String[]{"throw", "chuck", "drop"}, Type.TYPE_HASDIRECTOBJECT),
	ActionShake(new String[]{"shake", "chickendance"}, Type.TYPE_HASDIRECTOBJECT),
	ActionViewItems(new String[]{"inventory", "items"}, Type.TYPE_HASNOOBJECT),

// Indirect Object. Has one direct object and one indirect object, e.g. Put cpu in computer
	ActionPut(new String[]{"put", "install"}, Type.TYPE_HASINDIRECTOBJECT),

// Misc
	ActionUnknown(666),
	ActionError(667);

// Constructors
	Action(int code) {
		this(code, new String[]{}, Type.TYPE_UNKNOWN);
	}
	Action(String[] aliases, Type type) {
		this(0, aliases, type);
	}
	Action(int code, String[] aliases, Type type) {
		this.code = code;
		this.aliases = aliases;
		this.type = type;
	}

// Getters and Setters
	int getCode() {
		return this.code;
	}
	String[] getAliases() {
		return this.aliases;
	}
	Type type() {
		return this.type;
	}

	void setDirectObject(Item directObject) {
		this.directObject = directObject;
	}
	Item directObject() {
		return this.directObject;
	}
	void setIndirectObject(Item indirectObject) {
		this.indirectObject = indirectObject;
	}
	Item indirectObject() {
		return this.indirectObject;
	}

// opposite directions are used for the directional enumeration constants.
	static Action getOppositeDirection(Action a) {

		Action result = null;
		for(Action oa : Action.values()) {
			if(oa.getCode() == -(a.getCode())) {
				result = oa;
				break;
			}
		}
		return result;
	}

	protected int code;
	protected String[] aliases;
	protected Type type;
	protected Item directObject;
	protected Item indirectObject;
};
enum Type {

	TYPE_DIRECTIONAL,
	TYPE_HASDIRECTOBJECT,
	TYPE_HASINDIRECTOBJECT,
	TYPE_HASNOOBJECT,
	TYPE_UNKNOWN;
}
