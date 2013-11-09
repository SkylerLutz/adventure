public class ItemGold extends Item implements Holdable, Visible {

	public ItemGold(String s, String sd, String[] a) {
		super(s, sd, a);
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean b) {
		this.visible = b;
	}
	protected boolean visible = true;
}
