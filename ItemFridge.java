public class ItemFridge extends Item implements Pushable, Visible {

	public ItemFridge(String s, String sd, String[] a) {
		super(s, sd, a);
	}
	public void push() {
		
		if(this.relatedRoom != null && this.relatedRoom instanceof RoomObscured) {
			((RoomObscured)this.relatedRoom).setObscured(false);
		}
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean b) {
		this.visible = b;
	}
	protected boolean visible = true;
}
