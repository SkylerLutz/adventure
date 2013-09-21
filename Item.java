public enum Item {

	ItemShovel("shovel"),
	ItemBrick("brick"),
	ItemFood("food"),
	ItemLadder("ladder"),
	ItemKey("key"),
	ItemLock("lock", true, false),
	ItemGrafitti("grafitti", "It says: The cake is a lie", false),
	ItemLightSwitch("lightswitch", false, false),
	ItemUnknown;

	Item(String description) {
		this(description, false, true);
	}
	Item(String description, String detailDescription, boolean canBePickedUp) {
		this(description, false, canBePickedUp);
		this.detailDescription = detailDescription;
	}
	Item(String description, boolean permitsInstalledItems, boolean canBePickedUp) {
		this.description = description;
		this.installedItem = null;
		this.permitsInstalledItems = permitsInstalledItems;
		this.canBePickedUp = canBePickedUp;
	}
	Item() {
		this("unknown item", false, true);
	}
	public String toString() {
		return description;
	}
	public String toDetailString() {
		return this.detailDescription;
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
	public boolean canBePickedUp() {
		return this.canBePickedUp;
	}
	public String detailDescription() {
		String s = "";
		if(this.installedItem != null) {
			s = ", with a " + this.installedItem + " installed";
		}
		return description + s;
	}
	protected String description;
	protected String detailDescription;
	protected Item installedItem;
	protected boolean permitsInstalledItems;
	protected boolean canBePickedUp;
}
