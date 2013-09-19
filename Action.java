public enum Action {

	ActionLook(0),	
	ActionGoEast(1),
	ActionGoWest(-1),
	ActionGoSouth(-2),
	ActionGoNorth(2),

	ActionGoNortheast(3),
	ActionGoNorthwest(-3),
	ActionGoSoutheast(4),
	ActionGoSouthwest(-4),


	ActionPickUp(5),
	ActionDig(6),
	ActionBreak(7),
	ActionInspect(8),
	ActionThrow(9),
	ActionJump(10),
	ActionShake(11),

	ActionUnknown(666);

	Action(int code) {
		this.code = code;
	}
	int getCode() {
		return this.code;
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
};
