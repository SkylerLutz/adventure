public class ItemBrick extends Item implements Holdable, Visible{

	public ItemBrick(String s, String sd, String[] a) {
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
