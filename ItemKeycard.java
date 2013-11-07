public class ItemKeycard extends Item implements Acquirable, Visible{

	public ItemKeycard(String s, String sd, String[] a) {
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
