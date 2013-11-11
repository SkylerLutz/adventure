public class ItemKeycardReader extends Item implements Hostable, Visible{

	public ItemKeycardReader(String s, String sd, String[] a) {
		super(s, sd, a);
	}
	public void install(Item item) {
		this.installedItem = item;

		for(int i=0; i < 3; i++) {
			System.out.println("...");
			try {
				Thread.sleep(1000);
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Authentication Complete.");
		System.out.println("The junction box door swings open.");
		((Visible)this.relatedItem).setVisible(true);
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
	protected boolean visible = true;
	protected Item installedItem;
}
