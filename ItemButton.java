public class ItemButton extends Item implements Pushable, Visible {

	public ItemButton(String s, String sd, String[] a) {
		super(s, sd, a);
	}
	public void push() {

	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean b) {
		this.visible = b;
	}
	protected boolean visible = true;
}
