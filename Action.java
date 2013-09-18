public enum Action {

	ActionGoEast(1),
	ActionGoWest(-1),
	ActionGoSouth(-2),
	ActionGoNorth(2),

	ActionGoNortheast(3),
	ActionGoNorthwest(-3),
	ActionGoSoutheast(4),
	ActionGoSouthwest(-4),

	ActionLook(0),	

	ActionUnknown(666);

	Action(int code) {
		this.code = code;
	}
	int getCode() {
		return this.code;
	}


	
	/*
	static Action getOpposite(Action a) {

		return new Action(-1 * a.getCode());
	}
	*/
	protected int code;
};
