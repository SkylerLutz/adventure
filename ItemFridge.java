public class ItemFridge extends Item implements Pushable{

	public ItemFridge(String s, String sd, String[] a) {
		super(s, sd, a);
	}
// Pushable
	public void push() {
		
		if(this.relatedRoom != null && this.relatedRoom instanceof RoomObscured) {
			((RoomObscured)this.relatedRoom).setObscured(false);
		}
	}
}
