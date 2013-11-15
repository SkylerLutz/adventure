public class ItemMicrowave extends Item implements Hostable, Startable {

	public ItemMicrowave(String s, String sd, String[] a) {
		super(s, sd, a);
		this.installedItem = null;
	}

	public void start() {
		
		for(int i=0; i < 3; i++) {
			Game.print("...");
			try {
				Thread.sleep(1000);
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		Game.print("Beep beep beep");
		if(this.installedItem instanceof Meltable) {
			Item item = ((Meltable)this.installedItem).meltItem();
			Game.print("You melted the " + this.installedItem.detailDescription() + ", and it revealed a " + item.detailDescription() + "!");
			this.installedItem = item;
		}
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
	protected Item installedItem;
}
