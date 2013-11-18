public class ItemFridge extends Item implements Pushable{

	public ItemFridge(String s, String sd, String[] a) {
		super(s, sd, a);
		this.wasPushed = false;
	}
// Pushable
	public void push() {
		if(!this.wasPushed) {
			if(this.relatedRoom != null && this.relatedRoom instanceof RoomObscured) {
				((RoomObscured)this.relatedRoom).setObscured(false);
				Game.print(((RoomObscured)this.relatedRoom).obscureMessage());
			}
			this.wasPushed = true;
		}
	}
	protected boolean wasPushed;
}
