public class ItemClayPot extends Item implements Destroyable, Holdable, Hostable {

	public ItemClayPot(String s, String sd, String[] a) {
		super(s, sd, a);
		this.installedItem = null;
	}
	public void setDestroyMessage(String s) {
		this.destroyMessage = s;
	}
	public void destroy() {
		Game.print(destroyMessage);
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
	protected String destroyMessage;
	protected Item installedItem;
	protected boolean disappears;
}
