public class ItemClayPot extends Item implements Destroyable, Holdable, Hostable, Visible {

	public ItemClayPot(String s, String sd, String[] a) {
		super(s, sd, a);
		this.installedItem = null;
	}
	public void setDestroyMessage(String s) {
		this.destroyMessage = s;
	}
	public void destroy() {
		System.out.println(destroyMessage);
	}
	public void setDisappears(boolean b) {
		this.disappears = b;
	}
	public boolean disappears() {
		return this.disappears;
	}
	public void install(Item i) {
		this.installedItem = i;
	}
	public boolean uninstall(Item i) {
		if(this.installedItem == null) {
			return false;
		}
		else if(this.installedItem == i) {
			this.installedItem = null;
			return true;
		}
		else {
			return false;
		}
	}
	public Item installedItem() {
		return this.installedItem;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean b) {
		this.visible = b;
	}
	protected String destroyMessage;

	protected boolean visible = true;
	protected Item installedItem;
	protected boolean disappears;
}
