public class ItemJunctionBox extends Item implements Destroyable, Visible {

	public ItemJunctionBox(String s, String sd, String[] a) {
		super(s, sd, a);
	}
	public void destroy() {
		if(this.canBeDestroyed) {
			System.out.println(this.destroyMessage);
			this.canBeDestroyed = false;
		}
		else {
			System.out.println("You have already short circuited this junction box.");
		}
	}
	public boolean disappears() { 
		return false;
	}
	public void setDestroyMessage(String s) {
		this.destroyMessage = s;
	}
	public void setDisappears(boolean b) {
		this.disappears = b;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean b) {
		this.visible = b;
	}
	protected boolean visible = true;
	protected boolean canBeDestroyed = true;
	protected boolean disappears;
	protected String destroyMessage;
}
