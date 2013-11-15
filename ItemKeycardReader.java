public class ItemKeycardReader extends Item implements Hostable {

	public ItemKeycardReader(String s, String sd, String[] a) {
		super(s, sd, a);
		this.installMessage = null;
	}
	public void install(Item item) {
		this.installedItem = item;

		for(int i=0; i < 3; i++) {
			Game.print("...");
			try {
				Thread.sleep(1000);
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		if(this.installMessage != null) {
			Game.print(this.installMessage);
		}
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
	public void setInstallMessage(String s) {
		this.installMessage = s;
	}
	protected Item installedItem;
	protected String installMessage;
}
