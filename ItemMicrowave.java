public class ItemMicrowave extends Item implements Startable, Visible {

	public ItemMicrowave(String s, String sd, String[] a) {
		super(s, sd, a);
	}

	public void start() {
		System.out.println("Beep beep beep");
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean b) {
		this.visible = b;
	}
	protected boolean visible = true;
}
