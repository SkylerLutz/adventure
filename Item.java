public enum Item {

	ItemShovel("shovel"),
	ItemBrick("brick"),
	ItemFood("food"),
	ItemLadder("ladder"),
	ItemKey("key"),
	ItemUnknown;

	Item(String description) {
		this(description, false);
	}
	Item(String description, boolean permitsInstalledItems) {
		this.description = description;
		this.installedItem = null;
		this.permitsInstalledItems = permitsInstalledItems;
	}
	Item() {
		this("unknown item", false);
	}
	public String toString() {
		return description;
	}
	public void setInstalledItem(Item item) {
		if(this.permitsInstalledItems) {
			this.installedItem = item;
		}
		else {
			System.out.println("You cannot install an item into this " + this);
		}
	}
	public Item removeInstalledItem() {
		Item i = this.installedItem;
		this.installedItem = null;
		return i;
	}
	public boolean hasItemInstalled() {
		return this.installedItem != null;
	}
	public boolean canInstallItems() {
		return this.permitsInstalledItems;
	}
	public void setCanInstallItems(boolean permission) {
		this.permitsInstalledItems = permission;
	}
	public String detailDescription() {
		String s = "";
		if(this.installedItem != null) {
			s = ", with a " + this.installedItem + " installed";
		}
		return description + s;
	}
	protected String description;
	protected Item installedItem;
	protected boolean permitsInstalledItems;
}
