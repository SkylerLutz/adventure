public class RoomObscured extends Room {

	public RoomObscured(String description, String shortDescription, Item obscuringItem) {
		super(description, shortDescription);
		this.obscuringItem = obscuringItem;
		this.isObscured = true;
		this.obscureMessage = null;
		this.unobscureMessage = null;
	}
	public boolean isObscured() {
		return this.isObscured;
	}
	public void setObscured(boolean obscured) {
		this.isObscured = obscured;
	}
	public void setUnobscureMessage(String s) {
		this.unobscureMessage = s;
	}
	public String unobscureMessage() {
		return this.unobscureMessage;
	}
	public void setObscureMessage(String s) {
		this.obscureMessage = s;
	}
	public String obscureMessage() {
		return this.obscureMessage;
	}
	protected Item obscuringItem;
	protected boolean isObscured;
	protected String obscureMessage;
	protected String unobscureMessage;
}
