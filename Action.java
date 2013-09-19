public enum Action {

	ActionLook(0, new String[]{"look", "l"}),	
	ActionGoEast(1, new String[]{"east", "e"}),
	ActionGoWest(-1, new String[]{"west", "w"}),
	ActionGoSouth(-2, new String[]{"south", "s"}),
	ActionGoNorth(2, new String[]{"north", "n"}),

	ActionGoNortheast(3, new String[]{"northeast", "ne"}),
	ActionGoNorthwest(-3, new String[]{"northwest", "nw"}),
	ActionGoSoutheast(4, new String[]{"southeast", "se"}),
	ActionGoSouthwest(-4, new String[]{"southwest", "sw"}),


	ActionPickUp(5, new String[]{"pickup", "pick up", "get"}),
	ActionDig(6, new String[]{"dig"}),
	ActionBreak(7, new String[]{"break", "destroy"}),
	ActionInspect(8, new String[]{"inspect", "examine"}),
	ActionThrow(9),
	ActionJump(10),
	ActionShake(11),
	ActionViewItems(12, new String[]{"inventory", "items"}),

	ActionUnknown(666);

	Action(int code) {
		this(code, new String[]{});
	}
	Action(int code, String[] aliases) {
		this.code = code;
		this.aliases = aliases;
	}
	int getCode() {
		return this.code;
	}
	String[] getAliases() {
		return this.aliases;
	}

	
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
};
