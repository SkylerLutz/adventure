public class RoomObscured extends Room {

	public RoomObscured(String description, String shortDescription, Item obscuringItem) {
		super(description, shortDescription);
		this.obscuringItem = obscuringItem;
		this.isObscured = true;
	}
	public boolean isObscured() {
		return this.isObscured;
	}
	public void setObscured(boolean obscured) {
		this.isObscured = obscured;
	}
	protected Item obscuringItem;
	protected boolean isObscured;
}
