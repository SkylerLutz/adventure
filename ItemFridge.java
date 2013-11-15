public class ItemFridge extends Item implements Inspectable, Pushable{

	public ItemFridge(String s, String sd, String[] a) {
		super(s, sd, a);
		this.inspectMessage = null;
	}
// Inspectable
	public void setInspectMessage(String s) {
		this.inspectMessage = s;
	}
 	public void inspect() {
		Game.print(this.inspectMessage);
 	}
// Pushable
	public void push() {
		
		if(this.relatedRoom != null && this.relatedRoom instanceof RoomObscured) {
			((RoomObscured)this.relatedRoom).setObscured(false);
		}
	}
	protected String inspectMessage;
}
